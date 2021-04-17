package com.commandcenter.common.activemq.service.impl;


import com.commandcenter.common.activemq.service.ProduceMessPtp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.io.UnsupportedEncodingException;

@Service("ProduceMessPtp")
public class ProduceMessPtpImpl implements ProduceMessPtp {
    @Autowired
    private JmsTemplate jmsTemplateMess;
    @Autowired
    private JmsTemplate topicJmsTemplateMess;

    Logger logger = Logger.getLogger(ProduceMessPtpImpl.class);


    /**
     * 向指定的topic发布消息
     */
    @Override
    public void publishTopic(final String topic, final String msg) {
        topicJmsTemplateMess.setDeliveryMode(DeliveryMode.PERSISTENT);
        topicJmsTemplateMess.send(topic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                logger.info("ProduceMessPtpImpl 发布了字节主题：\t" + topic.toString() + "，发布消息内容为:\t" + msg);
                BytesMessage message = session.createBytesMessage();
                try {
                    message.writeBytes(msg.getBytes("utf-8"));
                } catch (UnsupportedEncodingException e) {
                    logger.info("ProduceMessPtpImpl ", e);
                }
                return message;
            }
        });
    }

    @Override
    public void publishStrTopic(String topic, String msg) {

        logger.info("进入publishStrTopic方法....");
        topicJmsTemplateMess.setDeliveryMode(DeliveryMode.PERSISTENT);

        topicJmsTemplateMess.send(topic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                logger.info("ProduceMessPtpImpl 发布了字符串主题：\t" + topic.toString() + "，发布消息内容为:\t" + msg);
                TextMessage message = session.createTextMessage();
                try {
                    message.setText(msg);
                    message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
                    message.setStringProperty("Client ID", "t1");
                } catch (Exception e) {
                    logger.info("ProduceMessPtpImpl ", e);
                }
                return message;
            }
        });

    }

    @Override
    public void publishStrTopic(String topic, String msg, String clientId) {
        topicJmsTemplateMess.setDeliveryMode(DeliveryMode.PERSISTENT);

        topicJmsTemplateMess.send(topic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                logger.info("ProduceMessPtpImpl 发布了字符串主题：\t" + topic.toString() + "，发布消息内容为:\t" + msg);
                TextMessage message = session.createTextMessage();
                try {
                    message.setText(msg);
                    message.setStringProperty("clientId", clientId);
                } catch (Exception e) {
                    logger.info("ProduceMessPtpImpl ", e);
                }
                return message;
            }
        });
    }


    @Override
    public void publishQueue(final String queue, final String msg) {
        jmsTemplateMess.setDeliveryMode(DeliveryMode.PERSISTENT);
        jmsTemplateMess.send(queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                logger.info("ProduceMessPtpImpl 发布了字节队列：\t" + queue.toString() + "，发布队列内容为:\t" + msg);
                BytesMessage message = session.createBytesMessage();
                try {
                    message.writeBytes(msg.getBytes("utf-8"));
                } catch (UnsupportedEncodingException e) {
                    logger.info("ProduceMessPtpImpl ", e);
                }
                return message;
            }
        });

    }


    @Override
    public void publishStrQueue(String queue, String msg) {

        logger.info("进入publishStrQueue方法.....");
        jmsTemplateMess.setDeliveryMode(DeliveryMode.PERSISTENT);

        jmsTemplateMess.send(queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                logger.info("ProduceMessPtpImpl 发布了字符串队列：\t" + queue.toString() + "，发布队列内容为:\t" + msg);
                TextMessage message = session.createTextMessage();
                try {
                    message.setText(msg);
                    message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
                    message.setStringProperty("Client ID", "t1");
                } catch (Exception e) {
                    logger.info("ProduceMessPtpImpl ", e);
                }
                return message;
            }
        });

    }
}
