package com.commandcenter.service.caseservice;
import java.util.List;
import java.util.Map;

import com.commandcenter.model.casemodel.MpatDispatch;
public interface MpatDispatchService{
	/**
	 * 获得MpatDispatch数据的总行数
	 * @return
	 */
    long getMpatDispatchRowCount();
	/**
	 * 获得MpatDispatch数据集合
	 * @return
	 */
    List<MpatDispatch> selectMpatDispatch();
	/**
	 * 获得一个MpatDispatch对象,以参数MpatDispatch对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    MpatDispatch selectMpatDispatchByObj(MpatDispatch obj);
	/**
	 * 通过MpatDispatch的id获得MpatDispatch对象
	 * @param id
	 * @return
	 */
    MpatDispatch selectMpatDispatchById(Object id);
	/**
	 * 插入MpatDispatch到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertMpatDispatch(MpatDispatch value);
	/**
	 * 插入MpatDispatch中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyMpatDispatch(MpatDispatch value);
	/**
	 * 通过MpatDispatch的id删除MpatDispatch
	 * @param id
	 * @return
	 */
    int deleteMpatDispatchById(Object id);
	/**
	 * 通过MpatDispatch的id更新MpatDispatch中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateMpatDispatchById(MpatDispatch enti);
	/**
	 * 通过MpatDispatch的id更新MpatDispatch中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyMpatDispatchById(MpatDispatch enti);

	/**
	 * 根据警情id查询参与警员信息
	 * @param map
	 * @return
	 */
	List<MpatDispatch> selectMpatDispatchListByCaseId(Map<String,Object> map);

	/**
	 * 查询数据库中调派表中，是否有记录
	 * @param enti
	 * @return
	 */
	List<MpatDispatch> selectMpatDispatchIsExist(MpatDispatch enti);
}