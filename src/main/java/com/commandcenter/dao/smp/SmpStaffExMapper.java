package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpStaffEx;

public interface SmpStaffExMapper {
    int deleteByPrimaryKey(String guid);

    int insert(SmpStaffEx record);

    int insertSelective(SmpStaffEx record);

    SmpStaffEx selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpStaffEx record);

    int updateByPrimaryKey(SmpStaffEx record);
}