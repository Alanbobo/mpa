package com.commandcenter.service.smp;

import com.commandcenter.model.smp.SmpVcGpsdata;

import java.util.List;

public interface SmpVcGpsdataService {
    int deleteByPrimaryKey(String guid);

    int insert(SmpVcGpsdata record);

    int insertSelective(SmpVcGpsdata record);

    SmpVcGpsdata selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpVcGpsdata record);

    int updateByPrimaryKey(SmpVcGpsdata record);

    void deleteAllVcgpsdataInfor();

    List<SmpVcGpsdata> selectByStaffGuid(String staffGuid);
}