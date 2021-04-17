package com.commandcenter.service.puctomqservice.impl;

import com.commandcenter.dao.puctomq.PucGpsGpsDataMapper;
import com.commandcenter.model.puctomq.PucGpsGpsData;
import com.commandcenter.service.puctomqservice.PucGpsGpsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PucGpsGpsDataServiceImpl  implements PucGpsGpsDataService{
    @Autowired
    private PucGpsGpsDataMapper pucGpsGpsDataMapper;
    @Override
    public int insertPucGpsGpsData(PucGpsGpsData pucGpsGpsData) {
        return pucGpsGpsDataMapper.insertPucGpsGpsData(pucGpsGpsData);
    }

    @Override
    public int updatePucGpsGpsData(PucGpsGpsData pucGpsGpsData) {
        return pucGpsGpsDataMapper.updatePucGpsGpsData(pucGpsGpsData);
    }

    @Override
    public Map selectGpsDataByDeviceId(String deviceId) {
        return pucGpsGpsDataMapper.selectGpsDataByDeviceId(deviceId);
    }

    @Override
    public PucGpsGpsData selectGpsData(String deviceId) {
        return pucGpsGpsDataMapper.selectGpsData(deviceId);
    }

    @Override
    public int deletePucGpsDataByDeviceId(String deviceId){
        return pucGpsGpsDataMapper.deletePucGpsDataByDeviceId(deviceId);
    }

}
