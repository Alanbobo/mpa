package com.commandcenter.dao.dictdao;
import com.commandcenter.model.dictmodel.SmptLanguage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmptLanguageDao{
	/**
	 * 获得SmptLanguage数据的总行数
	 * @return
	 */
    long getSmptLanguageRowCount();
	/**
	 * 获得SmptLanguage数据集合
	 * @return
	 */
	List<SmptLanguage> selectSmptLanguage();
	/**
	 * 获得一个SmptLanguage对象,以参数SmptLanguage对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    SmptLanguage selectSmptLanguageByObj(SmptLanguage obj);
	/**
	 * 通过SmptLanguage的id获得SmptLanguage对象
	 * @param id
	 * @return
	 */
    SmptLanguage selectSmptLanguageById(String id);
	/**
	 * 插入SmptLanguage到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertSmptLanguage(SmptLanguage value);
	/**
	 * 插入SmptLanguage中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptySmptLanguage(SmptLanguage value);
	/**
	 * 通过SmptLanguage的id删除SmptLanguage
	 * @param id
	 * @return
	 */
    int deleteSmptLanguageById(String id);
	/**
	 * 通过SmptLanguage的id更新SmptLanguage中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateSmptLanguageById(SmptLanguage enti);
	/**
	 * 通过SmptLanguage的id更新SmptLanguage中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptySmptLanguageById(SmptLanguage enti);
}