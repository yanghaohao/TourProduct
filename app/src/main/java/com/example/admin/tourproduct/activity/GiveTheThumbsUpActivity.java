package com.example.admin.tourproduct.activity;

import android.view.View;
import android.widget.TextView;

import com.example.admin.tourproduct.R;

import base.activity.BaseActivity;

public class GiveTheThumbsUpActivity extends BaseActivity {


    @Override
    protected void intiView() {
        hideActionBar();
        TextView tvActionBar = findViewById(R.id.tv_action_bar);
        tvActionBar.setVisibility(View.VISIBLE);
        tvActionBar.setText("点赞");
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_give_the_thumbs_up);
    }
}
