package com.commandcenter.controller.SmpController;

/**
 * @Auther: j18864
 * @Date: 2018/9/4 17:03
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.commandcenter.common.activemq.model.MqBodyData;
import com.commandcenter.common.activemq.model.MqHeaderData;
import com.commandcenter.common.activemq.service.MqSmpDataService;
import com.commandcenter.common.activemq.service.ProducerSmpPtp;
import com.commandcenter.common.utils.DateUtil;
import com.commandcenter.common.utils.R;
import com.commandcenter.service.smp.PucSystemInfoService;
import com.commandcenter.service.smp.SmpOrgInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

@RestController
@RequestMapping("/smp")
public class SmpController {
    /**
     * 点对点模式 生产者
     */
    @Autowired
    private ProducerSmpPtp queueSender;
    @Autowired
    private SmpOrgInfoService smpOrgInfoService;
    @Autowired
    private MqSmpDataService mqSmpDataService;

    @Autowired
    private PucSystemInfoService pucSystemInfoService;

    @Value("${jms.smp.base.queue}")
    private String smpBaseQueue;

    public MqBodyData getBaseMqBodyData() {
        MqBodyData mqBodyData = new MqBodyData();
        mqBodyData.setBegin_time("0001-01-01 00:00:00");
        mqBodyData.setEnd_time("2222-12-31 23:59:59");
        mqBodyData.setVersion("1.0");
        return mqBodyData;
    }

    public MqHeaderData MqHeaderData() {
        MqHeaderData mqHeaderData = new MqHeaderData();
        mqHeaderData.setSystem_id("MPA");
        mqHeaderData.setSubsystem_id("MPA");
        mqHeaderData.setSend_time(DateUtil.getToday("yyyy/MM/dd HH:mm:ss"));
        mqHeaderData.setMsgid(UUID.randomUUID().toString());
        mqHeaderData.setRequest("queue_smp_sharedata");
        mqHeaderData.setReques_type("1");
        mqHeaderData.setReponse(UUID.randomUUID().toString());
        mqHeaderData.setReponse_type("1");
        return mqHeaderData;
    }

    /**
     * 同步获取所有SMP数据
     */
    @ResponseBody
    @RequestMapping(value = "/syncAllSmpInfo")
    public R syncAllSmpInfo() {
        int org = (int) getOrganInfor().get("success");
        int staff = (int) getStaffInfor().get("success");
        int dic = (int) getDictInfor().get("success");
        int interphone = (int) getInterphoneInfor().get("success");
        int staffBind = (int) getSmpUserStaffBind().get("success");
        //int gpsInfo = (int)getVcgpsdataInfor().get("success");
        int userInfo = (int) getUserInfo().get("success");
        int languge = (int) getLanguage().get("success");
        int sysInfo = (int) getSystemInfo().get("success");
        int staffDevice = (int) getSmpStaffDevice().get("success");
        int smartApp = (int) getSmartAppInfo().get("success");
        int roleInfo = (int) getSmpRoleInfo().get("success");
        int dataAuthority = (int) getDataAuthority().get("success");
        int authInfo = (int) getAuthInfo().get("success");
        int ssiGroupInfo = (int) getSsiGroupInfo().get("success");
        getPucSystemInfo();
        if (org == 1 && staff == 1 && dic == 1 && interphone == 1 && staffBind == 1 && userInfo == 1 && languge == 1 && sysInfo == 1 && staffDevice == 1 && smartApp == 1 && roleInfo == 1 && dataAuthority == 1 && authInfo == 1 && ssiGroupInfo == 1) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @RequestMapping(value = "/getPucSystemInfor", method = RequestMethod.POST)
    public R getPucSystemInfo() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_pucsytem_infor");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        return R.ok();
    }

    @RequestMapping(value = "/getOrganInfor", method = RequestMethod.POST)
    public R getOrganInfor() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_organ_Infor");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int num = smpOrgInfoService.selectOrgCount();
        if (num >= 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @RequestMapping(value = "/getStaffInfor", method = RequestMethod.GET)
    public R getStaffInfor() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_staff_infor");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int staffNum = mqSmpDataService.selectStaffCount();
        if (staffNum >= 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getDictInfor", method = RequestMethod.GET)
    public R getDictInfor() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        //mqBodyData.setLangCode("ZH_CN");
        mqBodyData.setSystemNo("VCS,SSOP,MESS,ICC,AIA,SDMS,ZTSJCJ,SMP,PUBLIC");
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_dict_infor");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int dictInfoNum = mqSmpDataService.selectDictInfoCount();
        if (dictInfoNum >= 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * @param
     * @Author:jhx
     * @CreateDate:2018/9/6 11:34
     * @UpdateUser:jhx
     * @UpdateDate:2018/9/6 11:34
     * @UpdateRemark:The modified content
     */
    @ResponseBody
    @RequestMapping(value = "/getInterphoneInfor", method = RequestMethod.GET)
    public R getInterphoneInfor() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_interphone_infor");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int interphoneNum = mqSmpDataService.selectInterphoneCount();
        if (interphoneNum >= 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * @param
     * @Author:jhx
     * @CreateDate:2018/9/6 11:34
     * @UpdateUser:jhx
     * @UpdateDate:2018/9/6 11:34
     * @UpdateRemark:The modified content
     */
    @ResponseBody
    @RequestMapping(value = "/getSmpUserStaffBind", method = RequestMethod.GET)
    public R getSmpUserStaffBind() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_smp_user_staff_bind");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int staffUserNum = mqSmpDataService.selectStaffUserCount();
        if (staffUserNum >= 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * @param
     * @Author:jhx
     * @CreateDate:2018/9/6 11:34
     * @UpdateUser:jhx
     * @UpdateDate:2018/9/6 11:34
     * @UpdateRemark:The modified content
     */
    @ResponseBody
    @RequestMapping(value = "/getVcgpsdataInfor", method = RequestMethod.GET)
    public R getVcgpsdataInfor() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_vcgpsdata_infor");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int vcsGpsNum = mqSmpDataService.selectVcsGpsCount();
        if (vcsGpsNum >= 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * @param
     * @Author:jhx
     * @CreateDate:2018/9/6 11:34
     * @UpdateUser:jhx
     * @UpdateDate:2018/9/6 11:34
     * @UpdateRemark:The modified content
     */
    @ResponseBody
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public R getUserInfo() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_smp_user_info");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int userNum = mqSmpDataService.selectUserCount();
        if (userNum >= 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * @param
     * @Author:jhx
     * @CreateDate:2018/9/6 11:34
     * @UpdateUser:jhx
     * @UpdateDate:2018/9/6 11:34
     * @UpdateRemark:The modified content
     */
    @ResponseBody
    @RequestMapping(value = "/getDictValue", method = RequestMethod.GET)
    public R getDictValue() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_smp_user_info");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int dictValueNum = mqSmpDataService.selectdictValueCount();
        if (dictValueNum >= 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * @param
     * @Author:qmd
     * @CreateDate:2018/10/30 14.16
     * @UpdateUser:qmd
     * @UpdateDate:2018/10/30 14.16
     * @UpdateRemark:同步Language表
     */
    @ResponseBody
    @RequestMapping(value = "/getLanguage", method = RequestMethod.GET)
    public R getLanguage() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_dict_language_info");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int languageNum = mqSmpDataService.selectLanguageCount();
        if (languageNum >= 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * @param
     * @Author:qmd
     * @CreateDate:2018/10/30 14.16
     * @UpdateUser:qmd
     * @UpdateDate:2018/10/30 14.16
     * @UpdateRemark:同步系统表
     */
    @ResponseBody
    @RequestMapping(value = "/getSystemInfo", method = RequestMethod.GET)
    public R getSystemInfo() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_smp_system_info");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int systemNum = mqSmpDataService.selectSystemCount();
        if (systemNum >= 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * @param
     * @Author:zlb
     * @UpdateRemark:同步呼叫组信息
     */
    @ResponseBody
    @RequestMapping(value = "/getSsiGroupInfo", method = RequestMethod.GET)
    public R getSsiGroupInfo() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_pucssigroup_infor");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int ssiNum = mqSmpDataService.selectSsiGroupCount();
        if (ssiNum >= 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getSmpRoleInfo", method = RequestMethod.GET)
    public R getSmpRoleInfo() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_sys_role_info");
        mqBodyData.setSystemNo("MPA");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int systemNum = mqSmpDataService.selectSystemCount();
        if (systemNum >= 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getSmartAppInfo", method = RequestMethod.GET)
    public R getSmartAppInfo() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_smartapp_infor");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int smartNum = mqSmpDataService.selectSmartOneCount();
        if (smartNum >= 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getSmpStaffDevice")
    public R getSmpStaffDevice() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_staff_device_infor");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int staffDeviceNum = mqSmpDataService.staffDeviceNum();
        if (staffDeviceNum >= 0) {
            return R.ok();
        }
        return R.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/getRoleUser")
    public R getDataAuthority() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_user_role_info");
        mqBodyData.setSystemNo("MPA");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int staffDeviceNum = mqSmpDataService.roleUserNum();
        if (staffDeviceNum >= 0) {
            return R.ok();
        }
        return R.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/getSmpAuthInfo")
    public R getAuthInfo() {
        MqBodyData mqBodyData = getBaseMqBodyData();
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_auth_infor");
        mqBodyData.setSystemNo("MPA");
        HashMap<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header", mqHeaderData);
        requestMap.put("body", mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue, sendMes);
        int staffDeviceNum = mqSmpDataService.roleUserNum();
        if (staffDeviceNum >= 0) {
            return R.ok();
        }
        return R.ok();
    }


}
