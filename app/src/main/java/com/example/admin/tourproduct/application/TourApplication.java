package com.example.admin.tourproduct.application;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.baidu.mapapi.SDKInitializer;
import com.example.admin.tourproduct.util.LogUtil;


public class TourApplication extends Application {
    private static final String TAG = "JIGUANG-Example";
    public static Context context;
    public static TourApplication MainInstance;
    public static TourApplication getInstance() {
        return MainInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        LogUtil.d(TAG, "[ExampleApplication] onCreate");
        MainInstance = this;
        context = this;

        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext
        // 注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(TourApplication.this);

        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

    }
}
