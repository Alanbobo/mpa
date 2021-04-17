package com.commandcenter.common.advice;

import com.alibaba.fastjson.JSONObject;
import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.encrypt.AesEncryptUtil;
import com.commandcenter.common.utils.encrypt.Base64;
import com.commandcenter.common.utils.encrypt.RSAEncrypt;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * @author r25437
 * @create 2018-09-11 9:02
 * @desc requestAdvice 统一解密
 **/
//@ControllerAdvice(basePackages = "com.commandcenter.controller")
public class MpaRequestBodyAdvice implements RequestBodyAdvice {
    private final static Logger logger = LoggerFactory.getLogger(MpaRequestBodyAdvice.class);
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        try {
            return new MpaHttpInputMessage(inputMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return inputMessage;
        }
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    class MpaHttpInputMessage implements HttpInputMessage {
        private HttpHeaders headers;

        private InputStream body;

        public MpaHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
            this.headers = inputMessage.getHeaders();
            String inputBody = IOUtils.toString(inputMessage.getBody(),"UTF-8");
            //解密
            if(!"".equals(inputBody)) {
                JSONObject jsonObject = JSONObject.parseObject(inputBody);
                String aesString = jsonObject.getString("param");

                //验证签名 本次不验证签名
//            if(!checkSign(jsonObject.getString("sign"))){
//                return R.error(101,"签名无效");
//            }

                //获取动态秘钥
                //私钥解密过程
                String saltBase64 = jsonObject.getString("salt");
                byte[] res= RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(Constant.getKeyPath())), Base64.decode(saltBase64));
                String salt = new String(res);


                //解密
                aesString = AesEncryptUtil.aesDecrypt(aesString,salt+ Constant.ENCRYPT_KEY);

                logger.info("input param="+aesString);
                this.body = IOUtils.toInputStream(aesString,"UTF-8");
            }else{
                this.body = IOUtils.toInputStream("","UTF-8") ;
            }
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
}
