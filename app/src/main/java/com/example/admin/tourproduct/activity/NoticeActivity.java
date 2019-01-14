package com.example.admin.tourproduct.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.adapter.NoticeAndMessageAdapter;

import base.activity.BaseActivity;

public class NoticeActivity extends BaseActivity {


    @Override
    protected void intiView() {
        hideActionBar();

        showActionBar(true,false,"通知",null);

//        RecyclerView list = findViewById(R.id.list_notice);
//        list.setLayoutManager(new LinearLayoutManager(this));
//        NoticeAndMessageAdapter noticeAndMessageAdapter = new NoticeAndMessageAdapter();
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_notice);
    }
}
