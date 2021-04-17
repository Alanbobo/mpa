package com.commandcenter.service.logservice;

import com.commandcenter.model.logmodel.LoggerData;

/**
 * @author r25437
 * @create 2019-05-15 9:40
 * @desc 日志推送服务
 **/
public interface PushServerLogService {

    void pushServerLogData(LoggerData loggerData);
}
