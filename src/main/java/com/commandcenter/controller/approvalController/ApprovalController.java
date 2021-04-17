package com.commandcenter.controller.approvalController;

import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.HttpRequest;
import com.commandcenter.common.utils.R;
import com.commandcenter.controller.baseController.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/approval")
public class ApprovalController extends BaseController {


    @Value("${vcs.http.url}")
    private String vcsHttpUrl;

    @Value("${vcs.http.port}")
    private String vcsHttpPort;
    /**
     * 通过警情ID,获取警情申请批示列表信息
     */
    @RequestMapping(value = "/getApplyApprovalListByCaseId", method = RequestMethod.POST)
    public R getApplyApprovalListByCaseId(@RequestBody JSONObject jsonObject) throws IOException {

        R resultMap = null;
        Map<String, Object> ResponseMap = new HashMap<>();

        try {
            if (jsonObject != null) {
                JSONObject paraJsonObj = jsonObject.getJSONObject("para");
                if (paraJsonObj != null) {
                    String jsonStr = jsonObject.getJSONObject("para").toJSONString();
                    String caseResponseStr = HttpRequest.sendPost(vcsHttpUrl + ":" + vcsHttpPort + Constant.APPROCAL_LIST_BY_CASEID_URL, jsonStr);

                    logger.info("警情ID申请批示列表：" + caseResponseStr);
                    if (caseResponseStr != null && !caseResponseStr.trim().equals("")) {
                        ResponseMap = JSONObject.parseObject(caseResponseStr, Map.class);
                    }

                    if (ResponseMap.containsKey("Success") && (boolean) ResponseMap.get("Success")) {
                        resultMap = R.ok();
                    } else {
                        resultMap = R.error(-1, "警情ID申请批示列表异常 " + caseResponseStr);
                    }

                    resultMap.putAll(ResponseMap);
                }

            }

            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getApplyApprovalListByCaseId run error " + e.getStackTrace());
            return R.error();
        }
    }

    /**
     * 通过领导ID,获取警情申请批示列表信息
     */
    @RequestMapping(value = "/getApplyApprovalListByLeaderID", method = RequestMethod.POST)
    public R getApplyApprovalListByLeaderID(@RequestBody JSONObject jsonObject) throws IOException {

        R resultMap = null;
        Map<String, Object> ResponseMap = new HashMap<>();

        try {
            if (jsonObject != null) {
                JSONObject paraJsonObj = jsonObject.getJSONObject("para");
                if (paraJsonObj != null) {
                    String jsonStr = jsonObject.getJSONObject("para").toJSONString();
                    String leaderResponseStr = HttpRequest.sendPost(vcsHttpUrl + ":" + vcsHttpPort + Constant.APPROCAL_LIST_BY_lEADER_URL, jsonStr);

                    logger.info("领导ID申请批示列表：" + leaderResponseStr);
                    if (leaderResponseStr != null && !leaderResponseStr.trim().equals("")) {
                        ResponseMap = JSONObject.parseObject(leaderResponseStr, Map.class);
                    }

                    if (ResponseMap.containsKey("Success") && (boolean) ResponseMap.get("Success")) {
                        resultMap = R.ok();
                    } else {
                        resultMap = R.error(-1, "领导ID申请批示列表异常 " + leaderResponseStr);
                    }

                    resultMap.putAll(ResponseMap);
                }

            }

            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getApplyApprovalListByLeaderID run error " + e.getStackTrace());
            return R.error();
        }
    }

    /**
     * 批示详情
     */
    @RequestMapping(value = "/getApplyApprovalInfoByID", method = RequestMethod.POST)
    public R getApplyApprovalInfoByID(@RequestBody JSONObject jsonObject) throws IOException {

        R resultMap = null;
        Map<String, Object> ResponseMap = new HashMap<>();

        try {
            if (jsonObject != null) {
                JSONObject paraJsonObj = jsonObject.getJSONObject("para");
                if (paraJsonObj != null) {
                    String jsonStr = jsonObject.getJSONObject("para").toJSONString();
                    String approvalResponseStr = HttpRequest.sendPost(vcsHttpUrl + ":" + vcsHttpPort + Constant.APPROCAL_INFO_BY_ID, jsonStr);

                    logger.info("警情ID申请批示详情：" + approvalResponseStr);
                    if (approvalResponseStr != null && !approvalResponseStr.trim().equals("")) {
                        ResponseMap = JSONObject.parseObject(approvalResponseStr, Map.class);
                    }

                    if (ResponseMap.containsKey("Success") && (boolean) ResponseMap.get("Success")) {
                        resultMap = R.ok();
                    } else {
                        resultMap = R.error(-1, "警情ID申请批示详情异常 " + approvalResponseStr);
                    }

                    resultMap.putAll(ResponseMap);
                }

            }

            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getApplyApprovalInfoByID run error " + e.getStackTrace());
            return R.error();
        }
    }
}
