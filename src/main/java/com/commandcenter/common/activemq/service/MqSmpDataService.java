package com.commandcenter.common.activemq.service;


import com.commandcenter.common.activemq.model.SmpUserRoleSync;
import com.commandcenter.common.activemq.model.SmpUserStaffBindSync;
import com.commandcenter.model.dictmodel.SmptLanguage;
import com.commandcenter.model.dictmodel.SmptSystemInfo;
import com.commandcenter.model.smp.*;

import java.util.List; /**
 * @Auther: j18864
 * @Date: 2018/9/4 16:07
 * @Description:
 */
public interface MqSmpDataService {
    void deleteAllSmpOrgInfo();
    void deleteAllSmpStaffInfo();
    void deleteAllDataAuthority();
    void deleteAllRoleFunctionInfo();
    void batchInsertSmpStaffInfo(List<SmpStaffInfo> smpStaffInfoList, String action);

    void batchInsertSmpOrgInfo(List<SmpOrgInfo> smpOrgInfoList, String action);

    void batchDeleteSmpOrgGuid(List<String> smpOrgGuidList, String action);

    void batchDeleteSmpStaffInfoGuid(List<String> smpStaffInfoList, String action);
    void batchInsertSmpDictInfo(List<SmpDictInfo> smpDictInfoList, List<SmpDictValue> smpDictValueList, String type);
    void deleteAllSmpDictInfo();
    void deleteAllSmpDictValue();
    void batchDeleteSmpDictInfo(List<String> smpStaffInfoList, String action);

    void batchInsertSmpInterphoneInfor (List<SmpInterphoneInfo> smpInterphoneInfoList, String type);
    void batchInsertSmpLanguageInfor (List<SmptLanguage> smptLanguageList, String type);
    void deleteAllSmpInterphoneInfor();
    void batchDeleteSmpInterphoneInfor(List<String> smpStaffInfoList, String action);

    void batchInsertUserStaffBindData(List<SmpUserStaffBindSync> userStaffs, String action);

    void deleteAllUserStaffBind();
    void batchDeleteUserStaffBind(List<String> smpStaffInfoList, String action);

    void deleteAllVcgpsdataInfor();

    void batchInsertVcgpsdataInfor(List<SmpVcGpsdata> smpVcGpsdataList, String action);

    void batchDeleteVcgpsdataInfor(List<String> smpStaffInfoList, String action);

    void deleteAllUserInfor();
    void batchInsertSmpUserInfoInfo(List<SmpUserInfo> smpUserInfoInfoList, String action);
    void batchDeleteUserInfor(List<String> smpStaffInfoList, String action);

    void batchUserBindUnBindStaff(List<SmpUserStaffBindSync> userStaffs, String action);


    void batchUserBindUnBindRole(List<SmpUserRoleSync> userRoles, String action);

    void batchUserBindUnBindOrg(List<SmpUserInfo> userInfos, String action);

    void deleteAllLanguageInfo();
    void deleteAllSystemInfo();
    void deleteAllStaffDeviceInfo();
    void batchInsertSmpSystemInfor (List<SmptSystemInfo> smptSystemInfoList, String type);
    void batchInsertSmpGroupInfor (List<SmpSsiGroupInfo> smpSsiGroupInfoList, String type);
    void batchInsertSmpRoleInfo(List<SmpRoleInfo> SmpRoleInfoList, String type);
    void deleteAllSmartAppInfo();
    void batchInsertSmartappInfo(List<SmptSmartappInfo> smptSmartappInfo, String type);
    void batchInsertDataAuthority(List<SmpDataAuthority> smpDataAuthorityList, String type);
    void batchInsertRoleFunction(List<SmpRoleFunctionInfo> smpRoleFunctionInfoList, String type);
    void batchInsertStaffDeviceInfo(List<SmpStaffDeviceInfo> smpStaffDeviceInfoList, String type);
    void deleteAuthorityByroleGuid(String roleGuid);
    void batchInsertSmpRoleUser(List<SmpRoleUser> smpRoleUserList, String type);
    void batchInsertSmpAuthInfo(List<SmpAuthInfo> smpAuthInfoList, String type);
    int selectStaffCount();
    void deleteAllRoleUserInfo();
    int selectDictInfoCount();
    int selectInterphoneCount();
    int selectStaffUserCount();
    int selectVcsGpsCount();
    int selectUserCount();
    int selectdictValueCount();
    int selectLanguageCount();
    int selectSystemCount();
    int selectSsiGroupCount();
    int selectRoleCount();
    int selectSmartOneCount();
    int staffDeviceNum();
    void deleteAllSmpRoleInfo();
    int  roleUserNum();

    int selectStaffMaxVersion();
    int selectDictMaxVersion();
    int selectDeviceMaxVersion();
    int selectLanguageMaxVersion();
    int selectSystemMaxVersion();
    int selectSmartAppMaxVersion();
    int selectUserMaxVersion();
    void deleteAllGroupInfo();

}
