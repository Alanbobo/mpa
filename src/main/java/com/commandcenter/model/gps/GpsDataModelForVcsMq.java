package com.commandcenter.model.gps;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author r25437
 * @create 2018-10-23 10:30
 * @desc GPS发送VCS入参实体
 **/
public class GpsDataModelForVcsMq {
    /**
     * puc id
     */
    private String PUC_ID;
    /**
     * 系统编号
     */
    private String SYSTEM_ID;
    /**
     * 设备号码,在这里为警员guid
     */
    private String DEVICE_ID;



    private String STAFF_ID;
    private String DISPATCHER_ID;
    private String ELECTRICITY;
    private String RSSIDOWN;
    private String RSSIUP;
    /**
     * 0代表PUC，1代表app
     */
    private String SOURCETYPE;
    /**
     * Gps时间，yyyy/MM/dd HH:mm:ss
     */
    private String GPS_DATETIME;
    /**
     * 经度标识。E:东经，W：西经
     */
    private String LONG_WE;
    /**
     * 经度，单位度
     */
    private String LONGITUDE;
    /**
     * 纬度标识。S：南纬；N：北纬
     */
    private String LAT_NS;
    /**
     * 纬度，单位度
     */
    private String LATITUDE;
    /**
     * 地面速度，单位 公里/小时
     */
    private String SPEED;
    /**
     * 方向单位角度，以正北为基准，顺时针增加,(对接方单词打错了,没办法)
     */
    private String DIRECTION;
    /**
     * 记录设备状态传感器
     */
    private String STATE;
    /**
     * PUC_ID-SYSTEM_ID-DEVICE_ID
     */
    private String ID;
    /**
     * 二维空间地址，合并经纬度, POINT（经度 纬度）
     */
    private String SPACECOORDINATE;
    @JSONField(name = "STAFF_ID")
    public String getSTAFF_ID() {
        return STAFF_ID;
    }

    public void setSTAFF_ID(String STAFF_ID) {
        this.STAFF_ID = STAFF_ID;
    }
    @JSONField(name = "DISPATCHER_ID")
    public String getDISPATCHER_ID() {
        return DISPATCHER_ID;
    }

    public void setDISPATCHER_ID(String DISPATCHER_ID) {
        this.DISPATCHER_ID = DISPATCHER_ID;
    }
    @JSONField(name = "ELECTRICITY")
    public String getELECTRICITY() {
        return ELECTRICITY;
    }

    public void setELECTRICITY(String ELECTRICITY) {
        this.ELECTRICITY = ELECTRICITY;
    }
    @JSONField(name = "RSSIDOWN")
    public String getRSSIDOWN() {
        return RSSIDOWN;
    }

    public void setRSSIDOWN(String RSSIDOWN) {
        this.RSSIDOWN = RSSIDOWN;
    }
    @JSONField(name = "RSSIUP")
    public String getRSSIUP() {
        return RSSIUP;
    }

    public void setRSSIUP(String RSSIUP) {
        this.RSSIUP = RSSIUP;
    }
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

    @JSONField(name = "GPS_DATETIME")
    public String getGPS_DATETIME() {
        return GPS_DATETIME;
    }

    public void setGPS_DATETIME(String GPS_DATETIME) {
        this.GPS_DATETIME = GPS_DATETIME;
    }

    @JSONField(name = "LONG_WE")
    public String getLONG_WE() {
        return LONG_WE;
    }

    public void setLONG_WE(String LONG_WE) {
        this.LONG_WE = LONG_WE;
    }

    @JSONField(name = "LONGITUDE")
    public String getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(String LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    @JSONField(name = "LAT_NS")
    public String getLAT_NS() {
        return LAT_NS;
    }

    public void setLAT_NS(String LAT_NS) {
        this.LAT_NS = LAT_NS;
    }

    @JSONField(name = "LATITUDE")
    public String getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(String LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    @JSONField(name = "SPEED")
    public String getSPEED() {
        return SPEED;
    }

    public void setSPEED(String SPEED) {
        this.SPEED = SPEED;
    }

    @JSONField(name = "DIRECTION")
    public String getDIRECTION() {
        return DIRECTION;
    }

    public void setDIRECTION(String DIRECTION) {
        this.DIRECTION = DIRECTION;
    }

    @JSONField(name = "STATE")
    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    @JSONField(name = "ID")
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @JSONField(name = "SPACECOORDINATE")
    public String getSPACECOORDINATE() {
        return SPACECOORDINATE;
    }

    public void setSPACECOORDINATE(String SPACECOORDINATE) {
        this.SPACECOORDINATE = SPACECOORDINATE;
    }

    @Override
    public String toString() {
        return "GpsDataModelForVcsMq{" +
                "PUC_ID='" + PUC_ID + '\'' +
                ", SYSTEM_ID='" + SYSTEM_ID + '\'' +
                ", DEVICE_ID='" + DEVICE_ID + '\'' +
                ", SOURCE_TYPE='" + SOURCETYPE + '\'' +
                ", GPS_DATETIME='" + GPS_DATETIME + '\'' +
                ", LONG_WE='" + LONG_WE + '\'' +
                ", LONGITUDE='" + LONGITUDE + '\'' +
                ", LAT_NS='" + LAT_NS + '\'' +
                ", LATITUDE='" + LATITUDE + '\'' +
                ", SPEED='" + SPEED + '\'' +
                ", DERECTION='" + DIRECTION + '\'' +
                ", STATE='" + STATE + '\'' +
                ", ID='" + ID + '\'' +
                ", SPACECOORDINATE=" + SPACECOORDINATE +
                ", DISPATCHER_ID="+DISPATCHER_ID+
                ", ELECTRICITY="+ELECTRICITY+
                ", RSSIDOWN="+RSSIDOWN+
                ", RSSIUP="+RSSIUP+
                ", STAFF_ID="+STAFF_ID+

                '}';
    }
    public static GpsDataModelForVcsMq parseDataFromBasic(GpsDataModel gpsDataModel) {
        if (gpsDataModel == null){ return null;}

        GpsDataModelForVcsMq result = new GpsDataModelForVcsMq();
        result.setPUC_ID(gpsDataModel.getPucID());
        result.setSYSTEM_ID(gpsDataModel.getSystemID());
        result.setDEVICE_ID(gpsDataModel.getStaffGuid());
        result.setSTAFF_ID(gpsDataModel.getStaffGuid());
        result.setDISPATCHER_ID(gpsDataModel.getDispatcherID());
        result.setELECTRICITY(gpsDataModel.getElectricity());
        result.setRSSIDOWN(gpsDataModel.getRssidown());
        result.setRSSIUP(gpsDataModel.getRssiup());
        result.setSOURCETYPE("1");
        result.setGPS_DATETIME(gpsDataModel.getGpsTime());
        result.setLONG_WE(gpsDataModel.getLongWE());
        result.setLONGITUDE(gpsDataModel.getLongitude());
        result.setLAT_NS(gpsDataModel.getLatNS());
        result.setLATITUDE(gpsDataModel.getLatitude());
        result.setSPEED(gpsDataModel.getSpeed());
        result.setDIRECTION(gpsDataModel.getDirection());
        result.setSTATE(gpsDataModel.getState());
        result.setID("");
        result.setSPACECOORDINATE("POINT(" + gpsDataModel.getLongitude() + " " + gpsDataModel.getLatitude() + ")");

        return result;
    }
}
