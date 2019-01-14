package com.example.admin.tourproduct.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.activity.CityDetailsActivity;
import com.example.admin.tourproduct.activity.SearchActivity;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;
import com.example.admin.tourproduct.view.ShapeLoadingDialog;

import base.fragment.BaseFragment;

/**
 * Created by YH on 2018/4/13.
 */

public class StrategyFragment extends BaseFragment implements RecyclerViewClickListener {

    private final static String TITLE = "攻略";
    private RecyclerView mStrategyList;
    private ShapeLoadingDialog mShapeLoadingDialog;


    @Override
    protected int setLayout() {
        return R.layout.fragment_strategy;
    }

    @Override
    protected void initView(View view) {

        TextView action_bar_name = view.findViewById(R.id.action_bar_name);

        action_bar_name.setText(TITLE);

        mStrategyList = view.findViewById(R.id.strategy_list);
        mStrategyList.setLayoutManager(new LinearLayoutManager(getContext()));

        mShapeLoadingDialog = getUtil();

//        HomeFragmentPresenter homeFragmentPresenter = new HomeFragmentPresenter(this);
//        homeFragmentPresenter.loadingData(Constant.STRATEGY,null);


        EditText etActionbarCanBack = view.findViewById(R.id.et_actionbar_can_back);
        etActionbarCanBack.setInputType(InputType.TYPE_NULL);
        etActionbarCanBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        openActivity(getContext(), SearchActivity.class,R.anim.activity_start,0,false);
                        break;
                }
                return true;
            }
        });
    }

//    private void bindAdapter(RecyclerView recyclerView,List<Strategy> list){
//        StrategyAdapter strategyAdapter = new StrategyAdapter(getContext(),list);
//        recyclerView.setAdapter(strategyAdapter);
//        strategyAdapter.setmRecyclerViewClickListener(this);
//    }

//    @Override
//    public void showLoadingProgress(String msg) {
//        mShapeLoadingDialog.show();
//    }
//
//    @Override
//    public void showData(List<Strategy> list) {
//        mShapeLoadingDialog.cancel();
//        bindAdapter(mStrategyList,list);
//    }

    @Override
    public void onClick(View view, int position) {
        openActivity(getContext(), CityDetailsActivity.class,0,0,false);
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    @Override
    public void onDoubleClick(View view, int position) {

    }
}
