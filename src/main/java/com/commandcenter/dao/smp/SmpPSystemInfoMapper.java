package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpPSystemInfo;

public interface SmpPSystemInfoMapper {
    int insert(SmpPSystemInfo record);

    int insertSelective(SmpPSystemInfo record);
}