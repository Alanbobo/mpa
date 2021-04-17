package com.commandcenter.model.wcf;

import com.commandcenter.model.smp.vo.StaffForAppModel;

import java.util.List;

/**
 * @author r25437
 * @create 2018-09-07 11:22
 * @desc FeedbackForAppModel
 **/
public class FeedbackForAppModel {
    private String id;

    private String content;

    private String status;

    private String time;
    //新加批示
    private String applyApprovalFeedBack;

    private List<AlarmAttachfile> alarmAttachfile;

    private StaffForAppModel user;

   /* public FeedbackModel parseToModel() {
        FeedbackModel model = new FeedbackModel();
        model.setFbContent(this.content);
        StaffForAppModel staffForAppModel = this.getUser();
        if(staffForAppModel !=null){
            model.setFeedbacker(staffForAppModel.getStaffGuid());
        }
//        model.setFeedbacker(this.getUser();
        model.setCaseDispatchStatus(this.status);

        //String 类型转化为Date类型，需要先判断是否为空
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        //在这里处理下时间，并记录到csdn上
        Date date = null;
        if (this.time != null) {
            try {
                date = sdf.parse(this.time);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else {
            try {
                date = sdf.parse(sdf.format(new Date()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.setFbTime(date);
        return model;
    }
*/


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<AlarmAttachfile> getAlarmAttachfile() {
        return alarmAttachfile;
    }

    public void setAlarmAttachfile(List<AlarmAttachfile> alarmAttachfile) {
        this.alarmAttachfile = alarmAttachfile;
    }

    public StaffForAppModel getUser() {
        return user;
    }

    public void setUser(StaffForAppModel user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyApprovalFeedBack() {
        return applyApprovalFeedBack;
    }

    public void setApplyApprovalFeedBack(String applyApprovalFeedBack) {
        this.applyApprovalFeedBack = applyApprovalFeedBack;
    }
}
