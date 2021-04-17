package com.commandcenter.model.ppcs;

import java.util.List;

/**
 * @Author
 *  2019/1/18.
 */
public class DutyOneDay {
    /**
     * 排班领导列表
     */
    private DutyLeader dutyLeader;
    /**
     * 排班值班长列表
     */
    private DutyLeader dutychief;
    /**
     * 排班时间段列表
     */
    private List<DutyTime> dutyTimeList;
    /**
     * 日期 yyyy-mm-dd
     */
    private String dutyDay;
    /**
     * 组织机构id
     */
    private String orgGuid;

    public DutyLeader getDutychief() {
        return dutychief;
    }

    public void setDutychief(DutyLeader dutychief) {
        this.dutychief = dutychief;
    }

    public DutyLeader getDutyLeader() {
        return dutyLeader;
    }

    public void setDutyLeader(DutyLeader dutyLeader) {
        this.dutyLeader = dutyLeader;
    }

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public List<DutyTime> getDutyTimeList() {
        return dutyTimeList;
    }

    public void setDutyTimeList(List<DutyTime> dutyTimeList) {
        this.dutyTimeList = dutyTimeList;
    }

    public String getDutyDay() {
        return dutyDay;
    }

    public void setDutyDay(String dutyDay) {
        this.dutyDay = dutyDay;
    }
}
