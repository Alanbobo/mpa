package com.commandcenter.model.webmodel;

import java.io.Serializable;

public class KickEntity implements Serializable {
    private String userCode;
    private String staffName;
    private String orgName;
    private String guid;

    public String getGuid() {
        return guid;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getStaffName() {
        return staffName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
