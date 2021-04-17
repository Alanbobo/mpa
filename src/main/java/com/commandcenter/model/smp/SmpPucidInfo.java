package com.commandcenter.model.smp;

public class SmpPucidInfo {
    private String pucId;

    private String pucName;

    private String pucParentid;

    private String pucSysid;

    private String guid;

    public String getPucId() {
        return pucId;
    }

    public void setPucId(String pucId) {
        this.pucId = pucId == null ? null : pucId.trim();
    }

    public String getPucName() {
        return pucName;
    }

    public void setPucName(String pucName) {
        this.pucName = pucName == null ? null : pucName.trim();
    }

    public String getPucParentid() {
        return pucParentid;
    }

    public void setPucParentid(String pucParentid) {
        this.pucParentid = pucParentid == null ? null : pucParentid.trim();
    }

    public String getPucSysid() {
        return pucSysid;
    }

    public void setPucSysid(String pucSysid) {
        this.pucSysid = pucSysid == null ? null : pucSysid.trim();
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }
}