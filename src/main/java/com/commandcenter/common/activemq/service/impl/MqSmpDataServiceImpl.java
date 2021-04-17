package com.commandcenter.common.activemq.service.impl;


import com.commandcenter.common.activemq.model.SmpUserRoleSync;
import com.commandcenter.common.activemq.model.SmpUserStaffBindSync;
import com.commandcenter.common.activemq.service.MqSmpDataService;
import com.commandcenter.dao.smp.*;
import com.commandcenter.model.dictmodel.SmptLanguage;
import com.commandcenter.model.dictmodel.SmptSystemInfo;
import com.commandcenter.model.smp.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: j18864
 * @Date: 2018/9/4 16:07
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MqSmpDataServiceImpl implements MqSmpDataService {


    private static Logger log = Logger.getLogger(MqSmpDataServiceImpl.class);

    @Autowired
    SmpOrgInfoMapper smpOrgInfoMapper;
    @Autowired
    SmpStaffInfoMapper smpStaffInfoMapper;
    @Autowired
    SmpDictValueMapper smpDictValueMapper;
    @Autowired
    SmpInterphoneInfoMapper smpInterphoneInfoMapper;
    @Autowired
    SmpDictInfoMapper smpDictInfoMapper;
    @Autowired
    SmpStaffUserMapper smpStaffUserMapper;
    @Autowired
    SmpVcGpsdataMapper smpVcGpsdataMapper;
    @Autowired
    SmpUserInfoMapper smpUserInfoMapper;
    @Autowired
    SmpRoleUserMapper smpRoleUserMapper;
    @Autowired
    SmpLanguageMapper smpLanguageMapper;
    @Autowired
    SmpSystemInfoMapper smpSystemInfoMapper;
    @Autowired
    SmptSmartappInfoDao smptSmartappInfoDao;
    @Autowired
    SmpStaffDeviceMapper smpStaffDeviceMapper;
    @Autowired
    SmpRoleInfoMapper smpRoleInfoMapper;
    @Autowired
    SmpDataAutityMapper smpDataAutityMapper;
    @Autowired
    SmpSsiGroupInfoMapper smpSsiGroupInfoMapper;
    @Autowired
    SmpRoleFunctionMapper smpRoleFunctionMapper;
    @Autowired
    SmpAuthInfoMapper smpAuthInfoMapper;

    /**
     * 批量插入SMP同步的组织机构信息
     *
     * @param smpOrgInfoList
     * @throws Exception
     */
    @Override
    public void batchInsertSmpOrgInfo(List<SmpOrgInfo> smpOrgInfoList, String type) {
        switch (type) {
            case "add":
                log.info("正在进行批量" + "组织机构增加" + "处理");
                smpOrgInfoList.stream().forEach(smpOrgInfoMapper::insert);
                break;
            case "update":
                log.info("正在进行批量" + "组织机构修改" + "处理");
                smpOrgInfoList.stream().forEach(smpOrgInfoMapper::updateByPrimaryKeySelective);
                break;
            case "delete":
                log.info("正在进行批量" + "组织机构删除" + "处理");
                smpOrgInfoList.stream().forEach(info -> {
                    //删除用户信息
                    smpOrgInfoMapper.deleteByPrimaryKeyLogic(info);
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "组织机构" + "处理");
                smpOrgInfoList.stream().forEach(info -> {
                    if(smpOrgInfoMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpOrgInfoMapper.updateByPrimaryKey(info);
                    }else{
                        smpOrgInfoMapper.insert(info);
                    }
                });
                break;
            default:

                break;
        }
    }
    @Override
    public void batchDeleteSmpOrgGuid(List<String> smpOrgGuidList, String action) {

    }
    @Override
    public void deleteAllSmpOrgInfo() {
        smpOrgInfoMapper.deleteAllSmpOrgInfo();
    }
    /**
     * 批量插入SMP同步的警员信息
     *
     * @param smpStaffInfoList
     * @throws Exception
     */
    @Override
    public void batchInsertSmpStaffInfo(List<SmpStaffInfo> smpStaffInfoList, String type) {
        switch (type) {
            case "add":
                log.info("正在进行批量" + "警员增加" + "处理");
                smpStaffInfoList.stream().forEach(smpStaffInfoMapper::insert);
                break;
            case "update":
                log.info("正在进行批量" + "警员修改" + "处理");
                smpStaffInfoList.stream().forEach(smpStaffInfoMapper::updateByPrimaryKeySelective);
                break;
            case "delete":
                log.info("正在进行批量" + "警员删除" + "处理");
                smpStaffInfoList.stream().forEach(info -> {
                    //删除用户信息
                    smpStaffInfoMapper.deleteByPrimaryKeyLogic(info);
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "警员" + "处理");
                smpStaffInfoList.stream().forEach(info -> {
                    if(smpStaffInfoMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpStaffInfoMapper.updateByPrimaryKey(info);
                    }else{
                        smpStaffInfoMapper.insert(info);
                    }
                });
                break;
            default:
                break;
        }
    }
    @Override
    public void deleteAllSmpStaffInfo() {
        smpStaffInfoMapper.deleteAllSmpStaffInfo();
    }
    @Override
    public void deleteAllDataAuthority() {
        smpDataAutityMapper.deleteAllDataAuthority();
    }


    @Override
    public void batchDeleteSmpStaffInfoGuid(List<String> smpStaffInfoList, String action) {

    }
    /**
     * 批量插入SMP同步的字典信息
     *
     * @param smpDictInfoList
     * @param smpDictValueList
     * @throws Exception
     */
    @Override
    public void batchInsertSmpDictInfo(List<SmpDictInfo> smpDictInfoList, List<SmpDictValue> smpDictValueList, String type){
        switch (type) {
            case "add":
                log.info("正在进行批量" + "数据字典信息增加" + "处理");
                smpDictInfoList.stream().forEach(smpDictInfoMapper::insert);
                smpDictValueList.stream().forEach(smpDictValueMapper::insert);
                break;
            case "update":
                log.info("正在进行批量" + "数据字典信息修改" + "处理");
                smpDictInfoList.stream().forEach(smpDictInfoMapper::updateByPrimaryKeySelective);
                smpDictValueList.stream().forEach(smpDictValueMapper::updateByPrimaryKeySelective);
                break;
            case "delete":
                log.info("正在进行批量" + "数据字典信息删除" + "处理");
                smpDictInfoList.stream().forEach(info -> {
                    //删除数据字典信息
                    smpDictInfoMapper.deleteByPrimaryKey(info.getGuid());
                });
                smpDictValueList.stream().forEach(info -> {
                    //删除数据字典信息
                    smpDictValueMapper.deleteByPrimaryKey(info.getGuid());
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "数据字典信息" + "处理");
                smpDictInfoList.stream().forEach(info -> {
                    if(smpDictInfoMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpDictInfoMapper.updateByPrimaryKey(info);
                    }else{
                        smpDictInfoMapper.insert(info);
                    }
                });
                smpDictValueList.stream().forEach(info -> {
                    if(smpDictInfoMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpDictValueMapper.updateByPrimaryKey(info);
                    }else{
                        smpDictValueMapper.insert(info);
                    }
                });
                break;
            default:

                break;
        }
    }
    @Override
    public void deleteAllSmpDictInfo() {
        smpDictInfoMapper.deleteAllSmpDictInfo();
    }
    @Override
    public void deleteAllSmpRoleInfo() {
        smpRoleInfoMapper.deleteAllSmpRoleInfo();
    }
    @Override
    public void deleteAllSmpDictValue() {
        smpDictValueMapper.deleteAllSmpDictValue();
    }
    @Override
    public void deleteAllRoleFunctionInfo() {
        smpRoleFunctionMapper.deleteAllRoleFunctionInfo();
    }
    @Override
    public void batchDeleteSmpDictInfo(List<String> smpStaffInfoList, String action) {

    }
    /**
     * 批量插入SMP同步的手台信息
     *
     * @param smpInterphoneInfoList
     * @throws Exception
     */
    @Override
    public void batchInsertSmpInterphoneInfor (List<SmpInterphoneInfo> smpInterphoneInfoList, String type){
        switch (type) {
            case "add":
                log.info("正在进行批量" + "设备信息（手台)增加" + "处理");
                smpInterphoneInfoList.stream().forEach(smpInterphoneInfoMapper::insert);
                break;
            case "update":
            case "bind":
            case "unbind":
                log.info("正在进行批量" + "设备信息（手台)修改" + "处理");
                for(SmpInterphoneInfo smpInterphoneInfo :smpInterphoneInfoList){
                    smpInterphoneInfoMapper.unBindInterphoneInfo(smpInterphoneInfo.getUserGuid());
                }
                smpInterphoneInfoList.stream().forEach(smpInterphoneInfoMapper::updateByPrimaryKeySelective);
                break;
            case "delete":
                log.info("正在进行批量" + "设备信息（手台)删除" + "处理");
                smpInterphoneInfoList.stream().forEach(info -> {
                    //删除用户信息
                    smpInterphoneInfoMapper.deleteByPrimaryKeyLogic(info);
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "设备信息" + "处理");
                smpInterphoneInfoList.stream().forEach(info -> {
                    if(smpInterphoneInfoMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpInterphoneInfoMapper.updateByPrimaryKey(info);
                    }else{
                        smpInterphoneInfoMapper.insert(info);
                    }
                });
                break;
            default:
                break;
        }
    }
    @Override
    public void batchInsertSmpLanguageInfor (List<SmptLanguage> smptLanguageList, String type){
        switch (type) {
            case "add":
                log.info("正在进行批量" + "数据字典语言增加" + "处理");
                smptLanguageList.stream().forEach(smpLanguageMapper::insert);
                break;
            case "update":
                log.info("正在进行批量" + "数据字典语言修改" + "处理");
                smptLanguageList.stream().forEach(smpLanguageMapper::updateByPrimaryKeySelective);
                break;
            case "delete":
                log.info("正在进行批量" + "数据字典语言删除" + "处理");
                smptLanguageList.stream().forEach(info -> {
                    //删除数据字典语言信息
                    smpLanguageMapper.deleteByPrimaryKey(info.getGuid());
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "数据字典语言" + "处理");
                smptLanguageList.stream().forEach(info -> {
                    if(smpLanguageMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpLanguageMapper.updateByPrimaryKey(info);
                    }else{
                        smpLanguageMapper.insert(info);
                    }
                });
                break;
            default:

                break;
        }
    }
    @Override
    public void batchInsertSmpSystemInfor (List<SmptSystemInfo> smptSystemInfoList, String type){
        switch (type) {
            case "add":
                log.info("正在进行批量" + "系统增加" + "处理");
                smptSystemInfoList.stream().forEach(smpSystemInfoMapper::insert);
                break;
            case "update":
                log.info("正在进行批量" + "系统修改" + "处理");
                smptSystemInfoList.stream().forEach(smpSystemInfoMapper::updateByPrimaryKeySelective);
                break;
            case "delete":
                log.info("正在进行批量" + "系统删除" + "处理");
                smptSystemInfoList.stream().forEach(info -> {
                    //删除数据字典语言信息
                    smpSystemInfoMapper.deleteByPrimaryKey(info.getGuid());
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "系统" + "处理");
                smptSystemInfoList.stream().forEach(info -> {
                    if(smpSystemInfoMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpSystemInfoMapper.updateByPrimaryKey(info);
                    }else{
                        smpSystemInfoMapper.insert(info);
                    }
                });
                break;
            default:

                break;
        }
    }
    @Override
    public void batchInsertSmpGroupInfor (List<SmpSsiGroupInfo> smpSsiGroupInfoList, String type){
        switch (type) {
            case "add":
                log.info("正在进行批量" + "系统增加" + "处理");
                smpSsiGroupInfoList.stream().forEach(smpSsiGroupInfoMapper::insert);
                break;
            case "update":
                log.info("正在进行批量" + "系统修改" + "处理");
                smpSsiGroupInfoList.stream().forEach(smpSsiGroupInfoMapper::updateByPrimaryKeySelective);
                break;
            case "delete":
                log.info("正在进行批量" + "系统删除" + "处理");
                smpSsiGroupInfoList.stream().forEach(info -> {
                    //删除数据字典语言信息
                    smpSsiGroupInfoMapper.deleteByPrimaryKeyLogic(info);
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "系统" + "处理");
                smpSsiGroupInfoList.stream().forEach(info -> {
                    if(smpSsiGroupInfoMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpSsiGroupInfoMapper.updateByPrimaryKey(info);
                    }else{
                        smpSsiGroupInfoMapper.insert(info);
                    }
                });
                break;
            default:

                break;
        }
    }

    @Override
    public void batchInsertSmartappInfo(List<SmptSmartappInfo> smptSmartappInfoList, String type){
        switch (type) {
            case "add":
                log.info("正在进行批量" + "SMARTAPP增加" + "处理");
                smptSmartappInfoList.stream().forEach(smptSmartappInfoDao::insertSmptSmartappInfo);
                break;
            case "update":
                log.info("正在进行批量" + "SMARTAPP修改" + "处理");
                smptSmartappInfoList.stream().forEach(smptSmartappInfoDao::updateSmptSmartappInfoById);
                break;
            case "delete":
                log.info("正在进行批量" + "SMARTAPP删除" + "处理");
                smptSmartappInfoList.stream().forEach(info -> {
                    //删除数据字典语言信息
                    smptSmartappInfoDao.deleteSmptSmartappInfoByIdLogic(info);
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "SMARTAPP" + "处理");
                smptSmartappInfoList.stream().forEach(info -> {
                    if(smptSmartappInfoDao.selectSmptSmartappInfoById(info.getGuid())!=null){
                        smptSmartappInfoDao.updateSmptSmartappInfoById(info);
                    }else{
                        smptSmartappInfoDao.insertSmptSmartappInfo(info);
                    }
                });
                break;
            default:

                break;
        }
    }
    @Override
    public void batchInsertStaffDeviceInfo(List<SmpStaffDeviceInfo> smpStaffDeviceInfoList, String type){
        switch (type) {
            case "add":
                log.info("正在进行批量" + "STAFFDEVICE增加" + "处理");
                smpStaffDeviceInfoList.stream().forEach(smpStaffDeviceMapper::insertSmptStaffDeviceInfo);
                break;
            case "update":
                log.info("正在进行批量" + "STAFFDEVICE修改" + "处理");
                smpStaffDeviceInfoList.stream().forEach(smpStaffDeviceMapper::updateSmptStaffDeviceById);
                break;
            case "delete":
                log.info("正在进行批量" + "STAFFDEVICE删除" + "处理");
                smpStaffDeviceInfoList.stream().forEach(info -> {
                    //删除数据字典语言信息
                    smpStaffDeviceMapper.deleteSmptStaffDeviceByIdLogic(info);
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "STAFFDEVICE" + "处理");
                smpStaffDeviceInfoList.stream().forEach(info -> {
                    if(smpStaffDeviceMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpStaffDeviceMapper.updateSmptStaffDeviceById(info);
                    }else{
                        smpStaffDeviceMapper.insertSmptStaffDeviceInfo(info);
                    }
                });
                break;
            default:

                break;
        }
    }
    @Override
    public void batchInsertSmpRoleUser(List<SmpRoleUser> smpRoleUserList, String type){
        switch (type) {
            case "add":
                log.info("正在进行批量" + "用户角色增加" + "处理");
                smpRoleUserList.stream().forEach(smpRoleUserMapper::insert);
                break;
            case "update":
                log.info("正在进行批量" + "用户角色修改" + "处理");
                smpRoleUserList.stream().forEach(smpRoleUserMapper::insert);
                break;
            case "delete":
                log.info("正在进行批量" + "用户角色删除" + "处理");
                smpRoleUserList.stream().forEach(info -> {
                    //删除数据字典语言信息
                    smpRoleUserMapper.deleteByUserRoleId(info);
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "STAFFDEVICE" + "处理");
                smpRoleUserList.stream().forEach(smpRoleUserMapper::insert);
                break;
            default:

                break;
        }
    }
    @Override
    public void batchInsertSmpAuthInfo(List<SmpAuthInfo> smpAuthInfoList, String type){
        switch (type) {
            case "add":
                log.info("正在进行批量" + "功能增加" + "处理");
                smpAuthInfoList.stream().forEach(smpAuthInfoMapper::insert);
                break;
            case "update":
                log.info("正在进行批量" + "功能修改" + "处理");
                smpAuthInfoList.stream().forEach(smpAuthInfoMapper::insert);
                break;
            case "delete":
                log.info("正在进行批量" + "功能删除" + "处理");
                smpAuthInfoList.stream().forEach(info -> {
                    //删除数据字典语言信息
                    smpAuthInfoMapper.deleteByAuthGuid(info);
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "功能" + "处理");
                smpAuthInfoList.stream().forEach(smpAuthInfoMapper::insert);
                break;
            default:

                break;
        }
    }
    /**
     * 批量插入SMP同步的角色信息
     *
     * @param SmpRoleInfoList
     * @throws Exception
     */
    @Override
    public void batchInsertSmpRoleInfo(List<SmpRoleInfo> SmpRoleInfoList, String type){
        switch (type) {
            case "add":
                log.info("正在进行批量" + "STAFFDEVICE增加" + "处理");
                SmpRoleInfoList.stream().forEach(smpRoleInfoMapper::insert);
                break;
            case "update":
                log.info("正在进行批量" + "STAFFDEVICE修改" + "处理");
                SmpRoleInfoList.stream().forEach(smpRoleInfoMapper::updateByPrimaryKey);
                break;
            case "delete":
                log.info("正在进行批量" + "STAFFDEVICE删除" + "处理");
                SmpRoleInfoList.stream().forEach(info -> {
                    //删除数据字典语言信息
                    smpRoleInfoMapper.deleteByPrimaryKey(info.getGuid());
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "STAFFDEVICE" + "处理");
                SmpRoleInfoList.stream().forEach(info -> {
                    if(smpStaffDeviceMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpRoleInfoMapper.updateByPrimaryKey(info);
                    }else{
                        smpRoleInfoMapper.insert(info);
                    }
                });
                break;
            default:

                break;
        }
    }
    @Override
    public void deleteAllSmpInterphoneInfor() {
        smpInterphoneInfoMapper.deleteAllSmpInterphoneInfo();
    }


    @Override
    public void batchDeleteSmpInterphoneInfor(List<String> smpStaffInfoList, String action) {

    }

    /**
     * @Author:jhx
     * @CreateDate:2018/9/6 11:18
     * @UpdateUser:jhx
     * @UpdateDate:2018/9/6 11:18
     * @UpdateRemark:The modified content
     * @param smpStaffInfoSyncs
     * @param type
     */
    @Override
    public void batchInsertUserStaffBindData(List<SmpUserStaffBindSync> smpStaffInfoSyncs, String type){
        switch (type) {
            case "add":
                log.info("正在进行批量" + "用户警员关系增加" + "处理");
                smpStaffInfoSyncs.stream().forEach(smpStaffUserMapper::insertUserStaff);
                break;
            case "update":
                log.info("正在进行批量" + "用户警员关系修改" + "处理");
                smpStaffInfoSyncs.stream().forEach(smpStaffUserMapper::updateByPrimaryKeySelective);
                break;
            case "delete":
                log.info("正在进行批量" + "用户警员关系删除" + "处理");
                smpStaffInfoSyncs.stream().forEach(info -> {
                    smpStaffUserMapper.deleteByPrimaryKey(info.getGuid());
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "用户警员关系" + "处理");
                smpStaffInfoSyncs.stream().forEach(info -> {
                    if(smpStaffUserMapper.selectByPrimaryKey(info.getUserGuid())!=null){
                        smpStaffUserMapper.updateByPrimaryKeySelective(info);
                    }else{
                        smpStaffUserMapper.insertUserStaff(info);
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void deleteAllUserStaffBind() {
        smpStaffUserMapper.deleteAllUserStaffBind();
    }


    @Override
    public void batchDeleteUserStaffBind(List<String> smpStaffInfoList, String action) {

    }

    @Override
    public void batchInsertVcgpsdataInfor(List<SmpVcGpsdata> smpVcGpsdataList, String type) {
        switch (type) {
            case "add":
                log.info("正在进行批量" + "执法记录仪增加" + "处理");
                smpVcGpsdataList.stream().forEach(smpVcGpsdataMapper::insert);
                break;
            case "update":
            case "unbind":
            case "bind":
                log.info("正在进行批量" + "执法记录仪修改" + "处理");
                for(SmpVcGpsdata smpVcGpsdata :smpVcGpsdataList){
                    smpVcGpsdataMapper.unBindVcGpsdata(smpVcGpsdata.getStaffGuid());
                }
                smpVcGpsdataList.stream().forEach(smpVcGpsdataMapper::updateByPrimaryKeySelective);
                break;
            case "delete":
                log.info("正在进行批量" + "执法记录仪删除" + "处理");
                smpVcGpsdataList.stream().forEach(info -> {
                    smpVcGpsdataMapper.deleteByPrimaryKey(info.getGuid());
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "警员" + "处理");
                smpVcGpsdataList.stream().forEach(info -> {
                    if(smpVcGpsdataMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpVcGpsdataMapper.updateByPrimaryKey(info);
                    }else{
                        smpVcGpsdataMapper.insert(info);
                    }
                });
                break;
            default:
                break;
        }
    }
    @Override
    public void deleteAllVcgpsdataInfor() {
        smpVcGpsdataMapper.deleteAllVcgpsdataInfor();
    }
    @Override
    public void batchDeleteVcgpsdataInfor(List<String> smpStaffInfoList, String type) {

    }

    @Override
    public void batchInsertSmpUserInfoInfo(List<SmpUserInfo> smpUserInfoInfoList, String type) {
        switch (type) {
            case "add":
                log.info("正在进行批量" + "User增加" + "处理");
                smpUserInfoInfoList.stream().forEach(smpUserInfoMapper::insert);
                break;
            case "update":
                log.info("正在进行批量" + "User修改" + "处理");
                smpUserInfoInfoList.stream().forEach(smpUserInfoMapper::updateByPrimaryKeySelective);
                break;
            case "delete":
                log.info("正在进行批量" + "User删除" + "处理");
                smpUserInfoInfoList.stream().forEach(info -> {
                    smpUserInfoMapper.deleteByPrimaryKey(info.getGuid());
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "警员" + "处理");
                smpUserInfoInfoList.stream().forEach(info -> {
                    if(smpUserInfoMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpUserInfoMapper.updateByPrimaryKey(info);
                    }else{
                        smpUserInfoMapper.insert(info);
                    }
                });
                break;
            default:
                break;
        }
    }
    @Override
    public void batchInsertDataAuthority(List<SmpDataAuthority> smpDataAuthorityList, String type){
        switch (type) {
            case "add":
                log.info("正在进行批量" + "角色权限增加" + "处理");
                smpDataAuthorityList.stream().forEach(smpDataAutityMapper::insert);
                break;
            case "update":
                log.info("正在进行批量" + "角色权限修改" + "处理");
                smpDataAuthorityList.stream().forEach(smpDataAutityMapper::insert);
                break;
            case "delete":
                log.info("正在进行批量" + "角色权限删除" + "处理");
                smpDataAuthorityList.stream().forEach(info -> {
                    smpDataAutityMapper.deleteByPrimaryKey(info.getGuid());
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "用户警员关系" + "处理");
                smpDataAuthorityList.stream().forEach(info -> {
                    if(smpStaffUserMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpDataAutityMapper.updateByPrimaryKeySelective(info);
                    }else{
                        smpDataAutityMapper.insert(info);
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void batchDeleteUserInfor(List<String> smpStaffInfoList, String action) {

    }


    @Override
    public void deleteAllUserInfor() {
        smpUserInfoMapper.deleteAllUserInfor();
    }


    @Override
    public void batchUserBindUnBindStaff(List<SmpUserStaffBindSync> userStaffBindSyncs, String type){
        switch (type) {
            case "bind":
                log.info("正在进行批量" + "用户绑定警员" + "处理");
                //先按照userId删除绑定信息
                userStaffBindSyncs.stream().forEach(info -> {
                    smpStaffUserMapper.deleteByUserId(info.getUserGuid());
                });
                //再查询最新的信息
                for(SmpUserStaffBindSync userStaffBind :userStaffBindSyncs){
                    smpStaffUserMapper.insertUserStaff(userStaffBind);
                }
//                userStaffBindSyncs.stream().forEach(smpStaffUserMapper::insertUserStaff);
                break;
            case "unbind":
                log.info("正在进行批量" + "用户解绑警员" + "处理");
                //先按照userId删除绑定信息
                userStaffBindSyncs.stream().forEach(info -> {
                    smpStaffUserMapper.deleteByUserId(info.getUserGuid());
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "警员" + "处理");
                userStaffBindSyncs.stream().forEach(info -> {
                    if(smpStaffUserMapper.selectByPrimaryKey(info.getGuid())!=null){
                        smpStaffUserMapper.updateByPrimaryKeySelective(info);
                    }else{
                        smpStaffUserMapper.insertUserStaff(info);
                    }
                });
                break;
            default:

                break;
        }
    }
    @Override
    public void batchInsertRoleFunction(List<SmpRoleFunctionInfo> smpRoleFunctionInfoList, String type){
        switch (type) {
            case "add":
                log.info("正在进行批量" + "角色功能权限增加" + "处理");
                smpRoleFunctionInfoList.stream().forEach(smpRoleFunctionMapper::insert);
                break;
            case "update":
                log.info("正在进行批量" + "角色功能权限修改" + "处理");
                smpRoleFunctionInfoList.stream().forEach(smpRoleFunctionMapper::insert);
                break;
            case "delete":
                log.info("正在进行批量" + "角色功能权限删除" + "处理");
                smpRoleFunctionInfoList.stream().forEach(info -> {
                    smpRoleFunctionMapper.deleteByPrimaryKey(info.getCode());
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "用户警员关系" + "处理");
                smpRoleFunctionInfoList.stream().forEach(info -> {
                    if(smpRoleFunctionMapper.selectByPrimaryKey(info.getCode())!=null){
                        smpRoleFunctionMapper.updateByPrimaryKeySelective(info);
                    }else{
                        smpRoleFunctionMapper.insert(info);
                    }
                });
                break;
            default:
                break;
        }
    }
    @Override
    public void batchUserBindUnBindRole(List<SmpUserRoleSync> userRoleSyncs, String type){
        switch (type) {
            case "bind_role":
                log.info("正在进行批量" + "用户绑定角色" + "处理");
//                //先按照userId删除绑定信息
//                userRoleSyncs.stream().forEach(info -> {
//                    smpUserRoleSyncMapper.deleteByUserId(info.getUserGuid());
//                });
                //插入最新的信息
                userRoleSyncs.stream().forEach(smpRoleUserMapper::insertSmpUserRole);
                break;
            case "unbind_role":
                log.info("正在进行批量" + "用户解绑角色" + "处理");
                //先按照userId删除绑定信息
                userRoleSyncs.stream().forEach(info -> {
                    smpRoleUserMapper.deleteByUserId(info.getUserGuid());
                });
                break;
            default:

                break;
        }
    }

    @Override
    public void batchUserBindUnBindOrg(List<SmpUserInfo> userInfoSyncs, String type){
        switch (type) {
            case "bind_org":
                log.info("正在进行批量" + "用户绑定组织机构" + "处理");
                userInfoSyncs.stream().forEach(smpUserInfoMapper::updateByPrimaryKeySelective);
                break;
            case "unbind_org":
                log.info("正在进行批量" + "用户解绑组织机构" + "处理");
                userInfoSyncs.stream().forEach(smpUserInfoMapper::updateByPrimaryKeySelective);
                break;
            default:
                break;
        }
    }
    @Override
    public void deleteAllLanguageInfo(){
        smpLanguageMapper.deleteAllSmpLanguageInfo();
    }
    @Override
    public void deleteAllSystemInfo(){
        smpSystemInfoMapper.deleteAllSmpSystemInfo();
    }
    @Override
    public void deleteAuthorityByroleGuid(String roleGuid){
        smpDataAutityMapper.deleteAuthorityByroleGuid(roleGuid);
    }
    @Override
    public void deleteAllStaffDeviceInfo(){
        smpStaffDeviceMapper.deleteAllSmpStaffDeviceInfo();
    }


    @Override
    public void deleteAllSmartAppInfo(){
        smptSmartappInfoDao.deleteSmptSmartAppInfo();
    }
    @Override
    public int selectStaffCount(){
        return smpStaffInfoMapper.selectStaffCount();
    }
    @Override
    public void deleteAllRoleUserInfo(){
        smpRoleUserMapper.deleteAllRoleUserInfo();
    }
    @Override
    public int selectDictInfoCount(){
        return smpDictInfoMapper.selectDictInfoCount();
    }
    @Override
    public int selectInterphoneCount(){
        return smpInterphoneInfoMapper.selectInterphoneCount();
    }
    @Override
    public int selectStaffUserCount(){
        return smpStaffUserMapper.selectStaffUserCount();
    }
    @Override
    public int selectVcsGpsCount(){
        return smpVcGpsdataMapper.selectVcsGpsCount();
    }
    @Override
    public int selectUserCount(){
        return smpUserInfoMapper.selectUserCount();
    }
    @Override
    public int selectdictValueCount(){
        return smpDictValueMapper.selectdictValueCount();
    }
    @Override
    public int selectLanguageCount(){
        return smpLanguageMapper.selectLanguageCount();
    }
    @Override
    public int selectSystemCount(){
        return smpSystemInfoMapper.selectSystemCount();
    }
    @Override
    public int selectSsiGroupCount(){
        return smpSsiGroupInfoMapper.selectSsiGroupCount();
    }

    @Override
    public int selectRoleCount(){
        return smpRoleInfoMapper.selectRoleCount();
    }
    @Override
    public int selectSmartOneCount(){
        return smptSmartappInfoDao.selectSmartOneCount();
    }
    @Override
    public int staffDeviceNum(){
        return smpStaffDeviceMapper.selectstaffDeviceNum();
    }
    @Override
    public int roleUserNum(){
        return smpRoleUserMapper.selectRoleUserNum();
    }
    @Override
    public int selectStaffMaxVersion(){
        return smpStaffInfoMapper.selectStaffMaxVersion();
    }
    @Override
    public int selectDictMaxVersion() {
        return smpDictInfoMapper.selectDictMaxVersion();
    }

    @Override
    public int selectDeviceMaxVersion() {
        return smpInterphoneInfoMapper.selectDeviceMaxVersion();
    }

    @Override
    public int selectLanguageMaxVersion() {
        return smpLanguageMapper.selectLanguageMaxVersion();
    }

    @Override
    public int selectSystemMaxVersion() {
        return smpSystemInfoMapper.selectSystemMaxVersion();
    }

    @Override
    public int selectSmartAppMaxVersion() {
        return smptSmartappInfoDao.selectSmartAppMaxVersion();
    }

    @Override
    public int selectUserMaxVersion() {
        return smpUserInfoMapper.selectUserMaxVersion();
    }
    @Override
    public void deleteAllGroupInfo() {
        smpSsiGroupInfoMapper.deleteAllGroupInfo();
    }


}
