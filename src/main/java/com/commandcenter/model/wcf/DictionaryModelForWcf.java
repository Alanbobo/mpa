package com.commandcenter.model.wcf;

/**
 * @author r25437
 * @create 2018-09-07 11:27
 * @desc DictionaryModelForWcf
 **/
public class DictionaryModelForWcf {
    private String Category;
    private String Extends;
    private String ID;
    private String Icon;
    private String Icon2;
    private boolean IsDefault;
    private String Name;
    private String Seq;
    private String Type;
    private String Value;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getExtends() {
        return Extends;
    }

    public void setExtends(String anExtends) {
        Extends = anExtends;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getIcon2() {
        return Icon2;
    }

    public void setIcon2(String icon2) {
        Icon2 = icon2;
    }

    public boolean isDefault() {
        return IsDefault;
    }

    public void setDefault(boolean aDefault) {
        IsDefault = aDefault;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSeq() {
        return Seq;
    }

    public void setSeq(String seq) {
        Seq = seq;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
