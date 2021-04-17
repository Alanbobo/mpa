package com.commandcenter.model.smp;

import java.util.Date;

public class SmpSeatInfo {
    private String guid;

    private String seatIp;

    private String seatName;

    private String seatPassword;

    private String enableFlag;

    private Date createTime;

    private Date updateTime;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getSeatIp() {
        return seatIp;
    }

    public void setSeatIp(String seatIp) {
        this.seatIp = seatIp == null ? null : seatIp.trim();
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName == null ? null : seatName.trim();
    }

    public String getSeatPassword() {
        return seatPassword;
    }

    public void setSeatPassword(String seatPassword) {
        this.seatPassword = seatPassword == null ? null : seatPassword.trim();
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag == null ? null : enableFlag.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}