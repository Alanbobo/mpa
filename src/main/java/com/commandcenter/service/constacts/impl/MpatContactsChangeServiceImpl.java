package com.commandcenter.service.constacts.impl;

import com.commandcenter.dao.contactsdao.MpatContactsChangeDao;
import com.commandcenter.model.contacts.MpatContactsChange;
import com.commandcenter.service.constacts.MpatContactsChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MpatContactsChangeServiceImpl implements MpatContactsChangeService{
    @Autowired
    private MpatContactsChangeDao mpatContactsChangeDao;
    @Override
    public long getMpatContactsChangeRowCount(){
        return mpatContactsChangeDao.getMpatContactsChangeRowCount();
    }
    @Override
    public List<MpatContactsChange> selectMpatContactsChange(Map<String, Object> map){
        return mpatContactsChangeDao.selectMpatContactsChange(map);
    }
    @Override
    public MpatContactsChange selectMpatContactsChangeByObj(MpatContactsChange obj){
        return mpatContactsChangeDao.selectMpatContactsChangeByObj(obj);
    }
    @Override
    public MpatContactsChange selectMpatContactsChangeById(String id){
        return mpatContactsChangeDao.selectMpatContactsChangeById(id);
    }
    @Override
    public int insertMpatContactsChange(MpatContactsChange value){
        return mpatContactsChangeDao.insertMpatContactsChange(value);
    }
    @Override
    public int insertNonEmptyMpatContactsChange(MpatContactsChange value){
        return mpatContactsChangeDao.insertNonEmptyMpatContactsChange(value);
    }
    @Override
    public int deleteMpatContactsChangeById(String id){
        return mpatContactsChangeDao.deleteMpatContactsChangeById(id);
    }
    @Override
    public int updateMpatContactsChangeById(MpatContactsChange enti){
        return mpatContactsChangeDao.updateMpatContactsChangeById(enti);
    }
    @Override
    public int updateNonEmptyMpatContactsChangeById(MpatContactsChange enti){
        return mpatContactsChangeDao.updateNonEmptyMpatContactsChangeById(enti);
    }

    @Override
    public int deleteAll() {
        return mpatContactsChangeDao.deleteAll();
    }

    public MpatContactsChangeDao getMpatContactsChangeDao() {
        return this.mpatContactsChangeDao;
    }

    public void setMpatContactsChangeDao(MpatContactsChangeDao mpatContactsChangeDao) {
        this.mpatContactsChangeDao = mpatContactsChangeDao;
    }

}