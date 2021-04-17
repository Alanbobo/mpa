package com.commandcenter.model.casemodel;

import com.commandcenter.common.utils.DateUtil;

import java.util.List;

/**
 * @author r25437
 * @create 2018-09-17 14:00
 * @desc VCS接受警情model
 **/
public class CaseModelForVcsMq {

    private String id;

    private String callerno;

    private String calling_time;

    private String answering_time;

    private String seatno;

    private String contact;

    private String contactno;

    private String contact_sex;

    private String destrict;

    private String areaorg_id;

    private String address;

    private double longitude;

    private double latitude;

    private String casetime;

    private String case_type;

    private String case_sub_type;

    private String case_three_type;

    private String case_source;

    private String case_level;

    private String description;

    private String dispatch_status;

    private List<DispatchModelForVcsMq> dispatch_users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCallerno() {
        return callerno;
    }

    public void setCallerno(String callerno) {
        this.callerno = callerno;
    }

    public String getCalling_time() {
        return calling_time;
    }

    public void setCalling_time(String calling_time) {
        this.calling_time = calling_time;
    }

    public String getAnswering_time() {
        return answering_time;
    }

    public void setAnswering_time(String answering_time) {
        this.answering_time = answering_time;
    }

    public String getSeatno() {
        return seatno;
    }

    public void setSeatno(String seatno) {
        this.seatno = seatno;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getContact_sex() {
        return contact_sex;
    }

    public void setContact_sex(String contact_sex) {
        this.contact_sex = contact_sex;
    }

    public String getDestrict() {
        return destrict;
    }

    public void setDestrict(String destrict) {
        this.destrict = destrict;
    }

    public String getAreaorg_id() {
        return areaorg_id;
    }

    public void setAreaorg_id(String areaorg_id) {
        this.areaorg_id = areaorg_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getCasetime() {
        return casetime;
    }

    public void setCasetime(String casetime) {
        this.casetime = casetime;
    }

    public String getCase_type() {
        return case_type;
    }

    public void setCase_type(String case_type) {
        this.case_type = case_type;
    }

    public String getCase_sub_type() {
        return case_sub_type;
    }

    public void setCase_sub_type(String case_sub_type) {
        this.case_sub_type = case_sub_type;
    }

    public String getCase_three_type() {
        return case_three_type;
    }

    public void setCase_three_type(String case_three_type) {
        this.case_three_type = case_three_type;
    }

    public String getCase_source() {
        return case_source;
    }

    public void setCase_source(String case_source) {
        this.case_source = case_source;
    }

    public String getCase_level() {
        return case_level;
    }

    public void setCase_level(String case_level) {
        this.case_level = case_level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDispatch_status() {
        return dispatch_status;
    }

    public void setDispatch_status(String dispatch_status) {
        this.dispatch_status = dispatch_status;
    }

    public List<DispatchModelForVcsMq> getDispatch_users() {
        return dispatch_users;
    }

    public void setDispatch_users(List<DispatchModelForVcsMq> dispatch_users) {
        this.dispatch_users = dispatch_users;
    }

    public VcstCaseInfo parseToCaseModel() {
        VcstCaseInfo caseModel = new VcstCaseInfo();
        caseModel.setId(this.id);
        caseModel.setCallerno(this.callerno);
        //if (DateUtil.isDateString(this.calling_time)) {
        caseModel.setCallingtime(DateUtil.parse(this.calling_time, "yyyy-MM-dd HH:mm:ss"));
        //}
        //if (DateUtil.isDateString(this.answering_time)) {
        caseModel.setAnsweringtime(DateUtil.parse(this.answering_time, "yyyy-MM-dd HH:mm:ss"));
        //}
        caseModel.setSeatno(this.seatno);
        caseModel.setContact(this.contact);
        caseModel.setContactno(this.contactno);
        caseModel.setContactsex(this.contact_sex);
        caseModel.setCasedestrict(this.destrict);
        caseModel.setAreaorg(this.getAreaorg_id());
        caseModel.setCaseaddress(this.address);
        caseModel.setLongitude(this.longitude);
        caseModel.setLatitude(this.latitude);
        //if (DateUtil.isDateString(this.casetime)) {
        caseModel.setCasetime(DateUtil.parse(this.casetime, "yyyy-MM-dd HH:mm:ss"));
        //}
        caseModel.setCasetype(this.case_type);
        caseModel.setCasesubtype(this.case_sub_type);
        caseModel.setCasethreetype(this.case_three_type);
        caseModel.setCasesource(this.case_source);
        caseModel.setCaselevel(this.case_level);
        caseModel.setCasedesc(this.description);
        caseModel.setStatus(this.dispatch_status);

        List<MpatDispatch> dispatchModels = MpatDispatch.parseDispathsFromVcsMq(this.dispatch_users);
        if (dispatchModels != null) {
            for (MpatDispatch dispatchModel : dispatchModels) {
                dispatchModel.setCaseId(caseModel.getId());
            }
        }

        caseModel.setDispatchModels(dispatchModels);
        return caseModel;
    }

}
