package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpVcGpsdata;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmpVcGpsdataMapper {
    int deleteByPrimaryKey(String guid);

    int insert(SmpVcGpsdata record);

    int insertSelective(SmpVcGpsdata record);

    SmpVcGpsdata selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpVcGpsdata record);

    int updateByPrimaryKey(SmpVcGpsdata record);

    void deleteAllVcgpsdataInfor();

    List<SmpVcGpsdata> selectByStaffGuid(String staffGuid);

    void unBindVcGpsdata(String staffGuid);

    int selectVcsGpsCount();
}