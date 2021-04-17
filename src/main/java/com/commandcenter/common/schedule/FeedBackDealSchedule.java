package com.commandcenter.common.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.HttpRequest;
import com.commandcenter.common.wcf.WcfClient;
import com.commandcenter.model.casemodel.MpaCaseOprInfo;
import com.commandcenter.model.casemodel.VcstCaseFeedback;
import com.commandcenter.model.wcf.FeedbackModelForWcf;
import com.commandcenter.service.caseservice.VcstCaseFeedbackService;
import com.commandcenter.service.caseservice.VcstCaseInfoService;
import com.commandcenter.service.iccservice.IccService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author r25437
 * @create 2018-12-10 9:03
 * @desc 反馈处理后台进程
 **/
@Component
public class FeedBackDealSchedule {

    protected static Logger logger = LoggerFactory.getLogger(FeedBackDealSchedule.class);

    @Autowired
    private IccService iccService;

    @Autowired
    private VcstCaseFeedbackService vcstCaseFeedbackService;

    @Autowired
    private VcstCaseInfoService vcstCaseInfoService;

    @Value("${system.version.icc}")
    private String ICC_VERSION;

    @Value("${system.notice.app.feedBackMQ}")
    private String feedBackMQ;

    @Value("${feedbackschedule.enable}")
    private String scheduledEnable;

    @Value("${vcs.http.url}")
    private String vcsHttpUrl;

    @Value("${vcs.http.port}")
    private String vcsHttpPort;

    private static int corePoolSize ;

    @Value("${system.process.num}")
    private int processNum;

    private static Long keepAliveTime = 10L;

    private static ThreadPoolExecutor executor;

    @PostConstruct
    public void init() {
        corePoolSize = processNum;
        executor  = new ThreadPoolExecutor(corePoolSize, corePoolSize,
                keepAliveTime, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1000));
        //System.out.println("---初始化开放平台api参数---appKey---"+APP_KEY+"---appSecret---"+APP_SECRET+"---url---"+REQUEST_URL+"---");
    }

    @PreDestroy
    public void destroy(){
        if(executor != null) {
            executor.shutdownNow();
        }
    }

    @Scheduled(cron = "0/1 * * * * ?")
//    @Scheduled(cron = "0/59 * * * * ?")
    public void dealCaseFeedBackScheduled(){
        if(!Boolean.parseBoolean(scheduledEnable)){
            return;
        }
//        logger.info("dealCaseFeedBackScheduled start =====================");
        CountDownLatch latch = new CountDownLatch(corePoolSize);
        try {
            //使用execute方法
            for (int i = 0; i < corePoolSize; i++) {
                executor.execute(new Stats(i, latch));
//                logger.info("dealCaseFeedBackScheduled start ====================="+i);
            }
            latch.await();// 等待所有人任务结束
//            logger.info("dealCaseFeedBackScheduled end ====================="+executor.getActiveCount()+","+executor.getTaskCount());
        }catch (Exception e){
            logger.info("dealCaseFeedBackScheduled方法抛出异常============",e);
            e.printStackTrace();
        }
    }

    private void dealFeedBack(int modNum){
        Map<String,Object> paramMap = new HashMap<>(2);
        paramMap.put("modNum",modNum);
        paramMap.put("totalNum",corePoolSize);
        List<MpaCaseOprInfo> mpaCaseOprInfoList = vcstCaseInfoService.selectMpaCaseOprListByMod(paramMap);
        Map<String,String> vcsQueryMap = new HashMap<>(2);
        logger.info("dealFeedBack begin modNum="+modNum+",mpaCaseOprInfoList="+mpaCaseOprInfoList.size());
        //由于警情会存在多条反馈，在这里做一下剔重！取出的500条警情中，只进行一个警情的反馈处理即可！其余的反馈处理均重复
        List<String> caseList = mpaCaseOprInfoList.stream().map(MpaCaseOprInfo::getCaseId).distinct().collect(Collectors.toList());
        for(String caseId : caseList) {
            try {
                //获取警情信息，如果过来的警情id查询的警情为空，则不做任何处理
                int count = vcstCaseInfoService.selectVcstCaseInfoCountById(caseId);
                if (count == 0) {
                    continue;
                }
                //向VCS请求反馈列表
                vcsQueryMap.clear();
                vcsQueryMap.put("caseId",caseId);
                String vcsResponseJson = HttpRequest.sendGet(vcsHttpUrl+":"+vcsHttpPort+Constant.VCS_GET_HANDLE_RECORD_LIST,vcsQueryMap);
                JSONObject vcsResponseJsonObj = JSON.parseObject(vcsResponseJson);
                String json = vcsResponseJsonObj.getJSONObject("Result").getJSONArray("Records").toJSONString();
                logger.info("vcs请求反馈json----"+JSON.toJSONString(vcsResponseJsonObj));
                List<FeedbackModelForWcf> feedbackList = JSONObject.parseArray(json, FeedbackModelForWcf.class);
                for (FeedbackModelForWcf feedbackModelForWcf : feedbackList) {
                    feedbackModelForWcf.setCaseID(caseId);
                }
                /*
                List<FeedbackModelForWcf> feedbackList = WcfClient.getHandleRecordList(caseId);
                */
                if (null == feedbackList || feedbackList.size() == 0) {
                    continue;
                }
                //增量信息发送ICC系统MQ
                if (Constant.ICC_SEND_FEEDBACK_NOTIFY_VERSION.equals(ICC_VERSION)) {
                    iccService.sendVcsFeedBackInfoToIcc(caseId, feedbackList);
                }

                List<VcstCaseFeedback> vcstCaseFeedbackList = new ArrayList<>();
                for (FeedbackModelForWcf modelForWcf : feedbackList) {
                    if (null == modelForWcf.getID() || "".equals(modelForWcf.getID())) {
                        continue;
                    }
                    if ("1.0".equals(modelForWcf.getType())) {
                        continue;
                    }
                    vcstCaseFeedbackList.add(modelForWcf.parseToFeedbackModel());
                }
                vcstCaseFeedbackService.batchInsertFeedback(vcstCaseFeedbackList);
                //下发警情提醒
                vcstCaseInfoService.sendCaseInfoToStaffApp(caseId, Constant.DISPATCH_TYPE.STAFF.getValue(), feedBackMQ);
            }catch (Exception e){
                e.printStackTrace();
                logger.error("dealFeedBack error caseId="+caseId+",error="+e.getMessage());
                continue;
            }
        }

        //最后统一处理本次取出的批量数据
        List<Long> idList = mpaCaseOprInfoList.stream().map(MpaCaseOprInfo::getId).collect(Collectors.toList());
        if(idList!=null && idList.size()>0) {
            vcstCaseInfoService.deleteMpaCaseOprInfoByIds(idList);
        }
        logger.info("dealFeedBack end modNum="+modNum+",mpaCaseOprInfoList="+mpaCaseOprInfoList.size());
    }

    class Stats implements Runnable  {
        int modNum;
        CountDownLatch latch;

        public Stats(int modNum, CountDownLatch latch) {
            this.modNum = modNum;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                dealFeedBack(modNum);
            } catch (Exception e) {
                logger.info("Stats线程抛出异常",e);
                e.printStackTrace();
            }finally {
                latch.countDown();
            }
        }
    }
}
