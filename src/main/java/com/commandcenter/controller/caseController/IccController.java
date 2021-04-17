package com.commandcenter.controller.caseController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.commandcenter.common.activemq.component.PublishToIcc;
import com.commandcenter.common.activemq.model.IccCaseFeedBackModel;
import com.commandcenter.common.utils.R;
import com.commandcenter.controller.baseController.BaseController;
import com.commandcenter.model.smp.SmpOrgInfo;
import com.commandcenter.model.smp.SmpUserInfo;
import com.commandcenter.service.smp.SmpOrgInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

/**
 * @author r25437
 * @create 2018-10-24 9:22
 * @desc ICC警综平台对接
 **/
@RestController
@RequestMapping("/case/icc")
public class IccController extends BaseController{
    @Autowired
    private PublishToIcc publishToIcc;

    @Autowired
    private SmpOrgInfoService smpOrgInfoService;

    private static final String FEED_BACK_SUCCESS = "0";
    private static final String FEED_BACK_FAIL = "1";

    private static final String COMMIT_ALARM_SUCCESS = "0";
    private static final String COMMIT_ALARM_FAIL = "1";

    /**
     * 综合反馈查询
     */
    @RequestMapping(value = "/feedbackList", method = RequestMethod.POST)
    public R getFeedbackList(@RequestBody Map<String,Object> bodyMap) throws IOException {
        //入参的para节点map
        Map<String,Object> inputParaMap = (Map<String,Object>)bodyMap.get("para");
        //获取登录用户信息
        SmpUserInfo smpUserInfo = getUser();
        //根据登录用户信息获取组织机构信息，获取
        SmpOrgInfo smpOrgInfo = smpOrgInfoService.selectSmpOrgInfoByUserGuid(smpUserInfo.getGuid());

        IccCaseFeedBackModel iccCaseFeedBackModel = new IccCaseFeedBackModel();
        String caseId = inputParaMap.get("caseId") == null ? "" : inputParaMap.get("caseId").toString();
        try{
            if(!"".equals(caseId) && smpOrgInfo != null && smpOrgInfo.getOrgGovCode() != null) {
                iccCaseFeedBackModel = publishToIcc.getCaseFeedBackFromIccById(caseId,smpOrgInfo.getOrgGovCode());
            }

            logger.info("feedbackList方法查询综合反馈信息服务执行成功---"+JSON.toJSONString(iccCaseFeedBackModel));

            return R.ok().put("info",iccCaseFeedBackModel);
        }catch (Exception e){
            return R.error(500,"");
        }
    }

    /**
     * 综合反馈
     */
    @RequestMapping(value = "/feedbackToIcc", method = RequestMethod.POST)
    public R feedbackToIcc(@RequestBody Map<String,Object> bodyMap) throws IOException {
        //入参的para节点map
        Map<String,Object> inputParaMap = (Map<String,Object>)bodyMap.get("para");

        String resultStr = "1";
        Map<String,Object> resultMap = publishToIcc.feedBackToIcc(inputParaMap);
        if(resultMap!=null && !resultMap.isEmpty()){
            resultStr = resultMap.get("result").toString().trim();
        }

        logger.info("feedbackToIcc方法综合反馈服务执行成功---"+JSON.toJSONString(resultMap));

        if(FEED_BACK_SUCCESS.equals(resultStr)){
            return R.ok();
        }else{
            return R.error(100,"反馈失败");
        }
    }

    /**
     * 获取出警id
     */
    @RequestMapping(value = "/getDealCaseId", method = RequestMethod.POST)
    public R getDealCaseId(@RequestBody Map<String,Object> bodyMap) throws IOException {
        //入参的para节点map
        Map<String,Object> inputParaMap = (Map<String,Object>)bodyMap.get("para");
        JSONObject jsonObject = publishToIcc.getDealCaseId(inputParaMap);
        if(jsonObject!=null){
            Map<String,Object> jsonMap = jsonObject.toJavaObject(Map.class);
            R resultMap = R.ok();
            resultMap.putAll(jsonMap);
            return resultMap;
        }else{
            return R.error(103,"获取警单id失败");
        }
    }

    /**
     * APP退单
     */
    @RequestMapping(value = "/backAlarmToIcc", method = RequestMethod.POST)
    public R backAlarmToIcc(@RequestBody Map<String,Object> bodyMap) throws IOException {
        //入参的para节点map
        Map<String,Object> inputParaMap = (Map<String,Object>)bodyMap.get("para");

        String resultStr = "1";
        Map<String,Object> resultMap = publishToIcc.backAlarmToIcc(inputParaMap);
        if(resultMap!=null && !resultMap.isEmpty()){
            resultStr = resultMap.get("result").toString().trim();
        }

        logger.info("backAlarmToIcc方法退单执行成功---"+JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue));

        if(FEED_BACK_SUCCESS.equals(resultStr)){
            return R.ok();
        }else{
            return R.error(900002,"退单失败");
        }
    }


    /**
     * 获取公告和通知
     */
    @RequestMapping(value = "/getNotice", method = RequestMethod.POST)
    public R getNotice(@RequestBody Map<String,Object> bodyMap) throws IOException {

        //入参的para节点map
        Map<String,Object> inputParaMap = (Map<String,Object>)bodyMap.get("para");

        JSONObject jsonObject = publishToIcc.getNotice(inputParaMap);
        if(jsonObject!=null){
            Map<String,Object> jsonMap = jsonObject.toJavaObject(Map.class);
            R resultMap = R.ok();
            resultMap.putAll(jsonMap);
            return resultMap;
        }else{
            return R.error(104," 获取公告和通知失败");
        }
    }





    /**
     * 现场接警,提交警单
     */
    @RequestMapping(value = "/commitAlarmToIcc", method = RequestMethod.POST)
    public R commitAlarmToIcc(@RequestBody Map<String,Object> bodyMap) throws IOException {
        //入参的para节点map
        Map<String,Object> inputParaMap = (Map<String,Object>)bodyMap.get("para");

        String resultStr = "1";
        logger.info("commitAlarmToIcc现场接警app传输入参---"+ JSON.toJSONString(inputParaMap, SerializerFeature.WriteMapNullValue));
        Map<String,Object> resultMap = publishToIcc.commitAlarmToIcc(inputParaMap);
        if(resultMap!=null && !resultMap.isEmpty()){
            resultStr = resultMap.get("result").toString().trim();
        }
        logger.info("commitAlarmToIcc现场接警成功---"+ JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue));
        if(COMMIT_ALARM_SUCCESS.equals(resultStr)){
            return R.ok();
        }else{
            return R.error(105,"现场接警失败");
        }
    }
}
