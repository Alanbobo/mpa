package com.commandcenter.controller.WcfController;

import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.R;
import com.commandcenter.controller.baseController.BaseController;
import com.commandcenter.model.wcf.HeartBeatModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author r25437
 * @create 2018-09-18 10:50
 * @desc VCS心跳监测
 **/
/*
@Controller
@RequestMapping("/heartbeat")
public class WcfHttpController extends BaseController {
    private static Logger log = LoggerFactory.getLogger(WcfHttpController.class);
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Value("${system.notice.vcs.server.name}")
    private String serverName;

    @Value("${system.notice.vcs.server.version}")
    private String serverVersion;

    @ResponseBody
    @RequestMapping(value = "/index")
    public Map index(String param, HttpServletRequest request) {
        log.info("进入HeartBeatController.index方法");

        JSONObject jsonObject = JSONObject.parseObject(param);
        if (jsonObject == null && jsonObject.isEmpty()) {
            log.error("HeartBeatController.index 请求参数为空");
            return R.error("参数为空");
        }
        String cmd = jsonObject.getString("cmd").toString().trim();
        //是心跳http请求
        if (Constant.HEART_BEAT.equals(cmd)) {
            HeartBeatModel heartBeatModel = new HeartBeatModel();
            heartBeatModel.setCurrentTime(format.format(new Date()));
            heartBeatModel.setServerName(serverName);
            heartBeatModel.setServerVersion(serverVersion);

            return R.ok("成功返回数据").put("info",heartBeatModel);
        }
        return R.error("其他http请求");
    }

}
*/
