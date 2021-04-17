package com.commandcenter.model.contacts;

import java.util.Date;

public class MpatContactsChange {
    private String guid;
    private int dataType;
    private String jsonData;
    private int version;
    private String action;
    private Date createTime;
    public MpatContactsChange() {
        super();
    }
    public MpatContactsChange(String guid, int dataType, String jsonData, int version, String action, Date createTime) {
        super();
        this.guid = guid;
        this.dataType = dataType;
        this.jsonData = jsonData;
        this.version = version;
        this.action = action;
        this.createTime = createTime;
    }
    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Object getDataType() {
        return this.dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public Object getJsonData() {
        return this.jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public Object getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
