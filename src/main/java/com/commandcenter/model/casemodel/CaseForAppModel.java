package com.commandcenter.model.casemodel;

import com.commandcenter.model.wcf.FeedbackForAppModel;

import java.util.List;

/**
 * @author r25437
 * @create 2018-09-19 11:44
 * @desc 返回app警情信息
 **/
public class CaseForAppModel {
    /**
     * 警情ID
     */
    private String alarmId = "";
    /**
     * 警情列表图片地址
     */
    private String alarmImage = "";
    /**
     * 报警时间。时间戳，毫秒级
     */
    private String alarmTime = "";
    /**
     * 13312345678
     */
    private String alarmPhoneNumber = "";
    /**
     * 联系人
     */
    private String alarmLinkman = "";
    /**
     * 南山区源政创业大厦对面马路
     */
    private String alarmAddress = "";
    /**
     * 事件详细描述
     */
    private String alarmDesc = "";
    /**
     * 经度
     */
    private String alarmLongitude = "";
    /**
     * 纬度
     */
    private String alarmLatitude = "";
    /**
     * 警情状态：接警，到达，反馈等
     */
    private String alarmStatus = "";
    /**
     * 案情类型：多级间以;分隔。如"纠纷;民事纠纷"
     */
    private String alarmCaseTypeValue = "";
    /**
     * 案情类型：多级间以;分隔。如"0;11;52"
     */
    private String alarmCaseTypeKey = "";
    /**
     * 警情级别
     */
    private String alarmLevel = "";
    /**
     * 时间戳，毫秒级
     */
    private String alarmCreateTime = "";
    /**
     * 时间戳，毫秒级
     */
    private String alarmUpdateTime = "";

    private String feedbackStatus = "";

    private List<FeedbackForAppModel> alarmFeedback;

    private List<MpatDispatch> dispatchModels;

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getAlarmImage() {
        return alarmImage;
    }

    public void setAlarmImage(String alarmImage) {
        this.alarmImage = alarmImage;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmPhoneNumber() {
        return alarmPhoneNumber;
    }

    public void setAlarmPhoneNumber(String alarmPhoneNumber) {
        this.alarmPhoneNumber = alarmPhoneNumber;
    }

    public String getAlarmLinkman() {
        return alarmLinkman;
    }

    public void setAlarmLinkman(String alarmLinkman) {
        this.alarmLinkman = alarmLinkman;
    }

    public String getAlarmAddress() {
        return alarmAddress;
    }

    public void setAlarmAddress(String alarmAddress) {
        this.alarmAddress = alarmAddress;
    }

    public String getAlarmDesc() {
        return alarmDesc;
    }

    public void setAlarmDesc(String alarmDesc) {
        this.alarmDesc = alarmDesc;
    }

    public String getAlarmLongitude() {
        return alarmLongitude;
    }

    public void setAlarmLongitude(String alarmLongitude) {
        this.alarmLongitude = alarmLongitude;
    }

    public String getAlarmLatitude() {
        return alarmLatitude;
    }

    public void setAlarmLatitude(String alarmLatitude) {
        this.alarmLatitude = alarmLatitude;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public String getAlarmCaseTypeValue() {
        return alarmCaseTypeValue;
    }

    public void setAlarmCaseTypeValue(String alarmCaseTypeValue) {
        this.alarmCaseTypeValue = alarmCaseTypeValue;
    }

    public String getAlarmCaseTypeKey() {
        return alarmCaseTypeKey;
    }

    public void setAlarmCaseTypeKey(String alarmCaseTypeKey) {
        this.alarmCaseTypeKey = alarmCaseTypeKey;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmCreateTime() {
        return alarmCreateTime;
    }

    public void setAlarmCreateTime(String alarmCreateTime) {
        this.alarmCreateTime = alarmCreateTime;
    }

    public String getAlarmUpdateTime() {
        return alarmUpdateTime;
    }

    public void setAlarmUpdateTime(String alarmUpdateTime) {
        this.alarmUpdateTime = alarmUpdateTime;
    }

    public List<FeedbackForAppModel> getAlarmFeedback() {
        return alarmFeedback;
    }

    public void setAlarmFeedback(List<FeedbackForAppModel> alarmFeedback) {
        this.alarmFeedback = alarmFeedback;
    }

    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public List<MpatDispatch> getDispatchModels() {
        return dispatchModels;
    }

    public void setDispatchModels(List<MpatDispatch> dispatchModels) {
        this.dispatchModels = dispatchModels;
    }

    @Override
    public String toString() {
        return "CaseForAppModel{" +
                "alarmId='" + alarmId + '\'' +
                ", alarmImage='" + alarmImage + '\'' +
                ", alarmTime='" + alarmTime + '\'' +
                ", alarmPhoneNumber='" + alarmPhoneNumber + '\'' +
                ", alarmLinkman='" + alarmLinkman + '\'' +
                ", alarmAddress='" + alarmAddress + '\'' +
                ", alarmDesc='" + alarmDesc + '\'' +
                ", alarmLongitude='" + alarmLongitude + '\'' +
                ", alarmLatitude='" + alarmLatitude + '\'' +
                ", alarmStatus='" + alarmStatus + '\'' +
                ", alarmCaseTypeValue='" + alarmCaseTypeValue + '\'' +
                ", alarmCaseTypeKey='" + alarmCaseTypeKey + '\'' +
                ", alarmLevel='" + alarmLevel + '\'' +
                ", alarmCreateTime='" + alarmCreateTime + '\'' +
                ", alarmUpdateTime='" + alarmUpdateTime + '\'' +
                ", feedbackStatus='" + feedbackStatus + '\'' +
                ", alarmFeedback=" + alarmFeedback +
                '}';
    }
}
