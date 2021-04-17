package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpResources;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SmpResourcesMapper {
    int deleteByPrimaryKey(String guid);

    int deleteAllSmpResourceInfo();

    int insert(SmpResources record);

    int insertSelective(SmpResources record);

    SmpResources selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpResources record);

    int updateByPrimaryKey(SmpResources record);

    List<SmpResources> getSmpResourcesList(Map<String, Object> condition);

    int updateSmpResources(Map<String, Object> condition);
    int selectResourceNum();
    List<Map> selectResourcesList();
}