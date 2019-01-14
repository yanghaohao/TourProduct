package com.example.admin.tourproduct.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.util.ViewPagerBitmapUtil;

import base.activity.BaseActivity;

public class LeaderActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void intiView() {
        hideActionBar();

        ViewPager vp_leader = findViewById(R.id.vp_all_view_pager);


        LinearLayout ll_circle = (LinearLayout) findViewById(R.id.ll_circle);

        ViewPagerBitmapUtil viewPagerBitmapUtil = ViewPagerBitmapUtil.getViewPagerBitmapUtil(this);
        viewPagerBitmapUtil.bindLeaderViewPager(vp_leader,ll_circle,getDrawableRes());
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_leader);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_leader:
                openActivity(HomeActivity.class,true);
                break;
        }
    }

    private int[] getDrawableRes(){
        int[] drawableRes = {};
        return drawableRes;
    }
}
