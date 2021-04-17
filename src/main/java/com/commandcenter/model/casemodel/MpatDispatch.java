package com.commandcenter.model.casemodel;

import com.commandcenter.common.utils.DateUtil;
import com.commandcenter.model.smp.SmptSmartappInfo;

import java.util.ArrayList;
import java.util.List;

public class MpatDispatch {
    private String id;
    /**
     * 警情id
     */
    private String caseId;
    /**
     * 调派到警员的user_guid
     */
    private String userGuid;
    /**
     * 调派类型   1调派到单位（可选择接收） 2 调派到个人（必须处理）  3 取消调派
     */
    private String type;
    private String dispatchDesc;
    private String creator;
    private java.util.Date createTime;
    private String remark;
    private String orgGuid;
    /**
     * 当前状态   （出发、到达等）
     */
    private String status;
    /**
     * 调派到警员的staff_guid
     */
    private String staffGuid;
    private String iscancel;
    private String staffName;
    private String telephone;
    private String mobile;
    private SmptSmartappInfo smptSmartappInfo;
    public MpatDispatch() {
        super();
    }
    public MpatDispatch(String id,String caseId,String userGuid,String type,String dispatchDesc,String creator,java.util.Date createTime,String remark,String orgGuid,String status,String staffGuid,String iscancel) {
        super();
        this.id = id;
        this.caseId = caseId;
        this.userGuid = userGuid;
        this.type = type;
        this.dispatchDesc = dispatchDesc;
        this.creator = creator;
        this.createTime = createTime;
        this.remark = remark;
        this.orgGuid = orgGuid;
        this.status = status;
        this.staffGuid = staffGuid;
        this.iscancel = iscancel;
    }

    public SmptSmartappInfo getSmptSmartappInfo() {
        return smptSmartappInfo;
    }

    public void setSmptSmartappInfo(SmptSmartappInfo smptSmartappInfo) {
        this.smptSmartappInfo = smptSmartappInfo;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseId() {
        return this.caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getUserGuid() {
        return this.userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDispatchDesc() {
        return this.dispatchDesc;
    }

    public void setDispatchDesc(String dispatchDesc) {
        this.dispatchDesc = dispatchDesc;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrgGuid() {
        return this.orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStaffGuid() {
        return this.staffGuid;
    }

    public void setStaffGuid(String staffGuid) {
        this.staffGuid = staffGuid;
    }

    public String getIscancel() {
        return this.iscancel;
    }

    public void setIscancel(String iscancel) {
        this.iscancel = iscancel;
    }

    public static List<MpatDispatch> parseDispathsFromVcsMq(List<DispatchModelForVcsMq> dispatchModelForVcsMqs) {
        if (dispatchModelForVcsMqs == null) {
            return null;
        }
        List<MpatDispatch> dispatchModels = new ArrayList<MpatDispatch>();
        for (DispatchModelForVcsMq dispatchModelForVcsMq : dispatchModelForVcsMqs) {
            MpatDispatch dispatchModel = new MpatDispatch();
            dispatchModel.setStaffGuid(dispatchModelForVcsMq.getStaff_guid());
            dispatchModel.setOrgGuid(dispatchModelForVcsMq.getOrg_guid());
            dispatchModel.setType(dispatchModelForVcsMq.getType());
            //if (DateUtil.isDateString(dispatchModelForVcsMq.getCreate_time())) {
            dispatchModel.setCreateTime(DateUtil.parse(dispatchModelForVcsMq.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
            //}
            dispatchModels.add(dispatchModel);
        }
        return dispatchModels;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
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
}
