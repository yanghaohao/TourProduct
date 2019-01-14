package com.example.admin.tourproduct.activity;

import android.view.View;

import com.example.admin.tourproduct.R;

import base.activity.BaseActivity;

public class MyCollectActivity extends BaseActivity {
    @Override
    protected void intiView() {
        hideActionBar();

        showActionBar(true,false,"我的收藏",null);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_my_collect);
    }

}
