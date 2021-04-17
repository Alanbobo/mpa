package com.commandcenter.model.dictmodel;
public class SmptSystemInfo {
    private String guid;
    private String systemName;
    private String systemNo;
    private String systemCode;
    private String status;
    private String systemType;
    private String systemPath;
    private int sort;
    private java.util.Date createTime;
    private String createUser;
    private java.util.Date updateTime;
    private String updateUser;
    private String initialization;
    private String sysStatus;
    private int version;

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public SmptSystemInfo() {
        super();
    }
    public SmptSystemInfo(String guid,String systemName,String systemNo,String systemCode,String status,String systemType,String systemPath,int sort,java.util.Date createTime,String createUser,java.util.Date updateTime,String updateUser,String initialization,String sysStatus) {
        super();
        this.guid = guid;
        this.systemName = systemName;
        this.systemNo = systemNo;
        this.systemCode = systemCode;
        this.status = status;
        this.systemType = systemType;
        this.systemPath = systemPath;
        this.sort = sort;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.initialization = initialization;
        this.sysStatus = sysStatus;
    }
    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSystemName() {
        return this.systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemNo() {
        return this.systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public String getSystemCode() {
        return this.systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSystemType() {
        return this.systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getSystemPath() {
        return this.systemPath;
    }

    public void setSystemPath(String systemPath) {
        this.systemPath = systemPath;
    }

    public int getSort() {
        return this.sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getInitialization() {
        return this.initialization;
    }

    public void setInitialization(String initialization) {
        this.initialization = initialization;
    }

    public String getSysStatus() {
        return this.sysStatus;
    }

    public void setSysStatus(String sysStatus) {
        this.sysStatus = sysStatus;
    }

}
