package com.commandcenter.common.activemq.listener.vcs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.model.casemodel.CaseModelForVcsMq;
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
 * @desc 警情监听,现场接警
 **/
@Component
public class QueueAlarmCaseListener {
    protected final static Logger logger = LoggerFactory.getLogger(QueueAlarmCaseListener.class);
    @Autowired
    private VcstCaseInfoService vcstCaseInfoService;

    @Value("${system.notice.app.notifyCaseMQ}")
    private String notifyCase;

    @JmsListener(destination = "queue_mpa_alarm_case_info" ,containerFactory = "jmsQueueListenerMpa")
    public void receiveAlarmCase(ActiveMQBytesMessage text) {
        try {
            logger.info("QueueAlarmCaseListener收到了消息：\t" + new String(text.getContent().getData(), "utf-8"));
            String mesString = new String(text.getContent().getData(), "utf-8");
            //判断数据类型(JSONArray or JSONObject)， 为JSONObject则手动转换为JSONArray
            if (!mesString.startsWith("[")) {
                mesString = "[" + mesString + "]";
            }
            JSONArray array = JSONArray.parseArray(mesString);
            if (array.size() < 1) {
                return;
            }
            for (int i = 0; i < array.size(); i++) {
                JSONObject object = array.getJSONObject(0);
                CaseModelForVcsMq caseModelForVcsMq = JSONObject.toJavaObject(object, CaseModelForVcsMq.class);
                VcstCaseInfo vcstCaseInfo = caseModelForVcsMq.parseToCaseModel();
                //由于ICC在新建警情时，会下发3条警情。其中前两条警情并非有效警情，需要过滤。
                //定义的规则为，如果报警人为空，则默认是无效警情，抛弃即可
                if(vcstCaseInfo.getContact()==null || "".equals(vcstCaseInfo.getContact())){
                    continue;
                }

                vcstCaseInfoService.addCaseAndDispatchs(vcstCaseInfo);

                logger.info("给单位发送警情MQ"+ JSON.toJSONString(vcstCaseInfo));
                //给单位发送警情MQ
                vcstCaseInfoService.sendCaseInfoToStaffApp(vcstCaseInfo.getId(), Constant.DISPATCH_TYPE.ORG.getValue(), notifyCase);
            }
        }catch (Exception e){
            logger.error("插入警情失败，e:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
