package com.commandcenter.common.activemq.listener.vcs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.commandcenter.common.activemq.component.PublishToAPP;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.model.casemodel.MpaCaseOprInfo;
import com.commandcenter.service.caseservice.VcstCaseInfoService;
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
 * @author z26638
 * @create 2020-08-25 10:04
 * @desc 批示监听
 **/
@Component
public class QueueApplyApprovalListener {
    protected final static Logger logger = LoggerFactory.getLogger(QueueApplyApprovalListener.class);

    @Autowired
    private VcstCaseInfoService vcstCaseInfoService;

    @Autowired
    private PublishToAPP publishToAPP;

    @Autowired
    private SmpUserService smpUserService;


    @Value("${system.notice.app.approvalCreate}")
    private String approvalCreate;


    @JmsListener(destination = "queue_mpa_case_applyapproval_add", containerFactory = "jmsQueueListenerMpa")
    public void receiveAlarmCase(ActiveMQBytesMessage text) {
        try {
            logger.info("queue_mpa_case_applyapproval_add 收到了消息：\t" + new String(text.getContent().getData(), "utf-8"));

            /**
             *报文格式如下
             *   下发的字段传app，caseId就可以
             */
            String approvalString = new String(text.getContent().getData(), "utf-8");
            Map<String, Object> approvalMap = JSON.parseObject(approvalString, Map.class);
            List<String> userList = new ArrayList<>();
            String caseId = approvalMap.get("caseid").toString();
            String[] leaderIds = (String[]) approvalMap.get("leaderids");

            if (leaderIds == null || leaderIds.length == 0) {
                return;
            }
            List<String> userCodeList = smpUserService.selectUserCodeListByStaffGuid(leaderIds);
            if (userCodeList != null && !userCodeList.isEmpty()) {
                for (String userCode : userCodeList) {
                    if (Constant.tokenMapIns.get(userCode) != null) {
                        String tempNo = (String) Constant.tokenMapIns.get(userCode);
                        userList.add(tempNo);
                    }
                }
            }
            //批示新建下发
            MQParamModel param = new MQParamModel(caseId, approvalCreate);
            param.setEncryptFlag(true);
            param.setPersonalFlag(true);
            param.setUserNos(userList);
            publishToAPP.sendToApp(param);

            //批示下发到反馈流程，从vcs重新获取带有批示的反馈信息
            MpaCaseOprInfo mpaCaseOprInfo = new MpaCaseOprInfo();
            mpaCaseOprInfo.setCaseId(caseId);
            mpaCaseOprInfo.setHashCode(caseId.hashCode() > 0 ? caseId.hashCode() : caseId.hashCode() * -1);
            vcstCaseInfoService.insertMpaCaseOpr(mpaCaseOprInfo);
        } catch (Exception e) {
            logger.error("批示异常，e:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
