package com.commandcenter.service.smp;

import com.commandcenter.model.smp.SmpVehicleDevice;

import java.util.List;

public interface SmpVehicleDeviceService {
    void deleteAllVehicleDeviceBind();
    void batchInsertVehicleDeviceBindData(List<SmpVehicleDevice> vehicleDevices, String type);
}
