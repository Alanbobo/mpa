package com.commandcenter.webController.TestController;

import com.commandcenter.common.utils.R;
import com.commandcenter.controller.gpsController.GpsController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * 测试压测的gps上报
 */
@RestController
@RequestMapping("/webController/gpsReportTest")
public class TestGpsController extends GpsController {

    @RequestMapping(value = "/gps",method = RequestMethod.POST)
    public R gpsReportTest(@RequestBody Map<String,Object> bodyMap) throws IOException {
        return super.acceptCase(bodyMap);
    }
}
