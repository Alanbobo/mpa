package com.commandcenter.common.activemq.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.activemq.component.PublishToAPP;
import com.commandcenter.common.activemq.component.PublishToVCS;
import com.commandcenter.common.activemq.model.*;
import com.commandcenter.common.activemq.service.MqSmpDataService;
import com.commandcenter.common.activemq.service.ReceiveSmpBaseDataService;
import com.commandcenter.common.utils.*;
import com.commandcenter.dao.smp.SmpStaffUserMapper;
import com.commandcenter.dao.smp.SmpUserInfoMapper;
import com.commandcenter.model.contacts.MpatContactsChange;
import com.commandcenter.model.contacts.StaffForApp;
import com.commandcenter.model.dictmodel.SmptLanguage;
import com.commandcenter.model.dictmodel.SmptSystemInfo;
import com.commandcenter.model.smp.*;
import com.commandcenter.model.smp.vo.LoginForVcs;
import com.commandcenter.model.smp.vo.StaffModel;
import com.commandcenter.modules.sys.service.SysUserTokenService;
import com.commandcenter.service.constacts.MpatContactsChangeService;
import com.commandcenter.service.smp.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author j18864
 * @date 2018-09-04
 * @describe 系统管理平台接收基础数据处理类
 */
@Service
public class ReceiveSmpBaseDataServiceImpl implements ReceiveSmpBaseDataService {
    @Autowired
    private MqSmpDataService mqSmpDataService;
    @Autowired
    private SmpVersionInfoService smpVersionInfoService;
    @Autowired
    private SmpVehicleDeviceService smpVehicleDeviceService;
    @Value("${system.notice.app.AccountMQ}")
    private String AccountMQ;
    @Value("${system.notice.app.constactsUpdate}")
    private String constactsUpdate;
    @Autowired
    private SmpUserInfoMapper smpUserInfoMapper;
    @Autowired
    private SmpAuthInfoService smpAuthInfoService;
    protected static Logger logger = LoggerFactory.getLogger(ReceiveSmpBaseDataServiceImpl.class);
    @Autowired
    private PublishToAPP publishToAPP;
    @Autowired
    private PublishToVCS publishToVCS;
    @Autowired
    private SmpUserService smpUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private SmpVehicleService smpVehicleService;
    @Autowired
    private SmpResourceService smpResourceService;
    @Autowired
    private PucSmartonegroupInfoService pucSmartonegroupInfoService;

    @Autowired
    private SmptSmartappInfoService smptSmartappInfoService;

    @Autowired
    private SmpStaffInfoService smpStaffInfoService;

    @Autowired
    private MpatContactsChangeService mpatContactsChangeService;

    @Autowired
    private SmpPostService smpPostService;

    @Autowired
    private PucSystemInfoService pucSystemInfoService;



