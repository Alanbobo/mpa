package com.commandcenter.modules.sys.service.impl;

import com.commandcenter.dao.smp.SmpStaffInfoMapper;
import com.commandcenter.dao.smp.SmpStaffUserMapper;
import com.commandcenter.dao.smp.SmpUserInfoMapper;
import com.commandcenter.model.smp.SmpUserInfo;
import com.commandcenter.model.smp.vo.StaffModel;
import com.commandcenter.modules.sys.dao.SysUserTokenDao;
import com.commandcenter.modules.sys.entity.SysUserTokenEntity;
import com.commandcenter.modules.sys.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysUserTokenDao sysUserTokenDao;
    @Autowired
    private SmpUserInfoMapper smpUserInfoMapper;
    @Autowired
    private SmpStaffUserMapper smpStaffUserMapper;

    @Override
    public Set<String> getUserPermissions(long userId) {
        Set<String> permsSet = new HashSet<>();
        return permsSet;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        return sysUserTokenDao.queryByToken(token);
    }

    @Override
    public SmpUserInfo queryUser(String userId) {
        return smpUserInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public String selectStaffByUserGuid(String userGuid) {
        return smpStaffUserMapper.selectStaffByUserGuid(userGuid);
    }
}
