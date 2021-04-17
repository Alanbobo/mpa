package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.SmpSsiGroupInfoMapper;
import com.commandcenter.model.contacts.GroupForApp;
import com.commandcenter.model.smp.SmpSsiGroupInfo;
import com.commandcenter.service.smp.SmpSsiGroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SmpSsiGroupInfoServiceImpl implements SmpSsiGroupInfoService {
    @Autowired
    private SmpSsiGroupInfoMapper smpSsiGroupInfoMapper;
    @Override
    public List<GroupForApp> selectAllGroupInfo(String orgGuid) {
        return smpSsiGroupInfoMapper.selectAllGroupInfo(orgGuid);
    }
    @Override
    public SmpSsiGroupInfo selectGroupInfoById(String guid) {
        return smpSsiGroupInfoMapper.selectByPrimaryKey(guid);
    }

    @Override
    public List<GroupForApp> selectGroupInfoByMap(Map<String,Object> map) {
        return smpSsiGroupInfoMapper.selectGroupInfoByMap(map);
    }
}
