package com.commandcenter.common.utils;
/**
 * @author r25437
 * @create 2018-07-27 9:30
 * @desc 原生方法调用测试
 **/
public class NativeMethod {
    public native int testInput(String str);

    public native int platform_Init();

    public native int platform_Release();

    public native int platform_Login(String userName,String passwd,String host,int port);

    public native int platform_Logout();

    public native int platform_SceneOperation(String screen_id,String operation,int scene_id,String scene_name);

    public native String platform_QuerySceneList(String screen_id);
}
