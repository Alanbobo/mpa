package com.commandcenter.webController.TestController;


import com.commandcenter.common.utils.R;
import com.commandcenter.controller.ppcsController.PpcsController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/webController/ipcsTest")
public class TestIpcsController extends PpcsController {
    @RequestMapping(value = "/dutyListAndDetailTest", method = RequestMethod.POST)
    public R dutyListTest(@RequestBody Map<String,Object> bodyMap) throws IOException {
        return super.getDutyListAndDetail(bodyMap);
    }

    @RequestMapping(value = "/getDutyDetailTest",method = RequestMethod.POST)
    public R getDutyDetailTest(@RequestBody Map<String, Object> bodyMap) throws IOException {
        return super.getDutyDetail(bodyMap);
    }
}
