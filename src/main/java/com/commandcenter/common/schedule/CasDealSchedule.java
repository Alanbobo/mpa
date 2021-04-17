package com.commandcenter.common.schedule;

import com.alibaba.fastjson.JSON;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author r25437
 * @create 2019-4-24 9:03
 * @desc 循环casMap调用cas接口，判断失效时间
 **/
@Component
public class CasDealSchedule {
    protected static Logger logger = LoggerFactory.getLogger(CasDealSchedule.class);

    @Value("${smp.server.ip}")
    private String smpIp;

    @Value("${smp.server.port}")
    private String smpPort;

    @Value("${smp.isTgtExpired}")
    private String isTgtExpired;

//    @Scheduled(cron="0/10 * *  * * ?")
    public void casCheckScheduled(){
        for(String userCode : Constant.tgtMap.keySet()){
            String tgtId = Constant.tgtMap.get(userCode).toString();
            //通过CAS验证tgtid
            Map<String,Object> casMap = new HashMap<>(3);
            casMap.put("tgtId",tgtId);
            casMap.put("systemNo","MPA");
            String casJson = JSON.toJSONString(casMap);
            String casResponseJson = HttpRequest.sendPost("http://"+smpIp+":"+smpPort+isTgtExpired,casJson);
            logger.info("casLogin result is ======"+casResponseJson);
            Map<String,Object> casResponseMap = JSON.parseObject(casResponseJson,Map.class);
            if(casResponseMap != null){
                String status = casResponseMap.get("status").toString();
                //已失效,踢下线
                if("1".equals(status)){

                }
            }
        }
    }

}
