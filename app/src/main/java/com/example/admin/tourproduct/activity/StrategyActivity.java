package com.example.admin.tourproduct.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.adapter.StrategyAdapter;
import com.example.admin.tourproduct.entry.Strategy;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.view.ShapeLoadingDialog;

import java.util.List;

import base.activity.BaseActivity;

public class StrategyActivity extends BaseActivity implements RecyclerViewClickListener {

    private final static String TITLE = "攻略";
    private RecyclerView mStrategyList;
    private ShapeLoadingDialog mShapeLoadingDialog;
    private List<Strategy.EntityBean> list;


    @Override
    protected void setLayout() {
        setContentView(R.layout.fragment_strategy);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void intiView() {

        hideActionBar();

        showActionBar(true,false,TITLE,null);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            Strategy strategy = (Strategy) bundle.getSerializable(Constant.STRATEGY_BUNDLE);
            if (strategy != null){
                list = strategy.getEntity();
            }
        }else{
            Toast.makeText(this, "暂时没有获取到数据请退出后重试", Toast.LENGTH_SHORT).show();
        }


        mStrategyList = findViewById(R.id.strategy_list);
        mStrategyList.setLayoutManager(new LinearLayoutManager(this));

        mShapeLoadingDialog = getUtil();


        bindAdapter(mStrategyList,list);

        EditText etActionbarCanBack = findViewById(R.id.et_actionbar_can_back);
        etActionbarCanBack.setInputType(InputType.TYPE_NULL);
        etActionbarCanBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        openActivity(SearchActivity.class,false);
                        break;
                }
                return true;
            }
        });
    }

    private void bindAdapter(RecyclerView recyclerView,List<Strategy.EntityBean> list){
        StrategyAdapter strategyAdapter = new StrategyAdapter(this,list,0);
        recyclerView.setAdapter(strategyAdapter);
        strategyAdapter.setmRecyclerViewClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.STRATEGY_DETAIL_BUNDLE,list.get(position));
        openActivity(StrategyDetailActivity.class,bundle,false);
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    @Override
    public void onDoubleClick(View view, int position) {

    }

}
