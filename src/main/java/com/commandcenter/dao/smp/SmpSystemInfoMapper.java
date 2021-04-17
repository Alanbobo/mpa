package com.commandcenter.dao.smp;

import com.commandcenter.model.dictmodel.SmptSystemInfo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SmpSystemInfoMapper {
    int deleteByPrimaryKey(String guid);

    int insert(SmptSystemInfo record);

    int insertSelective(SmptSystemInfo record);

    SmptSystemInfo selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmptSystemInfo record);

    int updateByPrimaryKey(SmptSystemInfo record);
    void deleteAllSmpSystemInfo();
    int selectSystemCount();
    int selectSystemMaxVersion();
}