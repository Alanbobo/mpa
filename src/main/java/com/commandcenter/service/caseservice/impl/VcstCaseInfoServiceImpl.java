package com.commandcenter.service.caseservice.impl;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.activemq.component.PublishToAPP;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.dao.casedao.MpaCaseOprInfoDao;
import com.commandcenter.dao.casedao.MpatDispatchDao;
import com.commandcenter.dao.casedao.MpatFeedbackFileDao;
import com.commandcenter.dao.casedao.VcstCaseInfoDao;
import com.commandcenter.dao.smp.SmpStaffInfoMapper;
import com.commandcenter.dao.smp.SmpUserInfoMapper;
import com.commandcenter.model.casemodel.*;
import com.commandcenter.model.smp.SmpUserInfo;
import com.commandcenter.model.smp.vo.StaffForAppModel;
import com.commandcenter.model.smp.vo.UserModel;
import com.commandcenter.model.wcf.FeedBackFileModel;
import com.commandcenter.model.wcf.FeedbackModel;
import com.commandcenter.service.caseservice.VcstCaseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("vcstCaseInfoService")
public class VcstCaseInfoServiceImpl implements VcstCaseInfoService{

    private static Logger logger = LoggerFactory.getLogger(VcstCaseInfoServiceImpl.class);

    private static Map<String,Object> alarmFilerMap = new HashMap<>();

    private static Map<String,Object> offFilerMap = new HashMap<>();


    @Autowired
    private VcstCaseInfoDao vcstCaseInfoDao;

    @Autowired
    private MpatDispatchDao mpatDispatchDao;

    @Autowired
    private SmpUserInfoMapper smpUserInfoMapper;

    @Autowired
    private SmpStaffInfoMapper smpStaffInfoMapper;

    @Autowired
    private MpatFeedbackFileDao mpatFeedbackFileDao;

    @Autowired
    private MpaCaseOprInfoDao mpaCaseOprInfoDao;

    @Autowired
    private PublishToAPP publishToAPP;

    @Value("${system.notice.app.notifyCaseMQ}")
    private String notifyCaseMQ;

    @Value("${system.notice.app.staffChangeMQ}")
    private String staffChangeMQ;

    @Value("${system.notice.app.caseStatusChangeMQ}")
    private String caseStatusChangeMQ;

    @Value("${system.notice.app.locationChangeMQ}")
    private String locationChangeMQ;

    @Value("${system.notice.app.backAlarmStatusChange}")
    private String backAlarmStatusMQ;


