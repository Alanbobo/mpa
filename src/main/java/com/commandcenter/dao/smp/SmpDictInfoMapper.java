package com.commandcenter.dao.smp;


import com.commandcenter.model.smp.SmpDictInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmpDictInfoMapper {
    int deleteByPrimaryKey(String guid);

    int insert(SmpDictInfo record);

    int insertSelective(SmpDictInfo record);

    SmpDictInfo selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpDictInfo record);

    int updateByPrimaryKey(SmpDictInfo record);

    void deleteAllSmpDictInfo();
    int selectDictInfoCount();
    int selectDictMaxVersion();
}