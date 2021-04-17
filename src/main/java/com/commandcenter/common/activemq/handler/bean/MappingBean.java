package com.commandcenter.common.activemq.handler.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author r25437
 * @create 2018-09-06 11:45
 * @desc MappingBean
 **/
public class MappingBean {
    private CountDownLatch latch;
    private Date date;
    private String response;

    private MappingBean(CountDownLatch latch) {
        setLatch(latch);
        setDate(Calendar.getInstance().getTime());
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    private void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public Date getDate() {
        return date;
    }

    private void setDate(Date date) {
        this.date = date;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public static MappingBean getInstance(CountDownLatch latch) {
        return new MappingBean(latch);
    }
}
