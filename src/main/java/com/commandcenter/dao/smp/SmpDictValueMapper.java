package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpDictValue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmpDictValueMapper {
    int deleteByPrimaryKey(String guid);

    int insert(SmpDictValue record);

    int insertSelective(SmpDictValue record);

    SmpDictValue selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpDictValue record);

    int updateByPrimaryKey(SmpDictValue record);

    void deleteAllSmpDictValue();
    int selectdictValueCount();
}