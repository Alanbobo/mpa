package com.commandcenter.service.puctomqservice.impl;

import com.commandcenter.common.activemq.model.MqResponseData;
import com.commandcenter.model.puctomq.PuctomqGpsInfoForRedis;
import com.commandcenter.service.puctomqservice.ReceivePuctomqDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReceivePuctomqDataServiceImpl implements ReceivePuctomqDataService {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Override
    public void recSmpBaseData(MqResponseData mqResponseData) {
        redisTemplate.opsForValue().set(mqResponseData.getBody().get("DEVICE_ID")+"_ONLINE",mqResponseData.getBody().get("ISONLINE"));
    }
    @Override
    public void recPuctomqGpsData(PuctomqGpsInfoForRedis puctomqGpsInfoForRedis){
       // redisTemplate.opsForValue().set(mqResponseData.getBody().get("DEVICE_ID")+"_GPS",puctomqGpsInfoForRedis);
    }
}
