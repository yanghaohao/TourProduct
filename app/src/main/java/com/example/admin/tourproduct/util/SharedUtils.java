package com.example.admin.tourproduct.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by admin on 2018/4/12.
 */

public class SharedUtils {


    //shard保存信息
    public final static String CITY_PLACE = "定位城市";
    public final static String TITLE_RECOMMEND_PLACE = "攻略";
    public final static String HOT_PLACE = "游记";
    public final static String DELICACY_NAME = "美食";
    public final static String PLACE = "地点";
    public final static String USERNAME = "账号";
    public final static String PASSWORD = "密码";
    public final static String USERID = "账户id";
    public final static String IS_UPDATE_TRAVEL = "更新游记";
    public final static String IS_UPDATE_delicacy = "更新商户";


    public static SharedUtils mSharedUtils;
    Context mContext;


    public void saveString(String key, String set) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences("info", Context.MODE_PRIVATE).edit();
        editor.putString(key, set);
        editor.apply();
    }

    public String readString(String key) {
        return mContext.getSharedPreferences("info", Context.MODE_PRIVATE).getString(key, "");
    }

    public void saveBoolean(String key, boolean set) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences("info", Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, set);
        editor.apply();
    }

    public boolean readBoolean(String key) {
        return mContext.getSharedPreferences("info", Context.MODE_PRIVATE).getBoolean(key, false);
    }

    public void saveSetList(String key, Set set) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences("info", Context.MODE_PRIVATE).edit();
        editor.putStringSet(key, set);
        editor.apply();
    }

    public Set<String> readSetList(String key) {
        return mContext.getSharedPreferences("info", Context.MODE_PRIVATE).getStringSet(key, null);
    }

    public void saveInt(String key, int set) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences("info", Context.MODE_PRIVATE).edit();
        editor.putInt(key, set);
        editor.apply();
    }

    public int readUserId(String key) {
        return this.mContext.getSharedPreferences("info", Context.MODE_PRIVATE).getInt(key, 0);
    }

    private SharedUtils(Context context) {
        this.mContext = context;
    }

    //单例,懒汉式
    public static SharedUtils getSharedUtils(Context context) {
        if (mSharedUtils == null) {
            synchronized(SharedUtils.class) {
                if (mSharedUtils == null) {
                    mSharedUtils = new SharedUtils(context);
                }
            }
        }
        return mSharedUtils;
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences("info", Context.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.apply();
    }

    public void saveFloat(String key,float set){
        SharedPreferences.Editor editor = mContext.getSharedPreferences("info", Context.MODE_PRIVATE).edit();
        editor.putFloat(key, set);
        editor.apply();
    }

    public double readFloat(String key){
        return mContext.getSharedPreferences("info", Context.MODE_PRIVATE).getFloat(key, 0);
    }

}
