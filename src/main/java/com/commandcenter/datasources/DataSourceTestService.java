package com.commandcenter.datasources;

import com.commandcenter.modules.app.entity.UserEntity;
import com.commandcenter.modules.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试
 * @author r25437
 * @date 2017/9/16 23:10
 */
@Service
public class DataSourceTestService {
    @Autowired
    private UserService userService;

    public UserEntity queryObject(Long userId){
        return userService.queryObject(userId);
    }

    public UserEntity queryObject2(Long userId){
        return userService.queryObject(userId);
    }
}
