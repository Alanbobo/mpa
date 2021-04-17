package com.commandcenter.common.log;

import ch.qos.logback.core.rolling.RollingFileAppender;
import com.commandcenter.common.utils.HttpRequest;
import com.commandcenter.common.utils.PropertiesUtil;
import com.commandcenter.model.logmodel.LoggerData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author r25437
 * @create 2019-05-15 9:30
 * @desc 统一日志组件
 **/
@Component
public class MpaRollingFileAppender<E> extends RollingFileAppender<E> {

    private static ScheduledExecutorService executors =  new ScheduledThreadPoolExecutor(10);
    public static final Boolean LOG_SWITCH_FLAG = new Boolean(PropertiesUtil.getValue("log.switch.flag"));

    public static final String LOG_SERVER_IP = PropertiesUtil.getValue("log.server.ip");
    public static final String LOG_SERVER_PORT = PropertiesUtil.getValue("log.server.port");
    public static final String LOG_SERVER_KEY = PropertiesUtil.getValue("log.server.apiKey");


    @Override
    protected void subAppend(E event) {
        ReadWriteLock rwl = new ReentrantReadWriteLock();
        super.subAppend(event);
        if ((Boolean.TRUE).equals(LOG_SWITCH_FLAG)) {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            LoggerData loggerData = new LoggerData();
            HashMap map = new HashMap();
            HashMap threadMap = new HashMap();

            if (event instanceof ch.qos.logback.classic.spi.LoggingEvent) {
                ch.qos.logback.classic.spi.LoggingEvent logBackEvent = (ch.qos.logback.classic.spi.LoggingEvent) event;
                // class name
                String loggerName = logBackEvent.getLoggerName();
                String level = logBackEvent.getLevel().levelStr;
                String threadName = logBackEvent.getThreadName();
                String message = logBackEvent.getMessage();
                Long timeTemp = logBackEvent.getTimeStamp();
                Date date = new Date(timeTemp);
                String timeStr = sdf.format(date);

                threadMap.put("thread_id", threadName);
                map.put("@level", level);
                map.put("@environment", threadMap);
                loggerData.setUuid(UUID.randomUUID().toString());
                loggerData.setSource(loggerName);
                loggerData.setMessage(message);
                loggerData.setData(map);
                loggerData.setDate(timeStr);

                rwl.writeLock().lock();
                try {
                    String serverLogUrl = "http://"+LOG_SERVER_IP+":"+LOG_SERVER_PORT+ "/api/v2/events";
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    String json = objectMapper.writeValueAsString(loggerData);
                    executors.submit(new LogSendRunnable(serverLogUrl,json,"Bearer " + LOG_SERVER_KEY));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    rwl.writeLock().unlock();
                }
            }
        }

    }
}
