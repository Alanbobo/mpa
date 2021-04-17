package com.commandcenter.common.utils;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author r25437
 * @create 2018-08-01 10:20
 * @desc GUID生成
 **/
public class GUID implements Serializable {

    private static String guid;

    public static String getGuid() {
        guid = UUID.randomUUID().toString();
        return guid;
    }
}
