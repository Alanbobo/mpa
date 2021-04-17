package com.commandcenter.model.ppcs;

/**
 * @Author
 *  2019/1/18.
 */
public class DutyPhone {
    /**
     * 手台id
     */
    private String interphoneGuid;
    /**
     * 手台号码
     */
    private String deviceId;
    /**
     * pucid
     */
    private String pucId;
    /**
     * 系统id
     */
    private String systemId;
    /**
     * 手台名称
     */
    private String deviceName;
    /**
     * 组织机构id
     */
    private String phoneOrgGuid;
    /**
     * 组织机构名称
     */
    private String phoneOrgName;
    /**
     *  主辅班 ZFLX001主班  ZFLX002辅班
     */
    private String classType;

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getPhoneOrgGuid() {
        return phoneOrgGuid;
    }

    public void setPhoneOrgGuid(String phoneOrgGuid) {
        this.phoneOrgGuid = phoneOrgGuid;
    }

    public String getPhoneOrgName() {
        return phoneOrgName;
    }

    public void setPhoneOrgName(String phoneOrgName) {
        this.phoneOrgName = phoneOrgName;
    }

    public String getInterphoneGuid() {
        return interphoneGuid;
    }

    public void setInterphoneGuid(String interphoneGuid) {
        this.interphoneGuid = interphoneGuid;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

}
