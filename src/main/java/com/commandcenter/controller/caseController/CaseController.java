package com.commandcenter.controller.caseController;

import com.alibaba.fastjson.JSON;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.DateUtil;
import com.commandcenter.common.utils.HttpRequest;
import com.commandcenter.common.utils.R;
import com.commandcenter.controller.baseController.BaseController;
import com.commandcenter.model.casemodel.CaseForAppModel;
import com.commandcenter.model.casemodel.MpatDispatch;
import com.commandcenter.model.casemodel.VcstCaseInfo;
import com.commandcenter.model.wcf.FeedBackFileModel;
import com.commandcenter.model.wcf.FeedbackModel;
import com.commandcenter.model.wcf.FeedbackModelForWcf;
import com.commandcenter.service.caseservice.MpatDispatchService;
import com.commandcenter.service.caseservice.VcstCaseInfoService;
import com.commandcenter.service.smp.SmpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author r25437
 * @create 2018-09-19 9:22
 * @desc 警情服务
 **/
@RestController
@RequestMapping("/case")
public class CaseController extends BaseController {
    @Autowired
    private VcstCaseInfoService vcstCaseInfoService;

    @Autowired
    private MpatDispatchService mpatDispatchService;
    @Autowired
    private SmpUserService smpUserService;

    @Value("${vcs.http.url}")
    private String vcsHttpUrl;

    @Value("${vcs.http.port}")
    private String vcsHttpPort;

    /**
     * 警情列表查询
       接警单新增状态JQZT006 代表被退单 需要从待接收列表过滤
     */
    @RequestMapping(value = "/caseList", method = RequestMethod.POST)
    public R getCaseList(@RequestBody Map<String, Object> bodyMap) throws IOException {
        //具体查询时拼装map
        Map<String, Object> paraMap = new HashMap<>();
        //入参的para节点map
        Map<String, Object> inputParaMap = (Map<String, Object>) bodyMap.get("para");


        //查询方式，0-我的任务 1-待接收 2-模糊搜索
        String queryType = inputParaMap.getOrDefault("queryType", "0").toString();

        //解析具体业务参数
        for (String key : inputParaMap.keySet()) {
            switch (key) {
                //状态入参，以;分割
                case "statusList":
                    String statusList = inputParaMap.get("statusList").toString();
                    if (!(null == statusList || "".equals(statusList))) {
                        String[] statusArray = statusList.split(";");
                        List<String> status = new ArrayList();
                        for (int i = 0; i < statusArray.length; i++) {
                            status.add(statusArray[i]);
                        }
                        //由于VCS不存在未接收状态的警情，因此当APP查询未接收状态请求时，均按照mpa_t_dispatch表中的status为null进行查询
                        if (status.size() == 1 && status.get(0).trim().equals(Constant.ALARM_STATUS.NO_ACCEPT.getValue())) {
                            paraMap.put("status", "no_accept");
                        } else {
                            paraMap.put("statusArrays", status);
                        }
                    }
                    break;
                case "staffGuid":
                    String staffGuid = inputParaMap.get("staffGuid").toString();
                    if (!"".equals(staffGuid.trim())) {
                        paraMap.put("staffGuid", staffGuid);
                    }
                    break;
                case "keyword":
                    String keyword = inputParaMap.get("keyword").toString();
                    if (!"".equals(keyword.trim())) {
                        paraMap.put("keyword", keyword);
                    }
                    break;

                case "startDate":
                    String startDate = inputParaMap.get("startDate").toString();
                    if (!"".equals(startDate.trim()) && !"-1".equals(startDate.trim())) {
                        paraMap.put("startDate", startDate);
                    }
                    break;
                case "endDate":
                    String endDate = inputParaMap.get("endDate").toString();
                    if (!"".equals(endDate.trim()) && !"-1".equals(endDate.trim())) {
                        paraMap.put("endDate", endDate);
                    }
                    break;
                case "intervalDay":
                    //查询时间间隔，如果为空默认5天
                    Integer intervalDay = new Integer(inputParaMap.getOrDefault("intervalDay", "5").toString());
                    intervalDay = intervalDay - 1 > 0 ? intervalDay - 1 : 0;
                    //由于数据库计算时间差的时候，不包含当天，因此出现误解！服务中主动减去1天
                    //12月3日，计算五天前为11月28日，应该为11月29日
                    //判断依据由casetime改完createtime，需要计算毫秒数
                    //取今天零点的时间
                    long todayBeginTime = DateUtil.firstSecOfDay(new Date()).getTime();
                    long ss = 24 * 60 * 60 * 1000;
                    long findDate = intervalDay * ss;
                    paraMap.put("intervalDays", (todayBeginTime - findDate) + "");
                    break;
                default:
                    break;
            }
        }

        List<CaseForAppModel> caseForAppModelList = new ArrayList<>();
        List<VcstCaseInfo> vcstCaseInfoList = new ArrayList<>();
        Map<String, Object> staffUserMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("staffGuid", inputParaMap.get("staffGuid"));
        staffUserMap = smpUserService.selectUserStaff(map);
        //我的列表，已经处理完的警情在下面
        if ("0".equals(queryType)) {
            setPageDate(inputParaMap, paraMap);
            vcstCaseInfoList = vcstCaseInfoService.selectTaskList(paraMap);
        } else if ("1".equals(queryType)) {
            //待接收，人员对应组织机构的警情
            if (staffUserMap != null) {
                setPageDate(inputParaMap, paraMap);
                vcstCaseInfoList = vcstCaseInfoService.selectNotAcceptList(paraMap);
            }
        } else {
            //模糊搜索
            setPageDate(inputParaMap, paraMap);
            vcstCaseInfoList = vcstCaseInfoService.selectCaseList(paraMap);
        }

        if (vcstCaseInfoList != null && vcstCaseInfoList.size() > 0) {
            VcstCaseInfo last = vcstCaseInfoList.get(vcstCaseInfoList.size() - 1);
            VcstCaseInfo first = vcstCaseInfoList.get(0);

            for (VcstCaseInfo vcstCaseInfo : vcstCaseInfoList) {
                caseForAppModelList.add(vcstCaseInfo.parseToAppModel());
            }
        }

        return R.ok().trans(getPageData(caseForAppModelList));
    }

