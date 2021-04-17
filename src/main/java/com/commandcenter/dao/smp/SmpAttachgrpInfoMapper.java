package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpAttachgrpInfo;

public interface SmpAttachgrpInfoMapper {
    int insert(SmpAttachgrpInfo record);

    int insertSelective(SmpAttachgrpInfo record);
}