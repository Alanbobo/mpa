package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.SmpAuthInfoMapper;
import com.commandcenter.service.smp.SmpAuthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmpAuthInfoServiceImpl implements SmpAuthInfoService {
    @Autowired
    private SmpAuthInfoMapper smpAuthInfoMapper;
    /**
     * 删除所有功能数据
     */
    @Override
    public void deleteAllAuthInfo() {
        smpAuthInfoMapper.deleteAllAuthInfo();
    }
}
