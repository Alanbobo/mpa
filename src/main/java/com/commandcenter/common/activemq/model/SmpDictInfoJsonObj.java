package com.commandcenter.common.activemq.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SmpDictInfoJsonObj implements Serializable {
    private String guid;

    private String parentGuid;

    private String dictInfoKey;

    private String dictInfoCode;

    private String systemGuid;

    private String level;

    private String status;

    private Integer sort;

    private String treePath;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private String describe;

    private String dictType;
    private List<SmpDictValueJsonObj> dict_value;
    private int version;

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public List<SmpDictValueJsonObj> getDict_value() {
        return dict_value;
    }

    public void setDict_value(List<SmpDictValueJsonObj> dict_value) {
        this.dict_value = dict_value;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getParentGuid() {
        return parentGuid;
    }

    public void setParentGuid(String parentGuid) {
        this.parentGuid = parentGuid == null ? null : parentGuid.trim();
    }

    public String getDictInfoKey() {
        return dictInfoKey;
    }

    public void setDictInfoKey(String dictInfoKey) {
        this.dictInfoKey = dictInfoKey == null ? null : dictInfoKey.trim();
    }

    public String getDictInfoCode() {
        return dictInfoCode;
    }

    public void setDictInfoCode(String dictInfoCode) {
        this.dictInfoCode = dictInfoCode == null ? null : dictInfoCode.trim();
    }

    public String getSystemGuid() {
        return systemGuid;
    }

    public void setSystemGuid(String systemGuid) {
        this.systemGuid = systemGuid == null ? null : systemGuid.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath == null ? null : treePath.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }
}