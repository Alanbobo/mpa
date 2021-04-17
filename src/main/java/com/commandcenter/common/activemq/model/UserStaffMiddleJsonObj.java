package com.commandcenter.common.activemq.model;

import java.io.Serializable;

public class UserStaffMiddleJsonObj implements Serializable {
    private String user_guid;
    private String staff_guid;

    public String getUser_guid() {
        return user_guid;
    }

    public void setUser_guid(String user_guid) {
        this.user_guid = user_guid;
    }

    public String getStaff_guid() {
        return staff_guid;
    }

    public void setStaff_guid(String staff_guid) {
        this.staff_guid = staff_guid;
    }
}
