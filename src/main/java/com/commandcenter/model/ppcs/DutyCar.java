package com.commandcenter.model.ppcs;

import java.util.List;

/**
 * @Author
 *  2019/1/18.
 */
public class DutyCar extends DutyPhone {
    /**
     * 车辆guid
     */
    private String carGuid;
    /**
     * 车辆编号
     */
    private String carName;
    /**
     * 车牌号码
     */
    private String carCode;
    /**
     * 组织机构id
     */
    private String carOrgGuid;
    /**
     * 组织机构名称
     */
    private String carOrgName;
    /**
     * 车辆绑定人员列表
     */
    private List<DutyStaff> dutyStaffList;

    public String getCarGuid() {
        return carGuid;
    }

    public void setCarGuid(String carGuid) {
        this.carGuid = carGuid;
    }

    public List<DutyStaff> getDutyStaffList() {
        return dutyStaffList;
    }

    public void setDutyStaffList(List<DutyStaff> dutyStaffList) {
        this.dutyStaffList = dutyStaffList;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public String getCarOrgGuid() {
        return carOrgGuid;
    }

    public void setCarOrgGuid(String carOrgGuid) {
        this.carOrgGuid = carOrgGuid;
    }

    public String getCarOrgName() {
        return carOrgName;
    }

    public void setCarOrgName(String carOrgName) {
        this.carOrgName = carOrgName;
    }
}
