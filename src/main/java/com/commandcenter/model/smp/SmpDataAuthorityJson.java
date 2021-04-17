package com.commandcenter.model.smp;

import java.util.List;

public class SmpDataAuthorityJson {
    private String roleGuid;
    private List<String> dataAuthorityArray;
    private List<String> systemGuidArray;
    private List<String> functionGuidArray;

    public String getRoleGuid() {
        return roleGuid;
    }

    public void setRoleGuid(String roleGuid) {
        this.roleGuid = roleGuid;
    }

    public List<String> getDataAuthorityArray() {
        return dataAuthorityArray;
    }

    public void setDataAuthorityArray(List<String> arraydataAuthorityArray) {
        this.dataAuthorityArray = arraydataAuthorityArray;
    }

    public List<String> getSystemGuidArray() {
        return systemGuidArray;
    }

    public void setSystemGuidArray(List<String> systemGuidArray) {
        this.systemGuidArray = systemGuidArray;
    }

    public List<String> getFunctionGuidArray() {
        return functionGuidArray;
    }

    public void setFunctionGuidArray(List<String> functionGuidArray) {
        this.functionGuidArray = functionGuidArray;
    }
}
