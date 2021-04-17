package com.commandcenter.common.activemq.model;

import java.io.Serializable;
import java.util.Date;

public class SmpDictValueJsonObj implements Serializable {
    private String guid;

    private String value;

    private String languageGuid;

    private String dictGuid;

    private Date createTime;

    private Integer sort;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getLanguageGuid() {
        return languageGuid;
    }

    public void setLanguageGuid(String languageGuid) {
        this.languageGuid = languageGuid == null ? null : languageGuid.trim();
    }

    public String getDictGuid() {
        return dictGuid;
    }

    public void setDictGuid(String dictGuid) {
        this.dictGuid = dictGuid == null ? null : dictGuid.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}