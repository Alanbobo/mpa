package com.commandcenter.common.activemq.handler.bean;

/**
 * @author r25437
 * @create 2018-09-06 11:43
 * @desc MesHeader
 **/
public class MesHeader {
    public static final int TYPE_RESPONSE = 0;
    public static final int TYPE_REQUEST = 1;

    public static final int METHOD_BACK = 0;
    public static final int METHOD_CALL = 1;

    public static final int RESP_NO = 0;
    public static final int RESP_YES = 1;

    private int type;
    private int method;
    private int resp;
    private String uuid;
    /**
     * 1、如果method=METHOD_CALL,destination的格式为classpath.method。例如:"TestService.onMessage"
     */
    private String destination;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getResp() {
        return resp;
    }

    public void setResp(int resp) {
        this.resp = resp;
    }

    @Override
    public String toString() {
        return "MesHeader{" +
                "type=" + type +
                ", method=" + method +
                ", resp=" + resp +
                ", uuid='" + uuid + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }

    public String getClassPatch() {
        if (getMethod() != METHOD_CALL) {
            return null;
        }

        String des = getDestination();
        int point = des.indexOf(".");
        if (point == -1) {
            return null;
        }

        return des.substring(0, point);
    }

    public String getDesMethod() {
        if (getMethod() != METHOD_CALL) {
            return null;
        }

        String des = getDestination();
        int point = des.indexOf(".");
        if (point == -1) {
            return null;
        }

        return des.substring(point + 1);
    }

}
