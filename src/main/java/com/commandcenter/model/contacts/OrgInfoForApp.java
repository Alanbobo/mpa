package com.commandcenter.model.contacts;

import com.commandcenter.common.utils.PinYinUtils;


public class OrgInfoForApp implements Comparable<OrgInfoForApp> {
    private String orgGuid;
    private String parentOrgGuid;
    private String orgName;
    private String orgIdentifier;
    private int dataType = 4;
    private int version;

    private String enableFlag;
    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }


    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
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

    public String getOrgIdentifier() {
        return orgIdentifier;
    }

    public void setOrgIdentifier(String orgIdentifier) {
        this.orgIdentifier = orgIdentifier;
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
    public int compareTo(OrgInfoForApp o) {
        String afterName = PinYinUtils.getHanziPinYin(o.getOrgName());
        String beforeName = PinYinUtils.getHanziPinYin(this.orgName);
        if(afterName == null){
            afterName = o.getOrgName();
        }
        if(beforeName == null){
            beforeName = this.orgName;
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
