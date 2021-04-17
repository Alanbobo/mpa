package com.commandcenter.webController.TestController;

import com.commandcenter.common.utils.R;
import com.commandcenter.controller.login.SysLoginController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * 测试压测登录接口
 */
@RestController
@RequestMapping("/webController/loginTest")
public class TestLoginController extends SysLoginController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public R loginTest(@RequestBody Map<String,Object> bodyMap) throws IOException {
        return R.ok(super.login(bodyMap));
    }
}
