package com.commandcenter.dao.smp;
import com.commandcenter.model.contacts.StaffDeviceInfoForApp;
import com.commandcenter.model.smp.PucSystemInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PucSystemInfoMapper{
	/**
	 * 获得PucSystemInfo数据的总行数
	 * @return
	 */
    long getPucSystemInfoRowCount();
	/**
	 * 获得PucSystemInfo数据集合
	 * @return
	 */
    List<PucSystemInfo> selectPucSystemInfo();
	/**
	 * 获得一个PucSystemInfo对象,以参数PucSystemInfo对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    PucSystemInfo selectPucSystemInfoByObj(PucSystemInfo obj);
	/**
	 * 通过PucSystemInfo的id获得PucSystemInfo对象
	 * @param id
	 * @return
	 */
    PucSystemInfo selectPucSystemInfoById(String id);
	/**
	 * 插入PucSystemInfo到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertPucSystemInfo(PucSystemInfo value);
	/**
	 * 插入PucSystemInfo中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyPucSystemInfo(PucSystemInfo value);
	/**
	 * 通过PucSystemInfo的id删除PucSystemInfo
	 * @param id
	 * @return
	 */
    int deletePucSystemInfoById(String id);
	/**
	 * 通过PucSystemInfo的id更新PucSystemInfo中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updatePucSystemInfoById(PucSystemInfo enti);
	/**
	 * 通过PucSystemInfo的id更新PucSystemInfo中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyPucSystemInfoById(PucSystemInfo enti);

    int deleteAllPucSystemInfo();

	void deletePucSystemInfoByIdLogic(PucSystemInfo info);

	List<PucSystemInfo> selectPucSystemInfoByMap(Map paraMap);
}