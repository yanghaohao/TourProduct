package com.example.admin.tourproduct.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.db.DBProcess;

import java.util.ArrayList;
import java.util.List;

import base.activity.BaseActivity;

public class SearchActivity extends BaseActivity implements TextWatcher, AdapterView.OnItemClickListener, View.OnClickListener {

    private DBProcess dbProcess;
    private ListView mLvSearchMsg;

    @Override
    protected void intiView() {
        hideActionBar();

        dbProcess = new DBProcess(this);

        mLvSearchMsg = findViewById(R.id.lv_search_msg);

        EditText etSearchMsg = findViewById(R.id.et_search_msg);
        etSearchMsg.addTextChangedListener(this);

        findViewById(R.id.tv_search_cancel).setOnClickListener(this);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_search);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String keyword = s.toString();
        List<String> cursor = new ArrayList<>();
        for (int i = 0;i < dbProcess.findLikePlace(keyword).size();i++){
            cursor.add(dbProcess.findLikePlace(keyword).get(i).getName());
        }
        bindAdapter(mLvSearchMsg,cursor);
    }

    private void bindAdapter(ListView mLvSearchMsg, List<String> cursor) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(SearchActivity.this,R.layout.item_city_name,cursor);
        mLvSearchMsg.setAdapter(arrayAdapter);
        mLvSearchMsg.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_search_cancel:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
