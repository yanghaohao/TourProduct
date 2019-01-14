package com.example.admin.tourproduct.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by YH on 2018/4/17.
 */

public class FontsUtil {

    private static FontsUtil mFontsUtil;
    private Context mContext;

    private FontsUtil(Context context) {
        this.mContext = context;
    }

    //单例,懒汉式
    public static FontsUtil getFontsUtils(Context context) {
        if (mFontsUtil == null) {
            synchronized(FontsUtil.class) {
                if (mFontsUtil == null) {
                    mFontsUtil = new FontsUtil(context);
                }
            }
        }
        return mFontsUtil;
    }

    public void setTextViewFonts(TextView textViewFonts){
        Typeface face = Typeface.createFromAsset (  mContext.getAssets() , "fonts/FZXKJW.TTF");
        textViewFonts.setTypeface (face);
    }
}
