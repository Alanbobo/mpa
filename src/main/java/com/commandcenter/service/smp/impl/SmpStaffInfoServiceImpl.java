package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.SmpStaffInfoMapper;
import com.commandcenter.model.contacts.StaffForApp;
import com.commandcenter.model.smp.SmpStaffInfo;
import com.commandcenter.service.smp.SmpStaffInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SmpStaffInfoServiceImpl implements SmpStaffInfoService {
    @Autowired
    private SmpStaffInfoMapper smpStaffInfoMapper;
    @Override
    public List<StaffForApp> selectStaffInfoByOrgGuid(String orgGuid) {
        return smpStaffInfoMapper.selectStaffInfoByOrgGuid(orgGuid);
    }
    @Override
    public SmpStaffInfo selectStaffInfoByGuid(String staffGuid) {
        return smpStaffInfoMapper.selectStaffInfoByGuid(staffGuid);
    }

    @Override
    public Map selectStaffGpsByGuid(String guid) {
        return smpStaffInfoMapper.selectStaffGpsByGuid(guid);
    }

    @Override
    public Map selectStaffGpsByDeviceGuid(String guid) {
        return smpStaffInfoMapper.selectStaffGpsByDeviceGuid(guid);
    }

    @Override
    public List<StaffForApp> selectStaffInfoByMap(Map<String,String> map) {
        return smpStaffInfoMapper.selectStaffInfoByMap(map);
    }
}
