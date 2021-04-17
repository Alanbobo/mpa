package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpPolicestationInfo;

public interface SmpPolicestationInfoMapper {
    int deleteByPrimaryKey(String policestationGuid);

    int insert(SmpPolicestationInfo record);

    int insertSelective(SmpPolicestationInfo record);

    SmpPolicestationInfo selectByPrimaryKey(String policestationGuid);

    int updateByPrimaryKeySelective(SmpPolicestationInfo record);

    int updateByPrimaryKey(SmpPolicestationInfo record);
}