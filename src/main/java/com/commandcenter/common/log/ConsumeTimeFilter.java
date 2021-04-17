package com.commandcenter.common.log;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * @author r25437
 * @create 2018-08-01 9:20
 * @desc 服务耗时统计
 **/
public class ConsumeTimeFilter extends Filter {
    @Override
    public FilterReply decide(Object eventObject) {
        LoggingEvent event = (LoggingEvent)eventObject;

        if (event.getMessage().contains("consumeTime")) {
            return FilterReply.ACCEPT;
        } else {
            return FilterReply.DENY;
        }
    }
}
