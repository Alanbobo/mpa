package com.commandcenter.common.activemq.listener.smp;

import com.alibaba.fastjson.JSON;
import com.commandcenter.common.activemq.listener.vcs.QueueAlarmCaseListener;
import com.commandcenter.common.activemq.model.MqResponseData;
import com.commandcenter.common.activemq.service.ReceiveSmpBaseDataService;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author j18864
 * @create 2018-09-19 10:04
 * @desc smp监听
 **/
@Component
public class TopicSmpDataListener {
    protected final static Logger logger = LoggerFactory.getLogger(QueueAlarmCaseListener.class);
    @Autowired
    private ReceiveSmpBaseDataService receiveSmpBaseDataService;

    @JmsListener(destination = "topic_send_smp_data_notify" ,containerFactory = "jmsTopicListenerMpa")
    public void receiveAlarmCase(ActiveMQBytesMessage text) {
        try {
            MqResponseData mqResponseData = null;
            ActiveMQBytesMessage message =  text;
            // 接受消息
            logger.info("TopicSmpDataListener收到了消息：\t" + new String(message.getContent().getData(), "utf-8"));
            // 解析消息头
            mqResponseData = JSON.parseObject(new String(message.getContent().getData(), "utf-8"), MqResponseData.class);
            try {
                receiveSmpBaseDataService.recSmpBaseData(mqResponseData, 999, "dataList",false);
            } catch (Exception e) {
                logger.info("reveice error ");
                e.printStackTrace();
            }
        }catch (Exception e){
            logger.error("SmpNofityListener增量同步信息失败~:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
