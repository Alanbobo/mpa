package com.commandcenter.service.iccservice.impl;

import com.alibaba.fastjson.JSON;
import com.commandcenter.common.activemq.component.PublishToAPP;
import com.commandcenter.common.activemq.component.PublishToIcc;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.dao.casedao.MpatDispatchDao;
import com.commandcenter.dao.smp.SmpUserInfoMapper;
import com.commandcenter.model.casemodel.MpatDispatch;
import com.commandcenter.model.casemodel.VcstCaseFeedback;
import com.commandcenter.model.casemodel.VcstCaseInfo;
import com.commandcenter.model.smp.SmpUserInfo;
import com.commandcenter.model.smp.vo.UserModel;
import com.commandcenter.model.wcf.FeedbackModelForWcf;
import com.commandcenter.service.caseservice.VcstCaseFeedbackService;
import com.commandcenter.service.caseservice.VcstCaseInfoService;
import com.commandcenter.service.iccservice.IccService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author r25437
 * @create 2018-10-26 13:57
 * @desc icc信息处理服务
 **/
@Service("iccService")
public class IccServiceImpl implements IccService {
    Logger logger = Logger.getLogger(IccServiceImpl.class);

    @Autowired
    private VcstCaseInfoService vcstCaseInfoService;

    @Autowired
    private MpatDispatchDao mpatDispatchDao;

    @Autowired
    private SmpUserInfoMapper smpUserInfoMapper;

    @Autowired
    private PublishToAPP publishToAPP;

    @Autowired
    private PublishToIcc publishToIcc;

    @Autowired
    private VcstCaseFeedbackService vcstCaseFeedbackService;

    @Value("${system.notice.app.iccFeedBackMQ}")
    private String iccFeedBackMQ;

    /**
     * 推送处警信息给APP
     * @param caseId
     * @return
     */
    @Override
    public void sendIccFeedBackInfoToStaffApp(String caseId,String sendType, String message){
        //获取警情信息
        VcstCaseInfo vcstCaseInfo = vcstCaseInfoService.selectVcstCaseInfoById(caseId);
        //获取调派信息
        if(vcstCaseInfo!=null){
            List<MpatDispatch> mpatDispatchList = mpatDispatchDao.selectMpatDispatchByCaseId(caseId);
            if(mpatDispatchList!=null && mpatDispatchList.size()>0){
                vcstCaseInfo.setDispatchModels(mpatDispatchList);
            }
        }
        //获取调派数据
        List<MpatDispatch> dispatchList = vcstCaseInfo.getDispatchModels();
        //有调派数据，下发
        if(dispatchList!=null){
            //按照组织机构以及按照警员下发，仅仅是userNos列表不同，其余内容均相同
            List<String> userNos = new ArrayList<>();
            for(MpatDispatch mpatDispatch:dispatchList) {
                //按照组织机构下发时，按照组织机构查询警员，警员在线则下发
                if(Constant.DISPATCH_TYPE.ORG.getValue().equals(sendType)){
                    List<SmpUserInfo> smpUserInfoList = smpUserInfoMapper.selectUserByOrgGuid(mpatDispatch.getOrgGuid());
                    for(SmpUserInfo smpUserInfo:smpUserInfoList){
                        if (Constant.tokenMapIns.get(smpUserInfo.getUserCode()) != null) {
                            String tempNo = (String) Constant.tokenMapIns.get(smpUserInfo.getUserCode());
                            userNos.add(tempNo);
                        }
                    }
                    //获取下发用户列表，存在才下发
                    //下发警情给APP
                    sendIccFeedBackInfoToApp(message,userNos);
                }
                //按照个人下发时，按照调派警员，警员在线则下发
                else{
                    //判断调派类型是否与个人调派相符
                    //是不是警员调派类型
                    boolean isStaffDispatch = Constant.DISPATCH_TYPE.STAFF.getValue().equals(mpatDispatch.getType());
                    //警员是否已经取消调派
                    boolean staffIsNotCancel = !(mpatDispatch.getIscancel() != null && "1".equals(mpatDispatch.getIscancel()));
                    if( isStaffDispatch && staffIsNotCancel){
                        UserModel userModel = smpUserInfoMapper.selectUserByStaffGuid(mpatDispatch.getStaffGuid());
                        if (Constant.tokenMapIns.get(userModel.getUserCode()) != null) {
                            String tempNo = (String) Constant.tokenMapIns.get(userModel.getUserCode());
                            userNos.add(tempNo);
                        }
                    }
                    //获取下发用户列表，存在才下发
                    //下发警情给APP
                    sendIccFeedBackInfoToApp(message,userNos);
                }
            }
        }
    }

    /**
     *  sendIccFeedBackInfoToApp
     * @param message
     * @param userNos
     * 下发ICC反馈信息给userNos列表用户
     */
    private void sendIccFeedBackInfoToApp(String message,List<String> userNos){
        //获取下发用户列表，存在才下发
        if(userNos.size()>0) {

            MQParamModel param = new MQParamModel(message, iccFeedBackMQ);
            param.setQueueFlag(false);
            param.setPersonalFlag(true);
            param.setUserNos(userNos);
            //发送MQ
            logger.info("通过文件通道给["+iccFeedBackMQ+"]发送消息,param:" + JSON.toJSONString(param));
            logger.info("userNos=" + userNos);
            //发送警情信息
            publishToAPP.sendToApp(param);
        }else {
            //发送MQ
            logger.info("下发ICC警单反馈信息，调派列表为空！");
        }
    }

    /**
     * VCS新反馈推送ICC，增量推送
     * @param caseId
     * @return
     */
    @Override
    public void sendVcsFeedBackInfoToIcc(String caseId, List<FeedbackModelForWcf> feedbackModelForWcfList){
        List<FeedbackModelForWcf> publishList = new ArrayList<>();
        //通过caseId获取当前的警情反馈
        VcstCaseFeedback vcstCaseFeedback = new VcstCaseFeedback();
        vcstCaseFeedback.setCaseId(caseId);
        logger.info("selectVcstCaseFeedbackByObj begin==========="+caseId);
        List<VcstCaseFeedback> vcstCaseFeedbackList = vcstCaseFeedbackService.selectVcstCaseFeedbackByObj(vcstCaseFeedback);
        logger.info("selectVcstCaseFeedbackByObj end==========="+caseId);
        if(vcstCaseFeedbackList!=null && vcstCaseFeedbackList.size()>0){
            List<String> idList = vcstCaseFeedbackList.stream().map(VcstCaseFeedback::getId).collect(Collectors.toList());
            publishList = feedbackModelForWcfList.stream().filter(t -> !idList.contains(t.getID())).collect(Collectors.toList());
        }else{
            publishList = feedbackModelForWcfList;
        }

        logger.info("sendVcsFeedBackToIcc begin==========="+caseId);
        publishToIcc.sendVcsFeedBackToIcc(publishList);
        logger.info("sendVcsFeedBackToIcc end==========="+caseId);
    }
}
