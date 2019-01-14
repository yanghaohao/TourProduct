package com.example.admin.tourproduct.util;

import android.content.Context;

import com.example.admin.tourproduct.view.ShapeLoadingDialog;

public class ShapeLoadingDialogUtil {

    private static ShapeLoadingDialogUtil mShapeLoadingDialogUtil;
    private ShapeLoadingDialog mShapeLoadingDialog;
    private Context mContext;

    private ShapeLoadingDialogUtil(Context mContext) {
        this.mContext = mContext;
    }

    public ShapeLoadingDialog buildDialog(Context mContext,String loadText){
        mShapeLoadingDialog = new ShapeLoadingDialog.Builder(mContext)
                .loadText(loadText)
                .build();
        return mShapeLoadingDialog;
    }

    public void showProgressDialog(){
        mShapeLoadingDialog.show();
    }

    public void cancelProgressDialog(){
        mShapeLoadingDialog.cancel();
    }

    //单例,懒汉式
    public static ShapeLoadingDialogUtil getShapeLoadingDialogUtils(Context context) {
        if (mShapeLoadingDialogUtil == null) {
            synchronized(SharedUtils.class) {
                if (mShapeLoadingDialogUtil == null) {
                    mShapeLoadingDialogUtil = new ShapeLoadingDialogUtil(context);
                }
            }
        }
        return mShapeLoadingDialogUtil;
    }
}
