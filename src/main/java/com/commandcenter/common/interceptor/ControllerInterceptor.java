package com.commandcenter.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

/**
 * @author r25437
 * @create 2018-08-01 10:12
 * @desc controller统一拦截器
 **/
public class ControllerInterceptor implements HandlerInterceptor {
    private static NamedThreadLocal<Long> startTimeThreadLocal =   new NamedThreadLocal<Long>("startTime");
    private String timeStamp = "";
    private static Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    /**
     * 生成全局唯一的requestid
     */
    public static final String REQUEST_ID_KEY = "requestId";
    public static final String REQUEST_NAME_KEY = "requestName";

    private static final String GPS_REQUEST = "gpsReport";
    private static final String WEB_REQUEST = "webController";
    private static final String LOGIN_REQUEST = "login";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 对来自后台的请求统一进行日志处理
         */
        Map<String,String []> inputParams = request.getParameterMap();
        if(inputParams.containsKey("timestamp")){
            timeStamp = inputParams.get("timestamp").toString();
        }
        //统一获取requestid
        String requestId = getRequestId(request);
        MDC.put(REQUEST_ID_KEY,requestId);
        MDC.put(REQUEST_NAME_KEY,request.getRequestURI());
        startTimeThreadLocal.set(System.currentTimeMillis());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        long startTime = startTimeThreadLocal.get();
        long endTime = System.currentTimeMillis();
        long consumeTime = endTime - startTime;
        String URI = httpServletRequest.getRequestURI();
        String body = IOUtils.toString(IOUtils.toByteArray(httpServletRequest.getInputStream()),"utf-8");
        String staffGuid = "";
        String userName = "";
        if(body!=null && !"".equals(body.trim())) {
            JSONObject jsonObject = JSONObject.parseObject(body);
            if (jsonObject != null){
                staffGuid = jsonObject.getString("staffGuid");
            }
            //如果staffGuid为空，则证明应该是登录操作。验证如果是登录操作，获取userName
            if((staffGuid==null || "".equals(staffGuid)) && jsonObject != null){
                if(URI.contains(LOGIN_REQUEST)){
                    userName = jsonObject.getJSONObject("para").getString("userName");
                }
            }
        }
        if(!URI.contains(GPS_REQUEST)&&!URI.contains(WEB_REQUEST)) {
            if(staffGuid==null || "".equals(staffGuid)) {
                logger.info(URI + " consumeTime = " + consumeTime + " ms ,userName=" + userName);
            }else{
                logger.info(URI + " consumeTime = " + consumeTime + " ms ,staffGuid=" + staffGuid);
            }
        }
        MDC.clear();
    }

    public static String getRequestId(HttpServletRequest request) {
        String requestId = null;
        String parameterRequestId = request.getParameter(REQUEST_ID_KEY);
        String headerRequestId = request.getHeader(REQUEST_ID_KEY);

        if (parameterRequestId == null && headerRequestId == null) {
            requestId = UUID.randomUUID().toString();
        } else {
            requestId = parameterRequestId != null ? parameterRequestId : headerRequestId;
        }
        return requestId;
    }
}
