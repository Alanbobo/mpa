package com.commandcenter.model.smp.vo;

import com.commandcenter.common.utils.MyUtil;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * @author r25437
 * @create 2018-09-04 14:10
 * @desc Staff提取vo对象
 **/
public class StaffModel {
    private static Logger logger = Logger.getLogger(StaffModel.class);


    private String guid;

    private String staffCode;

    private String staffName;

    private String staffType;

    private String category;

    private String staffStatus;

    private String orgIdentifier;

    private String fromOrgIdentifier;

    private String orgName;

    private String fromorgName;

    private Date startTime;

    private Date endTime;

    private String interphoneGuid;

    private String bindType;

    private String bindConfirm;

    private String seq;

    private String position;

    private String sex;

    private String idNo;

    private String telephone;

    private String mobile;

    private String address;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String remark;

    private String xjType;

    private String isleader;

    private byte[] image;

    public StaffForAppModel parseToAppModel() {
        String val = "";

        StaffForAppModel staffReturn = new StaffForAppModel();

        staffReturn.setStaffGuid(MyUtil.checkNullString(this.getGuid(), val));
        staffReturn.setPhoneNumber(MyUtil.checkNullString(this.getMobile(), val));
        staffReturn.setOrganization(MyUtil.checkNullString(this.getOrgName(), val));

        if (this.getPosition() != null) {
            staffReturn.setRole(MyUtil.checkNullString(this.getPosition(), val));
        } else {
            logger.info("返回给app端的role为空串");
            staffReturn.setRole("");
        }

        staffReturn.setStaffCode(MyUtil.checkNullString(this.getStaffCode(), val));
        staffReturn.setRealName(MyUtil.checkNullString(this.getStaffName(), val));
        staffReturn.setIsLeader(MyUtil.checkNullString(this.getIsleader(), val));

        staffReturn.setLoginTime(val);
        return staffReturn;

    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus;
    }

    public String getOrgIdentifier() {
        return orgIdentifier;
    }

    public void setOrgIdentifier(String orgIdentifier) {
        this.orgIdentifier = orgIdentifier;
    }

    public String getFromOrgIdentifier() {
        return fromOrgIdentifier;
    }

    public void setFromOrgIdentifier(String fromOrgIdentifier) {
        this.fromOrgIdentifier = fromOrgIdentifier;
    }


    public String getFromorgName() {
        return fromorgName;
    }

    public void setFromorgName(String fromorgName) {
        this.fromorgName = fromorgName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getInterphoneGuid() {
        return interphoneGuid;
    }

    public void setInterphoneGuid(String interphoneGuid) {
        this.interphoneGuid = interphoneGuid;
    }

    public String getBindType() {
        return bindType;
    }

    public void setBindType(String bindType) {
        this.bindType = bindType;
    }

    public String getBindConfirm() {
        return bindConfirm;
    }

    public void setBindConfirm(String bindConfirm) {
        this.bindConfirm = bindConfirm;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getXjType() {
        return xjType;
    }

    public void setXjType(String xjType) {
        this.xjType = xjType;
    }

    public String getIsleader() {
        return isleader;
    }

    public void setIsleader(String isleader) {
        this.isleader = isleader;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = String.valueOf(seq);
    }
}
