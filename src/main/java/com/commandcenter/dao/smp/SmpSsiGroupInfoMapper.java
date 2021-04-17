package com.commandcenter.dao.smp;

import com.commandcenter.model.contacts.GroupForApp;
import com.commandcenter.model.smp.SmpSsiGroupInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SmpSsiGroupInfoMapper {
    int deleteByPrimaryKey(String guid);

    int deleteByPrimaryKeyLogic(SmpSsiGroupInfo smpSsiGroupInfo);

    int insert(SmpSsiGroupInfo record);

    int insertSelective(SmpSsiGroupInfo record);

    SmpSsiGroupInfo selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpSsiGroupInfo record);

    int updateByPrimaryKey(SmpSsiGroupInfo record);
    void deleteAllGroupInfo();
    int selectSsiGroupCount();
    List<GroupForApp> selectAllGroupInfo(String orgGuid);
    SmpSsiGroupInfo selectGroupInfoById(String orgGuid);

    List<GroupForApp> selectGroupInfoByMap(Map<String,Object> map);
}