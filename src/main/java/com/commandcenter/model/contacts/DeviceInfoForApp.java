package com.commandcenter.model.contacts;

import com.commandcenter.common.utils.PinYinUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class DeviceInfoForApp implements Comparable<DeviceInfoForApp>{
    private String deviceGuid;

    private String deviceCode;

    private String isOnline;

    private String pucId;

    private String systemId;

    private String parentOrgGuid;

    private String deviceType;

    private String deviceNumber;

    private String deviceName;

    private String numberType;

    private String orgIdentifier;

    private String systemNo;

    private String phoneType;

    private String alias;

    private String orgName;


    private int dataType =2;

    private int version;

    private String longitude;

    private String latitude;

    public String getIsOnLine() {
        return isOnLine;
    }

    public void setIsOnLine(String isOnLine) {
        this.isOnLine = isOnLine;
    }

    private String isOnLine;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }



    private String enableFlag;
    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getDeviceGuid() {
        return deviceGuid;
    }

    public void setDeviceGuid(String deviceGuid) {
        this.deviceGuid = deviceGuid;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

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

    public String getParentOrgGuid() {
        return parentOrgGuid;
    }

    public void setParentOrgGuid(String parentOrgGuid) {
        this.parentOrgGuid = parentOrgGuid;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getNumberType() {
        return numberType;
    }

    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }

    public String getOrgIdentifier() {
        return orgIdentifier;
    }

    public void setOrgIdentifier(String orgIdentifier) {
        this.orgIdentifier = orgIdentifier;
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int compareTo(DeviceInfoForApp deviceInfoForApp) {
        String afterName = PinYinUtils.getHanziPinYin(deviceInfoForApp.getDeviceName());
        String beforeName = PinYinUtils.getHanziPinYin(this.deviceName);
        if(afterName == null){
            afterName = deviceInfoForApp.getDeviceName();
        }
        if(beforeName == null){
            beforeName = this.deviceName;
        }
        //如果设备deviceName为空，之后的比较就会报错，所以如果 afterName 或者 beforeName == null，那么就赋一个默认值。
        if (StringUtils.isBlank(afterName)){
            afterName = "";
        }
        if (StringUtils.isBlank(beforeName)){
            beforeName = "";
        }
        if(!afterName.toLowerCase().equals(beforeName.toLowerCase())){
            return beforeName.toLowerCase().compareTo(afterName.toLowerCase());
        }else{
            if(!afterName.equals(beforeName)){
                return beforeName.compareTo(afterName);
            }
        }
        return beforeName.length() - afterName.length();
    }
}
