package com.commandcenter.model.ppcs;

import java.util.List;

/**
 * @author r25437
 * @create 2019-01-21 13:40
 * @desc 多日排班列表
 **/
public class DutyDetailInfo {

    /**
     * 排班明细情况列表
     */
    private List<DutyDayInfo> data;

    private String mess;

    private String type;

    private String messdetails;

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessdetails() {
        return messdetails;
    }

    public void setMessdetails(String messdetails) {
        this.messdetails = messdetails;
    }

    public List<DutyDayInfo> getData() {
        return data;
    }

    public void setData(List<DutyDayInfo> data) {
        this.data = data;
    }
}
