package com.commandcenter.model.dictmodel;
public class SmptLanguage {
    private String guid;
    private String languageCode;//对应的语言的编码ZH_CN,EN,FRECH
    private String describeName;//说明（中文 english French等）
    private int sort;
    private String iconPath;
    private String status;//状态Y启用N停用
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private int version;

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public SmptLanguage() {
        super();
    }
    public SmptLanguage(String guid,String languageCode,String describeName,int sort,String iconPath,String status,java.util.Date createTime,java.util.Date updateTime) {
        super();
        this.guid = guid;
        this.languageCode = languageCode;
        this.describeName = describeName;
        this.sort = sort;
        this.iconPath = iconPath;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getLanguageCode() {
        return this.languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getDescribeName() {
        return this.describeName;
    }

    public void setDescribeName(String describeName) {
        this.describeName = describeName;
    }

    public int getSort() {
        return this.sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getIconPath() {
        return this.iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

}
