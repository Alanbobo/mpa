package com.commandcenter.model.smp;

import java.util.Date;

public class SmpThrDictExt {
    private String guid;

    private String dictGuid;

    private String thrDictType;

    private String thrPeerCode;

    private String thrSysName;

    private String sort;

    private Date createTime;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getDictGuid() {
        return dictGuid;
    }

    public void setDictGuid(String dictGuid) {
        this.dictGuid = dictGuid == null ? null : dictGuid.trim();
    }

    public String getThrDictType() {
        return thrDictType;
    }

    public void setThrDictType(String thrDictType) {
        this.thrDictType = thrDictType == null ? null : thrDictType.trim();
    }

    public String getThrPeerCode() {
        return thrPeerCode;
    }

    public void setThrPeerCode(String thrPeerCode) {
        this.thrPeerCode = thrPeerCode == null ? null : thrPeerCode.trim();
    }

    public String getThrSysName() {
        return thrSysName;
    }

    public void setThrSysName(String thrSysName) {
        this.thrSysName = thrSysName == null ? null : thrSysName.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}