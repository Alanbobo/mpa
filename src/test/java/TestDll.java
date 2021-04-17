import com.commandcenter.common.utils.NativeMethod;

/**
 * @author r25437
 * @create 2018-07-27 11:05
 * @desc DLL调用测试
 * libPlatformSDK.dll请放置在C:\Windows\System32目录中，否则依赖的dll调用不到
 **/
public class TestDll {
    static {
        System.loadLibrary("libIfdsDLL");
    }

    public static void main(String[] args){
        NativeMethod nativeMethod = new NativeMethod();
        nativeMethod.testInput("CommandCenter");
        System.out.println("nativeMethod.testInput()=========="+nativeMethod.testInput("CommandCenter"));
        System.out.println("nativeMethod.platform_Init()=========="+nativeMethod.platform_Init());
        System.out.println("nativeMethod.platform_Login()=========="+nativeMethod.platform_Login("yang","111111","172.21.112.3",6000));
        System.out.println("nativeMethod.platform_QuerySceneList()=========="+nativeMethod.platform_QuerySceneList("1"));
        System.out.println("nativeMethod.platform_SceneOperation()=========="+nativeMethod.platform_SceneOperation("1","create",1,"test"));
        System.out.println("nativeMethod.platform_Logout()=========="+nativeMethod.platform_Logout());
        System.out.println("nativeMethod.platform_Release()=========="+nativeMethod.platform_Release());

    }
}
