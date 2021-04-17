package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.SmpVersionInfoMapper;
import com.commandcenter.model.smp.SmpVersionInfo;
import com.commandcenter.service.smp.SmpVersionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmpVersionInfoServiceImpl implements SmpVersionInfoService {
    @Autowired
    private SmpVersionInfoMapper smpVersionInfoMapper;
    @Override
    public int insertVersionInfo(SmpVersionInfo smpVersionInfo){
        return smpVersionInfoMapper.insertVersionInfo(smpVersionInfo);
    }
    @Override
    public int updateVersionInfo(SmpVersionInfo smpVersionInfo){
        return smpVersionInfoMapper.updateVersionInfo(smpVersionInfo);
    }
    @Override
    public int selectVersionByDataType(String dataType){
        return smpVersionInfoMapper.selectVersionByDataType(dataType);
    }
    @Override
    public List<SmpVersionInfo> selectVersionInfo(String dataType){
        return smpVersionInfoMapper.selectVersionInfo(dataType);
    }
}
