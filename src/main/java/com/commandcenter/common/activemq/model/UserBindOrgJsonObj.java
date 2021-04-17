package com.commandcenter.common.activemq.model;

import java.io.Serializable;

public class UserBindOrgJsonObj implements Serializable {
    private int enable_flag;
    private int count;
    private String update_time;
    private String org_guid;
    private String guid;

    public int getEnable_flag() {
        return enable_flag;
    }

    public void setEnable_flag(int enable_flag) {
        this.enable_flag = enable_flag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getOrg_guid() {
        return org_guid;
    }

    public void setOrg_guid(String org_guid) {
        this.org_guid = org_guid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
