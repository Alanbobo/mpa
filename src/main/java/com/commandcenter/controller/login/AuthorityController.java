package com.commandcenter.controller.login;

import com.commandcenter.common.utils.R;
import com.commandcenter.model.smp.SmpRoleFunctionInfo;
import com.commandcenter.service.smp.SmpRoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 判断登录用户的系统权限
 */
@RestController
public class AuthorityController {
    @Autowired
    private SmpRoleFunctionService smpRoleFunctionService;

    @RequestMapping(value = "/authority/getAuthority", method = RequestMethod.POST)
    public R getAuthority(@RequestBody Map<String,Object> bodyMap)throws IOException {
        try {
            Map<String, Object> paramMap = (Map<String, Object>) bodyMap.get("para");
            String userCode = paramMap.get("userCode").toString();
            List<SmpRoleFunctionInfo> smpRoleFunctionInfoList = smpRoleFunctionService.selectFunctionByuserCode(userCode);
            StringBuilder authorityStringBuilder = new StringBuilder();
            for(SmpRoleFunctionInfo smpRoleFunctionInfo : smpRoleFunctionInfoList){
                authorityStringBuilder.append(smpRoleFunctionInfo.getCode());
                authorityStringBuilder.append(",");
            }
            authorityStringBuilder.deleteCharAt(authorityStringBuilder.length()-1);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("authority",authorityStringBuilder.toString());
            return R.ok(map);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(200,"服务异常");
        }
    }
}
