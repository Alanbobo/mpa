package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpAuthInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmpAuthInfoMapper {
    void deleteAllAuthInfo();
    void insert(SmpAuthInfo smpAuthInfo);
    void deleteByAuthGuid(SmpAuthInfo smpAuthInfo);
}
