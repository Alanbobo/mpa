package com.commandcenter.common.activemq.listener.mess;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.commandcenter.common.activemq.component.PublishToAPP;
import com.commandcenter.common.activemq.listener.puctomq.TopicPuctomqGpsListener;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.activemq.model.MqResponseData;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.HttpRequest;
import com.commandcenter.model.mess.MessUserAuthority;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 监听mess发送的安保信息变更mq消息
 * 下发任务ID以及拥有该任务权限的用户id，所有有权限看到该任务用户，都通知
 */
@Component
public class QueueMessTaskChangeListener {
    protected final static Logger logger = LoggerFactory.getLogger(QueueMessTaskChangeListener.class);

    @Autowired
    private ReceivePuctomqDataService receivePuctomqDataService;
    @Autowired
    private PublishToAPP publishToAPP;
    @Value("${mess.server.ip}")
    private String messIp;
    @Value("${mess.server.port}")
    private String messPort;
    @Value("${mess.server.getTaskInfo}")
    private String getTaskInfo;
    @Autowired
    private SmpUserService smpUserService;

    @JmsListener(destination = "TaskNotifyMPA", containerFactory = "jmsQueueListenerMess")
    public void receiveGpsInfo(ActiveMQBytesMessage text) {
        try {
            MqResponseData mqResponseData = null;
            ActiveMQBytesMessage message = text;
            MQParamModel param;
            // 接受消息
            logger.info("QueueMessTaskChangeListener：\t" + new String(text.getContent().getData(), "utf-8"));
            // 解析消息头
            mqResponseData = JSON.parseObject(new String(message.getContent().getData(), "utf-8"), MqResponseData.class);
            Map<String, Object> messChangeMap = mqResponseData.getBody();
            //获取任务id
            String taskId = messChangeMap.get("TaskID").toString();
            if (taskId != null && !"".equals(taskId.trim())) {
                Map<String, String> taskMap = new HashMap<>(2);
                taskMap.put("taskid", taskId);
                List<String> userNos = new ArrayList<>();
                if (messChangeMap.containsKey("Users") && messChangeMap.get("Users") != null) {
                    JSONArray userArray = (JSONArray) messChangeMap.get("Users");
                    for (int i = 0; i < userArray.size(); i++) {
                        String userId = userArray.getString(i);
                        //获取用户id
                        SmpUserInfo smpUserInfo = smpUserService.selectUserByGuid(userId);
                        String tokenOld = (String) Constant.tokenMapIns.get(smpUserInfo.getUserCode());
                        userNos.add(tokenOld);
                    }
                } else {
                    logger.info("QueueMessTaskChangeListener: taskId ==" + taskId + ", but User is null ");
                }

                param = new MQParamModel(JSON.toJSONString(taskMap), "topic_mpa_app_changeTask");
                param.setPersonalFlag(true);
                param.setQueueFlag(false);

                param.setUserNos(userNos);
                publishToAPP.sendToApp(param);
            } else {
                logger.info("QueueMessTaskChangeListener: taskId is null taskId===" + taskId);
            }

        } catch (Exception e) {
            logger.error("QueueMessTaskChangeListener: error " + e.getStackTrace());
            e.printStackTrace();
        }
    }
}
