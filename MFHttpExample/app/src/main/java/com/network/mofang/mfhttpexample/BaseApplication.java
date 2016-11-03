package com.network.mofang.mfhttpexample;

import android.app.Application;

import com.mfzp.network.MFHttpManager;
import com.mfzp.network.utils.MFHttpConfiguration;

import java.util.HashMap;

/**
 * Created by mofang on 2016/10/21.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        HashMap mfmap = new HashMap<>();
        mfmap.put("device", "GT-I9500");
        mfmap.put("os", "Android");
        mfmap.put("osVersion", "5.0.1");
        mfmap.put("appVersion", "2.0.2");
        mfmap.put("download_chanle", "yingyongbao");


        MFHttpConfiguration configuration = MFHttpConfiguration.Builder(this).setBaseUrl("http://101.201.57.61:3261").addCommParams("http://101.201.57.61:3261", mfmap);
        MFHttpManager.instance(this).setConfiguration(configuration);
    }
}
