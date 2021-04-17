package com.commandcenter.common.log;

import com.commandcenter.common.utils.HttpRequest;

/**
 * @author r25437
 * @create 2019-05-17 11:36
 * @desc 异步发送日志
 **/
public class LogSendRunnable implements Runnable {
    private String url;
    private String json;
    private String key;
    public LogSendRunnable(String url,String json,String key){
        this.json = json;
        this.url = url;
        this.key = key;
    }
    @Override
    public void run(){
        try {
            HttpRequest.sendPostToLogSys(url, json, key);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
