package com.commandcenter.service.constacts.impl;
import java.util.List;
import java.util.Map;

import com.commandcenter.dao.contactsdao.MpaContactsCollectDao;
import com.commandcenter.model.contacts.MpaContactsCollect;
import com.commandcenter.service.constacts.MpaContactsCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MpaContactsCollectServiceImpl implements MpaContactsCollectService{
    @Autowired
    private MpaContactsCollectDao mpaContactsCollectDao;
    @Override
    public long getMpaContactsCollectRowCount(){
        return mpaContactsCollectDao.getMpaContactsCollectRowCount();
    }
    @Override
    public List<MpaContactsCollect> selectMpaContactsCollect(Map<String,Object> map){
        return mpaContactsCollectDao.selectMpaContactsCollect(map);
    }
    @Override
    public MpaContactsCollect selectMpaContactsCollectByObj(MpaContactsCollect obj){
        return mpaContactsCollectDao.selectMpaContactsCollectByObj(obj);
    }
    @Override
    public MpaContactsCollect selectMpaContactsCollectById(String id){
        return mpaContactsCollectDao.selectMpaContactsCollectById(id);
    }
    @Override
    public int insertMpaContactsCollect(MpaContactsCollect value){
        return mpaContactsCollectDao.insertMpaContactsCollect(value);
    }
    @Override
    public int insertNonEmptyMpaContactsCollect(MpaContactsCollect value){
        return mpaContactsCollectDao.insertNonEmptyMpaContactsCollect(value);
    }
    @Override
    public int deleteMpaContactsCollectById(String id){
        return mpaContactsCollectDao.deleteMpaContactsCollectById(id);
    }
    @Override
    public int updateMpaContactsCollectById(MpaContactsCollect enti){
        return mpaContactsCollectDao.updateMpaContactsCollectById(enti);
    }
    @Override
    public int updateNonEmptyMpaContactsCollectById(MpaContactsCollect enti){
        return mpaContactsCollectDao.updateNonEmptyMpaContactsCollectById(enti);
    }
    @Override
    public int updateNonEmptyMpaContactsCollectByRoomId(MpaContactsCollect enti){
        return mpaContactsCollectDao.updateNonEmptyMpaContactsCollectByRoomId(enti);
    }

    @Override
    public int deleteMpaContactsCollectByMap(Map<String, Object> map){
        return mpaContactsCollectDao.deleteMpaContactsCollectByMap(map);
    }
    public MpaContactsCollectDao getMpaContactsCollectDao() {
        return this.mpaContactsCollectDao;
    }

    public void setMpaContactsCollectDao(MpaContactsCollectDao mpaContactsCollectDao) {
        this.mpaContactsCollectDao = mpaContactsCollectDao;
    }

}