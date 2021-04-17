package com.commandcenter.service.dictservice;
import java.util.List;

import com.commandcenter.model.dictmodel.SmptDictInfo;
public interface SmptDictInfoService{
	/**
	 * 获得SmptDictInfo数据的总行数
	 * @return
	 */
    long getSmptDictInfoRowCount();
	/**
	 * 获得SmptDictInfo数据集合
	 * @return
	 */
	List<SmptDictInfo> selectSmptDictInfo();
	/**
	 * 获得一个SmptDictInfo对象,以参数SmptDictInfo对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    SmptDictInfo selectSmptDictInfoByObj(SmptDictInfo obj);
	/**
	 * 通过SmptDictInfo的id获得SmptDictInfo对象
	 * @param id
	 * @return
	 */
    SmptDictInfo selectSmptDictInfoById(String id);
	/**
	 * 插入SmptDictInfo到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertSmptDictInfo(SmptDictInfo value);
	/**
	 * 插入SmptDictInfo中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptySmptDictInfo(SmptDictInfo value);
	/**
	 * 通过SmptDictInfo的id删除SmptDictInfo
	 * @param id
	 * @return
	 */
    int deleteSmptDictInfoById(String id);
	/**
	 * 通过SmptDictInfo的id更新SmptDictInfo中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateSmptDictInfoById(SmptDictInfo enti);
	/**
	 * 通过SmptDictInfo的id更新SmptDictInfo中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptySmptDictInfoById(SmptDictInfo enti);
}