package com.commandcenter.model.casemodel;

import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.MyUtil;
import com.commandcenter.model.wcf.FeedbackForAppModel;
import com.commandcenter.model.wcf.FeedbackModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VcstCaseInfo {
    /**
     *标识
     */
    private String id;

    /**
     * 主叫号码
     */
    private String callerno;
    /**
     * 被叫号码
     */
    private String calledno;
    /**
     * 呼入时间
     */
    private java.util.Date callingtime;
    /**
     * 应答时间
     */
    private java.util.Date answeringtime;
    /**
     * 接警坐席号
     */
    private String seatno;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系电话
     */
    private String contactno;
    /**
     * 联系人性别
     */
    private String contactsex;
    /**
     * 案发行政区划
     */
    private String casedestrict;
    /**
     *
     */
    private String areaorg;
    /**
     * 案发地址
     */
    private String caseaddress;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 案发时间
     */
    private java.util.Date casetime;
    /**
     * 案件类型，数据字典
     */
    private String casetype;
    /**
     * 案件中类，数据字典
     */
    private String casesubtype;
    /**
     * 案件小类，数据字典
     */
    private String casethreetype;
    /**
     * 案件来源，数据字典
     */
    private String casesource;
    /**
     * 案件级别，数据字典
     */
    private String caselevel;
    /**
     * 案情描述
     */
    private String casedesc;
    /**
     * 创建人
     */
    private String createuser;
    /**
     * 创建时间
     */
    private java.util.Date createtime;
    /**
     * 修改人
     */
    private String updateuser;
    /**
     * 修改时间
     */
    private java.util.Date updatetime;
    /**
     * 0有效，1删除
     */
    private Short delflag;
    /**
     * 警情状态(字典CASE-STATUS)
     */
    private String status;
    /**
     * 最后同步时间
     */
    private java.util.Date lastsynctime;
    /**
     * 敏感警情（0否 1是）
     */
    private Object issensitive;
    /**
     * 逻辑合并警单标识
     */
    private String mergeid;
    /**
     * 调派模式( 0急速1普通)
     */
    private String dispatchmode;
    /**
     * 三方通话调派成功（0成功1失败）
     */
    private String callsuccessed;
    /**
     * 重大警情（0否 1是）
     */
    private Object ismajor;
    private String createTimeExtend;
    private String feedbackStatus;
    private String caseTypeValue;

    private String caseSubTypeValue;

    private String caseThreeTypeValue;

    private List<MpatDispatch> dispatchModels;

    private List<FeedbackModel> alarmFbcontent;
    public VcstCaseInfo() {
        super();
    }
    public VcstCaseInfo(String id,String callerno,String calledno,java.util.Date callingtime,java.util.Date answeringtime,String seatno,String contact,String contactno,String contactsex,String casedestrict,String areaorg,String caseaddress,Double longitude,Double latitude,java.util.Date casetime,String casetype,String casesubtype,String casethreetype,String casesource,String caselevel,String casedesc,String createuser,java.util.Date createtime,String updateuser,java.util.Date updatetime,Short delflag,String status,java.util.Date lastsynctime,Object issensitive,String mergeid,String dispatchmode,String callsuccessed,Object ismajor) {
        super();
        this.id = id;
        this.callerno = callerno;
        this.calledno = calledno;
        this.callingtime = callingtime;
        this.answeringtime = answeringtime;
        this.seatno = seatno;
        this.contact = contact;
        this.contactno = contactno;
        this.contactsex = contactsex;
        this.casedestrict = casedestrict;
        this.areaorg = areaorg;
        this.caseaddress = caseaddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.casetime = casetime;
        this.casetype = casetype;
        this.casesubtype = casesubtype;
        this.casethreetype = casethreetype;
        this.casesource = casesource;
        this.caselevel = caselevel;
        this.casedesc = casedesc;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
        this.delflag = delflag;
        this.status = status;
        this.lastsynctime = lastsynctime;
        this.issensitive = issensitive;
        this.mergeid = mergeid;
        this.dispatchmode = dispatchmode;
        this.callsuccessed = callsuccessed;
        this.ismajor = ismajor;
    }

    public String getCaseTypeValue() {
        return caseTypeValue;
    }

    public void setCaseTypeValue(String caseTypeValue) {
        this.caseTypeValue = caseTypeValue;
    }

    public String getCaseSubTypeValue() {
        return caseSubTypeValue;
    }

    public void setCaseSubTypeValue(String caseSubTypeValue) {
        this.caseSubTypeValue = caseSubTypeValue;
    }

    public String getCaseThreeTypeValue() {
        return caseThreeTypeValue;
    }

    public void setCaseThreeTypeValue(String caseThreeTypeValue) {
        this.caseThreeTypeValue = caseThreeTypeValue;
    }

    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public String getCreateTimeExtend() {
        return createTimeExtend;
    }

    public void setCreateTimeExtend(String createTimeExtend) {
        this.createTimeExtend = createTimeExtend;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCallerno() {
        return this.callerno;
    }

    public void setCallerno(String callerno) {
        this.callerno = callerno;
    }

    public String getCalledno() {
        return this.calledno;
    }

    public void setCalledno(String calledno) {
        this.calledno = calledno;
    }

    public java.util.Date getCallingtime() {
        return this.callingtime;
    }

    public void setCallingtime(java.util.Date callingtime) {
        this.callingtime = callingtime;
    }

    public java.util.Date getAnsweringtime() {
        return this.answeringtime;
    }

    public void setAnsweringtime(java.util.Date answeringtime) {
        this.answeringtime = answeringtime;
    }

    public String getSeatno() {
        return this.seatno;
    }

    public void setSeatno(String seatno) {
        this.seatno = seatno;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactno() {
        return this.contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getContactsex() {
        return this.contactsex;
    }

    public void setContactsex(String contactsex) {
        this.contactsex = contactsex;
    }

    public String getCasedestrict() {
        return this.casedestrict;
    }

    public void setCasedestrict(String casedestrict) {
        this.casedestrict = casedestrict;
    }

    public String getAreaorg() {
        return this.areaorg;
    }

    public void setAreaorg(String areaorg) {
        this.areaorg = areaorg;
    }

    public String getCaseaddress() {
        return this.caseaddress;
    }

    public void setCaseaddress(String caseaddress) {
        this.caseaddress = caseaddress;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public java.util.Date getCasetime() {
        return this.casetime;
    }

    public void setCasetime(java.util.Date casetime) {
        this.casetime = casetime;
    }

    public String getCasetype() {
        return this.casetype;
    }

    public void setCasetype(String casetype) {
        this.casetype = casetype;
    }

    public String getCasesubtype() {
        return this.casesubtype;
    }

    public void setCasesubtype(String casesubtype) {
        this.casesubtype = casesubtype;
    }

    public String getCasethreetype() {
        return this.casethreetype;
    }

    public void setCasethreetype(String casethreetype) {
        this.casethreetype = casethreetype;
    }

    public String getCasesource() {
        return this.casesource;
    }

    public void setCasesource(String casesource) {
        this.casesource = casesource;
    }

    public String getCaselevel() {
        return this.caselevel;
    }

    public void setCaselevel(String caselevel) {
        this.caselevel = caselevel;
    }

    public String getCasedesc() {
        return this.casedesc;
    }

    public void setCasedesc(String casedesc) {
        this.casedesc = casedesc;
    }

    public String getCreateuser() {
        return this.createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public java.util.Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(java.util.Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuser() {
        return this.updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public java.util.Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(java.util.Date updatetime) {
        this.updatetime = updatetime;
    }

    public Short getDelflag() {
        return this.delflag;
    }

    public void setDelflag(Short delflag) {
        this.delflag = delflag;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.util.Date getLastsynctime() {
        return this.lastsynctime;
    }

    public void setLastsynctime(java.util.Date lastsynctime) {
        this.lastsynctime = lastsynctime;
    }

    public Object getIssensitive() {
        return this.issensitive;
    }

    public void setIssensitive(Object issensitive) {
        this.issensitive = issensitive;
    }

    public String getMergeid() {
        return this.mergeid;
    }

    public void setMergeid(String mergeid) {
        this.mergeid = mergeid;
    }

    public String getDispatchmode() {
        return this.dispatchmode;
    }

    public void setDispatchmode(String dispatchmode) {
        this.dispatchmode = dispatchmode;
    }

    public String getCallsuccessed() {
        return this.callsuccessed;
    }

    public void setCallsuccessed(String callsuccessed) {
        this.callsuccessed = callsuccessed;
    }

    public Object getIsmajor() {
        return this.ismajor;
    }

    public void setIsmajor(Object ismajor) {
        this.ismajor = ismajor;
    }

    public List<MpatDispatch> getDispatchModels() {
        return dispatchModels;
    }

    public void setDispatchModels(List<MpatDispatch> dispatchModels) {
        this.dispatchModels = dispatchModels;
    }

    public CaseForAppModel parseToAppModel() {

        CaseForAppModel caseReturn = new CaseForAppModel();

        String val = "";


        caseReturn.setAlarmId(MyUtil.checkNullString(this.getId(), val));

        caseReturn.setAlarmPhoneNumber(MyUtil.checkNullString(this.getContactno(), val));
        caseReturn.setAlarmLinkman(MyUtil.checkNullString(this.getContact(), val));
        caseReturn.setAlarmAddress(MyUtil.checkNullString(this.getCaseaddress(), val));
        caseReturn.setAlarmDesc(MyUtil.checkNullString(this.getCasedesc(), val));
        caseReturn.setAlarmLongitude(MyUtil.checkNullString(String.valueOf(this.getLongitude()), val));
        caseReturn.setAlarmLatitude(MyUtil.checkNullString(String.valueOf(this.getLatitude()), val));
        caseReturn.setAlarmStatus(MyUtil.checkNullString(this.getStatus(), val));

        String alarmCaseTypeValue = MyUtil.checkNullString(NullToCaseValue(this.getCaseTypeValue(),this.getCasetype()), val) + ";" + MyUtil.checkNullString(this.getCaseSubTypeValue(), val) + ";" + MyUtil.checkNullString(this.getCaseThreeTypeValue(), val);
        caseReturn.setAlarmCaseTypeValue(alarmCaseTypeValue);

        String alarmCaseTypeKey = MyUtil.checkNullString(this.getCasetype(), val) + ";" + MyUtil.checkNullString(this.getCasesubtype(), val) + ";" + MyUtil.checkNullString(this.getCasethreetype(), val);
        caseReturn.setAlarmCaseTypeKey(alarmCaseTypeKey);

        caseReturn.setAlarmLevel(MyUtil.checkNullString(this.getCaselevel(), val));

        //个人状态为空则置为未调派状态（JQZT001）
        if(null==this.getFeedbackStatus()||"".equals(this.getFeedbackStatus())){
            caseReturn.setFeedbackStatus(Constant.ALARM_STATUS.NO_DISPATCH.getValue());
        }else {
            caseReturn.setFeedbackStatus(MyUtil.checkNullString(this.getFeedbackStatus(), val));
        }

        List<FeedbackModel> feedbackModels = this.getAlarmFbcontent();
        List<FeedbackForAppModel> feedbackForAppModels = new ArrayList<FeedbackForAppModel>();
        if (!(feedbackModels == null || feedbackModels.size() < 1)) {
            for (FeedbackModel feedbackModel : feedbackModels) {
                feedbackForAppModels.add(feedbackModel.parseToAppModel());
            }
            caseReturn.setAlarmFeedback(feedbackForAppModels);
        }

        caseReturn.setDispatchModels(dispatchModels);
        Date caseTime = this.getCasetime();
        if (caseTime != null) {
            caseReturn.setAlarmTime(String.valueOf(caseTime.getTime()));
        } else {
            caseReturn.setAlarmTime("");
        }

        Date updateTime = this.getUpdatetime();
        if (updateTime != null) {
            caseReturn.setAlarmUpdateTime(String.valueOf(updateTime.getTime()));
        } else {
            caseReturn.setAlarmUpdateTime("");
        }

        Date createTime = this.getCreatetime();
        if (createTime != null) {
            caseReturn.setAlarmCreateTime(this.createTimeExtend);
        } else {
            caseReturn.setAlarmCreateTime("");
        }

        return caseReturn;
    }

    public List<FeedbackModel> getAlarmFbcontent() {
        return alarmFbcontent;
    }

    public void setAlarmFbcontent(List<FeedbackModel> alarmFbcontent) {
        this.alarmFbcontent = alarmFbcontent;
    }

    public String NullToCaseValue(String caseValue,String caseType){
        if(caseValue==null||"null".equals(caseValue)){
            switch (caseType){
                case "DIC003001":
                    caseValue = "刑事警情";
                    break;
                case "DIC003002":
                    caseValue = "治安警情";
                    break;
                case "DIC003004":
                    caseValue = "火灾事故";
                    break;
                case "DIC003005":
                    caseValue = "群众求助";
                    break;
                case "DIC003006":
                    caseValue = "举报线索";
                    break;
                case "DIC003007":
                    caseValue = "事件";
                    break;
                case "DIC003008":
                    caseValue = "纠纷";
                    break;
                case "DIC003009":
                    caseValue = "灾害事故";
                    break;
                case "DIC003010":
                    caseValue = "其他行政违法";
                    break;
                case "DIC003012":
                    caseValue = "警务查询";
                    break;
                case "DIC003013":
                    caseValue = "非正常死亡";
                    break;
                case "DIC003014":
                    caseValue = "投诉监督";
                    break;
                case "DIC003015":
                    caseValue = "非警务报警";
                    break;
                case "DIC003016":
                    caseValue = "森林警情";
                    break;
                case "DIC003099":
                    caseValue = "其他类别";
                    break;
                case "DIC003003":
                    caseValue = "交通事故";
                    break;
                default :
                    caseValue="";
                    break;
            }
        }
        return caseValue;
    }
}
