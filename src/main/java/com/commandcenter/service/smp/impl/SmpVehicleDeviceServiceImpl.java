package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.CarDeviceMapper;
import com.commandcenter.model.smp.SmpVehicleDevice;
import com.commandcenter.service.smp.SmpVehicleDeviceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmpVehicleDeviceServiceImpl implements SmpVehicleDeviceService {

    private static Logger log = Logger.getLogger(SmpVehicleDeviceServiceImpl.class);
    @Autowired
    private CarDeviceMapper carDeviceMapper;
    @Override
    public void deleteAllVehicleDeviceBind() {
        carDeviceMapper.deleteAllVehicleDeviceBind();
    }

    @Override
    public void batchInsertVehicleDeviceBindData(List<SmpVehicleDevice> vehicleDevices, String type) {
        switch (type) {
            case "add":
                log.info("正在进行批量" + "车辆设备增加" + "处理");
                vehicleDevices.stream().forEach(carDeviceMapper::insert);
                break;
            case "update":
                log.info("正在进行批量" + "车辆设备修改" + "处理");
                vehicleDevices.stream().forEach(carDeviceMapper::updateByPrimaryKeySelective);
                break;
            case "delete":
                log.info("正在进行批量" + "车辆设备删除" + "处理");
                vehicleDevices.stream().forEach(info -> {
                    //删除车辆设备信息
                    carDeviceMapper.deleteByPrimaryKey(info.getGuid());
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "车辆设备" + "处理");
                vehicleDevices.stream().forEach(info -> {
                    if(carDeviceMapper.selectByPrimaryKey(info.getGuid())!=null){
                        carDeviceMapper.updateByPrimaryKeySelective(info);
                    }else{
                        carDeviceMapper.insert(info);
                    }
                });
                break;
            default:

                break;
        }
    }
}
