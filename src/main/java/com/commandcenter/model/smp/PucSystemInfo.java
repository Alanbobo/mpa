package com.commandcenter.model.smp;
public class PucSystemInfo {
    private int dataType =6;
    private String guid;
    private String pucId;
    private String systemId;
    private String systemAlias;
    private Object systemType;
    private Object versionSeq;
    private String enableFlag;
    private String createuser;
    private java.util.Date createtime;
    private String updateuser;
    private java.util.Date updatetime;
    private String remark;
    private Object version;
    private String orgIdentifier;
    private String syncType;
    public PucSystemInfo() {
        super();
    }
    public PucSystemInfo(String guid,String pucId,String systemId,String systemAlias,Object systemType,Object versionSeq,String enableFlag,String createuser,java.util.Date createtime,String updateuser,java.util.Date updatetime,String remark,Object version,String orgIdentifier,String syncType) {
        super();
        this.guid = guid;
        this.pucId = pucId;
        this.systemId = systemId;
        this.systemAlias = systemAlias;
        this.systemType = systemType;
        this.versionSeq = versionSeq;
        this.enableFlag = enableFlag;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
        this.remark = remark;
        this.version = version;
        this.orgIdentifier = orgIdentifier;
        this.syncType = syncType;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    public String getSystemAlias() {
        return this.systemAlias;
    }

    public void setSystemAlias(String systemAlias) {
        this.systemAlias = systemAlias;
    }

    public Object getSystemType() {
        return this.systemType;
    }

    public void setSystemType(Object systemType) {
        this.systemType = systemType;
    }

    public Object getVersionSeq() {
        return this.versionSeq;
    }

    public void setVersionSeq(Object versionSeq) {
        this.versionSeq = versionSeq;
    }

    public String getEnableFlag() {
        return this.enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getCreateuser() {
        return this.createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public java.util.Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(java.util.Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuser() {
        return this.updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public java.util.Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(java.util.Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Object getVersion() {
        return this.version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

    public String getOrgIdentifier() {
        return this.orgIdentifier;
    }

    public void setOrgIdentifier(String orgIdentifier) {
        this.orgIdentifier = orgIdentifier;
    }

    public String getSyncType() {
        return this.syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }

}
