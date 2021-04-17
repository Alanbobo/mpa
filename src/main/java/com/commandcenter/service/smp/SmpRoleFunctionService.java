package com.commandcenter.service.smp;

import com.commandcenter.model.smp.SmpRoleFunctionInfo;

import java.util.List;

public interface SmpRoleFunctionService {
    List<SmpRoleFunctionInfo> selectFunctionByuserCode(String userCode);
}
