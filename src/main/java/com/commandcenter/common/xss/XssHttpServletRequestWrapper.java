package com.commandcenter.common.xss;

import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.encrypt.AesEncryptUtil;
import com.commandcenter.common.utils.encrypt.Base64;
import com.commandcenter.common.utils.encrypt.RSAEncrypt;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * XSS过滤处理
 * @author r25437
 * @date 2017-04-01 11:29
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final static Logger logger = LoggerFactory.getLogger(XssHttpServletRequestWrapper.class);

    /**
     * 没被包装过的HttpServletRequest（特殊场景，需要自己过滤）
     */
    HttpServletRequest orgRequest;
    /**
     * html过滤
     */
    private final static HTMLFilter HTML_FILTER = new HTMLFilter();

    /**
     * webController不加密
     */
    private final static String WEB_CONTROLLER = "webController";

    /**
     * 保存流中的数据
     */
    private byte[] data;

    public XssHttpServletRequestWrapper(HttpServletRequest request) throws IOException{
        super(request);
        orgRequest = request;
        String URI = request.getRequestURI();
        data = IOUtils.toByteArray(request.getInputStream());
        try {
            if(!URI.contains(WEB_CONTROLLER)) {
                data = decryptData(data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        //非json类型，直接返回
        String type = super.getHeader(HttpHeaders.CONTENT_TYPE);
        if(type != null) {
            type = type.replaceAll(" ", "");
            if (!MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(type)) {
                return getInputStream(new ByteArrayInputStream(data));
            }
        }

        //为空，直接返回
        String json = IOUtils.toString(data, "utf-8");
        if (StringUtils.isBlank(json)) {
            return getInputStream(new ByteArrayInputStream(data));
        }

        //xss过滤
        json = xssEncode(json);
        data = json.getBytes("utf-8");
        final ByteArrayInputStream bis = new ByteArrayInputStream(data);
        return getInputStream(bis);
    }

    private ServletInputStream getInputStream(ByteArrayInputStream bis){
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bis.read();
            }
        };
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(xssEncode(name));
        if (StringUtils.isNotBlank(value)) {
            value = xssEncode(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameters = super.getParameterValues(name);
        if (parameters == null || parameters.length == 0) {
            return null;
        }

        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = xssEncode(parameters[i]);
        }
        return parameters;
    }

    @Override
    public Map<String,String[]> getParameterMap() {
        Map<String,String[]> map = new LinkedHashMap<>();
        Map<String,String[]> parameters = super.getParameterMap();
        for (String key : parameters.keySet()) {
            String[] values = parameters.get(key);
            for (int i = 0; i < values.length; i++) {
                values[i] = xssEncode(values[i]);
            }
            map.put(key, values);
        }
        return map;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(xssEncode(name));
        if (StringUtils.isNotBlank(value)) {
            value = xssEncode(value);
        }
        return value;
    }

    private String xssEncode(String input) {
        return HTML_FILTER.filter(input);
    }

    /**
     * 获取最原始的request
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * 获取最原始的request
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest request) {
        if (request instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) request).getOrgRequest();
        }

        return request;
    }

    /**
     * 解密
     */
    private byte[] decryptData(byte[] data) throws Exception{
        String inputBody = IOUtils.toString(data, "UTF-8");
        //解密
        if (!"".equals(inputBody)) {
            JSONObject jsonObject = JSONObject.parseObject(inputBody);
            String aesString = jsonObject.getString("param");

            //验证签名 本次不验证签名
//            if(!checkSign(jsonObject.getString("sign"))){
//                return R.error(101,"签名无效");
//            }

            //获取动态秘钥
            //私钥解密过程
            String saltBase64 = jsonObject.getString("salt");
            if(saltBase64!=null && !"".equals(saltBase64.trim())) {
                byte[] res = RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(Constant.getKeyPath())), Base64.decode(saltBase64));
                String salt = new String(res);


                //解密
                aesString = AesEncryptUtil.aesDecrypt(aesString, salt + Constant.ENCRYPT_KEY);

                logger.info("input param=" + aesString);
                jsonObject = JSONObject.parseObject(aesString);
                Map<String, Object> paramMap = jsonObject.toJavaObject(Map.class);
            }else{
                aesString = "";
            }
            return  aesString.getBytes("UTF-8");
        }else{
            return "".getBytes("UTF-8");
        }
    }

}
