package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpSeatInfo;

public interface SmpSeatInfoMapper {
    int deleteByPrimaryKey(String guid);

    int insert(SmpSeatInfo record);

    int insertSelective(SmpSeatInfo record);

    SmpSeatInfo selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpSeatInfo record);

    int updateByPrimaryKey(SmpSeatInfo record);
}