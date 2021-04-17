package com.commandcenter.model.mess;

public class ActivityTask {

    private String charger;
    private String privilegeOrg;

    private String createTime;
    private String endTime;
    private String startTime;
    private String id;
    private String activityId;
    private String name;
    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    public String getPrivilegeOrg() {
        return privilegeOrg;
    }

    public void setPrivilegeOrg(String privilegeOrg) {
        this.privilegeOrg = privilegeOrg;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
