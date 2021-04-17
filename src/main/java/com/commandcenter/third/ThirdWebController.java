package com.commandcenter.third;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.commandcenter.common.activemq.component.PublishToAPP;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.R;
import com.commandcenter.common.utils.encrypt.EncryptUtil;
import com.commandcenter.controller.baseController.BaseController;
import com.commandcenter.model.dictmodel.SmptDictInfo;
import com.commandcenter.model.dictmodel.SmptDictValue;
import com.commandcenter.model.dictmodel.SmptLanguage;
import com.commandcenter.model.dictmodel.SmptSystemInfo;
import com.commandcenter.modules.sys.service.SysUserTokenService;
import com.commandcenter.service.appupdate.UpdateInfoService;
import com.commandcenter.service.dictservice.SmptDictInfoService;
import com.commandcenter.service.dictservice.SmptDictValueService;
import com.commandcenter.service.dictservice.SmptLanguageService;
import com.commandcenter.service.dictservice.SmptSystemInfoService;
import com.commandcenter.service.smp.SmpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: q32756
 * @Date: 2018/10/8 10:42
 * @Description:与web对接
 */
@Controller
@RequestMapping("/kick")
public class ThirdWebController extends BaseController {

    @Autowired
    private PublishToAPP publishToAPP;

    @Autowired
    private SmpUserService smpUserService;

    @Autowired
    private UpdateInfoService updateInfoService;
    @Autowired
    private SmptDictValueService smptDictValueService;
    @Autowired
    private SmptLanguageService smptLanguageService;
    @Autowired
    private SmptSystemInfoService smptSystemInfoService;

    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private SmptDictInfoService smptDictInfoService;

    @Value("${system.notice.app.AccountMQ}")
    private String AccountMQ;

    @ResponseBody
    @RequestMapping(value="/getSmptDictInfo",method = RequestMethod.POST)
    public Object selectSmptDictInfo() throws Exception{
        List<SmptDictInfo> dictInfoList = smptDictInfoService.selectSmptDictInfo();
        String json = JSONObject.toJSONString(dictInfoList, SerializerFeature.WriteMapNullValue);
        return json.getBytes("utf-8");
    }
    @ResponseBody
    @RequestMapping(value="/getSmptDictValue",method = RequestMethod.POST)
    public Object selectSmptDictValue() throws Exception{
        List<SmptDictValue> dictValueList = smptDictValueService.selectSmptDictValue();
        String json = JSONObject.toJSONString(dictValueList, SerializerFeature.WriteMapNullValue);
        return json.getBytes("utf-8");
    }
    /**
     * 获得SmptLanguage数据集合
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getSmptLanguage",method = RequestMethod.POST)
    public Object selectSmptLanguage() throws Exception{
        List<SmptLanguage> smpLanguageList =  smptLanguageService.selectSmptLanguage();
        String json = JSONObject.toJSONString(smpLanguageList, SerializerFeature.WriteMapNullValue);
        return json.getBytes("utf-8");
    }
    /**
     * 获得SmptSystemInfo数据集合
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getSmptSystem",method = RequestMethod.POST)
    public Object selectSmptSystemInfo() throws Exception{
        List<SmptSystemInfo> smpSystemList = smptSystemInfoService.selectSmptSystemInfo();
        String json = JSONObject.toJSONString(smpSystemList, SerializerFeature.WriteMapNullValue);
        return json.getBytes("utf-8");
    }



    /**
     * 传递所有登录用户信息
     *
     * @return 是否成功
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/queryUser", method = RequestMethod.GET)
    public String queryUser(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("staffName", request.getParameter("searchInfo"));
        List<Map<String, Object>> users = smpUserService.selectUserByContain(map);
        String userCode = new String();
        String guid = new String();
        String staffName = new String();
        String orgName = new String();
        String allStr = new String();
        for (Map<String, Object> user : users) {
            String tokenOld = (String) Constant.tokenMapIns.get(user.get("userCode"));
            if (null != tokenOld && !"".equals(tokenOld)) {
                 guid += (String) user.get("guid")+",";
                userCode += (String) user.get("userCode") + ",";
                staffName += (String) user.get("staffName") + ",";
                orgName += (String) user.get("orgName") + ",";
            }
        }
        allStr = guid + ";" + userCode + ";" + staffName + ";" + orgName;
        R r = EncryptUtil.encryptInput(allStr);
        JSONObject jsonObject = new JSONObject(r);
        String json = jsonObject.toString();
        return json;
    }

    /**
     * 踢出用户
     *
     * @return 是否成功
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/on", method = RequestMethod.GET)
    public String kick(@ModelAttribute("userCode") String userCode, HttpServletRequest request) {
        String s = request.getParameter("userCode");
        String tokenOld = (String) Constant.tokenMapIns.get(request.getParameter("userCode"));
        String name = tokenOld;
        String restring = "failure";
        try {
            if (null != tokenOld) {
                String msg = "{\"code\":\"0\",\"msg\":\"已强制下线，账号已丢失\"}";
                logger.info("账号(" + request.getParameter("userCode") + ")已丢失");

                MQParamModel param = new MQParamModel(msg, AccountMQ);
                param.setPersonalFlag(true);
                param.setQueueFlag(false);

                List<String> userNos = new ArrayList<>();
                userNos.add(tokenOld);
                param.setUserNos(userNos);

                //发送强制下线MQ
                publishToAPP.sendToApp(param);

                //将token失效
                Constant.tokenMapIns.remove(userCode);
                Constant.tokenMap.remove(tokenOld);
                sysUserTokenService.logout(request.getParameter("guid"));
                restring = "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        R r = EncryptUtil.encryptInput(restring);
        JSONObject jsonObject = new JSONObject(r);
        String json = jsonObject.toString();
        return json;
    }

    /**
     * 接收最新客户端版本信息
     *
     * @return 是否成功
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("appSize", (String) request.getParameter("appSize"));
        map.put("appUpdateInfo", (String) request.getParameter("appUpdateInfo"));
        map.put("serverVersionCode", (String) request.getParameter("appVersionCode"));
        map.put("appUrl", (String) request.getParameter("appUrl"));
        map.put("serverVersionName", (String) request.getParameter("appVersionName"));
        Map<String, Object> selectInfo = updateInfoService.selectAllInfo();
        if (selectInfo != null && selectInfo.size()!=0) {
            int i = updateInfoService.update(map);
            if (i == 1) {
                return "success";
            }
        } else {
            int i = updateInfoService.insert(map);
            if (i == 1) {
                return "success";
            }
        }

        return "failure";
    }


}
