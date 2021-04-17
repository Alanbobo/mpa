package com.commandcenter.model.smp.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author r25437
 * @create 2018-09-06 14:36
 * @desc LoginForVcs
 **/
public class LoginForVcs {
    private String PUC_ID; //puc id
    private String SYSTEM_ID;//系统编号
    private String DEVICE_ID;//设备号码,在这里为警员guid
    private String SOURCETYPE;//0代表PUC，1代表app
    private String DEVICETYPE;//各类型的序号
    private String RECEIVETIME;//上报时间
    private String ISONLINE;//设备在线情况，0离线，1在线
    private String CASEID;//保留字段，为空
    private String PDTSTATE;//保留字段
    private String ISLOCK;//保留字段，为空
    private String ID;//PUC_ID-SYSTEM_ID-DEVICE_ID

    @JSONField(name = "PUC_ID")
    public String getPUC_ID() {
        return PUC_ID;
    }

    public void setPUC_ID(String PUC_ID) {
        this.PUC_ID = PUC_ID;
    }

    @JSONField(name = "SYSTEM_ID")
    public String getSYSTEM_ID() {
        return SYSTEM_ID;
    }

    public void setSYSTEM_ID(String SYSTEM_ID) {
        this.SYSTEM_ID = SYSTEM_ID;
    }

    @JSONField(name = "DEVICE_ID")
    public String getDEVICE_ID() {
        return DEVICE_ID;
    }

    public void setDEVICE_ID(String DEVICE_ID) {
        this.DEVICE_ID = DEVICE_ID;
    }

    @JSONField(name = "SOURCETYPE")
    public String getSOURCETYPE() {
        return SOURCETYPE;
    }

    public void setSOURCETYPE(String SOURCETYPE) {
        this.SOURCETYPE = SOURCETYPE;
    }

    @JSONField(name = "DEVICETYPE")
    public String getDEVICETYPE() {
        return DEVICETYPE;
    }

    public void setDEVICETYPE(String DEVICETYPE) {
        this.DEVICETYPE = DEVICETYPE;
    }

    @JSONField(name = "RECEIVETIME")
    public String getRECEIVETIME() {
        return RECEIVETIME;
    }

    public void setRECEIVETIME(String RECEIVETIME) {
        this.RECEIVETIME = RECEIVETIME;
    }

    @JSONField(name = "ISONLINE")
    public String getISONLINE() {
        return ISONLINE;
    }

    public void setISONLINE(String ISONLINE) {
        this.ISONLINE = ISONLINE;
    }

    @JSONField(name = "CASEID")
    public String getCASEID() {
        return CASEID;
    }

    public void setCASEID(String CASEID) {
        this.CASEID = CASEID;
    }

    @JSONField(name = "PDTSTATE")
    public String getPDTSTATE() {
        return PDTSTATE;
    }

    public void setPDTSTATE(String PDTSTATE) {
        this.PDTSTATE = PDTSTATE;
    }

    @JSONField(name = "ISLOCK")
    public String getISLOCK() {
        return ISLOCK;
    }

    public void setISLOCK(String ISLOCK) {
        this.ISLOCK = ISLOCK;
    }

    @JSONField(name = "ID")
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "LoginForVcs{" +
                "PUC_ID='" + PUC_ID + '\'' +
                ", SYSTEM_ID='" + SYSTEM_ID + '\'' +
                ", DEVICE_ID='" + DEVICE_ID + '\'' +
                ", SOURCETYPE='" + SOURCETYPE + '\'' +
                ", DEVICETYPE='" + DEVICETYPE + '\'' +
                ", RECEIVETIME='" + RECEIVETIME + '\'' +
                ", ISONLINE='" + ISONLINE + '\'' +
                ", CASEID='" + CASEID + '\'' +
                ", PDTSTATE='" + PDTSTATE + '\'' +
                ", ISLOCK='" + ISLOCK + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
