package com.commandcenter.common.utils;

import com.commandcenter.common.activemq.component.PublishToVCS;
import com.commandcenter.dao.smp.SmpStaffUserMapper;
import com.commandcenter.model.smp.SmpStaffUser;
import com.commandcenter.model.smp.vo.LoginForVcs;
import com.commandcenter.model.smp.vo.StaffModel;
import com.commandcenter.modules.sys.entity.SysUserTokenEntity;
import com.commandcenter.modules.sys.service.SysUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2018/8/9.
 * 监听项目启动，项目启动后清楚sys_user_token数据，所有用户需重新登录
 * @Author
 */
@Component
public class TokenStartListener implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private SmpStaffUserMapper smpStaffUserMapper;
    @Autowired
    private PublishToVCS publishToVCS;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() != null){
            return;
        }
        List<SmpStaffUser> staffUserList = smpStaffUserMapper.selectStaffToken();
        //将用户下线信息传给后台可视化
        LoginForVcs loginForVcs = new LoginForVcs();
        loginForVcs.setISONLINE("0");
        for (SmpStaffUser staffUser:staffUserList) {
            loginForVcs.setDEVICE_ID(staffUser.getStaffGuid());
            publishToVCS.notifyLoginToVCS(loginForVcs);
        }
        sysUserTokenService.cleanToken();
    }
}
