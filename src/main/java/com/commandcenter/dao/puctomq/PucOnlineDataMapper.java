package com.commandcenter.dao.puctomq;
import com.commandcenter.model.puctomq.PucOnlineData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface PucOnlineDataMapper{
	/**
	 * 获得PucOnlineData数据的总行数
	 * @return
	 */
    long getPucOnlineDataRowCount();
	/**
	 * 获得PucOnlineData数据集合
	 * @return
	 */
    List<PucOnlineData> selectPucOnlineData();
	/**
	 * 获得一个PucOnlineData对象,以参数PucOnlineData对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    PucOnlineData selectPucOnlineDataByObj(PucOnlineData obj);
	/**
	 * 通过PucOnlineData的id获得PucOnlineData对象
	 * @param id
	 * @return
	 */
    PucOnlineData selectPucOnlineDataById(String id);
	/**
	 * 插入PucOnlineData到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertPucOnlineData(PucOnlineData value);
	/**
	 * 插入PucOnlineData中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyPucOnlineData(PucOnlineData value);
	/**
	 * 通过PucOnlineData的id删除PucOnlineData
	 * @param id
	 * @return
	 */
    int deletePucOnlineDataById(String id);
	/**
	 * 通过PucOnlineData的id更新PucOnlineData中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updatePucOnlineDataById(PucOnlineData enti);
	/**
	 * 通过PucOnlineData的id更新PucOnlineData中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyPucOnlineDataById(PucOnlineData enti);

	/**
	 * 获得一个Map对象,以参数Map对象中的属性作为条件进行查询
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> selectPucOnlineDataByMap(Map<String, Object> map);
}