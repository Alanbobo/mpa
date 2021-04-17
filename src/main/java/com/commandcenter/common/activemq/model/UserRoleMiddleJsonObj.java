package com.commandcenter.common.activemq.model;

import java.io.Serializable;

public class UserRoleMiddleJsonObj implements Serializable {
    private String user_guid;
    private String role_guid;

    public String getUser_guid() {
        return user_guid;
    }

    public void setUser_guid(String user_guid) {
        this.user_guid = user_guid;
    }

    public String getRole_guid() {
        return role_guid;
    }

    public void setRole_guid(String role_guid) {
        this.role_guid = role_guid;
    }
}
