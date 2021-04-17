package com.commandcenter.model.smp;

import java.util.List;
import java.util.Map;

public class SmpRoleInfoMqJson {
    private String code;
    private List<Map> funcInfoList;
    private List<Map> dataInfoList;
    private String guid;
    private String isvalid;
    private String name;
    private String systemNo;
    private String enableFlag;
    private String roleAttr;

    public String getRoleAttr() {
        return roleAttr;
    }

    public void setRoleAttr(String roleAttr) {
        this.roleAttr = roleAttr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Map> getFuncInfoList() {
        return funcInfoList;
    }

    public void setFuncInfoList(List<Map> funcInfoList) {
        this.funcInfoList = funcInfoList;
    }

    public List<Map> getDataInfoList() {
        return dataInfoList;
    }

    public void setDataInfoList(List<Map> dataInfoList) {
        this.dataInfoList = dataInfoList;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }
}
