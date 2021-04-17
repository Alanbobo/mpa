package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.PostInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 170725437
 * @create 2020-03-05 12:52
 * @desc PostInfoMapper
 **/
@Mapper
public interface PostInfoMapper {

    /**
     * 查询数据
     * @param  map
     * @return list
     */
    List<PostInfo> selectPostInfoList(Map<String, Object> map);
    /**
     * 查询单条数据
     * @param  map
     * @return PostInfo
     */
    PostInfo getPostInfoOne(Map<String, Object> map);
    /**
     * 新增数据
     * @param  postInfo
     * @return int
     */
    int insertPostInfo(PostInfo postInfo);
    /**
     * 更新数据
     * @param  postInfo
     * @return int
     */
    int updatePostInfo(PostInfo postInfo);

    /**
     * 批量删除数据
     * @param  conditions
     * @return int
     */
    int deletePostInfo(Map<String, Object> conditions);
    /**
     * 查询条件下的总数
     * @param  postInfo
     * @return int
     */
    int queryPostInfoCount(PostInfo postInfo);

    /**
     * 清理全部数据
     */
    int deleteAllSmpPostInfo();

    /**
     * 按唯一标识清理数据
     */
    int deleteSmpPostInfoByGuid(String guid);

    /**
     * 按唯一标识获取数据
     */
    PostInfo selectSmpPostInfoByGuid(String guid);

}
