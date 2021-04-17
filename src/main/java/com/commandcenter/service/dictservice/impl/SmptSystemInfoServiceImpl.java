package com.commandcenter.service.dictservice.impl;
import java.util.List;

import com.commandcenter.dao.dictdao.SmptSystemInfoDao;
import com.commandcenter.model.dictmodel.SmptSystemInfo;
import com.commandcenter.service.dictservice.SmptSystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("smptSystemInfoService")
public class SmptSystemInfoServiceImpl implements SmptSystemInfoService{
    @Autowired
    private SmptSystemInfoDao smptSystemInfoDao;
    @Override
    public long getSmptSystemInfoRowCount(){
        return smptSystemInfoDao.getSmptSystemInfoRowCount();
    }
    @Override
    public List<SmptSystemInfo> selectSmptSystemInfo(){
        return smptSystemInfoDao.selectSmptSystemInfo();
    }
    @Override
    public SmptSystemInfo selectSmptSystemInfoByObj(SmptSystemInfo obj){
        return smptSystemInfoDao.selectSmptSystemInfoByObj(obj);
    }
    @Override
    public SmptSystemInfo selectSmptSystemInfoById(String id){
        return smptSystemInfoDao.selectSmptSystemInfoById(id);
    }
    @Override
    public int insertSmptSystemInfo(SmptSystemInfo value){
        return smptSystemInfoDao.insertSmptSystemInfo(value);
    }
    @Override
    public int insertNonEmptySmptSystemInfo(SmptSystemInfo value){
        return smptSystemInfoDao.insertNonEmptySmptSystemInfo(value);
    }
    @Override
    public int deleteSmptSystemInfoById(String id){
        return smptSystemInfoDao.deleteSmptSystemInfoById(id);
    }
    @Override
    public int updateSmptSystemInfoById(SmptSystemInfo enti){
        return smptSystemInfoDao.updateSmptSystemInfoById(enti);
    }
    @Override
    public int updateNonEmptySmptSystemInfoById(SmptSystemInfo enti){
        return smptSystemInfoDao.updateNonEmptySmptSystemInfoById(enti);
    }

    public SmptSystemInfoDao getSmptSystemInfoDao() {
        return this.smptSystemInfoDao;
    }

    public void setSmptSystemInfoDao(SmptSystemInfoDao smptSystemInfoDao) {
        this.smptSystemInfoDao = smptSystemInfoDao;
    }

}