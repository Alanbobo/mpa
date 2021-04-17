package com.commandcenter.controller.gpsController;

import com.alibaba.fastjson.JSON;
import com.commandcenter.common.activemq.component.PublishToAPP;
import com.commandcenter.common.activemq.component.PublishToMess;
import com.commandcenter.common.activemq.component.PublishToVCS;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.utils.CoordinateConvertUtil;
import com.commandcenter.common.utils.DateUtil;
import com.commandcenter.common.utils.R;
import com.commandcenter.model.gps.GpsDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @author r25437
 * @create 2018-10-23 10:24
 * @desc GPS上报
 **/
@RestController
@RequestMapping("/gps")
public class GpsController {
    @Autowired
    private PublishToVCS publishToVCS;
    @Autowired
    private PublishToMess publishToMess;
    @Value("${jms.mess.url}")
    private String messMq;
    @Value("${jms.vcs.url}")
    private String vcsMq;
    @Autowired
    private PublishToAPP publishToAPP;
    @Value("${system.notice.app.sendAppGps}")
    private String sendAppGps;
    /**
     * 接受GPS信息
     */
    @RequestMapping(value = "/gpsReport", method = RequestMethod.POST)
    public R acceptCase(@RequestBody Map<String,Object> bodyMap)throws IOException {
        //获取app传过来的反馈参数
        Map<String,Object> para = (Map<String,Object>)bodyMap.get("para");
        para.put("device_id",para.get("staff_id"));
        MQParamModel param = new MQParamModel(JSON.toJSONString(para),sendAppGps);
        publishToAPP.sendToApp(param);
        GpsDataModel gpsDataModel = new GpsDataModel();
        double longitude = 0.0;
        double latitude = 0.0;
        for(String key : para.keySet()){
            switch (key){
                case "staff_id":
                    gpsDataModel.setStaffGuid(para.get("staff_id").toString());
                    break;
                case "longitude":
                    longitude = new Double(para.get("longitude").toString()).doubleValue();
                    break;
                case "latitude":
                    latitude = new Double(para.get("latitude").toString()).doubleValue();
                    break;
                case "puc_id":
                    //gpsDataModel.setPucID("00001");
                    gpsDataModel.setPucID(para.get("puc_id").toString());
                    break;
                case "system_id":
                    //gpsDataModel.setSystemID("001");
                    gpsDataModel.setSystemID(para.get("system_id").toString());
                    break;
                case "device_id":
                    gpsDataModel.setDeviceID(para.get("device_id").toString());
                    break;
                case "dispatcher_id":
                    gpsDataModel.setDispatcherID(para.get("dispatcher_id").toString());
                    break;
                case "gps_datetime":
                    gpsDataModel.setGpsDatetime(para.get("gps_datetime").toString());
                    break;
                case "long_we":
                    gpsDataModel.setLongWE(para.get("long_we").toString());
                    break;
                case "lat_ns":
                    gpsDataModel.setLatNS(para.get("lat_ns").toString());
                    break;
                case "speed":
                    //gpsDataModel.setSpeed("100");
                    gpsDataModel.setSpeed(para.get("speed").toString());
                    break;
                case "derection":
                    gpsDataModel.setDirection(para.get("derection").toString());
                    break;
                case "state":
                    gpsDataModel.setState(para.get("state").toString());
                    break;
                case "spacecoordinate":
                    gpsDataModel.setSpaceCoordinate(para.get("spacecoordinate").toString());
                    break;
                case "electricity":
                    gpsDataModel.setElectricity(para.get("electricity").toString());
                    break;
                case "rssidown":
                    gpsDataModel.setRssidown(para.get("rssidown").toString());
                    break;
                case "rssiup":
                    gpsDataModel.setRssiup(para.get("rssiup").toString());
                    break;
                case "sourcetype":
                    gpsDataModel.setSourceType(para.get("sourcetype").toString());
                default:
                    break;
            }
        }
        if(longitude > 0.001 && latitude > 0.001){
            //转换坐标
            double[] gpsArray = CoordinateConvertUtil.gcj2WGS(latitude,longitude);
            gpsDataModel.setLatitude(gpsArray[0]+"");
            gpsDataModel.setLongitude(gpsArray[1]+"");
        }


        gpsDataModel.setGpsTime(DateUtil.curDateTime());
        if(vcsMq.equals(messMq)){
            publishToVCS.publicGpsDataToVCS(gpsDataModel);
        }else{
            publishToVCS.publicGpsDataToVCS(gpsDataModel);
            publishToMess.publicGpsDataToMess(gpsDataModel);
        }
        return R.ok();
    }
}
