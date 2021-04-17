package com.commandcenter.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import commandcenter.smp.agent.SendMessToAgent;
import commandcenter.smp.agent.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author r25437
 * @create 2019-04-28 11:52
 * @desc SMT工具类
 **/
@Component("smtUtils")
public class SmtUtils {
    protected static Logger logger = LoggerFactory.getLogger(SmtUtils.class);
    @Value("${agent.url}")
    private String agenturl;
    @Value("${agent.localip}")
    private String localIp;
    @Value("${agent.localport}")
    private String localPort;
    @Value("${agent.isopen}")
    private String isopen;

    private static final String[] FUNC_ARRAY= {"/mpa/sys/login","/mpa/sys/logout","/mpa/case/caseDetail","/mpa/case/icc/feedbackList","/mpa/case/icc/feedbackToIcc","/mpa/case/feedBack","/mpa/case/caseList","/mpa/ppcs/getDutyDetail","/mpa/ppcs/dutyListAndDetail"};

    public void sendFuncMessage(String opName, String accessTime){
        Meta meta = new Meta();
        meta.setCommand("functionalUsageStatistics");
        Production production = new Production();
        production.setName("09");
        production.setIp(localIp);
        production.setPort(localPort);
        meta.setProduction(production);
        Interfaces interfaces = new Interfaces();
        FunctionalUsageStatistics functionalUsageStatistics = new FunctionalUsageStatistics();
        functionalUsageStatistics.setFunctionalNameList(FUNC_ARRAY);
        functionalUsageStatistics.setFunctionalName(opName);
        functionalUsageStatistics.setAccessTime(accessTime);
        interfaces.setFunctionalUsageStatistics(functionalUsageStatistics);

        AlarmModel alarmModel = new AlarmModel();
        alarmModel.setMeta(meta);
        alarmModel.setInterfaces(interfaces);

        SendMessToAgent.send(JSON.toJSONString(alarmModel, SerializerFeature.WriteMapNullValue), agenturl);
    }
}
