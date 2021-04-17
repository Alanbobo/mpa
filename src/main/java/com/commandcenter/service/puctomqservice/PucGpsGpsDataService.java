package com.commandcenter.service.puctomqservice;

import com.commandcenter.model.puctomq.PucGpsGpsData;

import java.util.Map;

public interface PucGpsGpsDataService {
    int insertPucGpsGpsData(PucGpsGpsData pucGpsGpsData);
    int updatePucGpsGpsData(PucGpsGpsData pucGpsGpsData);
    Map selectGpsDataByDeviceId(String deviceId);
    PucGpsGpsData selectGpsData(String deviceId);
    int deletePucGpsDataByDeviceId(String deviceId);

}
