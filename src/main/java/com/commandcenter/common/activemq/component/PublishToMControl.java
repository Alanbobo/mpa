package com.commandcenter.common.activemq.component;

import com.alibaba.fastjson.JSON;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.activemq.service.ProducerPtp;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.R;
import com.commandcenter.common.utils.encrypt.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author r25437
 * @create 2018-09-12 10:46
 * @desc 推送MControl消息工具
 **/
@Component
public class PublishToMControl {
    private final static Logger logger = LoggerFactory.getLogger(PublishToMControl.class);
    @Autowired
    private ProducerPtp producerPtp;
    @Autowired
    private MisComponent misComponent;

    @Value("${mis.enable}")
    private boolean misEnable;

    public void sendToMControl(MQParamModel param){
        try {
            String name = param.getName();
            logger.info("name=" + name);
            String msg = param.getMsg();
            logger.info("msg=" + msg);
            //发送群推主题消息
            logger.info("准备发送群推主题消息，topic_name:" + name);
            //如果通过mis系统发送，调用mis接口，否则直接推送mq
            if (misEnable) {
                misComponent.publishTopic(name, msg);
            } else {
                producerPtp.publishTopic(name, msg);
            }

        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }
}
