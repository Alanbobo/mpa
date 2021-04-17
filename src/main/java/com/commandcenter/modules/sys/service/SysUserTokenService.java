package com.commandcenter.modules.sys.service;

import com.commandcenter.modules.sys.entity.SysUserTokenEntity;
import com.commandcenter.common.utils.R;

/**
 * 用户Token
 * 
 * @author r25437
 * @date 2017-03-23 15:22:07
 */
public interface SysUserTokenService {

	SysUserTokenEntity queryByUserId(String userId);

	void save(SysUserTokenEntity token);
	
	void update(SysUserTokenEntity token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(String userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(String userId);

	/**
	 * 清除数据库中的token信息
	 */
	void cleanToken();

}
