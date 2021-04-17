package com.commandcenter.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 常量
 * 
 * @author r25437
 * @date 2016年11月15日 下午1:23:52
 */
@Component
public class Constant {

    /**
     *app登录调度员
     */
    public static final String APP_ACCOUNT_TYPE = "2";

    /**
     *pc登录调度员
     */
    public static final String PC_ACCOUNT_TYPE = "3";


	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;

    /**
     * 批量执行SQL的数量
     */
	public static final int BATCH_COUNT = 300;

	/*http调用接口使用，读取配置文件中参数*/
	/*由于无法对static变量进行注入，因此改为set方法注入*/
    /**
     * 存放用户id与对应token的Map，以username为主键
     */
    public static final Map<String, Object> tokenMapIns = new ConcurrentHashMap<String, Object>();

    /**
     * 定义activeMQ 的 request_type   要发送消息的传送模型：0：代表队列，1：代表主题。
     */
    public static final String MQ_REQUEST_TYPE_QUEUE = "0";
    public static final String MQ_REQUEST_TYPE_TOPIC = "1";

    /**
     * ICC需要下发普通反馈通知的版本号
     */
    public static final String ICC_SEND_FEEDBACK_NOTIFY_VERSION = "1.0";

    /**
     * 存放常量token的tokenMap,以token为主键
     */
    public static final Map<String, Object> tokenMap = new ConcurrentHashMap<>();

    /**
     * 存放CAS票据TGT ID的tgtMap,以userCode为主键
     */
    public static final Map<String, Object> tgtMap = new ConcurrentHashMap<>();

    /**
     * 心跳监测接口名称
     */
    public static final String HEART_BEAT = "heartBeatMsg";

    /**
     * 取消调派状态
     */
    public static final String DISPATCH_ISCANCEL = "1";

    /**
     * VCS查询反馈列表接口
     */
    public static final String VCS_GET_HANDLE_RECORD_LIST = "/services/vcsserver/caseservice/gethandlerecordlist";

    /**
     * VCS反馈接口
     */
    public static final String VCS_SAVE_FEED_BACK = "/services/vcsserver/caseservice/saveappfeedback";

    /**
     *  VCS反馈接口
     */
    public static final String VCS_SAVE_POSITION = "/services/vcsserver/caseservice/caseservice/saveposition";


    /**
     *  通过警情ID,获取警情申请批示列表信息
     */
    public static final String  APPROCAL_LIST_BY_CASEID_URL = "/services/vcsserver/caseservice/caseservice/getapplyapprovallistbycaseid";



    /**
     *  通过批示领导ID,获取警情申请批示列表信息
     */
    public static final String APPROCAL_LIST_BY_lEADER_URL = "/services/vcsserver/caseservice/caseservice/getapplyapprovallistbyleaderid";


    /**
     *  通过批示ID,获取批示详情
     */
    public static final String APPROCAL_INFO_BY_ID = "/services/vcsserver/caseservice/caseservice/getapplyapprovalbyid";






    /**
     * ENCRYPT 配置
     */
    public static String ENCRYPT_KEY ;
    @Value("${system.encrypt.key}")
    private void setEncryptKey(String encryptKey){
        ENCRYPT_KEY = encryptKey;
    }
    public static String ENCRYPT_RSASIGN;
    @Value("${system.encrypt.rsasign}")
    private void setEncryptRsasign(String encryptRsasign){
        ENCRYPT_RSASIGN = encryptRsasign;
    }
    public static String ENCRYPT_SALT ;
    @Value("${system.encrypt.salt}")
    private void setEncryptSalt(String encryptSalt){
        ENCRYPT_SALT = encryptSalt;
    }

    public static String WCF_IP ;
    @Value("${system.notice.vcs.wcf.ip}")
    private void setWcfIp(String wcfIp){
        WCF_IP = wcfIp;
    }

    /**
     * 秘钥路径
     */
    public static String getKeyPath(){
        //秘钥路径
        try{

            return new File(ResourceUtils.getURL("classpath:keystore").getPath()).getPath();
        }catch (Exception e){
            return "";
        }
    }
    /**
	 * 菜单类型
	 * 
	 * @author r25437
	 * @date 2016年11月15日 下午1:24:29
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     * 
     * @author r25437
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /**
     * 调派类型
     */
    public enum DISPATCH_TYPE {
        /**
         * 警员
         */
        STAFF("2"),
        /**
         * 组织机构
         */
        ORG("1"),
        /**
         * 取消
         */
        CANCEL("3");

        private String value;

        DISPATCH_TYPE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static DISPATCH_TYPE getByValue(String value) {
            for (DISPATCH_TYPE code : values()) {
                if (code.getValue() == value) {
                    return code;
                }
            }
            return null;
        }
    }

    /**
     * 警情状态
     */
    public enum ALARM_STATUS {
        /**
         * 未调派
         */
        NO_DISPATCH("JQZT001"),
        /**
         * 未接受
         */
        NO_ACCEPT("JQZT002"),
        /**
         * 已接受
         */
        ACCEPTED("JQZT003"),
        /**
         * 已到达
         */
        ARRIVED("JQZT004"),
        /**
         * 已完成
         */
        FINISHED("JQZT005"),

        /**
         * 退单
         */
        BACK_ALARM("JQZT006");

        private String value;

        ALARM_STATUS(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static ALARM_STATUS getByValue(String value) {
            for (ALARM_STATUS code : values()) {
                if (code.getValue() == value) {
                    return code;
                }
            }
            return null;
        }
    }

}
