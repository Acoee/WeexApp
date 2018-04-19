package com.app.weexapp.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;

import cn.com.acoe.weexapp.app.R;


/**
 * Created by Acoe on 2018/4/4.
 */

public class MainActivity extends AppCompatActivity {
    private WXSDKInstance mWXSDKInstance;
    private FrameLayout mContainer;
    private Handler mHandler;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContainer = (FrameLayout) findViewById(R.id.container);

        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.onActivityCreate();
        mWXSDKInstance.registerRenderListener(new IWXRenderListener() {
            @Override
            public void onViewCreated(WXSDKInstance wxsdkInstance, View view) {
                if (mContainer != null) {
                    mContainer.addView(view);
                }
            }

            @Override
            public void onRenderSuccess(WXSDKInstance wxsdkInstance, int i, int i1) {
            }

            @Override
            public void onRefreshSuccess(WXSDKInstance wxsdkInstance, int i, int i1) {
            }

            @Override
            public void onException(WXSDKInstance wxsdkInstance, String s, String s1) {
            }
        });


        // 在渲染前先要保证WXSDKEngine已经完成了初始化
        if (WXSDKEngine.isInitialized()) {
          startRender();
        } else {
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0) {
                        Log.i("Main", "WXSDKEngine.isInitialized: " + WXSDKEngine.isInitialized());
                        if (WXSDKEngine.isInitialized()) {
                            startRender();
                        } else {
                            msg = mHandler.obtainMessage(0);
                            mHandler.sendMessageDelayed(msg, 100);
                        }
                    }
                }
            };
            Message msg = mHandler.obtainMessage(0);
            mHandler.sendMessageDelayed(msg, 100);
        }
        Log.i("Main", "onCreate over");

    }

    /**
     * 开始执行渲染
     */
    private void startRender() {
        mWXSDKInstance.render("Main", WXFileUtils.loadAsset("app.js", this),
                null, null, WXRenderStrategy.APPEND_ASYNC);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }
}
