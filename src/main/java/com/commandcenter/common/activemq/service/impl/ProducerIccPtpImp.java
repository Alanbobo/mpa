package com.commandcenter.common.activemq.service.impl;

import com.commandcenter.common.activemq.service.ProducerIccPtp;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author r25437
 * @create 2018-10-24 11:42
 * @desc Icc发送消息队列使用
 **/
@Service("producerIccPtp")
public class ProducerIccPtpImp implements ProducerIccPtp {

  @Autowired
  @Qualifier("jmsTemplateIcc")
  private JmsTemplate queueJmsTemplateIcc;

  Logger logger = Logger.getLogger(ProducerIccPtpImp.class);

  /**
   * 向指定队列发送消息
   */
  @Override
  public void sendMessage(final String queue, final String msg) {
    this.logger.info("向队列" + queue + "发送消息------------" + msg);
    this.queueJmsTemplateIcc.send(queue, new MessageCreator() {
      @Override
      public Message createMessage(Session session) throws JMSException {
        BytesMessage message = session.createBytesMessage();
        try {
          message.writeBytes(msg.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
          ProducerIccPtpImp.this.logger.info("ProducerP2pImp ", e);
        }
        return message;
      }
    });
  }

  @Override
  public String pubAndOnceRece(String queue, String msg) {
    this.logger.info("向队列" + queue + "发送消息------------" + msg);
    Message message = this.queueJmsTemplateIcc.sendAndReceive(queue, new MessageCreator() {
      @Override
      public Message createMessage(Session session) throws JMSException {
        BytesMessage message = session.createBytesMessage();
        String correlationID = UUID.randomUUID().toString();
        logger.info("队列" + queue + "生成临时队列ID------------" + correlationID);
        message.setJMSCorrelationID(correlationID);
        try {
          message.writeBytes(msg.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
          ProducerIccPtpImp.this.logger.info("ProducerP2pImp ", e);
        }
        return message;
      }
    });

    String returnStr = "";
    try{
      ActiveMQBytesMessage activeMQBytesMessage = (ActiveMQBytesMessage) message;
      if(activeMQBytesMessage!=null) {
        returnStr = new String(activeMQBytesMessage.getContent().getData(), "utf-8");
        logger.info("receive message：\t" + returnStr);
      }else{
        logger.info("receive message is null：\t" + returnStr);
      }
    }catch(Exception e){
      logger.info("队列" + queue + "接收回复消息失败------------");
      e.printStackTrace();
    }
    return returnStr;
  }
}
