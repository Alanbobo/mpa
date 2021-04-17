package com.commandcenter.common.activemq.listener.vcs;

import com.alibaba.fastjson.JSONArray;
import com.commandcenter.model.casemodel.MpaCaseOprInfo;
import com.commandcenter.service.caseservice.VcstCaseInfoService;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author r25437
 * @create 2018-09-17 10:04
 * @desc 反馈监听
 **/
@Component
public class QueueFeedbackListener {
    protected final static Logger logger = LoggerFactory.getLogger(QueueFeedbackListener.class);

    @Autowired
    private VcstCaseInfoService vcstCaseInfoService;

    @JmsListener(destination = "queue_mpa_feedback_new" ,containerFactory = "jmsQueueListenerMpa")
    public void receiveAlarmCase(ActiveMQBytesMessage text) {
        try {
            logger.info("QueueFeedbackListener收到了消息：\t" + new String(text.getContent().getData(), "utf-8"));
            JSONArray array = JSONArray.parseArray(new String(text.getContent().getData(), "utf-8"));
            String caseId = array.get(0).toString();

            MpaCaseOprInfo mpaCaseOprInfo = new MpaCaseOprInfo();
            mpaCaseOprInfo.setCaseId(caseId);
            mpaCaseOprInfo.setHashCode(caseId.hashCode()>0?caseId.hashCode():caseId.hashCode() * -1);

            vcstCaseInfoService.insertMpaCaseOpr(mpaCaseOprInfo);
        }catch (Exception e){
            logger.error("插入警情失败，e:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
