package com.commandcenter.common.activemq.listener.icc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.HttpRequest;
import com.commandcenter.common.wcf.WcfClient;
import com.commandcenter.model.wcf.FeedbackModelForWcf;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @create 2018-11-7 10:04
 * @desc ICC查询反馈消息队列监听
 **/
@Component
public class QueueIccFeedBackQueryListener {
    protected final static Logger logger = LoggerFactory.getLogger(QueueIccFeedBackQueryListener.class);

    @Value("${jms.icc.timeToLive}")
    private int ttl;

    @Value("${vcs.http.url}")
    private String vcsHttpUrl;

    @Value("${vcs.http.port}")
    private String vcsHttpPort;

    @JmsListener(destination = "queue_icc_get_feedback" ,containerFactory = "jmsQueueListenerIcc")
    public void queryFeedBack(ActiveMQBytesMessage messageBytes, Session session) {
        try {
            String jsonString = new String(messageBytes.getContent().getData(), "utf-8");
            logger.info("QueueIccFeedBackQueryListener收到了消息：\t" + jsonString);
            String caseId = JSONObject.parseObject(jsonString).getJSONObject("body").getString("case_id");
            //向VCS请求反馈列表
            List<FeedbackModelForWcf> feedbackList = new ArrayList<>();
            if(caseId!=null && !"".equals(caseId)){
                //向VCS请求反馈列表
                Map<String,String> vcsQueryMap = new HashMap<>(2);
                vcsQueryMap.put("caseId",caseId);
                String vcsResponseJson = HttpRequest.sendGet(vcsHttpUrl+":"+vcsHttpPort+ Constant.VCS_GET_HANDLE_RECORD_LIST,vcsQueryMap);
                JSONObject vcsResponseJsonObj = JSON.parseObject(vcsResponseJson);
                String json = vcsResponseJsonObj.getJSONObject("Result").getJSONArray("Records").toJSONString();
                feedbackList = JSONObject.parseArray(json, FeedbackModelForWcf.class);
                /*
                向VCS请求反馈列表
                feedbackList = WcfClient.getHandleRecordList(caseId);
                 */
            }
            Map<String,Object> returnMap = new HashMap<>();
            Map<String,Object> headMap = new HashMap<>();
            headMap.put("cmd","icc_get_feedback_respond");
            returnMap.put("head",headMap);
            returnMap.put("body",feedbackList);
            Destination dest = messageBytes.getJMSReplyTo();
            MessageProducer producer = session.createProducer(dest);
            producer.setTimeToLive(ttl);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            BytesMessage tm = session.createBytesMessage();
            //请求方指定回复的message_id 03-03 add
            tm.setJMSCorrelationID(messageBytes.getJMSCorrelationID());
            String message = JSON.toJSONString(returnMap);
            tm.writeBytes(message.getBytes("utf-8"));
            producer.send(tm);
            tm.clearBody();
            logger.info("QueueIccFeedBackQueryListener 反馈成功 json===="+message);
        }catch (Exception e){
            logger.error("查询警情反馈失败，e:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
