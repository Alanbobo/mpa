package com.commandcenter.common.wcf;

import com.commandcenter.common.utils.Constant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author r25437
 * @create 2018-09-07 11:07
 * @desc wcf调用
 **/
public class Network {
    private static volatile Network mInstance;
    private APi mApi;
    private final String WCF_IP = Constant.WCF_IP;

    private Network() {

    }

    public static Network getInstance() {
        if (mInstance == null) {
            synchronized (Network.class) {
                if (mInstance == null) {
                    mInstance = new Network();
                }
            }
        }
        return mInstance;
    }

    public APi getApi() {
        if (mApi == null) {
            synchronized (Network.class) {
                if (mApi == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(WCF_IP)
                            .build();
                    mApi = retrofit.create(APi.class);
                }
            }
        }

        return mApi;

    }
}
