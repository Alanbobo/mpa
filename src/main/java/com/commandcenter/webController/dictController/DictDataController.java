
package com.commandcenter.webController.dictController;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.commandcenter.model.dictmodel.SmptDictInfo;
import com.commandcenter.model.dictmodel.SmptDictValue;
import com.commandcenter.model.dictmodel.SmptLanguage;
import com.commandcenter.model.dictmodel.SmptSystemInfo;
import com.commandcenter.service.dictservice.SmptDictInfoService;
import com.commandcenter.service.dictservice.SmptDictValueService;
import com.commandcenter.service.dictservice.SmptLanguageService;
import com.commandcenter.service.dictservice.SmptSystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 */

@RestController
@RequestMapping("/webController/data")
public class DictDataController {
    @Autowired
    private SmptDictInfoService smptDictInfoService;
    @Autowired
    private SmptDictValueService smptDictValueService;
    @Autowired
    private SmptLanguageService smptLanguageService;
    @Autowired
    private SmptSystemInfoService smptSystemInfoService;
    @RequestMapping(value = "/getDictInfo", method = RequestMethod.POST)
    public String getDictInfo(){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            List<SmptDictInfo> dictInfoList = smptDictInfoService.selectSmptDictInfo();
            resultMap.put("success","success");
            resultMap.put("body",dictInfoList);
        }catch (Exception e){
            resultMap.put("success","failure");
            resultMap.put("body","");
            resultMap.put("msg","请求失败");
        }
        return JSONObject.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }



    @RequestMapping(value = "/getDictValue", method = RequestMethod.POST)
    public String getDictValue(){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            List<SmptDictValue> dictValueList = smptDictValueService.selectSmptDictValue();
            resultMap.put("success","success");
            resultMap.put("body",dictValueList);
        }catch (Exception e){
            resultMap.put("success","failure");
            resultMap.put("body","");
            resultMap.put("msg","请求失败");
        }
        return JSONObject.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }
    @RequestMapping(value = "/getLanguage", method = RequestMethod.POST)
    public String getLanguage(){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            List<SmptLanguage> smpLanguageList =  smptLanguageService.selectSmptLanguage();
            resultMap.put("success","success");
            resultMap.put("body",smpLanguageList);
        }catch (Exception e){
            resultMap.put("success","failure");
            resultMap.put("body","");
            resultMap.put("msg","请求失败");
        }
        return JSONObject.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }

    @ResponseBody
    @RequestMapping(value = "/getSystem", method = RequestMethod.POST)
    public String getSystem(){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            List<SmptSystemInfo> smpSystemList = smptSystemInfoService.selectSmptSystemInfo();
            resultMap.put("success","success");
            resultMap.put("body",smpSystemList);
        }catch (Exception e){
            resultMap.put("success","failure");
            resultMap.put("body","");
            resultMap.put("msg","请求失败");
        }
        Object o= JSONObject.toJSON(resultMap).toString();
        return JSONObject.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }


}

