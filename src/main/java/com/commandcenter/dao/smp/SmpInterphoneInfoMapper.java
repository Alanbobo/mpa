package com.commandcenter.dao.smp;

import com.commandcenter.model.contacts.DeviceInfoForApp;
import com.commandcenter.model.smp.SmpInterphoneInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SmpInterphoneInfoMapper {
    int deleteByPrimaryKey(String guid);

    int deleteByPrimaryKeyLogic(SmpInterphoneInfo record);

    int insert(SmpInterphoneInfo record);

    int insertSelective(SmpInterphoneInfo record);

    SmpInterphoneInfo selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpInterphoneInfo record);

    int updateByPrimaryKeyWithBLOBs(SmpInterphoneInfo record);

    int updateByPrimaryKey(SmpInterphoneInfo record);

    void deleteAllSmpInterphoneInfo();

    List<SmpInterphoneInfo> selectByStaffGuid(String staffGuid);

    int unBindInterphoneInfo(String userGuid);
    int selectInterphoneCount();
    List<SmpInterphoneInfo> selectDevicesByStaffGuid(String staffGuid);
    int selectDeviceMaxVersion();
    List<DeviceInfoForApp> selectDevicesByOrgGuid(String orgGuid);
    SmpInterphoneInfo selectInterphoneInfoById(String guid);

    List<DeviceInfoForApp> selectDeviceInfoByMap(Map<String,Object> map);

    List<Map> selectMapDevice();
    String selectGuidByDeviceId(String deviceId);

}