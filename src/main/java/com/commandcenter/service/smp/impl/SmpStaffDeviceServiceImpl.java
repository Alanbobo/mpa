package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.SmpStaffDeviceMapper;
import com.commandcenter.model.contacts.StaffDeviceInfoForApp;
import com.commandcenter.model.smp.SmpStaffDeviceInfo;
import com.commandcenter.service.smp.SmpStaffDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class SmpStaffDeviceServiceImpl implements SmpStaffDeviceService {
    @Autowired
    private SmpStaffDeviceMapper smpStaffDeviceMapper;

    @Override
    public List<StaffDeviceInfoForApp> selectstaffDeviceInfoByMap(Map<String, Object> map) {
        return smpStaffDeviceMapper.selectstaffDeviceInfoByMap(map);
    }

    @Override
    public SmpStaffDeviceInfo selectStaffDeviceByDeviceGuid(String deviceGuid){
        return smpStaffDeviceMapper.selectStaffDeviceByDeviceGuid(deviceGuid);
    }
}
