package com.commandcenter.dao.smp;

import com.commandcenter.model.contacts.OrgInfoForApp;
import com.commandcenter.model.smp.SmpOrgInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SmpOrgInfoMapper {
    int deleteByPrimaryKey(String guid);

    int deleteByPrimaryKeyLogic(SmpOrgInfo record);

    int insert(SmpOrgInfo record);

    int insertSelective(SmpOrgInfo record);

    SmpOrgInfo selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmpOrgInfo record);

    int updateByPrimaryKeyWithBLOBs(SmpOrgInfo record);

    int updateByPrimaryKey(SmpOrgInfo record);

    void deleteAllSmpOrgInfo();

    void deleteAllSmpStaffInfo();

    /**
     * 根据用户的guid查询归属的组织机构信息
     * @param userGuid
     * @return
     */
    SmpOrgInfo selectSmpOrgInfoByUserGuid(String userGuid);
    SmpOrgInfo selectMaxOrgInfo(String userCode);

    List<Map<String, Object>> selectLogOrgInfo();
    int selectOrgMaxVersion();

    int selectOrgCount();
    List<OrgInfoForApp> selectSmpOrgInfoByParentGuid(Map<String, Object> smpOrgInfo);

    List<OrgInfoForApp> selectOrgInfoByMap(Map<String,Object> map);

}