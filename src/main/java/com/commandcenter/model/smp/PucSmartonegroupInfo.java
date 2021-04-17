package com.commandcenter.model.smp;
public class PucSmartonegroupInfo {
    private String smartoneGroupGuid;
    private String smartoneGroupNumber;
    private String pucId;
    private String smartoneGroupAlias;
    private String enableFlag;
    private String callPriority;
    private java.util.Date createDatetime;
    private java.util.Date updateTime;
    private String menberList;
    private Object version;
    public PucSmartonegroupInfo() {
        super();
    }
    public PucSmartonegroupInfo(String smartoneGroupGuid, String smartoneGroupNumber, String pucId, String smartoneGroupAlias, String enableFlag, String callPriority, java.util.Date createDatetime, java.util.Date updateTime, String menberList, Object version) {
        super();
        this.smartoneGroupGuid = smartoneGroupGuid;
        this.smartoneGroupNumber = smartoneGroupNumber;
        this.pucId = pucId;
        this.smartoneGroupAlias = smartoneGroupAlias;
        this.enableFlag = enableFlag;
        this.callPriority = callPriority;
        this.createDatetime = createDatetime;
        this.updateTime = updateTime;
        this.menberList = menberList;
        this.version = version;
    }
    public String getSmartoneGroupGuid() {
        return this.smartoneGroupGuid;
    }

    public void setSmartoneGroupGuid(String smartoneGroupGuid) {
        this.smartoneGroupGuid = smartoneGroupGuid;
    }

    public String getSmartoneGroupNumber() {
        return this.smartoneGroupNumber;
    }

    public void setSmartoneGroupNumber(String smartoneGroupNumber) {
        this.smartoneGroupNumber = smartoneGroupNumber;
    }

    public String getPucId() {
        return this.pucId;
    }

    public void setPucId(String pucId) {
        this.pucId = pucId;
    }

    public String getSmartoneGroupAlias() {
        return this.smartoneGroupAlias;
    }

    public void setSmartoneGroupAlias(String smartoneGroupAlias) {
        this.smartoneGroupAlias = smartoneGroupAlias;
    }

    public String getEnableFlag() {
        return this.enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getCallPriority() {
        return this.callPriority;
    }

    public void setCallPriority(String callPriority) {
        this.callPriority = callPriority;
    }

    public java.util.Date getCreateDatetime() {
        return this.createDatetime;
    }

    public void setCreateDatetime(java.util.Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMenberList() {
        return this.menberList;
    }

    public void setMenberList(String menberList) {
        this.menberList = menberList;
    }

    public Object getVersion() {
        return this.version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

}
