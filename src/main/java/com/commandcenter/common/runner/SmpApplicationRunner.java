package com.commandcenter.common.runner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.commandcenter.common.activemq.component.PublishToMControl;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.activemq.model.MqBodyData;
import com.commandcenter.common.activemq.model.MqHeaderData;
import com.commandcenter.common.activemq.model.MqRequestData;
import com.commandcenter.common.activemq.service.MqSmpDataService;
import com.commandcenter.common.activemq.service.ProducerSmpPtp;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.DateUtil;
import com.commandcenter.common.utils.GUID;
import com.commandcenter.service.smp.SmpOrgInfoService;
import com.commandcenter.service.smp.SmpVersionInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author q32756
 * @create 2019-06-06 16:00
 * @desc 程序启动时，自动同步SMP数据
 **/
@Component
@Order(1)
public class SmpApplicationRunner implements ApplicationRunner {
    protected final static Logger logger = LoggerFactory.getLogger(SmpApplicationRunner.class);
    private static final String CMD_MCONTROL_SYNCHDATA_NOTIFY = "queue_mcontrol_notice_info";
    @Autowired
    private ProducerSmpPtp queueSender;
    @Autowired
    private MqSmpDataService mqSmpDataService;
    @Autowired
    private SmpOrgInfoService smpOrgInfoService;
    @Autowired
    private SmpVersionInfoService smpVersionInfoService;

