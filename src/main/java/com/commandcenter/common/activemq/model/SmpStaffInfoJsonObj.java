package com.commandcenter.common.activemq.model;

import java.io.Serializable;
import java.util.Date;

public class SmpStaffInfoJsonObj implements Serializable {
    private String guid;

    private String staffCode;

    private String staffName;

    private String staffType;

    private String category;

    private String staffStatus;

    private String sex;

    private String idNo;

    private String telephone;

    private String mobile;

    private String address;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String remark;

    private String xjType;

    private String isleader;

    private String isvalid;

    private String imagePath;

    private String orgGuid;

    private String orgIdentifier;

    private Long seq;

    private String position;

    private String systemNo;

    private String fromOrgGuid;

    private Date startTime;

    private Date endTime;

    private String interphoneGuid;

    private String deviceId;

    private String bindType;

    private String bindConfirm;

    private String loanReason;

    private String loanRemark;

    private String carGuid;

    private String vcGpsGuid;

    private Short enableFlag;

    private String source;

    private String pucId;

    private String systemId;

    private String email;

    private String imageGuid;

    private String versionSeq;

    private String orgHistory;

    private byte[] image;
    private int version;

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType == null ? null : staffType.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus == null ? null : staffStatus.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getXjType() {
        return xjType;
    }

    public void setXjType(String xjType) {
        this.xjType = xjType == null ? null : xjType.trim();
    }

    public String getIsleader() {
        return isleader;
    }

    public void setIsleader(String isleader) {
        this.isleader = isleader == null ? null : isleader.trim();
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid == null ? null : isvalid.trim();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid == null ? null : orgGuid.trim();
    }

    public String getOrgIdentifier() {
        return orgIdentifier;
    }

    public void setOrgIdentifier(String orgIdentifier) {
        this.orgIdentifier = orgIdentifier == null ? null : orgIdentifier.trim();
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo == null ? null : systemNo.trim();
    }

    public String getFromOrgGuid() {
        return fromOrgGuid;
    }

    public void setFromOrgGuid(String fromOrgGuid) {
        this.fromOrgGuid = fromOrgGuid == null ? null : fromOrgGuid.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getInterphoneGuid() {
        return interphoneGuid;
    }

    public void setInterphoneGuid(String interphoneGuid) {
        this.interphoneGuid = interphoneGuid == null ? null : interphoneGuid.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getBindType() {
        return bindType;
    }

    public void setBindType(String bindType) {
        this.bindType = bindType == null ? null : bindType.trim();
    }

    public String getBindConfirm() {
        return bindConfirm;
    }

    public void setBindConfirm(String bindConfirm) {
        this.bindConfirm = bindConfirm == null ? null : bindConfirm.trim();
    }

    public String getLoanReason() {
        return loanReason;
    }

    public void setLoanReason(String loanReason) {
        this.loanReason = loanReason == null ? null : loanReason.trim();
    }

    public String getLoanRemark() {
        return loanRemark;
    }

    public void setLoanRemark(String loanRemark) {
        this.loanRemark = loanRemark == null ? null : loanRemark.trim();
    }

    public String getCarGuid() {
        return carGuid;
    }

    public void setCarGuid(String carGuid) {
        this.carGuid = carGuid == null ? null : carGuid.trim();
    }

    public String getVcGpsGuid() {
        return vcGpsGuid;
    }

    public void setVcGpsGuid(String vcGpsGuid) {
        this.vcGpsGuid = vcGpsGuid == null ? null : vcGpsGuid.trim();
    }

    public Short getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Short enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getPucId() {
        return pucId;
    }

    public void setPucId(String pucId) {
        this.pucId = pucId == null ? null : pucId.trim();
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getImageGuid() {
        return imageGuid;
    }

    public void setImageGuid(String imageGuid) {
        this.imageGuid = imageGuid == null ? null : imageGuid.trim();
    }

    public String getVersionSeq() {
        return versionSeq;
    }

    public void setVersionSeq(String versionSeq) {
        this.versionSeq = versionSeq == null ? null : versionSeq.trim();
    }

    public String getOrgHistory() {
        return orgHistory;
    }

    public void setOrgHistory(String orgHistory) {
        this.orgHistory = orgHistory == null ? null : orgHistory.trim();
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}