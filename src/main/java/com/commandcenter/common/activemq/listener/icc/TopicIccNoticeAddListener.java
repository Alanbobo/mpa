package com.commandcenter.common.activemq.listener.icc;

import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.activemq.component.PublishToAPP;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.service.iccservice.IccService;
import com.commandcenter.service.smp.SmpUserService;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author r25437
 * @create 2018-09-17 10:04
 * @desc 公告通知
 **/


@Component
public class TopicIccNoticeAddListener {
    protected final static Logger logger = LoggerFactory.getLogger(TopicIccNoticeAddListener.class);

    @Autowired
    private IccService iccService;

    @Autowired
    private SmpUserService smpUserService;

    @Value("${system.notice.app.approvalCreate}")
    private String approvalCreate;

    @Autowired
    private PublishToAPP publishToAPP;

    @JmsListener(destination = "topic_notice_sync" ,containerFactory = "jmsTopicListenerIcc")
    public void receiveAlarmCase(ActiveMQBytesMessage text) {
        try {
            logger.info("TopicIccNoticeAddListener收到了消息：\t" + new String(text.getContent().getData(), "utf-8"));
            String noticeString = new String(text.getContent().getData(), "utf-8");
            JSONObject noticeJsonObj = JSONObject.parseObject(noticeString).getJSONObject("body");
            String bodyStr = JSONObject.parseObject(noticeString).getString("body");

//            List<String> leaderList = new ArrayList<>();
//            String targetDeptCode = noticeJsonObj.getString("target_dept_code");

            MQParamModel param = new MQParamModel(bodyStr, approvalCreate);
            param.setEncryptFlag(true);
            publishToAPP.sendToApp(param);

            //所有人都接收通知此处不需要
           /* if (targetDeptCode != null && !"".equals(targetDeptCode)) {
                List<String> leaderCodes = smpUserService.selectLeaderCodeByOrgGovCode(targetDeptCode);
                if (leaderCodes != null && !leaderCodes.isEmpty()) {
                    for (String userCode : leaderCodes) {
                        if (Constant.tokenMapIns.get(userCode) != null) {
                            String tempNo = (String) Constant.tokenMapIns.get(userCode);
                            leaderList.add(tempNo);
                        }
                    }
                }
                param.setPersonalFlag(true);
                param.setUserNos(leaderList);
                publishToAPP.sendToApp(param);
            }else{
                publishToAPP.sendToApp(param);
            }*/

        }catch (Exception e){
            logger.error("接收综合反馈失败，e:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
