package com.example.admin.tourproduct.util;

import android.util.Log;

public class LogUtil {

    public final static String TAG = "TourProduct";

    public static void v(String tag){
        Log.v(tag, "行进至此");
    }

    public static void v(String tag,String content){
        Log.v(tag, content);
    }

    public static void d(String tag){
        Log.d(tag, "行进至此");
    }

    public static void d(String tag,String content){
        Log.d(tag, content);
    }

    public static void i(String tag){
        Log.i(tag, "行进至此");
    }

    public static void i(String tag,String content){
        Log.i(tag, content);
    }

    public static void e(String tag){
        Log.e(tag, "行进至此");
    }

    public static void e(String tag,String content){
        Log.e(tag, content);
    }

    public static void w(String tag){
        Log.w(tag, "行进至此");
    }

    public static void w(String tag,String content){
        Log.w(tag, content);
    }
}
