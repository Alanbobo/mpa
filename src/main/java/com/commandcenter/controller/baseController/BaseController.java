package com.commandcenter.controller.baseController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.commandcenter.common.utils.*;
import com.commandcenter.common.utils.encrypt.AesEncryptUtil;
import com.commandcenter.common.utils.encrypt.Base64;
import com.commandcenter.common.utils.encrypt.RSAEncrypt;
import com.commandcenter.common.utils.encrypt.RSASignature;
import com.commandcenter.model.smp.SmpUserInfo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author r25437
 * @create 2018-08-02 10:31
 * @desc Controller基类
 **/
public class BaseController {

    public Page page = null;

    protected PageUtils getPageData(List<?> list){
        return new PageUtils(list, page!=null?page.getTotalCount():list.size(), page!=null?page.getPageSize():list.size(), page!=null?page.getPageNo():1);
    }

    protected void setPageDate(Map<String, Object> params,Map<String, Object> paramsMap){
        if (params.containsKey("pageCount")) {//分页
            page = createPage();
            int pageCount = new Integer(params.getOrDefault("pageCount","10").toString());
            int pageNumber = new Integer(params.getOrDefault("pageNumber","1").toString());
            page.setPageNo(pageNumber);
            page.setPageSize(pageCount);
            page.setBeginNum((pageNumber-1)*pageCount);
            page.setEndNum(pageNumber*pageCount);
            paramsMap.put("page",page);
        }
    }

    protected Page createPage(){
        return new Page();
    }

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SmpUserInfo getUser() {
        return (SmpUserInfo) SecurityUtils.getSubject().getPrincipal();
    }

    protected String getUserId() {
        return getUser().getGuid();
    }

    protected Map<String,Object> getRequestHeader(){
        //从header中获取token
        String version = getRequest().getHeader("version");

        Map<String,Object> headerMap = new HashMap<>();
        headerMap.put("version",version);
        return headerMap;
    }

    protected Map<String,Object> getRequestParam() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        //从param中获取data
        String paramJson = getRequest().getParameter("data");

        //解密
        paramJson = AesEncryptUtil.aesDecrypt(paramJson);

        Map<String,Object> paramMap = objectMapper.readValue(paramJson,Map.class);
        return paramMap;
    }

    protected Map<String,Object> getRequestBody() throws Exception{
//        BufferedReader reader = new BufferedReader(new InputStreamReader(getRequest().getInputStream()));
        BufferedReader reader = getRequest().getReader();

        String str, body = "";
        while((str = reader.readLine()) != null){
            body += str;
        }
//        String body = IOUtils.read(reader);
        if(!"".equals(body)) {
            JSONObject jsonObject = JSONObject.parseObject(body);
            String aesString = jsonObject.getString("param");

            //验证签名 本次不验证签名
//            if(!checkSign(jsonObject.getString("sign"))){
//                return R.error(101,"签名无效");
//            }

            //获取动态秘钥
            //私钥解密过程
            String saltBase64 = jsonObject.getString("salt");
            byte[] res=RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(Constant.getKeyPath())), Base64.decode(saltBase64));
            String salt = new String(res);


            //解密
            aesString = AesEncryptUtil.aesDecrypt(aesString,salt+ Constant.ENCRYPT_KEY);

            logger.info("input param="+aesString);
            jsonObject = JSONObject.parseObject(aesString);
            Map<String, Object> paramMap = jsonObject.toJavaObject(Map.class);

            return paramMap;
        }else{
            return null;
        }
    }

    private HttpServletRequest getRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        return request;
    }

    private HttpServletResponse getResponse(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
        return response;
    }

    private Boolean checkSign(String signstr){
        try {
            return RSASignature.doCheck(Constant.ENCRYPT_RSASIGN, signstr, RSAEncrypt.loadSignPublicKeyByFile(Constant.getKeyPath()));
        }catch (Exception e){
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

}