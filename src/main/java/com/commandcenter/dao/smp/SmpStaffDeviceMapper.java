package com.commandcenter.dao.smp;

import com.commandcenter.model.contacts.StaffDeviceInfoForApp;
import com.commandcenter.model.smp.SmpStaffDeviceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SmpStaffDeviceMapper {
    void deleteAllSmpStaffDeviceInfo();
    int insertSmptStaffDeviceInfo(SmpStaffDeviceInfo smpStaffDeviceInfo);
    int updateSmptStaffDeviceById(SmpStaffDeviceInfo smpStaffDeviceInfo);
    int deleteSmptStaffDeviceById(String id);
    int selectstaffDeviceNum();
    SmpStaffDeviceInfo selectByPrimaryKey(String guid);
    int updateByPrimaryKey(SmpStaffDeviceInfo smpStaffDeviceInfo);
    int deleteSmptStaffDeviceByIdLogic(SmpStaffDeviceInfo smpStaffDeviceInfo);
    SmpStaffDeviceInfo selectStaffDeviceByDeviceGuid(String deviceGuid);

    List<StaffDeviceInfoForApp> selectstaffDeviceInfoByMap(Map<String,Object> map);
}
