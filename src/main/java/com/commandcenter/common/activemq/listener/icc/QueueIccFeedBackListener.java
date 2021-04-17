package com.commandcenter.common.activemq.listener.icc;

import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.service.iccservice.IccService;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author r25437
 * @create 2018-09-17 10:04
 * @desc ICC出境单反馈监听
 **/
@Component
public class QueueIccFeedBackListener {
    protected final static Logger logger = LoggerFactory.getLogger(QueueIccFeedBackListener.class);

    @Autowired
    private IccService iccService;

    @JmsListener(destination = "icc_update_feedback" ,containerFactory = "jmsQueueListenerIcc")
    public void receiveAlarmCase(ActiveMQBytesMessage text) {
        try {
            logger.info("QueueIccFeedBackListener收到了消息：\t" + new String(text.getContent().getData(), "utf-8"));
            String mesString = new String(text.getContent().getData(), "utf-8");
            String caseId = JSONObject.parseObject(mesString).getJSONObject("body").getString("alarm_id");
            String bodyStr = JSONObject.parseObject(mesString).getString("body");
            //下发警情干系警员
            iccService.sendIccFeedBackInfoToStaffApp(caseId, Constant.DISPATCH_TYPE.STAFF.getValue(),bodyStr);
        }catch (Exception e){
            logger.error("接收综合反馈失败，e:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
