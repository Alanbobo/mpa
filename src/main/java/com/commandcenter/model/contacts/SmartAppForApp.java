package com.commandcenter.model.contacts;

import com.commandcenter.common.utils.PinYinUtils;

import java.util.Date;

public class SmartAppForApp implements Comparable<SmartAppForApp>{

    private String isOnline;
    private String guid;
    private String orgIdentifier;
    private String pucId;
    private String staffGuid;
    private String dispatcherAccount;
    private String dispatcherName;
    private String dispatcherNum;
    private Object onlineStatus;
    private String parentOrgGuid;
    private int version;
    private int dataType=1;
    private String orgName;
    private String enableFlag;


    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }


    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOrgIdentifier() {
        return orgIdentifier;
    }

    public void setOrgIdentifier(String orgIdentifier) {
        this.orgIdentifier = orgIdentifier;
    }

    public String getPucId() {
        return pucId;
    }

    public void setPucId(String pucId) {
        this.pucId = pucId;
    }

    public String getStaffGuid() {
        return staffGuid;
    }

    public void setStaffGuid(String staffGuid) {
        this.staffGuid = staffGuid;
    }

    public String getDispatcherAccount() {
        return dispatcherAccount;
    }

    public void setDispatcherAccount(String dispatcherAccount) {
        this.dispatcherAccount = dispatcherAccount;
    }

    public String getDispatcherName() {
        return dispatcherName;
    }

    public void setDispatcherName(String dispatcherName) {
        this.dispatcherName = dispatcherName;
    }

    public String getDispatcherNum() {
        return dispatcherNum;
    }

    public void setDispatcherNum(String dispatcherNum) {
        this.dispatcherNum = dispatcherNum;
    }

    public Object getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Object onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getParentOrgGuid() {
        return parentOrgGuid;
    }

    public void setParentOrgGuid(String parentOrgGuid) {
        this.parentOrgGuid = parentOrgGuid;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public int compareTo(SmartAppForApp o) {
        String afterName = PinYinUtils.getHanziPinYin(o.getDispatcherName());
        String beforeName = PinYinUtils.getHanziPinYin(this.dispatcherName);
        if(afterName == null){
            afterName = o.getDispatcherName();
        }
        if(beforeName == null){
            beforeName = this.dispatcherName;
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
