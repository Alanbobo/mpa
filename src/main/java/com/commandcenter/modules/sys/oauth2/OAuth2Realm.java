package com.commandcenter.modules.sys.oauth2;

import com.commandcenter.common.activemq.component.PublishToVCS;
import com.commandcenter.model.smp.SmpUserInfo;
import com.commandcenter.model.smp.vo.LoginForVcs;
import com.commandcenter.model.smp.vo.StaffModel;
import com.commandcenter.modules.sys.entity.SysUserEntity;
import com.commandcenter.modules.sys.entity.SysUserTokenEntity;
import com.commandcenter.modules.sys.service.ShiroService;
import com.commandcenter.service.smp.SmpUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 认证
 *
 * @author r25437
 * @date 2017-05-20 14:00
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private PublishToVCS publishToVCS;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
        Long userId = user.getUserId();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        //根据accessToken，查询用户信息
        SysUserTokenEntity tokenEntity = shiroService.queryByToken(accessToken);
        //token失效
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            //将用户下线信息传给后台可视化
            String staffGuid = shiroService.selectStaffByUserGuid(tokenEntity.getUserId());
            LoginForVcs loginForVcs = new LoginForVcs();
            loginForVcs.setDEVICE_ID(staffGuid);
            loginForVcs.setISONLINE("0");
            publishToVCS.notifyLoginToVCS(loginForVcs);
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        //查询用户信息
        SmpUserInfo user = shiroService.queryUser(tokenEntity.getUserId());

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
