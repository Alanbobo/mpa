package com.commandcenter.model.ppcs;

import java.util.List;

/**
 * @Author
 *  2019/1/18.
 */
public class DutyPlace {
    /**
     * 关系表id
     */
    private String guid;
    /**
     * 巡区id
     */
    private String placeGuid;
    /**
     * 巡区名称
     */
    private String placeName;
    /**
     * 巡区半径
     */
    private String radius;
    /**
     * 巡区图形坐标集合遵循wkt格式点POINT(6 10)，线LINESTRING(3 4,10 50,20 25)，多边形POLYGON((1 1,5 1,5 5,1 5,1 1))：
     */
    private String segmentContent;
    /**
     * 组织机构id
     */
    private String placeOrgGuid;
    /**
     * //组织机构名称
     */
    private String placeOrgName;
    /**
     * //巡区类型
     */
    private String patrolPlaceType;
    /**
     * //值班手台列表
     */
    private List<DutyPhone> dutyPhoneList;
    /**
     * //值班人员列表
     */
    private List<DutyStaff> dutyStaffList;
    /**
     * //值班车辆列表
     */
    private List<DutyCar> dutyCarList;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPlaceGuid() {
        return placeGuid;
    }

    public void setPlaceGuid(String placeGuid) {
        this.placeGuid = placeGuid;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getSegmentContent() {
        return segmentContent;
    }

    public void setSegmentContent(String segmentContent) {
        this.segmentContent = segmentContent;
    }

    public String getPlaceOrgGuid() {
        return placeOrgGuid;
    }

    public void setPlaceOrgGuid(String placeOrgGuid) {
        this.placeOrgGuid = placeOrgGuid;
    }

    public String getPlaceOrgName() {
        return placeOrgName;
    }

    public void setPlaceOrgName(String placeOrgName) {
        this.placeOrgName = placeOrgName;
    }

    public String getPatrolPlaceType() {
        return patrolPlaceType;
    }

    public void setPatrolPlaceType(String patrolPlaceType) {
        this.patrolPlaceType = patrolPlaceType;
    }

    public List<DutyPhone> getDutyPhoneList() {
        return dutyPhoneList;
    }

    public void setDutyPhoneList(List<DutyPhone> dutyPhoneList) {
        this.dutyPhoneList = dutyPhoneList;
    }

    public List<DutyStaff> getDutyStaffList() {
        return dutyStaffList;
    }

    public void setDutyStaffList(List<DutyStaff> dutyStaffList) {
        this.dutyStaffList = dutyStaffList;
    }

    public List<DutyCar> getDutyCarList() {
        return dutyCarList;
    }

    public void setDutyCarList(List<DutyCar> dutyCarList) {
        this.dutyCarList = dutyCarList;
    }
}
