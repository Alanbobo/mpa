package com.commandcenter.common.activemq.service.impl;


import com.commandcenter.common.activemq.service.JmsTemplateExt;
import com.commandcenter.common.activemq.service.ProducerSmpPtp;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author r25437
 * @create 2018-09-12 11:42
 * @desc Smp发送消息队列使用
 **/
@Service("producerSmpPtp")
public class ProducerSmpPtpImp implements ProducerSmpPtp {
  @Autowired
  private JmsTemplateExt jmsTemplateExt;

  Logger logger = Logger.getLogger(ProducerSmpPtpImp.class);

  /**
   * 向指定队列发送消息
   */
  @Override
  public void sendMessage(final String queue, final String msg) {
    this.logger.info("向队列" + queue + "发送消息------------" + msg);
    this.jmsTemplateExt.send(queue, new MessageCreator() {
      @Override
      public Message createMessage(Session session) throws JMSException {
        BytesMessage message = session.createBytesMessage();
        try {
          message.writeBytes(msg.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
          ProducerSmpPtpImp.this.logger.info("ProducerP2pImp ", e);
        }
        return message;
      }
    });
  }

  @Override
  public void pubAndOnceRece(String queue, String msg) {
    this.logger.info("向队列" + queue + "发送消息------------" + msg);
    Message message = this.jmsTemplateExt.sendAndReceive(queue, new MessageCreator() {
      @Override
      public Message createMessage(Session session) throws JMSException {
        BytesMessage message = session.createBytesMessage();
        String correlationID = UUID.randomUUID().toString();
        logger.info("队列" + queue + "生成临时队列ID------------" + correlationID);
        message.setJMSCorrelationID(correlationID);
        try {
          message.writeBytes(msg.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
          ProducerSmpPtpImp.this.logger.info("ProducerP2pImp ", e);
        }
        return message;
      }
    });

    try{
      ActiveMQBytesMessage activeMQBytesMessage = (ActiveMQBytesMessage) message;
      logger.info("receive message：\t" + new String(activeMQBytesMessage.getContent().getData(), "utf-8"));
    }catch(Exception e){
      logger.info("队列" + queue + "接收回复消息失败------------");
      e.printStackTrace();
    }

  }
  @Override
  public Boolean pubAndOnceReceReturn(String queue, String msg) {
    this.logger.info("向队列" + queue + "发送消息------------" + msg);
    //设置两分钟超时
    jmsTemplateExt.setReceiveTimeout(1000 * 60*10);
    Boolean finalResult = true;
    Message message = this.jmsTemplateExt.sendAndReceive(queue, new MessageCreator() {
      @Override
      public Message createMessage(Session session) throws JMSException {
        BytesMessage message = session.createBytesMessage();
        String correlationID = UUID.randomUUID().toString();
        logger.info("队列" + queue + "生成临时队列ID------------" + correlationID);
        message.setJMSCorrelationID(correlationID);
        try {
          message.writeBytes(msg.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
          ProducerSmpPtpImp.this.logger.info("ProducerP2pImp ", e);
        }
        return message;
      }
    });

    try{
      ActiveMQBytesMessage activeMQBytesMessage = (ActiveMQBytesMessage) message;
      logger.info("receive message：\t" + new String(activeMQBytesMessage.getContent().getData(), "utf-8"));
    }catch(Exception e){
      finalResult = false;
      logger.info("队列" + queue + "接收回复消息失败------------");
      e.printStackTrace();
    }
    return finalResult;
  }

  @Override
  public void pubAndMultiRece(String queue, String msg) {
    this.logger.info("向队列" + queue + "发送消息------------" + msg);
    //设置两分钟超时
    jmsTemplateExt.setReceiveTimeout(1000 * 60*10);
    Message message = this.jmsTemplateExt.sendAndMultiReceive(queue, new MessageCreator() {
      @Override
      public Message createMessage(Session session) throws JMSException {
        BytesMessage message = session.createBytesMessage();
        //使用snowflake IdWorker 分布式唯一ID生成器 生成临时队列ID
        String correlationID = UUID.randomUUID().toString();
        logger.info("队列" + queue + "生成临时队列ID------------" + correlationID);
        message.setJMSCorrelationID(correlationID);
        try {
          message.writeBytes(msg.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
          ProducerSmpPtpImp.this.logger.info("pubAndReceSpecial ", e);
        }
        return message;
      }
    });

  }
  @Override
  public void pubAndMultiReceAdd(String queue, String msg) {
    this.logger.info("向队列" + queue + "发送消息------------" + msg);
    //设置两分钟超时
    jmsTemplateExt.setReceiveTimeout(1000 * 60*10);
    Message message = this.jmsTemplateExt.sendAndMultiReceiveAdd(queue, new MessageCreator() {
      @Override
      public Message createMessage(Session session) throws JMSException {
        BytesMessage message = session.createBytesMessage();
        //使用snowflake IdWorker 分布式唯一ID生成器 生成临时队列ID
        String correlationID = UUID.randomUUID().toString();
        logger.info("队列" + queue + "生成临时队列ID------------" + correlationID);
        message.setJMSCorrelationID(correlationID);
        try {
          message.writeBytes(msg.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
          ProducerSmpPtpImp.this.logger.info("pubAndReceSpecial ", e);
        }
        return message;
      }
    });

  }
  @Override
  public Message pubAndMultiRece1(String queue, String msg) {
    this.logger.info("向队列" + queue + "发送消息------------" + msg);
    //设置两分钟超时
    jmsTemplateExt.setReceiveTimeout(1000 * 60*10);
    Message message = this.jmsTemplateExt.sendAndMultiReceive(queue, new MessageCreator() {
      @Override
      public Message createMessage(Session session) throws JMSException {
        BytesMessage message = session.createBytesMessage();
        //使用snowflake IdWorker 分布式唯一ID生成器 生成临时队列ID
        String correlationID = UUID.randomUUID().toString();
        logger.info("队列" + queue + "生成临时队列ID------------" + correlationID);
        message.setJMSCorrelationID(correlationID);
        try {
          message.writeBytes(msg.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
          ProducerSmpPtpImp.this.logger.info("pubAndReceSpecial ", e);
        }
        return message;
      }
    });
    return message;
  }
}
