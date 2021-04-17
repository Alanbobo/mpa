package com.commandcenter.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * @author r25437
 * @create 2019-05-16 16:09
 * @desc 属性获取
 **/
public class PropertiesUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    private PropertiesUtil() {

    }

    public static Properties propertie = null;

    static {
        propertie = new Properties();
        String configPath = System.getProperty("config.path");
        String configName = System.getProperty("spring.config.location",null);
        InputStream inputStream = null;
        try {
        if(configName != null && !"".equals(configName)){
            configName = URLDecoder.decode(configName,"utf-8");
            File file = new File(configName);
            inputStream = new FileInputStream(file);
        }else {
            configPath = URLDecoder.decode(configPath,"utf-8");
            File file = new File(configPath+"/application.properties");
            inputStream = new FileInputStream(file);
        }
        // 解决中文乱码
        BufferedReader buff = new BufferedReader(new InputStreamReader(inputStream));
        propertie.load(buff);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static String getValue(String key) {
        String value = (String) PropertiesUtil.propertie.get(key);
        if (StringUtils.isBlank(value)) {
            logger.info("value为空，请检查配置文件");
        }
        return value;
    }

}