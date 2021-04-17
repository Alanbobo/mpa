package com.commandcenter.config;

import com.commandcenter.common.activemq.service.JmsTemplateExt;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * @Auther: j18864
 * @Date: 2018/9/6 14:34
 * @Description:
 */
@Configuration
@EnableJms
public class ActivemqConfig {
    @Value("${jms.vcs.timeToLive}")
    private Long vcsTimeToLive;
    @Value("${jms.app.timeToLive}")
    private Long appTimeToLive;
    @Value("${jms.icc.timeToLive}")
    private Long iccTimeToLive;
    /*@Value("${jms.puctomq.timeToLive}")
    private Long puctomqTimeToLive;*/
    @Value("${jms.mess.timeToLive}")
    private Long messTimeToLive;
    @Value("${jms.explicitQosEnabled}")
    private Boolean explicitQosEnabled;
    @Value("${jms.vcs.receiveTimeout}")
    private Long vcsReceiveTimeout;
    @Value("${jms.app.receiveTimeout}")
    private Long appReceiveTimeout;
    @Value("${jms.icc.receiveTimeout}")
    private Long iccReceiveTimeout;
    /*@Value("${jms.puctomq.receiveTimeout}")
    private Long puctomqReceiveTimeout;*/
    @Value("${jms.mess.receiveTimeout}")
    private Long messReceiveTimeout;
    @Value("${jms.queueShareData}")
    private String queueShareData;
    @Value("${jms.pubSubDomain}")
    private Boolean pubSubDomain;
    @Value("${jms.consumerExpiryCheckEnabled}")
    private Boolean isConsumerExpiryCheckEnabled;
    @Value("${jms.vcs.url}")
    private String vcsUrl;
    @Value("${jms.vcs.username}")
    private String vcsJmsUserName;
    @Value("${jms.vcs.password}")
    private String vcsJmsUserPassword;
    @Value("${jms.app.url}")
    private String appUrl;
    @Value("${jms.app.username}")
    private String appJmsUserName;
    @Value("${jms.app.password}")
    private String appJmsUserPassword;
    @Value("${jms.icc.url}")
    private String iccUrl;
    @Value("${jms.icc.username}")
    private String iccJmsUserName;
    @Value("${jms.icc.password}")
    private String iccJmsUserPassword;
    /*@Value("${jms.puctomq.url}")
    private String puctomqUrl;
    @Value("${jms.puctomq.username}")
    private String puctomqJmsUserName;
    @Value("${jms.puctomq.password}")
    private String puctomqJmsUserPassword;*/
    @Value("${jms.mess.url}")
    private String messUrl;
    @Value("${jms.mess.username}")
    private String messJmsUserName;
    @Value("${jms.mess.password}")
    private String messJmsUserPassword;


    private static final String VCS_MQ_CONN = "vcs";
    private static final String APP_MQ_CONN = "app";
    private static final String ICC_MQ_CONN = "icc";
    //private static final String PUCTOMQ_MQ_CONN = "puctomq";
    private static final String MESS_MQ_CONN = "mess";
    /**
     * 默认有效期
     */
    private static final Long DEFAULT_TIME_TO_LIVE = 60000L;
    /**
     * 默认超时时间
     */
    private static final Long DEFAULT_RECEIVE_TIME_OUT = 60000L;

    private ConnectionFactory vcsConnectFactory = null;

    @Bean("vcsConnectionFactory")
    @Primary
    public ConnectionFactory vcsConnectionFactory(){
        if(vcsConnectFactory == null) {
            vcsConnectFactory = getConnectionFactory(vcsUrl, vcsJmsUserName, vcsJmsUserPassword, 15);
        }
        return vcsConnectFactory;
    }

    /**
     * puctomq的MQ建立连接工厂
     * @return
     */
    /*@Bean("puctomqConnectionFactory")
    public ConnectionFactory puctomqConnectionFactory(){
        //这里判断，如果appurl和vcs是同一个MQ，则不再新创建connection
        if(puctomqUrl.equals(vcsUrl)){
            if(vcsConnectFactory == null) {
                vcsConnectFactory = getConnectionFactory(vcsUrl, vcsJmsUserName, vcsJmsUserPassword, 15);
            }
            return vcsConnectFactory;
        }else {
            return getConnectionFactory(puctomqUrl, puctomqJmsUserName, puctomqJmsUserPassword, 15);
        }
    }*/
    /**
     * mess的MQ建立连接工厂
     * @return
     */
    @Bean("messConnectionFactory")
    public ConnectionFactory messConnectionFactory(){
        //这里判断，如果appurl和vcs是同一个MQ，则不再新创建connection
        if(messUrl.equals(vcsUrl)){
            if(vcsConnectFactory == null) {
                vcsConnectFactory = getConnectionFactory(vcsUrl, vcsJmsUserName, vcsJmsUserPassword, 15);
            }
            return vcsConnectFactory;
        }else {
            return getConnectionFactory(messUrl, messJmsUserName, messJmsUserPassword, 15);
        }
    }

