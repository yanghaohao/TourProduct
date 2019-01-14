package com.example.admin.tourproduct.activity;

import android.view.View;

import com.example.admin.tourproduct.R;

import base.activity.BaseActivity;

public class SettingActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void intiView() {
        hideActionBar();
        findViewById(R.id.btn_exit).setOnClickListener(this);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_exit:
                openActivity(SettingActivity.this,LoginActivity.class,0,0,true);
                break;
        }
    }
}
