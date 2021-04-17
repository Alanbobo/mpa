package com.commandcenter.webController.pageController;
import com.alibaba.fastjson.JSON;
import com.commandcenter.common.activemq.component.PublishToAPP;
import com.commandcenter.common.activemq.component.PublishToVCS;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.HttpRequest;
import com.commandcenter.common.utils.R;
import com.commandcenter.controller.SmpController.SmpController;
import com.commandcenter.model.smp.vo.LoginForVcs;
import com.commandcenter.model.smp.vo.StaffModel;
import com.commandcenter.model.webmodel.KickEntity;
import com.commandcenter.modules.sys.service.SysUserTokenService;
import com.commandcenter.service.appupdate.UpdateInfoService;
import com.commandcenter.service.smp.SmpUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/webController/kick")
public class KickController extends SmpController {

    protected final static Logger logger = LoggerFactory.getLogger(KickController.class);
    @Autowired
    private SmpUserService smpUserService;
    @Autowired
    private PublishToAPP publishToAPP;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private UpdateInfoService updateInfoService;
    @Autowired
    private PublishToVCS publishToVCS;

    private String WINDOWS_FILE_URL = "C:\\Program Files (x86)\\CommandCenter\\MPA\\MPA_Server\\webapps";
    private String LINUX_FILE_URL = "/usr/CommandCenter/tomcat/webapps";

    @Value("${system.notice.app.AccountMQ}")
    private String AccountMQ;

    @Value("${appupdate.addr}")
    private String updateUrl;

    @Value("${system.notice.app.updateAppMQ}")
    private String updateMQ;

    @Value("${smp.server.ip}")
    private String smpIp;

    @Value("${smp.server.port}")
    private String smpPort;

