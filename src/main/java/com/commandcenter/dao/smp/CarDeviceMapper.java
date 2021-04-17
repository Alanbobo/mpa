/**
 *	   <p>application name：     VehicleMapper</p>
 * 	   <p>application describing：  The interface of operation Vehicle</p>
 *	   <p>copyright：            Copyright ®海能达通信股份有限公司版权所有</p>
 *	   <p>company：            Hytera</p>
 *	   <p>time：                2017.02.09</p>
 *	   @author              y17708
 *	   @version              ver 0.1
 */
package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpVehicleDevice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarDeviceMapper {
    /**
     * 录入车辆guid获取车辆绑定设备信息
     * @param staff
     * @return int
     * @throws Exception
     */
    int insertVehicleDevice(Map<String, Object> staff) throws Exception;
    /**
     * 更新
     * @param condition
     * @return int
     * @throws Exception
     */
    int updateVehicleDevice(Map<String, Object> condition) throws Exception;
    /**
     * 查询车辆绑定设备信息
     * @param condition
     * @return List<Map<String, Object>>
     * @throws Exception
     */
    List<Map<String, Object>> queryVehicleDevice(Map<String, Object> condition) throws Exception;
    /**
     * 查询车辆绑定设备信息
     * @param condition
     * @return List<Map<String, Object>>
     * @throws Exception
     */
    Map<String, Object> getVehicleDevice(Map<String, Object> condition) throws Exception;

    /**
     * 根据车辆guid删除绑定设备信息
     * @param condition
     * @return int
     * @throws Exception
     */
    int deleteVehicleDevice(Map<String, Object> condition) throws Exception;

    List<Map<String, Object>> queryVehicleDeviceByCondtions(Map<String, Object> condition) throws Exception;

    /**
     * 清空车辆设备表
     * @return int
     * @throws Exception
     */
    int deleteAllVehicleDeviceBind();

    /**
     * 增加车辆设备表记录
     * @return int
     * @throws Exception
     */
    int insert(SmpVehicleDevice smpVehicleDevice);

    /**
     * 更新辆设备表记录
     * @return int
     * @throws Exception
     */
    int updateByPrimaryKeySelective(SmpVehicleDevice smpVehicleDevice);

    /**
     * 根据guid删除指定表记录
     * @return int
     * @throws Exception
     */
    int deleteByPrimaryKey(String guid);

    /**
     * 根据guid查询对应记录
     * @return int
     * @throws Exception
     */
    SmpVehicleDevice selectByPrimaryKey(String guid);

}
