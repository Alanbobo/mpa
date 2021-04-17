package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.SmptSmartappInfoDao;
import com.commandcenter.model.contacts.SmartAppForApp;
import com.commandcenter.model.smp.SmptSmartappInfo;
import com.commandcenter.service.smp.SmptSmartappInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SmptSmartappInfoServiceImpl implements SmptSmartappInfoService{
    @Autowired
    private SmptSmartappInfoDao smptSmartappInfoDao;
    @Override
    public long getSmptSmartappInfoRowCount(){
        return smptSmartappInfoDao.getSmptSmartappInfoRowCount();
    }
    @Override
    public List<SmptSmartappInfo> selectSmptSmartappInfo(){
        return smptSmartappInfoDao.selectSmptSmartappInfo();
    }
    @Override
    public SmptSmartappInfo selectSmptSmartappInfoByObj(SmptSmartappInfo obj){
        return smptSmartappInfoDao.selectSmptSmartappInfoByObj(obj);
    }
    @Override
    public SmptSmartappInfo selectSmptSmartappInfoById(String id){
        return smptSmartappInfoDao.selectSmptSmartappInfoById(id);
    }
    @Override
    public int insertSmptSmartappInfo(SmptSmartappInfo value){
        return smptSmartappInfoDao.insertSmptSmartappInfo(value);
    }
    @Override
    public int insertNonEmptySmptSmartappInfo(SmptSmartappInfo value){
        return smptSmartappInfoDao.insertNonEmptySmptSmartappInfo(value);
    }
    @Override
    public int deleteSmptSmartappInfoById(String id){
        return smptSmartappInfoDao.deleteSmptSmartappInfoById(id);
    }
    @Override
    public int updateSmptSmartappInfoById(SmptSmartappInfo enti){
        return smptSmartappInfoDao.updateSmptSmartappInfoById(enti);
    }
    @Override
    public int updateNonEmptySmptSmartappInfoById(SmptSmartappInfo enti){
        return smptSmartappInfoDao.updateNonEmptySmptSmartappInfoById(enti);
    }

    public SmptSmartappInfoDao getSmptSmartappInfoDao() {
        return this.smptSmartappInfoDao;
    }

    public void setSmptSmartappInfoDao(SmptSmartappInfoDao smptSmartappInfoDao) {
        this.smptSmartappInfoDao = smptSmartappInfoDao;
    }

    /**
     * 通过map查询SmptSmartappInfo中属性不为null的数据
     * @param map
     * @return
     */
    @Override
    public SmptSmartappInfo selectSmptSmartappInfoByMap(Map<String,Object> map){
        return smptSmartappInfoDao.selectSmptSmartappInfoByMap(map);
    }

    @Override
    public List<SmartAppForApp> seleSmartappInfoNullStaff(String orgGuid) {
        return smptSmartappInfoDao.seleSmartappInfoNullStaff(orgGuid);
    }

    @Override
    public SmptSmartappInfo selectSmptSmartappInfoByStaffGuid(String staffGuid) {
        return smptSmartappInfoDao.selectSmptSmartappInfoByStaffGuid(staffGuid);
    }

    @Override
    public List<SmartAppForApp> selectSmartInfoByMap(Map<String,Object> map) {
        return smptSmartappInfoDao.selectSmartInfoByMap(map);
    }

}