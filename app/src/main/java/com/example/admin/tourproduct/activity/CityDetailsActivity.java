package com.example.admin.tourproduct.activity;

import android.view.View;
import android.widget.EditText;

import com.example.admin.tourproduct.R;

import base.activity.BaseActivity;

public class CityDetailsActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void intiView() {
        hideActionBar();

        findViewById(R.id.action_bar_cancel).setOnClickListener(this);

        EditText etActionbarCanBack = findViewById(R.id.et_actionbar_can_back);
        setEdiText(etActionbarCanBack);

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_city_details);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.action_bar_cancel:
                finish();
                break;
        }
    }
}
