package com.commandcenter.common.wcf;

import com.alibaba.fastjson.JSONObject;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author r25437
 * @create 2018-09-07 11:09
 * @desc APi
 **/
public interface APi {

    @FormUrlEncoded
    @POST("test/postMes")
    Call<String> postMes(@Field("num") String num, @Field("name") String name);

    @FormUrlEncoded
    @GET("test/getMes")
    Call<String> getMes();

    @GET("services/caseService/getAppCaseList")
    Call<JSONObject> getAppCaseList(@Query("updateTime") String UpdateTime);


    @GET("services/caseService/saveAppFeedback")
    Call<JSONObject> saveAppFeedback(@Query("feedback") String feedBackModel);


    @GET("services/caseService/getHandleRecordList")
    Call<JSONObject> getHandleRecordList(@Query("caseId") String caseId);

    @GET("services/publicService/getAllDictList")
    Call<JSONObject> getAllDictList();

    //根据vcs提供的接口来写
    @GET("services/publicService/SendHeartbeat")
    Call<JSONObject> sendHeartBeatMsg(@Query("heartbeat") String heartBeatModel);
}
