package com.commandcenter.modules.job.utils;

import com.alibaba.fastjson.JSON;
import com.commandcenter.common.utils.SpringContextUtils;
import com.commandcenter.modules.job.entity.ScheduleJobEntity;
import com.commandcenter.modules.job.entity.ScheduleJobLogEntity;
import com.commandcenter.modules.job.service.ScheduleJobLogService;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.*;


/**
 * 定时任务
 * 
 * @author r25437
 * @date 2016年11月30日 下午12:44:21
 */
public class ScheduleJob extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ExecutorService service = new ThreadPoolExecutor(
			1,1,10, TimeUnit.SECONDS,
			new ArrayBlockingQueue<>(1),
			new ThreadPoolExecutor.DiscardOldestPolicy());

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String jsonJob = context.getMergedJobDataMap().getString(ScheduleJobEntity.JOB_PARAM_KEY);
		ScheduleJobEntity scheduleJob = JSON.parseObject(jsonJob, ScheduleJobEntity.class);

		//获取scheduleJobLogService
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogService");
        
        //数据库保存执行记录
        ScheduleJobLogEntity log = new ScheduleJobLogEntity();
        log.setJobId(scheduleJob.getJobId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setMethodName(scheduleJob.getMethodName());
        log.setParams(scheduleJob.getParams());
        log.setCreateTime(new Date());
        
        //任务开始时间
        long startTime = System.currentTimeMillis();
        
        try {
            //执行任务
        	logger.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), 
            		scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);
            
			future.get();
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);
			//任务状态    0：成功    1：失败
			log.setStatus(0);
			
			logger.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);
			
			//任务状态    0：成功    1：失败
			log.setStatus(1);
			log.setError(StringUtils.substring(e.toString(), 0, 2000));
		}finally {
			scheduleJobLogService.save(log);
		}
    }
}
