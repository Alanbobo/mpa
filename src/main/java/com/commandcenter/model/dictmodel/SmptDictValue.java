package com.commandcenter.model.dictmodel;
public class SmptDictValue {
    private String guid;//字典值id
    private String value;//字典值
    private String languageGuid;//对应语言id
    private String dictGuid;//归属字典分类id
    private Object createTime;//创建时间
    private Object sort;//排序
    public SmptDictValue() {
        super();
    }
    public SmptDictValue(String guid,String value,String languageGuid,String dictGuid,Object createTime,Object sort) {
        super();
        this.guid = guid;
        this.value = value;
        this.languageGuid = languageGuid;
        this.dictGuid = dictGuid;
        this.createTime = createTime;
        this.sort = sort;
    }
    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLanguageGuid() {
        return this.languageGuid;
    }

    public void setLanguageGuid(String languageGuid) {
        this.languageGuid = languageGuid;
    }

    public String getDictGuid() {
        return this.dictGuid;
    }

    public void setDictGuid(String dictGuid) {
        this.dictGuid = dictGuid;
    }

    public Object getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getSort() {
        return this.sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

}
