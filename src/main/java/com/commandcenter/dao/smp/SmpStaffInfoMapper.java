package com.commandcenter.dao.smp;


import com.commandcenter.model.contacts.StaffForApp;
import com.commandcenter.model.smp.SmpStaffInfo;
import com.commandcenter.model.smp.vo.StaffForAppModel;
import com.commandcenter.model.smp.vo.StaffModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SmpStaffInfoMapper {
    int deleteByPrimaryKey(String guid);

    int deleteByPrimaryKeyLogic(SmpStaffInfo record);

    int insert(SmpStaffInfo record);

    int insertSelective(SmpStaffInfo record);

    SmpStaffInfo selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpStaffInfo record);

    int updateByPrimaryKeyWithBLOBs(SmpStaffInfo record);

    int updateByPrimaryKey(SmpStaffInfo record);
    void  deleteAllSmpStaffInfo();

    List<StaffModel> selectStaffByUserCode(String userCode);

    /**
     * 根据警情id，通过反馈人员信息查询警员详情
     * @param caseId
     * @return
     */
    List<StaffForAppModel> selectFeedBackStaffForAppModelByCaseId(String caseId);

    int selectStaffCount();

    /**
     * 查询staff表最大版本号
     * @return
     */
    int selectStaffMaxVersion();
    List<StaffForApp> selectStaffInfoByOrgGuid(String orgGuid);
    SmpStaffInfo selectStaffInfoByGuid(String staffGuid);
    Map selectStaffGpsByGuid(String guid);
    Map selectStaffGpsByDeviceGuid(@Param("guid")String guid);
    List<StaffForApp> selectStaffInfoByMap(Map<String,String> map);

    int selectStaffCountByOrg(Map<String, Object> map);
}