package com.example.admin.tourproduct.util;

/**
 * Created by YH on 2018/4/25.
 */

public class Constant {

    public final static String APP_NAME = "途哟";

    //设置是什么请求方式
    public final static int NO = 0;
    public final static int POST = 1;
    public final static int GET = 2;

    //设置是否为第一次或者登陆
    public final static String FIRST = "first";
    //设置登陆
    public final static String IS_LOGIN = "login";

    //广告位的状态
    public final static int UPDATE_VIEW_PAGER_ITEM = 1;
    public final static int VIEW_PAGER_STOP = 2;
    public final static int VIEW_PAGER_RESUME = 3;
    public final static int VIEW_PAGER_PERSON_CHANGE = 4;

    //更新时间
    public final static int UPDATE_TIME = 3000;

    //参数传递
    public final static String STRATEGY_BUNDLE = "攻略";
    public final static String STRATEGY_DETAIL_BUNDLE = "攻略详情";
    public final static String PANORAMA_BUNDLE = "全景";
    public final static String DELICACY_BUNDLE = "美食";
    public final static String DELICACY_DETAIL_BUNDLE = "美食详情";
    public final static String TRAVEL_BUNDLE = "游记";
    public final static String TRAVEL_DETAIL_BUNDLE = "游记详情";
    public final static String MAP = "地图";
    public final static String PAN = "全景图片";
    public final static String CONPON = "优惠券";
    public final static String CONPON_PICTURE = "优惠券图片";
    public final static String SHOPID = "店铺号";
    public final static String AD_PICTURE = "广告图片";
    public final static String MY_COUPON = "我的优惠券";
    public final static String GROUP = "团购";

    //请求控制器
    public final static int LOGIN = 0;
    public final static int HOME_FEATURE_SPOT = 1;
    public final static int STRATEGY = 2;
    public final static int DELICACY = 3;
    public final static int NOTICE = 4;
    public final static int ENROLL = 5;
    public final static int ADDTRAVEL = 6;
    public final static int TRAVEL = 7;
    public final static int SAVE_TRAVEL_DISCUSS = 8;
    public final static int GET_TRAVEL_BY_ID = 9;
    public final static int SAVE_DELICACY_DISCUSS = 10;
    public final static int GET_ALL_AD = 11;
    public final static int GET_STRATEGY_BY_ID = 12;
    public final static int ADD_COUPON = 13;
    public final static int UPDATE_DATA = -1;
    public final static int FAIL = -1;

    //状态
    public final static int STATUS_NORMAL = 1;
    public final static int STATUS_UNUSUAL = -1;
    public final static int UPDATE = -2;
    public final static int STATUS_UPDATE_UNUSUAL = -4;
    public final static int NOTUPDATE = -3;
    public final static int ISLOGIN = 10;
    public final static int NotLOGIN = -10;
    public final static int HOME_STRATEGY_STATUS_NORMAL = 11;
    public final static int HOME_STRATEGY_STATUS_UNUSUAL = -11;
    public final static int HOME_TRAVEL_STATUS_NORMAL = 12;
    public final static int HOME_TRAVEL_STATUS_UNUSUAL = -12;
    public final static int HOME_DELICACY_STATUS_NORMAL = 13;
    public final static int HOME_DELICACY_STATUS_UNUSUAL = -13;
    public final static int HOME_AD_STATUS_NORMAL = 14;
    public final static int HOME_AD_STATUS_UNUSUAL = -14;

    //经纬度
    public final static String  LONGITUDE = " longitude";
    public final static String  LATITUDE = "latitude";
}
