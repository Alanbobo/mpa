package com.commandcenter.model.wcf;

import com.commandcenter.common.utils.MyUtil;
import com.commandcenter.model.smp.vo.StaffForAppModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author r25437
 * @create 2018-09-07 11:21
 * @desc FeedbackModel
 **/
public class FeedbackModel {
    private String id;

    private String caseId;

    private String disposalId;

    private String caseDispatchStatus;

    private String interphoneId;

    private String fbWay;

    private String fbContent;

    private String feedbacker;

    private Date fbTime;

    private double longitude;

    private double latitude;

    private String caseType;

    private String caseSubType;

    private String caseThreeType;

    private String caseProcessor;

    private String createuser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private int delFlag;
    private String staffGuid;
    private String dispatchId;
    private String caseLevel;
    private String applyApprovalFeedBack;

    private List<FeedBackFileModel> feedBackFileModels;

    private StaffForAppModel user;

    //检查过，没问题 by hqm
    public FeedbackForAppModel parseToAppModel() {
        FeedbackForAppModel appModel = new FeedbackForAppModel();

        String val = "";

        appModel.setContent(MyUtil.checkNullString(this.fbContent, val));
        appModel.setStatus(MyUtil.checkNullString(this.caseDispatchStatus, val));

        Date fbTime = this.getFbTime();
        if (fbTime != null) {
            appModel.setTime(String.valueOf(fbTime.getTime()));
        } else {
            appModel.setTime("");
        }

        List<FeedBackFileModel> feedBackFileModels = this.getFeedBackFileModels();
        List<AlarmAttachfile> alarmAttachfiles = new ArrayList<AlarmAttachfile>();

        if (feedBackFileModels != null && feedBackFileModels.size() > 0) {
            for (FeedBackFileModel fileModel : feedBackFileModels) {
                alarmAttachfiles.add(fileModel.parseToAttachFile());
            }
        }

        appModel.setId(MyUtil.checkNullString(this.getId(), val));
        appModel.setAlarmAttachfile(alarmAttachfiles);


        StaffForAppModel staffForAppModel = new StaffForAppModel();
        staffForAppModel.setRealName(this.getFeedbacker());
        staffForAppModel.setLoginTime("");
        staffForAppModel.setOrganization("");
        staffForAppModel.setPhoneNumber("");
        staffForAppModel.setRole("");
        staffForAppModel.setStaffCode("");
        staffForAppModel.setStaffGuid("");

        appModel.setUser(staffForAppModel);
        appModel.setApplyApprovalFeedBack(this.applyApprovalFeedBack);

        return appModel;

    }

    public FeedbackModelForWcf parseToVcsModel() {
        FeedbackModelForWcf vcsModel = new FeedbackModelForWcf();
        String val = "";

        vcsModel.setCaseID(MyUtil.checkNullString(this.caseId, val));
        vcsModel.setDispatchStatus(MyUtil.checkNullString(this.caseDispatchStatus, val));
        vcsModel.setStaffID(MyUtil.checkNullString(this.staffGuid, val));
        vcsModel.setFeedbackContent(MyUtil.checkNullString(this.fbContent, val));
        vcsModel.setLatitude(this.latitude);
        vcsModel.setLongitude(this.longitude);
        vcsModel.setID(this.id);
        //新加批示
        vcsModel.setApplyApprovalFeedBack(this.applyApprovalFeedBack);

        vcsModel.setFeedbacker(MyUtil.checkNullString(this.feedbacker, val));

        Date date = this.fbTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        vcsModel.setFeedbackTime(MyUtil.checkNullString(sdf.format(date), val));

        List<FeedBackFileModelForWcf> feedBackFileModelForWcfs = new ArrayList<FeedBackFileModelForWcf>();
        List<FeedBackFileModel> feedBackFileModels = this.getFeedBackFileModels();

        if (feedBackFileModels != null && feedBackFileModels.size() > 0) {
            for (FeedBackFileModel fileModel : feedBackFileModels) {
                feedBackFileModelForWcfs.add(fileModel.parseToVcsFile());
            }
        }
        vcsModel.setFiles(feedBackFileModelForWcfs);
        return vcsModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getDisposalId() {
        return disposalId;
    }

    public void setDisposalId(String disposalId) {
        this.disposalId = disposalId;
    }

    public String getCaseDispatchStatus() {
        return caseDispatchStatus;
    }

    public void setCaseDispatchStatus(String caseDispatchStatus) {
        this.caseDispatchStatus = caseDispatchStatus;
    }

    public String getInterphoneId() {
        return interphoneId;
    }

    public void setInterphoneId(String interphoneId) {
        this.interphoneId = interphoneId;
    }

    public String getFbWay() {
        return fbWay;
    }

    public void setFbWay(String fbWay) {
        this.fbWay = fbWay;
    }

    public String getFbContent() {
        return fbContent;
    }

    public void setFbContent(String fbContent) {
        this.fbContent = fbContent;
    }

    public String getFeedbacker() {
        return feedbacker;
    }

    public void setFeedbacker(String feedbacker) {
        this.feedbacker = feedbacker;
    }

    public Date getFbTime() {
        return fbTime;
    }

    public void setFbTime(Date fbTime) {
        this.fbTime = fbTime;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseSubType() {
        return caseSubType;
    }

    public void setCaseSubType(String caseSubType) {
        this.caseSubType = caseSubType;
    }

    public String getCaseThreeType() {
        return caseThreeType;
    }

    public void setCaseThreeType(String caseThreeType) {
        this.caseThreeType = caseThreeType;
    }

    public String getCaseProcessor() {
        return caseProcessor;
    }

    public void setCaseProcessor(String caseProcessor) {
        this.caseProcessor = caseProcessor;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
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

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
    }

    public List<FeedBackFileModel> getFeedBackFileModels() {
        return feedBackFileModels;
    }

    public void setFeedBackFileModels(List<FeedBackFileModel> feedBackFileModels) {
        this.feedBackFileModels = feedBackFileModels;
    }

    public StaffForAppModel getUser() {
        return user;
    }

    public void setUser(StaffForAppModel user) {
        this.user = user;
    }

    public String getCaseLevel() {
        return caseLevel;
    }

    public void setCaseLevel(String caseLevel) {
        this.caseLevel = caseLevel;
    }

    public String getStaffGuid() {
        return staffGuid;
    }

    public void setStaffGuid(String staffGuid) {
        this.staffGuid = staffGuid;
    }

    public String getApplyApprovalFeedBack() {
        return applyApprovalFeedBack;
    }

    public void setApplyApprovalFeedBack(String applyApprovalFeedBack) {
        this.applyApprovalFeedBack = applyApprovalFeedBack;
    }
}
