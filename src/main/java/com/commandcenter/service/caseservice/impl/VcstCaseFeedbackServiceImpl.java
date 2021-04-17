package com.commandcenter.service.caseservice.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.commandcenter.common.utils.Constant;
import com.commandcenter.dao.casedao.MpatFeedbackFileDao;
import com.commandcenter.dao.casedao.VcstCaseFeedbackDao;
import com.commandcenter.model.casemodel.MpatFeedbackFile;
import com.commandcenter.model.casemodel.VcstCaseFeedback;
import com.commandcenter.service.caseservice.VcstCaseFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("vcstCaseFeedbackService")
public class VcstCaseFeedbackServiceImpl implements VcstCaseFeedbackService{
    @Autowired
    private VcstCaseFeedbackDao vcstCaseFeedbackDao;
    @Autowired
    private MpatFeedbackFileDao mpatFeedbackFileDao;
    @Override
    public long getVcstCaseFeedbackRowCount(){
        return vcstCaseFeedbackDao.getVcstCaseFeedbackRowCount();
    }
    @Override
    public List<VcstCaseFeedback> selectVcstCaseFeedback(){
        return vcstCaseFeedbackDao.selectVcstCaseFeedback();
    }
    @Override
    public List<VcstCaseFeedback> selectVcstCaseFeedbackByObj(VcstCaseFeedback obj){
        return vcstCaseFeedbackDao.selectVcstCaseFeedbackByObj(obj);
    }
    @Override
    public VcstCaseFeedback selectVcstCaseFeedbackById(String id){
        return vcstCaseFeedbackDao.selectVcstCaseFeedbackById(id);
    }
    @Override
    public int insertVcstCaseFeedback(VcstCaseFeedback value){
        return vcstCaseFeedbackDao.insertVcstCaseFeedback(value);
    }
    @Override
    public int insertNonEmptyVcstCaseFeedback(VcstCaseFeedback value){
        return vcstCaseFeedbackDao.insertNonEmptyVcstCaseFeedback(value);
    }
    @Override
    public int deleteVcstCaseFeedbackById(String id){
        return vcstCaseFeedbackDao.deleteVcstCaseFeedbackById(id);
    }
    @Override
    public int updateVcstCaseFeedbackById(VcstCaseFeedback enti){
        return vcstCaseFeedbackDao.updateVcstCaseFeedbackById(enti);
    }
    @Override
    public int updateNonEmptyVcstCaseFeedbackById(VcstCaseFeedback enti){
        return vcstCaseFeedbackDao.updateNonEmptyVcstCaseFeedbackById(enti);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchInsertFeedback(List<VcstCaseFeedback> vcstCaseFeedbackList){
        if (null == vcstCaseFeedbackList || vcstCaseFeedbackList.size() < 1) {
            return;
        }
        List<String> ids = vcstCaseFeedbackList.stream().map(VcstCaseFeedback::getId).collect(Collectors.toList());
        vcstCaseFeedbackDao.deleteVcstCaseFeedbackByIds(ids);
        List<VcstCaseFeedback> vcstCaseFeedbackListTemp = null;
        for(int i=0;i<vcstCaseFeedbackList.size();){
            vcstCaseFeedbackListTemp = new ArrayList<>(Constant.BATCH_COUNT);
            for (int j = i; j < vcstCaseFeedbackList.size() && j < i + Constant.BATCH_COUNT; j++) {
                vcstCaseFeedbackListTemp.add(vcstCaseFeedbackList.get(j));
            }
            vcstCaseFeedbackDao.batchInsertFeedback(vcstCaseFeedbackListTemp);
            i = i + Constant.BATCH_COUNT;
        }

        for(VcstCaseFeedback vcstCaseFeedback : vcstCaseFeedbackList) {
            List<MpatFeedbackFile> mpatFeedbackFileList = vcstCaseFeedback.getMpatFeedbackFileList();
            if (mpatFeedbackFileList!=null && mpatFeedbackFileList.size()>0){
                mpatFeedbackFileDao.deleteByFeedbackId(vcstCaseFeedback.getId());
                List<MpatFeedbackFile> mpatFeedbackFileListInsert = new ArrayList<>();
                for(MpatFeedbackFile mpatFeedbackFile : mpatFeedbackFileList){
                    mpatFeedbackFile.setFeedbackId(vcstCaseFeedback.getId());
                    mpatFeedbackFileListInsert.add(mpatFeedbackFile);
                }
                mpatFeedbackFileDao.batchInsert(mpatFeedbackFileListInsert);
            }
        }
    }


    public VcstCaseFeedbackDao getVcstCaseFeedbackDao() {
        return this.vcstCaseFeedbackDao;
    }

    public void setVcstCaseFeedbackDao(VcstCaseFeedbackDao vcstCaseFeedbackDao) {
        this.vcstCaseFeedbackDao = vcstCaseFeedbackDao;
    }

    @Override
    public List<String> getArrivedStaffList(Map<String, Object> map) {
        return vcstCaseFeedbackDao.getArrivedStaffList(map);
    }
}