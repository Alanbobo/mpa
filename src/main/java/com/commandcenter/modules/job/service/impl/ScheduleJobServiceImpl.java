package com.commandcenter.modules.job.service.impl;

import com.commandcenter.modules.job.dao.ScheduleJobDao;
import com.commandcenter.modules.job.entity.ScheduleJobEntity;
import com.commandcenter.modules.job.utils.ScheduleUtils;
import com.commandcenter.modules.job.service.ScheduleJobService;
import com.commandcenter.common.utils.Constant.ScheduleStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

//@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
	@Autowired
    private Scheduler scheduler;
	@Autowired
	private ScheduleJobDao schedulerJobDao;
	
	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init(){
		List<ScheduleJobEntity> scheduleJobList = schedulerJobDao.queryList(new HashMap<>());
		for(ScheduleJobEntity scheduleJob : scheduleJobList){
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
		}
	}
	
	@Override
	public ScheduleJobEntity queryObject(Long jobId) {
		return schedulerJobDao.queryObject(jobId);
	}

	@Override
	public List<ScheduleJobEntity> queryList(Map<String, Object> map) {
		return schedulerJobDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return schedulerJobDao.queryTotal(map);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(ScheduleJobEntity scheduleJob) {
		scheduleJob.setCreateTime(new Date());
		scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
        schedulerJobDao.save(scheduleJob);
        
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(ScheduleJobEntity scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
                
        schedulerJobDao.update(scheduleJob);
    }

	@Override
	@Transactional
    public void deleteBatch(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
    	}
    	
    	//删除数据
    	schedulerJobDao.deleteBatch(jobIds);
	}

	@Override
    public int updateBatch(Long[] jobIds, int status){
    	Map<String, Object> map = new HashMap<>();
    	map.put("list", jobIds);
    	map.put("status", status);
    	return schedulerJobDao.updateBatch(map);
    }
    
	@Override
	@Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.run(scheduler, queryObject(jobId));
    	}
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
    		ScheduleUtils.pauseJob(scheduler, jobId);
    	}
        
    	updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.resumeJob(scheduler, jobId);
    	}

    	updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
    }
    
}
