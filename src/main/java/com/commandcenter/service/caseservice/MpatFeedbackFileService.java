package com.commandcenter.service.caseservice;
import java.util.List;
import com.commandcenter.model.casemodel.MpatFeedbackFile;
public interface MpatFeedbackFileService{
	/**
	 * 获得MpatFeedbackFile数据的总行数
	 * @return
	 */
    long getMpatFeedbackFileRowCount();
	/**
	 * 获得MpatFeedbackFile数据集合
	 * @return
	 */
    List<MpatFeedbackFile> selectMpatFeedbackFile();
	/**
	 * 获得一个MpatFeedbackFile对象,以参数MpatFeedbackFile对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    MpatFeedbackFile selectMpatFeedbackFileByObj(MpatFeedbackFile obj);
	/**
	 * 通过MpatFeedbackFile的id获得MpatFeedbackFile对象
	 * @param id
	 * @return
	 */
    MpatFeedbackFile selectMpatFeedbackFileById(Object id);
	/**
	 * 插入MpatFeedbackFile到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertMpatFeedbackFile(MpatFeedbackFile value);
	/**
	 * 插入MpatFeedbackFile中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyMpatFeedbackFile(MpatFeedbackFile value);
	/**
	 * 通过MpatFeedbackFile的id删除MpatFeedbackFile
	 * @param id
	 * @return
	 */
    int deleteMpatFeedbackFileById(Object id);
	/**
	 * 通过MpatFeedbackFile的id更新MpatFeedbackFile中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateMpatFeedbackFileById(MpatFeedbackFile enti);
	/**
	 * 通过MpatFeedbackFile的id更新MpatFeedbackFile中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyMpatFeedbackFileById(MpatFeedbackFile enti);

	/**
	 * @param originalId 原始反馈Id,用于在未返回feedbackId前,标识属于同一条反馈的附件
	 * @param feedbackId 反馈单Id
	 * @return
	 * @throws Exception
	 */
	int updateFileFeedbackId(String originalId, String feedbackId);
}