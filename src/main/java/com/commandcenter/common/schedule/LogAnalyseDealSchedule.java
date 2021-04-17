package com.commandcenter.common.schedule;

import com.commandcenter.common.utils.DateUtil;
import com.commandcenter.common.utils.SmtUtils;
import com.commandcenter.model.logmodel.MpaLogAnalyse;
import com.commandcenter.service.logservice.MpaLogAnalyseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author r25437
 * @create 2018-12-26 9:03
 * @desc 调用日志处理后台进程，每天凌晨1点开始处理
 **/
@Component
public class LogAnalyseDealSchedule {
    protected static Logger logger = LoggerFactory.getLogger(LogAnalyseDealSchedule.class);
    @Value("${logback.logdir}")
    private String logbackDir;
    @Value("${loganalyseschedule.enable}")
    private String scheduledEnable;
    @Autowired
    private MpaLogAnalyseService mpaLogAnalyseService;

    @Autowired
    private SmtUtils smtUtils;

    @Scheduled(cron = "${cron.log.time}")
    public void logAnalyseScheduled(){
        if(!Boolean.parseBoolean(scheduledEnable)){
            return;
        }
        //强行写次日志，以防日期切换后，无人调用服务，日志未切分的问题
        logger.info("logAnalyseScheduled consumeTime = 10 ms ,userName=null" );

        FileReader fr = null;
        BufferedReader br = null;

        try {
            //File path = new File(URLDecoder.decode(ResourceUtils.getURL("classpath:").getPath(), "utf-8"));
            //String logPath = path.getParentFile().getParentFile().getParentFile().getParentFile().getAbsolutePath()+"\\logs\\mpa\\info";
            String logPath = logbackDir+"/info";

            logger.info("logAnalyseScheduled path=="+logbackDir+",logPath=="+logPath);
            //logger.info("logAnalyseScheduled path=="+path+",logPath=="+logPath);
            File fileDir = new File(logPath);
            if(!fileDir.exists() || fileDir.isFile()){
                return;
            }
            //logger.info("logAnalyseScheduled path=="+path+",logPath=="+logPath+",fileDir.listFiles.length=="+fileDir.listFiles().length);
            logger.info("logAnalyseScheduled path=="+logbackDir+",logPath=="+logPath+",fileDir.listFiles.length=="+fileDir.listFiles().length);
            Date yesterday = DateUtil.addDay(new Date(), -1);
            String yesterDayStr = DateUtil.format(yesterday);
            List<MpaLogAnalyse> mpaLogAnalyseList = new ArrayList<>();
            for(File file:fileDir.listFiles()){
                //如果是昨天的文件，进行处理
                if(file.getName().contains("consumeTimeLog") && file.getName().contains(yesterDayStr)){
                    fr = new FileReader(file);
                    br = new BufferedReader(fr);
                    String str2 = br.readLine();
                    while(str2 != null) {
                        StringBuilder sBuilder = new StringBuilder();
                        MpaLogAnalyse mpaLogAnalyse = new MpaLogAnalyse();
                        String[] str = str2.split(" ");
                        sBuilder.append(str[0]).append(" ").append(str[1]);
                        String dateStr = sBuilder.toString();
                        String interName = str[4].substring(1,str[4].length()-1);
                        String[] splitStr = str[str.length-1].split("=");
                        String staffGuid = "";
                        if(splitStr.length>1){
                            staffGuid = splitStr[1];
                        }
                        if(interName!=null && !"".equals(interName.trim())) {
                            mpaLogAnalyse.setLogTime(yesterDayStr);
                            mpaLogAnalyse.setOpName(interName);
                            mpaLogAnalyse.setOpStaff(staffGuid);
                            mpaLogAnalyse.setOpTime(DateUtil.parse(dateStr, "yyyy-MM-dd HH:mm:ss"));
                            mpaLogAnalyseList.add(mpaLogAnalyse);
                        }
                        System.out.println("dateStr============"+dateStr+",interName ================== "+interName + "," + staffGuid);
                        str2 = br.readLine();
                    }
                    fr.close();
                    br.close();
                }
            }
            //先删除logtime是昨天的数据，即先删除后插入。以防止重复处理
            mpaLogAnalyseService.deleteMpaLogAnalyseByLogTime(yesterDayStr);
            mpaLogAnalyseList.stream().forEach(mpaLogAnalyseService::insertNonEmptyMpaLogAnalyse);

            for(MpaLogAnalyse t : mpaLogAnalyseList) {
                smtUtils.sendFuncMessage(t.getOpName(), DateUtil.format(t.getOpTime(), DateUtil.getFullDateTimePattern()));
            }
            //logger.info("path:" + path.getParentFile().getParentFile().getParentFile().getParentFile().getAbsolutePath()+"\\logs\\mpa\\info");
            logger.info("path:" + logbackDir+"/info");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if(br != null){
                    br.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
