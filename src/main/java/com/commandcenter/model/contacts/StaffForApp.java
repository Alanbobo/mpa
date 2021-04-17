package com.commandcenter.model.contacts;

import com.commandcenter.common.utils.PinYinUtils;

import java.util.Date;

/**
 * 通讯录展示中返回客户端的警员实体类
 *
 */
public class StaffForApp implements Comparable<StaffForApp> {
    private String staffCode;
    private String staffName;
    private int dataType =0;
    private String orgIdentifier;
    private String staffGuid;
    private String parentOrgGuid;
    private int version;
    private String orgGuid;
    private String enableFlag;
    private String phoneNum;
    private String position;


    private String orgRemark;
    public String getOrgRemark() {
        return orgRemark;
    }

    public void setOrgRemark(String orgRemark) {
        this.orgRemark = orgRemark;
    }




    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getOrgIdentifier() {
        return orgIdentifier;
    }

    public void setOrgIdentifier(String orgIdentifier) {
        this.orgIdentifier = orgIdentifier;
    }

    public String getStaffGuid() {
        return staffGuid;
    }

    public void setStaffGuid(String staffGuid) {
        this.staffGuid = staffGuid;
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

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public int compareTo(StaffForApp o) {
        String afterName = PinYinUtils.getHanziPinYin(o.getStaffName());
        String beforeName = PinYinUtils.getHanziPinYin(this.staffName);
        if(afterName == null){
            afterName = o.getStaffName();
        }
        if(beforeName == null){
            beforeName = this.staffName;
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
