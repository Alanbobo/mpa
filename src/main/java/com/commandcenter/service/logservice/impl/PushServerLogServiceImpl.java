package com.commandcenter.service.logservice.impl;

import com.alibaba.fastjson.JSON;
import com.commandcenter.model.logmodel.LoggerData;
import com.commandcenter.service.logservice.PushServerLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author r25437
 * @create 2019-05-15 9:41
 * @desc 日志推送服务
 **/
@Service("pushServerLogService")
public class PushServerLogServiceImpl implements PushServerLogService {
    @Value("${log.server.ip}")
    private String serverLogIp;

    @Value("${log.server.port}")
    private String serverLogPort;

    @Value("${log.server.apiKey}")
    private String serverApiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Async
    @Override
    public void pushServerLogData(LoggerData loggerData) {
        String serverLogUrl = "http://"+serverLogIp+":"+serverLogPort+ "/api/v2/events";
            String json = null;
            try {
                json = JSON.toJSONString(loggerData);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.add("Authorization", "Bearer " + serverApiKey);
                HttpEntity<String> formEntity = new HttpEntity<String>(json, requestHeaders);
                restTemplate.postForEntity(serverLogUrl, formEntity, String.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
