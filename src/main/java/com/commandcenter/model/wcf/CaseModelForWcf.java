package com.commandcenter.model.wcf;

import java.util.List;

/**
 * @author r25437
 * @create 2018-09-07 11:17
 * @desc 警情
 **/
public class CaseModelForWcf {
    private String Address;
    private String AnsweringTime;
    private String AreaOrgID;
    private String CallerNO;
    private String CallingTime;
    private String CaseLevel;
    private String CaseSource;
    private String CaseSubType;
    private String CaseThreeType;
    private String CaseTime;
    private String CaseType;
    private String Contact;
    private String ContactNO;
    private String ContactSex;
    private String Description;
    private String Destrict;
    private List<String> DispatchOrgs;
    private String DispatchStatus;
    private String ID;
    private Double Latitude;
    private Double Longitude;
    private List Polices;
    private String SeatNO;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAnsweringTime() {
        return AnsweringTime;
    }

    public void setAnsweringTime(String answeringTime) {
        AnsweringTime = answeringTime;
    }

    public String getAreaOrgID() {
        return AreaOrgID;
    }

    public void setAreaOrgID(String areaOrgID) {
        AreaOrgID = areaOrgID;
    }

    public String getCallerNO() {
        return CallerNO;
    }

    public void setCallerNO(String callerNO) {
        CallerNO = callerNO;
    }

    public String getCallingTime() {
        return CallingTime;
    }

    public void setCallingTime(String callingTime) {
        CallingTime = callingTime;
    }

    public String getCaseLevel() {
        return CaseLevel;
    }

    public void setCaseLevel(String caseLevel) {
        CaseLevel = caseLevel;
    }

    public String getCaseSource() {
        return CaseSource;
    }

    public void setCaseSource(String caseSource) {
        CaseSource = caseSource;
    }

    public String getCaseSubType() {
        return CaseSubType;
    }

    public void setCaseSubType(String caseSubType) {
        CaseSubType = caseSubType;
    }

    public String getCaseThreeType() {
        return CaseThreeType;
    }

    public void setCaseThreeType(String caseThreeType) {
        CaseThreeType = caseThreeType;
    }

    public String getCaseTime() {
        return CaseTime;
    }

    public void setCaseTime(String caseTime) {
        CaseTime = caseTime;
    }

    public String getCaseType() {
        return CaseType;
    }

    public void setCaseType(String caseType) {
        CaseType = caseType;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getContactNO() {
        return ContactNO;
    }

    public void setContactNO(String contactNO) {
        ContactNO = contactNO;
    }

    public String getContactSex() {
        return ContactSex;
    }

    public void setContactSex(String contactSex) {
        ContactSex = contactSex;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDestrict() {
        return Destrict;
    }

    public void setDestrict(String destrict) {
        Destrict = destrict;
    }

    public List<String> getDispatchOrgs() {
        return DispatchOrgs;
    }

    public void setDispatchOrgs(List<String> dispatchOrgs) {
        DispatchOrgs = dispatchOrgs;
    }

    public String getDispatchStatus() {
        return DispatchStatus;
    }

    public void setDispatchStatus(String dispatchStatus) {
        DispatchStatus = dispatchStatus;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public List getPolices() {
        return Polices;
    }

    public void setPolices(List polices) {
        Polices = polices;
    }

    public String getSeatNO() {
        return SeatNO;
    }

    public void setSeatNO(String seatNO) {
        SeatNO = seatNO;
    }
}
