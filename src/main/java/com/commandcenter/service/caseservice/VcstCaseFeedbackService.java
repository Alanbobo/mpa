package com.commandcenter.service.caseservice;
import java.util.List;
import java.util.Map;

import com.commandcenter.model.casemodel.VcstCaseFeedback;

public interface VcstCaseFeedbackService{
	/**
	 * 获得VcstCaseFeedback数据的总行数
	 * @return
	 */
    long getVcstCaseFeedbackRowCount();
	/**
	 * 获得VcstCaseFeedback数据集合
	 * @return
	 */
    List<VcstCaseFeedback> selectVcstCaseFeedback();
	/**
	 * 获得一个VcstCaseFeedback对象,以参数VcstCaseFeedback对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    List<VcstCaseFeedback> selectVcstCaseFeedbackByObj(VcstCaseFeedback obj);
	/**
	 * 通过VcstCaseFeedback的id获得VcstCaseFeedback对象
	 * @param id
	 * @return
	 */
    VcstCaseFeedback selectVcstCaseFeedbackById(String id);
	/**
	 * 插入VcstCaseFeedback到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertVcstCaseFeedback(VcstCaseFeedback value);
	/**
	 * 插入VcstCaseFeedback中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyVcstCaseFeedback(VcstCaseFeedback value);
	/**
	 * 通过VcstCaseFeedback的id删除VcstCaseFeedback
	 * @param id
	 * @return
	 */
    int deleteVcstCaseFeedbackById(String id);
	/**
	 * 通过VcstCaseFeedback的id更新VcstCaseFeedback中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateVcstCaseFeedbackById(VcstCaseFeedback enti);
	/**
	 * 通过VcstCaseFeedback的id更新VcstCaseFeedback中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyVcstCaseFeedbackById(VcstCaseFeedback enti);

	/**
	 * 批量插入警情反馈
	 *
	 * @param vcstCaseFeedbackList
	 * @throws Exception
	 */
	void batchInsertFeedback(List<VcstCaseFeedback> vcstCaseFeedbackList);


	/**
	 * 到达现场StaffGuid
	 *
	 * @param map
	 */

	List<String> getArrivedStaffList(Map<String,Object> map);
}