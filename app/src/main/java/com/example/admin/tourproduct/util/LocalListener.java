package com.example.admin.tourproduct.util;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

public class LocalListener implements BDLocationListener {

    private Context mContext;
    private final  String splitStr = "都";

    public LocalListener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        //Receive Location
        //经纬度
        double lati = bdLocation.getLatitude();
        double longa = bdLocation.getLongitude();
        //打印出当前位置
        Log.i("TAG", "location.getAddrStr()=" + bdLocation.getAddrStr());
        //打印出当前城市
        Log.i("TAG", "location.getCity()=" + bdLocation.getCity());
        String[] str = bdLocation.getCity().split(String.valueOf(bdLocation.getCity().charAt(bdLocation.getCity().length()-1)));

        SharedUtils sharedUtils = SharedUtils.getSharedUtils(mContext);
        sharedUtils.saveString(SharedUtils.CITY_PLACE,str[0]);

        LogUtil.e("经纬度",bdLocation.getLongitude()+"\t"+bdLocation.getLatitude());
        sharedUtils.saveString(Constant.LONGITUDE, bdLocation.getLongitude()+"");
        sharedUtils.saveString(Constant.LATITUDE, bdLocation.getLatitude()+"");
        sharedUtils.saveString(SharedUtils.PLACE,bdLocation.getAddrStr());

        //返回码
        int i = bdLocation.getLocType();
    }



}
