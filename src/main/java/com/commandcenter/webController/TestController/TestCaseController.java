package com.commandcenter.webController.TestController;

import com.commandcenter.common.utils.*;
import com.commandcenter.controller.caseController.CaseController;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * @author r25437
 * @create 2019-02-21 8:53
 * @desc 测试压测单独提供服务
 **/
@RestController
@RequestMapping("/webController/caseTest")
public class TestCaseController extends CaseController{

    /**
     * 警情列表查询
     */
    @RequestMapping(value = "/caseListTest", method = RequestMethod.POST)
    public R getTestCaseList(@RequestBody Map<String,Object> bodyMap) throws IOException {
        return super.getCaseList(bodyMap);
    }

    /**
     *反馈接口
     * @param bodyMap
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/feedBackTest", method = RequestMethod.POST)
    public R feedBackTest(@RequestBody Map<String,Object> bodyMap) throws Exception {
        return super.feedBack(bodyMap);
    }
}