    /**
     * 解析消息头接收SMP数据(全量数据+增量数据)　2018-09-04
     *
     * @param mqResponseData
     * @throws Exception
     * @author j18864
     */
    @Override
    public void recSmpBaseData(MqResponseData mqResponseData, int runTimeNum, String dataType, boolean isAddData) throws Exception {
        // 通过请求类型,找到具体实现逻辑
        String cmd = mqResponseData.getHeader().getCmd();
        String action = mqResponseData.getHeader().getAction();
        if (StringUtils.isBlank(action)) {
            //全量同步
            action = MqConstants.SmpNotifyCode.ADD.getValue();
        }
        switch (cmd) {
            //smp组织机构信息
            case MqConstants.MQ_CMD_CODE_SMP_ORG_INFO:
            case MqConstants.MQ_CMD_CODE_SMP_ORG_INFO_NOTIFY:
                getSmpOrgInfo(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //SmartOne账户信息
            case MqConstants.MQ_CMD_CODE_SMP_SMART_INFO_NOTIFY:
            case MqConstants.MQ_CMD_CODE_SMP_SMART_INFO:
                getSmartOneAppInfo(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //smp组信息
            case MqConstants.MQ_CMD_CODE_GROUP_INFO_NOTIFY:
            case MqConstants.MQ_CMD_CODE_SMP_GROUP_INFO:
                getGroupInfo(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //smp警员信息
            case MqConstants.MQ_CMD_CODE_SMP_STAFF_INFO:
            case MqConstants.MQ_CMD_CODE_SMP_STAFF_INFO_NOTIFY:
                getStaffInfor(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //smp设备信息
            case MqConstants.MQ_CMD_CODE_SMP_DEVICE_INFO:
            case MqConstants.MQ_CMD_CODE_SMP_INTER_NOTIFY:
                getInterphoneInfor(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //smp警员和设备信息
            case MqConstants.MQ_CMD_CODE_SMP_STAFF_DEIVCE_INFO:
            case MqConstants.MQ_CMD_CODE_SMP_STAFF_DEVICE_NOTIFY:
                getStaffDeviceInfo(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //smp SmartOneGroup信息
            case MqConstants.MQ_CMD_CODE_SMP_SMARTONEGROUP_INFO:
                getSmartOneGroupInfo(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //smp字典信息
            case MqConstants.MQ_CMD_CODE_SMP_DICT_INFO:
            case MqConstants.MQ_CMD_CODE_SMP_DICT_NOTIFY:
                getSmpDictInfor(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //smp语言信息
            case MqConstants.MQ_CMD_CODE_SMP_LANGUAGE_INFO:
            case MqConstants.MQ_CMD_CODE_SMP_LANGUAGE_NOTIFY:
                getSmpLanguageInfor(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //smp系统信息
            case MqConstants.MQ_CMD_CODE_SMP_SYSTEM_INFO:
                getSmpSystemInfor(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //smp用户和人绑定关系
            case MqConstants.MQ_CMD_CODE_SMP_USER_STAFF_BIND:
                getSmpUserStaffBind(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            case MqConstants.MQ_CMD_CODE_SMP_VCGPSDATA_INFOR:
            case MqConstants.MQ_CMD_CODE_SMP_VCGPSDATA_NOTIFY:
                getVcgpsdataInfor(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //smp用户信息
            case MqConstants.MQ_CMD_CODE_SMP_USER_INFO:
                getUserInfor(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            case MqConstants.MQ_CMD_CODE_SMP_USER_INFO_NOTIFY:
                sendSmpUserNotify(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;

            case MqConstants.MQ_CMD_CODE_SMP_ROLE_INFO_NOTIFY:
                getSmpRoleInfo(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            case MqConstants.MQ_CMD_CODE_SMP_ROLE_SYS_INFO:
                getSmpRoleDataInfo(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            case MqConstants.MQ_CMD_CODE_ROLE_BIND_AUTHORITY_NOTIFY:
                getRoleBindAuthority(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            case MqConstants.MQ_CMD_CODE_ROLE_USER_INFO:
                getRoleUserInfo(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;

            case MqConstants.MQ_CMD_CODE_SMP_AUTH_INFO:
                getAuthInfo(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            case MqConstants.MQ_CMD_CODE_AUTHORITY_INFO_NOTIFY:
                getAuthInfo(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //用户权限绑定变更通知
           /* case MqConstants.MQ_CMD_CODE_ROLE_BIND_USER_NOTIFY:
                getRoleBindUserNotify(mqResponseData,  runTimeNum,  dataType,  action, isAddData);
                break;
            case MqConstants.MQ_CMD_CODE_ROLE_UNBIND_USER_NOTIFY:
                getRoleUnbindUserNotify(mqResponseData,  runTimeNum,  dataType,  action, isAddData);
                break;*/
            //同步车辆信息
            case MqConstants.MQ_CMD_CODE_CAR_INFO_NOTIFY:
            case MqConstants.MQ_CMD_CODE_SMP_CAR_INFO:
                getCarInfo(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //同步车辆设备信息
            case MqConstants.MQ_CMD_CODE_CAR_DEVICE_NOTIFY:
            case MqConstants.MQ_CMD_CODE_SMP_CAR_DEVICE_INFO:
                getCarDeviceInfo(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //同步资源信息
            case MqConstants.MQ_CMD_CODE_RESOURCE_NOTIFY:
            case MqConstants.MQ_CMD_CODE_SMP_RESOURCES_INFO:
                getResourceInfo(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //同步岗位信息
            case MqConstants.MQ_CMD_CODE_POST_NOTIFY:
            case MqConstants.MQ_CMD_CODE_SMP_POST_INFO:
                getPostInfo(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            //同步PUC_SYSTEM信息
            case MqConstants.MQ_CMD_CODE_PUC_SYSTEM_INFO:
                getPucSystemInfo(mqResponseData, runTimeNum, dataType, action, isAddData);
                break;
            default:
                break;
        }
    }

    public void getRoleUnbindUserNotify(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //放在这里目的是 如果返回数据为空,不做处理
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }
        List<SmpRoleUser> smpRoleUserList = new ArrayList<>();
        List<SmpRoleUser> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpRoleUser.class);
        if (jsonList != null) {
            for (SmpRoleUser json : jsonList) {
                json.setGuid(UUID.randomUUID().toString());
                smpRoleUserList.add(json);
            }
        }

        if (!smpRoleUserList.isEmpty()) {
            mqSmpDataService.batchInsertSmpRoleUser(smpRoleUserList, "delete");
        }

    }

    /**
     * 用户角色解绑变更通知
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @param isAddData
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getRoleBindUserNotify(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //放在这里目的是 如果返回数据为空,不做处理
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }
        List<SmpRoleUser> smpRoleUserList = new ArrayList<>();
        List<SmpRoleUser> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpRoleUser.class);
        if (jsonList != null) {
            for (SmpRoleUser json : jsonList) {
                json.setGuid(UUID.randomUUID().toString());
                smpRoleUserList.add(json);
            }
        }

        if (!smpRoleUserList.isEmpty()) {
            mqSmpDataService.batchInsertSmpRoleUser(smpRoleUserList, action);
        }
    }

    public void getSmpOrgInfo(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            mqSmpDataService.deleteAllSmpOrgInfo();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);

        /**
         * 增改操作
         */
        List<SmpOrgInfoJsonObj> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpOrgInfoJsonObj.class);

        //接收到smp的组织机构变更通知，向APP发送消息
        if (999 == runTimeNum) {
            orgNotifyApp(jsonList, action);


        }
        List<SmpOrgInfo> smpOrgInfoList = new ArrayList<>();
        if (jsonList != null && !jsonList.isEmpty()) {
            for (SmpOrgInfoJsonObj json : jsonList) {
                SmpOrgInfo smpOrgInfo = new SmpOrgInfo();
                BeanUtils.copyProperties(json, smpOrgInfo);
                smpOrgInfoList.add(smpOrgInfo);
            }
        }

        //批量组织机构信息
        if (!smpOrgInfoList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertSmpOrgInfo(smpOrgInfoList, "addHalf");
            } else {
                mqSmpDataService.batchInsertSmpOrgInfo(smpOrgInfoList, action);
            }
        }
    }

    /**
     * 警员信息
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @param isAddData
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getStaffInfor(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            mqSmpDataService.deleteAllSmpStaffInfo();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);
        /**
         * 增改操作
         */
        List<SmpStaffInfoJsonObj> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpStaffInfoJsonObj.class);
        //接收到smp的人员变更通知，向APP发送消息
        if (999 == runTimeNum) {
//            logger.info("接收到smp的人员变更通知 消息体 = " + mqResponseData.getBody().toString());
//            logger.info("接收到smp的人员变更通知 消息指令CMD = " + mqResponseData.getHeader().getCmd());
//            logger.info("接收到smp的人员变更通知 消息指令ACTION = " + mqResponseData.getHeader().getAction());
            staffNotifyApp(jsonList, action);
        }
        List<SmpStaffInfo> smpStaffInfoList = new ArrayList<>();
        if (jsonList != null && !jsonList.isEmpty()) {
            for (SmpStaffInfoJsonObj json : jsonList) {
                SmpStaffInfo smpStaffInfo = new SmpStaffInfo();
                BeanUtils.copyProperties(json, smpStaffInfo);
                smpStaffInfoList.add(smpStaffInfo);
            }
        }

        //批量警员信息
        if (!smpStaffInfoList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertSmpStaffInfo(smpStaffInfoList, "addHalf");
            } else {
                mqSmpDataService.batchInsertSmpStaffInfo(smpStaffInfoList, action);
            }
        }
    }


    /**
     * 用户信息
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @param isAddData
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getUserInfor(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            mqSmpDataService.deleteAllUserInfor();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }


        addVersionData(mqResponseData, runTimeNum);

        /**
         * 增改操作
         */
        List<SmpUserInfoJsonObj> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpUserInfoJsonObj.class);
        List<SmpUserInfo> smpUserInfoInfoList = new ArrayList<>();
        if (jsonList != null && !jsonList.isEmpty()) {
            for (SmpUserInfoJsonObj json : jsonList) {
                SmpUserInfo smpUserInfo = new SmpUserInfo();
                BeanUtils.copyProperties(json, smpUserInfo);
                smpUserInfoInfoList.add(smpUserInfo);
            }
        }

        //批量警员信息
        if (!smpUserInfoInfoList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertSmpUserInfoInfo(smpUserInfoInfoList, "addHalf");
            } else {
                mqSmpDataService.batchInsertSmpUserInfoInfo(smpUserInfoInfoList, action);
            }

        }
    }

    /**
     * 用户信息
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @param isAddData
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void sendSmpUserNotify(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            mqSmpDataService.deleteAllUserInfor();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }
        if (MqConstants.SmpNotifyCode.ADD.getValue().equals(action) || MqConstants.SmpNotifyCode.UPDATE.getValue().equals(action) || MqConstants.SmpNotifyCode.DELETE.getValue().equals(action)) {
            getUserInfor(mqResponseData, runTimeNum, dataType, action, isAddData);
        }

        addVersionData(mqResponseData, runTimeNum);

        if (MqConstants.SmpNotifyCode.BIND.getValue().equals(action) || MqConstants.SmpNotifyCode.UNBIND.getValue().equals(action)) {
            /**
             * 批量绑定警员
             */
            List<SmpUserStaffBindSync> userRoleMiddleJsonObjs = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpUserStaffBindSync.class);
            List<SmpUserStaffBindSync> userStaffs = new ArrayList<>();
            if (userRoleMiddleJsonObjs != null && !userRoleMiddleJsonObjs.isEmpty()) {
                for (SmpUserStaffBindSync json : userRoleMiddleJsonObjs) {
                    SmpUserStaffBindSync userStaff = new SmpUserStaffBindSync();
                    userStaff.setGuid(GUID.getGuid());
                    userStaff.setUserGuid(json.getUserGuid());
                    userStaff.setStaffGuid(json.getStaffGuid());
                    userStaffs.add(userStaff);

                    if ("unbind".equals(action)) {
                        String userCode = smpUserInfoMapper.selectUserByGuid(userStaff.getUserGuid()).getUserCode();
//                    Map<String, Object> map = new HashMap<>(2);
                        String msg = "{\"code\":\"0\",\"msg\":\"已强制下线，账号已丢失\"}";

                        String tokenOld = (String) Constant.tokenMapIns.get(userCode);
                        try {
                            if (null != tokenOld) {
                                MQParamModel param = new MQParamModel(msg, AccountMQ);
                                param.setPersonalFlag(true);
                                param.setQueueFlag(false);

                                List<String> userNos = new ArrayList<>();
                                userNos.add(tokenOld);
                                userNos.add(tokenOld);
                                param.setUserNos(userNos);

                                //发送强制下线MQ
                                publishToAPP.sendToApp(param);

                                //将token失效
                                Constant.tokenMapIns.remove(userCode);
                                Constant.tokenMap.remove(tokenOld);
                                sysUserTokenService.logout(userStaff.getUserGuid());
                                //将用户下线信息传给后台可视化
                                List<StaffModel> staffList = smpUserService.selectStaffByUserCode(userCode);
                                LoginForVcs loginForVcs = new LoginForVcs();
                                loginForVcs.setDEVICE_ID(staffList.get(0).getGuid());
                                loginForVcs.setISONLINE("0");
                                publishToVCS.notifyLoginToVCS(loginForVcs);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    }

                }
            }

            //批量用户绑定解绑警员信息
            if (!userStaffs.isEmpty()) {

                mqSmpDataService.batchUserBindUnBindStaff(userStaffs, action);
            }
        } else if (MqConstants.SmpNotifyCode.BIND_ROLE.getValue().equals(action)) {
            /**
             * 批量绑定角色
             */
            List<UserRoleMiddleJsonObj> jsons = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), UserRoleMiddleJsonObj.class);
            List<SmpUserRoleSync> userRoles = new ArrayList<>();
            if (jsons != null && !jsons.isEmpty()) {
                for (UserRoleMiddleJsonObj json : jsons) {
                    SmpUserRoleSync userRole = new SmpUserRoleSync();
                    userRole.setGuid(GUID.getGuid());
                    userRole.setUserGuid(json.getUser_guid());
                    userRole.setRoleGuid(json.getRole_guid());
                    userRoles.add(userRole);
                }
            }

            //批量用户绑定解绑警员信息
            if (!userRoles.isEmpty()) {
                mqSmpDataService.batchUserBindUnBindRole(userRoles, action);
            }
        } else if (MqConstants.SmpNotifyCode.UNBIND_ROLE.getValue().equals(action)) {
            /**
             * 批量解绑角色
             */
            List<UserRoleMiddleJsonObj> jsons = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), UserRoleMiddleJsonObj.class);
            List<SmpUserRoleSync> userRoles = new ArrayList<>();
            if (jsons != null && !jsons.isEmpty()) {
                for (UserRoleMiddleJsonObj json : jsons) {
                    SmpUserRoleSync userRole = new SmpUserRoleSync();
                    userRole.setUserGuid(json.getUser_guid());
                    userRoles.add(userRole);
                }
            }

            //批量用户绑定解绑警员信息
            if (!userRoles.isEmpty()) {
                mqSmpDataService.batchUserBindUnBindRole(userRoles, action);
            }
        } else if (MqConstants.SmpNotifyCode.BIND_ORG.getValue().equals(action) || MqConstants.SmpNotifyCode.UNBIND_ORG.getValue().equals(action)) {
            /**
             * 绑定解绑组织机构
             */
            List<UserBindOrgJsonObj> jsons = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), UserBindOrgJsonObj.class);
            List<SmpUserInfo> userInfos = new ArrayList<>();
            if (jsons != null) {
                for (UserBindOrgJsonObj json : jsons) {
                    SmpUserInfo userInfo = new SmpUserInfo();
                    userInfo.setGuid(json.getGuid());
                    userInfo.setOrgGuid(json.getOrg_guid());
                    userInfos.add(userInfo);
                }
            }

            //批量处理用户绑定解绑组织机构信息
            if (!userInfos.isEmpty()) {
                if (isAddData) {
                    mqSmpDataService.batchUserBindUnBindOrg(userInfos, "addHalf");
                } else {
                    mqSmpDataService.batchUserBindUnBindOrg(userInfos, action);
                }
            }
        }

    }

    public void getSmpDictInfor(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            mqSmpDataService.deleteAllSmpDictInfo();
            mqSmpDataService.deleteAllSmpDictValue();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);

        /**
         * 增改操作
         */
        List<SmpDictInfoJsonObj> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpDictInfoJsonObj.class);
        List<SmpDictInfo> smpDictInfoList = new ArrayList<>();
        List<SmpDictValue> smpDictValueList = new ArrayList<>();
        if (jsonList != null && !jsonList.isEmpty()) {
            for (SmpDictInfoJsonObj json : jsonList) {
                SmpDictInfo smpDictInfo = new SmpDictInfo();
                BeanUtils.copyProperties(json, smpDictInfo);
                List<SmpDictValueJsonObj> dict_value = json.getDict_value();
                for (SmpDictValueJsonObj dictvalJson : dict_value) {
                    SmpDictValue smpDictValue = new SmpDictValue();
                    BeanUtils.copyProperties(dictvalJson, smpDictValue);
                    smpDictValueList.add(smpDictValue);
                }
                smpDictInfoList.add(smpDictInfo);
            }
        }

        //批量字典信息
        if (!smpDictInfoList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertSmpDictInfo(smpDictInfoList, smpDictValueList, "addHalf");
            } else {
                mqSmpDataService.batchInsertSmpDictInfo(smpDictInfoList, smpDictValueList, action);
            }
        }
    }

    /**
     * 设备信息
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @param isAddData
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getInterphoneInfor(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            mqSmpDataService.deleteAllSmpInterphoneInfor();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);

        /**
         * 增改操作
         */
        List<SmpInterphoneInfoJsonObj> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpInterphoneInfoJsonObj.class);
        List<SmpInterphoneInfo> smpInterphoneInfoList = new ArrayList<>();
        if (jsonList != null) {
            if (999 == runTimeNum) {
                deviceNotifyApp(jsonList, action);

            }

            for (SmpInterphoneInfoJsonObj json : jsonList) {
                SmpInterphoneInfo smpInterphoneInfo = new SmpInterphoneInfo();
                BeanUtils.copyProperties(json, smpInterphoneInfo);
                smpInterphoneInfo.setGuid(json.getInterphoneGuid());
                smpInterphoneInfoList.add(smpInterphoneInfo);
            }
        }
        //接收到smp的设备变更通知，向APP发送消息

        //批量警员信息
        if (!smpInterphoneInfoList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertSmpInterphoneInfor(smpInterphoneInfoList, "addHalf");
            } else {
                mqSmpDataService.batchInsertSmpInterphoneInfor(smpInterphoneInfoList, action);
            }
        }
    }

