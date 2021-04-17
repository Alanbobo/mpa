package com.commandcenter.controller.constacts;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.commandcenter.common.utils.R;
import com.commandcenter.model.contacts.MpaContactsCollect;
import com.commandcenter.service.constacts.MpaContactsCollectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contacts/collect")
public class ContactsCollectController {

    private static Logger logger = Logger.getLogger(ContactsCollectController.class);

    @Autowired
    private MpaContactsCollectService mpaContactsCollectService;


    @RequestMapping(value ="/saveOrUpdateCollectInfo",method = RequestMethod.POST)
    public R saveCollectInfo(@RequestBody MpaContactsCollect mpaContactsCollect){
        try {
            logger.info("收藏夹-saveOrUpdateCollectInfo============" + JSON.toJSONString(mpaContactsCollect, SerializerFeature.WriteMapNullValue));
            MpaContactsCollect collect = mpaContactsCollectService.selectMpaContactsCollectByObj(mpaContactsCollect);
            if (collect != null) {
                mpaContactsCollectService.updateNonEmptyMpaContactsCollectByRoomId(mpaContactsCollect);
            }else{
                mpaContactsCollectService.insertNonEmptyMpaContactsCollect(mpaContactsCollect);
            }

        }catch (Exception e){
            e.printStackTrace();
            logger.error("新增修改收藏夹失败:" + e.getMessage());
            return R.error(500, "");
        }
        return R.ok();
    }


    @RequestMapping(value = "/deleteCollectInfo", method = RequestMethod.POST)
    public R deleteCollectInfo(@RequestBody Map<String, Object> bodyMap) {

        logger.info("收藏夹-deleteCollectInfo============" + JSON.toJSONString(bodyMap, SerializerFeature.WriteMapNullValue));
        //入参的para节点map
        Map<String, Object> inputParaMap = (Map<String, Object>) bodyMap.get("para");
        try {
            int n = mpaContactsCollectService.deleteMpaContactsCollectByMap(inputParaMap);
            if (n == 0) {
                return R.error(500, "未能找到删除记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除收藏失败:" + e.getMessage());
            return R.error(500, "");
        }
        return R.ok();
    }


    @RequestMapping(value = "/collectList",method = RequestMethod.POST)
    public R collectList(@RequestBody Map<String, Object> bodyMap) {

        logger.info("收藏夹-collectList============" + JSON.toJSONString(bodyMap, SerializerFeature.WriteMapNullValue));
        //具体查询时拼装map
        Map<String, Object> paraMap = new HashMap<>();
        Map returnMap = new HashMap<String, Object>();
        //入参的para节点map
        Map<String, Object> inputParaMap = (Map<String, Object>) bodyMap.get("para");
        try {
            for (String key : inputParaMap.keySet()) {
                switch (key) {
                    case "userGuid":
                        String userGuid = inputParaMap.get("userGuid").toString();
                        if (!(null == userGuid || "".equals(userGuid))) {
                            paraMap.put("userGuid", userGuid);
                        }
                        break;
                    default:
                        break;
                }
            }
            List<MpaContactsCollect> mpaContactsCollectList = mpaContactsCollectService.selectMpaContactsCollect(paraMap);
            returnMap.put("data",mpaContactsCollectList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询收藏列表失败:" + e.getMessage());
            return R.error(500, "");
        }
        return R.ok(returnMap);
    }
}
