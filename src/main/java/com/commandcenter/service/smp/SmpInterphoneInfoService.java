package com.commandcenter.service.smp;

import com.commandcenter.model.contacts.DeviceInfoForApp;
import com.commandcenter.model.smp.SmpInterphoneInfo;

import java.util.List;
import java.util.Map;

public interface SmpInterphoneInfoService {
    int deleteByPrimaryKey(String guid);

    List<Map> selectMapDevice();

    int insert(SmpInterphoneInfo record);

    int insertSelective(SmpInterphoneInfo record);

    SmpInterphoneInfo selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpInterphoneInfo record);

    int updateByPrimaryKeyWithBLOBs(SmpInterphoneInfo record);

    int updateByPrimaryKey(SmpInterphoneInfo record);

    void deleteAllSmpInterphoneInfo();

    List<SmpInterphoneInfo> selectByStaffGuid(String staffGuid);
    List<SmpInterphoneInfo> selectDevicesByStaffGuid(String staffGuid);

    /**
     * 通过组织机构id查询设备信息
     * @param orgGuid
     * @return
     */
    List<DeviceInfoForApp> selectDevicesByOrgGuid(String orgGuid);
    SmpInterphoneInfo selectInterphoneInfoById(String guid);

    List<DeviceInfoForApp> selectDeviceInfoByMap(Map<String,Object> map);

    String selectGuidByDeviceId(String deviceId);
}