    @Override
    public long getVcstCaseInfoRowCount(){
        return vcstCaseInfoDao.getVcstCaseInfoRowCount();
    }
    @Override
    public List<VcstCaseInfo> selectVcstCaseInfo(){
        return vcstCaseInfoDao.selectVcstCaseInfo();
    }
    @Override
    public VcstCaseInfo selectVcstCaseInfoByObj(VcstCaseInfo obj){
        return vcstCaseInfoDao.selectVcstCaseInfoByObj(obj);
    }
    @Override
    public VcstCaseInfo selectVcstCaseInfoById(String id){
        //由于association存在性能问题，再此通过单独查询数据方式，补全整个警情
        //警情基本信息及警情反馈信息
        VcstCaseInfo vcstCaseInfo = vcstCaseInfoDao.selectVcstCaseInfoById(id);

        if(vcstCaseInfo!=null) {
            vcstCaseInfo = getFeedBackDetail(vcstCaseInfo);
        }

        return vcstCaseInfo;
    }
    private VcstCaseInfo getFeedBackDetail(VcstCaseInfo vcstCaseInfo){
        //如果存在反馈内容才进行补全，否则不查询
        if(vcstCaseInfo.getAlarmFbcontent() != null && vcstCaseInfo.getAlarmFbcontent().size()>0) {
            //查询反馈人员信息
            List<StaffForAppModel> staffForAppModelList = smpStaffInfoMapper.selectFeedBackStaffForAppModelByCaseId(vcstCaseInfo.getId());
            //查询反馈附件信息
            List<FeedBackFileModel> feedBackFileModelList = mpatFeedbackFileDao.selectFileListByCaseId(vcstCaseInfo.getId());

            //循环处理每条反馈，将人员信息及附件信息加入到结果集中
            List<FeedbackModel> feedbackModelList = new ArrayList<>(vcstCaseInfo.getAlarmFbcontent().size());
            for(FeedbackModel feedbackModel : vcstCaseInfo.getAlarmFbcontent()){
                //赋值反馈人员信息
                String feedbacker = feedbackModel.getFeedbacker();
                if(feedbacker !=null && !"".equals(feedbacker.trim())){
                    Optional<StaffForAppModel> optional = staffForAppModelList.stream().filter(staff -> staff.getStaffGuid().trim().equals(feedbacker)).findFirst();
                    if(optional.isPresent()) {
                        feedbackModel.setUser(optional.get());
                    }
                }
                //赋值反馈附件信息
                List<FeedBackFileModel> feedBackFileList = feedBackFileModelList.stream().
                        filter(feedbackfile -> feedbackfile.getFeedBackId().trim().equals(feedbackModel.getId())).
                        collect(Collectors.toList());
                if(feedBackFileList!=null && feedBackFileList.size()>0) {
                    feedbackModel.setFeedBackFileModels(feedBackFileList);
                }
            }
        }
        return vcstCaseInfo;
    }
    @Override
    public int insertVcstCaseInfo(VcstCaseInfo value){
        return vcstCaseInfoDao.insertVcstCaseInfo(value);
    }
    @Override
    public int insertNonEmptyVcstCaseInfo(VcstCaseInfo value){
        return vcstCaseInfoDao.insertNonEmptyVcstCaseInfo(value);
    }
    @Override
    public int deleteVcstCaseInfoById(String id){
        return vcstCaseInfoDao.deleteVcstCaseInfoById(id);
    }
    @Override
    public int updateVcstCaseInfoById(VcstCaseInfo enti){
        return vcstCaseInfoDao.updateVcstCaseInfoById(enti);
    }
    @Override
    public int updateNonEmptyVcstCaseInfoById(VcstCaseInfo enti){
        return vcstCaseInfoDao.updateNonEmptyVcstCaseInfoById(enti);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addCaseAndDispatchs(VcstCaseInfo vcstCaseInfo){
        Date now = new Date();
        if (vcstCaseInfo.getCreatetime() == null) {
            vcstCaseInfo.setCreatetime(now);
        }
        if (vcstCaseInfo.getCreateTimeExtend() == null) {
            vcstCaseInfo.setCreateTimeExtend(String.valueOf(vcstCaseInfo.getCreatetime().getTime()));
        }
        VcstCaseInfo caseInfo = selectVcstCaseInfoById(vcstCaseInfo.getId());
        if(caseInfo!=null){
            vcstCaseInfo.setCreatetime(null);
            updateNonEmptyVcstCaseInfoById(vcstCaseInfo);
        }else{
            insertNonEmptyVcstCaseInfo(vcstCaseInfo);
        }

        //调派信息，修改为逐条判断
        if(vcstCaseInfo.getDispatchModels()!=null){
            List<MpatDispatch> list = vcstCaseInfo.getDispatchModels();
            for (MpatDispatch mpatDispatch : list) {
                //如果调派信息存在则进行修改，否则直接入表
                mpatDispatch.setCreateTime(now);
                List<MpatDispatch> existMpatDispatchList = mpatDispatchDao.selectMpatDispatchIsExist(mpatDispatch);
                if (existMpatDispatchList != null && existMpatDispatchList.size()>0) {
                    existMpatDispatchList.stream().forEach(t -> {
                        mpatDispatch.setId(t.getId());
                        mpatDispatchDao.updateNonEmptyMpatDispatchById(mpatDispatch);
                    });
                } else {
                    mpatDispatchDao.insertNonEmptyMpatDispatch(mpatDispatch);
                }
            }
        }
    }

    @Override
    public List<VcstCaseInfo> selectCaseList(Map<String,Object> map){
        return vcstCaseInfoDao.selectCaseList(map);
    }

    @Override
    public void sendCaseInfoToStaffApp(String id,String sendType, String queueName){
        //获取警情信息
        VcstCaseInfo vcstCaseInfo = selectVcstCaseInfoById(id);

        //获取调派信息
        if(vcstCaseInfo!=null){
            List<MpatDispatch> mpatDispatchList = mpatDispatchDao.selectMpatDispatchByCaseId(id);
            if(mpatDispatchList!=null && mpatDispatchList.size()>0){
                vcstCaseInfo.setDispatchModels(mpatDispatchList);
            }
        }
        //获取调派数据
        List<MpatDispatch> dispatchList = vcstCaseInfo.getDispatchModels();
        //有调派数据，下发
        if(dispatchList!=null){
            //按照组织机构以及按照警员下发，仅仅是userNos列表不同，其余内容均相同
            String orgGuid = "";
            for(MpatDispatch mpatDispatch:dispatchList) {
                if(mpatDispatch.getOrgGuid()!=null && !"".equals(mpatDispatch.getOrgGuid())){
                    orgGuid = mpatDispatch.getOrgGuid();
                }
            }
            List<String> userNos = new ArrayList<>();
            //按照组织机构下发时，按照组织机构查询警员，警员在线则下发
            if(Constant.DISPATCH_TYPE.ORG.getValue().equals(sendType)){
                List<SmpUserInfo> smpUserInfoList = smpUserInfoMapper.selectUserByOrgGuid(orgGuid);
                for(SmpUserInfo smpUserInfo:smpUserInfoList){
                    if (Constant.tokenMapIns.get(smpUserInfo.getUserCode()) != null) {
                        String tempNo = (String) Constant.tokenMapIns.get(smpUserInfo.getUserCode());
                        userNos.add(tempNo);
                    }
                }
            }
            //按照个人下发时，按照调派警员，警员在线则下发
            else{
                for(MpatDispatch mpatDispatch:dispatchList) {
                    //判断调派类型是否与个人调派相符
                    //是不是警员调派类型
                    boolean isStaffDispatch = Constant.DISPATCH_TYPE.STAFF.getValue().equals(mpatDispatch.getType());
                    //警员是否已经取消调派
                    boolean staffIsNotCancel = !(mpatDispatch.getIscancel() != null && "1".equals(mpatDispatch.getIscancel()));
                    if (isStaffDispatch && staffIsNotCancel) {
                        UserModel userModel = smpUserInfoMapper.selectUserByStaffGuid(mpatDispatch.getStaffGuid());
                        if(userModel == null ){
                            logger.info("根据调派警员GUID，查询不到绑定关系的人员，警员GUID=="+mpatDispatch.getStaffGuid());
                        }
                        if (userModel != null && Constant.tokenMapIns.get(userModel.getUserCode()) != null) {
                            String tempNo = (String) Constant.tokenMapIns.get(userModel.getUserCode());
                            userNos.add(tempNo);
                        }
                    }
                    //这里查询出的警员，都是调派警员
                    vcstCaseInfo.setFeedbackStatus(Constant.ALARM_STATUS.ACCEPTED.getValue());
                }
            }
            //获取下发用户列表，存在才下发
            //下发警情给APP
            sendCaseInfoToApp(vcstCaseInfo,userNos,queueName);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void changeDispatchInfo(Map<String,Object> map, String queueName){
        Date now = new Date();
        //警情id
        String caseId = map.get("id").toString();
        //获取警情信息
        VcstCaseInfo vcstCaseInfo = selectVcstCaseInfoById(caseId);
        //调派警员信息
        List<Map<String,Object>> listMap = (List<Map<String,Object>>)map.get("staffs");
        List<MpatDispatch> mpatDispatchList = new ArrayList<>();
        //如果调派信息有效则处理
        if(listMap!=null && listMap.size() > 0){
            for(Map dispatchMap : listMap){
                MpatDispatch mpatDispatch = new MpatDispatch();
                mpatDispatch.setCaseId(caseId);
                mpatDispatch.setStaffGuid(dispatchMap.get("staffid").toString());
                mpatDispatch.setStatus(dispatchMap.get("status").toString());
                if(dispatchMap.get("iscancel") != null) {
                    if ((boolean) dispatchMap.get("iscancel")) {
                        mpatDispatch.setIscancel(Constant.DISPATCH_ISCANCEL);
                    }else{
                        mpatDispatch.setIscancel("0");
                    }
                }
                mpatDispatch.setType(Constant.DISPATCH_TYPE.STAFF.getValue());
                mpatDispatch.setCreateTime(now);
                mpatDispatchList.add(mpatDispatch);

                //修改调派信息
                int updateFlag = mpatDispatchDao.updateNonEmptyMpatDispatchByCaseAndStaff(mpatDispatch);
                //没有修改到，则新增
                if(updateFlag<1){
                    mpatDispatchDao.insertNonEmptyMpatDispatch(mpatDispatch);
                }
            }
            vcstCaseInfo.setDispatchModels(mpatDispatchList);

            //警情推送需要按照警员
            for(MpatDispatch mpatDispatch : mpatDispatchList){
                List<String> userNos = new ArrayList<>();
                if(mpatDispatch.getIscancel() !=null && !mpatDispatch.getIscancel().equals(Constant.DISPATCH_ISCANCEL)){
                    vcstCaseInfo.setFeedbackStatus(mpatDispatch.getStatus());
                }else {
                    vcstCaseInfo.setFeedbackStatus(null);
                }
                UserModel userModel = smpUserInfoMapper.selectUserByStaffGuid(mpatDispatch.getStaffGuid());
                if (userModel!=null && Constant.tokenMapIns.get(userModel.getUserCode()) != null) {
                    String tempNo = (String) Constant.tokenMapIns.get(userModel.getUserCode());
                    userNos.add(tempNo);
                }
                //下发警情给APP
                sendCaseInfoToApp(vcstCaseInfo,userNos, queueName);
            }


        }
    }

    /**
     *
     * @param vcstCaseInfo
     * @param userNos
     * 下发警情信息给userNos列表用户
     */
    private void sendCaseInfoToApp(VcstCaseInfo vcstCaseInfo,List<String> userNos, String queueName){
        //获取下发用户列表，存在才下发
        if(userNos.size()>0) {
            //准备下发APP报文格式
            CaseForAppModel caseForAppModel = vcstCaseInfo.parseToAppModel();

            //针对APP要求，对不同消息队列推送报文进行单独定制
            MQParamModel param;
            if(locationChangeMQ.equals(queueName)){
                Map<String,Object> map = new HashMap<>();
                map.put("alarmid",caseForAppModel.getAlarmId());
                map.put("longitude",caseForAppModel.getAlarmLongitude());
                map.put("latitude",caseForAppModel.getAlarmLatitude());
                map.put("address",caseForAppModel.getAlarmAddress());
                param = new MQParamModel(JSON.toJSONString(map), queueName);
            }
            else if(caseStatusChangeMQ.equals(queueName)){
                Map<String,Object> map = new HashMap<>();
                map.put("alarmid",caseForAppModel.getAlarmId());
                map.put("status",caseForAppModel.getAlarmStatus());
                param = new MQParamModel(JSON.toJSONString(map), queueName);
            }
            else if(staffChangeMQ.equals(queueName)){
                Map<String,Object> map = new HashMap<>();
                map.put("alarmid",caseForAppModel.getAlarmId());
                map.put("dispatchModels",caseForAppModel.getDispatchModels());
                param = new MQParamModel(JSON.toJSONString(map), queueName);
            }
            else if(backAlarmStatusMQ.equals(queueName)){
                Map<String,Object> map = new HashMap<>();
                map.put("alarmid",caseForAppModel.getAlarmId());
                map.put("status",caseForAppModel.getAlarmStatus());
                param = new MQParamModel(JSON.toJSONString(map), queueName);
            }
            else {
                param = new MQParamModel(JSON.toJSONString(caseForAppModel), queueName);
            }

            param.setQueueFlag(false);
            param.setPersonalFlag(true);
            param.setUserNos(userNos);
            //发送MQ
            logger.info("通过文件通道给["+notifyCaseMQ+"]发送消息,param:" + JSON.toJSONString(param));
            logger.info("userNos=" + userNos);
            //发送警情信息
            publishToAPP.sendToApp(param);
        }else {
            //发送MQ
            logger.info("下发警情信息，调派列表为空！");
        }
    }

    /**
     * 获得一个VcstCaseInfo对象,以参数VcstCaseInfo对象中不为空的属性作为条件进行查询
     * @param map
     * @return
     */
    @Override
    public VcstCaseInfo selectCaseByAlarmIdAndStaffGuid(Map<String,Object> map){
        VcstCaseInfo vcstCaseInfo = vcstCaseInfoDao.selectCaseByAlarmIdAndStaffGuid(map);
        vcstCaseInfo = getFeedBackDetail(vcstCaseInfo);
        return vcstCaseInfo;
    }
    /**
     * 查询我的待接收警情列表信息
     * @param map
     * @return
     */
    @Override
    public List<VcstCaseInfo> selectNotAcceptList(Map<String,Object> map){
        return vcstCaseInfoDao.selectNotAcceptList(map);
    }
    /**
     * 查询我的任务警情列表信息
     * @param map
     * @return
     */
    @Override
    public List<VcstCaseInfo> selectTaskList(Map<String,Object> map){
        return vcstCaseInfoDao.selectTaskList(map);
    }
    public VcstCaseInfoDao getVcstCaseInfoDao() {
        return this.vcstCaseInfoDao;
    }

    public void setVcstCaseInfoDao(VcstCaseInfoDao vcstCaseInfoDao) {
        this.vcstCaseInfoDao = vcstCaseInfoDao;
    }

    /**
     * 根据警情id查询警情信息
     * @param id
     * @return
     */
    @Override
    public int selectVcstCaseInfoCountById(String id){
        return vcstCaseInfoDao.selectVcstCaseInfoCountById(id);
    }

    /**
     * 插入mpacaseopr表
     * @param mpaCaseOprInfo
     * @return
     */
    @Override
    public int insertMpaCaseOpr(MpaCaseOprInfo mpaCaseOprInfo){
        return mpaCaseOprInfoDao.insertMpaCaseOpr(mpaCaseOprInfo);
    }

    /**
     * 查询列表
     * @return
     */
    @Override
    public List<MpaCaseOprInfo> selectMpaCaseOprList(){
        return mpaCaseOprInfoDao.selectMpaCaseOprList();
    }

    /**
     * 查询列表
     * @return
     */
    @Override
    public List<MpaCaseOprInfo> selectMpaCaseOprListByMod(Map<String,Object> map){
        return mpaCaseOprInfoDao.selectMpaCaseOprListByMod(map);
    }

    /**
     * 批量删除警情通知
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteMpaCaseOprInfoByIds(List<Long> ids){
        return mpaCaseOprInfoDao.deleteMpaCaseOprInfoByIds(ids);
    }

    @Override
    public List<VcstCaseInfo> selectCaseListForLeader(Map<String,Object> map){
        return vcstCaseInfoDao.selectCaseListForLeader(map);
    }

    @Override
    public List<Map<String, Object>> getCaseTypeStatistic(Map<String, Object> map) {
        return vcstCaseInfoDao.getCaseTypeStatistic(map);
    }

    @Override
    public int getCaseCountByMap(Map<String, Object> map) {
        return vcstCaseInfoDao.getCaseCountByMap(map);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> getStaffStateByOrg(String stateReponseJson,Map<String, Object> map) throws Exception{


        Map<String,Object> returnMap = new HashMap<>();
        List<Map<String, Object>> alarmGpsList = new ArrayList<>();
        List<Map<String, Object>> patrolGpsList = new ArrayList<>();
        List<Map<String, Object>> reportGpsList = new ArrayList<>();
        try {
            Map<String,Object> stateReponseMap = JSON.parseObject(stateReponseJson, Map.class);
            List<Map<String, Object>> patrolList = (List<Map<String, Object>>) stateReponseMap.get("patrolGpsList");
            List<Map<String, Object>> reportList = (List<Map<String, Object>>) stateReponseMap.get("postGpsList");
            List<Map<String, Object>> alarmList = vcstCaseInfoDao.selectStaffStateByOrg(map);

            //下班警员优先级最高，其次是处警-巡逻-报备，过滤查出来的处警人员
            if (reportList != null && !reportList.isEmpty()) {
//                reportList.stream().filter(a -> a.get("status").equals("1")).forEach(b ->offFilerMap.put((String) b.get("staffGuid"),""));
                //只有上班的人算做报备,0-上班，1-下班
                reportList = reportList.stream().filter(item -> item.get("status").equals("0")).collect(Collectors.toList());
            }


            List<Map<String, Object>> alarmFilterList = alarmList;

            //筛选出来的处警加如map，作为过滤组
            if (alarmFilterList != null && !alarmFilterList.isEmpty()) {
                alarmFilterList.stream().forEach(a ->alarmFilerMap.put((String) a.get("staffGuid"),""));
            }

            //用处警map过滤勤务的巡逻list，勤务之前已经筛选了下班人员
            List<Map<String, Object>> patrolFilterList = handleListFilter(patrolList,alarmFilerMap);
            //用处警map过滤勤务的报备list，勤务之前已经筛选了巡逻人员和下班人员
            List<Map<String, Object>> reportFilterList = handleListFilter(reportList,alarmFilerMap);

            //为了查询app所要求的gps，组织机构等字段
            if (alarmFilterList != null && !alarmFilterList.isEmpty()) {
                alarmGpsList = getGpsList(alarmFilterList);
            }
            if (patrolFilterList != null && !patrolFilterList.isEmpty()) {
                patrolGpsList = getGpsList(patrolFilterList);
            }
            if (reportFilterList != null && !reportFilterList.isEmpty()) {
                reportGpsList = getGpsList(reportFilterList);
            }

            int orgCount = smpStaffInfoMapper.selectStaffCountByOrg(map);
            returnMap.put("orgCount",orgCount);
            returnMap.put("alarmData",alarmGpsList);
            returnMap.put("alarmCount",alarmGpsList.size());
            returnMap.put("patrolData",patrolGpsList);
            returnMap.put("patrolCount",patrolGpsList.size());
            returnMap.put("reportData",reportGpsList);
            returnMap.put("reportCount",reportGpsList.size());

        } finally {
            alarmFilerMap.clear();
            offFilerMap.clear();
        }


        return returnMap;
    }

    @Override
    public int getMonthCaseCountByMap(Map<String, Object> map) {
        return vcstCaseInfoDao.getMonthCaseCountByMap(map);
    }


    private List<Map<String,Object>> getGpsList(List<Map<String, Object>> list){

        List<Map<String, Object>> returnList = new ArrayList<>();
        for (Map<String,Object> map:list) {
            List<Map<String, Object>> gpsList = vcstCaseInfoDao.selectMapInfoByStaffGuid(map);
            if (gpsList.size()==1){
                returnList.add(gpsList.get(0));
            }else if(gpsList.size()==2){
                //一个人绑定2个设备时，走smartOne的gps信息
                if (gpsList.get(0).get("gpsType").equals("0")) {
                    returnList.add(gpsList.get(0));
                } else {
                    returnList.add(gpsList.get(1));
                }
                //警员没绑定设备或者smartOne就返回警员信息,gpstype=2
            }else{
                Map<String, Object> staffMap = vcstCaseInfoDao.selectNonGpsInfoByMap(map);
                if (staffMap != null) {
                    returnList.add(staffMap);
                }
            }
        }

        return returnList;
    }



    private List<Map<String,Object>> handleListFilter(List<Map<String, Object>> list,Map<String,Object> map){

        if (list == null || list.isEmpty() || map == null || map.isEmpty()) {
            return list;
        }
        Iterator<Map<String, Object>> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = (String) iterator.next().get("staffGuid");
            if (map.containsKey(s)) {
                iterator.remove();
            }
        }

        return list;
    }

}