    @Bean("appConnectionFactory")
    public ConnectionFactory appConnectionFactory(){
        //这里判断，如果appurl和vcs是同一个MQ，则不再新创建connection
        if(appUrl.equals(vcsUrl)){
            if(vcsConnectFactory == null) {
                vcsConnectFactory = getConnectionFactory(vcsUrl, vcsJmsUserName, vcsJmsUserPassword, 15);
            }
            return vcsConnectFactory;
        }else {
            return getConnectionFactory(appUrl, appJmsUserName, appJmsUserPassword, 15);
        }
    }

    @Bean("iccConnectionFactory")
    public ConnectionFactory iccConnectionFactory(){
        //这里判断，如果iccurl和vcs是同一个MQ，则不再新创建connection
        if(appUrl.equals(vcsUrl)){
            if(vcsConnectFactory == null) {
                vcsConnectFactory = getConnectionFactory(vcsUrl, vcsJmsUserName, vcsJmsUserPassword, 15);
            }
            return vcsConnectFactory;
        }else {
            return getConnectionFactory(iccUrl, iccJmsUserName, iccJmsUserPassword, 5);
        }
    }

    private ConnectionFactory getConnectionFactory(String url,String username,String password, int maxConnect){
        System.out.println("getConnectionFactory==================="+url);
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(url);
        connectionFactory.setUserName(username);
        connectionFactory.setPassword(password);
        connectionFactory.setConsumerExpiryCheckEnabled(isConsumerExpiryCheckEnabled);
        PooledConnectionFactory pool = new PooledConnectionFactory();
        pool.setConnectionFactory(connectionFactory);
        // 最大连接数
        pool.setMaxConnections(maxConnect);
        //每个连接，可开的最大session数
        pool.setMaximumActiveSessionPerConnection(20);
        //空闲回收时间，不回收可增加重复使用的次数
        pool.setIdleTimeout(60000);
        return pool;
    }

    @Bean("jmsTemplate")
    public JmsTemplate jmsTemplate(@Qualifier("vcsConnectionFactory") ConnectionFactory vcsConnectionFactory){
        return getJmsTemplate(vcsConnectionFactory,VCS_MQ_CONN);
    }

    @Bean("jmsTemplateApp")
    public JmsTemplate jmsTemplateApp(@Qualifier("appConnectionFactory") ConnectionFactory appConnectionFactory){
        return getJmsTemplate(appConnectionFactory,APP_MQ_CONN);
    }

    @Bean("jmsTemplateIcc")
    public JmsTemplate jmsTemplateIcc(@Qualifier("iccConnectionFactory") ConnectionFactory iccConnectionFactory){
        return getJmsTemplate(iccConnectionFactory,ICC_MQ_CONN);
    }
    @Bean("jmsTemplateMess")
    public JmsTemplate jmsTemplateMess(@Qualifier("messConnectionFactory") ConnectionFactory messConnectionFactory){
        return getJmsTemplate(messConnectionFactory,MESS_MQ_CONN);
    }

    private JmsTemplate getJmsTemplate(ConnectionFactory connectionFactory,String mqConn){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setExplicitQosEnabled(explicitQosEnabled);
        setTemplateTime(jmsTemplate,mqConn);

        return jmsTemplate;
    }

    @Bean("jmsTemplateExt")
    public JmsTemplateExt jmsTemplateExt(@Qualifier("vcsConnectionFactory") ConnectionFactory vcsConnectionFactory){
        JmsTemplateExt jmsTemplateExt = new JmsTemplateExt(vcsConnectionFactory);
        jmsTemplateExt.setTimeToLive(vcsTimeToLive);
        jmsTemplateExt.setExplicitQosEnabled(explicitQosEnabled);
        jmsTemplateExt.setReceiveTimeout(vcsReceiveTimeout);
        return jmsTemplateExt;
    }

    @Bean("topicJmsTemplate")
    public JmsTemplate topicJmsTemplate(@Qualifier("vcsConnectionFactory") ConnectionFactory vcsConnectionFactory){
        return getTopicJmsTemplate(vcsConnectionFactory,VCS_MQ_CONN);
    }

