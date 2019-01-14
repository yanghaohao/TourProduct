package com.example.admin.tourproduct.activity;

import android.view.View;

import com.example.admin.tourproduct.R;

import base.activity.BaseActivity;

public class DraftActivity extends BaseActivity {
    @Override
    protected void intiView() {
        hideActionBar();

        showActionBar(true,false,"草稿箱",null);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_draft);
    }

}
