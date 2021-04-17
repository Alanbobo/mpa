package com.commandcenter.model.smp;
public class SmptSmartappInfo {
    private String guid;
    private String createUser;
    private java.util.Date createTime;
    private String updateUser;
    private java.util.Date updateTime;
    private String orgIdentifier;//授权组织
    private String pucId;//puc编号
    private String staffGuid;//警员guid
    private String dispatcherAccount;//调度员账号
    private String dispatcherPwd;//调度员用户密码
    private String dispatcherName;//调度员用户名称
    private String dispatcherNum;//调度员用户号码
    private String role;//角色1：普通用户2：高级用户3：超级用户
    private Object onlineStatus;//是否在线
    private Object enableFlag;//删除标志位   1标识可用  0标识删除
    private String orgGuid;//组织机构guid
    private Object version;
    public SmptSmartappInfo() {
        super();
    }
    public SmptSmartappInfo(String guid,String createUser,java.util.Date createTime,String updateUser,java.util.Date updateTime,String orgIdentifier,String pucId,String staffGuid,String dispatcherAccount,String dispatcherPwd,String dispatcherName,String dispatcherNum,String role,Object onlineStatus,Object enableFlag,String orgGuid,Object version) {
        super();
        this.guid = guid;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.orgIdentifier = orgIdentifier;
        this.pucId = pucId;
        this.staffGuid = staffGuid;
        this.dispatcherAccount = dispatcherAccount;
        this.dispatcherPwd = dispatcherPwd;
        this.dispatcherName = dispatcherName;
        this.dispatcherNum = dispatcherNum;
        this.role = role;
        this.onlineStatus = onlineStatus;
        this.enableFlag = enableFlag;
        this.orgGuid = orgGuid;
        this.version = version;
    }
    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrgIdentifier() {
        return this.orgIdentifier;
    }

    public void setOrgIdentifier(String orgIdentifier) {
        this.orgIdentifier = orgIdentifier;
    }

    public String getPucId() {
        return this.pucId;
    }

    public void setPucId(String pucId) {
        this.pucId = pucId;
    }

    public String getStaffGuid() {
        return this.staffGuid;
    }

    public void setStaffGuid(String staffGuid) {
        this.staffGuid = staffGuid;
    }

    public String getDispatcherAccount() {
        return this.dispatcherAccount;
    }

    public void setDispatcherAccount(String dispatcherAccount) {
        this.dispatcherAccount = dispatcherAccount;
    }

    public String getDispatcherPwd() {
        return this.dispatcherPwd;
    }

    public void setDispatcherPwd(String dispatcherPwd) {
        this.dispatcherPwd = dispatcherPwd;
    }

    public String getDispatcherName() {
        return this.dispatcherName;
    }

    public void setDispatcherName(String dispatcherName) {
        this.dispatcherName = dispatcherName;
    }

    public String getDispatcherNum() {
        return this.dispatcherNum;
    }

    public void setDispatcherNum(String dispatcherNum) {
        this.dispatcherNum = dispatcherNum;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Object getOnlineStatus() {
        return this.onlineStatus;
    }

    public void setOnlineStatus(Object onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Object getEnableFlag() {
        return this.enableFlag;
    }

    public void setEnableFlag(Object enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getOrgGuid() {
        return this.orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public Object getVersion() {
        return this.version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

}
