package com.commandcenter.service.appupdate.impl;

import com.commandcenter.dao.appupdate.UpdateInfoMapper;
import com.commandcenter.service.appupdate.UpdateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("updateInfoService")
public class UpdateInfoServiceImpl implements UpdateInfoService {
    @Autowired
    private UpdateInfoMapper updateInfoMapper;
    @Override
    public Map<String, Object> selectAllInfo(){
        return updateInfoMapper.selectAllInfo();
    }
    @Override
    public int update(Map<String, Object> map){
        return updateInfoMapper.update(map);
    }
    @Override
    public int insert(Map<String, Object> map){
        return updateInfoMapper.insert(map);
    }
}
