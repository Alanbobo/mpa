package com.commandcenter.common.activemq.listener.puctomq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.commandcenter.common.activemq.component.PublishToAPP;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.activemq.model.MqResponseData;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.model.puctomq.PucOnlineData;
import com.commandcenter.model.smp.SmpInterphoneInfo;
import com.commandcenter.model.smp.SmptSmartappInfo;
import com.commandcenter.service.puctomqservice.PucOnlineDataService;
import com.commandcenter.service.smp.SmpInterphoneInfoService;
import com.commandcenter.service.smp.SmptSmartappInfoService;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.ActiveMQMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author z26638
 * @create 2019-12-11 10:17
 * @desc puctomq的online信息监听
 **/
@Component
public class TopicPuctomqOnlineListener {
    protected final static Logger logger = LoggerFactory.getLogger(TopicPuctomqOnlineListener.class);

    @Autowired
    private PublishToAPP publishToAPP;
    @Autowired
    private PucOnlineDataService pucOnlineDataService;
    @Autowired
    private SmpInterphoneInfoService smpInterphoneInfoService;
    @Autowired
    private SmptSmartappInfoService smptSmartappInfoService;

    @Value("${system.notice.app.sendAppOnline}")
    private String sendAppOnline;

    @JmsListener(destination = "PUC_OnlineStatus_onlineStatus", containerFactory = "jmsTopicListenerMpa")
    public void receiveOnlineInfo(ActiveMQBytesMessage text) {
        try {
            MqResponseData mqResponseData = null;
            ActiveMQBytesMessage message = text;
            MQParamModel param;
            // 接受消息
            logger.info("TopicPuctomqOnlineListener：\t" + new String(message.getContent().getData(), "utf-8"));
            // 解析消息头
            mqResponseData = JSON.parseObject(new String(message.getContent().getData(), "utf-8"), MqResponseData.class);
            Map<String, Object> onLineMap = mqResponseData.getBody();

            if (onLineMap.get("ID") == null) {
                return;
            }
            for (Map.Entry<String, Object> entry : onLineMap.entrySet()) {
                if (onLineMap.get(entry.getKey()) == null) {
                    onLineMap.put(entry.getKey(), "");
                }
            }

            Map<String, Object> appOnLineMap = new HashMap<>();
            //将收到的消息存库
            PucOnlineData pucOnlineData = new PucOnlineData();
            pucOnlineData.setGuid(UUID.randomUUID().toString());
            pucOnlineData.setId(onLineMap.get("ID").toString());
            pucOnlineData.setPucId(onLineMap.get("PUC_ID").toString());
            pucOnlineData.setSystemId(onLineMap.get("SYSTEM_ID").toString());
            pucOnlineData.setDeviceId(onLineMap.get("DEVICE_ID").toString());
            pucOnlineData.setDeviceType(onLineMap.get("DEVICETYPE").toString());
            pucOnlineData.setReceiveTime(onLineMap.get("RECEIVETIME").toString());
            pucOnlineData.setIsOnline(onLineMap.get("ISONLINE").toString());
            pucOnlineData.setCaseId(onLineMap.get("CASEID").toString());
            pucOnlineData.setPdtState(onLineMap.get("PDTSTATE").toString());
            pucOnlineData.setIsLock(onLineMap.get("ISLOCK").toString());
            pucOnlineData.setSourceType(onLineMap.get("SOURCETYPE").toString());
//            pucOnlineData.setSubSapGuid(onLineMap.get("SUBSAPGUID").toString());


            //批量删除本地mpa_t_online_info表
            pucOnlineDataService.deletePucOnlineDataById(pucOnlineData.getId());

            //批量插入mpa_t_online_info表
            pucOnlineDataService.insertNonEmptyPucOnlineData(pucOnlineData);
            //发送mq给客户端
            String deviceGuid = "";
            String deviceType = "";
            if ("0".equals(onLineMap.get("SOURCETYPE"))) {
                deviceGuid = smpInterphoneInfoService.selectGuidByDeviceId(onLineMap.get("DEVICE_ID").toString());
                if (deviceGuid != null && !"".equals(deviceGuid)) {
                    SmpInterphoneInfo smpInterphoneInfo = smpInterphoneInfoService.selectInterphoneInfoById(deviceGuid);
                    if (smpInterphoneInfo != null) {
                        //由于puc上下线协议deviceType不准,通过设备guid查询deviceType
                        deviceType = smpInterphoneInfo.getDeviceType();
                    }
                }
                logger.info("设备guid---" + deviceGuid);
            }
            if (Constant.APP_ACCOUNT_TYPE.equals(onLineMap.get("SOURCETYPE")) || Constant.PC_ACCOUNT_TYPE.equals(onLineMap.get("SOURCETYPE"))) {
                Map content = new HashMap<>();
                content.put("dispatcherAccount", onLineMap.get("DEVICE_ID").toString());
                SmptSmartappInfo smptSmartappInfo = smptSmartappInfoService.selectSmptSmartappInfoByMap(content);
                if (smptSmartappInfo != null) {
                    deviceGuid = smptSmartappInfo.getGuid();
                    logger.info("调度员账号guid---" + deviceGuid);
                }
            }


            appOnLineMap.put("pucId", onLineMap.get("PUC_ID"));
            appOnLineMap.put("systemId", onLineMap.get("SYSTEM_ID"));
            appOnLineMap.put("deviceId", deviceGuid);
            appOnLineMap.put("deviceType", deviceType);
            appOnLineMap.put("receiveTime", onLineMap.get("RECEIVETIME"));
            appOnLineMap.put("isOnline", onLineMap.get("ISONLINE"));
            appOnLineMap.put("caseId", onLineMap.get("CASEID"));
            appOnLineMap.put("pdtState", onLineMap.get("PDTSTATE"));
            appOnLineMap.put("isLock", onLineMap.get("ISLOCK"));
            appOnLineMap.put("id", onLineMap.get("ID"));
            appOnLineMap.put("sourceType", onLineMap.get("SOURCETYPE"));
//            appOnLineMap.put("subSapGuid",onLineMap.get("SUBSAPGUID"));


            param = new MQParamModel(JSON.toJSONString(appOnLineMap, SerializerFeature.WriteMapNullValue), sendAppOnline);
            publishToAPP.sendToApp(param);
        } catch (Exception e) {
            logger.error("TopicPuctomqOnlineListener同步上下线消息失败", e);

        }
    }

}
