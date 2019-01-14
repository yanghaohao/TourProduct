package com.example.admin.tourproduct.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.adapter.MycouponAdapter;
import com.example.admin.tourproduct.db.DBProcess;
import com.example.admin.tourproduct.entry.Delicacy;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;
import com.example.admin.tourproduct.util.Constant;

import java.util.List;

import base.activity.BaseActivity;

public class MyCouponActivity extends BaseActivity {

    private List<Delicacy.EntityBean.CouponListBean> coupons;
    @Override
    protected void intiView() {
        hideActionBar();
        showActionBar(true,false,"我的优惠券",null);

        RecyclerView my_all_coupon = findViewById(R.id.my_all_coupon);
        my_all_coupon.setLayoutManager(new LinearLayoutManager(this));
        DBProcess dbProcess = new DBProcess(this);
        coupons = dbProcess.findAllCoupon();
        MycouponAdapter mycouponAdapter = new MycouponAdapter(this,coupons);
        my_all_coupon.setAdapter(mycouponAdapter);
        mycouponAdapter.setmRecyclerViewClickListener(recyclerViewClickListener);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_my_coupon);
    }

    RecyclerViewClickListener recyclerViewClickListener = new RecyclerViewClickListener() {
        @Override
        public void onClick(View view, int position) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.MY_COUPON,coupons.get(position));
            openActivity(CouponDetailActivity.class,bundle,false);
        }

        @Override
        public void onLongClick(View view, int position) {

        }

        @Override
        public void onDoubleClick(View view, int position) {

        }
    };
}
