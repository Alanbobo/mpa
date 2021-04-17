package com.commandcenter.model.ppcs;

import java.util.List;

/**
 * @Author
 *  2019/1/18.
 */
public class DutyLeader {
    /**
     * 领导人员列表
     */
    private List<DutyPhone> dutyPhoneList;
    /**
     * 领导手台人员列表
     */
    private List<DutyStaff> dutyStaffList;
    /**
     * 领导车辆列表
     */
    private List<DutyCar> dutyCarList;

    public List<DutyPhone> getDutyPhoneList() {
        return dutyPhoneList;
    }

    public void setDutyPhoneList(List<DutyPhone> dutyPhoneList) {
        this.dutyPhoneList = dutyPhoneList;
    }

    public List<DutyStaff> getDutyStaffList() {
        return dutyStaffList;
    }

    public void setDutyStaffList(List<DutyStaff> dutyStaffList) {
        this.dutyStaffList = dutyStaffList;
    }

    public List<DutyCar> getDutyCarList() {
        return dutyCarList;
    }

    public void setDutyCarList(List<DutyCar> dutyCarList) {
        this.dutyCarList = dutyCarList;
    }
}
