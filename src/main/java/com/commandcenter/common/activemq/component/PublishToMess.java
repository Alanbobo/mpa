package com.commandcenter.common.activemq.component;

import com.alibaba.fastjson.JSON;
import com.commandcenter.common.activemq.model.MqHeaderData;
import com.commandcenter.common.activemq.model.MqRequestData;
import com.commandcenter.common.activemq.service.ProduceMessPtp;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.DateUtil;
import com.commandcenter.common.utils.GUID;
import com.commandcenter.model.gps.GpsDataModel;
import com.commandcenter.model.gps.GpsDataModelForVcsMq;
import com.commandcenter.model.mess.TaskBeginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author q32756
 * @create 2019-09-03 10:46
 * @desc 推送MESS消息工具
 **/
@Component
public class PublishToMess {
    @Value("${system.notic.mess.taskBegin}")
    private String taskBeginUrl;
    @Autowired
    private ProduceMessPtp produceMessPtp;
    @Value("${system.notice.vcs.gpsUrl}")
    private String gpsUrl;
    public void taskBeginToMess(TaskBeginModel taskBeginModel){
        MqHeaderData mqHeaderData = new MqHeaderData();
        mqHeaderData.setSystem_id("mpa");
        mqHeaderData.setSubsystem_id("mpal");
        mqHeaderData.setMsgid(GUID.getGuid());
        mqHeaderData.setCmd("topic_mess_task_begin");
        mqHeaderData.setRequest("topic_mess_task_begin");
        MqRequestData mqRequestData = new MqRequestData();
        mqRequestData.setHeader(mqHeaderData);
        mqRequestData.setBody(taskBeginModel);

        String message = JSON.toJSONString(mqRequestData);

        try {
            produceMessPtp.publishTopic(taskBeginUrl, message);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    /**
     * @create 2019-09-06 14:42
     * @desc GPS消息通知MESS
     **/
    public void publicGpsDataToMess(GpsDataModel gpsDataModel){
        MqHeaderData mqHeaderData = new MqHeaderData();
        mqHeaderData.setSystem_id("mpa");
        mqHeaderData.setSubsystem_id("mpal");
        mqHeaderData.setMsgid(GUID.getGuid());
        mqHeaderData.setRelated_id(null);
        mqHeaderData.setCmd("PUC_GPS_GpsData");
        mqHeaderData.setRequest("PUC_GPS_GpsData");
        mqHeaderData.setReques_type(Constant.MQ_REQUEST_TYPE_QUEUE);
        mqHeaderData.setReponse(null);
        mqHeaderData.setReponse_type(null);

        GpsDataModelForVcsMq gpsData = GpsDataModelForVcsMq.parseDataFromBasic(gpsDataModel);
        MqRequestData mqRequestData = new MqRequestData();
        mqRequestData.setHeader(mqHeaderData);
        mqRequestData.setBody(gpsData);

        String message = JSON.toJSONString(mqRequestData);

        try {
            produceMessPtp.publishTopic(gpsUrl, message);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }
}
