package com.commandcenter.dao.dictdao;
import com.commandcenter.model.dictmodel.SmptDictValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmptDictValueDao{
	/**
	 * 获得SmptDictValue数据的总行数
	 * @return
	 */
    long getSmptDictValueRowCount();
	/**
	 * 获得SmptDictValue数据集合
	 * @return
	 */
	List<SmptDictValue> selectSmptDictValue();
	/**
	 * 获得一个SmptDictValue对象,以参数SmptDictValue对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    SmptDictValue selectSmptDictValueByObj(SmptDictValue obj);
	/**
	 * 通过SmptDictValue的id获得SmptDictValue对象
	 * @param id
	 * @return
	 */
    SmptDictValue selectSmptDictValueById(String id);
	/**
	 * 插入SmptDictValue到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertSmptDictValue(SmptDictValue value);
	/**
	 * 插入SmptDictValue中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptySmptDictValue(SmptDictValue value);
	/**
	 * 通过SmptDictValue的id删除SmptDictValue
	 * @param id
	 * @return
	 */
    int deleteSmptDictValueById(String id);
	/**
	 * 通过SmptDictValue的id更新SmptDictValue中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateSmptDictValueById(SmptDictValue enti);
	/**
	 * 通过SmptDictValue的id更新SmptDictValue中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptySmptDictValueById(SmptDictValue enti);
}