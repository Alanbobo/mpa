package com.commandcenter.model.ppcs;

import java.util.Date;
import java.util.List;

/**
 * @author r25437
 * @create 2019-01-21 13:39
 * @desc 某日排班情况
 **/
public class DutyDayInfo {

    List<DutyLeader> dutyLeaderList;

    List<DutyLeader> dutychiefList;

    List<DutyTime> dutyTimeList;

    Date dutyDay;

    String orgGuid;

    public List<DutyTime> getDutyTimeList() {
        return dutyTimeList;
    }

    public void setDutyTimeList(List<DutyTime> dutyTimeList) {
        this.dutyTimeList = dutyTimeList;
    }

    public Date getDutyDay() {
        return dutyDay;
    }

    public void setDutyDay(Date dutyDay) {
        this.dutyDay = dutyDay;
    }

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public List<DutyLeader> getDutyLeaderList() {
        return dutyLeaderList;
    }

    public void setDutyLeaderList(List<DutyLeader> dutyLeaderList) {
        this.dutyLeaderList = dutyLeaderList;
    }

    public List<DutyLeader> getDutychiefList() {
        return dutychiefList;
    }

    public void setDutychiefList(List<DutyLeader> dutychiefList) {
        this.dutychiefList = dutychiefList;
    }
}
