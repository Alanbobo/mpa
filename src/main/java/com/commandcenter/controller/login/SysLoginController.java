package com.commandcenter.controller.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.activemq.component.PublishToAPP;
import com.commandcenter.common.activemq.component.PublishToVCS;
import com.commandcenter.common.activemq.model.MQParamModel;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.HttpRequest;
import com.commandcenter.common.utils.MD5Util;
import com.commandcenter.common.utils.R;
import com.commandcenter.controller.baseController.BaseController;
import com.commandcenter.model.smp.SmpRoleFunctionInfo;
import com.commandcenter.model.smp.SmpUserInfo;
import com.commandcenter.model.smp.SmptSmartappInfo;
import com.commandcenter.model.smp.vo.LoginForVcs;
import com.commandcenter.model.smp.vo.StaffForAppModel;
import com.commandcenter.model.smp.vo.StaffModel;
import com.commandcenter.model.smp.vo.UserModel;
import com.commandcenter.modules.sys.entity.SysUserTokenEntity;
import com.commandcenter.modules.sys.service.SysUserTokenService;
import com.commandcenter.service.smp.SmpRoleFunctionService;
import com.commandcenter.service.smp.SmpUserService;
import com.commandcenter.service.smp.SmptSmartappInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录相关
 * 
 * @author r25437
 * @date 2016年11月10日 下午1:15:31
 */
