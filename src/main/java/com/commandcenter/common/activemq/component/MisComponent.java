package com.commandcenter.common.activemq.component;

import com.alibaba.fastjson.JSON;
import com.commandcenter.common.utils.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author r25437
 * @create 2018-10-15 13:19
 * @desc mis系统发送mq组件
 **/
@Component
public class MisComponent {

    private final static Logger logger = LoggerFactory.getLogger(MisComponent.class);

    @Value("${mis.addr}")
    private String misAddr;

    /**
     * 通过mis接口发送mq给指定主题，发送指定clientId
     * @param topic
     * @param msg
     * @param clientId
     */
    public void publishStrTopic(final String topic, final String msg, String clientId){
        String json = createMisJson(topic,msg,clientId);
        String returnStr = HttpRequest.sendPost(misAddr,json);
        logger.info("MisComponent.publishStrTopic ========"+returnStr);
    }

    /**
     * 通过mis接口发送mq到指定主题，群发
     * @param topic
     * @param msg
     */
    public void publishTopic(final String topic, final String msg){
        String json = createMisJson(topic,msg,null);
        String returnStr = HttpRequest.sendPost(misAddr,json);
        logger.info("MisComponent.publishTopic ========"+returnStr);
    }

    private String createMisJson(final String topic, final String msg, String clientId){
        List<String> userList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>(5);
        map.put("destination", topic);
        map.put("hasReturn","false");
        map.put("info",msg);
        //如果按照指定用户发送
        if(clientId != null) {
            userList.add(clientId);
        }
        map.put("clientsGroup",userList);
        Map<String, Object> rootMap = new HashMap<>(2);
        rootMap.put("param",map);
        return JSON.toJSONString(rootMap);

    }
}
