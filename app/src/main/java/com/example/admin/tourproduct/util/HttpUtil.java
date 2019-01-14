package com.example.admin.tourproduct.util;

public class HttpUtil {

    /**
     * 当前的地址，图片网址
     */
    public static final String DRAWABLE_RES = "http://203.195.237.243:8725/";
    public static final String DELICACY_DRAWABLE = "http://203.195.237.243";

    /**
     * 获取最新的版本
     */
    public static final String GET_LAST_VERSION = "http://203.195.237.243/api/Version/GetLastVersion";

    /**
     * 游记保存图片
     */
    public static final String UP_IMAGE = "http://203.195.237.243/API/TravelNotes/SaveBitmaps";

    /**
     * 登陆
     */
    public static final String LOGIN = "http://203.195.237.243/API/User/Login";

    /**
     * 获取攻略列表
     */
    public static final String STRATEGY = "http://203.195.237.243/API/TravelGuide/GetTravelGuidePaged";

    /**
     * 获取美食商户列表
     */
    public static final String DELICACY = "http://203.195.237.243/API/BusinessShop/GetBusinessShopPaged";

    /**
     * 注册
     */
    public static final String ENROLL = "http://203.195.237.243/API/User/Register";

    /**
     * 保存游记文字信息
     */
    public static final String SAVE_TRAVEL_NOTE = "http://203.195.237.243/API/TravelNotes/SaveTravelNotes";

    /**
     * 获取游记列表
     */
    public static final String TRAVEL = "http://203.195.237.243/API/TravelNotes/GetTravelNotesPaged";

    /**
     * 保存评论
     */
    public static final String SAVE_TRAVEL_DISCUSS = "http://203.195.237.243/API/TravelNotes/SaveDiscuss";

    /**
     * 根据id获取游记
     */
    public static final String GET_TRAVEL_BY_ID = "http://203.195.237.243/API/TravelNotes/QueryTravelNotesById";

    /**
     * 删除游记
     */
    public static final String DETELE_TRAVEL = "http://203.195.237.243/API/TravelNotes/DeleteTravelNotes";
    /**
     * 保存美食评论
     */
    public static final String ADD_DELICACY_DISCUSS = "http://203.195.237.243/API/BusinessShop/AddComment";
    /**
     * 获取全部游记
     */
    public static final String GET_ALL_TRAVELS = "http://203.195.237.243/API/TravelGuide/GetTravelGuideAll";
    /**
     * 获取全部游记
     */
    public static final String GET_ALL_AD = "http://203.195.237.243/API/Advertisement/GetAdvertisementAll";
    /**
     * 根据id获取攻略
     */
    public static final String GET_STRATEGY_BY_ID = "http://203.195.237.243/API/TravelGuide/QueryTravelGuideById";
    /**
     * 分配优惠券给用户
     */
    public static final String ALLOT_COUPON = "http://203.195.237.243/API/BusinessShop/AllotCoupon";
    /**
     * 根据id获取优惠券
     */
    public static final String GET_COUPON_BY_ID = "http://203.195.237.243/API/BusinessShop/QueryCouponById";
    /**
     * 获取所有消息
     */
    public static final String GET_MESSAGE = "http://203.195.237.243/API/Message/GetMessagePaged";

}
