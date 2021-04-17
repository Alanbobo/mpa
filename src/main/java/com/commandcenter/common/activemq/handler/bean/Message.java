package com.commandcenter.common.activemq.handler.bean;

/**
 * @author r25437
 * @create 2018-09-06 11:42
 * @desc Message
 **/
public class Message {
    private MesHeader mesHeader;
    private String mesBody;

    public MesHeader getMesHeader() {
        return mesHeader;
    }

    public void setMesHeader(MesHeader mesHeader) {
        this.mesHeader = mesHeader;
    }

    public String getMesBody() {
        return mesBody;
    }

    public void setMesBody(String mesBody) {
        this.mesBody = mesBody;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mesHeader=" + mesHeader +
                ", mesBody='" + mesBody + '\'' +
                '}';
    }
}
