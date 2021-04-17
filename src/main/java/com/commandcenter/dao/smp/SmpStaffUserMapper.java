package com.commandcenter.dao.smp;

import com.commandcenter.common.activemq.model.SmpUserStaffBindSync;
import com.commandcenter.model.smp.SmpStaffUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmpStaffUserMapper {
    int insert(SmpStaffUser record);

    int insertSelective(SmpStaffUser record);

    void updateByPrimaryKeySelective(SmpUserStaffBindSync smpUserStaffBindSync);

    void deleteByPrimaryKey(String guid);

    void insertUserStaff(SmpUserStaffBindSync smpUserStaffBindSync);

    void deleteAllUserStaffBind();

    void deleteByUserId(String userGuid);

    int selectStaffUserCount();
    String selectStaffByUserGuid(String userGuid);
    SmpStaffUser selectByPrimaryKey(String guid);
    List<SmpStaffUser> selectStaffToken();
}