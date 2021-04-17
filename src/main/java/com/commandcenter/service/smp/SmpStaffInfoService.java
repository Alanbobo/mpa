package com.commandcenter.service.smp;

import com.commandcenter.model.contacts.StaffForApp;
import com.commandcenter.model.smp.SmpStaffInfo;

import java.util.List;
import java.util.Map;

public interface SmpStaffInfoService {
    List<StaffForApp> selectStaffInfoByOrgGuid(String orgGuid);
    SmpStaffInfo selectStaffInfoByGuid(String staffGuid);
    Map selectStaffGpsByGuid(String guid);
    Map selectStaffGpsByDeviceGuid(String guid);
    List<StaffForApp> selectStaffInfoByMap(Map<String,String> map);
}
