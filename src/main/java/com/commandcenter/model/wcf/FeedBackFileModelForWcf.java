package com.commandcenter.model.wcf;

import com.commandcenter.common.utils.DateUtils;
import com.commandcenter.model.casemodel.MpatFeedbackFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author r25437
 * @create 2018-09-07 11:18
 * @desc 反馈
 **/
public class FeedBackFileModelForWcf {
    private String id;
    private String FileGuid;
    private String FileUrl;
    private String FileGuidType;

    private String FileName;
    private String FileType;//文件类型（1图片 2视频）
    private String FileSize;//文件大小（字节数）
    private String CreateTime;

    //向WCF上报需要用到的字段
    private String FileEncode;
    private String FileSuffix;
    //泉州新加字段，语音时长
    private long audioSize;

    public MpatFeedbackFile praseToFeedBackFileModel() {
        MpatFeedbackFile mpatFeedbackFile = new MpatFeedbackFile();

        mpatFeedbackFile.setFileGuid(this.FileGuid);
        mpatFeedbackFile.setFileUrl(this.FileUrl);
        mpatFeedbackFile.setFileType(this.FileType);
        mpatFeedbackFile.setFileEncode(this.FileEncode);
        mpatFeedbackFile.setFileName(this.FileName);
        mpatFeedbackFile.setFileSize(this.FileSize);

        String time = this.CreateTime;
        //http file附件时间格式更改
        if (!(null == time || "".equals(time))) {

            mpatFeedbackFile.setFileTime(DateUtils.format(time,"yyyy-MM-dd HH:mm:ss"));
        }


        mpatFeedbackFile.setFileSuffix(this.FileSuffix);
        mpatFeedbackFile.setId(this.id);
        //泉州添加语音时长
        mpatFeedbackFile.setAudioSize(this.audioSize);

        return mpatFeedbackFile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileGuid() {
        return FileGuid;
    }

    public void setFileGuid(String fileGuid) {
        FileGuid = fileGuid;
    }

    public String getFileUrl() {
        return FileUrl;
    }

    public void setFileUrl(String fileUrl) {
        FileUrl = fileUrl;
    }

    public String getFileGuidType() {
        return FileGuidType;
    }

    public void setFileGuidType(String fileGuidType) {
        FileGuidType = fileGuidType;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public String getFileSize() {
        return FileSize;
    }

    public void setFileSize(String fileSize) {
        FileSize = fileSize;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getFileEncode() {
        return FileEncode;
    }

    public void setFileEncode(String fileEncode) {
        FileEncode = fileEncode;
    }

    public String getFileSuffix() {
        return FileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        FileSuffix = fileSuffix;
    }

    public long getAudioSize() {
        return audioSize;
    }

    public void setAudioSize(long audioSize) {
        this.audioSize = audioSize;
    }
}
