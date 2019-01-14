package com.example.admin.tourproduct.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.adapter.CouponAdapter;
import com.example.admin.tourproduct.adapter.DiscussAdapter;
import com.example.admin.tourproduct.entry.AddTravelsResult;
import com.example.admin.tourproduct.entry.Delicacy;
import com.example.admin.tourproduct.entry.Discuss;
import com.example.admin.tourproduct.interfaces.AddDelicacyDiscuss;
import com.example.admin.tourproduct.interfaces.DelicacyView;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;
import com.example.admin.tourproduct.presenter.HomeFragmentPresenter;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.HttpUtil;
import com.example.admin.tourproduct.util.SharedUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import base.activity.BaseActivity;

/**
 * @Author: Bro0cL
 * @Date: 2018/2/8 21:22
 */
public class DelicacyDetailsActivity extends BaseActivity implements View.OnClickListener {

    private Delicacy.EntityBean delicacy;
    private EditText mEtTravel;
    private TextView mTvUp;
    private HomeFragmentPresenter homeFragmentPresenter;
    private RatingBar mRbUserStar;
    private TextView mTvDelicacyCoupon;
    private DiscussAdapter discussAdapter;
    private RecyclerView rvDelicacyDiscuss;
    private AddTravelsResult addTravelsDiscussResult;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case 1:
                    Toast.makeText(DelicacyDetailsActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                    discussAdapter.addDiscuss(new Discuss(HomeActivity.user.getEntity().getID(),delicacy.getId(),mEtTravel.getText().toString()
                            ,HomeActivity.user.getEntity().getUserName()+"",null));
                    rvDelicacyDiscuss.scrollToPosition(discussAdapter.getItemCount()-1);
                    SharedUtils.getSharedUtils(DelicacyDetailsActivity.this).saveBoolean(SharedUtils.IS_UPDATE_delicacy,true);
                    mEtTravel.setText("");
                    break;
            }
            return false;
        }
    });
    private AddDelicacyDiscuss addDelicacyDiscuss = new AddDelicacyDiscuss() {
        @Override
        public void addDelicacyDiscuss(String re) {
            addTravelsDiscussResult = (AddTravelsResult) new Gson().fromJson(re, new TypeToken<AddTravelsResult>() {}.getType());
            if(addTravelsDiscussResult.getStatus()!=-1)
                handler.sendEmptyMessage(Constant.STATUS_NORMAL);
            else {
                handler.sendEmptyMessage(Constant.STATUS_UNUSUAL);
            }
        }

        @Override
        public void addDelicacyDiscussFail() {

        }
    };

    RecyclerViewClickListener recyclerViewClickListener = new RecyclerViewClickListener() {
        @Override
        public void onClick(View view, int position) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.CONPON,delicacy.getCouponList().get(position));
            bundle.putString(Constant.CONPON_PICTURE,delicacy.getDrawableURL());
            bundle.putInt(Constant.SHOPID,delicacy.getId());
            openActivity(CouponActivity.class,bundle,false);
        }

        @Override
        public void onLongClick(View view, int position) {

        }

        @Override
        public void onDoubleClick(View view, int position) {

        }
    };

    private DelicacyView delicacyView = new DelicacyView() {
        @Override
        public void delicacyFail() {

        }

        @Override
        public void showDelicacyData(String delicacy, int status) {

        }
    };

    @Override
    protected void intiView() {
        hideActionBar();

        showActionBar(true,false,"餐厅详情",null);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            delicacy = (Delicacy.EntityBean) bundle.getSerializable(Constant.DELICACY_DETAIL_BUNDLE);
        }else{
            Toast.makeText(this, "暂时没有获取到数据请退出后重试", Toast.LENGTH_SHORT).show();
        }

        ImageView ivDelicacyHead = findViewById(R.id.iv_delicacy_head);
        Glide.with(this).load(HttpUtil.DELICACY_DRAWABLE+delicacy.getDrawableURL()).into(ivDelicacyHead);

        TextView tvDelicacyName = findViewById(R.id.tv_delicacy_name);
        tvDelicacyName.setText(delicacy.getCommercialTenantName());

        RatingBar rbStar = findViewById(R.id.rb_star);
        rbStar.setRating((float) delicacy.getStarLevel());

        TextView tvPhoneNumber = findViewById(R.id.tv_phone_number);
        tvPhoneNumber.setText("电话："+delicacy.getPhoneNumber());

        TextView tvAddress = findViewById(R.id.tv_address);
        tvAddress.setText(delicacy.getAddress());
        tvAddress.setOnClickListener(this);

        TextView tvTime = findViewById(R.id.tv_time);
        tvTime.setText(delicacy.getTime());

        RecyclerView rvDelicacyCoupon = findViewById(R.id.rv_delicacy_coupon);
        rvDelicacyCoupon.setLayoutManager(new LinearLayoutManager(this));
        for (Delicacy.EntityBean.CouponListBean couponListBean : delicacy.getCouponList()){
            couponListBean.setBitmap(R.mipmap.hots_s);
        }
        CouponAdapter couponAdapter = new CouponAdapter(delicacy.getCouponList(),this);
        rvDelicacyCoupon.setAdapter(couponAdapter);
        couponAdapter.setmRecyclerViewClickListener(recyclerViewClickListener);

        List<Discuss> discusses = new ArrayList<>();
        for (Delicacy.EntityBean.CommentListBean commentListBean : delicacy.getCommentList()){
            discusses.add(new Discuss(commentListBean.getId(),commentListBean.getShopId(),commentListBean.getContent()
                    ,commentListBean.getCommentUser(),commentListBean.getTitle()));
        }
        TextView tvDelicacyDis = findViewById(R.id.tv_delicacy_dis);
        if (discusses.size()>0){
            tvDelicacyDis.setVisibility(View.GONE);
        }else {
            tvDelicacyDis.setVisibility(View.VISIBLE);
        }
        rvDelicacyDiscuss = findViewById(R.id.rv_delicacy_discuss);
        rvDelicacyDiscuss.setLayoutManager(new LinearLayoutManager(this));
        discussAdapter = new DiscussAdapter(discusses,this);
        rvDelicacyDiscuss.setAdapter(discussAdapter);
        mEtTravel = findViewById(R.id.et_travel);
        mTvUp = findViewById(R.id.tv_up);
        mTvUp.setOnClickListener(this);
        mRbUserStar = findViewById(R.id.rb_user_star);
        homeFragmentPresenter = new HomeFragmentPresenter(addDelicacyDiscuss);
        mTvDelicacyCoupon = findViewById(R.id.tv_delicacy_coupon);
        if (delicacy.getCouponList() == null||delicacy.getCouponList().size()<=0){
            mTvDelicacyCoupon.setVisibility(View.GONE);
        }

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_delicacy_details);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.action_bar_cancel:
                finish();
                break;
            case R.id.tv_address:
                Bundle bundle = new Bundle();
                bundle.putString(Constant.MAP,delicacy.getAddress());
                openActivity(MapActivity.class,bundle,false);
                break;
            case R.id.tv_up:
                String data = new Gson().toJson(new Delicacy.EntityBean.CommentListBean(0,delicacy.getId(),null
                        ,HomeActivity.user.getEntity().getID()+"",mEtTravel.getText().toString(),null
                        ,mRbUserStar.getRating(),null));
                homeFragmentPresenter.loadingData(Constant.SAVE_DELICACY_DISCUSS,data,Constant.POST,false);
                break;
        }
    }
}
