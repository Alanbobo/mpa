package com.commandcenter.controller.ppcsController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.commandcenter.common.activemq.component.PublishToVCS;
import com.commandcenter.common.utils.HttpRequest;
import com.commandcenter.common.utils.R;
import com.commandcenter.model.ppcs.DutyDetailInfo;
import com.commandcenter.model.ppcs.DutyListInfo;
import com.commandcenter.service.caseservice.VcstCaseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

/**
 * @author r25437
 * @create 2019-01-21 16:06
 * @desc 巡逻防控接口
 **/
@RestController
@RequestMapping("/ppcs")
public class PpcsController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${ipcs.url}")
    private String ppcsUrl;

    @Value("${ipcs.detail}")
    private String ppcsDetail;

    @Value("${ipcs.list}")
    private String ppcsList;

    @Value("${ipcs.postState}")
    private String ppcsPostState;

    @Value("${ipcs.dutyInfo}")
    private String ppcsDutyInfo;

    @Value("${ipcs.ScheduleStatus}")
    private String ppcsScheduleStatus;


    @Autowired
    private PublishToVCS publishToVCS;

    @Autowired
    private VcstCaseInfoService vcstCaseInfoService;

    /**
     * 查询排班详细信息
     */
    @RequestMapping(value = "/getDutyDetail", method = RequestMethod.POST)
    public R getDutyDetail(@RequestBody Map<String,Object> bodyMap) throws IOException {
        Map<String,Object> queryMap = new HashMap<>(3);
        //入参的para节点map
        Map<String,Object> inputParaMap = (Map<String,Object>)bodyMap.get("para");
        queryMap.put("staffGuid",inputParaMap.get("staffGuid"));
        queryMap.put("startTime",inputParaMap.get("startTime"));
        queryMap.put("endTime",inputParaMap.get("endTime"));
        String queryJson = JSON.toJSONString(queryMap);
        StringBuilder url = new StringBuilder();
        url.append(ppcsUrl).append(ppcsDetail);
        String detailResponseJson = HttpRequest.sendPost(url.toString(),queryJson);
        logger.info("getDutyDetail result is ======"+detailResponseJson);
        if (detailResponseJson != null && !detailResponseJson.trim().equals("")) {
            DutyDetailInfo dutyDetailInfo = JSON.parseObject(detailResponseJson,DutyDetailInfo.class);
            return R.ok().put("result",dutyDetailInfo);
        }else {
            return R.error(500, "getDutyDetail run error ");
        }

    }

    /**
     * 按月查询排班信息
     */
    @RequestMapping(value = "/dutyList", method = RequestMethod.POST)
    public R getDutyList(@RequestBody Map<String,Object> bodyMap) throws IOException {
        Map<String, Object> queryMap = new HashMap<>(2);
        //入参的para节点map
        Map<String, Object> inputParaMap = (Map<String, Object>) bodyMap.get("para");
        queryMap.put("staffGuid", inputParaMap.get("staffGuid"));
        queryMap.put("month", inputParaMap.get("month"));
        String queryJson = JSON.toJSONString(queryMap);
        StringBuilder url = new StringBuilder();
        url.append(ppcsUrl).append(ppcsList);
        String listReponseJson = HttpRequest.sendPost(url.toString(), queryJson);
        logger.info("dutyList result is ======" + listReponseJson);
        if (listReponseJson != null && !listReponseJson.trim().equals("")) {
            DutyListInfo dutyListInfo = JSON.parseObject(listReponseJson, DutyListInfo.class);
            return R.ok().put("result", dutyListInfo);
        } else {
            return R.error(500, "dutyList run error ");
        }

    }
    /**
     * 按月查询排班信息,并且查询某时间段的详细信息
     */
    @RequestMapping(value = "/dutyListAndDetail", method = RequestMethod.POST)
    public R getDutyListAndDetail(@RequestBody Map<String,Object> bodyMap) throws IOException {
        Map<String,Object> queryMap = new HashMap<>(3);
        //入参的para节点map
        Map<String,Object> inputParaMap = (Map<String,Object>)bodyMap.get("para");
        queryMap.put("staffGuid",inputParaMap.get("staffGuid"));
        queryMap.put("startTime",inputParaMap.get("startTime"));
        queryMap.put("endTime",inputParaMap.get("endTime"));
        queryMap.put("month",inputParaMap.get("month"));
        Map<String,Object> returnMap = null;
        try {
            String queryJson = JSON.toJSONString(queryMap);
            String detailReponseJson = HttpRequest.sendPost(ppcsUrl+ppcsDetail,queryJson);
            logger.info("dutyListAndDetail ppcsDetail result is ======"+detailReponseJson);
            DutyDetailInfo dutyDetailInfo = JSON.parseObject(detailReponseJson,DutyDetailInfo.class);


            queryJson = JSON.toJSONString(queryMap);
            String listReponseJson = HttpRequest.sendPost(ppcsUrl+ppcsList.toString(),queryJson);
            logger.info("dutyListAndDetail ppcsList result is ======"+listReponseJson);
            DutyListInfo dutyListInfo = JSON.parseObject(listReponseJson,DutyListInfo.class);

            returnMap = new HashMap<>();
            returnMap.put("code",dutyListInfo.getCode());
            returnMap.put("mess",dutyListInfo.getMess());
            returnMap.put("type",dutyListInfo.getType());
            returnMap.put("messdetails",dutyListInfo.getMessdetails());
            returnMap.put("data",dutyListInfo.getData());
            returnMap.put("detail",dutyDetailInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("dutyListAndDetail run error "+e.getMessage());
            return R.error(500, "dutyListAndDetail run error ");
        }

        return R.ok().put("result",returnMap);
    }

    /**
     * 通过ppcs获取当前警员上下岗状态信息
     */
    @RequestMapping(value = "/getPostState", method = RequestMethod.POST)
    public R getPostState(@RequestBody Map<String,Object> bodyMap) throws IOException {

        Map<String,Object> inputParaMap = (Map<String,Object>)bodyMap.get("para");
        Map<String,Object> queryMap = new HashMap<>(1);
        //入参的para节点map
        queryMap.put("staffGuid",inputParaMap.get("staffGuid"));
        String queryJson = JSON.toJSONString(queryMap);
        StringBuilder url = new StringBuilder();
        url.append(ppcsUrl).append(ppcsPostState);
        try{
            String stateReponseJson = HttpRequest.sendPost(url.toString(),queryJson);
            logger.info("ppcsPostState result is ======"+stateReponseJson);
            if(stateReponseJson != null && !"".equals(stateReponseJson)){
                JSONObject jObj = JSON.parseObject(stateReponseJson);
                return R.ok().put("state",jObj.getString("status"));
            }else{
                return R.error(-100,"查询ipcs结果为空");
            }
        }catch(Exception e){
            e.printStackTrace();
            return R.error(-101,"无法调用IPCS接口");
        }

    }

    /**
     * 警员上下岗状态信息变更，由于vcs和ipcs都用此状态，因此发布到MQ上
     */
    @RequestMapping(value = "/postStateChange", method = RequestMethod.POST)
    public R postStateChange(@RequestBody Map<String,Object> bodyMap) throws IOException {
        Map<String,Object> queryMap = new HashMap<>(4);
        //入参的para节点map
        try {
            Map<String,Object> inputParaMap = (Map<String,Object>)bodyMap.get("para");
            queryMap.put("staffGuid",inputParaMap.get("staffGuid"));
            queryMap.put("status",inputParaMap.get("state"));
            queryMap.put("longitude",inputParaMap.get("longitude"));
            queryMap.put("latitude",inputParaMap.get("latitude"));
            String queryJson = JSON.toJSONString(queryMap);
            publishToVCS.publicDataToVCS("commandcenter_working_status_changed",queryJson);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(-102,e.getMessage());
        }

        return R.ok();
    }



    /**
     * 根据组织机构和日期，查看值班领导，值班人员，值班长等信息
     *
     */
    @RequestMapping(value = "/getDutyInfoByOrg", method = RequestMethod.POST)
    public R getDutyInfoByOrg(@RequestBody Map<String,Object> bodyMap) throws IOException {
        Map<String,Object> inputParaMap = (Map<String,Object>)bodyMap.get("para");

        String queryJson = JSON.toJSONString(inputParaMap);
        StringBuilder url = new StringBuilder();
        url.append(ppcsUrl).append(ppcsDutyInfo);
        try{
            String dutyReponseJson = HttpRequest.sendPost(url.toString(),queryJson);
            logger.info("dutyReponseJson result is ======"+dutyReponseJson);
            if(dutyReponseJson != null && !"".equals(dutyReponseJson)){
                JSONObject jObj = JSON.parseObject(dutyReponseJson);
                return R.ok().put("result",jObj);
            }else{
                return R.error(-100,"查询ipcs值班信息结果为空");
            }
        }catch(Exception e){
            e.printStackTrace();
            return R.error(-101,"无法调用IPCS接口");
        }

    }


    /**
     * 通过组织机构获取当前组织机构下所有人员的 出警 巡逻 报备 离岗 这四种状态
     */
    @RequestMapping(value = "/getStaffStateByOrg", method = RequestMethod.POST)
    public R getPostStateByOrg(@RequestBody Map<String,Object> bodyMap) {


        Map<String,Object> inputParaMap = (Map<String,Object>)bodyMap.get("para");
        String queryJson = JSON.toJSONString(inputParaMap);
        StringBuilder url = new StringBuilder();
        url.append(ppcsUrl).append(ppcsScheduleStatus);
        try{
            String scheduleReponseJson = HttpRequest.sendPost(url.toString(),queryJson);
            logger.info("ppcs-state  result is ======"+scheduleReponseJson);
            if(scheduleReponseJson != null && !"".equals(scheduleReponseJson)){

                Map<String, Object> returnMap = vcstCaseInfoService.getStaffStateByOrg(scheduleReponseJson, inputParaMap);
                logger.info("mpa-state result is ======"+JSON.toJSONString(returnMap, SerializerFeature.WriteMapNullValue));
                return R.ok(returnMap);
            }else{
                return R.error(-100,"查询ipcs人员状态结果为空");
            }
        }catch(Exception e){
            e.printStackTrace();
            return R.error(-101,"无法调用IPCS接口");
        }

    }
}
