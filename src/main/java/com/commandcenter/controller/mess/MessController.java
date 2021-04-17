package com.commandcenter.controller.mess;

import com.alibaba.fastjson.JSON;
import com.commandcenter.common.activemq.component.PublishToMess;
import com.commandcenter.common.utils.HttpRequest;
import com.commandcenter.common.utils.MyUtil;
import com.commandcenter.common.utils.R;
import com.commandcenter.controller.baseController.BaseController;
import com.commandcenter.model.mess.ActivityTask;
import com.commandcenter.model.mess.MessUserAuthority;
import com.commandcenter.model.mess.TaskBeginModel;
import com.commandcenter.model.mess.TaskForApp;
import com.commandcenter.model.smp.SmpRoleFunctionInfo;
import com.commandcenter.service.smp.SmpRoleFunctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author q32756
 * @create 2019-09-04 9:22
 * @desc mess任务
 **/
@RestController
@RequestMapping("/mess")
public class MessController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(MessController.class);
    @Value("${mess.server.ip}")
    private String messIp;
    @Value("${mess.server.port}")
    private String messPort;
    @Value("${mess.server.login}")
    private String messLogin;
    @Value("${mess.server.getTaskList}")
    private String getTaskList;
    @Value("${mess.server.getTaskInfo}")
    private String getTaskInfo;
    @Autowired
    private PublishToMess publishToMess;
    @Autowired
    private SmpRoleFunctionService smpRoleFunctionService;

    /**
     * 接收客户端的mess鉴权，服务端收到mess的时间后做处理，继续向mess请求任务列表
     */
    @RequestMapping(value = "/getTaskList", method = RequestMethod.POST)
    public R getAuthority(@RequestBody Map<String, Object> bodyMap) {
        //具体查询时拼装map
        Map<String, Object> returnMap = new HashMap<>();
        //入参的para节点map
        Map<String, Object> inputParaMap = (Map<String, Object>) bodyMap.get("para");
        String userGuid = inputParaMap.get("userGuid").toString();
        String userCode = inputParaMap.get("userCode").toString();
        Map<String, String> messMap = new HashMap<>(3);
        Map<String, String> taskMap = new HashMap<>(3);
        messMap.put("id", userGuid);
        taskMap.put("uid", userGuid);
        try {
            String messResponseJson = HttpRequest.sendGet("http://" + messIp + ":" + messPort + messLogin, messMap);
            logger.info("messLogin result is ======" + messResponseJson);
            if (messResponseJson != null && !"".equals(messResponseJson)) {
                //MESS返回json串包含请求方法名，所以将方法名截下
                String getAccountByIdResult = JSON.parseObject(messResponseJson, Map.class).get("GetAccountByIdResult").toString();
                MessUserAuthority messUserAuthority = JSON.parseObject(getAccountByIdResult, MessUserAuthority.class);
                if (messUserAuthority.isSuccess() && messUserAuthority.getResult() != null) {
                    Map messUserAuthorityResult = messUserAuthority.getResult().get(0);
                    if ("1".equals(messUserAuthorityResult.get("ENABLE_FLAG"))) {
                        String closingDateStr = messUserAuthorityResult.get("ClosingDate").toString();
                        String currentServerTimeStr = messUserAuthorityResult.get("CurrentServerTime").toString();
                        String effectiveDateStr = messUserAuthorityResult.get("EffectiveDate").toString();
                        //获取账号有效截止时间
                        Date closingDate = MyUtil.JsonParseDate(closingDateStr);
                        //获取当前服务器时
                        Date currentServerDate = MyUtil.JsonParseDate(currentServerTimeStr);
                        //获取账号有效开始时间
                        Date effectiveDate = MyUtil.JsonParseDate(effectiveDateStr);
                        //若账号有时间权限，则可继续请求任务列表
                        if ((effectiveDate.compareTo(currentServerDate) == -1 || effectiveDate.compareTo(currentServerDate) == 0) && closingDate.compareTo(currentServerDate) == 1 || closingDate.compareTo(currentServerDate) == 0) {
                            boolean authorityflag = false;
                            List<SmpRoleFunctionInfo> smpRoleFunctionInfos = smpRoleFunctionService.selectFunctionByuserCode(userCode);
                            for (SmpRoleFunctionInfo smpRoleRunctionInfo : smpRoleFunctionInfos) {
                                if ("MPA_MESS".equals(smpRoleRunctionInfo.getCode())) {
                                    authorityflag = true;
                                }
                            }
                            if (!authorityflag) {
                                return R.error(108, "暂无安保任务");
                            } else {
                                String messTaskResponseJson = HttpRequest.sendGet("http://" + messIp + ":" + messPort + getTaskList, taskMap);
                                logger.info("messTaskResponseJson result is ======" + messTaskResponseJson);
                                if (messTaskResponseJson != null && !"".equals(messTaskResponseJson)) {
                                    String getTasksUserCanSeeResult = JSON.parseObject(messTaskResponseJson, Map.class).get("GetTasksMPACanSeeResult").toString();
                                    TaskForApp taskForAppResult = JSON.parseObject(getTasksUserCanSeeResult, TaskForApp.class);
                                    List<ActivityTask> activityTaskList = taskForAppResult.getResult();
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    //为客户端将date转换成yyyy-MM-dd HH:mm:ss
                                    for (ActivityTask activityTask : activityTaskList) {
                                        Date taskCreateDate = MyUtil.JsonParseDate(activityTask.getCreateTime());
                                        Date taskEndDate = MyUtil.JsonParseDate(activityTask.getEndTime());
                                        Date taskStartDate = MyUtil.JsonParseDate(activityTask.getStartTime());
                                        if (taskCreateDate != null) {
                                            activityTask.setCreateTime(formatter.format(taskCreateDate));
                                        } else {
                                            activityTask.setCreateTime("");
                                        }
                                        if (taskEndDate != null) {
                                            activityTask.setEndTime(formatter.format(taskEndDate));
                                        } else {
                                            activityTask.setEndTime("");
                                        }
                                        if (taskStartDate != null) {
                                            activityTask.setStartTime(formatter.format(taskStartDate));
                                        } else {
                                            activityTask.setStartTime("");
                                        }
                                    }
                                    returnMap.put("activityTaskList", taskForAppResult.getResult());
                                }
                            }
                        }

                    }
                } else {
                    return R.error(108, "暂无安保任务");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("mess数据处理异常 ======"+e.getMessage());
            return R.error(108, "暂无安保任务");
        }

        return R.ok(returnMap);
    }

    /**
     * 向MESS发送任务开始状态
     *
     * @param bodyMap
     * @return
     */
    @RequestMapping(value = "/taskBegin", method = RequestMethod.POST)
    public R sendMessTaskBegin(@RequestBody Map<String, Object> bodyMap) {
        try {
            Map<String, Object> paramMap = (Map<String, Object>) bodyMap.get("para");
            TaskBeginModel taskBeginModel = new TaskBeginModel();
            taskBeginModel.setActivityGuid(paramMap.get("activeGuid").toString());
            taskBeginModel.setIsBegin(paramMap.get("isBegin").toString());
            taskBeginModel.setStaffGuid(paramMap.get("staffGuid").toString());
            taskBeginModel.setTaskGuid(paramMap.get("taskGuid").toString());
            publishToMess.taskBeginToMess(taskBeginModel);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(200, "服务异常");
        }
    }

    /**
     * 请求安保任务详情
     *
     * @param bodyMap
     * @return
     */
    @RequestMapping(value = "/taskInfo", method = RequestMethod.POST)
    public R getTaskInfo(@RequestBody Map<String, Object> bodyMap) {
        try {
            Map<String, Object> paramMap = (Map<String, Object>) bodyMap.get("para");
            Map<String, Object> resultMap = new HashMap<>();
            String taskId = paramMap.get("id").toString();
            Map<String, String> taskMap = new HashMap<>();
            taskMap.put("taskid", taskId);
            String messTaskInfoResponseJson = HttpRequest.sendGet("http://" + messIp + ":" + messPort + getTaskInfo, taskMap);
            if (messTaskInfoResponseJson != null && !"".equals(messTaskInfoResponseJson)) {
                String getTaskAllChildrenResult = JSON.parseObject(messTaskInfoResponseJson, Map.class).get("GetTaskAllChildrenResult").toString();
                MessUserAuthority messUserAuthority = JSON.parseObject(getTaskAllChildrenResult, MessUserAuthority.class);
                List<Map> resultTaskInfo = messUserAuthority.getResult();
                resultMap.put("taskInfo", resultTaskInfo);
            }
            return R.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(200, "服务异常");
        }
    }

}


