package com.commandcenter.controller.constacts;

import com.commandcenter.common.utils.R;
import com.commandcenter.model.contacts.*;
import com.commandcenter.model.smp.PucSystemInfo;
import com.commandcenter.service.smp.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通讯录相关controller
 */
@RestController
@RequestMapping("/contacts")
public class ConstactsController {
    private static Logger logger = Logger.getLogger(ConstactsController.class);
    @Autowired
    private SmpOrgInfoService smpOrgInfoService;
    @Autowired
    private SmpInterphoneInfoService smpInterphoneInfoService;
    @Autowired
    private SmptSmartappInfoService smptSmartappInfoService;
    @Autowired
    private SmpSsiGroupInfoService smpSsiGroupInfoService;
    @Autowired
    private SmpStaffInfoService smpStaffInfoService;

    @Autowired
    private SmpStaffDeviceService smpStaffDeviceService;

    @Autowired
    private PucSystemInfoService pucSystemInfoService;

    /**
     * 获取通讯录列表
     *
     * @param bodyMap
     * @return
     */
    @RequestMapping(value = "/contactList", method = RequestMethod.POST)
    public R contactList(@RequestBody Map<String, Object> bodyMap) {
        //入参的para节点map
        Map<String, Object> inputParaMap = (Map<String, Object>) bodyMap.get("para");
        //返回值Map
        Map returnMap = new HashMap<String, Object>();
        Map paraMap = new HashMap<String, Object>();
        int version = (int) inputParaMap.get("version");
        int dataType = (int) inputParaMap.get("dataType");

        paraMap.put("version", version);
        //查询map
        try {
            switch (dataType) {
                case 0:
                    List<StaffForApp> staffList = smpStaffInfoService.selectStaffInfoByMap(paraMap);
                    Collections.sort(staffList);
                    returnMap.put("dataList", staffList);
                    break;
                case 1:
                    List<SmartAppForApp> smartList = smptSmartappInfoService.selectSmartInfoByMap(paraMap);
                    Collections.sort(smartList);
                    returnMap.put("dataList", smartList);
                    break;
                case 2:
                    List<DeviceInfoForApp> deviceList = smpInterphoneInfoService.selectDeviceInfoByMap(paraMap);
                    Collections.sort(deviceList);
                    returnMap.put("dataList", deviceList);
                    break;
                case 3:
                    List<GroupForApp> groupList = smpSsiGroupInfoService.selectGroupInfoByMap(paraMap);
                    Collections.sort(groupList);
                    returnMap.put("dataList", groupList);
                    break;
                case 4:
                    List<OrgInfoForApp> orgList = smpOrgInfoService.selectOrgInfoByMap(paraMap);
//                    Collections.sort(orgList);
                    returnMap.put("dataList", orgList);
                    break;
                case 5:
                    List<StaffDeviceInfoForApp> staffDeviceList = smpStaffDeviceService.selectstaffDeviceInfoByMap(paraMap);
                    returnMap.put("dataList", staffDeviceList);
                    break;
                case 6:
                    List<PucSystemInfo> pucSystemList = pucSystemInfoService.selectPucSystemInfoByMap(paraMap);
                    returnMap.put("dataList", pucSystemList);
                    break;
                default:
                    break;
            }

            return R.ok(returnMap);
        } catch (Exception e) {
            logger.error("通讯录列表获取失败:" + e.getMessage());
            e.printStackTrace();
            return R.error(500, "");
        }
    }

}
