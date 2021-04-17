package com.commandcenter.service.smp;

import com.commandcenter.model.smp.SmpCarInfo;

import java.util.List;
import java.util.Map;

public interface SmpVehicleService {
    int deleteAllSmpVehicleInfo();
    void batchInsertSmpVehicleInfo(List<SmpCarInfo> smpCarInfoList, String type);
    int selectVehicleCount();
    List<Map> selectMapVehicle();
    Map selectVehicleGpsByGuid(String guid);
}
