package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.SmpCarInfoMapper;
import com.commandcenter.model.smp.SmpCarInfo;
import com.commandcenter.service.smp.SmpVehicleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SmpVehicleServiceImpl implements SmpVehicleService {
    private static Logger log = Logger.getLogger(SmpVehicleServiceImpl.class);
    @Autowired
    private SmpCarInfoMapper smpCarInfoMapper;
    @Override
    public int deleteAllSmpVehicleInfo() {
        return smpCarInfoMapper.deleteAllSmpVehicleInfo();
    }

    @Override
    public void batchInsertSmpVehicleInfo(List<SmpCarInfo> smpCarInfoList, String type) {
        switch (type) {
            case "add":
                log.info("正在进行批量" + "车辆信息增加" + "处理");
                smpCarInfoList.stream().forEach(smpCarInfoMapper::insertSelective);
                break;
            case "update":
                log.info("正在进行批量" + "车辆信息修改" + "处理");
                smpCarInfoList.stream().forEach(smpCarInfoMapper::updateByPrimaryKeySelective);
                break;
            case "delete":
                log.info("正在进行批量" + "车辆信息删除" + "处理");
                smpCarInfoList.stream().forEach(info -> {
                    //删除用户信息
                    smpCarInfoMapper.deleteByPrimaryKey(info.getGuid());
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "车辆信息" + "处理");
                smpCarInfoList.stream().forEach(info -> {
                    if(smpCarInfoMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpCarInfoMapper.updateByPrimaryKey(info);
                    }else{
                        smpCarInfoMapper.insertSelective(info);
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public int selectVehicleCount() {
        return smpCarInfoMapper.selectVehicleCount();
    }

    @Override
    public List<Map> selectMapVehicle() {
        return smpCarInfoMapper.selectMapVehicle();
    }

    @Override
    public Map selectVehicleGpsByGuid(String guid) {
        return smpCarInfoMapper.selectVehicleGpsByGuid(guid);
    }
}
