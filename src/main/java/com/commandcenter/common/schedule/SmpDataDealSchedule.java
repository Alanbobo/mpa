package com.commandcenter.common.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.commandcenter.common.activemq.component.PublishToMControl;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.activemq.model.MqBodyData;
import com.commandcenter.common.activemq.model.MqHeaderData;
import com.commandcenter.common.activemq.service.MqSmpDataService;
import com.commandcenter.common.activemq.service.ProducerSmpPtp;
import com.commandcenter.common.runner.SmpApplicationRunner;
import com.commandcenter.common.utils.DateUtil;
import com.commandcenter.common.utils.R;
import com.commandcenter.service.smp.SmpOrgInfoService;
import com.commandcenter.service.smp.SmpVersionInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author q32756
 * @create 2018-12-26 9:03
 * @desc 同步smp后台进程，每天凌晨1点开始处理
 **/
@Component
public class SmpDataDealSchedule {
    protected final static Logger logger = LoggerFactory.getLogger(SmpApplicationRunner.class);
    private static final String CMD_MCONTROL_SYNCHDATA_NOTIFY = "queue_mcontrol_notice_info";
    @Autowired
    private PublishToMControl publishToMControl;
    @Autowired
    private ProducerSmpPtp queueSender;
    @Autowired
    private MqSmpDataService mqSmpDataService;
    @Autowired
    private SmpVersionInfoService smpVersionInfoService;

    @Autowired
    private SmpOrgInfoService smpOrgInfoService;
    @Value("${jms.smp.base.queue}")
    private String smpBaseQueue;
    @Scheduled(cron = "${schedule.smpdata.time}")
    public void smpDataScheduled(){
        getOrganInfo();
        getStaffInfo();
        getDictInfo();
        getInterphoneInfo();
        getSmpUserStaffBind();
        getLanguage();
        getSystemInfo();
        getSmartAppInfo();
        getSmpStaffDevice();
        getUserInfo();
        getSmpRoleInfo();
        getDataAuthority();
        getAuthInfo();
        getSsiGroupInfo();
        Map<String, Object> synchMap = new HashMap<>(1);
        synchMap.put("dataMessage", "ok");
        MQParamModel param = new MQParamModel(JSON.toJSONString(synchMap), CMD_MCONTROL_SYNCHDATA_NOTIFY);
        param.setEncryptFlag(false);
        logger.info("向MControl发送同步数据通知消息");
        publishToMControl.sendToMControl(param);
    }
    public void getOrganInfo() {
        logger.info("定时任务，开始同步组织机构数据..............");
        int maxOrgVersion = smpVersionInfoService.selectVersionByDataType("get_organ_Infor");
        MqBodyData mqBodyData= getBaseMqBodyData(maxOrgVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_organ_Infor");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("定时任务同步组织机构数据成功");

    }

    public void getStaffInfo() {
        logger.info("定时任务，开始同步警员数据..............");
        int maxStaffVersion = smpVersionInfoService.selectVersionByDataType("get_staff_infor");
        MqBodyData mqBodyData= getBaseMqBodyData(maxStaffVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_staff_infor");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("定时任务同步警员数据成功");
    }

    public void getDictInfo() {
        logger.info("定时任务，开始同步数据字典数据..............");
        int maxDictVersion = smpVersionInfoService.selectVersionByDataType("get_dict_infor");
        MqBodyData mqBodyData= getBaseMqBodyData(maxDictVersion);
        mqBodyData.setSystemNo("VCS,SSOP,MESS,ICC,AIA,SDMS,ZTSJCJ,SMP,PUBLIC");
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_dict_infor");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("定时任务同步数据字典数据成功");
    }

    public void getInterphoneInfo() {
        logger.info("定时任务，开始同步设备数据..............");
        int maxDeviceVersion = smpVersionInfoService.selectVersionByDataType("get_interphone_infor");
        MqBodyData mqBodyData= getBaseMqBodyData(maxDeviceVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_interphone_infor");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("定时任务同步设备数据成功");
    }

    public void getSmpUserStaffBind() {
        logger.info("定时任务，开始同步警员用户绑定数据..............");
        MqBodyData mqBodyData= getBaseMqBodyData(0);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_smp_user_staff_bind");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("定时任务同步警员用户绑定数据成功");
    }
    public void getLanguage() {
        logger.info("定时任务，开始同步语言数据..............");
        int maxLanguageVersion = smpVersionInfoService.selectVersionByDataType("get_dict_language_info");
        MqBodyData mqBodyData= getBaseMqBodyData(maxLanguageVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_dict_language_info");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("定时任务同步语言数据成功");
    }
    public void getSystemInfo() {
        logger.info("定时任务，开始同步系统数据..............");
        int maxSystemVersion = smpVersionInfoService.selectVersionByDataType("get_smp_system_info");
        MqBodyData mqBodyData= getBaseMqBodyData(maxSystemVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_smp_system_info");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("定时任务同步系统数据成功");
    }

    public void getSmartAppInfo() {
        logger.info("定时任务，开始同步SmartAPP数据..............");
        int maxSmartAppVersion = smpVersionInfoService.selectVersionByDataType("get_smartapp_infor");
        MqBodyData mqBodyData= getBaseMqBodyData(maxSmartAppVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_smartapp_infor");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("定时任务同步SmartAPP数据成功");
    }
    public void getSmpStaffDevice(){
        logger.info("定时任务，开始同步警员设备绑定数据..............");
        MqBodyData mqBodyData = getBaseMqBodyData(0);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_staff_device_infor");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("定时任务同步警员设备绑定数据成功");
    }

    public void getUserInfo() {
        logger.info("定时任务，开始同步用户数据..............");
        int maxUserVersion = smpVersionInfoService.selectVersionByDataType("get_smp_user_info");
        MqBodyData mqBodyData= getBaseMqBodyData(maxUserVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_smp_user_info");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("定时任务同步用户数据成功");
    }
    public void getSmpRoleInfo() {
        logger.info("定时任务，开始同步角色数据..............");
        MqBodyData mqBodyData= getBaseMqBodyData(0);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_sys_role_info");
        mqBodyData.setSystemNo("MPA");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue,sendMes);
        logger.info("定时任务同步角色数据成功");
    }


    public void getAuthInfo(){
        logger.info("定时任务，开始同步功能数据..............");
        MqBodyData mqBodyData = getBaseMqBodyData(0);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_auth_infor");
        mqBodyData.setSystemNo("MPA");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue,sendMes);
        logger.info("定时任务同步功能数据成功");
    }

    public void getDataAuthority(){
        logger.info("定时任务，开始同步角色权限数据..............");
        MqBodyData mqBodyData = getBaseMqBodyData(0);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_user_role_info");
        mqBodyData.setSystemNo("MPA");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue,sendMes);
        logger.info("定时任务同步角色权限数据成功");
    }


    public void getSsiGroupInfo(){
        logger.info("定时任务，开始同步puc呼叫组数据..............");
        MqBodyData mqBodyData = getBaseMqBodyData(0);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_pucssigroup_infor");
        mqBodyData.setSystemNo("MPA");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue,sendMes);
        logger.info("定时任务同步puc呼叫组数据成功");
    }


    public MqBodyData getBaseMqBodyData(int versionData){
        MqBodyData mqBodyData = new MqBodyData();
        mqBodyData.setBegin_time("0001-01-01 00:00:00");
        mqBodyData.setEnd_time("2222-12-31 23:59:59");
        mqBodyData.setVersion("1.0");
        mqBodyData.setVersion_data(versionData);
        return mqBodyData;
    }
    public MqHeaderData MqHeaderData(){
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
}
