package com.commandcenter.dao.puctomq;

import com.commandcenter.model.puctomq.PucGpsGpsData;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface PucGpsGpsDataMapper {
    int insertPucGpsGpsData(PucGpsGpsData pucGpsGpsData);
    int updatePucGpsGpsData(PucGpsGpsData pucGpsGpsData);
    Map selectGpsDataByDeviceId(String deviceId);
    PucGpsGpsData selectGpsData(String deviceId);
    int deletePucGpsDataByDeviceId(String deviceId);
}
