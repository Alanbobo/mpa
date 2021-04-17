package com.commandcenter.model.ppcs;

import java.util.List;

/**
 * @Author
 *  2019/1/18.
 */
public class DutyTime {
    /**
     * //时间表id
     */
    private String guid;
    /**
     * //值班日期  yyyy-mm-dd
     */
    private String dutyDay;
    /**
     * //组织机构id
     */
    private String orgGuid;

    private String orgIdentifier;
    /**
     * //值班开始时间 hh:mi
     */
    private String startTime;
    /**
     * //值班结束时间 hh:mi
     */
    private String endTime;
    /**
     * //班次id
     */
    private String classGuid;
    /**
     * //班次类型 ZFLX001主班  ZFLX002辅班
     */
    private String classType;
    /**
     * //是否跨天 Y跨天  N不跨天
     */
    private String isAcross;
    /**
     * //巡区列表
     */
    private List<DutyPlace> dutyPlaceList;

    public List<DutyPlace> getDutyPlaceList() {
        return dutyPlaceList;
    }

    public void setDutyPlaceList(List<DutyPlace> dutyPlaceList) {
        this.dutyPlaceList = dutyPlaceList;
    }

    public String getOrgIdentifier() {
        return orgIdentifier;
    }

    public void setOrgIdentifier(String orgIdentifier) {
        this.orgIdentifier = orgIdentifier;
    }


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDutyDay() {
        return dutyDay;
    }

    public void setDutyDay(String dutyDay) {
        this.dutyDay = dutyDay;
    }

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getClassGuid() {
        return classGuid;
    }

    public void setClassGuid(String classGuid) {
        this.classGuid = classGuid;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getIsAcross() {
        return isAcross;
    }

    public void setIsAcross(String isAcross) {
        this.isAcross = isAcross;
    }
}
