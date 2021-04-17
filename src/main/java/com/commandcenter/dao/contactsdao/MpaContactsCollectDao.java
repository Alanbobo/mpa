package com.commandcenter.dao.contactsdao;
import com.commandcenter.model.contacts.MpaContactsCollect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MpaContactsCollectDao{
	/**
	 * 获得MpaContactsCollect数据的总行数
	 * @return
	 */
    long getMpaContactsCollectRowCount();
	/**
	 * 获得MpaContactsCollect数据集合
	 * @return
	 */
    List<MpaContactsCollect> selectMpaContactsCollect(Map<String,Object> map);
	/**
	 * 获得一个MpaContactsCollect对象,以参数MpaContactsCollect对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    MpaContactsCollect selectMpaContactsCollectByObj(MpaContactsCollect obj);
	/**
	 * 通过MpaContactsCollect的id获得MpaContactsCollect对象
	 * @param id
	 * @return
	 */
    MpaContactsCollect selectMpaContactsCollectById(String id);
	/**
	 * 插入MpaContactsCollect到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertMpaContactsCollect(MpaContactsCollect value);
	/**
	 * 插入MpaContactsCollect中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyMpaContactsCollect(MpaContactsCollect value);
	/**
	 * 通过MpaContactsCollect的id删除MpaContactsCollect
	 * @param id
	 * @return
	 */
    int deleteMpaContactsCollectById(@Param("id")String id);
	/**
	 * 通过MpaContactsCollect的id更新MpaContactsCollect中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateMpaContactsCollectById(MpaContactsCollect enti);
	/**
	 * 通过MpaContactsCollect的id更新MpaContactsCollect中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyMpaContactsCollectById(MpaContactsCollect enti);

	int updateNonEmptyMpaContactsCollectByRoomId(MpaContactsCollect enti);

	int deleteMpaContactsCollectByMap(Map<String, Object> map);
}