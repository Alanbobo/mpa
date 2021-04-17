package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpPucidInfo;

public interface SmpPucidInfoMapper {
    int insert(SmpPucidInfo record);

    int insertSelective(SmpPucidInfo record);
}