package com.commandcenter.service.dictservice.impl;
import java.util.List;

import com.commandcenter.dao.dictdao.SmptDictInfoDao;
import com.commandcenter.model.dictmodel.SmptDictInfo;
import com.commandcenter.service.dictservice.SmptDictInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("smptDictInfoService")
public class SmptDictInfoServiceImpl implements SmptDictInfoService{
    @Autowired
    private SmptDictInfoDao smptDictInfoDao;
    @Override
    public long getSmptDictInfoRowCount(){
        return smptDictInfoDao.getSmptDictInfoRowCount();
    }

    @Override
    public List<SmptDictInfo> selectSmptDictInfo(){
        return smptDictInfoDao.selectSmptDictInfo();
    }
    @Override
    public SmptDictInfo selectSmptDictInfoByObj(SmptDictInfo obj){
        return smptDictInfoDao.selectSmptDictInfoByObj(obj);
    }
    @Override
    public SmptDictInfo selectSmptDictInfoById(String id){
        return smptDictInfoDao.selectSmptDictInfoById(id);
    }
    @Override
    public int insertSmptDictInfo(SmptDictInfo value){
        return smptDictInfoDao.insertSmptDictInfo(value);
    }
    @Override
    public int insertNonEmptySmptDictInfo(SmptDictInfo value){
        return smptDictInfoDao.insertNonEmptySmptDictInfo(value);
    }
    @Override
    public int deleteSmptDictInfoById(String id){
        return smptDictInfoDao.deleteSmptDictInfoById(id);
    }
    @Override
    public int updateSmptDictInfoById(SmptDictInfo enti){
        return smptDictInfoDao.updateSmptDictInfoById(enti);
    }
    @Override
    public int updateNonEmptySmptDictInfoById(SmptDictInfo enti){
        return smptDictInfoDao.updateNonEmptySmptDictInfoById(enti);
    }

    public SmptDictInfoDao getSmptDictInfoDao() {
        return this.smptDictInfoDao;
    }

    public void setSmptDictInfoDao(SmptDictInfoDao smptDictInfoDao) {
        this.smptDictInfoDao = smptDictInfoDao;
    }

}