    /**
     * 警情详情查询
     */
    @RequestMapping(value = "/caseDetail", method = RequestMethod.POST)
    public R getCaseDetail(@RequestBody Map<String, Object> bodyMap) throws IOException {
        //入参的para节点map
        Map<String, Object> inputParaMap = (Map<String, Object>) bodyMap.get("para");

        VcstCaseInfo vcstCaseInfo = vcstCaseInfoService.selectCaseByAlarmIdAndStaffGuid(inputParaMap);
        if (null == vcstCaseInfo) {
            //数据库中无该条警情，则返回错误
            logger.info("查询警情详情失败，警情(\"+alarmId+\")不存在");

            return R.error(201, "该警情不存在");
        }

        List<MpatDispatch> mpatDispatchList = mpatDispatchService.selectMpatDispatchListByCaseId(inputParaMap);
        vcstCaseInfo.setDispatchModels(mpatDispatchList);

        CaseForAppModel caseForAppModel = vcstCaseInfo.parseToAppModel();

        //如果个人警情状态为空，将其设置为未调派
        if (null == caseForAppModel.getFeedbackStatus() || "".equals(caseForAppModel.getFeedbackStatus())) {
            vcstCaseInfo.setFeedbackStatus(Constant.ALARM_STATUS.NO_DISPATCH.getValue());
        }

        logger.info("getCaseDetail方法查询警情详情成功---" + caseForAppModel.toString());

        return R.ok().put("info", caseForAppModel);
    }

    /**
     * 警情反馈，时间轴的反馈查询，上传语音
     */
    @RequestMapping(value = "/feedBack", method = RequestMethod.POST)
    public R feedBack(@RequestBody Map<String, Object> bodyMap) throws Exception {
        //获取app传过来的反馈参数
        Map<String, Object> para = (Map<String, Object>) bodyMap.get("para");

        List<Map<String, Object>> alarmAttachfiles = new ArrayList<>();
        alarmAttachfiles = (List<Map<String, Object>>) para.get("alarmAttachfile");
        String staffGuid = para.get("staffGuid").toString();

        FeedbackModel feedbackModel = new FeedbackModel();

        feedbackModel.setId(para.get("feedbackGuid").toString());
        feedbackModel.setCaseDispatchStatus(para.get("feedbackStatus").toString());
        feedbackModel.setFeedbacker(para.get("feedbackUserStaffGuid").toString());
        feedbackModel.setStaffGuid(staffGuid);
        feedbackModel.setFbContent(para.get("feedbackContent").toString());
        //获得的是一个时间戳
        String time = para.get("feedbackTime").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date(Long.valueOf(time)));
        //将时间戳转化为Date类型
        feedbackModel.setFbTime(sdf.parse(date));
        if (para.containsKey("alarmLongitude") && !"".equals(para.get("alarmLongitude").toString().trim())) {
            feedbackModel.setLongitude(new Double(para.get("alarmLongitude").toString()));
        }
        if (para.containsKey("alarmLatitude") && !"".equals(para.get("alarmLatitude").toString().trim())) {
            feedbackModel.setLatitude(new Double(para.get("alarmLatitude").toString()));
        }
        feedbackModel.setCaseId(para.get("alarmId").toString());

