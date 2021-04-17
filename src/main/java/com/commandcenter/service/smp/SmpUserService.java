package com.commandcenter.service.smp;

import com.commandcenter.model.smp.SmpUserInfo;
import com.commandcenter.model.smp.vo.StaffModel;
import com.commandcenter.model.smp.vo.UserModel;

import java.util.List;
import java.util.Map;

/**
 * @author r25437
 * @create 2018-09-06 10:03
 * @desc smpuserinfo
 **/
public interface SmpUserService {

    //查询用户信息
    List<UserModel> selectUserByStaffCode(String userName);
    //查询用户信息
    SmpUserInfo selectUserByGuid(String userGuid);

    //查询用户信息
    List<UserModel> selectUserByMobile(String userName);

    //查询用户信息
    List<UserModel> selectUserByUserCode(String userName);

    /**
     * 根据用户名查询绑定的警员信息
     *
     * @param userCode
     * @return
     */
    List<StaffModel> selectStaffByUserCode(String userCode);
    /**
     * 查询用户信息
     *
     * @param map
     * @return
     */
    List<Map<String,Object>> selectUserByContain(Map<String, Object> map);
    Map<String, Object> selectUserIdByUserCode(Map<String, Object> map);
    Map<String, Object> selectUserStaff(Map<String, Object> map);
    List<SmpUserInfo> selectUserByOrgGuid(String orgGuid);
    //selectAuthorityByUserCode(String userCode);

    List<String> selectUserCodeListByStaffGuid(String[] list);

    List<String> selectLeaderCodeByOrgGovCode(String orgGovCode);
}
