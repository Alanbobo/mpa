package com.commandcenter.model.gps;

/**
 * @author r25437
 * @create 2018-10-23 10:43
 * @desc GPS转换结果
 **/
public class GpsModel {
    private double wgLat;
    private double wgLon;

    public GpsModel(double wgLat, double wgLon) {
        setWgLat(wgLat);
        setWgLon(wgLon);
    }

    public double getWgLat() {
        return wgLat;
    }

    public void setWgLat(double wgLat) {
        this.wgLat = wgLat;
    }

    public double getWgLon() {
        return wgLon;
    }

    public void setWgLon(double wgLon) {
        this.wgLon = wgLon;
    }

    @Override
    public String toString() {
        return "Gps{" +
                "wgLat=" + wgLat +
                ", wgLon=" + wgLon +
                '}';
    }
}
