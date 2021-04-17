package com.commandcenter.model.ppcs;

/**
 * @Author
 *  2019/1/18.
 */
public class DutyStaff extends DutyPhone {
    /**
     * //人员id
     */
    private String staffGuid;
    /**
     * //人员姓名
     */
    private String staffName;
    /**
     * //人员警号（编号）
     */
    private String staffCode;
    /**
     * //手机号
     */
    private String mobile;
    /**
     * //座机号
     */
    private String phone;
    /**
     * //组织机构id
     */
    private String staffOrgGuid;
    /**
     * //组织机构名称
     */
    private String staffOrgName;

    public String getStaffOrgGuid() {
        return staffOrgGuid;
    }

    public void setStaffOrgGuid(String staffOrgGuid) {
        this.staffOrgGuid = staffOrgGuid;
    }

    public String getStaffOrgName() {
        return staffOrgName;
    }

    public void setStaffOrgName(String staffOrgName) {
        this.staffOrgName = staffOrgName;
    }

    public String getStaffGuid() {
        return staffGuid;
    }

    public void setStaffGuid(String staffGuid) {
        this.staffGuid = staffGuid;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
