package com.commandcenter.common.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.DateUtil;
import com.commandcenter.common.utils.GUID;
import com.commandcenter.common.utils.HttpRequest;
import com.commandcenter.common.wcf.WcfClient;
import com.commandcenter.dao.logdao.MpaLogAnalyseDao;
import commandcenter.smp.agent.SendMessToAgent;
import commandcenter.smp.agent.model.*;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author r25437
 * @create 2019-03-22 10:22
 * @desc 检测数据库 Activemq状态 定时任务类
 **/
@Component("mpaCheckStatus")
public class MpaCheckStatus {
    protected static Logger logger = LoggerFactory.getLogger(MpaCheckStatus.class);
    @Value("${agent.url}")
    private String agenturl;
    @Value("${agent.localip}")
    private String localIp;
    @Value("${agent.localport}")
    private String localPort;
    @Value("${agent.isopen}")
    private String isopen;
    @Value("${spring.datasource.druid.first.url}")
    private String dbUrl;
    @Value("${jms.vcs.url}")
    private String vcsUrl;
    @Value("${jms.vcs.username}")
    private String vcsJmsUserName;
    @Value("${jms.vcs.password}")
    private String vcsJmsUserPassword;
    @Value("${jms.app.url}")
    private String appUrl;
    @Value("${jms.app.username}")
    private String appJmsUserName;
    @Value("${jms.app.password}")
    private String appJmsUserPassword;
    @Value("${jms.icc.url}")
    private String iccUrl;
    @Value("${jms.icc.username}")
    private String iccJmsUserName;
    @Value("${jms.icc.password}")
    private String iccJmsUserPassword;
    @Value("${system.notice.vcs.wcf.ip}")
    private String wcfIp;
    @Value("${ipcs.url}")
    private String ipcsUrl;
    @Value("${ipcs.heartbeat}")
    private String ipcsHeartBeat;
    @Value("${checkstatusschedule.enable}")
    private String scheduledEnable;
    @Value("${vcs.http.url}")
    private String vcsHttpUrl;

    @Value("${vcs.http.port}")
    private String vcsHttpPort;

    @Autowired
    private MpaLogAnalyseDao mpaLogAnalyseDao;

    private static final String CHECK_STATUS = "0";
    /**
     记录状态
     */
    private HashMap<String,Object> hashMap = new HashMap<String,Object>();

    @Scheduled(cron="0/10 * *  * * ?")
    public void checkstatus(){
        if(!Boolean.parseBoolean(scheduledEnable)){
            return;
        }
        logger.info("MpaCheckStatus start =====================");
        if(CHECK_STATUS.equals(isopen)){
            //检测数据库状态
            logger.info("检查数据库方法开始执行=========checkDbStatus()");
            checkDbStatus();
            //检测VCS消息总线状态
            logger.info("检查VCSMQ方法开始执行=========checkVcsJmsStatus()");
            checkVcsJmsStatus();
            //检测ICC消息总线状态
            logger.info("检查ICCMQ方法开始执行=========checkIccJmsStatus()");
            checkIccJmsStatus();
            //检测APP消息总线状态
            logger.info("检查APPMQ方法开始执行=========checkAppJmsStatus()");
            checkAppJmsStatus();
            //检查wcf接口状态
            /** wcf接口弃用，改为http接口
             * logger.info("检查WCF方法开始执行=========checkWcfStatus()");
             checkWcfStatus();
             */
            //检查IPCS接口状态
            logger.info("检查IPCS方法开始执行=========checkIpcsStatus()");
            checkIpcsStatus();
            logger.info("检查VCS HTTP接口方法开始执行=========checkVcsHttpStatus()");
            checkVcsHttpStatus();
        }
        logger.info("MpaCheckStatus end =====================");

    }