    @Autowired
    private PublishToMControl publishToMControl;
    @Value("${jms.smp.base.queue}")
    private String smpBaseQueue;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        StartRunnable startRunnable = new StartRunnable();
        Thread thread = new Thread(startRunnable);
        thread.start();
    }
    public MqHeaderData prepareMqHeader(String cmd){
        MqHeaderData mqHeaderData = new MqHeaderData();
        mqHeaderData.setSystem_id("mpa");
        mqHeaderData.setSubsystem_id("mpa");
        mqHeaderData.setMsgid(GUID.getGuid());
        mqHeaderData.setRelated_id(null);
        mqHeaderData.setReques_type(Constant.MQ_REQUEST_TYPE_QUEUE);
        mqHeaderData.setReponse(null);
        mqHeaderData.setReponse_type(null);
        mqHeaderData.setCmd(cmd);
        mqHeaderData.setRequest("mcontrol_synch_data");

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

    public void getOrganInfo() {
        logger.info("程序启动，开始同步组织机构数据..............");
        int maxOrgVersion = smpVersionInfoService.selectVersionByDataType("get_organ_Infor");
        MqBodyData mqBodyData= getBaseMqBodyData(maxOrgVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_organ_Infor");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("自启动同步组织机构数据成功");

    }



    public void getStaffInfo() {
        logger.info("程序启动，开始同步警员数据..............");
        int maxStaffVersion = smpVersionInfoService.selectVersionByDataType("get_staff_infor");
        MqBodyData mqBodyData= getBaseMqBodyData(maxStaffVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_staff_infor");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("自启动同步警员数据成功");
    }

    public void getDictInfo() {
        logger.info("程序启动，开始同步数据字典数据..............");
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
        logger.info("自启动同步数据字典数据成功");
    }

    public void getInterphoneInfo() {
        logger.info("程序启动，开始同步设备数据..............");
        int maxDeviceVersion = smpVersionInfoService.selectVersionByDataType("get_interphone_infor");
        MqBodyData mqBodyData= getBaseMqBodyData(maxDeviceVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_interphone_infor");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("自启动同步设备数据成功");
    }

    public void getSmpUserStaffBind() {
        logger.info("程序启动，开始同步警员用户绑定数据..............");
        MqBodyData mqBodyData= getBaseMqBodyData(0);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_smp_user_staff_bind");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("自启动同步警员用户绑定数据成功");
    }
    public void getLanguage() {
        logger.info("程序启动，开始同步语言数据..............");
        int maxLanguageVersion = smpVersionInfoService.selectVersionByDataType("get_dict_language_info");
        MqBodyData mqBodyData= getBaseMqBodyData(maxLanguageVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_dict_language_info");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("自启动同步语言数据成功");
    }
    public void getSystemInfo() {
        logger.info("程序启动，开始同步系统数据..............");
        int maxSystemVersion = smpVersionInfoService.selectVersionByDataType("get_smp_system_info");
        MqBodyData mqBodyData= getBaseMqBodyData(maxSystemVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_smp_system_info");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("自启动同步系统数据成功");
    }

    public void getSmartAppInfo() {
        logger.info("程序启动，开始同步SmartAPP数据..............");
        int maxSmartAppVersion = smpVersionInfoService.selectVersionByDataType("get_smartapp_infor");
        MqBodyData mqBodyData= getBaseMqBodyData(maxSmartAppVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_smartapp_infor");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("自启动同步SmartAPP数据成功");
    }
    public void getSmpStaffDevice(){
        logger.info("程序启动，开始同步警员设备绑定数据..............");
        MqBodyData mqBodyData = getBaseMqBodyData(0);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_staff_device_infor");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("自启动同步警员设备绑定数据成功");
    }

    public void getUserInfo() {
        logger.info("程序启动，开始同步用户数据..............");
        int maxUserVersion = smpVersionInfoService.selectVersionByDataType("get_smp_user_info");
        MqBodyData mqBodyData= getBaseMqBodyData(maxUserVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_smp_user_info");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("自启动同步用户数据成功");
    }
    public void getSmpRoleInfo() {
        logger.info("程序启动，开始同步角色数据..............");
        MqBodyData mqBodyData= getBaseMqBodyData(0);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_sys_role_info");
        mqBodyData.setSystemNo("MPA");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue,sendMes);
        logger.info("自启动同步角色数据成功");
    }

    public void getAuthInfo(){
        logger.info("程序启动，开始同步功能数据..............");
        MqBodyData mqBodyData = getBaseMqBodyData(0);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_auth_infor");
        mqBodyData.setSystemNo("MPA");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue,sendMes);
        logger.info("自启动同步功能数据成功");
    }

    public void getDataAuthority(){
        logger.info("程序启动，开始同步角色权限数据..............");
        MqBodyData mqBodyData = getBaseMqBodyData(0);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_user_role_info");
        mqBodyData.setSystemNo("MPA");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiRece(smpBaseQueue,sendMes);
        logger.info("自启动同步角色权限数据成功");
    }



    public void getSsiGroupInfo() {
        logger.info("程序启动，开始同步puc呼叫组数据..............");
        int maxOrgVersion = smpVersionInfoService.selectVersionByDataType("get_pucssigroup_infor");
        MqBodyData mqBodyData= getBaseMqBodyData(maxOrgVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_pucssigroup_infor");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("自启动同步呼叫组数据成功");

    }


    public void getPucSystemInfo() {
        logger.info("程序启动，开始同步PucSystem数据..............");
        int maxOrgVersion = smpVersionInfoService.selectVersionByDataType("get_pucsytem_infor");
        MqBodyData mqBodyData= getBaseMqBodyData(maxOrgVersion);
        MqHeaderData mqHeaderData = MqHeaderData();
        mqHeaderData.setCmd("get_pucsytem_infor");
        HashMap<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("header",mqHeaderData);
        requestMap.put("body",mqBodyData);
        String sendMes = JSON.toJSONString(requestMap, SerializerFeature.WriteMapNullValue);
        this.queueSender.pubAndMultiReceAdd(smpBaseQueue,sendMes);
        logger.info("自启动同步PucSystem数据成功");

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

    class StartRunnable implements Runnable{
        @Override
        public void run() {
            getSystemInfo();
            getOrganInfo();
            getStaffInfo();
            getDictInfo();
            getInterphoneInfo();
            getSmpUserStaffBind();
            getLanguage();
            getSmartAppInfo();
            getSmpStaffDevice();
            getUserInfo();
            getSmpRoleInfo();
            getDataAuthority();
            getAuthInfo();
            getSsiGroupInfo();
            getPucSystemInfo();
            Map<String, Object> synchMap = new HashMap<>(1);
            synchMap.put("dataMessage", "ok");
            MQParamModel param = new MQParamModel(JSON.toJSONString(synchMap), CMD_MCONTROL_SYNCHDATA_NOTIFY);
            param.setEncryptFlag(false);
            logger.info("向MControl发送同步数据通知消息");
            publishToMControl.sendToMControl(param);
        }
    }
}
