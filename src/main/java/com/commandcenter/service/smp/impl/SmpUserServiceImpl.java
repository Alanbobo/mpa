package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.SmpStaffInfoMapper;
import com.commandcenter.dao.smp.SmpUserInfoMapper;
import com.commandcenter.model.smp.SmpUserInfo;
import com.commandcenter.model.smp.vo.StaffModel;
import com.commandcenter.model.smp.vo.UserModel;
import com.commandcenter.service.smp.SmpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author r25437
 * @create 2018-09-06 10:04
 * @desc impl
 **/
@Service("smpUserService")
public class SmpUserServiceImpl implements SmpUserService {
    @Autowired
    private SmpUserInfoMapper smpUserInfoMapper;

    @Autowired
    private SmpStaffInfoMapper smpStaffInfoMapper;

    @Override
    public List<UserModel> selectUserByStaffCode(String userName){
        return smpUserInfoMapper.selectUserByStaffCode(userName);
    }
    @Override
    public SmpUserInfo selectUserByGuid(String userGuid){
        return smpUserInfoMapper.selectUserByGuid(userGuid);
    }

    @Override
    public List<UserModel> selectUserByMobile(String userName){
        return smpUserInfoMapper.selectUserByMobile(userName);
    }

    @Override
    public List<UserModel> selectUserByUserCode(String userName){
        return smpUserInfoMapper.selectUserByUserCode(userName);
    }

    @Override
    public List<StaffModel> selectStaffByUserCode(String userCode){
        return smpStaffInfoMapper.selectStaffByUserCode(userCode);
    }
    @Override
    public List<Map<String,Object>> selectUserByContain(Map<String,Object> map){
        return smpUserInfoMapper.selectUserByContain(map);
    }
    @Override
    public Map<String, Object> selectUserIdByUserCode(Map<String,Object> map){
        return smpUserInfoMapper.selectUserIdByUserCode(map);
    }
    @Override
    public Map<String, Object> selectUserStaff(Map<String,Object> map){
        return smpUserInfoMapper.selectUserStaff(map);
    }
    @Override
    public List<SmpUserInfo> selectUserByOrgGuid(String orgGuid){
        return smpUserInfoMapper.selectUserByOrgGuid(orgGuid);
    }

    @Override
    public List<String> selectUserCodeListByStaffGuid(String[] array) {
        return smpUserInfoMapper.selectUserCodeListByStaffGuid(array);
    }

    @Override
    public List<String> selectLeaderCodeByOrgGovCode(String orgGovCode) {
        return smpUserInfoMapper.selectLeaderCodeByOrgGovCode(orgGovCode);
    }
}
