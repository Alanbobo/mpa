package com.commandcenter.common.activemq.model;

import java.io.Serializable;

public class StaffUpdateOrgJsonObj implements Serializable {
    private String org_guid;
    private String org_identifier;
    private String guid;

    public String getOrg_guid() {
        return org_guid;
    }

    public void setOrg_guid(String org_guid) {
        this.org_guid = org_guid;
    }

    public String getOrg_identifier() {
        return org_identifier;
    }

    public void setOrg_identifier(String org_identifier) {
        this.org_identifier = org_identifier;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
