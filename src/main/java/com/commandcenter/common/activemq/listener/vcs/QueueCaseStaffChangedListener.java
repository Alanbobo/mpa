package com.commandcenter.common.activemq.listener.vcs;

import com.alibaba.fastjson.JSON;
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
public class QueueCaseStaffChangedListener {
    protected final static Logger logger = LoggerFactory.getLogger(QueueCaseStaffChangedListener.class);

    @Autowired
    private VcstCaseInfoService vcstCaseInfoService;

    @Value("${system.notice.app.staffChangeMQ}")
    private String staffChangeMQ;

    @JmsListener(destination = "queue_mpa_case_staff_changed" ,containerFactory = "jmsQueueListenerMpa")
    public void receiveAlarmCase(ActiveMQBytesMessage text) {
        try {
            logger.info("QueueCaseStaffChangedListener收到了消息：\t" + new String(text.getContent().getData(), "utf-8"));
            /**
             * 报文格式如下
             * {"id":"20180920094455180","staffs":[{"staffid":"00140A86-E661-4FB7-8FB4-A2C3CD705686","status":"JQZT003","iscancel":false},
             * {"staffid":"001C8FAC-ACEB-4095-A30F-26EB7AF741F6","status":"JQZT003","iscancel":false},
             * {"staffid":"0008E210-291D-489F-9D5A-D83B39D899A9","status":"JQZT003","iscancel":false}]}
             */
            String mesString = new String(text.getContent().getData(), "utf-8");
            Map<String,Object> changeMap = JSON.parseObject(mesString, Map.class);

            vcstCaseInfoService.changeDispatchInfo(changeMap, staffChangeMQ);
        }catch (Exception e){
            logger.error("调派变更失败，e:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
