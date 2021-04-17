package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.SmpInterphoneInfoMapper;
import com.commandcenter.model.contacts.DeviceInfoForApp;
import com.commandcenter.model.smp.SmpInterphoneInfo;
import com.commandcenter.service.smp.SmpInterphoneInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("smpInterphoneInfoService")
public class SmpInterphoneInfoServiceImpl implements SmpInterphoneInfoService {
    @Autowired
    SmpInterphoneInfoMapper smpInterphoneInfoMapper;
    @Override
    public int deleteByPrimaryKey(String guid) {
        return smpInterphoneInfoMapper.deleteByPrimaryKey(guid);
    }

    @Override
    public List<Map> selectMapDevice() {
        return smpInterphoneInfoMapper.selectMapDevice();
    }

    @Override
    public int insert(SmpInterphoneInfo record) {
        return smpInterphoneInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(SmpInterphoneInfo record) {
        return smpInterphoneInfoMapper.insertSelective(record);
    }

    @Override
    public SmpInterphoneInfo selectByPrimaryKey(String guid) {
        return smpInterphoneInfoMapper.selectByPrimaryKey(guid);
    }

    @Override
    public int updateByPrimaryKeySelective(SmpInterphoneInfo record) {
        return smpInterphoneInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(SmpInterphoneInfo record) {
        return smpInterphoneInfoMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(SmpInterphoneInfo record) {
        return smpInterphoneInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public void deleteAllSmpInterphoneInfo() {
        smpInterphoneInfoMapper.deleteAllSmpInterphoneInfo();

    }

    @Override
    public List<SmpInterphoneInfo> selectByStaffGuid(String staffGuid) {
        return smpInterphoneInfoMapper.selectByStaffGuid(staffGuid);
    }
    @Override
    public List<SmpInterphoneInfo> selectDevicesByStaffGuid(String staffGuid) {
        return smpInterphoneInfoMapper.selectDevicesByStaffGuid(staffGuid);
    }

    @Override
    public List<DeviceInfoForApp> selectDevicesByOrgGuid(String orgGuid) {
        return smpInterphoneInfoMapper.selectDevicesByOrgGuid(orgGuid);
    }
    @Override
    public SmpInterphoneInfo selectInterphoneInfoById(String guid) {
        return smpInterphoneInfoMapper.selectInterphoneInfoById(guid);
    }

    @Override
    public List<DeviceInfoForApp> selectDeviceInfoByMap(Map<String,Object> map) {
        return smpInterphoneInfoMapper.selectDeviceInfoByMap(map);
    }

    @Override
    public String selectGuidByDeviceId(String deviceId) {
        return smpInterphoneInfoMapper.selectGuidByDeviceId(deviceId);
    }
}