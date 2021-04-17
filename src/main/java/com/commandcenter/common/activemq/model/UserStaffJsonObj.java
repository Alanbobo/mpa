package com.commandcenter.common.activemq.model;

import java.io.Serializable;

/**
 * @author g20416
 * @date 2018-03-19
 * @describe 对应SMP用户警员绑定关系Json串的JsonObject
 */
public class UserStaffJsonObj implements Serializable {
    private String guid;
    private String userCode;
    private String staffGuid;
    private String staffCode;
    private String orgGuid;
    private String orgIdentifier;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getStaffGuid() {
        return staffGuid;
    }

    public void setStaffGuid(String staffGuid) {
        this.staffGuid = staffGuid;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getOrgIdentifier() {
        return orgIdentifier;
    }

    public void setOrgIdentifier(String orgIdentifier) {
        this.orgIdentifier = orgIdentifier;
    }
}
