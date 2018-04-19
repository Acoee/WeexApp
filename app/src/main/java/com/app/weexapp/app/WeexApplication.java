package com.app.weexapp.app;

import android.app.Application;
import android.util.Log;

import com.app.weexapp.util.ImageAdapter;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;

/**
 * Application
 * Created by Acoe on 2018/4/4.
 */

public class WeexApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        InitConfig.Builder builder = new InitConfig.Builder();
        builder.setImgAdapter(new ImageAdapter());
        InitConfig config = builder.build();
        WXSDKEngine.initialize(this, config);
        Log.i("Application", "WXSDKEngine.isInitialized: " + WXSDKEngine.isInitialized());
    }
}