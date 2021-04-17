package com.commandcenter.common.activemq.listener.vcs;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author r25437
 * @create 2018-09-17 10:04
 * @desc 警情监听
 **/
@Component
public class QueueNoticeListener {
    protected final static Logger logger = LoggerFactory.getLogger(QueueNoticeListener.class);

    @JmsListener(destination = "queue_mpa_notice_new" ,containerFactory = "jmsQueueListenerMpa")
    public void receiveAlarmCase(ActiveMQBytesMessage text) {
        try {
            logger.info("QueueNoticeListener收到了消息：\t" + new String(text.getContent().getData(), "utf-8"));
            String mesString = new String(text.getContent().getData(), "utf-8");
        }catch (Exception e){
            logger.error("插入警情失败，e:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
