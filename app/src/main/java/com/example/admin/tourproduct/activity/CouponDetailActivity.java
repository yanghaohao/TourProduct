package com.example.admin.tourproduct.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.entry.Delicacy;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.HttpUtil;
import com.example.admin.tourproduct.util.QRCodeUtil;

import base.activity.BaseActivity;

public class CouponDetailActivity extends BaseActivity{

    @Override
    protected void intiView() {

        hideActionBar();
        showActionBar(true,false,"优惠券",null);
        Bundle bundle = getIntent().getExtras();
        Delicacy.EntityBean.CouponListBean coupon = (Delicacy.EntityBean.CouponListBean) bundle.getSerializable(Constant.MY_COUPON);
        ImageView ivCouponDetail = findViewById(R.id.iv_coupon_detail);
        Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(HttpUtil.GET_COUPON_BY_ID+"?Id="+coupon.getId(), 480, 480);
        ivCouponDetail.setImageBitmap(mBitmap);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_coupon_detail);
    }
}
