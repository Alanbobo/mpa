package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpUserInfo;
import com.commandcenter.model.smp.vo.StaffModel;
import com.commandcenter.model.smp.vo.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SmpUserInfoMapper {
    int deleteByPrimaryKey(String guid);

    int insert(SmpUserInfo record);

    int insertSelective(SmpUserInfo record);

    SmpUserInfo selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpUserInfo record);

    int updateByPrimaryKey(SmpUserInfo record);

    //查询用户信息
    List<UserModel> selectUserByStaffCode(String userName);

    //查询用户信息
    List<UserModel> selectUserByMobile(String userName);

    //查询用户信息
    List<UserModel> selectUserByUserCode(String userName);

    List<StaffModel> selectStaffByUserCode(String userCode);

    void deleteAllUserInfor();

    List<UserModel> selectUserByMap(Map<String, Object> map);

    List<SmpUserInfo> selectUserByOrgGuid(String orgGuid);

    List<Map<String, Object>> selectUserByContain(Map<String, Object> map);
    Map<String, Object> selectUserIdByUserCode(Map<String, Object> map);
    Map<String, Object> selectUserStaff(Map<String, Object> map);

    UserModel selectUserByStaffGuid(String staffGuid);
    int selectUserCount();
    int selectUserMaxVersion();

    SmpUserInfo selectUserByGuid(String userGuid);

    List<String> selectUserCodeListByStaffGuid(String[] array);

    List<String> selectLeaderCodeByOrgGovCode(@Param("orgGovCode")String orgGovCode);

}