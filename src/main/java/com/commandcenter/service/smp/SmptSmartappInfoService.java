package com.commandcenter.service.smp;

import com.commandcenter.model.contacts.SmartAppForApp;
import com.commandcenter.model.smp.SmptSmartappInfo;

import java.util.List;
import java.util.Map;

public interface SmptSmartappInfoService{
	/**
	 * 获得SmptSmartappInfo数据的总行数
	 * @return
	 */
    long getSmptSmartappInfoRowCount();
	/**
	 * 获得SmptSmartappInfo数据集合
	 * @return
	 */
    List<SmptSmartappInfo> selectSmptSmartappInfo();
	/**
	 * 获得一个SmptSmartappInfo对象,以参数SmptSmartappInfo对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    SmptSmartappInfo selectSmptSmartappInfoByObj(SmptSmartappInfo obj);
	/**
	 * 通过SmptSmartappInfo的id获得SmptSmartappInfo对象
	 * @param id
	 * @return
	 */
    SmptSmartappInfo selectSmptSmartappInfoById(String id);
	/**
	 * 插入SmptSmartappInfo到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertSmptSmartappInfo(SmptSmartappInfo value);
	/**
	 * 插入SmptSmartappInfo中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptySmptSmartappInfo(SmptSmartappInfo value);
	/**
	 * 通过SmptSmartappInfo的id删除SmptSmartappInfo
	 * @param id
	 * @return
	 */
    int deleteSmptSmartappInfoById(String id);
	/**
	 * 通过SmptSmartappInfo的id更新SmptSmartappInfo中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateSmptSmartappInfoById(SmptSmartappInfo enti);
	/**
	 * 通过SmptSmartappInfo的id更新SmptSmartappInfo中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptySmptSmartappInfoById(SmptSmartappInfo enti);

	/**
	 * 通过map查询SmptSmartappInfo中属性不为null的数据
	 * @param map
	 * @return
	 */
	SmptSmartappInfo selectSmptSmartappInfoByMap(Map<String, Object> map);
	/**
	 * 查询SmptSmartappInfo中未绑定警员的数据
	 * @return
	 */
	List<SmartAppForApp> seleSmartappInfoNullStaff(String orgGuid);
	SmptSmartappInfo selectSmptSmartappInfoByStaffGuid(String staffGuid);

	List<SmartAppForApp> selectSmartInfoByMap(Map<String, Object> map);
}