        String alarmCaseTypeKey = para.get("alarmCaseTypeKey").toString();
        if (!(null == alarmCaseTypeKey || "".equals(alarmCaseTypeKey))) {
            String[] types = alarmCaseTypeKey.split(";");
            if (types.length > 0) {
                feedbackModel.setCaseType(types[0]);
            }
            if (types.length > 1) {
                feedbackModel.setCaseSubType(types[1]);
            }
            if (types.length > 2) {
                feedbackModel.setCaseThreeType(types[2]);
            }

        }

        feedbackModel.setCaseLevel(para.get("alarmLevel").toString());
//        feedbackModel.setApplyApprovalFeedBack(para.get("applyApprovalFeedBack").toString());

        List<FeedBackFileModel> listFeedBack = new ArrayList<>();

        if (alarmAttachfiles != null) {
            for (Map<String, Object> alarm : alarmAttachfiles) {
                FeedBackFileModel feedBackFileModel = new FeedBackFileModel();
                feedBackFileModel.setId(alarm.get("id").toString());
                feedBackFileModel.setFileUrl(alarm.get("url").toString());
                feedBackFileModel.setFileType(alarm.get("type").toString());
                feedBackFileModel.setOriginalId(para.get("feedbackGuid").toString());
                feedBackFileModel.setFileName(alarm.get("name").toString());
                feedBackFileModel.setFileEncode(alarm.get("encode").toString());
                feedBackFileModel.setFileSuffix(alarm.get("suffix").toString());
                feedBackFileModel.setFileSize(alarm.get("size").toString());
                feedBackFileModel.setCaseId(para.get("alarmId").toString());
                feedBackFileModel.setFileGuid(alarm.get("id").toString());
                //泉州新加录音时长
                feedBackFileModel.setAudioSize(Long.parseLong(alarm.get("audioSize").toString()));

                //反馈附件时间
                String t = alarm.get("time").toString();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = format.format(new Date(Long.valueOf(t)));
                Date fileTime = format.parse(d);
                feedBackFileModel.setFileTime(fileTime);

                listFeedBack.add(feedBackFileModel);
            }
        }
        feedbackModel.setFeedBackFileModels(listFeedBack);
        // 获取当前警情信息，如果警情状态为处置完成，不响应客户端发起的接收警情和处置完成的反馈请求
        /*
        VcstCaseInfo vcstCaseInfo = vcstCaseInfoService.selectVcstCaseInfoById(para.get("alarmId").toString());
        if(vcstCaseInfo!=null){
            //如果警情状态是已完成 JQZT005
            if(Constant.ALARM_STATUS.FINISHED.getValue().equals(vcstCaseInfo.getStatus())){
                return R.error(601,"警情状态已变更，请更新后重新操作！").put("caseStatus",Constant.ALARM_STATUS.FINISHED.getValue());
            }
        }
        */
        //将反馈发送到mq,给后台可视化
        logger.info("接口调用测试-----------");
        FeedbackModelForWcf feedbackModelForWcf = feedbackModel.parseToVcsModel();
        Map<String, String> queryMap = new HashMap<>(2);
        queryMap.put("feedback", JSON.toJSONString(feedbackModelForWcf));
        String vcsResponseJson = HttpRequest.sendGet(vcsHttpUrl + ":" + vcsHttpPort + Constant.VCS_SAVE_FEED_BACK, queryMap);

