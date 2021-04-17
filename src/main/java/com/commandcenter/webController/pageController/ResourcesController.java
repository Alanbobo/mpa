package com.commandcenter.webController.pageController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.commandcenter.common.utils.R;
import com.commandcenter.service.caseservice.VcstCaseFeedbackService;
import com.commandcenter.service.puctomqservice.PucGpsGpsDataService;
import com.commandcenter.service.smp.SmpInterphoneInfoService;
import com.commandcenter.service.smp.SmpResourceService;
import com.commandcenter.service.smp.SmpStaffInfoService;
import com.commandcenter.service.smp.SmpVehicleService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resources")
public class ResourcesController {
    protected final static Logger logger = LoggerFactory.getLogger(ResourcesController.class);
    @Autowired
    private SmpResourceService smpResourceService;
    @Autowired
    private SmpVehicleService smpVehicleService;
    @Autowired
    private PucGpsGpsDataService pucGpsGpsDataService;
    @Autowired
    private SmpInterphoneInfoService smpInterphoneInfoService;
    @Autowired
    private SmpStaffInfoService smpStaffInfoService;

    @Autowired
    private VcstCaseFeedbackService vcstCaseFeedbackService;


    @Value("${smp.server.ip}")
    private String smpIp;

    @Value("${smp.server.port}")
    private String smpPort;

    /**
     * 查询所有地图资源GPS信息
     *
     * @return
     */
    @RequestMapping(value = "/resourcesInfo", method = RequestMethod.POST)
    public R getResourcesInfo() {


        List<Map> resourcesList = smpResourceService.selectResourcesList();
        List<Map> deviceList = smpInterphoneInfoService.selectMapDevice();
        for (Map device : deviceList) {
            device.put("pucType", "9");
            device.put("type", "MONITOR");
        }
        resourcesList.addAll(deviceList);
        return R.ok().put("resourceInfo", resourcesList).put("picUrl", "http://" + smpIp + ":" + smpPort + "/smp/download/");
    }

    /**
     * 批量查询GPS信息
     *
     * @param resourceMap
     * @return
     */
    @RequestMapping(value = "/getResourcesGps", method = RequestMethod.POST)
    public R getResourcesGps(@RequestBody Map<String,Object> resourceMap) {
        try {
            Map<String, Object> map = new HashMap<>(2);
            logger.info("获取地图传输数据=================================" + JSONObject.toJSONString(resourceMap));
            List<Map> requestList = (List<Map>)resourceMap.get("resource");
            List<Map> resultList = new ArrayList<>();
            if (requestList != null && !requestList.isEmpty()) {
                for (Map request : requestList) {
                    String type = null;
                    String deviceId = null;
                    if (request.containsKey("ResourceType") && request.get("ResourceType") != null) {
                        type = request.get("ResourceType").toString();
                        if (request.containsKey("ResourceId") && request.get("ResourceId") != null) {
                            deviceId = request.get("ResourceId").toString();
                            //查询设备以及GPS信息
                            if ("3".equals(type)) {
                                Map<String, Object> resultMap = pucGpsGpsDataService.selectGpsDataByDeviceId(deviceId);
                                if (resultMap != null && !resultMap.isEmpty()) {
                                    resultMap.put("type", "phone");
                                    resultMap.put("pucType", "3");
                                    resultMap.put("resourceType",type);
                                }
                                //摄像头首次上图未接收puctomq的上下线状态，默认初始值为上线
                                if(resultMap != null && ("8").equals(resultMap.get("deviceType")) && StringUtils.isBlank((String) resultMap.get("isOnline"))){
                                    resultMap.put("isOnline", "1");
                                }
                                resultList.add(resultMap);
                            }
                            //查询车辆以及GPS信息
                            if ("2".equals(type)) {
                                Map<String, Object> resultCarMap = smpVehicleService.selectVehicleGpsByGuid(deviceId);
                                if (resultCarMap != null && !resultCarMap.isEmpty()) {
                                    resultCarMap.put("pucType", "2");
                                    resultCarMap.put("resourceType",type);
                                    resultList.add(resultCarMap);
                                }
                            }
                            //查询人员以及GPS信息
                            if ("1".equals(type)) {
                                Map<String, Object> resultStaffMap = new HashMap<>();
                                resultStaffMap = smpStaffInfoService.selectStaffGpsByGuid(deviceId);
                                if (resultStaffMap!=null && StringUtils.isBlank((String) resultStaffMap.get("dispatcherAccount"))) {
                                    resultStaffMap = smpStaffInfoService.selectStaffGpsByDeviceGuid(deviceId);
                                    logger.info("窄带终端人员信息============" + JSON.toJSONString(resultStaffMap, SerializerFeature.WriteMapNullValue));
                                }
                                if (resultStaffMap != null && !resultStaffMap.isEmpty()) {
                                    resultStaffMap.put("type", "phone");
                                    resultStaffMap.put("pucType", "1");
                                    resultStaffMap.put("sType", "staff");
                                    resultStaffMap.put("resourceType",type);
                                    if (request.containsKey("ResourceId") && request.get("ResourceId") != null) {
                                        resultStaffMap.put("ResourceId", request.get("ResourceId").toString());
                                    }
                                    if (request.containsKey("PUCId") && request.get("PUCId") != null) {
                                        resultStaffMap.put("PUCId", request.get("PUCId").toString());
                                    }
                                    if (request.containsKey("SystemId") && request.get("SystemId") != null) {
                                        resultStaffMap.put("SystemId", request.get("SystemId").toString());
                                    }
                                    if (request.containsKey("Number") && request.get("Number") != null) {
                                        resultStaffMap.put("Number", request.get("Number").toString());
                                    }
                                    if (request.containsKey("IsAlarm") && request.get("IsAlarm") != null) {
                                        resultStaffMap.put("IsAlarm", request.get("IsAlarm").toString());
                                    }
                                    if (request.containsKey("ShortName") && request.get("ShortName") != null) {
                                        resultStaffMap.put("ShortName", request.get("ShortName").toString());
                                    } else {
                                        resultStaffMap.put("ShortName", "");
                                    }
                                    resultList.add(resultStaffMap);
                                }
                            }
                        }
                    }
                }
            }

            map.put("resourceInfo", resultList);
            logger.info("返回地图传输数据=================================" + JSON.toJSONString(map, SerializerFeature.WriteMapNullValue));
            return R.ok(map);
        } catch (Exception e) {
            logger.error("地图传输数据获取失败:" + e.getMessage());
            e.printStackTrace();
            return R.error(500, "");

        }
    }


    /**
     * 根据警情id查询到达现场的staffGuid
     * 根据反馈表的到达现场字段case_dispatch_status：JQZT004代表达到现场
     * @return
     */
    
    @RequestMapping(value = "/getArrivedStaffList", method = RequestMethod.POST)
    public R getArrivedStaffList(@RequestBody Map<String,Object> bodyMap) {

        //入参的para节点map
        List<String> arrivedStaffList = new ArrayList<>();
        try {
            Map<String, Object> inputParaMap = (Map<String, Object>) bodyMap.get("para");
            arrivedStaffList = vcstCaseFeedbackService.getArrivedStaffList(inputParaMap);
            return R.ok().put("arrivedStaffList", arrivedStaffList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getArrivedStaffList run error " + e.getMessage());
            return R.error(500, "");

        }
    }


}
