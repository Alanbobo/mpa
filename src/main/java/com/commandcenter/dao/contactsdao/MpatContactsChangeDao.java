package com.commandcenter.dao.contactsdao;
import com.commandcenter.model.contacts.MpatContactsChange;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MpatContactsChangeDao {
	/**
	 * 获得MpatContactsChange数据的总行数
	 * @return
	 */
    long getMpatContactsChangeRowCount();
	/**
	 * 获得MpatContactsChange数据集合
	 * @return
	 */
    List<MpatContactsChange> selectMpatContactsChange(Map<String, Object> map);
	/**
	 * 获得一个MpatContactsChange对象,以参数MpatContactsChange对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    MpatContactsChange selectMpatContactsChangeByObj(MpatContactsChange obj);
	/**
	 * 通过MpatContactsChange的id获得MpatContactsChange对象
	 * @param id
	 * @return
	 */
    MpatContactsChange selectMpatContactsChangeById(String id);
	/**
	 * 插入MpatContactsChange到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertMpatContactsChange(MpatContactsChange value);
	/**
	 * 插入MpatContactsChange中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyMpatContactsChange(MpatContactsChange value);
	/**
	 * 通过MpatContactsChange的id删除MpatContactsChange
	 * @param id
	 * @return
	 */
    int deleteMpatContactsChangeById(String id);
	/**
	 * 通过MpatContactsChange的id更新MpatContactsChange中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateMpatContactsChangeById(MpatContactsChange enti);
	/**
	 * 通过MpatContactsChange的id更新MpatContactsChange中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyMpatContactsChangeById(MpatContactsChange enti);


	int deleteAll();
}