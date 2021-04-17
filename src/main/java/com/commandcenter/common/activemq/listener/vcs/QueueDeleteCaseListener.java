package com.commandcenter.common.activemq.listener.vcs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.activemq.component.PublishToAPP;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.model.casemodel.MpatDispatch;
import com.commandcenter.model.smp.SmpUserInfo;
import com.commandcenter.service.caseservice.MpatDispatchService;
import com.commandcenter.service.caseservice.VcstCaseInfoService;
import com.commandcenter.service.smp.SmpUserService;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author r25437
 * @create 2018-09-17 10:04
 * @desc 警情置为无效
 **/
@Component
public class QueueDeleteCaseListener {
    protected final static Logger logger = LoggerFactory.getLogger(QueueDeleteCaseListener.class);

    @Autowired
    private VcstCaseInfoService vcstCaseInfoService;

    @Autowired
    private PublishToAPP publishToAPP;

    @Autowired
    private SmpUserService smpUserService;

    @Autowired
    private MpatDispatchService mpatDispatchService;

    @Value("${system.notice.app.deleteCaseMQ}")
    private String deleteCaseMQ;

    @JmsListener(destination = "case_disposal_info_changed_del" ,containerFactory = "jmsQueueListenerMpa")
    public void receiveAlarmCase(ActiveMQTextMessage text) {
        try {
            String mesString = new String(text.getContent().getData(), "utf-8");
            logger.info("QueueDeleteCaseListener收到了消息：\t" + mesString);
            JSONObject root = JSONObject.parseObject(mesString);
            JSONObject mqBody = (JSONObject) root.get("body");
            String caseId = mqBody.getString("id");
            String[] orgIds = (String[])mqBody.get("dispatch_orgs");
            for(String orgId : orgIds){
                List<String> userNos = new ArrayList<>();
                List<SmpUserInfo> smpUserInfoList = smpUserService.selectUserByOrgGuid(orgId);
                for(SmpUserInfo smpUserInfo:smpUserInfoList){
                    if (Constant.tokenMapIns.get(smpUserInfo.getUserCode()) != null) {
                        String tempNo = (String) Constant.tokenMapIns.get(smpUserInfo.getUserCode());
                        userNos.add(tempNo);
                    }
                }
                //下发警情信息给APP
                Map<String,Object> map = new HashMap<>();
                map.put("alarmid",caseId);
                MQParamModel param = new MQParamModel(JSON.toJSONString(map), deleteCaseMQ);
                param.setPersonalFlag(false);
                param.setQueueFlag(false);
                param.setQueueFlag(false);
                param.setPersonalFlag(true);
                param.setUserNos(userNos);
                logger.info("发送退单警情mq给app,警情id为：" + caseId);

                //发送警情信息
                publishToAPP.sendToApp(param);

                //进行mpa_t_dispatch表的删除，查询此表中，是否有caseId和orgId对应的数据，如果有，就删除
                MpatDispatch mpatDispatch = new MpatDispatch();
                mpatDispatch.setCaseId(caseId);
                mpatDispatch.setOrgGuid(orgId);
                List<MpatDispatch> mpatDispatchList = mpatDispatchService.selectMpatDispatchIsExist(mpatDispatch);
                if(mpatDispatchList != null && mpatDispatchList.size()>0){
                    mpatDispatchList.stream().forEach(t -> mpatDispatchService.deleteMpatDispatchById(t.getId()));
                }
            }
            //判断mpa_t_dispatch表中是否存在该警情的记录，如果不存在，删除警情信息表
            MpatDispatch mpatDispatch = new MpatDispatch();
            mpatDispatch.setCaseId(caseId);
            List<MpatDispatch> mpatDispatchList = mpatDispatchService.selectMpatDispatchIsExist(mpatDispatch);
            if(mpatDispatchList != null && mpatDispatchList.size()>0){
                //证明还有警情记录，此时警情记录表无需处理
            }else{
                //证明无警情调派信息，此时删除警情即可
                vcstCaseInfoService.deleteVcstCaseInfoById(caseId);
            }
        }catch (Exception e){
            logger.error("插入警情失败，e:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
