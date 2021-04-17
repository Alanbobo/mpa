package com.commandcenter.service.dictservice.impl;
import java.util.List;

import com.commandcenter.dao.dictdao.SmptDictValueDao;
import com.commandcenter.model.dictmodel.SmptDictValue;
import com.commandcenter.service.dictservice.SmptDictValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("smptDictValueService")
public class SmptDictValueServiceImpl implements SmptDictValueService{
    @Autowired
    private SmptDictValueDao smptDictValueDao;
    @Override
    public long getSmptDictValueRowCount(){
        return smptDictValueDao.getSmptDictValueRowCount();
    }
    @Override
    public List<SmptDictValue> selectSmptDictValue(){
        return smptDictValueDao.selectSmptDictValue();
    }
    @Override
    public SmptDictValue selectSmptDictValueByObj(SmptDictValue obj){
        return smptDictValueDao.selectSmptDictValueByObj(obj);
    }
    @Override
    public SmptDictValue selectSmptDictValueById(String id){
        return smptDictValueDao.selectSmptDictValueById(id);
    }
    @Override
    public int insertSmptDictValue(SmptDictValue value){
        return smptDictValueDao.insertSmptDictValue(value);
    }
    @Override
    public int insertNonEmptySmptDictValue(SmptDictValue value){
        return smptDictValueDao.insertNonEmptySmptDictValue(value);
    }
    @Override
    public int deleteSmptDictValueById(String id){
        return smptDictValueDao.deleteSmptDictValueById(id);
    }
    @Override
    public int updateSmptDictValueById(SmptDictValue enti){
        return smptDictValueDao.updateSmptDictValueById(enti);
    }
    @Override
    public int updateNonEmptySmptDictValueById(SmptDictValue enti){
        return smptDictValueDao.updateNonEmptySmptDictValueById(enti);
    }

    public SmptDictValueDao getSmptDictValueDao() {
        return this.smptDictValueDao;
    }

    public void setSmptDictValueDao(SmptDictValueDao smptDictValueDao) {
        this.smptDictValueDao = smptDictValueDao;
    }

}