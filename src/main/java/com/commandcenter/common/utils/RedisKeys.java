package com.commandcenter.common.utils;

/**
 * Redis所有Keys
 *
 * @author r25437
 * @date 2017-07-18 19:51
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
