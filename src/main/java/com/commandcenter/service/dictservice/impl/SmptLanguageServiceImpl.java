package com.commandcenter.service.dictservice.impl;
import java.util.List;

import com.commandcenter.dao.dictdao.SmptLanguageDao;
import com.commandcenter.model.dictmodel.SmptLanguage;
import com.commandcenter.service.dictservice.SmptLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("smptLanguageService")
public class SmptLanguageServiceImpl implements SmptLanguageService{
    @Autowired
    private SmptLanguageDao smptLanguageDao;
    @Override
    public long getSmptLanguageRowCount(){
        return smptLanguageDao.getSmptLanguageRowCount();
    }
    @Override
    public List<SmptLanguage> selectSmptLanguage(){
        return smptLanguageDao.selectSmptLanguage();
    }
    @Override
    public SmptLanguage selectSmptLanguageByObj(SmptLanguage obj){
        return smptLanguageDao.selectSmptLanguageByObj(obj);
    }
    @Override
    public SmptLanguage selectSmptLanguageById(String id){
        return smptLanguageDao.selectSmptLanguageById(id);
    }
    @Override
    public int insertSmptLanguage(SmptLanguage value){
        return smptLanguageDao.insertSmptLanguage(value);
    }
    @Override
    public int insertNonEmptySmptLanguage(SmptLanguage value){
        return smptLanguageDao.insertNonEmptySmptLanguage(value);
    }
    @Override
    public int deleteSmptLanguageById(String id){
        return smptLanguageDao.deleteSmptLanguageById(id);
    }
    @Override
    public int updateSmptLanguageById(SmptLanguage enti){
        return smptLanguageDao.updateSmptLanguageById(enti);
    }
    @Override
    public int updateNonEmptySmptLanguageById(SmptLanguage enti){
        return smptLanguageDao.updateNonEmptySmptLanguageById(enti);
    }

    public SmptLanguageDao getSmptLanguageDao() {
        return this.smptLanguageDao;
    }

    public void setSmptLanguageDao(SmptLanguageDao smptLanguageDao) {
        this.smptLanguageDao = smptLanguageDao;
    }

}