package com.commandcenter.service.smp;

import com.commandcenter.model.contacts.StaffDeviceInfoForApp;
import com.commandcenter.model.smp.SmpStaffDeviceInfo;

import java.util.List;
import java.util.Map;

public interface SmpStaffDeviceService {
    List<StaffDeviceInfoForApp> selectstaffDeviceInfoByMap(Map<String,Object> map);
    SmpStaffDeviceInfo selectStaffDeviceByDeviceGuid(String deviceGuid);
}
