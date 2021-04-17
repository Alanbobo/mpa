package com.commandcenter.dao.smp;
import com.commandcenter.model.smp.PucSmartonegroupInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PucSmartonegroupInfoDao {
	/**
	 * 获得PucSmartonegroupInfo数据的总行数
	 * @return
	 */
    long getPucSmartonegroupInfoRowCount();
	/**
	 * 获得PucSmartonegroupInfo数据集合
	 * @return
	 */
    List<PucSmartonegroupInfo> selectPucSmartonegroupInfo();
	/**
	 * 获得一个PucSmartonegroupInfo对象,以参数PucSmartonegroupInfo对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    PucSmartonegroupInfo selectPucSmartonegroupInfoByObj(PucSmartonegroupInfo obj);
	/**
	 * 通过PucSmartonegroupInfo的id获得PucSmartonegroupInfo对象
	 * @param id
	 * @return
	 */
    PucSmartonegroupInfo selectPucSmartonegroupInfoById(Object id);
	/**
	 * 插入PucSmartonegroupInfo到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertPucSmartonegroupInfo(PucSmartonegroupInfo value);
	/**
	 * 插入PucSmartonegroupInfo中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyPucSmartonegroupInfo(PucSmartonegroupInfo value);
	/**
	 * 通过PucSmartonegroupInfo的id删除PucSmartonegroupInfo
	 * @param id
	 * @return
	 */
    int deletePucSmartonegroupInfoById(Object id);
	/**
	 * 通过PucSmartonegroupInfo的id更新PucSmartonegroupInfo中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updatePucSmartonegroupInfoById(PucSmartonegroupInfo enti);
	/**
	 * 通过PucSmartonegroupInfo的id更新PucSmartonegroupInfo中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyPucSmartonegroupInfoById(PucSmartonegroupInfo enti);
    int deleteAllSmartoneGroup();
}