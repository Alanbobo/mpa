package com.commandcenter.model.puctomq;
public class PucOnlineData {
    private String guid;
    private String id;
    private String pucId;
    private String systemId;
    private String deviceId;
    private String deviceType;
    private String receiveTime;
    private String isOnline;
    private String caseId;
    private String pdtState;
    private String isLock;
    private String sourceType;
    private String subSapGuid;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private String remark;
    public PucOnlineData() {
        super();
    }
    public PucOnlineData(String guid, String id, String pucId, String systemId, String deviceId, String deviceType, String receiveTime, String isOnline, String caseId, String pdtState, String isLock, String sourceType, String subSapGuid, java.util.Date createTime, java.util.Date updateTime, String remark) {
        super();
        this.guid = guid;
        this.id = id;
        this.pucId = pucId;
        this.systemId = systemId;
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.receiveTime = receiveTime;
        this.isOnline = isOnline;
        this.caseId = caseId;
        this.pdtState = pdtState;
        this.isLock = isLock;
        this.sourceType = sourceType;
        this.subSapGuid = subSapGuid;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.remark = remark;
    }
    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPucId() {
        return this.pucId;
    }

    public void setPucId(String pucId) {
        this.pucId = pucId;
    }

    public String getSystemId() {
        return this.systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getReceiveTime() {
        return this.receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getIsOnline() {
        return this.isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public String getCaseId() {
        return this.caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getPdtState() {
        return this.pdtState;
    }

    public void setPdtState(String pdtState) {
        this.pdtState = pdtState;
    }

    public String getIsLock() {
        return this.isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSubSapGuid() {
        return this.subSapGuid;
    }

    public void setSubSapGuid(String subSapGuid) {
        this.subSapGuid = subSapGuid;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
