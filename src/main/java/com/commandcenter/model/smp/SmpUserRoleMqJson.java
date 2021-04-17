package com.commandcenter.model.smp;

import java.util.List;
import java.util.Map;

public class SmpUserRoleMqJson {
    private String customerType;
    private String isvalid;
    private String userCode;
    private String userGuid;
    private List<Map> roleInfo;

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public List<Map> getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(List<Map> roleInfo) {
        this.roleInfo = roleInfo;
    }



}