@RestController
public class SysLoginController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(SysLoginController.class);
	@Autowired
	private SysUserTokenService sysUserTokenService;

	@Autowired
    private SmpUserService smpUserService;
	@Autowired
    private SmpRoleFunctionService smpRoleFunctionService;

	@Autowired
	private PublishToAPP publishToAPP;

	@Autowired
	private PublishToVCS publishToVCS;

	@Autowired
	private SmptSmartappInfoService smptSmartappInfoService;

	@Value("${system.connect}")
	private String CONN_SYSTEM;
	@Value("${system.function}")
	private String SYSTEM_FUNCTION;

	@Value("${system.version.vcs}")
	private String VCS_VERSION;

	@Value("${system.gps.interval}")
	private String appGpsInterval;

	@Value("${jms.android.url}")
	private String jmsAndroidUrl;

	@Value("${jms.android.username}")
	private String jmsAndroidUsername;

	@Value("${jms.android.password}")
	private String jmsAndroidPassword;

	@Value("${system.http.timeout}")
	private String appHttpTimeout;

	@Value("${mcontrol.port}")
	private String mcontrolPort;

	@Value("${mcontrol.ip}")
	private String mcontrolIp;

	@Value("${system.notice.app.AccountMQ}")
	private String AccountMQ;

	@Value("${appupdate.addr}")
	private String updateAddress;

	@Value("${puc.server.ip}")
	private String pucServerIp;

	@Value("${puc.server.port}")
	private String pucServerPort;

	@Value("${puc.ws.ip}")
	private String pucWsIp;

	@Value("${puc.ws.port}")
	private String pucWsPort;

	@Value("${smp.server.ip}")
	private String smpIp;

	@Value("${smp.server.port}")
	private String smpPort;

	@Value("${smp.casLogin}")
	private String casLogin;

	@Value("${smp.casLogOut}")
	private String casLogout;
	@Value("${mess.map.url}")
	private String mapUrl;
	@Value("${mess.map.localCoordinate}")
	private String localCoordinate;
	@Value("${mess.map.flag}")
	private String mapFlag;


	@Value("${map.basic.baseMapType}")
	private String basicBaseMapType;

	@Value("${map.basic.center}")
	private String basicCenter;

	@Value("${map.basic.extent}")
	private String basicExtent;

	@Value("${map.basic.coordinateType}")
	private String basicCoordinateType;

	@Value("${map.basic.mapProjection}")
	private String basicMapProjection;

	@Value("${map.basic.maxZoom}")
	private String basicMaxZoom;

	@Value("${map.basic.minZoom}")
	private String basicMinZoom;

	@Value("${map.basic.defaultZoom}")
	private String basicDefaultZoom;

	@Value("${map.basic.plotLayer}")
	private String basicPlotLayer;

	@Value("${map.basic.layerName}")
	private String basicLayerName;

	@Value("${map.basic.mapTileUrl}")
	private String basicMapTileUrl;


	@Value("${map.satellite.baseMapType}")
	private String satelliteBaseMapType;

	@Value("${map.satellite.center}")
	private String satelliteCenter;

	@Value("${map.satellite.extent}")
	private String satelliteExtent;

	@Value("${map.satellite.coordinateType}")
	private String satelliteCoordinateType;

	@Value("${map.satellite.mapProjection}")
	private String satelliteMapProjection;

	@Value("${map.satellite.maxZoom}")
	private String satelliteMaxZoom;

	@Value("${map.satellite.minZoom}")
	private String satelliteMinZoom;

	@Value("${map.satellite.defaultZoom}")
	private String satelliteDefaultZoom;

	@Value("${map.satellite.plotLayer}")
	private String satellitePlotLayer;

	@Value("${map.satellite.layerName}")
	private String satelliteLayerName;

	@Value("${map.satellite.mapTileUrl}")
	private String satelliteMapTileUrl;
    //到达现场距离
	@Value("${app.arrive.distance}")
	private String arriveDistance;



	/**
	 * 登录
	 */
	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	public Map<String, Object> login(@RequestBody Map<String,Object> bodyMap)throws IOException {
		//本项目已实现，前后端完全分离，但页面还是跟项目放在一起了，所以还是会依赖session
		//如果想把页面单独放到nginx里，实现前后端完全分离，则需要把验证码注释掉(因为不再依赖session了)
		try {
			Map<String, Object> paramMap = (Map<String, Object>) bodyMap.get("para");

			String userName = paramMap.get("userName").toString();
			String password = paramMap.get("pwd").toString();
			String macAddress = paramMap.getOrDefault("macAddress","").toString();

			//我们不用校验用户名和密码的长度
			if (userName.length() < 0 || password.length() < 0) {
				logger.error("账号(" + userName + ")账号或密码为空");
				return R.error(101,"账号或密码为空");
			}

			List<UserModel> userList = new ArrayList<>();
			//查询用户信息
			//1.基于警号搜索
			userList = smpUserService.selectUserByStaffCode(userName);

			if (userList.size() < 1) {
				//2.基于手机号搜索
				userList = smpUserService.selectUserByMobile(userName);
			}

			if (userList.size() < 1) {
				//3.基于用户名搜索
				userList = smpUserService.selectUserByUserCode(userName);
			}

            //判断用户是否存在
            if (userList == null || userList.size() == 0) {
                logger.error("账号(" + userName + ")不存在");
                return R.error(102,"此用户账号不存在");
            }

            //判断账号是否重复
            if (userList.size() > 1) {
                logger.info("账号(" + userName + ")重复");
                return R.error(104,"账号重复");
            }

            UserModel user = userList.get(0);

            //判断密码是否正确
            if (!user.getPassword().equals(MD5Util.EncryptStr(password))) {
                logger.info("账号(" + userName + ")的密码错误");
                return R.error(105,"密码错误");
            }

            //通过CAS验证用户名密码
			Map<String,String> casMap = new HashMap<>(3);
			casMap.put("userCode",user.getUserCode());
			casMap.put("passWord",password);
			casMap.put("systemNo","MPA");
			String casResponseJson = HttpRequest.sendGet("http://"+smpIp+":"+smpPort+casLogin,casMap);
			logger.info("casLogin result is ======"+casResponseJson);
			Map<String, Object> casResponseMap = null;
			if(casResponseJson != null && !"".equals(casResponseJson)) {
				casResponseMap = JSON.parseObject(casResponseJson, Map.class);
				if (casResponseMap != null) {
					String casStatus = casResponseMap.getOrDefault("status","1").toString();
					//成功
					if ("0".equals(casStatus)) {
						String tgtId = casResponseMap.get("tgtId").toString();
						//如果存在则替换，不存在则新增
						if (Constant.tgtMap.containsKey(user.getUserCode())) {
							Constant.tgtMap.replace(user.getUserCode(), tgtId);
						} else {
							Constant.tgtMap.put(user.getUserCode(), tgtId);
						}
					}
					//失败
					else {
						return R.error(107, "CAS登录异常");
					}
				}
			}

            //判断是否已经在其他设备登录 如果已经登录 向已经登录的设备发送mq，让其被踢下线
            String tokenOld = (String) Constant.tokenMapIns.get(user.getUserCode());
            String name = tokenOld;
            if (null != tokenOld) {
				String msg = "{\"code\":\"0\",\"msg\":\"已强制下线，账号在其他地方登录\",\"macAddress\":\""+macAddress+"\"}";
                logger.info("账号(" + userName + ")已在其他地方登录");

                MQParamModel param = new MQParamModel(msg, AccountMQ);
                param.setPersonalFlag(true);
                param.setQueueFlag(false);

                List<String> userNos = new ArrayList<>();
                userNos.add(tokenOld);
                param.setUserNos(userNos);

				//发送强制下线MQ
				publishToAPP.sendToApp(param);

                //将token失效
                Constant.tokenMapIns.remove(user.getUserCode());
                Constant.tokenMap.remove(tokenOld);
            }
			List<StaffModel> staffList = smpUserService.selectStaffByUserCode(user.getUserCode());
			if (null == staffList || staffList.size() == 0) {
				logger.info("账号(" + userName + ")无对应警员信息");
				return R.error(106,"无对应警员信息");
			}
			StaffForAppModel staffReturn = staffList.get(0).parseToAppModel();

			//将用户上线信息传给后台可视化
			LoginForVcs loginForVcs = new LoginForVcs();
			loginForVcs.setDEVICE_ID(staffList.get(0).getGuid());
			loginForVcs.setISONLINE("1");
			publishToVCS.notifyLoginToVCS(loginForVcs);

			long time = System.currentTimeMillis();
			staffReturn.setLoginTime(String.valueOf(time));

			//生成token，并保存到数据库
			R r = sysUserTokenService.createToken(user.getGuid());
			String token = r.get("token").toString();
			Constant.tokenMap.put(token, user.getUserCode());
			Constant.tokenMapIns.put(user.getUserCode(), token);

			//获取PUC调度台相关信息
			Map<String,Object> mapQuery = new HashMap<>(2);
			mapQuery.put("staffGuid",staffReturn.getStaffGuid());
			mapQuery.put("enableFlag",1);
			SmptSmartappInfo smptSmartappInfo = smptSmartappInfoService.selectSmptSmartappInfoByMap(mapQuery);
			//鉴定用户权限，若没有权限，提示用户
			/*
			List<SmpRoleFunctionInfo> smpRoleFunctionInfoList = smpRoleFunctionService.selectFunctionByuserCode(userName);
			if(smpRoleFunctionInfoList==null||smpRoleFunctionInfoList.size()==0){
				return R.error(109,"用户未绑定权限");
			}
			StringBuilder authorityStringBuilder = new StringBuilder();
			for(SmpRoleFunctionInfo smpRoleFunctionInfo : smpRoleFunctionInfoList){
				authorityStringBuilder.append(smpRoleFunctionInfo.getCode());
				authorityStringBuilder.append(",");
			}
			authorityStringBuilder.deleteCharAt(authorityStringBuilder.length()-1);
			*/
			Map<String, Object> map = new HashMap<String, Object>();
			//返回登录成功信息
			map.put("code", "1");
			map.put("info", staffReturn);
			map.put("token", token);
			map.put("userCode", user.getUserCode());
			map.put("gpsTime", appGpsInterval);
			map.put("mqLoginAccount", jmsAndroidUsername);
			map.put("mqPassword", jmsAndroidPassword);
			map.put("mqIp", jmsAndroidUrl);
			map.put("mrpsIp",mcontrolIp);
			map.put("mrpsPort",mcontrolPort);
			map.put("httpTimeOut", appHttpTimeout);
			map.put("vcsVersion",VCS_VERSION);
			map.put("connSystem",CONN_SYSTEM);
			map.put("systemFunction",SYSTEM_FUNCTION);
			map.put("updateAddress",updateAddress);
			//cmd必须回传
			map.put("cmd",bodyMap.get("cmd"));
			map.put("userGuid",user.getGuid());
			map.put("pucServerIp",pucServerIp);
			map.put("pucServerPort",pucServerPort);
			map.put("pucWsIp",pucWsIp);
			map.put("pucWsPort",pucWsPort);
			map.put("mapUrl",mapUrl);
			map.put("mapFlag",mapFlag);
			map.put("localCoordinate",localCoordinate);
			map.put("smartAppInfo",smptSmartappInfo);
			map.put("arriveDistance",arriveDistance);


			//新增地图配置，将地图配置返回前台
			//标准地图
			Map<String,Object> basicMap = new HashMap<>(12);
			basicMap.put("baseMapType",basicBaseMapType);
			basicMap.put("center",basicCenter);
			basicMap.put("extent",basicExtent);
			basicMap.put("coordinateType",basicCoordinateType);
			basicMap.put("mapProjection",basicMapProjection);
			basicMap.put("maxZoom",basicMaxZoom);
			basicMap.put("minZoom",basicMinZoom);
			basicMap.put("defaultZoom",basicDefaultZoom);
			basicMap.put("plotLayer",basicPlotLayer);
			basicMap.put("layerName",basicLayerName);
			basicMap.put("mapTileUrl",basicMapTileUrl);

			//卫星地图
			Map<String,Object> satelliteMap = new HashMap<>(12);
			satelliteMap.put("baseMapType",satelliteBaseMapType);
			satelliteMap.put("center",satelliteCenter);
			satelliteMap.put("extent",satelliteExtent);
			satelliteMap.put("coordinateType",satelliteCoordinateType);
			satelliteMap.put("mapProjection",satelliteMapProjection);
			satelliteMap.put("maxZoom",satelliteMaxZoom);
			satelliteMap.put("minZoom",satelliteMinZoom);
			satelliteMap.put("defaultZoom",satelliteDefaultZoom);
			satelliteMap.put("plotLayer",satellitePlotLayer);
			satelliteMap.put("layerName",satelliteLayerName);
			satelliteMap.put("mapTileUrl",satelliteMapTileUrl);

			Map<String,Object> mapMap = new HashMap<>(2);
			mapMap.put("basicMap",basicMap);
			mapMap.put("satelliteMap",satelliteMap);

			map.put("map",mapMap);
			//返回权限信息
			map.put("authorizationInfo",casResponseMap);

			logger.info("账号(" + userName + ")登录成功");
			logger.info("传给app的map" +map.toString());

			return R.ok(map);
		}catch(Exception e){
			e.printStackTrace();
			return R.error(200,"服务异常");
		}
	}


	/**
	 * 退出
	 */
	@RequestMapping(value = "/sys/logout", method = RequestMethod.POST)
	public R logout() {
		try {
			SmpUserInfo smpUserInfo = getUser();
			SysUserTokenEntity sysUserTokenEntity = sysUserTokenService.queryByUserId(getUserId());
			String token = sysUserTokenEntity.getToken();

			Map<String, Object> map = new HashMap<String, Object>();

			String userCode = (String) Constant.tokenMap.get(token);
			if(userCode != null) {
				List<StaffModel> staffList = smpUserService.selectStaffByUserCode(userCode);

				//删掉token
				if (Constant.tokenMap != null && Constant.tokenMap.containsKey(token)) {
					Constant.tokenMap.remove(token);
				}
				if (userCode != null && Constant.tokenMapIns != null && Constant.tokenMapIns.containsKey(userCode)) {
					Constant.tokenMapIns.remove(userCode);
				}

				//将用户下线信息传给后台可视化
				LoginForVcs loginForVcs = new LoginForVcs();
				loginForVcs.setDEVICE_ID(staffList.get(0).getGuid());
				loginForVcs.setISONLINE("0");
				publishToVCS.notifyLoginToVCS(loginForVcs);
			}
			sysUserTokenService.logout(getUserId());
			//CAS登出
			Map<String,Object> casMap = new HashMap<>(2);
			if(Constant.tgtMap.containsKey(smpUserInfo.getUserCode())) {
				casMap.put("tgtId", Constant.tgtMap.get(smpUserInfo.getUserCode()));
				casMap.put("systemNo", "MPA");
				String casJson = JSON.toJSONString(casMap);
				HttpRequest.sendPost("http://" + smpIp + ":" + smpPort + casLogout, casJson);
			}
			//返回注销成功信息
			map.put("code", "1");
			logger.info("用户(" + userCode + ")注销成功");
			return R.ok(map);
		} catch (Exception e) {
			logger.error("UserBusiness.logout方法异常:" + e.getMessage());
			e.printStackTrace();
			return R.error(10, "系统异常");
		}
	}

	@RequestMapping(value = "/sys/getUserRoleInfo", method = RequestMethod.POST)
	public R getUserRoleInfo(@RequestBody Map<String,Object> bodyMap) {
		SmpUserInfo smpUserInfo = getUser();
		String smpUserRoleUrl = "http://"+smpIp+":"+smpPort+"/smp/authorityInfo/smpAuthIntf/"+smpUserInfo.getUserCode()+"/MPA";
		String casResponseJson = HttpRequest.sendPost(smpUserRoleUrl,"");
		logger.info("smpAuthIntf result is ======"+casResponseJson);
		List<Map<String,Object>> listMap = new ArrayList<>();
		if(casResponseJson != null && !"".equals(casResponseJson)) {
			JSONArray jsonArray = JSON.parseObject(casResponseJson,JSONArray.class);
			if(jsonArray!=null && jsonArray.size()>0){
				JSONObject casResObj = jsonArray.getJSONObject(0);
				listMap = (List<Map<String,Object>>)casResObj.get("dataInfoList");

			}
		}
		return R.ok().put("dataInfoList",listMap);
	}
}
