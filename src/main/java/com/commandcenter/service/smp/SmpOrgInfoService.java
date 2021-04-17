package com.commandcenter.service.smp;


import com.commandcenter.model.contacts.OrgInfoForApp;
import com.commandcenter.model.smp.SmpOrgInfo;

import java.util.List;
import java.util.Map;

/**
 * @author r25437
 * @create 2018-10-29 11:23
 * @desc 组织机构接口
 **/
public interface SmpOrgInfoService {
    /**
     * 根据guid查询组织机构信息
     * @param guid
     * @return
     */
    SmpOrgInfo selectByPrimaryKey(String guid);

    /**
     * 根据用户的guid查询归属的组织机构信息
     * @param userGuid
     * @return
     */
    SmpOrgInfo selectSmpOrgInfoByUserGuid(String userGuid);
    /**
     * 查询日志分析所需组织机构信息
     * @param
     * @return
     */
    List<Map<String, Object>> selectLogOrgInfo();

    int selectOrgMaxVersion();

    /**
     * 查询组织机构数量
     * @return
     */
    int selectOrgCount();

    SmpOrgInfo selectMaxOrgInfo(String userCode);
    List<OrgInfoForApp> selectSmpOrgInfoByParentGuid(Map<String, Object> smpOrgInfo);

    /**
     * 全量组织机构
     * @return
     */
    List<OrgInfoForApp> selectOrgInfoByMap(Map<String,Object> map);
}
