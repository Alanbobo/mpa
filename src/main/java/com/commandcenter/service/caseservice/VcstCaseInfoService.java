package com.commandcenter.service.caseservice;
import java.util.List;
import java.util.Map;

import com.commandcenter.model.casemodel.MpaCaseOprInfo;
import com.commandcenter.model.casemodel.VcstCaseInfo;
public interface VcstCaseInfoService{
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
	 * 通过VcstCaseInfo的id获得VcstCaseInfo对象
	 * @param id
	 * @return
	 */
    VcstCaseInfo selectVcstCaseInfoById(String id);
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
    int deleteVcstCaseInfoById(String id);
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
	 * 通过VcstCaseInfo添加警情信息，如果有调派信息一同增加
	 * @param enti
	 * @return
	 */
	void addCaseAndDispatchs(VcstCaseInfo enti);
	/**
	 * 查询警情列表信息
	 * @param map
	 * @return
	 */
	List<VcstCaseInfo> selectCaseList(Map<String,Object> map);
	/**
	 * 推送警情信息给APP
	 * @param id
	 * @return
	 */
	void sendCaseInfoToStaffApp(String id,String sendType, String queueName);
	/**
	 * 处理调派信息变更
	 * @param map
	 * @param queueName 推送队列名字
	 * @return
	 */
	void changeDispatchInfo(Map<String,Object> map, String queueName);
	/**
	 * 获得一个VcstCaseInfo对象,以参数VcstCaseInfo对象中不为空的属性作为条件进行查询
	 * @param map
	 * @return
	 */
	VcstCaseInfo selectCaseByAlarmIdAndStaffGuid(Map<String,Object> map);
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
	 * 插入mpacaseopr表
	 * @param mpaCaseOprInfo
	 * @return
	 */
	int insertMpaCaseOpr(MpaCaseOprInfo mpaCaseOprInfo);

	/**
	 * 查询列表
	 * @return
	 */
	List<MpaCaseOprInfo> selectMpaCaseOprList();

	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	List<MpaCaseOprInfo> selectMpaCaseOprListByMod(Map<String,Object> map);

	/**
	 * 批量删除警情通知
	 * @param ids
	 * @return
	 */
	int deleteMpaCaseOprInfoByIds(List<Long> ids);
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


	Map<String,Object> getStaffStateByOrg(String stateReponseJson,Map<String, Object> map) throws Exception;

    int getMonthCaseCountByMap(Map<String, Object> map);
}