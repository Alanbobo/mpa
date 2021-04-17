package com.commandcenter.model.casemodel;

import java.util.List;

public class VcstCaseFeedback {
    private String id;//标识
    private String caseId;//警情ID
    private String disposalId;//处警单ID
    private String caseDispatchStatus;//反馈类型:1接收警单；2到达现场；3处置完毕
    private String interphoneId;//设备ID列表（逗号分割）
    private String fbWay;//反馈方式:1终端快捷键；2短信；3电话
    private String fbContent;//反馈内容
    private String feedbacker;//反馈人
    private java.util.Date fbTime;//反馈时间
    private Double longitude;//经度
    private Double latitude;//纬度
    private String caseType;//警情类型
    private String caseSubType;//警情中类
    private String caseThreeType;//警情小类
    private String caseProcessor;//处警人列表（逗号分割）
    private String createuser;//创建人
    private java.util.Date createTime;//创建时间
    private String updateUser;//修改人
    private java.util.Date updateTime;//修改时间
    private int delFlag;//删除标记：0有效，1删除
    private String dispatchId;//调派id
    private String caseLevel;//警情级别
    //新加批示
    private String applyApprovalFeedBack;//申请批示反馈
    private String staffGuid;
    private List<MpatFeedbackFile> mpatFeedbackFileList;

    public VcstCaseFeedback() {
        super();
    }
    public VcstCaseFeedback(String id,String caseId,String disposalId,String caseDispatchStatus,String interphoneId,String fbWay,String fbContent,String feedbacker,java.util.Date fbTime,Double longitude,Double latitude,String caseType,String caseSubType,String caseThreeType,String caseProcessor,String createuser,java.util.Date createTime,String updateUser,java.util.Date updateTime,int delFlag,String dispatchId,String caseLevel,String applyApprovalFeedBack) {
        super();
        this.id = id;
        this.caseId = caseId;
        this.disposalId = disposalId;
        this.caseDispatchStatus = caseDispatchStatus;
        this.interphoneId = interphoneId;
        this.fbWay = fbWay;
        this.fbContent = fbContent;
        this.feedbacker = feedbacker;
        this.fbTime = fbTime;
        this.longitude = longitude;
        this.latitude = latitude;
        this.caseType = caseType;
        this.caseSubType = caseSubType;
        this.caseThreeType = caseThreeType;
        this.caseProcessor = caseProcessor;
        this.createuser = createuser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.delFlag = delFlag;
        this.dispatchId = dispatchId;
        this.caseLevel = caseLevel;
        this.applyApprovalFeedBack = applyApprovalFeedBack;
    }

    public String getStaffGuid() {
        return staffGuid;
    }

    public void setStaffGuid(String staffGuid) {
        this.staffGuid = staffGuid;
    }

    public List<MpatFeedbackFile> getMpatFeedbackFileList() {
        return mpatFeedbackFileList;
    }

    public void setMpatFeedbackFileList(List<MpatFeedbackFile> mpatFeedbackFileList) {
        this.mpatFeedbackFileList = mpatFeedbackFileList;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseId() {
        return this.caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getDisposalId() {
        return this.disposalId;
    }

    public void setDisposalId(String disposalId) {
        this.disposalId = disposalId;
    }

    public String getCaseDispatchStatus() {
        return this.caseDispatchStatus;
    }

    public void setCaseDispatchStatus(String caseDispatchStatus) {
        this.caseDispatchStatus = caseDispatchStatus;
    }

    public String getInterphoneId() {
        return this.interphoneId;
    }

    public void setInterphoneId(String interphoneId) {
        this.interphoneId = interphoneId;
    }

    public String getFbWay() {
        return this.fbWay;
    }

    public void setFbWay(String fbWay) {
        this.fbWay = fbWay;
    }

    public String getFbContent() {
        return this.fbContent;
    }

    public void setFbContent(String fbContent) {
        this.fbContent = fbContent;
    }

    public String getFeedbacker() {
        return this.feedbacker;
    }

    public void setFeedbacker(String feedbacker) {
        this.feedbacker = feedbacker;
    }

    public java.util.Date getFbTime() {
        return this.fbTime;
    }

    public void setFbTime(java.util.Date fbTime) {
        this.fbTime = fbTime;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getCaseType() {
        return this.caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseSubType() {
        return this.caseSubType;
    }

    public void setCaseSubType(String caseSubType) {
        this.caseSubType = caseSubType;
    }

    public String getCaseThreeType() {
        return this.caseThreeType;
    }

    public void setCaseThreeType(String caseThreeType) {
        this.caseThreeType = caseThreeType;
    }

    public String getCaseProcessor() {
        return this.caseProcessor;
    }

    public void setCaseProcessor(String caseProcessor) {
        this.caseProcessor = caseProcessor;
    }

    public String getCreateuser() {
        return this.createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public String getDispatchId() {
        return this.dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getCaseLevel() {
        return this.caseLevel;
    }

    public void setCaseLevel(String caseLevel) {
        this.caseLevel = caseLevel;
    }

    public String getApplyApprovalFeedBack() {
        return applyApprovalFeedBack;
    }

    public void setApplyApprovalFeedBack(String applyApprovalFeedBack) {
        this.applyApprovalFeedBack = applyApprovalFeedBack;
    }
}
