package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpCarInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface SmpCarInfoMapper {
    int deleteByPrimaryKey(String guid);

    int insert(SmpCarInfo record);

    int insertSelective(SmpCarInfo record);

    SmpCarInfo selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpCarInfo record);

    int updateByPrimaryKey(SmpCarInfo record);

    int deleteAllSmpVehicleInfo();

    int selectVehicleCount();
    List<Map> selectMapVehicle();
    Map selectVehicleGpsByGuid(String guid);
}