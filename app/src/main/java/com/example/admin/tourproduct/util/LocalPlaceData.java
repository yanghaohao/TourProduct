package com.example.admin.tourproduct.util;

import android.content.Context;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class LocalPlaceData {

    private static LocalPlaceData mLocalPlaceData;
    private Context mContext;
    private BDLocationListener mBDLocationListener;

    private LocalPlaceData(Context mContext) {
        this.mContext = mContext;
        mBDLocationListener = new LocalListener(mContext);
    }

    //单例,懒汉式
    public static LocalPlaceData getSharedUtils(Context context) {
        if (mLocalPlaceData == null) {
            synchronized (SharedUtils.class) {
                if (mLocalPlaceData == null) {
                    mLocalPlaceData = new LocalPlaceData(context);
                }
            }
        }
        return mLocalPlaceData;
    }

    private void initLocation(LocationClient mLocationClient) {
        mLocationClient.registerLocationListener(mBDLocationListener);
        LocationClientOption option = new LocationClientOption();
        //就是这个方法设置为true，才能获取当前的位置信息
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("gcj02");//可选，默认gcj02，设置返回的定位结果坐标系
        //int span = 1000;
        //option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        mLocationClient.setLocOption(option);
    }

    public void bindListener(LocationClient mLocationClient) {
        initLocation(mLocationClient);
        mLocationClient.start();
    }


}

