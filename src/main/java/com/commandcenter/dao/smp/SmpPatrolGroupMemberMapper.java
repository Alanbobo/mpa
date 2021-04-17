package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpPatrolGroupMember;

public interface SmpPatrolGroupMemberMapper {
    int insert(SmpPatrolGroupMember record);

    int insertSelective(SmpPatrolGroupMember record);
}