    @Value("${smp.casLogOut}")
    private String casLogout;
    @RequestMapping(value = "/queryUser", method = RequestMethod.GET)
    public R queryUser(@ModelAttribute("searchInfo") String searchInfo, HttpServletRequest request){
        Map map = new HashMap<String, Object>(2);
        map.put("staffName", request.getParameter("searchInfo"));
        List<Map<String, Object>> users = smpUserService.selectUserByContain(map);
        String allUserCode = new String();
        String allGuid = new String();
        String allStaffName = new String();
        String allOrgName = new String();
        for (Map<String, Object> user : users) {
            String tokenOld = (String) Constant.tokenMapIns.get(user.get("userCode"));
            if (null != tokenOld && !"".equals(tokenOld)) {
                allGuid += (String) user.get("guid")+",";
                allUserCode += (String) user.get("userCode") + ",";
                allStaffName += (String) user.get("staffName") + ",";
                allOrgName += (String) user.get("orgName") + ",";
            }
        }
        String[] guid = allGuid.split(",");
        String[] userCode = allUserCode.split(",");
        String[] staffName = allStaffName.split(",");
        String[] orgName = allOrgName.split(",");
        Map resultMap = new HashMap(5);
        KickEntity[] resultString = new KickEntity[userCode.length];
        if(users.size()!=0){
            for (int i=0; i<userCode.length; i++){
                KickEntity kickEntity = new KickEntity();
                kickEntity.setOrgName(orgName[i]);
                kickEntity.setGuid(guid[i]);
                kickEntity.setStaffName(staffName[i]);
                kickEntity.setUserCode(userCode[i]);
                if(kickEntity!=null){
                    resultString[i] = kickEntity;
                }
            }
            resultMap.put("entity",resultString);
        }
        return R.ok(resultMap);


    }
    @RequestMapping(value = "/on", method = RequestMethod.GET)
    public R kick(@ModelAttribute("userCode") String userCode,@ModelAttribute("guid") String guid, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(2);
        String tokenOld = (String) Constant.tokenMapIns.get(userCode);
        String restring = "failure";
        try {
            if (null != tokenOld) {
                String msg = "{\"code\":\"0\",\"msg\":\"已强制下线，账号已丢失\"}";
                //logger.info("账号(" + userCode + ")已丢失");

                MQParamModel param = new MQParamModel(msg, AccountMQ);
                param.setPersonalFlag(true);
                param.setQueueFlag(false);

                List<String> userNos = new ArrayList<>();
                userNos.add(tokenOld);
                userNos.add(tokenOld);
                param.setUserNos(userNos);

                //发送强制下线MQ
                publishToAPP.sendToApp(param);

                //将token失效
                Constant.tokenMapIns.remove(userCode);
                Constant.tokenMap.remove(tokenOld);
                sysUserTokenService.logout(guid);
                //将用户下线信息传给后台可视化
                List<StaffModel> staffList = smpUserService.selectStaffByUserCode(userCode);
                LoginForVcs loginForVcs = new LoginForVcs();
                loginForVcs.setDEVICE_ID(staffList.get(0).getGuid());
                loginForVcs.setISONLINE("0");
                publishToVCS.notifyLoginToVCS(loginForVcs);

                //CAS登出
                Map<String,Object> casMap = new HashMap<>(2);
                if(Constant.tgtMap.containsKey(userCode)) {
                    casMap.put("tgtId", Constant.tgtMap.get(userCode));
                    casMap.put("systemNo", "MPA");
                    String casJson = JSON.toJSONString(casMap);
                    HttpRequest.sendPost("http://" + smpIp + ":" + smpPort + casLogout, casJson);
                }
                restring = "success";
                map.put("restring", restring);
                return R.ok(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("restring", "failure");
            return R.ok(map);
        }
        return R.ok(map);
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R updateApp(String appSize, String appUpdateInfo, String appVersionCode,String appUrl,String appVersionName, @RequestParam("file") MultipartFile file,HttpServletRequest request){
        String restring = "failure";
        Map<String ,Object> resultMap = new HashMap<>(2);
        Map<String, Object> map = new HashMap<>(6);
        String appFileName = file.getOriginalFilename();
         String OS = System.getProperty("os.name").toLowerCase();

        try{
            map.put("appSize", appSize);
            map.put("appUpdateInfo", appUpdateInfo);
            map.put("serverVersionCode", appVersionCode);
            map.put("appUrl", appUrl);
            map.put("serverVersionName", appVersionName);

            String tomcat_path = System.getProperty( "catalina.home");
            logger.info("Tomcat服务器所在的路径: "+tomcat_path);

            String pic_path = "";
            if(tomcat_path.contains("tomcat")){
                if (OS.contains("linux")){
                    pic_path = tomcat_path +"/webapps"+"/appApk";

                }else{
                    pic_path = tomcat_path +"\\webapps"+"\\appApk";
                }
                logger.info("pic_path: "+pic_path);
            }else{
                if (OS.contains("linux")){
                    pic_path = LINUX_FILE_URL+"/appApk";
                }else{
                    pic_path = WINDOWS_FILE_URL+"\\appApk";
                }

            }
            String savePath;
            File newFileDir = new File(pic_path);
            //如果不存在 则创建
            if (!newFileDir.exists()) {
                newFileDir.mkdirs();
            }
            if (OS.contains("linux")){
                savePath = newFileDir +"/" + appFileName;
            }else{
                savePath = newFileDir +"\\" + appFileName;
            }

            logger.info("上传apk的路径：" + savePath);
            if(!appFileName.equals("")){
                file.transferTo(new File(savePath));
            }

            Map<String, Object> selectInfo = updateInfoService.selectAllInfo();
            if (selectInfo != null && selectInfo.size()!=0) {
                int i = updateInfoService.update(map);
                if (i == 1) {
                    restring = "success";
                }
            } else {
                int i = updateInfoService.insert(map);
                if (i == 1) {
                    restring = "success";
                }
            }
            resultMap.put("restring",restring);
            return R.ok(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("restring",restring);
            return R.ok(resultMap);
        }
    }

    @RequestMapping(value = "/getUpdate",method = RequestMethod.POST)
    public R getUpdate(){
        Map<String, String> map = new HashMap<>(2);
        map.put("appName","mpa");
        String requestJson = HttpRequest.sendGet(updateUrl,map);
        System.out.println("接收到的数据==========================="+requestJson);
        MQParamModel param = new MQParamModel(requestJson,updateMQ);
        param.setQueueFlag(false);
        param.setPersonalFlag(true);
        List values = new ArrayList<>(Constant.tokenMapIns.values());
        List userNos = new ArrayList<String>();
        for (Object userNo : values) {
            userNos.add(userNo.toString());
        }
        param.setUserNos(userNos);
        System.out.println("用户数量==============================="+userNos.size());
        publishToAPP.sendToApp(param);
        return R.ok();
    }
    @RequestMapping(value = "sync",method = RequestMethod.GET)
    public R sync(@ModelAttribute("info") String info){
        SmpController smpController = new SmpController();
        switch (info){
            case "all":
                smpController.syncAllSmpInfo();
                break;
            case "organ":
                smpController.getOrganInfor();
                break;
            case "staff":
                smpController.getStaffInfor();
                break;
            case "dict":
                smpController.getDictInfor();
                break;
            case "interphone":
                smpController.getInterphoneInfor();
                break;
            case "userStaff":
                smpController.getSmpUserStaffBind();
                break;
            case "vcgps":
                smpController.getVcgpsdataInfor();
                break;
            case "user":
                smpController.getUserInfo();
                break;
            case "language":
                smpController.getLanguage();
                break;
            case "system":
                smpController.getSystemInfo();
                break;
            case "ssigroup":
                smpController.getSsiGroupInfo();
                break;
        }
        return R.ok();
    }

    @RequestMapping("/sdg")
    public String getSystatus(){
        return "200";
    }

    @RequestMapping("/getOrganInfor")
    public R getOrgData(){
        return super.getOrganInfor();
    }
    @RequestMapping("/getStaffInfor")
    public R getStaffData(){
        return super.getStaffInfor();
    }
    @RequestMapping("/getDictInfor")
    public R getDictData(){
        return super.getDictInfor();
    }
    @RequestMapping("/getInterphoneInfor")
    public R getInterphoneData(){
        return super.getInterphoneInfor();
    }
    @RequestMapping("/getSmpUserStaffBind")
    public R getSmpUserStaffBindData(){
        return super.getSmpUserStaffBind();
    }
    @RequestMapping("/getVcgpsdataInfor")
    public R getVcgpsdata(){
        return super.getVcgpsdataInfor();
    }
    @RequestMapping("/getUserInfo")
    public R getUserInfoData(){
        return super.getUserInfo();
    }
    @RequestMapping("/getLanguage")
    public R getLanguageData(){
        return super.getLanguage();
    }
    @RequestMapping("/getSystemInfo")
    public R getSystemInfoData(){
        return super.getSystemInfo();
    }
    @RequestMapping("/getSmartAppInfo")
    public R getSmartAppData(){
        return super.getSmartAppInfo();
    }
    @RequestMapping("/getSmpStaffDeviceInfo")
    public R getSmpStaffDeviceInfo(){
        return super.getSmpStaffDevice();
    }
    @RequestMapping("/getAuthInfo")
    public R getSmpAuthInfo(){
        return super.getAuthInfo();
    }
    @RequestMapping("/getSmpRoleUser")
    public R getSmpRoleUser(){
        return super.getAuthInfo();
    }

    @RequestMapping("/getSsiGroupInfo")
    public R getSsiGroup(){
        return super.getSsiGroupInfo();
    }




}

