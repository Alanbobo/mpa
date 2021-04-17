package com.commandcenter.model.contacts;

import com.commandcenter.common.utils.PinYinUtils;

/**
 * 通讯录展示中返回客户端的警员实体类
 *
 */
public class GroupForApp implements Comparable<GroupForApp>{

    private String smartonegroupGuid;
    private String groupId;
    private String cmdName;
    private String enableFlag;
    private String pucId;
    private String smartonegroupAlias;
    private String smartonegroupNumber;
    private String parentOrgGuid;
    private String orgName;
    private int dataType =3;
    private int version;
    private String systemId;


    public String getSmartonegroupGuid() {
        return smartonegroupGuid;
    }

    public void setSmartonegroupGuid(String smartonegroupGuid) {
        this.smartonegroupGuid = smartonegroupGuid;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCmdName() {
        return cmdName;
    }

    public void setCmdName(String cmdName) {
        this.cmdName = cmdName;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getPucId() {
        return pucId;
    }

    public void setPucId(String pucId) {
        this.pucId = pucId;
    }

    public String getSmartonegroupAlias() {
        return smartonegroupAlias;
    }

    public void setSmartonegroupAlias(String smartonegroupAlias) {
        this.smartonegroupAlias = smartonegroupAlias;
    }

    public String getSmartonegroupNumber() {
        return smartonegroupNumber;
    }

    public void setSmartonegroupNumber(String smartonegroupNumber) {
        this.smartonegroupNumber = smartonegroupNumber;
    }

    public String getParentOrgGuid() {
        return parentOrgGuid;
    }

    public void setParentOrgGuid(String parentOrgGuid) {
        this.parentOrgGuid = parentOrgGuid;
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

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    @Override
    public int compareTo(GroupForApp o) {
        String afterName = PinYinUtils.getHanziPinYin(o.getCmdName());
        String beforeName = PinYinUtils.getHanziPinYin(this.cmdName);
        if(afterName == null){
            afterName = o.getCmdName();
        }
        if(beforeName == null){
            beforeName = this.cmdName;
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
