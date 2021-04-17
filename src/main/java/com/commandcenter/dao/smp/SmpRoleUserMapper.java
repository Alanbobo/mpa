package com.commandcenter.dao.smp;

import com.commandcenter.common.activemq.model.SmpUserRoleSync;
import com.commandcenter.model.smp.SmpRoleUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmpRoleUserMapper {
    int insertSmpUserRole(SmpUserRoleSync record);
    int insert(SmpRoleUser record);
    int insertSelective(SmpRoleUser record);
    void updateByPrimaryKeySelective(SmpRoleUser record);
    void deleteByUserId(String userGuid);
    void deleteByUserRoleId(SmpRoleUser record);
    int selectRoleUserNum();
    void deleteAllRoleUserInfo();
}