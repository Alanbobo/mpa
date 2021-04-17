package com.commandcenter.common.activemq.handler.bean;

/**
 * @author r25437
 * @create 2018-09-06 11:44
 * @desc MesParameter
 **/
public class MesParameter {
    private String destination;
    private String mesBody;

    private MesParameter() {
        //空构造器
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMesBody() {
        return mesBody;
    }

    public void setMesBody(String mesBody) {
        this.mesBody = mesBody;
    }

    public static MesParameter getInstance(String destination, String mesBody) {
        MesParameter mesParameter = new MesParameter();
        mesParameter.setDestination(destination);
        mesParameter.setMesBody(mesBody);

        return mesParameter;
    }

    /**
     * 参数合法返回true,非法返回false
     */
    public static boolean checkMesParameter(MesParameter mesParameter) {
        if (checkStrNull(mesParameter.getDestination())
                || !checkDestination(mesParameter.getDestination())) {
            return false;
        }
        if (checkStrNull(mesParameter.getMesBody())) {
            return false;
        }
        return true;
    }

    /**
     * 字符串为null返回true,不为null返回false
     */
    private static boolean checkStrNull(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 字符串只包含一个"."返回ture,否则返回false
     */
    private static boolean checkDestination(String str) {
        return str.contains(".") && (str.indexOf(".") == str.lastIndexOf("."));
    }
}
