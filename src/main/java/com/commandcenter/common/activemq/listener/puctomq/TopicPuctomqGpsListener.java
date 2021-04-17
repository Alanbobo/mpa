package com.commandcenter.common.activemq.listener.puctomq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.commandcenter.common.activemq.component.PublishToAPP;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.activemq.model.MqResponseData;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.model.puctomq.PucGpsGpsData;
import com.commandcenter.model.smp.SmptSmartappInfo;
import com.commandcenter.service.puctomqservice.PucGpsGpsDataService;
import com.commandcenter.service.puctomqservice.ReceivePuctomqDataService;
import com.commandcenter.service.smp.SmpInterphoneInfoService;
import com.commandcenter.service.smp.SmpStaffDeviceService;
import com.commandcenter.service.smp.SmptSmartappInfoService;
import org.apache.activemq.command.ActiveMQBytesMessage;
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
 * @author q32756
 *
 * @create 2019-08-15 10:17
 * @desc puctomq的上下线信息监听
 **/
@Component
public class TopicPuctomqGpsListener {
    protected final static Logger logger = LoggerFactory.getLogger(TopicPuctomqGpsListener.class);
    @Autowired
    private ReceivePuctomqDataService receivePuctomqDataService;
    @Autowired
    private PublishToAPP publishToAPP;
    @Autowired
    private PucGpsGpsDataService pucGpsGpsDataService;
    @Autowired
    private SmpInterphoneInfoService smpInterphoneInfoService;
    @Autowired
    private SmptSmartappInfoService smptSmartappInfoService;
    @Autowired
    private SmpStaffDeviceService smpStaffDeviceService;
    @Value("${system.notice.app.sendAppGps}")
    private String sendAppGps;

    @JmsListener(destination = "PUC_GPS_GpsData", containerFactory = "jmsTopicListenerMpa")
    public void receiveGpsInfo(ActiveMQBytesMessage text) {
        try {
            MqResponseData mqResponseData = null;
            ActiveMQBytesMessage message = text;
            MQParamModel param;
            // 接受消息
            logger.info("TopicPuctomqGpsListener：\t" + new String(message.getContent().getData(), "utf-8"));
            // 解析消息头
            mqResponseData = JSON.parseObject(new String(message.getContent().getData(), "utf-8"), MqResponseData.class);
            Map<String, Object> gpsMap = mqResponseData.getBody();
            Map<String, Object> appGpsMap = new HashMap<>();
            //将收到的消息存库
            PucGpsGpsData pucGpsGpsData = new PucGpsGpsData();
            pucGpsGpsData.setGuid(UUID.randomUUID().toString());
            pucGpsGpsData.setDeviceId(gpsMap.get("DEVICE_ID").toString());
            pucGpsGpsData.setLatitude(Double.parseDouble(gpsMap.get("LATITUDE").toString()));
            pucGpsGpsData.setLongitude(Double.parseDouble(gpsMap.get("LONGITUDE").toString()));
            pucGpsGpsData.setPucId(gpsMap.get("PUC_ID").toString());
            pucGpsGpsData.setSystemId(gpsMap.get("SYSTEM_ID").toString());
            PucGpsGpsData exisPucGpsData = pucGpsGpsDataService.selectGpsData(pucGpsGpsData.getDeviceId());
            //测试发现，会有device_id存在多条的情况，因此更新时，清除后重新插入，解决多条记录问题
            if (exisPucGpsData != null) {
                pucGpsGpsDataService.deletePucGpsDataByDeviceId(pucGpsGpsData.getDeviceId());
                pucGpsGpsDataService.insertPucGpsGpsData(pucGpsGpsData);
            } else {
                pucGpsGpsDataService.insertPucGpsGpsData(pucGpsGpsData);
            }
            //发送mq给客户端
            String deviceGuid = "";
            if ("0".equals(gpsMap.get("SOURCETYPE"))) {
                deviceGuid = gpsMap.get("PUC_ID").toString()+gpsMap.get("SYSTEM_ID").toString()+gpsMap.get("DEVICE_ID").toString();
               /* deviceGuid = smpInterphoneInfoService.selectGuidByDeviceId(gpsMap.get("DEVICE_ID").toString());
                //通过deviceGuid查找警员绑定关系，设备是否绑定警员需要告知前台
                if(deviceGuid!=null && !"".equals(deviceGuid)){
                    SmpStaffDeviceInfo smpStaffDeviceInfo = smpStaffDeviceService.selectStaffDeviceByDeviceGuid(deviceGuid);
                    if(smpStaffDeviceInfo!=null){
                        //如果是人员，则返回客户端的deviceId为设备puc_id+system_id+deviceId，如果是设备，则返回客户端对应手台的guid
                        deviceGuid = gpsMap.get("PUC_ID").toString()+gpsMap.get("SYSTEM_ID").toString()+gpsMap.get("DEVICE_ID").toString();
                    }
                }*/
            }
            if (Constant.APP_ACCOUNT_TYPE.equals(gpsMap.get("SOURCETYPE")) || Constant.PC_ACCOUNT_TYPE.equals(gpsMap.get("SOURCETYPE"))) {
                Map content = new HashMap<>(2);
                content.put("dispatcherAccount", gpsMap.get("DEVICE_ID").toString());
                SmptSmartappInfo smptSmartappInfo = smptSmartappInfoService.selectSmptSmartappInfoByMap(content);
                if (smptSmartappInfo != null) {
                    deviceGuid = gpsMap.get("PUC_ID").toString()+gpsMap.get("SYSTEM_ID").toString()+gpsMap.get("DEVICE_ID").toString();
                }
            }
            appGpsMap.put("spacecoordinate", gpsMap.get("SPACECOORDINATE"));
            appGpsMap.put("longWe", gpsMap.get("LONG_WE"));
            appGpsMap.put("staffGuid", gpsMap.get("STAFF_ID"));
            appGpsMap.put("state", gpsMap.get("STATE"));
            appGpsMap.put("sourceType", gpsMap.get("SOURCETYPE"));
            appGpsMap.put("pucId", gpsMap.get("PUC_ID"));
            appGpsMap.put("latNs", gpsMap.get("LAT_NS"));
            appGpsMap.put("systemId", gpsMap.get("SYSTEM_ID"));
            appGpsMap.put("derection", gpsMap.get("DERECTION"));
            appGpsMap.put("speed", gpsMap.get("SPEED"));
            appGpsMap.put("gpsDateTime", gpsMap.get("GPS_DATETIME"));
            appGpsMap.put("rssidown", gpsMap.get("RSSIDOWN"));
            appGpsMap.put("rssiup", gpsMap.get("RSSIUP"));
            appGpsMap.put("id", gpsMap.get("ID"));
            appGpsMap.put("deviceId", deviceGuid);
            appGpsMap.put("latitude", gpsMap.get("LATITUDE"));
            appGpsMap.put("longitude", gpsMap.get("LONGITUDE"));
            appGpsMap.put("electricity", gpsMap.get("ELECTRICITY"));
            param = new MQParamModel(JSON.toJSONString(appGpsMap, SerializerFeature.WriteMapNullValue), sendAppGps);
            publishToAPP.sendToApp(param);
        } catch (Exception e) {
            logger.error("TopicPuctomqGpsListener同步GPS消息失败~:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
