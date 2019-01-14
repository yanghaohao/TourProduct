package com.example.admin.tourproduct.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.adapter.NoticeAdapter;
import com.example.admin.tourproduct.entry.Notice;
import com.example.admin.tourproduct.interfaces.NoticeView;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;
import com.example.admin.tourproduct.presenter.HomeFragmentPresenter;
import com.example.admin.tourproduct.util.Constant;

import java.util.List;

import base.activity.BaseActivity;

/**
 * author Bro0cL on 2016/1/26.
 */
public class HotEventActivity extends BaseActivity implements NoticeView, RecyclerViewClickListener {

    private RecyclerView mRvTop;
    @Override
    protected void intiView() {
        hideActionBar();

        showActionBar(true,false,"途哟头条",null);
        findViewById(R.id.action_bar_menu).setVisibility(View.VISIBLE);

        mRvTop = findViewById(R.id.rv_top);
        mRvTop.setLayoutManager(new LinearLayoutManager(this));

        HomeFragmentPresenter presenter = new HomeFragmentPresenter(this);
        presenter.loadingData(Constant.NOTICE,null,Constant.NO,false);
    }

//    private void bindAdapter(RecyclerView recyclerView,List<Notice> list){
//        NoticeAdapter placeAdapter = new NoticeAdapter(this,list);
//        recyclerView.setAdapter(placeAdapter);
//        placeAdapter.setmRecyclerViewClickListener(this);
//    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_hot_event);
    }

    @Override
    public void noticFail() {

    }

    @Override
    public void showNoticeData(String list) {
//        bindAdapter(mRvTop,list);
    }

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onLongClick(View view, int position) {

    }

    @Override
    public void onDoubleClick(View view, int position) {

    }
}
