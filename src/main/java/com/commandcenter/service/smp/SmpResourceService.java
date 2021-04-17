package com.commandcenter.service.smp;

import com.commandcenter.model.smp.SmpResources;

import java.util.List;
import java.util.Map;

public interface SmpResourceService {
    int deleteAllSmpResourceInfo();
    void batchInsertSmpResourceInfo(List<SmpResources> smpResourcesList, String type);
    List<Map> selectResourcesList();
}
