package com.example.admin.tourproduct.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.adapter.DelicacyAdapter;
import com.example.admin.tourproduct.entry.Delicacy;
import com.example.admin.tourproduct.interfaces.DelicacyView;
import com.example.admin.tourproduct.interfaces.PersonChanged;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;
import com.example.admin.tourproduct.presenter.HomeFragmentPresenter;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.SharedUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import base.activity.BaseActivity;

public class DelicacyActivity extends BaseActivity implements PersonChanged,RecyclerViewClickListener {


    private ViewPager mVpAllViewPager;
    private RecyclerView mRvAllList;
    private int curretItem;
    private int page = 1;
    private List<Delicacy.EntityBean> delicacies;
    private Delicacy mDelicacy;
    private Delicacy delicacy;
    private HomeFragmentPresenter homeFragmentPresenter;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if ((  handler.hasMessages(Constant.UPDATE_VIEW_PAGER_ITEM))&&(curretItem!=Integer.MAX_VALUE/2)){
                handler.removeMessages(Constant.UPDATE_VIEW_PAGER_ITEM);
            }
            switch (message.what){
                case Constant.UPDATE_VIEW_PAGER_ITEM:
                    curretItem++;
                    mVpAllViewPager.setCurrentItem(curretItem);
                    handler.sendEmptyMessageDelayed(Constant.UPDATE_VIEW_PAGER_ITEM, Constant.UPDATE_TIME);
                    break;
                case Constant.VIEW_PAGER_RESUME:
                    handler.sendEmptyMessageDelayed(Constant.UPDATE_VIEW_PAGER_ITEM, Constant.UPDATE_TIME);
                    break;
                case Constant.VIEW_PAGER_STOP:
                    break;
                case Constant.VIEW_PAGER_PERSON_CHANGE:
                    curretItem = message.arg1;
                    break;
            }
            return false;
        }
    });

    private Handler delicacyHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (delicacies!=null) {
                switch (msg.what){
                    case 13:
                        DelicacyAdapter adapter = new DelicacyAdapter(delicacies, DelicacyActivity.this, 1);
                        mRvAllList.setAdapter(adapter);
                        adapter.setmRecyclerViewClickListener(new RecyclerViewClickListener() {
                            @Override
                            public void onClick(View view, int position) {

                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable(Constant.DELICACY_DETAIL_BUNDLE, delicacies.get(position));
                                    openActivity( DelicacyDetailsActivity.class, bundle, false);

                            }

                            @Override
                            public void onLongClick(View view, int position) {

                            }

                            @Override
                            public void onDoubleClick(View view, int position) {

                            }
                        });
                        break;
                }

            }
            return false;
        }
    });

    @Override
    protected void setLayout() {
        setContentView(R.layout.fragment_travels);
    }

    @Override
    protected void intiView() {

        hideActionBar();

        showActionBar(true,"美食",onClickListener,"团购");
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            delicacy = (Delicacy) bundle.getSerializable(Constant.DELICACY_BUNDLE);
            if (delicacy != null){
                delicacies = delicacy.getEntity();
            }
        }else{
            Toast.makeText(this, "暂时没有获取到数据请退出后重试", Toast.LENGTH_SHORT).show();
        }
        mVpAllViewPager = (ViewPager) findViewById(R.id.vp_all_view_pager);

        mRvAllList = findViewById(R.id.rv_delicacy);
        LinearLayoutManager delicacyLayoutManager = new LinearLayoutManager(this);
        delicacyLayoutManager.setSmoothScrollbarEnabled(true);
        delicacyLayoutManager.setAutoMeasureEnabled(true);
        mRvAllList.setLayoutManager(delicacyLayoutManager);
        mRvAllList.setHasFixedSize(true);
        mRvAllList.setNestedScrollingEnabled(false);
        bindAdapter(mRvAllList,delicacies);

//        List<View> list = new ArrayList<>();
//        LinearLayout ll_circle = (LinearLayout) findViewById(R.id.ll_circle);

//        ViewPagerBitmapUtil viewPagerBitmapUtil = ViewPagerBitmapUtil.getViewPagerBitmapUtil(this);
//        viewPagerBitmapUtil.setmPersonChanged(this);
//        viewPagerBitmapUtil.bindDelicacyAdViewPager(mVpAllViewPager,list,ll_circle,getDrawableRes(), ADInfoActivity.class);
        homeFragmentPresenter = new HomeFragmentPresenter(delicacyView);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.action_bar_menu:
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.DELICACY_DETAIL_BUNDLE,delicacy);
                    openActivity(ActivityTimeSale.class,bundle,false);
                    break;
            }
        }
    };

    DelicacyView delicacyView = new DelicacyView() {
        @Override
        public void delicacyFail() {

        }

        @Override
        public void showDelicacyData(String delicacy, int status) {
            mDelicacy = (Delicacy) new Gson().fromJson(delicacy, new TypeToken<Delicacy>() {}.getType());
            delicacies = mDelicacy.getEntity();
            if(mDelicacy.getStatus()==1)
                delicacyHandler.sendEmptyMessage(Constant.HOME_DELICACY_STATUS_NORMAL);
            else {
                delicacyHandler.sendEmptyMessage(Constant.HOME_DELICACY_STATUS_UNUSUAL);
            }
        }
    };

//    private List<Advertisement> getDrawableRes(){
//        List<Advertisement> list = new ArrayList<>();
//        list.add(new Advertisement(0,R.mipmap.ad_delicacy));
//        list.add(new Advertisement(0,R.mipmap.ad_delicacy));
//        return list;
//    }

    private void bindAdapter(RecyclerView recyclerView,List<Delicacy.EntityBean> list){
        DelicacyAdapter delicacyAdapter = new DelicacyAdapter(list,this,0);
        recyclerView.setAdapter(delicacyAdapter);
        delicacyAdapter.setmRecyclerViewClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SharedUtils.getSharedUtils(DelicacyActivity.this).readBoolean(SharedUtils.IS_UPDATE_TRAVEL)){
            homeFragmentPresenter.loadingData(Constant.TRAVEL,"PageSize=50&CurrentPage="+page, Constant.GET,false);
        }
    }

    @Override
    public void personChangeViewPager(int position) {
        handler.sendMessage(Message.obtain(handler, Constant.VIEW_PAGER_PERSON_CHANGE, position, 0));
    }

    @Override
    public void personDragging() {
        handler.sendEmptyMessage(Constant.VIEW_PAGER_RESUME);
    }

    @Override
    public void startViewPager() {
        handler.sendEmptyMessageDelayed(Constant.UPDATE_VIEW_PAGER_ITEM, Constant.UPDATE_TIME);
    }

    @Override
    public void onClick(View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.DELICACY_DETAIL_BUNDLE,delicacies.get(position));
        openActivity(DelicacyDetailsActivity.class,bundle,false);
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    @Override
    public void onDoubleClick(View view, int position) {

    }
}
