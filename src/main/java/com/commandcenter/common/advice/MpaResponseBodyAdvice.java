package com.commandcenter.common.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.utils.R;
import com.commandcenter.common.utils.encrypt.EncryptUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author r25437
 * @create 2018-09-10 16:13
 * @desc 返回统一拦截器 统一加密
 **/
@ControllerAdvice(basePackages = "com.commandcenter.controller")
public class MpaResponseBodyAdvice implements ResponseBodyAdvice{
    private final static Logger logger = LoggerFactory.getLogger(MpaResponseBodyAdvice.class);

    @Override
    public Object beforeBodyWrite(Object returnValue, MethodParameter methodParameter,
                                  MediaType mediaType, Class clas, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        //统一修改返回值/响应体
        //返回修改后的值
        try {
            String body = IOUtils.toString(IOUtils.toByteArray(request.getInputStream()),"utf-8");
            String cmdStr = "";
            if(!"".equals(body.trim())){
                //获取cmd
                JSONObject jsonObject = JSONObject.parseObject(body);
                cmdStr = jsonObject.getString("cmd");
            }
            logger.debug("reponse get request input ================ "+body);

            //将返回值returnValue转成需要的类型Map<?>  方便统一修改其中的某个属性
            Map<String,Object> msg=(Map<String,Object>) returnValue;
            //统一返回cmd
            msg.put("cmd",cmdStr);

            //进行加密返回
            //先将传入的map转换为json串
            String json = JSON.toJSONString(msg);
//            R r = new R();
//            r.put("param",json);
            R r = EncryptUtil.encryptInput(json);
            return r;
        }catch (Exception e){
            e.printStackTrace();
            return new R();
        }
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class clas) {
        //获取当前处理请求的controller的方法
        String methodName=methodParameter.getMethod().getName();
        // 不拦截/不需要处理返回值 的方法
        //如登录
        String method= "loginCheck";
        //不拦截
        return !method.equals(methodName);
    }
}
