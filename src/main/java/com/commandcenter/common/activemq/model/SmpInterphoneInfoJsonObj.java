package com.commandcenter.common.activemq.model;

import java.io.Serializable;
import java.util.Date;

public class SmpInterphoneInfoJsonObj implements Serializable {
    public String getInterphoneGuid() {
        return interphoneGuid;
    }

    public void setInterphoneGuid(String interphoneGuid) {
        this.interphoneGuid = interphoneGuid;
    }

    private String interphoneGuid;
    private String guid;
    private String pucId;

    private String userName;

    private String userType;

    private String systemId;

    private String orgGuid;

    private String deviceId;

    private String deviceType;

    private String deviceNumber;

    private String deviceName;

    private String status;

    private String deviceMake;

    private String numberType;

    private Long hasGps;

    private Long enableGps;

    private Long andOrFlag;

    private Long gpsInterval;

    private Long gpsChannel;

    private Long distance;

    private Date gpsDatetime;

    private Double longitude;

    private Double latitude;

    private Long hasScreen;

    private Long versionSeq;

    private String orgHistory;

    private String joinGroup;

    private String joinGroupInfo;

    private String responseGroup;

    private String responseGroupInfo;

    private String implictGroup;

    private String implictGroupInfo;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    private String expend;

    private String remark;

    private String isvalid;

    private String orgIdentifier;

    private String systemNo;

    private String phoneType;

    private String userGuid;

    private Short enableFlag;

    private String source;

    private String alias;

    private String subsapGuid;

    private byte[] deviceIcon;

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

    public String getPucId() {
        return pucId;
    }

    public void setPucId(String pucId) {
        this.pucId = pucId == null ? null : pucId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid == null ? null : orgGuid.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber == null ? null : deviceNumber.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDeviceMake() {
        return deviceMake;
    }

    public void setDeviceMake(String deviceMake) {
        this.deviceMake = deviceMake == null ? null : deviceMake.trim();
    }

    public String getNumberType() {
        return numberType;
    }

    public void setNumberType(String numberType) {
        this.numberType = numberType == null ? null : numberType.trim();
    }

    public Long getHasGps() {
        return hasGps;
    }

    public void setHasGps(Long hasGps) {
        this.hasGps = hasGps;
    }

    public Long getEnableGps() {
        return enableGps;
    }

    public void setEnableGps(Long enableGps) {
        this.enableGps = enableGps;
    }

    public Long getAndOrFlag() {
        return andOrFlag;
    }

    public void setAndOrFlag(Long andOrFlag) {
        this.andOrFlag = andOrFlag;
    }

    public Long getGpsInterval() {
        return gpsInterval;
    }

    public void setGpsInterval(Long gpsInterval) {
        this.gpsInterval = gpsInterval;
    }

    public Long getGpsChannel() {
        return gpsChannel;
    }

    public void setGpsChannel(Long gpsChannel) {
        this.gpsChannel = gpsChannel;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Date getGpsDatetime() {
        return gpsDatetime;
    }

    public void setGpsDatetime(Date gpsDatetime) {
        this.gpsDatetime = gpsDatetime;
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

    public Long getHasScreen() {
        return hasScreen;
    }

    public void setHasScreen(Long hasScreen) {
        this.hasScreen = hasScreen;
    }

    public Long getVersionSeq() {
        return versionSeq;
    }

    public void setVersionSeq(Long versionSeq) {
        this.versionSeq = versionSeq;
    }

    public String getOrgHistory() {
        return orgHistory;
    }

    public void setOrgHistory(String orgHistory) {
        this.orgHistory = orgHistory == null ? null : orgHistory.trim();
    }

    public String getJoinGroup() {
        return joinGroup;
    }

    public void setJoinGroup(String joinGroup) {
        this.joinGroup = joinGroup == null ? null : joinGroup.trim();
    }

    public String getJoinGroupInfo() {
        return joinGroupInfo;
    }

    public void setJoinGroupInfo(String joinGroupInfo) {
        this.joinGroupInfo = joinGroupInfo == null ? null : joinGroupInfo.trim();
    }

    public String getResponseGroup() {
        return responseGroup;
    }

    public void setResponseGroup(String responseGroup) {
        this.responseGroup = responseGroup == null ? null : responseGroup.trim();
    }

    public String getResponseGroupInfo() {
        return responseGroupInfo;
    }

    public void setResponseGroupInfo(String responseGroupInfo) {
        this.responseGroupInfo = responseGroupInfo == null ? null : responseGroupInfo.trim();
    }

    public String getImplictGroup() {
        return implictGroup;
    }

    public void setImplictGroup(String implictGroup) {
        this.implictGroup = implictGroup == null ? null : implictGroup.trim();
    }

    public String getImplictGroupInfo() {
        return implictGroupInfo;
    }

    public void setImplictGroupInfo(String implictGroupInfo) {
        this.implictGroupInfo = implictGroupInfo == null ? null : implictGroupInfo.trim();
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getExpend() {
        return expend;
    }

    public void setExpend(String expend) {
        this.expend = expend == null ? null : expend.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid == null ? null : isvalid.trim();
    }

    public String getOrgIdentifier() {
        return orgIdentifier;
    }

    public void setOrgIdentifier(String orgIdentifier) {
        this.orgIdentifier = orgIdentifier == null ? null : orgIdentifier.trim();
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo == null ? null : systemNo.trim();
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType == null ? null : phoneType.trim();
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid == null ? null : userGuid.trim();
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getSubsapGuid() {
        return subsapGuid;
    }

    public void setSubsapGuid(String subsapGuid) {
        this.subsapGuid = subsapGuid == null ? null : subsapGuid.trim();
    }

    public byte[] getDeviceIcon() {
        return deviceIcon;
    }

    public void setDeviceIcon(byte[] deviceIcon) {
        this.deviceIcon = deviceIcon;
    }
}