    @Bean("topicJmsTemplateMess")
    public JmsTemplate topicJmsTemplateMess(@Qualifier("messConnectionFactory") ConnectionFactory messConnectionFactory){
        return getTopicJmsTemplate(messConnectionFactory,MESS_MQ_CONN);
    }

    @Bean("topicJmsTemplateApp")
    public JmsTemplate topicJmsTemplateApp(@Qualifier("appConnectionFactory") ConnectionFactory appConnectionFactory){
        return getTopicJmsTemplate(appConnectionFactory,APP_MQ_CONN);
    }

    private JmsTemplate getTopicJmsTemplate(ConnectionFactory connectionFactory,String mqConn){
        JmsTemplate topicPucTemplate = new JmsTemplate();
        topicPucTemplate.setConnectionFactory(connectionFactory);
        topicPucTemplate.setPubSubDomain(pubSubDomain);
        setTemplateTime(topicPucTemplate,mqConn);
        return topicPucTemplate;
    }


    @Bean(name = "jmsTopicListenerMpa")
    public DefaultJmsListenerContainerFactory jmsTopicListenerContainerFactory(@Qualifier("vcsConnectionFactory") ConnectionFactory vcsConnectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(vcsConnectionFactory);
        factory.setConcurrency("1");
        factory.setPubSubDomain(true);
        return factory;
    }
   @Bean(name = "jmsTopicListenerMess")
    public DefaultJmsListenerContainerFactory messTopicListenerContainerFactory(@Qualifier("messConnectionFactory") ConnectionFactory messConnectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(messConnectionFactory);
        factory.setConcurrency("1");
        factory.setPubSubDomain(true);
        return factory;
    }
   /*@Bean(name = "jmsTopicListenerPuctomq")
    public DefaultJmsListenerContainerFactory puctomqTopicListenerContainerFactory(@Qualifier("puctomqConnectionFactory") ConnectionFactory puctomqConnectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(puctomqConnectionFactory);
        factory.setConcurrency("1");
        factory.setPubSubDomain(true);
        return factory;
    }*/

    @Bean(name = "jmsQueueListenerMpa")
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory(@Qualifier("vcsConnectionFactory") ConnectionFactory vcsConnectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(vcsConnectionFactory);
        factory.setConcurrency("1");
        return factory;
    }
    @Bean(name = "jmsQueueListenerMess")
    public DefaultJmsListenerContainerFactory messJmsQueueListenerContainerFactory(@Qualifier("messConnectionFactory") ConnectionFactory messConnectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(messConnectionFactory);
        factory.setConcurrency("1");
        return factory;
    }

    @Bean(name = "jmsQueueListenerIcc")
    public DefaultJmsListenerContainerFactory iccJmsQueueListenerContainerFactory(@Qualifier("iccConnectionFactory") ConnectionFactory iccConnectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(iccConnectionFactory);
        factory.setConcurrency("1");
        return factory;
    }

    @Bean(name = "jmsTopicListenerIcc")
    public DefaultJmsListenerContainerFactory iccJmsTopicListenerContainerFactory(@Qualifier("iccConnectionFactory") ConnectionFactory iccConnectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(iccConnectionFactory);
        factory.setConcurrency("1");
        factory.setPubSubDomain(true);
        return factory;
    }

    private void setTemplateTime(JmsTemplate jmsTemplate, String mqConn){
        switch (mqConn){
            case VCS_MQ_CONN:
                jmsTemplate.setTimeToLive(vcsTimeToLive);
                jmsTemplate.setReceiveTimeout(vcsReceiveTimeout);
                break;
            case APP_MQ_CONN:
                jmsTemplate.setTimeToLive(appTimeToLive);
                jmsTemplate.setReceiveTimeout(appReceiveTimeout);
                break;
            case ICC_MQ_CONN:
                jmsTemplate.setTimeToLive(iccTimeToLive);
                jmsTemplate.setReceiveTimeout(iccReceiveTimeout);
                break;
            /*case PUCTOMQ_MQ_CONN:
                jmsTemplate.setTimeToLive(puctomqTimeToLive);
                jmsTemplate.setReceiveTimeout(puctomqReceiveTimeout);
                break;*/
            case MESS_MQ_CONN:
                jmsTemplate.setTimeToLive(messTimeToLive);
                jmsTemplate.setReceiveTimeout(messReceiveTimeout);
                break;
            default:
                jmsTemplate.setTimeToLive(DEFAULT_TIME_TO_LIVE);
                jmsTemplate.setReceiveTimeout(DEFAULT_RECEIVE_TIME_OUT);
                break;
        }
    }
}
