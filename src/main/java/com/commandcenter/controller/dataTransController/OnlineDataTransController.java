package com.commandcenter.controller.dataTransController;

import com.commandcenter.common.utils.DateUtils;
import com.commandcenter.common.utils.R;
import com.commandcenter.service.puctomqservice.PucOnlineDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 170725437
 * @create 2020-05-08 9:16
 * @desc 上下线信息同步服务，根据update_time进行增量更新
 **/
@RestController
@RequestMapping("/transData")
public class OnlineDataTransController {

    @Autowired
    private PucOnlineDataService pucOnlineDataService;
    /**
     * 获取上下线信息列表。andriod同步到客户端本地，每次进行增量同步
     *
     * @param bodyMap
     * @return
     */
    @RequestMapping(value = "/onlineData", method = RequestMethod.POST)
    public R contactList(@RequestBody Map<String, Object> bodyMap) {
        //入参的para节点map
        Map<String, Object> inputParaMap = (Map<String, Object>) bodyMap.get("para");

        Map<String,Object> queryMap = new HashMap<>();

        for(String key : inputParaMap.keySet()){
            switch (key){
                //设备类型
                case "deviceType":
                    queryMap.put("deviceType", inputParaMap.get(key));
                    break;
                //更新时间，客户端根据此更新时间进行增量数据获取
                case "updateTime":
                    Date updateDate = DateUtils.format(inputParaMap.get(key).toString(),"yyyy-MM-dd HH:mm:ss");
                    queryMap.put("updateTime", updateDate);
                    break;
                case "deviceGuid":
                    queryMap.put("deviceGuid", inputParaMap.get(key));
                    break;
            }
        }

        List<Map<String,Object>> onlineDataList = pucOnlineDataService.selectPucOnlineDataByMap(queryMap);

        return R.ok().put("onlineDataList",onlineDataList);
    }
}
