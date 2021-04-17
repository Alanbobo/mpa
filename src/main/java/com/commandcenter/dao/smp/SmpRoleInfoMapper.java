package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpRoleInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmpRoleInfoMapper {
    int deleteByPrimaryKey(String guid);
    void deleteAllSmpRoleInfo();

    int insert(SmpRoleInfo record);

    int insertSelective(SmpRoleInfo record);

    SmpRoleInfo selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpRoleInfo record);

    int updateByPrimaryKey(SmpRoleInfo record);
    int selectRoleCount();
}