package com.commandcenter.model.smp;


import java.io.Serializable;
import java.util.Date;

/**
 * 岗位表
 *
 * @author rendi
 * @date 2020-03-5 12:43:05
 */
public class PostInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    private String guid;
    /**
     * 岗位名称
     */

    private String name;
    /**
     * 简称
     */

    private String shortName;
    /**
     * 任务类型
     */

    private String taskType;
    /**
     * 归属部门
     */

    private String orgGuid;
    private String orgName;
    /**
     * 备注
     */

    private String remark;
    /**
     * 创建人
     */

    private String createUser;
    /**
     * 创建时间
     */

    private Date createTime;
    /**
     * 修改人,取最后一次修改值
     */

    private String updateUser;
    /**
     * 修改时间,取最后一次修改值
     */

    private Date updateTime;
    /**
     * 0删除1未删除
     */

    private Short enableFlag;
    /**
     * 版本号
     */

    private Long version;

    private String taskTypeStr;

    private Integer sort;
    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getGuid() {
        return guid;
    }

    public void setName(String name) {
        this.name = name==null?null:name.trim();
    }

    public String getName() {
        return name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName==null?null:shortName.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getOrgGuid() {
        return orgGuid;
    }
    public void setOrgName(String orgName){
        this.orgName=orgName;
    }
    public String getOrgName(){
        return orgName;
    }

    public void setRemark(String remark) {
        this.remark = remark==null?null:remark.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setEnableFlag(Short enableFlag) {
        this.enableFlag = enableFlag;
    }

    public Short getEnableFlag() {
        return enableFlag;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getVersion() {
        return version;
    }

    public String getTaskTypeStr() {
        return taskTypeStr;
    }

    public void setTaskTypeStr(String taskTypeStr) {
        this.taskTypeStr = taskTypeStr;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
