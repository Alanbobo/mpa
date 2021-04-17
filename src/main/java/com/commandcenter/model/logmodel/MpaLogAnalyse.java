package com.commandcenter.model.logmodel;
public class MpaLogAnalyse {
    private String guid;//GUID
    private String opName;//操作名称-服务名
    private String opStaff;//操作工号，可空
    private java.util.Date opTime;//操作时间，该操作记录日志时间
    private String logTime;//日志处理时间，年月日
    private java.util.Date updateTime;//记录更新时间
    private String opMark;//操作备注，预留
    public MpaLogAnalyse() {
        super();
    }
    public MpaLogAnalyse(String guid,String opName,String opStaff,java.util.Date opTime,String logTime,java.util.Date updateTime,String opMark) {
        super();
        this.guid = guid;
        this.opName = opName;
        this.opStaff = opStaff;
        this.opTime = opTime;
        this.logTime = logTime;
        this.updateTime = updateTime;
        this.opMark = opMark;
    }
    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOpName() {
        return this.opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public String getOpStaff() {
        return this.opStaff;
    }

    public void setOpStaff(String opStaff) {
        this.opStaff = opStaff;
    }

    public java.util.Date getOpTime() {
        return this.opTime;
    }

    public void setOpTime(java.util.Date opTime) {
        this.opTime = opTime;
    }

    public String getLogTime() {
        return this.logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOpMark() {
        return this.opMark;
    }

    public void setOpMark(String opMark) {
        this.opMark = opMark;
    }

}
