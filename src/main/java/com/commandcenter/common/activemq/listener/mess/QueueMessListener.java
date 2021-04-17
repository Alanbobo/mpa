package com.commandcenter.common.activemq.listener.mess;

import com.alibaba.fastjson.JSON;
import com.commandcenter.common.activemq.component.PublishToAPP;
import com.commandcenter.common.activemq.listener.puctomq.TopicPuctomqGpsListener;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.activemq.model.MqResponseData;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.model.smp.SmpUserInfo;
import com.commandcenter.service.puctomqservice.ReceivePuctomqDataService;
import com.commandcenter.service.smp.SmpUserService;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 监听mess发送的mq消息
 */
@Component
public class QueueMessListener {
    protected final static Logger logger = LoggerFactory.getLogger(TopicPuctomqGpsListener.class);

    @Autowired
    private ReceivePuctomqDataService receivePuctomqDataService;
    @Autowired
    private PublishToAPP publishToAPP;
    @Value("${system.notice.app.changeMessAuth}")
    private String changeMessAuth;
    @Autowired
    private SmpUserService smpUserService;
    @JmsListener(destination = "MPA_Msg_Alter" ,containerFactory = "jmsQueueListenerMess")
    public void receiveGpsInfo(ActiveMQBytesMessage text) {
        try {
            MqResponseData mqResponseData = null;
            ActiveMQBytesMessage message = text;
            MQParamModel param;
            // 接受消息
            logger.info("QueueMessListener：\t" + new String(text.getContent().getData(), "utf-8"));
            // 解析消息头
            mqResponseData = JSON.parseObject(new String(message.getContent().getData(), "utf-8"), MqResponseData.class);
            Map<String,Object> changeAuthMap = mqResponseData.getBody();
            //获取用户id
            SmpUserInfo smpUserInfo = smpUserService.selectUserByGuid(changeAuthMap.get("userid").toString());
            String tokenOld = (String) Constant.tokenMapIns.get(smpUserInfo.getUserCode());
            param = new MQParamModel(JSON.toJSONString(changeAuthMap),changeMessAuth);

            param.setPersonalFlag(true);
            param.setQueueFlag(false);

            List<String> userNos = new ArrayList<>();
            userNos.add(tokenOld);
            param.setUserNos(userNos);
            publishToAPP.sendToApp(param);

        }catch(Exception e){

        }
    }
}
