package com.commandcenter.modules.job.dao;

import com.commandcenter.modules.job.entity.ScheduleJobLogEntity;
import com.commandcenter.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 * 
 * @author r25437
 * @date 2016年12月1日 下午10:30:02
 */
@Mapper
public interface ScheduleJobLogDao extends BaseDao<ScheduleJobLogEntity> {
	
}
