package com.commandcenter.modules.job.service.impl;

import com.commandcenter.modules.job.dao.ScheduleJobLogDao;
import com.commandcenter.modules.job.entity.ScheduleJobLogEntity;
import com.commandcenter.modules.job.service.ScheduleJobLogService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

//@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {
	@Autowired
	private ScheduleJobLogDao scheduleJobLogDao;
	
	@Override
	public ScheduleJobLogEntity queryObject(Long jobId) {
		return scheduleJobLogDao.queryObject(jobId);
	}

	@Override
	public List<ScheduleJobLogEntity> queryList(Map<String, Object> map) {
		return scheduleJobLogDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return scheduleJobLogDao.queryTotal(map);
	}

	@Override
	public void save(ScheduleJobLogEntity log) {
		scheduleJobLogDao.save(log);
	}

}
