package com.commandcenter.webController.logController;


import com.commandcenter.common.utils.R;
import com.commandcenter.service.logservice.MpaLogAnalyseService;
import com.commandcenter.service.smp.SmpOrgInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/webController/log")
public class LogAnalysisController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SmpOrgInfoService smpOrgInfoService;
    @Autowired
    private MpaLogAnalyseService mpaLogAnalyseService;

    /**
     * 为日志分析页面返回组织机构树所需数据
     * @return
     * @author q32756
     */
    @RequestMapping("/orgList")
    public R getOrgList(){
        //获取带有父id字段组织机构集合
        List<Map<String,Object>> logOrgList = smpOrgInfoService.selectLogOrgInfo();
        Map logReturnOrg = new HashMap(1);
        if(logOrgList.size()!=0){
            for (Map<String, Object> map: logOrgList) {
                //将key值修改为页面需要值
                map.put("id",map.get("orgIdentifier"));
                map.put("pId",map.get("parentIdentifier"));
                map.put("name",map.get("orgName"));
                if(map.get("orgIdentifier").equals(map.get("parentIdentifier"))){
                    map.put("open",true);
                }
                map.remove("orgIdentifier");
                map.remove("parentIdentifier");
                map.remove("orgName");
            }
            logReturnOrg.put("allOrg",logOrgList);
            return R.ok(logReturnOrg);
        }
        return R.ok();
    }
    /**
     * 为日志分析页面返回所需数据
     * @return
     * @author q32756
     */
    @RequestMapping("/selectNumber")
    public R selectNumber(@ModelAttribute("startTime") String startTime,@ModelAttribute("endTime") String endTime,@ModelAttribute("orgCode") String orgCode,@ModelAttribute("functionName") String functionName){
        Map<String, Object> content = new HashMap<>(5);
        Map<String, Object> resting = new HashMap<>(4);
        StringBuilder logDatas = new StringBuilder().append("[");
        StringBuilder logTimes = new StringBuilder().append("[");
        content.put("endTime",endTime);
        content.put("startTime",startTime);
        content.put("orgCode",orgCode);
        content.put("functionName",functionName);
        int num = mpaLogAnalyseService.getMpaLogAnalyseRowCountByOrgTimeOpName(content);
        List<Map<String, Object>> analyseList = mpaLogAnalyseService.getCountAndTimeByOrgTimeOpName(content);
        for (Map<String, Object> statistics : analyseList) {
            logDatas.append(statistics.get("count")+",");
            logTimes.append("'"+statistics.get("logTime")+"',");
        }
        logDatas.append("]");
        logTimes.append("]");
        resting.put("allNumber", num);
        resting.put("datas", logDatas);
        resting.put("times", logTimes);
        logger.info("返回的log数据：allNumber-" + num + "datas*-" + logDatas + "times--" + logTimes);
        return R.ok(resting);
    }
}
