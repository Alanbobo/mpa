package com.commandcenter.dao.dictdao;
import com.commandcenter.model.dictmodel.SmptSystemInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmptSystemInfoDao{
	/**
	 * 获得SmptSystemInfo数据的总行数
	 * @return
	 */
    long getSmptSystemInfoRowCount();
	/**
	 * 获得SmptSystemInfo数据集合
	 * @return
	 */
	List<SmptSystemInfo> selectSmptSystemInfo();
	/**
	 * 获得一个SmptSystemInfo对象,以参数SmptSystemInfo对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    SmptSystemInfo selectSmptSystemInfoByObj(SmptSystemInfo obj);
	/**
	 * 通过SmptSystemInfo的id获得SmptSystemInfo对象
	 * @param id
	 * @return
	 */
    SmptSystemInfo selectSmptSystemInfoById(String id);
	/**
	 * 插入SmptSystemInfo到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertSmptSystemInfo(SmptSystemInfo value);
	/**
	 * 插入SmptSystemInfo中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptySmptSystemInfo(SmptSystemInfo value);
	/**
	 * 通过SmptSystemInfo的id删除SmptSystemInfo
	 * @param id
	 * @return
	 */
    int deleteSmptSystemInfoById(String id);
	/**
	 * 通过SmptSystemInfo的id更新SmptSystemInfo中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateSmptSystemInfoById(SmptSystemInfo enti);
	/**
	 * 通过SmptSystemInfo的id更新SmptSystemInfo中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptySmptSystemInfoById(SmptSystemInfo enti);
}