package com.commandcenter.model.logmodel;

import java.util.HashMap;
import java.util.List;

/**
 * @author r25437
 * @create 2019-05-15 11:52
 * @desc 日志工具类
 **/
public class LoggerData {

    private String uuid;
    private String source;
    private String date;
    private List<String> tags;
    private String message;
    private HashMap data;
    public void setSource(String source) {
        this.source = source;
    }
    public String getSource() {
        return source;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public List<String> getTags() {
        return tags;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public HashMap getData() {
        return data;
    }

    public void setData(HashMap data) {
        this.data = data;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
