package com.commandcenter.modules.sys.dao;

import com.commandcenter.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 * 
 * @author r25437
 * @date 2017-03-23 15:22:07
 */
@Mapper
public interface SysUserTokenDao extends BaseDao<SysUserTokenEntity> {
    
    SysUserTokenEntity queryByUserId(String userId);

    SysUserTokenEntity queryByToken(String token);

    void cleanToken();
	
}
