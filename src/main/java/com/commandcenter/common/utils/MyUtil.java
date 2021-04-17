package com.commandcenter.common.utils;

import java.util.Date;

/**
 * @author r25437
 * @create 2018-09-06 14:09
 * @desc 工具类
 **/
public class MyUtil {
    /**
     * 检查字符串是否为null，为null则返回给定的val值
     *
     * @param source
     * @param val
     * @return
     */
    public static String checkNullString(String source, String val) {
        return (source == null) ? val : source;
    }
    /**
     * 将接收到的date类型json转换为date
     *
     * @param date
     * @return
     */
    public static Date JsonParseDate(String date){
        if(date==null||"".equals(date)){
            return null;
        }else{
            date=date.replace("/Date(","").replace(")/","");
            String dateTime = date.substring(0,date.length()-5);
            Date newDate = new Date(Long.parseLong(dateTime));
            return newDate;
        }
    }
}

