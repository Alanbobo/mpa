package com.commandcenter.model.smp;

import java.util.Date;

public class SmpPolicestationInfo {
    private String policestationGuid;

    private String policestationName;

    private String policestationSeat;

    private String policestationPerson;

    private String outsidePhone;

    private String internalPhone;

    private String parentGroup;

    private String address;

    private String categoryType;

    private String abandonedFlag;

    private Date updatetime;

    private Double longitude;

    private Double latitude;

    private String statusType;

    private String orgGuid;

    private String policestationGuidExt;

    private Date createtime;

    private String enableFlag;

    public String getPolicestationGuid() {
        return policestationGuid;
    }

    public void setPolicestationGuid(String policestationGuid) {
        this.policestationGuid = policestationGuid == null ? null : policestationGuid.trim();
    }

    public String getPolicestationName() {
        return policestationName;
    }

    public void setPolicestationName(String policestationName) {
        this.policestationName = policestationName == null ? null : policestationName.trim();
    }

    public String getPolicestationSeat() {
        return policestationSeat;
    }

    public void setPolicestationSeat(String policestationSeat) {
        this.policestationSeat = policestationSeat == null ? null : policestationSeat.trim();
    }

    public String getPolicestationPerson() {
        return policestationPerson;
    }

    public void setPolicestationPerson(String policestationPerson) {
        this.policestationPerson = policestationPerson == null ? null : policestationPerson.trim();
    }

    public String getOutsidePhone() {
        return outsidePhone;
    }

    public void setOutsidePhone(String outsidePhone) {
        this.outsidePhone = outsidePhone == null ? null : outsidePhone.trim();
    }

    public String getInternalPhone() {
        return internalPhone;
    }

    public void setInternalPhone(String internalPhone) {
        this.internalPhone = internalPhone == null ? null : internalPhone.trim();
    }

    public String getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(String parentGroup) {
        this.parentGroup = parentGroup == null ? null : parentGroup.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType == null ? null : categoryType.trim();
    }

    public String getAbandonedFlag() {
        return abandonedFlag;
    }

    public void setAbandonedFlag(String abandonedFlag) {
        this.abandonedFlag = abandonedFlag == null ? null : abandonedFlag.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType == null ? null : statusType.trim();
    }

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid == null ? null : orgGuid.trim();
    }

    public String getPolicestationGuidExt() {
        return policestationGuidExt;
    }

    public void setPolicestationGuidExt(String policestationGuidExt) {
        this.policestationGuidExt = policestationGuidExt == null ? null : policestationGuidExt.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag == null ? null : enableFlag.trim();
    }
}