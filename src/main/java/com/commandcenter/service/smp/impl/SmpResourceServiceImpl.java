package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.SmpResourcesMapper;
import com.commandcenter.model.smp.SmpResources;
import com.commandcenter.service.smp.SmpResourceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("SmpResourceService")
public class SmpResourceServiceImpl implements SmpResourceService {
    private static Logger log = Logger.getLogger(SmpResourceServiceImpl.class);
    @Autowired
    private SmpResourcesMapper smpResourcesMapper;
    @Override
    public int deleteAllSmpResourceInfo() {
        return smpResourcesMapper.deleteAllSmpResourceInfo();
    }

    /**
     * 批量增加Resource数据
     * @param smpResourcesList
     * @param type
     */
    @Override
    public void batchInsertSmpResourceInfo(List<SmpResources> smpResourcesList, String type) {
        switch (type) {
            case "add":
                log.info("正在进行批量" + "资源信息增加" + "处理");
                smpResourcesList.stream().forEach(smpResourcesMapper::insert);
                break;
            case "update":
                log.info("正在进行批量" + "资源信息修改" + "处理");
                smpResourcesList.stream().forEach(smpResourcesMapper::updateByPrimaryKeySelective);
                break;
            case "delete":
                log.info("正在进行批量" + "资源信息删除" + "处理");
                smpResourcesList.stream().forEach(info -> {
                    //删除用户信息
                    smpResourcesMapper.deleteByPrimaryKey(info.getGuid());
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "资源信息" + "处理");
                smpResourcesList.stream().forEach(info -> {
                    if(smpResourcesMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpResourcesMapper.updateByPrimaryKey(info);
                    }else{
                        smpResourcesMapper.insert(info);
                    }
                });
                break;
            default:
                break;
        }
    }
    @Override
    public List<Map> selectResourcesList(){
        return smpResourcesMapper.selectResourcesList();
    }
}
