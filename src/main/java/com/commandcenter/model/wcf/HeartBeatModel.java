package com.commandcenter.model.wcf;

/**
 * @author r25437
 * @create 2018-09-07 11:23
 * @desc 心跳
 **/
public class HeartBeatModel {

    private String serverName;
    private String serverVersion;
    private String currentTime;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
