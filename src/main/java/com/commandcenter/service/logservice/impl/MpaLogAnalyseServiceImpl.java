package com.commandcenter.service.logservice.impl;
import java.util.List;
import java.util.Map;

import com.commandcenter.dao.logdao.MpaLogAnalyseDao;
import com.commandcenter.model.logmodel.MpaLogAnalyse;
import com.commandcenter.service.logservice.MpaLogAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mpaLogAnalyseService")
public class MpaLogAnalyseServiceImpl implements MpaLogAnalyseService{
    @Autowired
    private MpaLogAnalyseDao mpaLogAnalyseDao;
    @Override
    public long getMpaLogAnalyseRowCount(){
        return mpaLogAnalyseDao.getMpaLogAnalyseRowCount();
    }
    @Override
    public List<MpaLogAnalyse> selectMpaLogAnalyse(){
        return mpaLogAnalyseDao.selectMpaLogAnalyse();
    }
    @Override
    public MpaLogAnalyse selectMpaLogAnalyseByObj(MpaLogAnalyse obj){
        return mpaLogAnalyseDao.selectMpaLogAnalyseByObj(obj);
    }
    @Override
    public MpaLogAnalyse selectMpaLogAnalyseById(Object id){
        return mpaLogAnalyseDao.selectMpaLogAnalyseById(id);
    }
    @Override
    public int insertMpaLogAnalyse(MpaLogAnalyse value){
        return mpaLogAnalyseDao.insertMpaLogAnalyse(value);
    }
    @Override
    public int insertNonEmptyMpaLogAnalyse(MpaLogAnalyse value){
        return mpaLogAnalyseDao.insertNonEmptyMpaLogAnalyse(value);
    }
    @Override
    public int deleteMpaLogAnalyseById(Object id){
        return mpaLogAnalyseDao.deleteMpaLogAnalyseById(id);
    }
    @Override
    public int updateMpaLogAnalyseById(MpaLogAnalyse enti){
        return mpaLogAnalyseDao.updateMpaLogAnalyseById(enti);
    }
    @Override
    public int updateNonEmptyMpaLogAnalyseById(MpaLogAnalyse enti){
        return mpaLogAnalyseDao.updateNonEmptyMpaLogAnalyseById(enti);
    }

    /**
     * 按照log_time进行数据清除
     * @param logTime
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMpaLogAnalyseByLogTime(String logTime){
        mpaLogAnalyseDao.deleteMpaLogAnalyseByLogTime(logTime);
    }

    public MpaLogAnalyseDao getMpaLogAnalyseDao() {
        return this.mpaLogAnalyseDao;
    }

    public void setMpaLogAnalyseDao(MpaLogAnalyseDao mpaLogAnalyseDao) {
        this.mpaLogAnalyseDao = mpaLogAnalyseDao;
    }
    @Override
    public int getMpaLogAnalyseRowCountByOrgTimeOpName(Map<String, Object> content){
        return mpaLogAnalyseDao.getMpaLogAnalyseRowCountByOrgTimeOpName(content);
    }
    /**
     * 根据时间分组，获取给定时间、组织机构以及方法名后的记录次数
     * @param content
     * @return
     */
    @Override
    public List<Map<String, Object>> getCountAndTimeByOrgTimeOpName(Map<String, Object> content){
        return mpaLogAnalyseDao.getCountAndTimeByOrgTimeOpName(content);
    }

}