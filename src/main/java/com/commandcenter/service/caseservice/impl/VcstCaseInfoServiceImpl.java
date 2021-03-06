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
        //??????association??????????????????????????????????????????????????????????????????????????????
        //???????????????????????????????????????
        VcstCaseInfo vcstCaseInfo = vcstCaseInfoDao.selectVcstCaseInfoById(id);

        if(vcstCaseInfo!=null) {
            vcstCaseInfo = getFeedBackDetail(vcstCaseInfo);
        }

        return vcstCaseInfo;
    }
    private VcstCaseInfo getFeedBackDetail(VcstCaseInfo vcstCaseInfo){
        //?????????????????????????????????????????????????????????
        if(vcstCaseInfo.getAlarmFbcontent() != null && vcstCaseInfo.getAlarmFbcontent().size()>0) {
            //????????????????????????
            List<StaffForAppModel> staffForAppModelList = smpStaffInfoMapper.selectFeedBackStaffForAppModelByCaseId(vcstCaseInfo.getId());
            //????????????????????????
            List<FeedBackFileModel> feedBackFileModelList = mpatFeedbackFileDao.selectFileListByCaseId(vcstCaseInfo.getId());

            //??????????????????????????????????????????????????????????????????????????????
            List<FeedbackModel> feedbackModelList = new ArrayList<>(vcstCaseInfo.getAlarmFbcontent().size());
            for(FeedbackModel feedbackModel : vcstCaseInfo.getAlarmFbcontent()){
                //????????????????????????
                String feedbacker = feedbackModel.getFeedbacker();
                if(feedbacker !=null && !"".equals(feedbacker.trim())){
                    Optional<StaffForAppModel> optional = staffForAppModelList.stream().filter(staff -> staff.getStaffGuid().trim().equals(feedbacker)).findFirst();
                    if(optional.isPresent()) {
                        feedbackModel.setUser(optional.get());
                    }
                }
                //????????????????????????
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

        //????????????????????????????????????
        if(vcstCaseInfo.getDispatchModels()!=null){
            List<MpatDispatch> list = vcstCaseInfo.getDispatchModels();
            for (MpatDispatch mpatDispatch : list) {
                //????????????????????????????????????????????????????????????
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
        //??????????????????
        VcstCaseInfo vcstCaseInfo = selectVcstCaseInfoById(id);

        //??????????????????
        if(vcstCaseInfo!=null){
            List<MpatDispatch> mpatDispatchList = mpatDispatchDao.selectMpatDispatchByCaseId(id);
            if(mpatDispatchList!=null && mpatDispatchList.size()>0){
                vcstCaseInfo.setDispatchModels(mpatDispatchList);
            }
        }
        //??????????????????
        List<MpatDispatch> dispatchList = vcstCaseInfo.getDispatchModels();
        //????????????????????????
        if(dispatchList!=null){
            //??????????????????????????????????????????????????????userNos????????????????????????????????????
            String orgGuid = "";
            for(MpatDispatch mpatDispatch:dispatchList) {
                if(mpatDispatch.getOrgGuid()!=null && !"".equals(mpatDispatch.getOrgGuid())){
                    orgGuid = mpatDispatch.getOrgGuid();
                }
            }
            List<String> userNos = new ArrayList<>();
            //????????????????????????????????????????????????????????????????????????????????????
            if(Constant.DISPATCH_TYPE.ORG.getValue().equals(sendType)){
                List<SmpUserInfo> smpUserInfoList = smpUserInfoMapper.selectUserByOrgGuid(orgGuid);
                for(SmpUserInfo smpUserInfo:smpUserInfoList){
                    if (Constant.tokenMapIns.get(smpUserInfo.getUserCode()) != null) {
                        String tempNo = (String) Constant.tokenMapIns.get(smpUserInfo.getUserCode());
                        userNos.add(tempNo);
                    }
                }
            }
            //??????????????????????????????????????????????????????????????????
            else{
                for(MpatDispatch mpatDispatch:dispatchList) {
                    //?????????????????????????????????????????????
                    //???????????????????????????
                    boolean isStaffDispatch = Constant.DISPATCH_TYPE.STAFF.getValue().equals(mpatDispatch.getType());
                    //??????????????????????????????
                    boolean staffIsNotCancel = !(mpatDispatch.getIscancel() != null && "1".equals(mpatDispatch.getIscancel()));
                    if (isStaffDispatch && staffIsNotCancel) {
                        UserModel userModel = smpUserInfoMapper.selectUserByStaffGuid(mpatDispatch.getStaffGuid());
                        if(userModel == null ){
                            logger.info("??????????????????GUID?????????????????????????????????????????????GUID=="+mpatDispatch.getStaffGuid());
                        }
                        if (userModel != null && Constant.tokenMapIns.get(userModel.getUserCode()) != null) {
                            String tempNo = (String) Constant.tokenMapIns.get(userModel.getUserCode());
                            userNos.add(tempNo);
                        }
                    }
                    //?????????????????????????????????????????????
                    vcstCaseInfo.setFeedbackStatus(Constant.ALARM_STATUS.ACCEPTED.getValue());
                }
            }
            //??????????????????????????????????????????
            //???????????????APP
            sendCaseInfoToApp(vcstCaseInfo,userNos,queueName);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void changeDispatchInfo(Map<String,Object> map, String queueName){
        Date now = new Date();
        //??????id
        String caseId = map.get("id").toString();
        //??????????????????
        VcstCaseInfo vcstCaseInfo = selectVcstCaseInfoById(caseId);
        //??????????????????
        List<Map<String,Object>> listMap = (List<Map<String,Object>>)map.get("staffs");
        List<MpatDispatch> mpatDispatchList = new ArrayList<>();
        //?????????????????????????????????
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

                //??????????????????
                int updateFlag = mpatDispatchDao.updateNonEmptyMpatDispatchByCaseAndStaff(mpatDispatch);
                //???????????????????????????
                if(updateFlag<1){
                    mpatDispatchDao.insertNonEmptyMpatDispatch(mpatDispatch);
                }
            }
            vcstCaseInfo.setDispatchModels(mpatDispatchList);

            //??????????????????????????????
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
                //???????????????APP
                sendCaseInfoToApp(vcstCaseInfo,userNos, queueName);
            }


        }
    }

    /**
     *
     * @param vcstCaseInfo
     * @param userNos
     * ?????????????????????userNos????????????
     */
    private void sendCaseInfoToApp(VcstCaseInfo vcstCaseInfo,List<String> userNos, String queueName){
        //??????????????????????????????????????????
        if(userNos.size()>0) {
            //????????????APP????????????
            CaseForAppModel caseForAppModel = vcstCaseInfo.parseToAppModel();

            //??????APP????????????????????????????????????????????????????????????
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
            //??????MQ
            logger.info("?????????????????????["+notifyCaseMQ+"]????????????,param:" + JSON.toJSONString(param));
            logger.info("userNos=" + userNos);
            //??????????????????
            publishToAPP.sendToApp(param);
        }else {
            //??????MQ
            logger.info("??????????????????????????????????????????");
        }
    }

    /**
     * ????????????VcstCaseInfo??????,?????????VcstCaseInfo???????????????????????????????????????????????????
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
     * ???????????????????????????????????????
     * @param map
     * @return
     */
    @Override
    public List<VcstCaseInfo> selectNotAcceptList(Map<String,Object> map){
        return vcstCaseInfoDao.selectNotAcceptList(map);
    }
    /**
     * ????????????????????????????????????
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
     * ????????????id??????????????????
     * @param id
     * @return
     */
    @Override
    public int selectVcstCaseInfoCountById(String id){
        return vcstCaseInfoDao.selectVcstCaseInfoCountById(id);
    }

    /**
     * ??????mpacaseopr???
     * @param mpaCaseOprInfo
     * @return
     */
    @Override
    public int insertMpaCaseOpr(MpaCaseOprInfo mpaCaseOprInfo){
        return mpaCaseOprInfoDao.insertMpaCaseOpr(mpaCaseOprInfo);
    }

    /**
     * ????????????
     * @return
     */
    @Override
    public List<MpaCaseOprInfo> selectMpaCaseOprList(){
        return mpaCaseOprInfoDao.selectMpaCaseOprList();
    }

    /**
     * ????????????
     * @return
     */
    @Override
    public List<MpaCaseOprInfo> selectMpaCaseOprListByMod(Map<String,Object> map){
        return mpaCaseOprInfoDao.selectMpaCaseOprListByMod(map);
    }

    /**
     * ????????????????????????
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

            //?????????????????????????????????????????????-??????-???????????????????????????????????????
            if (reportList != null && !reportList.isEmpty()) {
//                reportList.stream().filter(a -> a.get("status").equals("1")).forEach(b ->offFilerMap.put((String) b.get("staffGuid"),""));
                //??????????????????????????????,0-?????????1-??????
                reportList = reportList.stream().filter(item -> item.get("status").equals("0")).collect(Collectors.toList());
            }


            List<Map<String, Object>> alarmFilterList = alarmList;

            //???????????????????????????map??????????????????
            if (alarmFilterList != null && !alarmFilterList.isEmpty()) {
                alarmFilterList.stream().forEach(a ->alarmFilerMap.put((String) a.get("staffGuid"),""));
            }

            //?????????map?????????????????????list??????????????????????????????????????????
            List<Map<String, Object>> patrolFilterList = handleListFilter(patrolList,alarmFilerMap);
            //?????????map?????????????????????list?????????????????????????????????????????????????????????
            List<Map<String, Object>> reportFilterList = handleListFilter(reportList,alarmFilerMap);

            //????????????app????????????gps????????????????????????
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
                //???????????????2??????????????????smartOne???gps??????
                if (gpsList.get(0).get("gpsType").equals("0")) {
                    returnList.add(gpsList.get(0));
                } else {
                    returnList.add(gpsList.get(1));
                }
                //???????????????????????????smartOne?????????????????????,gpstype=2
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