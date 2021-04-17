package com.commandcenter.dao.casedao;
import com.commandcenter.model.casemodel.VcstCaseInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface VcstCaseInfoDao{
	/**
	 * 获得VcstCaseInfo数据的总行数
	 * @return
	 */
    long getVcstCaseInfoRowCount();
	/**
	 * 获得VcstCaseInfo数据集合
	 * @return
	 */
    List<VcstCaseInfo> selectVcstCaseInfo();
	/**
	 * 获得一个VcstCaseInfo对象,以参数VcstCaseInfo对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    VcstCaseInfo selectVcstCaseInfoByObj(VcstCaseInfo obj);
	/**
	 * 获得一个VcstCaseInfo对象,以参数VcstCaseInfo对象中不为空的属性作为条件进行查询
	 * @param map
	 * @return
	 */
	VcstCaseInfo selectCaseByAlarmIdAndStaffGuid(Map<String,Object> map);
	/**
	 * 通过VcstCaseInfo的id获得VcstCaseInfo对象
	 * @param id
	 * @return
	 */
    VcstCaseInfo selectVcstCaseInfoById(Object id);
	/**
	 * 插入VcstCaseInfo到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertVcstCaseInfo(VcstCaseInfo value);
	/**
	 * 插入VcstCaseInfo中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyVcstCaseInfo(VcstCaseInfo value);
	/**
	 * 通过VcstCaseInfo的id删除VcstCaseInfo
	 * @param id
	 * @return
	 */
    int deleteVcstCaseInfoById(Object id);
	/**
	 * 通过VcstCaseInfo的id更新VcstCaseInfo中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateVcstCaseInfoById(VcstCaseInfo enti);
	/**
	 * 通过VcstCaseInfo的id更新VcstCaseInfo中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyVcstCaseInfoById(VcstCaseInfo enti);
	/**
	 * 查询警情列表信息
	 * @param map
	 * @return
	 */
	List<VcstCaseInfo> selectCaseList(Map<String,Object> map);
	/**
	 * 查询我的待接收警情列表信息
	 * @param map
	 * @return
	 */
	List<VcstCaseInfo> selectNotAcceptList(Map<String,Object> map);
	/**
	 * 查询我的任务警情列表信息
	 * @param map
	 * @return
	 */
	List<VcstCaseInfo> selectTaskList(Map<String,Object> map);

	/**
	 * 根据警情id查询警情信息
	 * @param id
	 * @return
	 */
	int selectVcstCaseInfoCountById(String id);

	/**
	 * 查询警情列表信息，领导端查看
	 * @param map
	 * @return
	 */
	List<VcstCaseInfo> selectCaseListForLeader(Map<String,Object> map);


	/**
	 * 根据时间组织机构进行警情类型统计
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getCaseTypeStatistic(Map<String,Object> map);


	/**
	 * 根据组织机构查询本月当日警情数量
	 * @param map
	 * @return
	 */
	int getCaseCountByMap(Map<String,Object> map);


    List<Map<String,Object>> selectStaffStateByOrg(Map<String, Object> map);

    List<Map<String,Object>> selectMapInfoByStaffGuid(Map<String, Object> map);

    int getMonthCaseCountByMap(Map<String, Object> map);


	Map<String,Object> selectNonGpsInfoByMap(Map<String, Object> map);
}