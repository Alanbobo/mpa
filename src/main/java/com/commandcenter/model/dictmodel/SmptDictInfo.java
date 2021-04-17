package com.commandcenter.model.dictmodel;
public class SmptDictInfo {
    private String guid;//主键
    private String parentGuid;//父主键
    private String dictInfoKey;//数据字典类型编码或数据字典项编码，用于通过它来查询数据字典值信息
    private String dictInfoCode;//数据字典code 系统自动生成
    private String systemGuid;//系统guid
    private String level;//字典层级
    private String status;//Y启用N停用
    private Object sort;//排序
    private String treePath;//字典层级tree路径 根据字典父级path和key叠加，用于树形结构查询
    private java.util.Date createTime;//创建时间
    private String createUser;//创建人
    private java.util.Date updateTime;//修改时间
    private String updateUser;//修改人
    private String describe;//描述
    private String dictType;//数据字典类型 TYPE为数据字典类型,ITEM为数据字典项
    public SmptDictInfo() {
        super();
    }
    public SmptDictInfo(String guid,String parentGuid,String dictInfoKey,String dictInfoCode,String systemGuid,String level,String status,Object sort,String treePath,java.util.Date createTime,String createUser,java.util.Date updateTime,String updateUser,String describe,String dictType) {
        super();
        this.guid = guid;
        this.parentGuid = parentGuid;
        this.dictInfoKey = dictInfoKey;
        this.dictInfoCode = dictInfoCode;
        this.systemGuid = systemGuid;
        this.level = level;
        this.status = status;
        this.sort = sort;
        this.treePath = treePath;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.describe = describe;
        this.dictType = dictType;
    }
    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getParentGuid() {
        return this.parentGuid;
    }

    public void setParentGuid(String parentGuid) {
        this.parentGuid = parentGuid;
    }

    public String getDictInfoKey() {
        return this.dictInfoKey;
    }

    public void setDictInfoKey(String dictInfoKey) {
        this.dictInfoKey = dictInfoKey;
    }

    public String getDictInfoCode() {
        return this.dictInfoCode;
    }

    public void setDictInfoCode(String dictInfoCode) {
        this.dictInfoCode = dictInfoCode;
    }

    public String getSystemGuid() {
        return this.systemGuid;
    }

    public void setSystemGuid(String systemGuid) {
        this.systemGuid = systemGuid;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getSort() {
        return this.sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public String getTreePath() {
        return this.treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getDescribe() {
        return this.describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDictType() {
        return this.dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

}
