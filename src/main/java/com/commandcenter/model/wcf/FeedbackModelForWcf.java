package com.commandcenter.model.wcf;

import com.commandcenter.common.utils.DateUtils;
import com.commandcenter.model.casemodel.MpatFeedbackFile;
import com.commandcenter.model.casemodel.VcstCaseFeedback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author r25437
 * @create 2018-09-07 11:18
 * @desc 反馈
 * @desc 新加字段 20200817 ApplyApprovalFeedBack 申请批示反馈
 **/
public class FeedbackModelForWcf {
    private String ID;
    private String DispatchStatus;
    private String Feedbacker;
    private String RecordFile;
    private String RecordTime;
    private double Longitude;
    private double Latitude;
    private String Content;
    private String Status;
    private String Time;
    private String Type;
    private List<FeedBackFileModelForWcf> Files;

    //向WCF上报需要用到的字段
    private String CaseID;
    //警员Id
    private String StaffID;
    private String FeedbackContent;
    private String FeedbackTime;// yyyy/MM/dd hh:mm:ss
    //新加批示
    private String ApplyApprovalFeedBack;



    public VcstCaseFeedback parseToFeedbackModel() {
        VcstCaseFeedback vcstCaseFeedback = new VcstCaseFeedback();

        vcstCaseFeedback.setCaseDispatchStatus(this.getDispatchStatus());
        vcstCaseFeedback.setCaseId(this.getCaseID());
        vcstCaseFeedback.setFbContent(this.getContent());

        String time = this.getTime();
        if (!(null == time || "".equals(time))) {
            /*
            time = time.replace("/Date(", "").replace(")/", "");
            time = time.substring(0, time.length() - 5);
            */
            vcstCaseFeedback.setFbTime(DateUtils.format(time,"yyyy-MM-dd HH:mm:ss"));
        }

        vcstCaseFeedback.setFeedbacker(this.getFeedbacker());
        vcstCaseFeedback.setId(this.getID());
        vcstCaseFeedback.setApplyApprovalFeedBack(this.getApplyApprovalFeedBack());

        List<FeedBackFileModelForWcf> feedbackModelForWcfs = this.Files;
        if (feedbackModelForWcfs != null && feedbackModelForWcfs.size() > 0) {
            List<MpatFeedbackFile> mpatFeedbackFileList = new ArrayList<>();
            for (FeedBackFileModelForWcf fileModelForWcf : feedbackModelForWcfs) {
                MpatFeedbackFile mpatFeedbackFile = new MpatFeedbackFile();
                mpatFeedbackFile = fileModelForWcf.praseToFeedBackFileModel();
                mpatFeedbackFile.setFeedbackId(this.ID);
                mpatFeedbackFileList.add(mpatFeedbackFile);
            }
            vcstCaseFeedback.setMpatFeedbackFileList(mpatFeedbackFileList);
        }

        return vcstCaseFeedback;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDispatchStatus() {
        return DispatchStatus;
    }

    public void setDispatchStatus(String dispatchStatus) {
        DispatchStatus = dispatchStatus;
    }

    public String getFeedbacker() {
        return Feedbacker;
    }

    public void setFeedbacker(String feedbacker) {
        Feedbacker = feedbacker;
    }

    public String getRecordFile() {
        return RecordFile;
    }

    public void setRecordFile(String recordFile) {
        RecordFile = recordFile;
    }

    public String getRecordTime() {
        return RecordTime;
    }

    public void setRecordTime(String recordTime) {
        RecordTime = recordTime;
    }


    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public List<FeedBackFileModelForWcf> getFiles() {
        return Files;
    }

    public void setFiles(List<FeedBackFileModelForWcf> files) {
        Files = files;
    }

    public String getCaseID() {
        return CaseID;
    }

    public void setCaseID(String caseID) {
        CaseID = caseID;
    }

    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String staffID) {
        StaffID = staffID;
    }

    public String getFeedbackContent() {
        return FeedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        FeedbackContent = feedbackContent;
    }

    public String getFeedbackTime() {
        return FeedbackTime;
    }

    public void setFeedbackTime(String feedbackTime) {
        FeedbackTime = feedbackTime;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public String getApplyApprovalFeedBack() {
        return ApplyApprovalFeedBack;
    }

    public void setApplyApprovalFeedBack(String applyApprovalFeedBack) {
        ApplyApprovalFeedBack = applyApprovalFeedBack;
    }
}
