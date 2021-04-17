package com.commandcenter.model.gps;


/**
 * @author r25437
 * @create 2018-10-23 10:31
 * @desc GPS接受APP上报入参实体
 **/
public class GpsDataModel {
    private String staffGuid;
    private String longWE;
    /**
     * 经度
     */
    private String longitude;
    private String latNS;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 方向
     */
    private String direction;
    /**
     * 速度
     */
    private String speed;
    /**
     * 时间
     */
    private String gpsTime;
    /**
     * 距离
     */
    private String distance;
    private String pucID;
    private String systemID;
    private String deviceID;
    private String dispatcherID;
    private String gpsDatetime;
    private String state;
    private String spaceCoordinate;
    private String electricity;
    private String rssidown;
    private String rssiup;
    private String sourceType;

    public String getPucID() {
        return pucID;
    }

    public void setPucID(String pucID) {
        this.pucID = pucID;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDispatcherID() {
        return dispatcherID;
    }

    public void setDispatcherID(String dispatcherID) {
        this.dispatcherID = dispatcherID;
    }

    public String getGpsDatetime() {
        return gpsDatetime;
    }

    public void setGpsDatetime(String gpsDatetime) {
        this.gpsDatetime = gpsDatetime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSpaceCoordinate() {
        return spaceCoordinate;
    }

    public void setSpaceCoordinate(String spaceCoordinate) {
        this.spaceCoordinate = spaceCoordinate;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public String getRssidown() {
        return rssidown;
    }

    public void setRssidown(String rssidown) {
        this.rssidown = rssidown;
    }

    public String getRssiup() {
        return rssiup;
    }

    public void setRssiup(String rssiup) {
        this.rssiup = rssiup;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStaffGuid() {
        return staffGuid;
    }

    public void setStaffGuid(String staffGuid) {
        this.staffGuid = staffGuid;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getLongWE() {
        return longWE;
    }

    public void setLongWE(String longWE) {
        this.longWE = longWE;
    }

    public String getLatNS() {
        return latNS;
    }

    public void setLatNS(String latNS) {
        this.latNS = latNS;
    }

    public String getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(String gpsTime) {
        this.gpsTime = gpsTime;
    }

    @Override
    public String toString() {
        return "GpsDataModel{" +
                "staffGuiId='" + staffGuid + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", direction='" + direction + '\'' +
                ", speed='" + speed + '\'' +
                ", gpsTime='" + gpsTime + '\'' +
                '}';
    }
}
