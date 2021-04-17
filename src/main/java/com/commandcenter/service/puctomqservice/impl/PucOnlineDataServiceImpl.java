package com.commandcenter.service.puctomqservice.impl;

import com.commandcenter.dao.puctomq.PucOnlineDataMapper;
import com.commandcenter.model.puctomq.PucOnlineData;
import com.commandcenter.service.puctomqservice.PucOnlineDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PucOnlineDataServiceImpl implements PucOnlineDataService {
    @Autowired
    private PucOnlineDataMapper pucOnlineDataMapper;
    @Override
    public long getPucOnlineDataRowCount(){
        return pucOnlineDataMapper.getPucOnlineDataRowCount();
    }
    @Override
    public List<PucOnlineData> selectPucOnlineData(){
        return pucOnlineDataMapper.selectPucOnlineData();
    }
    @Override
    public PucOnlineData selectPucOnlineDataByObj(PucOnlineData obj){
        return pucOnlineDataMapper.selectPucOnlineDataByObj(obj);
    }
    @Override
    public PucOnlineData selectPucOnlineDataById(String id){
        return pucOnlineDataMapper.selectPucOnlineDataById(id);
    }
    @Override
    public int insertPucOnlineData(PucOnlineData value){
        return pucOnlineDataMapper.insertPucOnlineData(value);
    }
    @Override
    public int insertNonEmptyPucOnlineData(PucOnlineData value){
        return pucOnlineDataMapper.insertNonEmptyPucOnlineData(value);
    }
    @Override
    public int deletePucOnlineDataById(String id){
        return pucOnlineDataMapper.deletePucOnlineDataById(id);
    }
    @Override
    public int updatePucOnlineDataById(PucOnlineData enti){
        return pucOnlineDataMapper.updatePucOnlineDataById(enti);
    }
    @Override
    public int updateNonEmptyPucOnlineDataById(PucOnlineData enti){
        return pucOnlineDataMapper.updateNonEmptyPucOnlineDataById(enti);
    }

    @Override
    public List<Map<String,Object>> selectPucOnlineDataByMap(Map<String,Object> map){
        return pucOnlineDataMapper.selectPucOnlineDataByMap(map);
    }

    public PucOnlineDataMapper getPucOnlineDataMapper() {
        return this.pucOnlineDataMapper;
    }

    public void setPucOnlineDataMapper(PucOnlineDataMapper pucOnlineDataMapper) {
        this.pucOnlineDataMapper = pucOnlineDataMapper;
    }

}