    /**
     * 批量修改车辆信息
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @param isAddData
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getCarInfo(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            smpVehicleService.deleteAllSmpVehicleInfo();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);

        /**
         * 增改操作
         */
        List<SmpCarInfo> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpCarInfo.class);
        List<SmpCarInfo> smpCarInfoList = new ArrayList<>();
        if (jsonList != null && !jsonList.isEmpty()) {
            for (SmpCarInfo json : jsonList) {
                smpCarInfoList.add(json);
            }
        }

        //批量警员信息
        if (!smpCarInfoList.isEmpty()) {
            if (isAddData) {
                smpVehicleService.batchInsertSmpVehicleInfo(smpCarInfoList, "addHalf");
            } else {
                smpVehicleService.batchInsertSmpVehicleInfo(smpCarInfoList, action);
            }
        }
    }


    /**
     * 批量修改资源信息
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @param isAddData
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getResourceInfo(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            smpResourceService.deleteAllSmpResourceInfo();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);

        /**
         * 增改操作
         */
        List<SmpResources> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpResources.class);

        //批量资源信息
        if (jsonList != null && !jsonList.isEmpty()) {
            if (isAddData) {
                smpResourceService.batchInsertSmpResourceInfo(jsonList, "addHalf");
            } else {
                smpResourceService.batchInsertSmpResourceInfo(jsonList, action);
            }
        }
    }

    /**
     * 批量修改岗位信息
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @param isAddData
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getPostInfo(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            smpResourceService.deleteAllSmpResourceInfo();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);

        /**
         * 增改操作
         */
        List<PostInfo> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), PostInfo.class);

        //批量资源信息
        if (jsonList != null && !jsonList.isEmpty()) {
            if (isAddData) {
                smpPostService.batchInsertSmpPostInfo(jsonList, "addHalf");
            } else {
                smpPostService.batchInsertSmpPostInfo(jsonList, action);
            }
        }
    }

    /**
     * 批量修改puc系统信息
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @param isAddData
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getPucSystemInfo(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            pucSystemInfoService.deleteAllPucSystemInfo();


        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);

        /**
         * 增改操作
         */
        List<PucSystemInfo> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), PucSystemInfo.class);

        //批量资源信息
        if (jsonList != null && !jsonList.isEmpty()) {
            if (isAddData) {
                pucSystemInfoService.batchInsertPucSystemInfo(jsonList, "addHalf");
            } else {
                pucSystemInfoService.batchInsertPucSystemInfo(jsonList, action);
            }
        }
    }

    /**
     * 批量修改SmartOneGroup信息
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @param isAddData
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getSmartOneGroupInfo(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            pucSmartonegroupInfoService.deleteAllSmartoneGroup();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);

        /**
         * 增改操作
         */
        List<PucSmartonegroupInfo> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), PucSmartonegroupInfo.class);

        //批量资源信息
        if (jsonList != null && !jsonList.isEmpty()) {
            if (isAddData) {
                pucSmartonegroupInfoService.batchInsertSmartoneGroupInfo(jsonList, "addHalf");
            } else {
                pucSmartonegroupInfoService.batchInsertSmartoneGroupInfo(jsonList, action);
            }
        }
    }

    public void getSmpUserStaffBind(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            mqSmpDataService.deleteAllUserStaffBind();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }
        List<UserStaffJsonObj> userStaffJsonObjs = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), UserStaffJsonObj.class);
        List<SmpUserStaffBindSync> userStaffs = new ArrayList<>();
        if (userStaffJsonObjs != null) {
            for (UserStaffJsonObj json : userStaffJsonObjs) {
                SmpUserStaffBindSync userStaff = new SmpUserStaffBindSync();
                //从SMP收到两个数据一个为staffGuid，一个为GUID，guid实际为userGUID
                userStaff.setGuid(json.getGuid());
                userStaff.setStaffGuid(json.getStaffGuid());
                userStaff.setUserGuid(json.getGuid());
                userStaffs.add(userStaff);
            }
        }

        //批量插入用户警员绑定信息
        if (!userStaffs.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.deleteAllUserStaffBind();
                mqSmpDataService.batchInsertUserStaffBindData(userStaffs, "addHalf");
            } else {
                mqSmpDataService.batchInsertUserStaffBindData(userStaffs, action);
            }
        }
    }

    /**
     * 批量添加车辆设备绑定关系数据
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @param isAddData
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getCarDeviceInfo(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            smpVehicleDeviceService.deleteAllVehicleDeviceBind();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);

        List<SmpVehicleDevice> smpVehicleDevices = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpVehicleDevice.class);
        List<SmpVehicleDevice> smpVehicleDeviceList = new ArrayList<>();
        if (smpVehicleDevices != null) {
            for (SmpVehicleDevice json : smpVehicleDevices) {
                smpVehicleDeviceList.add(json);
            }
        }

        //批量插入用户警员绑定信息
        if (!smpVehicleDeviceList.isEmpty()) {
            if (isAddData) {
                smpVehicleDeviceService.batchInsertVehicleDeviceBindData(smpVehicleDeviceList, "addHalf");
            } else {
                smpVehicleDeviceService.batchInsertVehicleDeviceBindData(smpVehicleDeviceList, action);
            }
        }
    }

    public void getVcgpsdataInfor(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            mqSmpDataService.deleteAllVcgpsdataInfor();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);

        List<SmpVcGpsdata> smpVcGpsdataList = new ArrayList<>();
        if (MqConstants.SmpNotifyCode.UNBIND.getValue().equals(action)) {
            List<String> Guids = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), String.class);
            if (Guids != null && !Guids.isEmpty()) {
                for (String guid : Guids) {
                    SmpVcGpsdata smpVcGpsdata = new SmpVcGpsdata();
                    smpVcGpsdata.setGuid(guid);
                    smpVcGpsdata.setStaffGuid("");
                    smpVcGpsdataList.add(smpVcGpsdata);
                }
            }
        } else {
            List<SmpVcGpsdataJsonObj> smpVcGpsdataJsonObjs = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpVcGpsdataJsonObj.class);
            if (smpVcGpsdataJsonObjs != null) {
                for (SmpVcGpsdataJsonObj json : smpVcGpsdataJsonObjs) {
                    SmpVcGpsdata smpVcGpsdata = new SmpVcGpsdata();
                    BeanUtils.copyProperties(json, smpVcGpsdata);
                    if (json.getGuid() == null || "".equals(json.getGuid())) {
                        smpVcGpsdata.setGuid(json.getDeviceGuid());
                    }
                    smpVcGpsdataList.add(smpVcGpsdata);
                }
            }

        }
        //批量插入用户警员绑定信息
        if (!smpVcGpsdataList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertVcgpsdataInfor(smpVcGpsdataList, "addHalf");
            } else {
                mqSmpDataService.batchInsertVcgpsdataInfor(smpVcGpsdataList, action);
            }
        }
    }

    /**
     * 批量修改数据字典语言信息
     *
     * @param mqResponseData 接收到MQ的数据
     * @param runTimeNum
     * @param dataType
     * @param action         动作
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getSmpLanguageInfor(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            mqSmpDataService.deleteAllLanguageInfo();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);

        List<SmptLanguage> smptLanguageList = new ArrayList<>();
        List<SmptLanguage> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmptLanguage.class);
        if (jsonList != null) {
            for (SmptLanguage json : jsonList) {
                SmptLanguage smptLanguage = new SmptLanguage();
                BeanUtils.copyProperties(json, smptLanguage);
                smptLanguage.setGuid(json.getGuid());
                smptLanguageList.add(smptLanguage);
            }
        }

        //批量语言信息
        if (!smptLanguageList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertSmpLanguageInfor(smptLanguageList, "addHalf");
            } else {
                mqSmpDataService.batchInsertSmpLanguageInfor(smptLanguageList, action);
            }
        }
    }

    /**
     * 批量修改系统信息
     *
     * @param mqResponseData 接收到MQ的数据
     * @param runTimeNum
     * @param dataType
     * @param action         动作
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getSmpSystemInfor(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            mqSmpDataService.deleteAllSystemInfo();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);

        List<SmptSystemInfo> smptSystemInfoList = new ArrayList<>();
        List<SmptSystemInfo> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmptSystemInfo.class);
        if (jsonList != null) {
            for (SmptSystemInfo json : jsonList) {
                SmptSystemInfo smptSystemInfo = new SmptSystemInfo();
                BeanUtils.copyProperties(json, smptSystemInfo);
                smptSystemInfoList.add(smptSystemInfo);
            }
        }

        //批量语言信息
        if (!smptSystemInfoList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertSmpSystemInfor(smptSystemInfoList, "addHalf");
            } else {
                mqSmpDataService.batchInsertSmpSystemInfor(smptSystemInfoList, action);
            }
        }
    }

    /**
     * 批量修改组信息
     *
     * @param mqResponseData 接收到MQ的数据
     * @param runTimeNum
     * @param dataType
     * @param action         动作
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getGroupInfo(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        //第一次清表
        if (runTimeNum == 0) {
            mqSmpDataService.deleteAllGroupInfo();
        }
        //放在这里目的是 如果返回数据为空,那么该清表还是清表
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);

        List<SmpSsiGroupInfo> smpSsiGroupInfoList = new ArrayList<>();
        List<SmpSsiGroupInfo> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpSsiGroupInfo.class);
        if (jsonList != null) {
            //接收到smp的对讲机组变更通知，向APP发送消息
            if (999 == runTimeNum) {
                groupNotifyApp(jsonList, action);
            }

            for (SmpSsiGroupInfo json : jsonList) {
                SmpSsiGroupInfo smpSsiGroupInfo = new SmpSsiGroupInfo();
                BeanUtils.copyProperties(json, smpSsiGroupInfo);
                smpSsiGroupInfoList.add(smpSsiGroupInfo);
            }
        }


        //批量语言信息
        if (!smpSsiGroupInfoList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertSmpGroupInfor(smpSsiGroupInfoList, "addHalf");
            } else {
                mqSmpDataService.batchInsertSmpGroupInfor(smpSsiGroupInfoList, action);
            }
        }
    }

    /**
     * 批量修改puc信息
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getSmartOneAppInfo(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        if (runTimeNum == 0) {
            mqSmpDataService.deleteAllSmartAppInfo();
        }
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);

        List<SmptSmartappInfo> smptSmartappInfoList = new ArrayList<>();
        List<SmptSmartappInfo> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmptSmartappInfo.class);
        if (jsonList != null) {
            if (999 == runTimeNum) {
                smartNotifyApp(jsonList, action);
            }

            for (SmptSmartappInfo json : jsonList) {
                SmptSmartappInfo smptSmartappInfo = new SmptSmartappInfo();
                BeanUtils.copyProperties(json, smptSmartappInfo);
                smptSmartappInfoList.add(smptSmartappInfo);
            }
        }

        //批量语言信息
        if (!smptSmartappInfoList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertSmartappInfo(smptSmartappInfoList, "addHalf");
            } else {
                mqSmpDataService.batchInsertSmartappInfo(smptSmartappInfoList, action);
            }
        }

    }

    /**
     * 批量修改人员设备
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getStaffDeviceInfo(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        if (runTimeNum == 0) {
            mqSmpDataService.deleteAllStaffDeviceInfo();
        }
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }
        List<SmpStaffDeviceInfo> smpStaffDeviceInfoList = new ArrayList<>();
        List<SmpStaffDeviceInfo> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpStaffDeviceInfo.class);
        if (jsonList != null) {
            if (999 == runTimeNum) {
                staffDeviceNotifyApp(jsonList, action);
            }
            for (SmpStaffDeviceInfo json : jsonList) {
                SmpStaffDeviceInfo smpStaffDeviceInfo = new SmpStaffDeviceInfo();
                BeanUtils.copyProperties(json, smpStaffDeviceInfo);
                smpStaffDeviceInfoList.add(smpStaffDeviceInfo);
            }
        }

        //批量语言信息
        if (!smpStaffDeviceInfoList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertStaffDeviceInfo(smpStaffDeviceInfoList, "addHalf");
            } else {
                mqSmpDataService.batchInsertStaffDeviceInfo(smpStaffDeviceInfoList, action);
            }
        }

    }

    /**
     * 批量修改角色
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getSmpRoleInfo(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) {
        if (runTimeNum == 0 || isAddData) {
            mqSmpDataService.deleteAllSmpRoleInfo();
        }
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        addVersionData(mqResponseData, runTimeNum);

        List<SmpRoleInfo> smpRoleInfoList = new ArrayList<>();
        List<SmpRoleInfo> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpRoleInfo.class);
        if (jsonList != null) {
            for (SmpRoleInfo json : jsonList) {
                SmpRoleInfo smpRoleInfo = new SmpRoleInfo();
                BeanUtils.copyProperties(json, smpRoleInfo);
                smpRoleInfoList.add(smpRoleInfo);
            }
        }

        //批量语言信息
        if (!smpRoleInfoList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertSmpRoleInfo(smpRoleInfoList, "addHalf");
            } else {
                mqSmpDataService.batchInsertSmpRoleInfo(smpRoleInfoList, action);
            }
        }
    }

    /**
     * 判断smp同步数据中，是否包含了version数据，如果包含则更新到本地版本表中
     *
     * @param mqResponseData
     * @param runTimeNum
     */
    public void addVersionData(MqResponseData mqResponseData, int runTimeNum) {
        if (runTimeNum != 999 && mqResponseData.getBody().get("version_data") != null && !"".equals(mqResponseData.getBody().get("version_data"))) {
            int versionData = (int) mqResponseData.getBody().get("version_data");
            Date updateTime = new Date();
            String type = mqResponseData.getHeader().getCmd();
            SmpVersionInfo smpVersionInfo = new SmpVersionInfo();
            smpVersionInfo.setDataType(type);
            smpVersionInfo.setGuid(UUID.randomUUID().toString());
            smpVersionInfo.setUpdateTime(updateTime);
            smpVersionInfo.setVersion(versionData);
            List<SmpVersionInfo> smpVersionInfos = smpVersionInfoService.selectVersionInfo(type);
            if (smpVersionInfos == null || smpVersionInfos.size() == 0) {
                smpVersionInfoService.insertVersionInfo(smpVersionInfo);
            } else {
                smpVersionInfoService.updateVersionInfo(smpVersionInfo);
            }
        }
    }

    /**
     * 变更通知批量修改角色权限
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getRoleBindAuthority(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        if (runTimeNum == 0 || isAddData) {
            mqSmpDataService.deleteAllDataAuthority();
        }
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        List<SmpDataAuthority> smpDataAuthorityList = new ArrayList<>();
        List<SmpRoleFunctionInfo> smpRoleFunctionInfoList = new ArrayList<>();
        List<SmpDataAuthorityJson> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpDataAuthorityJson.class);
        if (jsonList != null) {
            for (SmpDataAuthorityJson json : jsonList) {
                //因smp如何操作都将所有权限数据一次性传过来，所以每次收到消息后先删除，后全量增加
                mqSmpDataService.deleteAuthorityByroleGuid(json.getRoleGuid());
                if (json.getDataAuthorityArray() != null && json.getDataAuthorityArray().size() != 0) {
                    for (String orgGuid : json.getDataAuthorityArray()) {
                        SmpDataAuthority smpDataAuthority = new SmpDataAuthority();
                        smpDataAuthority.setGuid(UUID.randomUUID().toString());
                        smpDataAuthority.setRoleGuid(json.getRoleGuid());
                        smpDataAuthority.setOrgGuid(orgGuid);
                        smpDataAuthorityList.add(smpDataAuthority);
                    }
                }
                if (json.getFunctionGuidArray() != null && json.getFunctionGuidArray().size() != 0) {
                    for (String functionGuid : json.getFunctionGuidArray()) {
                        SmpRoleFunctionInfo smpRoleFunctionInfo = new SmpRoleFunctionInfo();
                        smpRoleFunctionInfo.setFunctionGuid(functionGuid);
                        smpRoleFunctionInfo.setGuid(UUID.randomUUID().toString());
                        smpRoleFunctionInfo.setRoleGuid(json.getRoleGuid());
                        smpRoleFunctionInfoList.add(smpRoleFunctionInfo);
                    }
                }
            }
        }

        //批量语言信息
        if (!smpDataAuthorityList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertDataAuthority(smpDataAuthorityList, "addHalf");
            } else {
                mqSmpDataService.batchInsertDataAuthority(smpDataAuthorityList, action);
            }
        }
        if (!smpRoleFunctionInfoList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertRoleFunction(smpRoleFunctionInfoList, "addHalf");
            } else {
                mqSmpDataService.batchInsertRoleFunction(smpRoleFunctionInfoList, action);
            }
        }

    }

    /**
     * 请求批量修改角色及角色用户
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getRoleUserInfo(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        if (runTimeNum == 0 || isAddData) {
            mqSmpDataService.deleteAllRoleUserInfo();
        }
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        List<SmpRoleUser> smpRoleUserList = new ArrayList<>();
        //smp返回角色信息以及角色权限数据绑定信息
        List<SmpUserRoleMqJson> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpUserRoleMqJson.class);
        if (jsonList != null) {
            for (SmpUserRoleMqJson json : jsonList) {
                //将返回的角色信息取出
                for (Map roleGuid : json.getRoleInfo()) {
                    SmpRoleUser smpRoleUser = new SmpRoleUser();
                    smpRoleUser.setRoleGuid((String) roleGuid.get("guid"));
                    smpRoleUser.setUserGuid(json.getUserGuid());
                    smpRoleUser.setGuid(UUID.randomUUID().toString());
                    smpRoleUserList.add(smpRoleUser);
                }
            }
        }

        //批量语言信息
        if (!smpRoleUserList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertSmpRoleUser(smpRoleUserList, "addHalf");
            } else {
                mqSmpDataService.batchInsertSmpRoleUser(smpRoleUserList, action);
            }
        }

    }

    /**
     * 请求批量修改功能信息
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getAuthInfo(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        if (runTimeNum == 0 || isAddData) {
            smpAuthInfoService.deleteAllAuthInfo();
        }
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        List<SmpAuthInfo> smpAuthInfoList = new ArrayList<>();
        //smp返回角色信息以及角色权限数据绑定信息
        List<SmpAuthInfo> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpAuthInfo.class);
        if (jsonList != null) {
            for (SmpAuthInfo json : jsonList) {
                //将返回的角色信息取出
                smpAuthInfoList.add(json);
            }
        }

        //批量语言信息
        if (!smpAuthInfoList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertSmpAuthInfo(smpAuthInfoList, "addHalf");
            } else {
                mqSmpDataService.batchInsertSmpAuthInfo(smpAuthInfoList, action);
            }
        }

    }

    /**
     * 请求用户权限绑定批量修改
     *
     * @param mqResponseData
     * @param runTimeNum
     * @param dataType
     * @param action
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getSmpRoleDataInfo(MqResponseData mqResponseData, int runTimeNum, String dataType, String action, boolean isAddData) throws InvocationTargetException, IllegalAccessException {
        if (runTimeNum == 0 || isAddData) {
            mqSmpDataService.deleteAllSmpRoleInfo();
            mqSmpDataService.deleteAllDataAuthority();
            mqSmpDataService.deleteAllRoleFunctionInfo();
        }
        if (mqResponseData.getBody().get(dataType) == null) {
            return;
        }

        List<SmpRoleInfo> smpRoleInfoList = new ArrayList<>();
        List<SmpDataAuthority> smpDataAuthorityList = new ArrayList<>();
        List<SmpRoleFunctionInfo> smpRoleFunctionInfoList = new ArrayList<>();
        //smp返回角色信息以及角色权限数据绑定信息
        List<SmpRoleInfoMqJson> jsonList = JSONObject.parseArray(mqResponseData.getBody().get(dataType).toString(), SmpRoleInfoMqJson.class);
        for (SmpRoleInfoMqJson json : jsonList) {
            //将返回的角色信息取出
            SmpRoleInfo smpRoleInfo = new SmpRoleInfo();
            smpRoleInfo.setGuid(json.getGuid());
            smpRoleInfo.setCode(json.getCode());
            smpRoleInfo.setName(json.getName());
            smpRoleInfo.setIsvalid(json.getIsvalid());
            smpRoleInfo.setEnableFlag(json.getEnableFlag());
            smpRoleInfo.setRoleAttr(json.getRoleAttr());
            smpRoleInfoList.add(smpRoleInfo);
            //将角色权限数据绑定信息取出
            for (Map dataAuthority : json.getDataInfoList()) {
                SmpDataAuthority smpDataAuthority = new SmpDataAuthority();
                smpDataAuthority.setGuid(UUID.randomUUID().toString());
                smpDataAuthority.setRoleGuid(json.getGuid());
                smpDataAuthority.setOrgGuid((String) dataAuthority.get("guid"));
                smpDataAuthorityList.add(smpDataAuthority);
            }
            for (Map functionInfo : json.getFuncInfoList()) {
                SmpRoleFunctionInfo smpRoleFunctionInfo = new SmpRoleFunctionInfo();
                smpRoleFunctionInfo.setCode(functionInfo.get("code").toString());
                smpRoleFunctionInfo.setGuid(UUID.randomUUID().toString());
                smpRoleFunctionInfo.setRoleGuid(json.getGuid());
                smpRoleFunctionInfo.setFunctionGuid(functionInfo.get("guid").toString());
                smpRoleFunctionInfoList.add(smpRoleFunctionInfo);
            }
        }
        //批量语言信息
        if (!smpRoleInfoList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertSmpRoleInfo(smpRoleInfoList, "addHalf");
            } else {
                mqSmpDataService.batchInsertSmpRoleInfo(smpRoleInfoList, action);
            }
        }
        if (!smpDataAuthorityList.isEmpty()) {
            if (isAddData) {
                mqSmpDataService.batchInsertDataAuthority(smpDataAuthorityList, "addHalf");
            } else {
                mqSmpDataService.batchInsertDataAuthority(smpDataAuthorityList, action);
            }
        }
        if (!smpRoleFunctionInfoList.isEmpty()) {
            mqSmpDataService.batchInsertRoleFunction(smpRoleFunctionInfoList, action);
        }

    }

    public void publishConstactChangeToApp(Map changeMap, String action) {

        //接收到smp的人员变更通知，向APP发送消息
        if ("update".equals(action) || "delete".equals(action) || "add".equals(action)) {
            if ("update".equals(action)) {
                changeMap.put("action", "update");
            } else if ("add".equals(action)) {
                changeMap.put("action", "add");
            } else {
                changeMap.put("action", "delete");
            }
            MQParamModel param = new MQParamModel(JSON.toJSONString(changeMap), constactsUpdate);
            param.setPersonalFlag(false);
            param.setEncryptFlag(true);
            publishToAPP.sendToApp(param);

            if ("delete".equals(action)) {
                MpatContactsChange mpatContactsChange = new MpatContactsChange();
                mpatContactsChange.setGuid(UUID.randomUUID().toString());
                mpatContactsChange.setAction((String) changeMap.get("action"));
                mpatContactsChange.setDataType((Integer) changeMap.get("dataType"));
                // SMP 发送删除通知协议时，协议中没有 version 字段，所有此处设置 为 0 ;
//                mpatContactsChange.setVersion(0);
                mpatContactsChange.setVersion((Integer) changeMap.get("version"));
                mpatContactsChange.setJsonData(JSON.toJSONString(changeMap));
                mpatContactsChangeService.insertNonEmptyMpatContactsChange(mpatContactsChange);
            }
        }
    }


    public void smartNotifyApp(List<SmptSmartappInfo> jsonList, String action) {

        for (int i = 0; i < jsonList.size(); i++) {
            Map smartChangeMap = new HashMap<String, Object>();
            smartChangeMap.put("guid", jsonList.get(i).getGuid());
            smartChangeMap.put("dispatcherName", jsonList.get(i).getDispatcherName());
            smartChangeMap.put("dispatcherAccount", jsonList.get(i).getDispatcherAccount());
            smartChangeMap.put("orgIdentifier", jsonList.get(i).getOrgIdentifier());
            smartChangeMap.put("pucId", jsonList.get(i).getPucId());
            smartChangeMap.put("staffGuid", jsonList.get(i).getStaffGuid());
            smartChangeMap.put("dispatcherPwd", jsonList.get(i).getDispatcherPwd());
            smartChangeMap.put("dispatcherNum", jsonList.get(i).getDispatcherNum());
            smartChangeMap.put("role", jsonList.get(i).getRole());
            smartChangeMap.put("onlineStatus", jsonList.get(i).getOnlineStatus());
            smartChangeMap.put("enableFlag", jsonList.get(i).getEnableFlag());
            smartChangeMap.put("parentOrgGuid", jsonList.get(i).getOrgGuid());
            smartChangeMap.put("version", jsonList.get(i).getVersion());
            smartChangeMap.put("dataType", 1);
            publishConstactChangeToApp(smartChangeMap, action);

        }
    }

    public void groupNotifyApp(List<SmpSsiGroupInfo> jsonList, String action) {

        for (int i = 0; i < jsonList.size(); i++) {
            Map groupChangeMap = new HashMap<String, Object>();
            groupChangeMap.put("smartonegroupGuid", jsonList.get(i).getGuid());
            groupChangeMap.put("cmdName", jsonList.get(i).getGroupName());
            groupChangeMap.put("groupId", jsonList.get(i).getGroupId());
            groupChangeMap.put("createUser", jsonList.get(i).getCreateuser());
            groupChangeMap.put("createDatetime", jsonList.get(i).getCreatetime());
            groupChangeMap.put("smartonegroupAlias", jsonList.get(i).getAlias());
            groupChangeMap.put("remark", jsonList.get(i).getRemark());
            groupChangeMap.put("updateTime", jsonList.get(i).getUpdatetime());
            groupChangeMap.put("smartonegroupNumber", jsonList.get(i).getNumberType());
            groupChangeMap.put("parentOrgGuid", jsonList.get(i).getOrgGuid());
            groupChangeMap.put("version", jsonList.get(i).getVersion());
            groupChangeMap.put("enableFlag", jsonList.get(i).getEnableFlag());
            groupChangeMap.put("dataType", 3);
            publishConstactChangeToApp(groupChangeMap, action);
        }

    }

    public void orgNotifyApp(List<SmpOrgInfoJsonObj> jsonList, String action) {

        for (int i = 0; i < jsonList.size(); i++) {
            Map orgChangeMap = new HashMap<String, Object>();
            orgChangeMap.put("orgGuid", jsonList.get(i).getGuid());
            orgChangeMap.put("orgName", jsonList.get(i).getOrgName());
            orgChangeMap.put("orgIdentifier", jsonList.get(i).getOrgIdentifier());
            orgChangeMap.put("parentOrgGuid", jsonList.get(i).getParentOrgGuid());
            orgChangeMap.put("version", jsonList.get(i).getVersion());
            orgChangeMap.put("dataType", 4);
            publishConstactChangeToApp(orgChangeMap, action);

        }

    }

    public void deviceNotifyApp(List<SmpInterphoneInfoJsonObj> jsonList, String action) {

        for (int i = 0; i < jsonList.size(); i++) {
            Map smartChangeMap = new HashMap<String, Object>();
            smartChangeMap.put("deviceGuid", jsonList.get(i).getInterphoneGuid());
            smartChangeMap.put("deviceCode", jsonList.get(i).getDeviceId());
            smartChangeMap.put("parentGuid", jsonList.get(i).getOrgGuid());
            smartChangeMap.put("pucId", jsonList.get(i).getPucId());
            smartChangeMap.put("systemId", jsonList.get(i).getSystemId());
            smartChangeMap.put("parentOrgGuid", jsonList.get(i).getOrgGuid());
            smartChangeMap.put("deviceType", jsonList.get(i).getDeviceType());
            smartChangeMap.put("deviceNumber", jsonList.get(i).getDeviceNumber());
            smartChangeMap.put("deviceName", jsonList.get(i).getDeviceName());
            smartChangeMap.put("numberType", jsonList.get(i).getNumberType());
            smartChangeMap.put("orgIdentifier", jsonList.get(i).getOrgIdentifier());
            smartChangeMap.put("systemNo", jsonList.get(i).getSystemNo());
            smartChangeMap.put("phoneType", jsonList.get(i).getPhoneType());
            smartChangeMap.put("alias", jsonList.get(i).getAlias());
            smartChangeMap.put("version", jsonList.get(i).getVersion());
            smartChangeMap.put("enableFlag", jsonList.get(i).getEnableFlag());
            smartChangeMap.put("longitude", jsonList.get(i).getLongitude());
            smartChangeMap.put("latitude", jsonList.get(i).getLatitude());
            smartChangeMap.put("dataType", 2);
            publishConstactChangeToApp(smartChangeMap, action);
        }

    }


    public void staffNotifyApp(List<SmpStaffInfoJsonObj> jsonList, String action) {
        for (int i = 0; i < jsonList.size(); i++) {
            Map staffChangeMap = new HashMap<String, Object>();
            staffChangeMap.put("staffCode", jsonList.get(i).getStaffCode());
            staffChangeMap.put("staffName", jsonList.get(i).getStaffName());
            staffChangeMap.put("staffGuid", jsonList.get(i).getGuid());
            staffChangeMap.put("orgIdentifier", jsonList.get(i).getOrgIdentifier());
            staffChangeMap.put("parentOrgGuid", jsonList.get(i).getOrgGuid());
            staffChangeMap.put("phoneNum", jsonList.get(i).getMobile());
            staffChangeMap.put("version", jsonList.get(i).getVersion());
            staffChangeMap.put("enableFlag", jsonList.get(i).getEnableFlag());
            staffChangeMap.put("position", jsonList.get(i).getPosition());
            staffChangeMap.put("dataType", 0);
            publishConstactChangeToApp(staffChangeMap, action);
        }
    }

    public void staffDeviceNotifyApp(List<SmpStaffDeviceInfo> jsonList, String action) {

        for (int i = 0; i < jsonList.size(); i++) {
            Map staffDeviceMap = new HashMap<String, Object>();
            staffDeviceMap.put("guid", jsonList.get(i).getGuid());
            staffDeviceMap.put("staffGuid", jsonList.get(i).getStaffGuid());
            staffDeviceMap.put("deviceCode", jsonList.get(i).getDeviceId());
            staffDeviceMap.put("deviceType", jsonList.get(i).getDeviceType());
            staffDeviceMap.put("deviceGuid", jsonList.get(i).getDeviceGuid());
            staffDeviceMap.put("staffGuid", jsonList.get(i).getStaffGuid());
            staffDeviceMap.put("enableFlag", jsonList.get(i).getEnableFlag());
            staffDeviceMap.put("version", jsonList.get(i).getVersion());
            staffDeviceMap.put("dataType", 5);
            publishConstactChangeToApp(staffDeviceMap, action);

        }
    }

}
