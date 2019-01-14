package com.example.admin.tourproduct.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.util.AnimationUtil;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.LocalPlaceData;
import com.example.admin.tourproduct.util.LogUtil;
import com.example.admin.tourproduct.util.SharedUtils;
import com.example.admin.tourproduct.view.ShapeLoadingDialog;

import base.activity.BaseActivity;

public class MapActivity extends BaseActivity {

    private BaiduMap mBaidumap;
    private ShapeLoadingDialog mShapeLoadingDialog;
    private GeoCoder mSearch;
    private LocationClient mLocationClient;
    private MapView mMapView;
    private LatLng cenpt1;
    private LatLng destination;
    private SharedUtils sharedUtils;
    private String address;
    OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
        public void onGetGeoCodeResult(GeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有检索到结果
                getmap(cenpt1);
                setMapDrawable(cenpt1,R.mipmap.eoh);
                mShapeLoadingDialog.cancel();
            }
            if (result!=null){
                destination = result.getLocation();
                Log.e("onGetGeoCodeResult: ",destination + "" );
                LogUtil.e("地图",result.getLocation().longitude+"\t"+result.getLocation().latitude+"\t"+destination.latitudeE6+"\t"+destination.longitudeE6);
            }else {
                Toast.makeText(MapActivity.this, "没有获取到数据请退出后重试", Toast.LENGTH_SHORT).show();
            }
            //获取地理编码结果
            getmap(destination);
            setMapDrawable(destination,R.mipmap.map_pin);
            setMapDrawable(cenpt1,R.mipmap.eoh);
            mShapeLoadingDialog.cancel();

        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有找到检索结果
            }
            //获取反向地理编码结果
            if (result!=null){
                destination = result.getLocation();
                LogUtil.e("地图",result.getLocation().longitude+"\t"+result.getLocation().latitude+"\t"+destination.latitudeE6+"\t"+destination.longitudeE6);
            }else {
                Toast.makeText(MapActivity.this, "没有获取到数据请退出后重试", Toast.LENGTH_SHORT).show();
            }

        }
    };


    @Override
    protected void intiView() {

        hideActionBar();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            address = bundle.getString(Constant.MAP);
        else
            address = "";
        mLocationClient = new LocationClient(MapActivity.this);
        if (Build.VERSION.SDK_INT>=23){
            showContacts();
        }else{
            init();//init为定位方法
        }

        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(listener);
        mSearch.geocode(new GeoCodeOption()
                .city("成都市")
                .address(address));


        mShapeLoadingDialog =new ShapeLoadingDialog.Builder(this)
                .loadText(loadText)
                .build();
        mShapeLoadingDialog.show();

        //初始化地图
        mMapView = (MapView) findViewById(R.id.map);
        mBaidumap = mMapView.getMap();

        sharedUtils = SharedUtils.getSharedUtils(this);
        cenpt1 = new LatLng(Double.valueOf(sharedUtils.readString(Constant.LATITUDE)),Double.valueOf(sharedUtils.readString(Constant.LONGITUDE)));
        LogUtil.e("经纬度转换",sharedUtils.readString(Constant.LATITUDE)+"\t"+sharedUtils.readString(Constant.LONGITUDE));

        TextView tvMessagePic = findViewById(R.id.tv_message_pic);
        tvMessagePic.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_message_pic:
                    AnimationUtil animationUtil = AnimationUtil.getFontsUtils(MapActivity.this);
//                    if (animationUtil.isAvilible("com.autonavi.minimap")){
//                        LogUtil.e("地图","已经安装高德地图");
//                        double[] start = animationUtil.bdToGaoDe(cenpt1.latitude,cenpt1.longitude);
//                        double[] end = animationUtil.bdToGaoDe(destination.latitude,destination.longitude);
//                        LogUtil.e("地图",destination.latitude+"\t"+destination.longitude);
//                        animationUtil.openGaoDeMap(start[0],start[1],sharedUtils.readNews(Constant.PLACE),end[0],end[1],address);
//                    }else
                        if (animationUtil.isAvilible("com.baidu.BaiduMap")){
                        LogUtil.e("地图","已经安装百度地图");
                        animationUtil.openBaiduMap(cenpt1.latitude,cenpt1.longitude,sharedUtils.readString(SharedUtils.PLACE)
                                ,destination.latitude,destination.longitude,address,"成都市");
                    }else {
                        showSnackBar(mMapView,"没有安装百度地图，请安装后再试",2000);
                    }
                    break;
            }
        }
    };

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_map);
    }


    public void showContacts(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            showShortToast("没有权限,请手动开启定位权限");
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE}, 100);
        }else{
            init();
        }
    }

    private void init(){
        LocalPlaceData localPlaceData = LocalPlaceData.getSharedUtils(this);
        localPlaceData.bindListener(mLocationClient);
    }

    private void getmap(LatLng cenpt){
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaidumap.setMapStatus(mMapStatusUpdate);
    }

    private void setMapDrawable(LatLng cenpt,int drawableRes){
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(drawableRes);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(cenpt)
                .icon(bitmap);
        mBaidumap.addOverlay(option);
    }
}
