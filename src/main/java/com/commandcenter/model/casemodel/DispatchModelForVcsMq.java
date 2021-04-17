package com.commandcenter.model.casemodel;

/**
 * @author r25437
 * @create 2018-09-17 14:01
 * @desc 调派对象   为了接收vcs的mq 属性重新定义
 **/
public class DispatchModelForVcsMq {
    //警员id
    private String staff_guid;
    //组织机构id
    private String org_guid;
    //1.调派到单位2.调派到个人 3.取消调派
    private String type;
    //时间
    private String create_time;

    public String getStaff_guid() {
        return staff_guid;
    }

    public void setStaff_guid(String staff_guid) {
        this.staff_guid = staff_guid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getOrg_guid() {
        return org_guid;
    }

    public void setOrg_guid(String org_guid) {
        this.org_guid = org_guid;
    }
}
