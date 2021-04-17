package com.commandcenter.model.casemodel;
public class MpatFeedbackFile {
    private String id;//附件ID
    private String feedbackId;//反馈单号ID
    private String fileUrl;//附件路径
    private String fileType;//附件类型
    private String fileName;//附件名称
    private String caseId;//警情ID
    private String creater;//创建人
    private java.util.Date fileTime;//创建时间
    private String remark;//备注（暂无）
    private Double longitude;//经度
    private Double latitude;//纬度
    private String fileSize;//文件大小
    private String fileSuffix;//文件后缀
    private String fileEncode;//编码格式
    private String fileGuid;//文件GUID，用来获取文件下载地址
    private String originalId;//原始反馈ID
    private long audioSize;//泉州语音时长
    public MpatFeedbackFile() {
        super();
    }
    public MpatFeedbackFile(String id,String feedbackId,String fileUrl,String fileType,String fileName,String caseId,String creater,java.util.Date fileTime,String remark,Double longitude,Double latitude,String fileSize,String fileSuffix,String fileEncode,String fileGuid,String originalId,long audioSize) {
        super();
        this.id = id;
        this.feedbackId = feedbackId;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.fileName = fileName;
        this.caseId = caseId;
        this.creater = creater;
        this.fileTime = fileTime;
        this.remark = remark;
        this.longitude = longitude;
        this.latitude = latitude;
        this.fileSize = fileSize;
        this.fileSuffix = fileSuffix;
        this.fileEncode = fileEncode;
        this.fileGuid = fileGuid;
        this.originalId = originalId;
        this.audioSize = audioSize;
    }
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeedbackId() {
        return this.feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCaseId() {
        return this.caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCreater() {
        return this.creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public java.util.Date getFileTime() {
        return this.fileTime;
    }

    public void setFileTime(java.util.Date fileTime) {
        this.fileTime = fileTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSuffix() {
        return this.fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getFileEncode() {
        return this.fileEncode;
    }

    public void setFileEncode(String fileEncode) {
        this.fileEncode = fileEncode;
    }

    public String getFileGuid() {
        return this.fileGuid;
    }

    public void setFileGuid(String fileGuid) {
        this.fileGuid = fileGuid;
    }

    public String getOriginalId() {
        return this.originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public long getAudioSize() {
        return audioSize;
    }

    public void setAudioSize(long audioSize) {
        this.audioSize = audioSize;
    }
}
