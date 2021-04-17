package com.commandcenter.common.activemq.model;

import java.util.List;

/**
 * @author r25437
 * @create 2018-09-06 11:05
 * @desc 发送MQ消息报文
 **/
public class MQParamModel {
    private String msg; //MQ内容

    private String name; //MQ名称(主题名或队列名)

    private boolean queueFlag = false; //MQ类型 false表示主题  true表示队列

    private boolean personalFlag = false; //是否个推 个推 true ,  false 群推

    private List<String> userNos; //发送用户列表，个推时使用

    private boolean encryptFlag = true; //是否加密标识，默认全部加密

    public MQParamModel(String msg, String name) {
        this.name = name;
        this.msg = msg;
    }

    public MQParamModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isQueueFlag() {
        return queueFlag;
    }

    public void setQueueFlag(boolean queueFlag) {
        this.queueFlag = queueFlag;
    }

    public boolean isPersonalFlag() {
        return personalFlag;
    }

    public void setPersonalFlag(boolean personalFlag) {
        this.personalFlag = personalFlag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getUserNos() {
        return userNos;
    }

    public void setUserNos(List<String> userNos) {
        this.userNos = userNos;
    }

    public boolean isEncryptFlag() {
        return encryptFlag;
    }

    public void setEncryptFlag(boolean encryptFlag) {
        this.encryptFlag = encryptFlag;
    }
}
