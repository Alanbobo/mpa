package com.commandcenter.service.logservice;
import java.util.List;
import java.util.Map;

import com.commandcenter.model.logmodel.MpaLogAnalyse;
public interface MpaLogAnalyseService{
	/**
	 * 获得MpaLogAnalyse数据的总行数
	 * @return
	 */
    long getMpaLogAnalyseRowCount();
	/**
	 * 获得MpaLogAnalyse数据集合
	 * @return
	 */
    List<MpaLogAnalyse> selectMpaLogAnalyse();
	/**
	 * 获得一个MpaLogAnalyse对象,以参数MpaLogAnalyse对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    MpaLogAnalyse selectMpaLogAnalyseByObj(MpaLogAnalyse obj);
	/**
	 * 通过MpaLogAnalyse的id获得MpaLogAnalyse对象
	 * @param id
	 * @return
	 */
    MpaLogAnalyse selectMpaLogAnalyseById(Object id);
	/**
	 * 插入MpaLogAnalyse到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertMpaLogAnalyse(MpaLogAnalyse value);
	/**
	 * 插入MpaLogAnalyse中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyMpaLogAnalyse(MpaLogAnalyse value);
	/**
	 * 通过MpaLogAnalyse的id删除MpaLogAnalyse
	 * @param id
	 * @return
	 */
    int deleteMpaLogAnalyseById(Object id);
	/**
	 * 通过MpaLogAnalyse的id更新MpaLogAnalyse中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateMpaLogAnalyseById(MpaLogAnalyse enti);
	/**
	 * 通过MpaLogAnalyse的id更新MpaLogAnalyse中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyMpaLogAnalyseById(MpaLogAnalyse enti);

	/**
	 * 按照log_time进行数据清除
	 * @param logTime
	 */
	void deleteMpaLogAnalyseByLogTime(String logTime);

	int getMpaLogAnalyseRowCountByOrgTimeOpName(Map<String, Object> content);

	/**
	 * 根据时间分组，获取给定时间、组织机构以及方法名后的记录次数
	 * @param content
	 * @return
	 */
	List<Map<String, Object>> getCountAndTimeByOrgTimeOpName(Map<String, Object> content);
}