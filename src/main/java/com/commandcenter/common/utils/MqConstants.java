package com.commandcenter.common.utils;

import java.io.Serializable;

/**
 * @author g20416
 * @date 2018-03-12
 * @describe 常量单元类
 */
public class MqConstants implements Serializable {

    public static final String GET_PATROLSCHEDULE_INFOR = "get_patrolresult_infor";
    public static final String GET_PARTOLPLACE_INFOR = "get_placeandpoint_infor";
    public static final String GET_TOPIC_SEND_SMP_DATA_NOTIFY = "topic_send_smp_data_notify1";
    //MQ 队列
    public static final String AIA_QUEUE_TEXT = "P2P_PRODUCER_RESULT_TEST";

    //批量插入提交数量
    public static final int BATCH_INSERT_NUM = 500;

    /**
     * 系统管理平台全量同步接口
     */
    //系统管理平台用户同步cmdCode
    public static final String MQ_CMD_CODE_SMP_USER_INFO = "get_smp_user_info";
    //系统管理平台警员设备绑定同步cmdCode
    public static final String MQ_CMD_CODE_SMP_STAFF_DEIVCE_INFO = "get_staff_device_infor";
    //系统管理平台警员同步cmdCode
    public static final String MQ_CMD_CODE_SMP_STAFF_INFO = "get_staff_infor";
    //系统管理平台用户警员绑定关系同步cmdCode
    public static final String MQ_CMD_CODE_SMP_USER_STAFF_BIND = "get_smp_user_staff_bind";
    //系统管理平台用户警员绑定关系同步cmdCode
    public static final String MQ_CMD_CODE_SMP_VCGPSDATA_INFOR= "get_vcgpsdata_infor";
    //系统管理平台组织机构同步cmdCode
    public static final String MQ_CMD_CODE_SMP_ORG_INFO = "get_organ_Infor";
    //系统管理平台功能菜单同步cmdCode(系统管理平台于20180412实现)
    public static final String MQ_CMD_CODE_SMP_FUNC_INFO = "get_auth_infor";
    //系统管理平台用户角色关系信息同步cmdCode
    public static final String MQ_CMD_CODE_SMP_USER_ROLE = "get_user_role_info";
    //系统管理平台角色权限信息同步cmdCode(该接口入三张表 角色表、角色组织机构关系表、角色菜单关系表)
    public static final String MQ_CMD_CODE_SMP_ROLE_AUTH = "get_sys_role_info";
    //系统管理平台数据字典信息同步cmdCode
    public static final String MQ_CMD_CODE_SMP_DICT_INFO = "get_dict_infor";
    //系统管理平台语言信息同步cmdCode
    public static final String MQ_CMD_CODE_SMP_LANGUAGE_INFO = "get_dict_language_info";
    //系统管理平台系统信息同步cmdCode
    public static final String MQ_CMD_CODE_SMP_SYSTEM_INFO = "get_smp_system_info";
    //系统管理平台用户同步cmdCode
    public static final String MQ_CMD_CODE_SMP_DEVICE_INFO = "get_interphone_infor";
    //系统管理平台SmartOne信息cmdCode
    public static final String MQ_CMD_CODE_SMP_SMART_INFO = "get_smartapp_infor";
    //系统管理平台角色信息cmdCode
    public static final String MQ_CMD_CODE_SMP_ROLE_SYS_INFO = "get_sys_role_info";
    //系统管理平台用户角色信息cmdCode
    public static final String MQ_CMD_CODE_ROLE_USER_INFO = "get_user_role_info";
    //系统管理平台用户角色信息cmdCode
    public static final String MQ_CMD_CODE_SMP_AUTH_INFO = "get_auth_infor";
    //系统管理平台组信息cmdCode
    public static final String MQ_CMD_CODE_SMP_GROUP_INFO = "get_pucssigroup_infor";
    //系统管理平台SmartOne组信息cmdCode
    public static final String MQ_CMD_CODE_SMP_SMARTONEGROUP_INFO = "get_smartonegroup_infor";
    //系统管理平台车辆信息cmdCode
    public static final String MQ_CMD_CODE_SMP_CAR_INFO = "get_car_infor";
    //系统管理平台车辆设备cmdCode
    public static final String MQ_CMD_CODE_CAR_DEVICE_NOTIFY = "send_smp_car_device_notify";
    //系统管理平台车辆设备cmdCode
    public static final String MQ_CMD_CODE_SMP_CAR_DEVICE_INFO = "get_car_device_infor";
    //系统管理平台资源信息cmdCode
    public static final String MQ_CMD_CODE_SMP_RESOURCES_INFO = "get_resources_infor";
    //系统管理平台岗位信息cmdCode
    public static final String MQ_CMD_CODE_SMP_POST_INFO = "get_smp_t_post_info_infor";
    //同步PUC系统信息
    public static final String MQ_CMD_CODE_PUC_SYSTEM_INFO = "get_pucsytem_infor";

