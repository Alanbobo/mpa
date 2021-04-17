package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.SmpRoleFunctionMapper;
import com.commandcenter.model.smp.SmpRoleFunctionInfo;
import com.commandcenter.service.smp.SmpRoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SmpRoleFunctionServiceImpl implements SmpRoleFunctionService {
    @Autowired
    private SmpRoleFunctionMapper smpRoleFunctionMapper;
    @Override
    public List<SmpRoleFunctionInfo> selectFunctionByuserCode(String userCode) {
        return smpRoleFunctionMapper.selectFunctionByuserCode(userCode);
    }
}
