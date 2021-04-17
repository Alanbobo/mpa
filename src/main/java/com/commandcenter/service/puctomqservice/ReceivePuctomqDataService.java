package com.commandcenter.service.puctomqservice;

import com.commandcenter.common.activemq.model.MqResponseData;
import com.commandcenter.model.puctomq.PuctomqGpsInfoForRedis;

public interface ReceivePuctomqDataService {
    void recSmpBaseData(MqResponseData mqResponseData);
    void recPuctomqGpsData(PuctomqGpsInfoForRedis puctomqGpsInfoForRedis);
}
