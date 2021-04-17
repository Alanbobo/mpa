package com.commandcenter.common.activemq.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.activemq.model.IccCaseFeedBackModel;
import com.commandcenter.common.activemq.model.MqHeaderData;
import com.commandcenter.common.activemq.model.MqRequestData;
import com.commandcenter.common.activemq.service.ProducerIccPtp;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.GUID;
import com.commandcenter.model.wcf.FeedbackModelForWcf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author r25437
 * @create 2018-10-25 9:40
 * @desc ICC系统MQ对接组件
 **/
@Component
public class PublishToIcc {
    @Autowired
    private ProducerIccPtp producerIccPtp;

    @Value("${jms.icc.queryFeedBackQueue}")
    private String queryFeedBackQueueName;

    @Value("${jms.icc.feedBackQueue}")
    private String feedBackQueueName;

    @Value("${jms.icc.feedBackPublishQueue}")
    private String feedBackPublishQueue;

    @Value("${jms.icc.caseStatusFinishQueue}")
    private String caseStatusFinishQueue;

    @Value("${jms.icc.getCaseIdQueue}")
    private String getCaseIdQueue;

    @Value("${jms.icc.backAlarmIccQueue}")
    private String backAlarmIccQueue;

    @Value("${jms.icc.getNoticeIccQueue}")
    private String getNoticeIccQueue;

    @Value("${jms.icc.commitAlarmIccQueue}")
    private String commitAlarmIccQueue;

    private static final String CMD_GET_FEEDBACK = "app_get_feedback_request";
    private static final String CMD_UPDATE_FEEDBACK = "app_edit_feedback_request";
    private static final String CMD_VCS_UPDATE_FEEDBACK_NOTIFY = "vcs_edit_feedback_notify";
    private static final String CMD_VCS_CASE_FINISH_NOTIFY = "finish_case";

    /**
     * 获取警单id
     */
    private static final String CMD_ICC_DEAL_CASE_ID = "app_get_id_request";

    /**
     * 退单
     */
    private static final String CMD_ICC_BACK_ALARM = "app_back_alarm_request";


    /**
     * 获取公告
     */
    private static final String CMD_ADD_NOTICE_SYNC = "add_notice_sync";


    /**
     * 现场接警
     */
    private static final String CMD_APP_COMIT_ALARM_REQUEST = "app_comit_alarm_request";



    public IccCaseFeedBackModel getCaseFeedBackFromIccById(String caseId, String deptCode){

        Map<String,Object> inputMap = new HashMap<>();
        inputMap.put("alarm_id",caseId);
        inputMap.put("process_dept_code",deptCode);

        String message = getMqSendMessage(CMD_GET_FEEDBACK, inputMap);

        String responseJson = producerIccPtp.pubAndOnceRece(queryFeedBackQueueName, message);
        if(responseJson!=null && !"".equals(responseJson)) {
            JSONObject bodyJson = JSON.parseObject(responseJson, JSONObject.class);
            IccCaseFeedBackModel iccCaseFeedBackModel = JSON.parseObject(bodyJson.get("body").toString(), IccCaseFeedBackModel.class);
            return iccCaseFeedBackModel;
        }else{
            return new IccCaseFeedBackModel();
        }
    }