    /**
     * 构建接口消息
     * @param alarmCode /postgre.disconnection.alarm/activemq.disconnection.alarm/
     * @param alarmCause 告警原因
     * @param alarmType  告警类型  database.alarm/数据库告警 message.bus.alarm/消息总线告警
     * @param alarmName  告警名称 /databaseAlarm/messageBusAlarm/
     * @param netElement 网元信息 /postgre/activeMQ/网元信息
     * @param url 网元地址 activeMQ/地址信息
     * @return
     */
    public AlarmModel buildMess(String alarmCode, String alarmCause, String alarmType,
                                String alarmName, String netElement, String alarmUUID, String url){
        AlarmModel alarmModel = null;
        try{
            logger.info("向SMT发送告警信息=====================");
            // 告警信息
            Alarm alarm = new Alarm();
            Meta meta = new Meta();
            meta.setCommand("alarmMonitor");
            Production production = new Production();
            production.setName("09");
            production.setIp(localIp);
            production.setPort(localPort);
            meta.setProduction(production);
            Interfaces interfaces = new Interfaces();
            // 告警监控
            List<AlarmMonitor> monitorList = new ArrayList<AlarmMonitor>();
            AlarmMonitor alarmMonitor = new AlarmMonitor();
            // 告警网元信息
            NetworkElement networkElement = new NetworkElement();
            networkElement.setNetworkElementType("09_03");
            networkElement.setIp(localIp);
            networkElement.setPort(localPort);
            if("activeMQ".equals(netElement)){
                alarm.setAlarmCause(alarmCause+url);
            }else if("VCS".equals(netElement)){
                alarm.setAlarmCause(wcfIp);
            }else if("IPCS".equals(netElement)){
                alarm.setAlarmCause(ipcsUrl);
            }else{
                alarm.setAlarmCause(dbUrl);
            }


            alarm.setAlarmUUID(alarmUUID);
            alarm.setAlarmCode(alarmCode);
            alarm.setAlarmType(alarmType);
            alarm.setAlarmName(alarmName);
            if("postgre.connection.alarm".equals(alarmCode) || "activemq.connection.alarm".equals(alarmCode) ||
                    "vcs.wcf.service.connection.alarm".equals(alarmCode)){
                alarm.setAlarmLevel("warning");
            }else{
                alarm.setAlarmLevel("critical");
            }

            alarm.setFirstOccurrenceTime(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
            alarmMonitor.setNetworkElement(networkElement);
            alarmMonitor.setAlarm(alarm);
            monitorList.add(alarmMonitor);
            interfaces.setAlarmMonitor(monitorList);
            alarmModel = new AlarmModel();
            alarmModel.setMeta(meta);
            alarmModel.setInterfaces(interfaces);
            return alarmModel ;
        }catch(Exception e){
            logger.error("checkstatus build message exception ",e);
        }
        logger.info("向SMT发送告警信息结束=====================");
        return alarmModel ;
    }

    /**
     * 检测wcf接口状态
     */
    private void checkWcfStatus(){
        try{
            WcfClient.checkWcfStatus();
            //发送WCF恢复告警通知
            if("error".equals(getAlarmCache("wcf"))){
                //更新本地告警状态
                putAlarmCache("wcf","success");
                //获取本地告警状态
                Alarm alarm = (Alarm) getAlarmCache("wcfAlarm");
                if(alarm != null){
                    AlarmModel alarmModel = buildMess("vcs.wcf.service.connection.alarm", "", "business.alarm"
                            , "wcfAlarm", "VCS", alarm.getAlarmUUID(), Constant.WCF_IP);
                    if(alarmModel != null){
                        String send = SendMessToAgent.send(JSON.toJSONString(alarmModel, SerializerFeature.WriteMapNullValue), agenturl);
                        if("success".equals(send)){
                            //移除本地告警信息
                            reAlarmCache("wcfAlarm");
                        }
                    }
                }else{
                    logger.error("checkstatus  get wcfAlarm cache is null");
                }
            }
        }catch (Exception e) {
            logger.error("checkstatus wcf conn exception ",e);
            //获取本地告警状态
            Alarm alarm = (Alarm) getAlarmCache("wcfAlarm");
            if(alarm == null){
                //本地记录告警状态
                putAlarmCache("wcf","error");
                //构建告警消息
                AlarmModel alarmModel = buildMess("vcs.wcf.service.disconnection.alarm", "", "business.alarm"
                        , "wcfAlarm", "VCS", GUID.getGuid(), Constant.WCF_IP);
                if(alarmModel != null){
                    //发送告警消息
                    String send = SendMessToAgent.send(JSON.toJSONString(alarmModel, SerializerFeature.WriteMapNullValue), agenturl);
                    if("success".equals(send)){
                        //本地记录告警信息
                        putAlarmCache("wcfAlarm",alarmModel.getInterfaces().getAlarmMonitor().get(0).getAlarm());
                    }
                }
            }
        }
    }

    /**
     * 检测VCS HTTP接口状态
     */
    private void checkVcsHttpStatus(){
        try{
            Map<String,String> vcsQueryMap = new HashMap<>(2);
            vcsQueryMap.put("caseId","");
            String detailReponseJson = HttpRequest.sendGet(vcsHttpUrl+":"+vcsHttpPort+Constant.VCS_GET_HANDLE_RECORD_LIST,vcsQueryMap);
            //发送vcs Http恢复告警通知
            if("error".equals(getAlarmCache("vcshttp"))){
                //更新本地告警状态
                putAlarmCache("vcshttp","success");
                //获取本地告警状态
                Alarm alarm = (Alarm) getAlarmCache("vcshttpAlarm");
                if(alarm != null){
                    AlarmModel alarmModel = buildMess("vcshttp.service.connection.alarm", "", "business.alarm"
                            , "vcshttpAlarm", "VCS", alarm.getAlarmUUID(), vcsHttpUrl);
                    if(alarmModel != null){
                        String send = SendMessToAgent.send(JSON.toJSONString(alarmModel, SerializerFeature.WriteMapNullValue), agenturl);
                        if("success".equals(send)){
                            //移除本地告警信息
                            reAlarmCache("vcshttpAlarm");
                        }
                    }
                }else{
                    logger.error("checkstatus  get vcshttpAlarm cache is null");
                }
            }
        }catch (Exception e) {
            logger.error("checkstatus vcs conn exception ",e);
            //获取本地告警状态
            Alarm alarm = (Alarm) getAlarmCache("vcshttpAlarm");
            if(alarm == null){
                //本地记录告警状态
                putAlarmCache("vcshttp","error");
                //构建告警消息
                AlarmModel alarmModel = buildMess("vcshttp.service.disconnection.alarm", "", "business.alarm"
                        , "vcshttpAlarm", "VCS", GUID.getGuid(),ipcsUrl);
                if(alarmModel != null){
                    //发送告警消息
                    String send = SendMessToAgent.send(JSON.toJSONString(alarmModel, SerializerFeature.WriteMapNullValue), agenturl);
                    if("success".equals(send)){
                        //本地记录告警信息
                        putAlarmCache("vcshttpAlarm",alarmModel.getInterfaces().getAlarmMonitor().get(0).getAlarm());
                    }
                }
            }
        }
    }

    /**
     * 检测IPCS接口状态
     */
    private void checkIpcsStatus(){
        try{
            String detailReponseJson = HttpRequest.sendGet(ipcsUrl+ipcsHeartBeat,null);
            //发送IPCS恢复告警通知
            if("error".equals(getAlarmCache("ipcs"))){
                //更新本地告警状态
                putAlarmCache("ipcs","success");
                //获取本地告警状态
                Alarm alarm = (Alarm) getAlarmCache("ipcsAlarm");
                if(alarm != null){
                    AlarmModel alarmModel = buildMess("ipcs.service.connection.alarm", "", "business.alarm"
                            , "ipcsAlarm", "IPCS", alarm.getAlarmUUID(), ipcsUrl);
                    if(alarmModel != null){
                        String send = SendMessToAgent.send(JSON.toJSONString(alarmModel, SerializerFeature.WriteMapNullValue), agenturl);
                        if("success".equals(send)){
                            //移除本地告警信息
                            reAlarmCache("ipcsAlarm");
                        }
                    }
                }else{
                    logger.error("checkstatus  get ipcsAlarm cache is null");
                }
            }
        }catch (Exception e) {
            logger.error("checkstatus ipcs conn exception ",e);
            //获取本地告警状态
            Alarm alarm = (Alarm) getAlarmCache("ipcsAlarm");
            if(alarm == null){
                //本地记录告警状态
                putAlarmCache("ipcs","error");
                //构建告警消息
                AlarmModel alarmModel = buildMess("ipcs.service.disconnection.alarm", "", "business.alarm"
                        , "ipcsAlarm", "IPCS", GUID.getGuid(),ipcsUrl);
                if(alarmModel != null){
                    //发送告警消息
                    String send = SendMessToAgent.send(JSON.toJSONString(alarmModel, SerializerFeature.WriteMapNullValue), agenturl);
                    if("success".equals(send)){
                        //本地记录告警信息
                        putAlarmCache("ipcsAlarm",alarmModel.getInterfaces().getAlarmMonitor().get(0).getAlarm());
                    }
                }
            }
        }
    }

    /**
     * 检测数据库状态
     */
    private void checkDbStatus(){
        try{
            //检测数据库状态
            mpaLogAnalyseDao.checkStatus();
            //发送数据库恢复告警通知
            if("error".equals(getAlarmCache("postgre"))){
                //更新本地告警状态
                putAlarmCache("postgre","success");
                //获取本地告警状态
                Alarm alarm = (Alarm) getAlarmCache("dbAlarm");
                if(alarm != null){
                    AlarmModel alarmModel = buildMess("postgre.connection.alarm", "postgre数据库正常连接", "database.alarm"
                            , "databaseAlarm", "postgre", alarm.getAlarmUUID(), "");
                    if(alarmModel != null){
                        String send = SendMessToAgent.send(JSON.toJSONString(alarmModel, SerializerFeature.WriteMapNullValue), agenturl);
                        if("success".equals(send)){
                            //移除本地告警信息
                            reAlarmCache("dbAlarm");
                        }
                    }
                }else{
                    logger.error("checkstatus  get dbalarm cache is null");
                }
            }
        }catch (Exception e) {
            logger.error("checkstatus db conn exception ",e);
            //获取本地告警状态
            Alarm alarm = (Alarm) getAlarmCache("dbAlarm");
            if(alarm == null){
                //本地记录告警状态
                putAlarmCache("postgre","error");
                //构建告警消息
                AlarmModel alarmModel = buildMess("postgre.disconnection.alarm", "postgre数据库无法连接", "database.alarm"
                        , "databaseAlarm", "postgre", GUID.getGuid(),"");
                if(alarmModel != null){
                    //发送告警消息
                    String send = SendMessToAgent.send(JSON.toJSONString(alarmModel, SerializerFeature.WriteMapNullValue), agenturl);
                    if("success".equals(send)){
                        //本地记录告警信息
                        putAlarmCache("dbAlarm",alarmModel.getInterfaces().getAlarmMonitor().get(0).getAlarm());
                    }
                }
            }
        }

    }

    /**
     * 检测VCS消息总线状态
     */
    private void checkVcsJmsStatus(){
        Connection connection = null;
        try{
            //检测Activemq状态
            ConnectionFactory contectionFactory = new ActiveMQConnectionFactory(vcsJmsUserName, vcsJmsUserPassword, vcsUrl);
            connection = contectionFactory.createConnection();
            connection.start();
            //发送Activemq恢复告警通知
            if("error".equals(getAlarmCache("activeMQVcs"))){
                //更新本地告警状态
                putAlarmCache("activeMQVcs","success");
                //获取本地告警状态
                Alarm alarm = (Alarm) getAlarmCache("jmsAlarmVcs");
                if(alarm != null){
                    AlarmModel alarmModel = buildMess("activemq.connection.alarm", "VCS:", "message.bus.alarm"
                            , "messageBusAlarm", "activeMQ",alarm.getAlarmUUID(), vcsUrl);
                    if(alarmModel != null){
                        String send = SendMessToAgent.send(JSON.toJSONString(alarmModel, SerializerFeature.WriteMapNullValue), agenturl);
                        if("success".equals(send)){
                            //移除本地告警信息
                            reAlarmCache("jmsAlarmVcs");
                        }
                    }
                }else{
                    logger.error("checkstatus  get jmsalarmVcs cache is null");
                }
            }
        }catch (JMSException e) {
            logger.error("checkstatus activemqVcs conn exception ",e);
            //获取本地告警状态
            Alarm alarm = (Alarm) getAlarmCache("jmsAlarmVcs");
            if(alarm == null){
                putAlarmCache("activeMQVcs","error");
                //记录消息总线告警记录
                AlarmModel alarmModel = buildMess("activemq.disconnection.alarm", "VCS:", "message.bus.alarm"
                        , "messageBusAlarm", "activeMQ", GUID.getGuid(), vcsUrl);
                if(alarmModel != null){
                    //发送告警消息
                    String send = SendMessToAgent.send(JSON.toJSONString(alarmModel, SerializerFeature.WriteMapNullValue), agenturl);
                    if("success".equals(send)){
                        //本地记录告警信息
                        putAlarmCache("jmsAlarmVcs",alarmModel.getInterfaces().getAlarmMonitor().get(0).getAlarm());
                    }
                }
            }
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    logger.error("checkstatus activemqVcs conn close exception ",e);
                }
            }
        }
    }

    /**
     * 检测ICC消息总线状态
     */
    private void checkIccJmsStatus(){
        Connection connection = null;
        try{
            //检测Activemq状态
            ConnectionFactory contectionFactory = new ActiveMQConnectionFactory(iccJmsUserName, iccJmsUserPassword, iccUrl);
            connection = contectionFactory.createConnection();
            connection.start();
            //发送Activemq恢复告警通知
            if("error".equals(getAlarmCache("activeMQIcc"))){
                //更新本地告警状态
                putAlarmCache("activeMQIcc","success");
                //获取本地告警状态
                Alarm alarm = (Alarm) getAlarmCache("jmsAlarmIcc");
                if(alarm != null){
                    AlarmModel alarmModel = buildMess("activemq.connection.alarm", "ICC:", "message.bus.alarm"
                            , "messageBusAlarm", "activeMQ",alarm.getAlarmUUID(), iccUrl);
                    if(alarmModel != null){
                        String send = SendMessToAgent.send(JSON.toJSONString(alarmModel, SerializerFeature.WriteMapNullValue), agenturl);
                        if("success".equals(send)){
                            //移除本地告警信息
                            reAlarmCache("jmsAlarmIcc");
                        }
                    }
                }else{
                    logger.error("checkstatus  get jmsalarmIcc cache is null");
                }
            }
        }catch (JMSException e) {
            logger.error("checkstatus activemqIcc conn exception ",e);
            //获取本地告警状态
            Alarm alarm = (Alarm) getAlarmCache("jmsAlarmIcc");
            if(alarm == null){
                putAlarmCache("activeMQIcc","error");
                //记录消息总线告警记录
                AlarmModel alarmModel = buildMess("activemq.disconnection.alarm", "ICC:", "message.bus.alarm"
                        , "messageBusAlarm", "activeMQ", GUID.getGuid(), iccUrl);
                if(alarmModel != null){
                    //发送告警消息
                    String send = SendMessToAgent.send(JSON.toJSONString(alarmModel, SerializerFeature.WriteMapNullValue), agenturl);
                    if("success".equals(send)){
                        //本地记录告警信息
                        putAlarmCache("jmsAlarmIcc",alarmModel.getInterfaces().getAlarmMonitor().get(0).getAlarm());
                    }
                }
            }
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    logger.error("checkstatus activemqIcc conn close exception ",e);
                }
            }
        }
    }

    /**
     * 检测APP消息总线状态
     */
    private void checkAppJmsStatus(){
        Connection connection = null;
        try{
            //检测Activemq状态
            ConnectionFactory contectionFactory = new ActiveMQConnectionFactory(appJmsUserName, appJmsUserPassword, appUrl);
            connection = contectionFactory.createConnection();
            connection.start();
            //发送Activemq恢复告警通知
            if("error".equals(getAlarmCache("activeMQApp"))){
                //更新本地告警状态
                putAlarmCache("activeMQApp","success");
                //获取本地告警状态
                Alarm alarm = (Alarm) getAlarmCache("jmsAlarmApp");
                if(alarm != null){
                    AlarmModel alarmModel = buildMess("activemq.connection.alarm", "APP:", "message.bus.alarm"
                            , "messageBusAlarm", "activeMQ",alarm.getAlarmUUID(), appUrl);
                    if(alarmModel != null){
                        String send = SendMessToAgent.send(JSON.toJSONString(alarmModel, SerializerFeature.WriteMapNullValue), agenturl);
                        if("success".equals(send)){
                            //移除本地告警信息
                            reAlarmCache("jmsAlarmApp");
                        }
                    }
                }else{
                    logger.error("checkstatus  get jmsalarmApp cache is null");
                }
            }
        }catch (JMSException e) {
            logger.error("checkstatus activemqApp conn exception ",e);
            //获取本地告警状态
            Alarm alarm = (Alarm) getAlarmCache("jmsAlarmApp");
            if(alarm == null){
                putAlarmCache("activeMQApp","error");
                //记录消息总线告警记录
                AlarmModel alarmModel = buildMess("activemq.disconnection.alarm", "APP:", "message.bus.alarm"
                        , "messageBusAlarm", "activeMQ", GUID.getGuid(), appUrl);
                if(alarmModel != null){
                    //发送告警消息
                    String send = SendMessToAgent.send(JSON.toJSONString(alarmModel, SerializerFeature.WriteMapNullValue), agenturl);
                    if("success".equals(send)){
                        //本地记录告警信息
                        putAlarmCache("jmsAlarmApp",alarmModel.getInterfaces().getAlarmMonitor().get(0).getAlarm());
                    }
                }
            }
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    logger.error("checkstatus activemqApp conn close exception ",e);
                }
            }
        }
    }

    /**
     * 记录本地缓存信息
     * @param key
     * @param value
     */
    private void putAlarmCache(String key,Object value){
        try {
            CacheManager manager = CacheManager.create(URLDecoder.decode(Thread.currentThread().getContextClassLoader()
                    .getResource("ehcache_agent.xml").getPath(), "utf-8"));
            // 获取指定Cache
            Cache cache = manager.getCache("mpa_agent_status");
            cache.put(new Element(key, value));
            cache.flush();
            manager.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    /**
     * 获取本地缓存信息
     * @param key
     */
    private Object getAlarmCache(String key){
        try {
            CacheManager manager = CacheManager.create(URLDecoder.decode(Thread.currentThread().getContextClassLoader()
                    .getResource("ehcache_agent.xml").getPath(), "utf-8"));

            // 获取指定Cache
            Cache cache = manager.getCache("mpa_agent_status");
            Element element = cache.get(key);
            manager.shutdown();
            if (element != null) {
                return element.getObjectValue();
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 删除本地缓存信息
     * @param key
     */
    private void reAlarmCache(String key){
        try {
            CacheManager manager = CacheManager.create(URLDecoder.decode(Thread.currentThread().getContextClassLoader()
                    .getResource("ehcache_agent.xml").getPath(), "utf-8"));
            //获取指定Cache
            Cache cache = manager.getCache("mpa_agent_status");
            cache.remove(key);
            cache.flush();
            manager.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
