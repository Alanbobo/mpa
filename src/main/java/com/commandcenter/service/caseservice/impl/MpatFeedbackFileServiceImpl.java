package com.commandcenter.service.caseservice.impl;
import java.util.List;
import com.commandcenter.dao.casedao.MpatFeedbackFileDao;
import com.commandcenter.model.casemodel.MpatFeedbackFile;
import com.commandcenter.service.caseservice.MpatFeedbackFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("mpatFeedbackFileService")
public class MpatFeedbackFileServiceImpl implements MpatFeedbackFileService{
    @Autowired
    private MpatFeedbackFileDao mpatFeedbackFileDao;
    @Override
    public long getMpatFeedbackFileRowCount(){
        return mpatFeedbackFileDao.getMpatFeedbackFileRowCount();
    }
    @Override
    public List<MpatFeedbackFile> selectMpatFeedbackFile(){
        return mpatFeedbackFileDao.selectMpatFeedbackFile();
    }
    @Override
    public MpatFeedbackFile selectMpatFeedbackFileByObj(MpatFeedbackFile obj){
        return mpatFeedbackFileDao.selectMpatFeedbackFileByObj(obj);
    }
    @Override
    public MpatFeedbackFile selectMpatFeedbackFileById(Object id){
        return mpatFeedbackFileDao.selectMpatFeedbackFileById(id);
    }
    @Override
    public int insertMpatFeedbackFile(MpatFeedbackFile value){
        return mpatFeedbackFileDao.insertMpatFeedbackFile(value);
    }
    @Override
    public int insertNonEmptyMpatFeedbackFile(MpatFeedbackFile value){
        return mpatFeedbackFileDao.insertNonEmptyMpatFeedbackFile(value);
    }
    @Override
    public int deleteMpatFeedbackFileById(Object id){
        return mpatFeedbackFileDao.deleteMpatFeedbackFileById(id);
    }
    @Override
    public int updateMpatFeedbackFileById(MpatFeedbackFile enti){
        return mpatFeedbackFileDao.updateMpatFeedbackFileById(enti);
    }
    @Override
    public int updateNonEmptyMpatFeedbackFileById(MpatFeedbackFile enti){
        return mpatFeedbackFileDao.updateNonEmptyMpatFeedbackFileById(enti);
    }

    @Override
    public int updateFileFeedbackId(String originalId, String feedbackId){
        return mpatFeedbackFileDao.updateFileFeedbackId(originalId,feedbackId);
    }

    public MpatFeedbackFileDao getMpatFeedbackFileDao() {
        return this.mpatFeedbackFileDao;
    }

    public void setMpatFeedbackFileDao(MpatFeedbackFileDao mpatFeedbackFileDao) {
        this.mpatFeedbackFileDao = mpatFeedbackFileDao;
    }

}