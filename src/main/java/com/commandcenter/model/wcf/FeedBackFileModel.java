package com.commandcenter.model.wcf;

import com.commandcenter.common.utils.MyUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author r25437
 * @create 2018-09-07 11:19
 * @desc 附件
 **/
public class FeedBackFileModel {
    private String id;
    private String fileUrl; //附件路径
    private String fileType; //附件类型
    private Date fileTime; //时间
    private String fileName; //附件名
    private String fileSize; //文件大小
    private String fileSuffix; //文件后缀
    private String fileEncode; //编码格式
    private String fileGuid; //文件 file_guid,用来获取从存储平台下载的地址
    private String originalId; //原始反馈 private String file原始反馈ＩｄGuid; //文件 file_guid,用来获取从存储平台下载的地址
    private String feedBackId; //关联反馈表和反馈附件表
    private String caseId; // 警情 Id
    private long audioSize;// 语音时长

    public AlarmAttachfile parseToAttachFile() {
        AlarmAttachfile attachfile = new AlarmAttachfile();

        String val = "";

        attachfile.setUrl(MyUtil.checkNullString(this.getFileUrl(), val));
        Date fileTime = this.getFileTime();
        if (fileTime != null) {
            attachfile.setTime(String.valueOf(fileTime.getTime()));
        } else {
            attachfile.setTime("");
        }

        attachfile.setName(MyUtil.checkNullString(this.getFileName(), val));
        attachfile.setType(MyUtil.checkNullString(this.getFileType(), val));

        attachfile.setSize(MyUtil.checkNullString(this.getFileSize(), val));
        attachfile.setSuffix(MyUtil.checkNullString(this.getFileSuffix(), val));

        attachfile.setEncode(MyUtil.checkNullString(this.getFileEncode(), val));
        attachfile.setId(MyUtil.checkNullString(this.getId(), val));

        attachfile.setFeedbackId(MyUtil.checkNullString(this.getFeedBackId(), val));
        attachfile.setCaseId(MyUtil.checkNullString(this.getCaseId(), val));
        attachfile.setFileGuid(MyUtil.checkNullString(this.getFileGuid(), val));
        //泉州附件添加返回语音时长
        attachfile.setAudioSize(this.getAudioSize());
        return attachfile;
    }

    public FeedBackFileModelForWcf parseToVcsFile() {
        FeedBackFileModelForWcf vcsFile = new FeedBackFileModelForWcf();
        vcsFile.setFileGuid(this.fileGuid);
        vcsFile.setFileGuidType("1");
        vcsFile.setFileName(this.fileName);
        vcsFile.setFileSize(this.fileSize);
        vcsFile.setFileType(this.fileType);
        vcsFile.setFileUrl(this.fileUrl);
        vcsFile.setFileEncode(this.fileEncode);
        vcsFile.setFileSuffix(this.fileSuffix);
        vcsFile.setId(this.id);
        //泉州新加字段语音时长
        vcsFile.setAudioSize(this.audioSize);
        Date date = this.fileTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        vcsFile.setCreateTime(sdf.format(date));
        return vcsFile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(String feedBackId) {
        this.feedBackId = feedBackId;
    }

    public Date getFileTime() {
        return fileTime;
    }

    public void setFileTime(Date fileTime) {
        this.fileTime = fileTime;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getFileEncode() {
        return fileEncode;
    }

    public void setFileEncode(String fileEncode) {
        this.fileEncode = fileEncode;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getFileGuid() {
        return fileGuid;
    }

    public void setFileGuid(String fileGuid) {
        this.fileGuid = fileGuid;
    }

    public String getOriginalId() {
        return originalId;
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
