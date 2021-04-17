package com.commandcenter.controller.deivceController;

import com.commandcenter.common.activemq.service.ProducerSmpPtp;
import com.commandcenter.common.utils.R;
import com.commandcenter.controller.baseController.BaseController;
import com.commandcenter.model.smp.SmpInterphoneInfo;
import com.commandcenter.model.smp.SmpVcGpsdata;
import com.commandcenter.service.appupdate.UpdateInfoService;
import com.commandcenter.service.smp.SmpInterphoneInfoService;
import com.commandcenter.service.smp.SmpVcGpsdataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Auther: j18864
 * @Date: 2018/9/7 10:42
 * @Description:
 */
@Controller
@RequestMapping("/comm")
public class SmpInterphoneInfoController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(SmpInterphoneInfoController.class);
    @Autowired
    SmpInterphoneInfoService smpInterphoneInfoService;
    @Autowired
    SmpVcGpsdataService smpVcGpsdataService;
    @Autowired
    UpdateInfoService updateInfoService;
    /**
     * 点对点模式 生产者
     */
    @Autowired
    private ProducerSmpPtp queueSender;
    @ResponseBody
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public Map<String, Object> getInterphoneInfo(@RequestBody Map<String,Object> body) throws Exception {
        Map<String, Object> paramMap = (Map<String, Object>) body.get("para");
        String staffGuid =  paramMap.get("staffGuid").toString();
       // List<SmpInterphoneInfo> smpInterphoneInfoList  = smpInterphoneInfoService.selectByStaffGuid(staffGuid);
        List<SmpInterphoneInfo> smpDeviceInfoList  = smpInterphoneInfoService.selectDevicesByStaffGuid(staffGuid);
        //List<SmpVcGpsdata> smpVcGpsdataList = smpVcGpsdataService.selectByStaffGuid(staffGuid);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> msg = new HashMap<>();
        map.put("Interphones",smpDeviceInfoList);
        //map.put("VcGpsdataList",smpVcGpsdataList);
        msg.put("info",map);
        return R.ok(msg);

    }

    /**
     * 更新设备信息
     * @return 是否成功
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/changeDeviceBind", method = RequestMethod.POST)
    public Map<String, Object> changeDeviceBind(@RequestBody Map<String,Object> body) throws Exception {
        Map<String, Object> paramMap = (Map<String, Object>) body.get("para");
        String staffGuid =  paramMap.get("staffGuid").toString();
        String deviceId =  paramMap.get("deviceId").toString();
        String deviceType =  paramMap.get("deviceType").toString();
//        String staffGuid = "FFBAA553-11F0-4906-A360-70A4E2B87790";
//        String deviceId =  "B312C553-E61F-422D-82B1-8AB1516A517C";
//        String deviceType =  "1";
        String message = getSMPChangeStr(staffGuid,deviceId,deviceType);
        Boolean result = queueSender.pubAndOnceReceReturn("queue_smp_rec_notify",message);
        if(result){
            return R.ok();
        }else{
            return R.error();
        }
    }

    /**
     * 更新客户端版本信息
     * @return 是否成功
     * @throws Exception
     * @Auther: q32756
     */
    @ResponseBody
    @RequestMapping(value = "/appUpdate", method = RequestMethod.POST)
    public Map<String, Object> appUpdate(@RequestBody Map<String,Object> body) throws Exception {
        Map<String, Object> paramMap = (Map<String, Object>) body.get("para");
        //String staffGuid =  paramMap.get("staffGuid").toString();
        String appVersionCode =  paramMap.get("appVersionCode").toString();
        //String appVersionName =  paramMap.get("appVersionName").toString();
        String serverVersionCode,serverVersionName,appSize,appUpdateInfo,appUrl;
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> selectInfo = updateInfoService.selectAllInfo();
        if(selectInfo!=null && selectInfo.size()!=0){
            serverVersionCode = (String) selectInfo.get("serverVersionCode");
            serverVersionName = (String) selectInfo.get("serverVersionName");
            appSize = (String) selectInfo.get("appSize");
            appUpdateInfo = (String) selectInfo.get("appUpdateInfo");
            appUrl = (String) selectInfo.get("appUrl");
            if(Integer.parseInt(serverVersionCode)>Integer.parseInt(appVersionCode)){
                map.put("appVersionCode",serverVersionCode);
                map.put("appVersionName",serverVersionName);
                map.put("appSize",appSize);
                map.put("appUpdateInfo",appUpdateInfo);
                map.put("appUrl",appUrl);
                return R.ok("有新的版本").put("info",map);
            }else {
                return R.ok("");
            }
        }else{
            return R.ok("");
        }
    }

    /**
     * 获取设备变更Json
     */
    public String getSMPChangeStr(String staffGuid,String deviceId, String deviceType){
        String cmdStr,requestStr;
        cmdStr = "staff_device_bind_notify";
        requestStr= "queue_smp_rec_notify";
        String message = "{\n" +
                "\t\"header\": {\n" +
                "\t\t\"system_id\": \"SMP\",\n" +
                "\t\t\"msgid\": \""+ UUID.randomUUID()+"\",\n" +
                "\t\t\"cmd\": \""+cmdStr+"\",\n" +
                "\t\t\"request\": \""+requestStr+"\",\n" +
                "\t\t\"request_type\":\"1\",\n" +
                "\t\t\"action\": \"bind\"\n" +
                "\t},\n" +
                "\t\"body\": {\n" +
                "\t\t\"dataList\": [{\n" +
                "\t\t\t\"deviceGuid\": \""+deviceId+"\",\n" +
                "\t\t\t\"staffGuid\": \""+staffGuid+"\"\n" +
                "\t\t}]\n" +
                "\t}\n" +
                "}";
        return message;
    }
}
