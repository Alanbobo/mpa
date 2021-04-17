package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.SmpOrgInfoMapper;
import com.commandcenter.model.contacts.OrgInfoForApp;
import com.commandcenter.model.smp.SmpOrgInfo;
import com.commandcenter.service.smp.SmpOrgInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author r25437
 * @create 2018-10-29 11:25
 * @desc 组织机构接口实现
 **/
@Service("smpOrgInfoService")
public class SmpOrgInfoServiceImpl implements SmpOrgInfoService {

    @Autowired
    private SmpOrgInfoMapper smpOrgInfoMapper;
    /**
     * 根据guid查询组织机构信息
     * @param guid
     * @return
     */
    @Override
    public SmpOrgInfo selectByPrimaryKey(String guid){
        return smpOrgInfoMapper.selectByPrimaryKey(guid);
    }

    /**
     * 根据用户的guid查询归属的组织机构信息
     * @param userGuid
     * @return
     */
    @Override
    public SmpOrgInfo selectSmpOrgInfoByUserGuid(String userGuid){
        return smpOrgInfoMapper.selectSmpOrgInfoByUserGuid(userGuid);
    }
    /**
     * 查询日志分析所需组织机构信息
     * @param
     * @return
     */
    @Override
    public List<Map<String, Object>> selectLogOrgInfo(){
        return smpOrgInfoMapper.selectLogOrgInfo();
    }

    @Override
    public int selectOrgMaxVersion() {
        return smpOrgInfoMapper.selectOrgMaxVersion();
    }

    @Override
    public int selectOrgCount(){
        return smpOrgInfoMapper.selectOrgCount();
    }

    @Override
    public SmpOrgInfo selectMaxOrgInfo(String userCode) {
        return smpOrgInfoMapper.selectMaxOrgInfo(userCode);
    }

    @Override
    public List<OrgInfoForApp> selectSmpOrgInfoByParentGuid(Map<String,Object> smpOrgInfo) {
        return smpOrgInfoMapper.selectSmpOrgInfoByParentGuid(smpOrgInfo);
    }

    @Override
    public List<OrgInfoForApp> selectOrgInfoByMap(Map<String,Object> map) {
        return smpOrgInfoMapper.selectOrgInfoByMap(map);
    }
}
