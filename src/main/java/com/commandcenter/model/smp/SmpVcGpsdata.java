package com.commandcenter.model.smp;

import java.util.Date;

public class SmpVcGpsdata {
    private String guid;

    private String deviceId;

    private Date gpsDatetime;

    private Double longitude;

    private Double latitude;

    private Long speed;

    private Long mileage;

    private String position;

    private Long direction;

    private Long deviceType;

    private String deviceName;

    private Long isOnline;

    private Long ischanged;

    private String orgId;

    private String carType;

    private Long channelCount;

    private String gatewIp;

    private String gatewPort;

    private String gatewUname;

    private String gatewPwd;

    private String staffGuid;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Short enableFlag;

    private String source;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
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

    public Long getSpeed() {
        return speed;
    }

    public void setSpeed(Long speed) {
        this.speed = speed;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Long getDirection() {
        return direction;
    }

    public void setDirection(Long direction) {
        this.direction = direction;
    }

    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public Long getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Long isOnline) {
        this.isOnline = isOnline;
    }

    public Long getIschanged() {
        return ischanged;
    }

    public void setIschanged(Long ischanged) {
        this.ischanged = ischanged;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    public Long getChannelCount() {
        return channelCount;
    }

    public void setChannelCount(Long channelCount) {
        this.channelCount = channelCount;
    }

    public String getGatewIp() {
        return gatewIp;
    }

    public void setGatewIp(String gatewIp) {
        this.gatewIp = gatewIp == null ? null : gatewIp.trim();
    }

    public String getGatewPort() {
        return gatewPort;
    }

    public void setGatewPort(String gatewPort) {
        this.gatewPort = gatewPort == null ? null : gatewPort.trim();
    }

    public String getGatewUname() {
        return gatewUname;
    }

    public void setGatewUname(String gatewUname) {
        this.gatewUname = gatewUname == null ? null : gatewUname.trim();
    }

    public String getGatewPwd() {
        return gatewPwd;
    }

    public void setGatewPwd(String gatewPwd) {
        this.gatewPwd = gatewPwd == null ? null : gatewPwd.trim();
    }

    public String getStaffGuid() {
        return staffGuid;
    }

    public void setStaffGuid(String staffGuid) {
        this.staffGuid = staffGuid == null ? null : staffGuid.trim();
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
}