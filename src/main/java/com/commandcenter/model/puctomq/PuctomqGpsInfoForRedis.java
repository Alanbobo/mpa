package com.commandcenter.model.puctomq;

import java.util.Date;

public class PuctomqGpsInfoForRedis {
    private String longitude;
    private String latitude;
    private String pucId;
    private String systemId;
    private Date gpsDataTime;
    private String longWe;
    private String latNs;
    private String speed;
    private String derection;
    private String state;
    private String sourcetype;
    private String spacecoordinate;
    private String deviceId;

    public String getPucId() {
        return pucId;
    }

    public void setPucId(String pucId) {
        this.pucId = pucId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public Date getGpsDataTime() {
        return gpsDataTime;
    }

    public void setGpsDataTime(Date gpsDataTime) {
        this.gpsDataTime = gpsDataTime;
    }

    public String getLongWe() {
        return longWe;
    }

    public void setLongWe(String longWe) {
        this.longWe = longWe;
    }

    public String getLatNs() {
        return latNs;
    }

    public void setLatNs(String latNs) {
        this.latNs = latNs;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDerection() {
        return derection;
    }

    public void setDerection(String derection) {
        this.derection = derection;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSourcetype() {
        return sourcetype;
    }

    public void setSourcetype(String sourcetype) {
        this.sourcetype = sourcetype;
    }

    public String getSpacecoordinate() {
        return spacecoordinate;
    }

    public void setSpacecoordinate(String spacecoordinate) {
        this.spacecoordinate = spacecoordinate;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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
}
