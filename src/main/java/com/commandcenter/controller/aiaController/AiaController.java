package com.commandcenter.controller.aiaController;

import com.alibaba.fastjson.JSON;
import com.commandcenter.common.utils.HttpRequest;
import com.commandcenter.common.utils.R;
import com.commandcenter.model.ppcs.DutyDetailInfo;
import com.commandcenter.model.ppcs.DutyListInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author r25437
 * @create 2019-01-21 16:06
 * @desc 巡逻防控接口
 **/
@RestController
@RequestMapping("/aia")
public class AiaController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${aia.url}")
    private String aiaUrl;

    @Value("${aia.four.color}")
    private String aiaFourColorUrl;


    /**
     * 查询aia四色预警信息
     */
    @RequestMapping(value = "/getFourColorAlarm", method = RequestMethod.POST)
    public R getFourColorAlarm(@RequestBody Map<String, Object> bodyMap) throws IOException {
        String fourColorResponseJson = null;
        try {
            Map<String, Object> queryMap = new HashMap<>(4);
            //入参的para节点map
            Map<String, Object> inputParaMap = (Map<String, Object>) bodyMap.get("para");
            queryMap.put("orgId", inputParaMap.get("orgId"));
            queryMap.put("startTime", inputParaMap.get("startTime"));
            queryMap.put("endTime", inputParaMap.get("endTime"));
            queryMap.put("timeType", inputParaMap.get("timeType"));
            String queryJson = JSON.toJSONString(queryMap);
            StringBuilder url = new StringBuilder();
            url.append(aiaUrl).append(aiaFourColorUrl);
            fourColorResponseJson = HttpRequest.sendPost(url.toString(), queryJson);
            logger.info("getFourColor result is ======" + fourColorResponseJson);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("mpa获取四色预警数据异常---"+e.getMessage());
        }
        return R.ok().put("result", fourColorResponseJson);
    }


}
