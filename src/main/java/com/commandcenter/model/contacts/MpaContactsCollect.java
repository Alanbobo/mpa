package com.commandcenter.model.contacts;
public class MpaContactsCollect {
    private String guid;//主键
    private String userGuid;//用户唯一标识
    private String roomGuid;//聊天室guid
    private String roomName;//聊天室名称
    private String roomNumber;//数据信息（设备，组，调度员账号）
    private String roomNumberType;//数据类型（设备，组，调度员账号，临时组）
    private String roomPucId;//pucId
    private String roomSystemId;//systemId
    private String roomContent;//组成员信息，可空
    private String roomQp;//聊天室全拼
    private String roomJp;//聊天室简拼
    private java.util.Date updateTime;//更新时间
    public MpaContactsCollect() {
        super();
    }
    public MpaContactsCollect(String guid,String userGuid,String roomGuid,String roomName,String roomNumber,String roomNumberType,String roomPucId,String roomSystemId,String roomContent,String roomQp,String roomJp,java.util.Date updateTime) {
        super();
        this.guid = guid;
        this.userGuid = userGuid;
        this.roomGuid = roomGuid;
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.roomNumberType = roomNumberType;
        this.roomPucId = roomPucId;
        this.roomSystemId = roomSystemId;
        this.roomContent = roomContent;
        this.roomQp = roomQp;
        this.roomJp = roomJp;
        this.updateTime = updateTime;
    }
    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUserGuid() {
        return this.userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String getRoomGuid() {
        return this.roomGuid;
    }

    public void setRoomGuid(String roomGuid) {
        this.roomGuid = roomGuid;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumberType() {
        return this.roomNumberType;
    }

    public void setRoomNumberType(String roomNumberType) {
        this.roomNumberType = roomNumberType;
    }

    public String getRoomPucId() {
        return this.roomPucId;
    }

    public void setRoomPucId(String roomPucId) {
        this.roomPucId = roomPucId;
    }

    public String getRoomSystemId() {
        return this.roomSystemId;
    }

    public void setRoomSystemId(String roomSystemId) {
        this.roomSystemId = roomSystemId;
    }

    public String getRoomContent() {
        return this.roomContent;
    }

    public void setRoomContent(String roomContent) {
        this.roomContent = roomContent;
    }

    public String getRoomQp() {
        return this.roomQp;
    }

    public void setRoomQp(String roomQp) {
        this.roomQp = roomQp;
    }

    public String getRoomJp() {
        return this.roomJp;
    }

    public void setRoomJp(String roomJp) {
        this.roomJp = roomJp;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

}
