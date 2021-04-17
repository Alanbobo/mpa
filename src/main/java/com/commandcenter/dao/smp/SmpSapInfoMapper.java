package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpSapInfo;

public interface SmpSapInfoMapper {
    int insert(SmpSapInfo record);

    int insertSelective(SmpSapInfo record);
}