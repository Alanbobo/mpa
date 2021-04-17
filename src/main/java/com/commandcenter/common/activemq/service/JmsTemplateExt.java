package com.commandcenter.common.activemq.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.activemq.model.MqResponseData;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.connection.ConnectionFactoryUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.support.JmsUtils;
import org.springframework.util.Assert;

import javax.jms.*;

/**
 * @author j18864
 * @date 2018-09-04
 * @describe 继承自JmsTemplate 增加方法 sendAndMultiReceive
 * 能够循环接收来自其他系统的mq消息,同时不影响原有JmsTemplate功能
 * 添加事务,使循环分段接收调用的service在同一个事务中,保证事务一致及事务回滚
 */
public class JmsTemplateExt extends JmsTemplate {
    @Autowired
    private ReceiveSmpBaseDataService receiveSmpBaseDataService;

    @Value("${jms.smp.base.queue}")
    private String smpBaseQueue;

    public JmsTemplateExt() {
       super();
    }

    public JmsTemplateExt(ConnectionFactory connectionFactory) {
        this();
        this.setConnectionFactory(connectionFactory);
        this.afterPropertiesSet();
    }

    /**
     * 模拟sendAndReceive方法 根据业务逻辑扩展的新临时队列方法
     *
     * @param destinationName
     * @param messageCreator
     * @return
     * @throws JmsException
     */
    public Message sendAndMultiReceive(final String destinationName, final MessageCreator messageCreator) throws JmsException {
        return (Message) this.executeLocal(new SessionCallback<Message>() {
            @Override
            public Message doInJms(Session session) throws JMSException {
                Destination destination = resolveDestinationName(session, destinationName);
                return doSendAndMultiReceive(destinationName, session, destination, messageCreator);
            }
        }, true);
    }
    public Message sendAndMultiReceiveAdd(final String destinationName, final MessageCreator messageCreator) throws JmsException {
        return (Message) this.executeLocal(new SessionCallback<Message>() {
            @Override
            public Message doInJms(Session session) throws JMSException {
                Destination destination = resolveDestinationName(session, destinationName);
                return doSendAndMultiReceiveAdd(destinationName, session, destination, messageCreator);
            }
        }, true);
    }

    /**
     * 生成session
     *
     * @param action
     * @param startConnection
     * @param <T>
     * @return
     * @throws JmsException
     */
    private <T> T executeLocal(SessionCallback<T> action, boolean startConnection) throws JmsException {
        Assert.notNull(action, "Callback object must not be null");
        Connection con = null;
        Session session = null;

        T var5;
        try {
            con = this.getConnectionFactory().createConnection();
            session = con.createSession(false, 1);
            if (startConnection) {
                con.start();

            }

            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Executing callback on JMS Session: " + session);
            }

            var5 = action.doInJms(session);
        } catch (JMSException var9) {
            throw this.convertJmsAccessException(var9);
        } finally {
            JmsUtils.closeSession(session);
            ConnectionFactoryUtils.releaseConnection(con, this.getConnectionFactory(), startConnection);
        }

