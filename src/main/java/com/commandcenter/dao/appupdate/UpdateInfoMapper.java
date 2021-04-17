package com.commandcenter.dao.appupdate;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
@Mapper
public interface UpdateInfoMapper {
    Map<String, Object> selectAllInfo();
    int update(Map<String, Object> map);
    int insert(Map<String, Object> map);
}
