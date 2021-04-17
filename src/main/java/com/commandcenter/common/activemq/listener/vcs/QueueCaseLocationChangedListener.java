package com.commandcenter.common.activemq.listener.vcs;

import com.alibaba.fastjson.JSON;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.model.casemodel.VcstCaseInfo;
import com.commandcenter.service.caseservice.VcstCaseInfoService;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author r25437
 * @create 2018-09-17 10:04
 * @desc 警情监听
 **/
@Component
public class QueueCaseLocationChangedListener {
    protected final static Logger logger = LoggerFactory.getLogger(QueueCaseLocationChangedListener.class);

    @Autowired
    private VcstCaseInfoService vcstCaseInfoService;

    @Value("${system.notice.app.locationChangeMQ}")
    private String locationChangeMQ;

    @JmsListener(destination = "queue_mpa_case_location_changed" ,containerFactory = "jmsQueueListenerMpa")
    public void receiveAlarmCase(ActiveMQBytesMessage text) {
        try {
            logger.info("QueueCaseLocationChangedListener收到了消息：\t" + new String(text.getContent().getData(), "utf-8"));
            /**
             *报文格式如下
             * {"id":"20180919134815770","longitude":0.0,"latitude":0.0}
             */
            String mesString = new String(text.getContent().getData(), "utf-8");
            Map<String,Object> changeMap = JSON.parseObject(mesString, Map.class);

            //根据警情id，修改经纬度信息
            VcstCaseInfo vcstCaseInfo = new VcstCaseInfo();
            vcstCaseInfo.setId(changeMap.get("id").toString());
            vcstCaseInfo.setLatitude(Double.valueOf(changeMap.get("latitude").toString()));
            vcstCaseInfo.setLongitude(Double.valueOf(changeMap.get("longitude").toString()));
            vcstCaseInfo.setCaseaddress(changeMap.get("address").toString());

            //修改警情信息
            int updateRow = vcstCaseInfoService.updateNonEmptyVcstCaseInfoById(vcstCaseInfo);
            //如果修改到记录，在进行推送，否则不进行推送
            if(updateRow>0) {
                //推送最新警情信息给APP
                vcstCaseInfoService.sendCaseInfoToStaffApp(vcstCaseInfo.getId(), Constant.DISPATCH_TYPE.ORG.getValue(), locationChangeMQ);
            }
        }catch (Exception e){
            logger.error("插入警情失败，e:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