        return var5;
    }


    /**
     * 相比于doSendAndReceive方法,能够多次接收receive 回复消息
     * 用于接收大数据量时,分段发送的mq消息,同时需满足临时queue队列的机制
     *
     * @param destinationName
     * @param session
     * @param destination
     * @param messageCreator
     * @return
     * @throws JMSException
     */
    protected Message doSendAndMultiReceive(String destinationName, Session session, Destination destination, MessageCreator messageCreator) throws JMSException {
        Assert.notNull(messageCreator, "MessageCreator must not be null");
        TemporaryQueue responseQueue = null;
        MessageProducer producer = null;
        MessageConsumer consumer = null;
        //返回响应json格式
        MqResponseData mqResponseData = null;

        Message var8 = null;
        try {
            Message requestMessage = messageCreator.createMessage(session);
            responseQueue = session.createTemporaryQueue();
            producer = session.createProducer(destination);
            consumer = session.createConsumer(responseQueue);
            requestMessage.setJMSReplyTo(responseQueue);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Sending created message: " + requestMessage);
            }
            //发消息
            this.doSend(producer, requestMessage);
            int tempCount = 0;
            int count = 0;
            boolean isAddData = false;
            try {
                int runTimeNum = 0;
                //循环接收消息
                while (true) {

                    //获取到消息
                    var8 = this.doReceive(consumer, this.getReceiveTimeout());
                    if (var8 != null) {
                        //转换消息为ActiveMQBytesMessage
                        ActiveMQBytesMessage message = (ActiveMQBytesMessage) var8;
                        logger.info("JMS TEM 接到消息：\t" + new String(message.getContent().getData(), "utf-8"));
                        // 解析消息头
                        mqResponseData = JSON.parseObject(new String(message.getContent().getData(), "utf-8"), MqResponseData.class);
                        JSONArray data = new JSONArray();
                        //如果接收的数据不为null
                        if (mqResponseData.getBody().get("data") != null) {
                            //获取到body数据
                            data = JSONObject.parseArray(mqResponseData.getBody().get("data").toString());
                        }
                        /**
                         * 接收消息业务逻辑
                         * 根据不同的queue进入不同的业务
                         */
                        if (smpBaseQueue.equals(destinationName)) {
                            logger.info("seelog-------------准备解析数据---quque==" + smpBaseQueue);
                        }
                        receiveSmpBaseDataService.recSmpBaseData(mqResponseData, runTimeNum, "data", isAddData);
                        tempCount += data.size();
                        logger.info("receive puc data package: " + tempCount);
                        count = Integer.parseInt(mqResponseData.getBody().get("count").toString());
                        if (tempCount == count) {
                            logger.info("reveice finish , receive data base function result: " + tempCount);
                            //全部接收完毕,则跳出循环
                            break;
                        }
                    } else {
                        throw new JMSException("receive puc data base timeout !");
                    }

                    runTimeNum++;
                }

            } catch (Exception e) {
                logger.info("reveice error" + e.getMessage());
                e.printStackTrace();
                //回滚事务
            }

        } finally {
            System.out.println("seelog-------------最终准备关闭连接");
            //关闭consumer和Producer
            JmsUtils.closeMessageConsumer(consumer);
            JmsUtils.closeMessageProducer(producer);
            if (responseQueue != null) {
                responseQueue.delete();
            }
        }
        return var8;
    }
    /**
     * 相比于doSendAndReceive方法,能够多次接收receive 回复消息
     * 用于接收大数据量时,分段发送的mq消息,同时需满足临时queue队列的机制
     *
     * @param destinationName
     * @param session
     * @param destination
     * @param messageCreator
     * @return
     * @throws JMSException
     */
    protected Message doSendAndMultiReceiveAdd(String destinationName, Session session, Destination destination, MessageCreator messageCreator) throws JMSException {
        Assert.notNull(messageCreator, "MessageCreator must not be null");
        TemporaryQueue responseQueue = null;
        MessageProducer producer = null;
        MessageConsumer consumer = null;
        //返回响应json格式
        MqResponseData mqResponseData = null;

        Message var8 = null;
        try {
            Message requestMessage = messageCreator.createMessage(session);
            responseQueue = session.createTemporaryQueue();
            producer = session.createProducer(destination);
            consumer = session.createConsumer(responseQueue);
            requestMessage.setJMSReplyTo(responseQueue);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Sending created message: " + requestMessage);
            }
            //发消息
            this.doSend(producer, requestMessage);
            int tempCount = 0;
            int count = 0;
            boolean isAddData = true;
            try {
                int runTimeNum = 1;
                //循环接收消息
                while (true) {

                    //获取到消息
                    var8 = this.doReceive(consumer, this.getReceiveTimeout());
                    if (var8 != null) {
                        //转换消息为ActiveMQBytesMessage
                        ActiveMQBytesMessage message = (ActiveMQBytesMessage) var8;
                        logger.info("JMS TEM 接到消息：\t" + new String(message.getContent().getData(), "utf-8"));
                        // 解析消息头
                        mqResponseData = JSON.parseObject(new String(message.getContent().getData(), "utf-8"), MqResponseData.class);
                        JSONArray data = new JSONArray();
                        //如果接收的数据不为null
                        if (mqResponseData.getBody().get("data") != null) {
                            //获取到body数据
                            data = JSONObject.parseArray(mqResponseData.getBody().get("data").toString());
                        }
                        /**
                         * 接收消息业务逻辑
                         * 根据不同的queue进入不同的业务
                         */
                        if (smpBaseQueue.equals(destinationName)) {
                            logger.info("seelog-------------准备解析数据---quque==" + smpBaseQueue);
                            receiveSmpBaseDataService.recSmpBaseData(mqResponseData, runTimeNum, "data",isAddData);
                        }
                        tempCount += data.size();
                        logger.info("receive puc data package: " + tempCount);
                        count = Integer.parseInt(mqResponseData.getBody().get("count").toString());
                        if (tempCount == count||(data.size() == 0&&count>0)) {
                            logger.info("reveice finish , receive data base function result: " + tempCount);
                            //全部接收完毕,则跳出循环
                            break;
                        }
                    } else {
                        throw new JMSException("receive puc data base timeout !");
                    }

                    runTimeNum++;
                }

            } catch (Exception e) {
                logger.info("reveice error "+e.getMessage());
                e.printStackTrace();
                //回滚事务
            }

        } finally {
            System.out.println("seelog-------------最终准备关闭连接");
            //关闭consumer和Producer
            JmsUtils.closeMessageConsumer(consumer);
            JmsUtils.closeMessageProducer(producer);
            if (responseQueue != null) {
                responseQueue.delete();
            }
        }
        return var8;
    }


    private Message doReceive(MessageConsumer consumer, long timeout) throws JMSException {
        if (timeout == -1L) {
            return consumer.receiveNoWait();
        } else {
            return timeout > 0L ? consumer.receive(timeout) : consumer.receive();
        }
    }
}