        logger.info("send_feed_back return json is " + vcsResponseJson + ", input is " + JSON.toJSONString(feedbackModelForWcf));
        return R.ok();
    }

    /**
     * 警情接警
     */
    @RequestMapping(value = "/acceptCase", method = RequestMethod.POST)
    public R acceptCase(@RequestBody Map<String, Object> bodyMap) throws IOException {
        return R.ok();
    }


    /**
     * @author z26638
     * @create 2020-07-09 17:21
     * @desc 领导端警情查询
     **/
    @RequestMapping(value = "/getCaseListForLeader", method = RequestMethod.POST)
    public R getCaseListForLeader(@RequestBody Map<String, Object> bodyMap) throws IOException {

        Map<String, Object> paraMap = new HashMap<>();
        //入参的para节点map
        Map<String, Object> inputParaMap = (Map<String, Object>) bodyMap.get("para");
        //转换为返回APP的数据格式
        List<CaseForAppModel> caseForAppModelList = new ArrayList<>();
        try {
            //解析具体业务参数
            for (String key : inputParaMap.keySet()) {
                switch (key) {
                    case "keyword":
                        String keyword = inputParaMap.get("keyword").toString();
                        if (!"".equals(keyword.trim())) {
                            paraMap.put("keyword", keyword);
                        }
                        break;
                    case "maxId":
                        String maxId = inputParaMap.get("maxId").toString();
                        if (!"".equals(maxId.trim()) && !"-1".equals(maxId.trim())) {
                            paraMap.put("maxId", maxId);
                        }
                        break;
                    case "minId":
                        String minId = inputParaMap.get("minId").toString();
                        if (!"".equals(minId.trim()) && !"-1".equals(minId.trim())) {
                            paraMap.put("minId", minId);
                        }
                        break;
                    case "startDate":
                        String startDate = inputParaMap.get("startDate").toString();
                        if (!"".equals(startDate.trim()) && !"-1".equals(startDate.trim())) {
                            paraMap.put("startDate", startDate);
                        }
                        break;
                    case "endDate":
                        String endDate = inputParaMap.get("endDate").toString();
                        if (!"".equals(endDate.trim()) && !"-1".equals(endDate.trim())) {
                            paraMap.put("endDate", endDate);
                        }
                        break;
                    case "caseLevel":
                        String caseLevel = inputParaMap.get("caseLevel").toString();
                        if (!"".equals(caseLevel.trim()) && !"-1".equals(caseLevel.trim())) {
                            paraMap.put("caseLevel", caseLevel);
                        }
                        break;
                    case "intervalDay":
                        //查询时间间隔，如果为空默认5天
                        Integer intervalDay = new Integer(inputParaMap.getOrDefault("intervalDay", "5").toString());
                        intervalDay = intervalDay - 1 > 0 ? intervalDay - 1 : 0;
                        //由于数据库计算时间差的时候，不包含当天，因此出现误解！服务中主动减去1天
                        //12月3日，计算五天前为11月28日，应该为11月29日
                        //判断依据由casetime改完createtime，需要计算毫秒数
                        //取今天零点的时间
                        long todayBeginTime = DateUtil.firstSecOfDay(new Date()).getTime();
                        long ss = 24 * 60 * 60 * 1000;
                        long findDate = intervalDay * ss;
                        paraMap.put("intervalDays", (todayBeginTime - findDate) + "");
                        break;
                    //领导端入参belongOrg，查看调派或者接警辖区的所有警情，可能多个
                    case "belongOrg":
                        String belongOrg = inputParaMap.get("belongOrg").toString();
                        if (!(null == belongOrg || "".equals(belongOrg))) {
                            List<String> belongOrgList = Arrays.asList(belongOrg.split(";"));
                            paraMap.put("belongOrg", belongOrgList);
                        }
                        break;
                    default:
                        break;
                }
            }
            setPageDate(inputParaMap, paraMap);
            List<VcstCaseInfo> vcstCaseInfoList = vcstCaseInfoService.selectCaseListForLeader(paraMap);
            if (vcstCaseInfoList != null && !vcstCaseInfoList.isEmpty()) {
                for (VcstCaseInfo vcstCaseInfo : vcstCaseInfoList) {
                    caseForAppModelList.add(vcstCaseInfo.parseToAppModel());
                }
            }
            return R.ok().trans(getPageData(caseForAppModelList));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return R.error(500, "领导端警情查询失败");
        }
    }

    /**
     * 警情二次定位
     */
    @RequestMapping(value = "/savePosition", method = RequestMethod.POST)
    public R savePosition(@RequestBody Map<String, Object> bodyMap) throws Exception {
        //获取app传过来的反馈参数
        Map<String, Object> para = (Map<String, Object>) bodyMap.get("para");

        String savePositionJson = JSON.toJSONString(para);

        logger.info("savePosition接口调用测试-----------");
        String vcsResponseJson = HttpRequest.sendPost(vcsHttpUrl + ":" + vcsHttpPort + Constant.VCS_SAVE_POSITION, savePositionJson);

        logger.info("savePosition return json is " + vcsResponseJson + ", input is " + savePositionJson);
        return R.ok();
    }


    /**
     * 警情类型统计
     */
    @RequestMapping(value = "/getCaseTypeStatistic", method = RequestMethod.POST)
    public R getCaseTypeStatistic(@RequestBody Map<String, Object> bodyMap) throws Exception {
        //获取app传过来的反馈参数
        Map returnMap = new HashMap<String, Object>();
        try {
            Map<String, Object> para = (Map<String, Object>) bodyMap.get("para");
            para.put("startDateStr", DateUtil.getTodayStartTime());
            para.put("endDateStr", DateUtil.getTodayEndTime());
            int todayCount = vcstCaseInfoService.getCaseCountByMap(para);
            int monthCount = vcstCaseInfoService.getMonthCaseCountByMap(para);
            List<Map<String, Object>> caseTypeStatistic = vcstCaseInfoService.getCaseTypeStatistic(para);
            returnMap.put("todayCount", todayCount);
            returnMap.put("monthCount", monthCount);
            returnMap.put("data", caseTypeStatistic);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询收藏列表失败:" + e.getMessage());
            return R.error(500, "");
        }

        return R.ok(returnMap);
    }





}