    /**
     * 系统管理平台通知接口
     */
    //系统管理平台用户同步cmdCode
    public static final String MQ_CMD_CODE_SMP_USER_INFO_NOTIFY = "send_smp_user_notify";
    //系统管理平台设备警员绑定同步cmdCode
    public static final String MQ_CMD_CODE_SMP_STAFF_DEVICE_NOTIFY = "send_smp_staff_device_notify";
    //系统管理平台警员同步cmdCode
    public static final String MQ_CMD_CODE_SMP_STAFF_INFO_NOTIFY = "send_smp_staff_notify";
    //系统管理平台组织机构同步cmdCode
    public static final String MQ_CMD_CODE_SMP_ORG_INFO_NOTIFY = "send_smp_org_notify";
    //系统管理平台字典同步cmdCode
    public static final String MQ_CMD_CODE_SMP_DICT_NOTIFY = "send_smp_dict_notify";
    //系统管理平台语言同步cmdCode
    public static final String MQ_CMD_CODE_SMP_LANGUAGE_NOTIFY = "send_smp_language_notify";
    //系统管理平台手台cmdCode
    public static final String MQ_CMD_CODE_SMP_INTER_NOTIFY = "send_smp_inter_notify";
    //系统管理平台执法记录仪cmdCode
    public static final String MQ_CMD_CODE_SMP_VCGPSDATA_NOTIFY = "send_smp_vcgpsdata_notify";
    //系统管理平台SmartOne信息cmdCode
    public static final String MQ_CMD_CODE_SMP_SMART_INFO_NOTIFY = "send_smp_app_notify";
    //系统管理平台角色信息cmdCode
    public static final String MQ_CMD_CODE_SMP_ROLE_INFO_NOTIFY = "send_smp_role_notify";
    //系统管理平台角色信息cmdCode
    public static final String MQ_CMD_CODE_ROLE_BIND_AUTHORITY_NOTIFY = "send_smp_role_bind_authority_notify";
    public static final String MQ_CMD_CODE_GROUP_INFO_NOTIFY = "send_smp_group_notify";
    public static final String MQ_CMD_CODE_AUTHORITY_INFO_NOTIFY = "send_smp_authority_notify";
    //系统管理平台岗位信息cmdCode
    public static final String MQ_CMD_CODE_POST_NOTIFY = "send_smp_t_post_info_notify";
    public static final String MQ_CMD_CODE_CAR_INFO_NOTIFY = "send_smp_car_notify";

    //系统管理平台用户角色信息绑定通知cmdCode
    public static final String MQ_CMD_CODE_ROLE_BIND_USER_NOTIFY = "send_smp_user_bind_role_notify";
    //系统管理平台用户角色信息解绑通知cmdCode
    public static final String MQ_CMD_CODE_ROLE_UNBIND_USER_NOTIFY = "send_smp_user_unbind_role_notify";
    //系统管理平台资源信息cmdCode
    public static final String MQ_CMD_CODE_RESOURCE_NOTIFY = "send_resources_notify";



    /**
     * AMQCodes
     */
    public enum AMQCodes {
        /**
         * 警情分析queue测试
         */
        AIA_QUEUE_TEXT("P2P_PRODUCER_RESULT_TEST"),
        /**
         * 系统管理平台基础数据queue
         */
        SMP_QUEUE_BASE_DATA("queue_smp_sharedata_test");

        private String value;

        AMQCodes(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * ajax调用返回代码
     */
    public enum AjaxResultCodes {
        /**
         * 成功
         */
        OK("000000"),
        /**
         * 失败
         */
        ERROR("444444");

        private String value;

        AjaxResultCodes(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * smp通知cmd类型
     */
    public enum SmpNotifyCode {
        /**
         * 增加
         */
        ADD("add"),
        /**
         * 修改
         */
        UPDATE("update"),
        /**
         * 删除
         */
        DELETE("delete"),
        /**
         * 解绑警员
         */
        UNBIND("unbind"),
        /**
         * 用户绑定警员,角色绑定权限
         */
        BIND("bind"),
        /**
         * 警员批量修改部门
         */
        UPDATE_ORG("update_org"),
        /**
         * 用户绑定角色
         */
        BIND_ROLE("bind_role"),
        /**
         * 用户解绑角色
         */
        UNBIND_ROLE("unbind_role"),
        /**
         * 用户绑定组织机构
         */
        BIND_ORG("bind_org"),
        /**
         * 用户解绑组织机构
         */
        UNBIND_ORG("unbind_org");

        private String value;

        SmpNotifyCode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
