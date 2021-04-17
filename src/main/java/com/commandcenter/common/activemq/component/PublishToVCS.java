package com.commandcenter.common.activemq.component;

import com.alibaba.fastjson.JSON;
import com.commandcenter.common.activemq.model.MqHeaderData;
import com.commandcenter.common.activemq.model.MqRequestData;
import com.commandcenter.common.activemq.service.ProducerVcsPtp;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.DateUtil;
import com.commandcenter.common.utils.GUID;
import com.commandcenter.model.gps.GpsDataModel;
import com.commandcenter.model.gps.GpsDataModelForVcsMq;
import com.commandcenter.model.smp.vo.LoginForVcs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author r25437
 * @create 2018-09-12 11:42
 * @desc 向VCS发送消息
 **/
@Component
public class PublishToVCS {
    @Autowired
    private ProducerVcsPtp producerVcsPtp;

    @Value("${system.notice.vcs.loginTipUrl}")
    private String loginTipUrl;

    @Value("${system.notice.vcs.gpsUrl}")
    private String gpsUrl;

    /**
     * @create 2018-09-14 14:42
     * @desc 用户登陆登出消息通知VCS
     **/
    public void notifyLoginToVCS(LoginForVcs loginForVcs){
        MqHeaderData mqHeaderData = new MqHeaderData();
        loginForVcs.setRECEIVETIME(DateUtil.curDateTime());
        loginForVcs.setSOURCETYPE("1");
        //安保展示需要设置puc和系统id
        //loginForVcs.setPUC_ID("00001");
        //loginForVcs.setSYSTEM_ID("001");
        MqRequestData mqRequestData = new MqRequestData();
        mqRequestData.setHeader(mqHeaderData);
        mqRequestData.setBody(loginForVcs);
        String message = JSON.toJSONString(mqRequestData);

        try {
            producerVcsPtp.publishTopic(loginTipUrl, message);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    /**
     * @create 2018-10-23 14:42
     * @desc GPS消息通知VCS
     **/
    public void publicGpsDataToVCS(GpsDataModel gpsDataModel){
        MqHeaderData mqHeaderData = new MqHeaderData();
        mqHeaderData.setSystem_id("mpa");
        mqHeaderData.setSubsystem_id("mpal");
        mqHeaderData.setMsgid(GUID.getGuid());
        mqHeaderData.setRelated_id(null);
        mqHeaderData.setCmd("VCS_GPS_GpsData");
        mqHeaderData.setRequest("VCS_GPS_GpsData");
        mqHeaderData.setReques_type(Constant.MQ_REQUEST_TYPE_QUEUE);
        mqHeaderData.setReponse(null);
        mqHeaderData.setReponse_type(null);

        GpsDataModelForVcsMq gpsData = GpsDataModelForVcsMq.parseDataFromBasic(gpsDataModel);
        MqRequestData mqRequestData = new MqRequestData();
        mqRequestData.setHeader(mqHeaderData);
        mqRequestData.setBody(gpsData);

        String message = JSON.toJSONString(mqRequestData);

        try {
            producerVcsPtp.publishTopic(gpsUrl, message);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    /**
     * @create 2020-09-14 14:02
     * @desc 消息通知VCS,这里指通用消息，不在有固定结构，JSON报文即可
     **/
    public void publicDataToVCS(String topicNameStr,String message){
        try {
            producerVcsPtp.publishTopic(topicNameStr, message);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }
}
