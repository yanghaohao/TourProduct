package com.example.admin.tourproduct.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/4/13.
 */

public class AnimationUtil {

    private static AnimationUtil mAnimationUtil;
    private Context mContext;

    private AnimationUtil(Context context) {
        this.mContext = context;
    }

    //单例,懒汉式
    public static AnimationUtil getFontsUtils(Context context) {
        if (mAnimationUtil == null) {
            synchronized(AnimationUtil.class) {
                if (mAnimationUtil == null) {
                    mAnimationUtil = new AnimationUtil(context);
                }
            }
        }
        return mAnimationUtil;
    }

    /**
     * 打开百度地图
     * @param slat 开始地点 维度
     * @param slon 开始地点 经度
     * @param sname 开始地点 名字
     * @param dlat 终点地点 维度
     * @param dlon 终点地点 经度
     * @param dname 终点名字
     * @param city 所在城市- 动态获取 （例如：北京市）
     * @author jack
     * created at 2017/8/2 15:01
     */
    public void openBaiduMap(double slat, double slon, String sname,
                              double dlat, double dlon, String dname, String city) {
        try {
            String uri = getBaiduMapUri(String.valueOf(slat), String.valueOf(slon), sname,
                    String.valueOf(dlat), String.valueOf(dlon), dname, city, "");
            Intent intent = Intent.parseUri(uri, 0);
            mContext.startActivity(intent); //启动调用
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 百度地图定位经纬度转高德经纬度
     * @param bd_lat
     * @param bd_lon
     * @return
     */
    public double[] bdToGaoDe(double bd_lat, double bd_lon) {
        double[] gd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);
        gd_lat_lon[0] = z * Math.cos(theta);
        gd_lat_lon[1] = z * Math.sin(theta);
        return gd_lat_lon;
    }

    private String getBaiduMapUri(String originLat, String originLon, String originName, String desLat, String desLon, String destination, String region, String src){
        String uri = "intent://map/direction?origin=latlng:%1$s,%2$s|name:%3$s" +
                "&destination=latlng:%4$s,%5$s|name:%6$s&mode=driving&region=%7$s&src=%8$s#Intent;" +
                "scheme=bdapp;package=com.baidu.BaiduMap;end";
        return String.format(uri, originLat, originLon, originName, desLat, desLon, destination, region, src);
    }

    /**
     * 打开高德地图
     * @author jack
     * created at 2017/8/2 15:01
     */
    public void openGaoDeMap(double slat, double slon, String sname,double dlat, double dlon, String dname) {
        try {
            // APP_NAME  自己应用的名字
            String uri = getGdMapUri(Constant.APP_NAME, String.valueOf(slat), String.valueOf(slon),
                    sname, String.valueOf(dlat), String.valueOf(dlon), dname);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.autonavi.minimap");
            intent.setData(Uri.parse(uri));
            mContext.startActivity(intent); //启动调用
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取打开高德地图应用uri
     * style
     *导航方式(0 速度快; 1 费用少; 2 路程短; 3 不走高速；4 躲避拥堵；5
     *不走高速且避免收费；6 不走高速且躲避拥堵；
     *7 躲避收费和拥堵；8 不走高速躲避收费和拥堵)
     */
    public static String getGdMapUri(String appName, String slat, String slon, String sname, String dlat, String dlon, String dname){
        String newUri = "androidamap://navi?sourceApplication=%1$s&poiname=%2$s&lat=%3$s&lon=%4$s&dev=1&style=2";
        return String.format(newUri, appName, slat, slon, sname, dlat, dlon, dname);
    }


    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param
     * @param packageName 应用包名
     * @return
     */
    public boolean isAvilible( String packageName) {
        //获取packagemanager
        final PackageManager packageManager = mContext.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }


}
