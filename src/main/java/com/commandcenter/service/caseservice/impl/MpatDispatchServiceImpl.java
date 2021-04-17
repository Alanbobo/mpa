package com.commandcenter.service.caseservice.impl;
import java.util.List;
import java.util.Map;

import com.commandcenter.dao.casedao.MpatDispatchDao;
import com.commandcenter.model.casemodel.MpatDispatch;
import com.commandcenter.service.caseservice.MpatDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MpatDispatchServiceImpl implements MpatDispatchService{
    @Autowired
    private MpatDispatchDao mpatDispatchDao;
    @Override
    public long getMpatDispatchRowCount(){
        return mpatDispatchDao.getMpatDispatchRowCount();
    }
    @Override
    public List<MpatDispatch> selectMpatDispatch(){
        return mpatDispatchDao.selectMpatDispatch();
    }
    @Override
    public MpatDispatch selectMpatDispatchByObj(MpatDispatch obj){
        return mpatDispatchDao.selectMpatDispatchByObj(obj);
    }
    @Override
    public MpatDispatch selectMpatDispatchById(Object id){
        return mpatDispatchDao.selectMpatDispatchById(id);
    }
    @Override
    public int insertMpatDispatch(MpatDispatch value){
        return mpatDispatchDao.insertMpatDispatch(value);
    }
    @Override
    public int insertNonEmptyMpatDispatch(MpatDispatch value){
        return mpatDispatchDao.insertNonEmptyMpatDispatch(value);
    }
    @Override
    public int deleteMpatDispatchById(Object id){
        return mpatDispatchDao.deleteMpatDispatchById(id);
    }
    @Override
    public int updateMpatDispatchById(MpatDispatch enti){
        return mpatDispatchDao.updateMpatDispatchById(enti);
    }
    @Override
    public int updateNonEmptyMpatDispatchById(MpatDispatch enti){
        return mpatDispatchDao.updateNonEmptyMpatDispatchById(enti);
    }
    /**
     * 根据警情id查询参与警员信息
     * @param map
     * @return
     */
    @Override
    public List<MpatDispatch> selectMpatDispatchListByCaseId(Map<String,Object> map){
        return mpatDispatchDao.selectMpatDispatchListByCaseId(map);
    }

    /**
     * 查询数据库中调派表中，是否有记录
     * @param enti
     * @return
     */
    @Override
    public List<MpatDispatch> selectMpatDispatchIsExist(MpatDispatch enti){
        return mpatDispatchDao.selectMpatDispatchIsExist(enti);
    }


    public MpatDispatchDao getMpatDispatchDao() {
        return this.mpatDispatchDao;
    }

    public void setMpatDispatchDao(MpatDispatchDao mpatDispatchDao) {
        this.mpatDispatchDao = mpatDispatchDao;
    }

}