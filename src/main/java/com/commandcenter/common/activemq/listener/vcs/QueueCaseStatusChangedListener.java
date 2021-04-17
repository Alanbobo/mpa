package com.commandcenter.common.activemq.listener.vcs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.activemq.component.PublishToIcc;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.model.casemodel.VcstCaseInfo;
import com.commandcenter.service.caseservice.VcstCaseInfoService;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author r25437
 * @create 2018-09-17 10:04
 * @desc 警情监听，退单状态改为JQZT003
 * 2020-09-18 10:04 170726638
 * 状态 JQZT003，推送组织机构退单通知，新增mq主题
 **/
@Component
public class QueueCaseStatusChangedListener {
    protected final static Logger logger = LoggerFactory.getLogger(QueueCaseStatusChangedListener.class);

    @Autowired
    private VcstCaseInfoService vcstCaseInfoService;

    @Autowired
    private PublishToIcc publishToIcc;

    @Value("${system.version.icc}")
    private String ICC_VERSION;

    @Value("${system.notice.app.caseStatusChangeMQ}")
    private String caseStatusChangeMQ;

    @Value("${system.notice.app.backAlarmStatusChange}")
    private String backAlarmStatusChange;

    @JmsListener(destination = "queue_mpa_case_status_changed" ,containerFactory = "jmsQueueListenerMpa")
    public void receiveAlarmCase(ActiveMQBytesMessage text) {
        try {
            String mesString = new String(text.getContent().getData(), "utf-8");
            logger.info("QueueCaseStatusChangedListener收到了消息：\t" + mesString);JSONArray array = JSONArray.parseArray(mesString);
            for(int i=0;i<array.size();i++) {
                JSONObject jsonObject = array.getJSONObject(i);

                VcstCaseInfo vcstCaseInfo = new VcstCaseInfo();
                vcstCaseInfo.setId(jsonObject.get("id").toString());
                vcstCaseInfo.setStatus(jsonObject.get("status").toString());

                //如果警情处置完毕，并且icc的版本是1.0，通知ICC
                if(Constant.ALARM_STATUS.FINISHED.getValue().equals(vcstCaseInfo.getStatus()) &&
                        Constant.ICC_SEND_FEEDBACK_NOTIFY_VERSION.equals(ICC_VERSION)){
                    publishToIcc.sendCaseFinishToIcc(vcstCaseInfo.getId());
                }

                //因为vcs会推送多次消息过来，判断，如果当前警情状态与推送消息状态相符，不进行修改
                VcstCaseInfo vcstCaseInfoExists = vcstCaseInfoService.selectVcstCaseInfoById(vcstCaseInfo.getId());
                if(vcstCaseInfoExists != null && vcstCaseInfoExists.getStatus().equals(vcstCaseInfo.getStatus())){
//                    return;
                }

                //修改警情状态
                vcstCaseInfoService.updateNonEmptyVcstCaseInfoById(vcstCaseInfo);

                if(Constant.ALARM_STATUS.FINISHED.getValue().equals(vcstCaseInfo.getStatus())){
                    //如果是处置完成状态，推送最新警情信息给组织机构
                    vcstCaseInfoService.sendCaseInfoToStaffApp(vcstCaseInfo.getId(), Constant.DISPATCH_TYPE.ORG.getValue(), caseStatusChangeMQ);
                }else if(Constant.ALARM_STATUS.BACK_ALARM.getValue().equals(vcstCaseInfo.getStatus())){
                    //如果是退单状态，推送最新警情信息给组织机构
                    vcstCaseInfoService.sendCaseInfoToStaffApp(vcstCaseInfo.getId(), Constant.DISPATCH_TYPE.ORG.getValue(), backAlarmStatusChange);
                }else {
                    //推送最新警情信息给APP
                    vcstCaseInfoService.sendCaseInfoToStaffApp(vcstCaseInfo.getId(), Constant.DISPATCH_TYPE.STAFF.getValue(), caseStatusChangeMQ);
                }
            }
        }catch (Exception e){
            logger.error("警情状态变更失败，e:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
