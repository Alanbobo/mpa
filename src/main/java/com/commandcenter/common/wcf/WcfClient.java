package com.commandcenter.common.wcf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.commandcenter.model.wcf.CaseModelForWcf;
import com.commandcenter.model.wcf.DictionaryModelForWcf;
import com.commandcenter.model.wcf.FeedbackModelForWcf;
import com.commandcenter.model.wcf.HeartBeatModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author r25437
 * @create 2018-09-07 10:54
 * @desc VCS接口调用
 **/
public class WcfClient {
    private static Logger log = LoggerFactory.getLogger(WcfClient.class);
    //得到实例
    private static APi api = Network.getInstance().getApi();

    /**
     * 请求警情列表
     *
     * @param updateTime yyyy/MM/dd hh:mm:ss的字符串格式
     */
    public static List<CaseModelForWcf> getAppCaseList(String updateTime) throws Exception {
        Call<JSONObject> response = api.getAppCaseList(updateTime);
        try {
            log.info("wcf.getAppCaseList方法开始执行");
            Response<JSONObject> responseModel = response.execute();

            JSONObject body = responseModel.body();

            String json = body.getJSONArray("Result").toJSONString();

            log.info("wcf.getAppCaseList方法收到JSON---" + json);

            List<CaseModelForWcf> list = JSONObject.parseArray(json, CaseModelForWcf.class);

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 请求反馈列表
     */
    public static List<FeedbackModelForWcf> getHandleRecordList(String caseId) {
        log.info("wcf.getHandleRecordList方法开始执行,caseId="+caseId);
        Call<JSONObject> response = api.getHandleRecordList(caseId);
        try {
            log.info("wcf.getHandleRecordList方法执行结束,caseId="+caseId);
            log.info("getHandleRecordList方法返回结果为====================="+response.execute());
            Response<JSONObject> responseModel = response.execute();
            log.info("responseModel====================="+responseModel);
            JSONObject body = responseModel.body();

            String json = body.getJSONObject("Result").getJSONArray("Records").toJSONString();

            log.info("wcf.getHandleRecordList方法收到JSON---" + json+",caseId="+caseId);

            List<FeedbackModelForWcf> list = JSONObject.parseArray(json, FeedbackModelForWcf.class);
            for (FeedbackModelForWcf feedbackModelForWcf : list) {
                feedbackModelForWcf.setCaseID(caseId);
            }

            return list;
        } catch (Exception e) {
            log.error("getHandleRecordList==================",e);
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送心跳给vcs
     */
    public static String sendHeartBeatMsg(HeartBeatModel heartBeatModel) {
        String json = JSON.toJSONString(heartBeatModel);
        Call<JSONObject> response = api.sendHeartBeatMsg(json);
        try {
            log.info("wcf.sendHeartBeatMsg方法开始执行");
            Response<JSONObject> responseModel = response.execute();
            JSONObject body = responseModel.body();
            String serverName = body.getString("serverName");
            String serverVersion = body.getString("serverVersion");
            Date currentTime = body.getDate("currentTime");
            log.info("wcf.sendHeartBeatMsg方法收到Json---" + "serverName=" + serverName + " serverVersion=" + serverVersion + " currentTime" + currentTime);
            return body.toJSONString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 发送反馈
     */
    public static String saveAppFeedback(FeedbackModelForWcf feedbackModelForWcf) {

        String json = JSON.toJSONString(feedbackModelForWcf);
        log.info("wcf.saveAppFeedback方法开始执行===="+json);
        Call<JSONObject> response = api.saveAppFeedback(json);
        try {
            Response<JSONObject> responseModel = response.execute();

            JSONObject body = responseModel.body();

            String feedbackId = body.getString("Result");

            log.info("wcf.saveAppFeedback方法收到JSON---" + feedbackId+",json="+json);

            System.out.println(body);

            return feedbackId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取字典
     */
    public static List<DictionaryModelForWcf> getAllDictList() {
        Call<JSONObject> response = api.getAllDictList();
        try {
            log.info("wcf.getAllDictList方法开始执行");
            Response<JSONObject> responseModel = response.execute();

            JSONObject body = responseModel.body();

            String json = body.getJSONArray("Result").toJSONString();

            log.info("wcf.getAllDictList方法收到JSON---" + json);

            List<DictionaryModelForWcf> list = JSONObject.parseArray(json, DictionaryModelForWcf.class);

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断wcf接口状态
     */
    public static void checkWcfStatus() throws Exception{
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String now = simpleDateFormat.format(new Date());
            Call<JSONObject> call = api.getAppCaseList(now);
            Response r = call.execute();
            log.info("checkWcfStatus code========="+r.code());
            if(!r.isSuccessful()){
                Exception e = new Exception();
                e.printStackTrace();
                throw e;
            }
        }catch (Exception e){
            log.info("getAppCaseList Exception",e);
            e.printStackTrace();
            throw e;
        }
    }
}
