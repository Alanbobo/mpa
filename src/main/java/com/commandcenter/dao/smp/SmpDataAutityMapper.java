package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpDataAuthority;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmpDataAutityMapper {
    void insert(SmpDataAuthority smpDataAuthority);
    void updateByPrimaryKeySelective(SmpDataAuthority smpDataAuthority);
    void deleteByPrimaryKey(String guid);
    void deleteAuthorityByroleGuid(String roleGuid);
    void deleteAllDataAuthority();
}
