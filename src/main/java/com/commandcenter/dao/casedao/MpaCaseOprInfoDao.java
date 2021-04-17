package com.commandcenter.dao.casedao;
import com.commandcenter.model.casemodel.MpaCaseOprInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MpaCaseOprInfoDao {

	/**
	 * 插入mpacaseopr表
	 * @param mpaCaseOprInfo
	 * @return
	 */
	int insertMpaCaseOpr(MpaCaseOprInfo mpaCaseOprInfo);

	/**
	 * 查询列表
	 * @return
	 */
	List<MpaCaseOprInfo> selectMpaCaseOprList();

	/**
	 * 批量删除警情通知
	 * @param ids
	 * @return
	 */
	int deleteMpaCaseOprInfoByIds(List<Long> ids);

	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	List<MpaCaseOprInfo> selectMpaCaseOprListByMod(Map<String,Object> map);
}