    public void sendVcsFeedBackToIcc(List<FeedbackModelForWcf> feedbackModelForWcfList){
        try {
            String message = getMqSendMessage(CMD_VCS_UPDATE_FEEDBACK_NOTIFY, feedbackModelForWcfList);
            producerIccPtp.sendMessage(feedBackPublishQueue, message);
            return;
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
    }

    public void sendCaseFinishToIcc(String caseId){
        try {
            Map<String, Object> caseMap = new HashMap<>(1);
            caseMap.put("caseID", caseId);
            String message = getMqSendMessage(CMD_VCS_CASE_FINISH_NOTIFY, caseMap);
            producerIccPtp.sendMessage(caseStatusFinishQueue, message);
            return;
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    public Map<String,Object> feedBackToIcc(Map<String,Object> map){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String message = getMqSendMessage(CMD_UPDATE_FEEDBACK, map);
            String responseJson = producerIccPtp.pubAndOnceRece(feedBackQueueName, message);
            if (responseJson != null && !"".equals(responseJson)) {
                JSONObject bodyJson = JSON.parseObject(responseJson, JSONObject.class);
                resultMap = JSON.parseObject(bodyJson.get("body").toString(), Map.class);
            }
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            return resultMap;
        }
    }

    public Map<String,Object> backAlarmToIcc(Map<String,Object> map){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String message = getMqSendMessage(CMD_ICC_BACK_ALARM, map);
            String responseJson = producerIccPtp.pubAndOnceRece(backAlarmIccQueue, message);
            if (responseJson != null && !"".equals(responseJson)) {
                JSONObject bodyJson = JSON.parseObject(responseJson, JSONObject.class);
                resultMap = JSON.parseObject(bodyJson.get("body").toString(), Map.class);
            }
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            return resultMap;
        }
    }


    public JSONObject getDealCaseId(Map<String,Object> map){

        String message = getMqSendMessage(CMD_ICC_DEAL_CASE_ID, map);

        String responseJson = producerIccPtp.pubAndOnceRece(getCaseIdQueue, message);
        if(responseJson!=null && !"".equals(responseJson)) {
            JSONObject bodyJson = JSON.parseObject(responseJson, JSONObject.class);
            return bodyJson.getJSONObject("body");
        }else{
            return null;
        }
    }

    public JSONObject getNotice(Map<String,Object> map){

        String message = getMqSendMessage(getNoticeIccQueue, map);

        String responseJson = producerIccPtp.pubAndOnceRece(getNoticeIccQueue, message);
        if(responseJson!=null && !"".equals(responseJson)) {
            JSONObject bodyJson = JSON.parseObject(responseJson, JSONObject.class);
            return bodyJson.getJSONObject("body");
        }else{
            return null;
        }
    }

    public Map<String,Object> commitAlarmToIcc(Map<String,Object> map){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String message = getMqSendMessage(CMD_APP_COMIT_ALARM_REQUEST, map);
            String responseJson = producerIccPtp.pubAndOnceRece(commitAlarmIccQueue, message);
            if (responseJson != null && !"".equals(responseJson)) {
                JSONObject bodyJson = JSON.parseObject(responseJson, JSONObject.class);
                resultMap = JSON.parseObject(bodyJson.get("body").toString(), Map.class);
            }
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            return resultMap;
        }
    }


    /**
     * 生成请求头信息
     * @param cmd
     * @return
     */
    private MqHeaderData prepareMqHeader(String cmd){
        MqHeaderData mqHeaderData = new MqHeaderData();
        mqHeaderData.setSystem_id("mpa");
        mqHeaderData.setSubsystem_id("mpa");
        mqHeaderData.setMsgid(GUID.getGuid());
        mqHeaderData.setRelated_id(null);
        mqHeaderData.setReques_type(Constant.MQ_REQUEST_TYPE_QUEUE);
        mqHeaderData.setReponse(null);
        mqHeaderData.setReponse_type(null);
        switch (cmd){
            case CMD_GET_FEEDBACK:
                mqHeaderData.setCmd(CMD_GET_FEEDBACK);
                mqHeaderData.setRequest(queryFeedBackQueueName);
                break;
            case CMD_UPDATE_FEEDBACK:
                mqHeaderData.setCmd(CMD_UPDATE_FEEDBACK);
                mqHeaderData.setRequest(feedBackQueueName);
                break;
            case CMD_VCS_UPDATE_FEEDBACK_NOTIFY:
                mqHeaderData.setCmd(CMD_VCS_UPDATE_FEEDBACK_NOTIFY);
                mqHeaderData.setRequest(feedBackPublishQueue);
				break;
            case CMD_VCS_CASE_FINISH_NOTIFY:
                mqHeaderData.setCmd(CMD_VCS_CASE_FINISH_NOTIFY);
                mqHeaderData.setRequest(caseStatusFinishQueue);
				break;
            case CMD_ICC_DEAL_CASE_ID:
                mqHeaderData.setCmd(CMD_ICC_DEAL_CASE_ID);
                mqHeaderData.setRequest(getCaseIdQueue);
                break;
            case CMD_ICC_BACK_ALARM:
                mqHeaderData.setCmd(CMD_ICC_BACK_ALARM);
                mqHeaderData.setRequest(backAlarmIccQueue);
                break;
            case CMD_ADD_NOTICE_SYNC:
                mqHeaderData.setCmd(CMD_ADD_NOTICE_SYNC);
                mqHeaderData.setRequest(getNoticeIccQueue);
                break;
            case CMD_APP_COMIT_ALARM_REQUEST:
                mqHeaderData.setCmd(CMD_APP_COMIT_ALARM_REQUEST);
                mqHeaderData.setRequest(commitAlarmIccQueue);
                break;
            default:
                break;
        }

        return mqHeaderData;
    }

    /**
     * 生成请求报文信息
     * @param cmd
     * @param params
     * @return
     */
    public String getMqSendMessage(String cmd, Object params){
        MqHeaderData mqHeaderData = prepareMqHeader(cmd);

        MqRequestData mqRequestData = new MqRequestData();
        mqRequestData.setHeader(mqHeaderData);
        mqRequestData.setBody(params);

        String message = JSON.toJSONString(mqRequestData);

        return message;
    }
}
