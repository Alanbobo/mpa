package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpThrDictExt;

public interface SmpThrDictExtMapper {
    int deleteByPrimaryKey(String guid);

    int insert(SmpThrDictExt record);

    int insertSelective(SmpThrDictExt record);

    SmpThrDictExt selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpThrDictExt record);

    int updateByPrimaryKey(SmpThrDictExt record);
}