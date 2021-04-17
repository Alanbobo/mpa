package com.commandcenter.modules.sys.service;

import com.commandcenter.model.smp.SmpUserInfo;
import com.commandcenter.model.smp.vo.StaffModel;
import com.commandcenter.modules.sys.entity.SysUserTokenEntity;

import java.util.List;
import java.util.Set;

/**
 * shiro相关接口
 * @author r25437
 * @date 2017-06-06 8:49
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SmpUserInfo queryUser(String userId);
    /**
     * 根据用户ID，查询用户
     * @param userCode
     */
    String selectStaffByUserGuid(String userCode);
}
