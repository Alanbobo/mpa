package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpRoleFunctionInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SmpRoleFunctionMapper {
    void insert(SmpRoleFunctionInfo smpRoleFunctionInfo);
    void deleteByPrimaryKey(String code);
    List<SmpRoleFunctionInfo> selectByPrimaryKey(String code);
    void updateByPrimaryKeySelective(SmpRoleFunctionInfo smpRoleFunctionInfo);
    List<SmpRoleFunctionInfo> selectFunctionByuserCode(String userCode);
    void deleteAllRoleFunctionInfo();
}
