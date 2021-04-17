package com.commandcenter.model.smp;

import java.util.Date;

public class SmpSapInfo {
    private String sapGuid;

    private String pucId;

    private String systemId;

    private String sapAlias;

    private String userList;

    private String defaultUser;

    private String groupList;

    private String ownerGatewayguid;

    private String applicationMode;

    private String sapNo;

    private String slotId;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    private String remark;

    private String isOnline;

    private String sapType;

    private String ruleType;

    private String versionSeq;

    private String enableFlag;

    public String getSapGuid() {
        return sapGuid;
    }

    public void setSapGuid(String sapGuid) {
        this.sapGuid = sapGuid == null ? null : sapGuid.trim();
    }

    public String getPucId() {
        return pucId;
    }

    public void setPucId(String pucId) {
        this.pucId = pucId == null ? null : pucId.trim();
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getSapAlias() {
        return sapAlias;
    }

    public void setSapAlias(String sapAlias) {
        this.sapAlias = sapAlias == null ? null : sapAlias.trim();
    }

    public String getUserList() {
        return userList;
    }

    public void setUserList(String userList) {
        this.userList = userList == null ? null : userList.trim();
    }

    public String getDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(String defaultUser) {
        this.defaultUser = defaultUser == null ? null : defaultUser.trim();
    }

    public String getGroupList() {
        return groupList;
    }

    public void setGroupList(String groupList) {
        this.groupList = groupList == null ? null : groupList.trim();
    }

    public String getOwnerGatewayguid() {
        return ownerGatewayguid;
    }

    public void setOwnerGatewayguid(String ownerGatewayguid) {
        this.ownerGatewayguid = ownerGatewayguid == null ? null : ownerGatewayguid.trim();
    }

    public String getApplicationMode() {
        return applicationMode;
    }

    public void setApplicationMode(String applicationMode) {
        this.applicationMode = applicationMode == null ? null : applicationMode.trim();
    }

    public String getSapNo() {
        return sapNo;
    }

    public void setSapNo(String sapNo) {
        this.sapNo = sapNo == null ? null : sapNo.trim();
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId == null ? null : slotId.trim();
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline == null ? null : isOnline.trim();
    }

    public String getSapType() {
        return sapType;
    }

    public void setSapType(String sapType) {
        this.sapType = sapType == null ? null : sapType.trim();
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType == null ? null : ruleType.trim();
    }

    public String getVersionSeq() {
        return versionSeq;
    }

    public void setVersionSeq(String versionSeq) {
        this.versionSeq = versionSeq == null ? null : versionSeq.trim();
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag == null ? null : enableFlag.trim();
    }
}