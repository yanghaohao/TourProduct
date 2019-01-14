package com.example.admin.tourproduct.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.adapter.DiscussAdapter;
import com.example.admin.tourproduct.adapter.TravelsBitmapAdapter;
import com.example.admin.tourproduct.entry.AddTravelsResult;
import com.example.admin.tourproduct.entry.Discuss;
import com.example.admin.tourproduct.entry.TravelsNote;
import com.example.admin.tourproduct.interfaces.AddTravelDiscuss;
import com.example.admin.tourproduct.interfaces.TravelsView;
import com.example.admin.tourproduct.presenter.HomeFragmentPresenter;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.HttpUtil;
import com.example.admin.tourproduct.util.LogUtil;
import com.example.admin.tourproduct.util.SharedUtils;
import com.example.admin.tourproduct.view.ShapeLoadingDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import base.activity.BaseActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class TravelsDetailActivity extends BaseActivity {

    private EditText etTravel;
    private ShapeLoadingDialog mShapeLoadingDialog;
    private HomeFragmentPresenter homeFragmentPresenter;
    private TravelsNote.EntityBean travel;
    private DiscussAdapter discussAdapter;
    private RecyclerView travelDiscuss;
    private AddTravelsResult addTravelsDiscussResult;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (addTravelsDiscussResult.getStatus() == 1){
//                homeFragmentPresenter.loadingData(Constant.GET_TRAVEL_BY_ID,"id="+travel.getId(),Constant.GET,false);
                Toast.makeText(TravelsDetailActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                discussAdapter.addDiscuss(new Discuss(HomeActivity.user.getEntity().getID(),travel.getId(),etTravel.getText().toString()
                        ,HomeActivity.user.getEntity().getUserName()+"",null));
                travelDiscuss.scrollToPosition(discussAdapter.getItemCount()-1);
                SharedUtils.getSharedUtils(TravelsDetailActivity.this).saveBoolean(SharedUtils.IS_UPDATE_TRAVEL,true);
                etTravel.setText("");
            }
            return false;
        }
    });
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_up:
                    LogUtil.e("aaa",HomeActivity.user.toString());
                    String data = new Gson().toJson(new TravelsNote.EntityBean.DiscussBean(0,travel.getId(),etTravel.getText().toString()
                            ,HomeActivity.user.getEntity().getID()+"",null));
                    homeFragmentPresenter.loadingData(Constant.SAVE_TRAVEL_DISCUSS,data,Constant.POST,false);
                    break;
                case R.id.action_bar_menu:

                    break;
            }
        }
    };

    private AddTravelDiscuss allDataGet = new AddTravelDiscuss() {
        @Override
        public void addTravelDiscuss(String re) {
            addTravelsDiscussResult = (AddTravelsResult) new Gson().fromJson(re, new TypeToken<AddTravelsResult>() {}.getType());
            if(addTravelsDiscussResult.getStatus()!=-1)
                handler.sendEmptyMessage(Constant.STATUS_NORMAL);
            else {
                handler.sendEmptyMessage(Constant.STATUS_UNUSUAL);
            }
        }

        @Override
        public void addTravelDiscussFail() {

        }
    };

    TravelsView travelsView = new TravelsView() {
        @Override
        public void travelsFail() {

        }

        @Override
        public void travelsData(String list,int status) {

        }
    };

    @Override
    protected void intiView() {

        hideActionBar();
        showActionBar(true, "攻略详情",onClickListener,"收藏");
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            travel = (TravelsNote.EntityBean) bundle.getSerializable(Constant.TRAVEL_DETAIL_BUNDLE);
        }else{
            Toast.makeText(this, "暂时没有获取到数据请退出后重试", Toast.LENGTH_SHORT).show();
        }
        CircleImageView mClvItemTravelHead = findViewById(R.id.clv_item_travel_head);
        TextView mTvItemTravelName = findViewById(R.id.tv_item_travel_name);
        TextView mTvItemTravelContent = findViewById(R.id.tv_item_travel_content);
        RecyclerView mGvItemTravelPic = findViewById(R.id.gv_item_travel_pic);
        ImageView ivTravelItemBitmap = findViewById(R.id.iv_travel_item_bitmap);


        if (travel.getBitmaps().size() == 1){
            ivTravelItemBitmap.setVisibility(View.VISIBLE);
            mGvItemTravelPic.setVisibility(View.GONE);
            Glide.with(this).load(HttpUtil.DELICACY_DRAWABLE+"/"+travel.getBitmaps().get(0).getBitmap())
                    .into(ivTravelItemBitmap);
        }else if (travel.getBitmaps().size() == 0){
            ivTravelItemBitmap.setVisibility(View.GONE);
            mGvItemTravelPic.setVisibility(View.GONE);
        }else {
            ivTravelItemBitmap.setVisibility(View.GONE);
            mGvItemTravelPic.setVisibility(View.VISIBLE);
        }
        mTvItemTravelName.setText(travel.getName());
        mTvItemTravelContent.setText(travel.getContent());
        TravelsBitmapAdapter travelsBitmapAdapter = new TravelsBitmapAdapter (travel.getBitmaps(),TravelsDetailActivity.this);
        mGvItemTravelPic.setLayoutManager(new GridLayoutManager(this,3));
        mGvItemTravelPic.setAdapter(travelsBitmapAdapter);
        TextView mTvItemTravelTime = findViewById(R.id.tv_item_travel_time);
        mTvItemTravelTime.setText(travel.getInputdate());

        List<Discuss> discusses = new ArrayList<>();
        for (TravelsNote.EntityBean.DiscussBean discussBean : travel.getDiscuss()){
            discusses.add(new Discuss(discussBean.getId(),discussBean.getTNId(),discussBean.getContent(),discussBean.getInputuser()
                    ,discussBean.getInputdate()));
        }
        travelDiscuss = findViewById(R.id.travel_discuss);
        travelDiscuss.setLayoutManager(new LinearLayoutManager(this));
        discussAdapter = new DiscussAdapter(discusses,this);
        travelDiscuss.setAdapter(discussAdapter);

        etTravel = findViewById(R.id.et_travel);
        TextView tvUp = findViewById(R.id.tv_up);
        tvUp.setOnClickListener(onClickListener);

        mShapeLoadingDialog = new ShapeLoadingDialog.Builder(TravelsDetailActivity.this)
                .loadText(loadText)
                .build();
        homeFragmentPresenter = new HomeFragmentPresenter(allDataGet,travelsView);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_travels_detail);
    }



}
