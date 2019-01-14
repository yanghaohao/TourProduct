package com.example.admin.tourproduct.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.activity.GiveTheThumbsUpActivity;
import com.example.admin.tourproduct.activity.NoticeActivity;
import com.example.admin.tourproduct.adapter.NoticeAdapter;
import com.example.admin.tourproduct.entry.Notice;
import com.example.admin.tourproduct.interfaces.IsLogin;
import com.example.admin.tourproduct.interfaces.NoticeView;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;
import com.example.admin.tourproduct.presenter.HomeFragmentPresenter;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.view.ShapeLoadingDialog;

import java.util.List;

import base.fragment.BaseFragment;

public class NoticeFragment extends BaseFragment implements View.OnClickListener,NoticeView, RecyclerViewClickListener {

    private TextView mTvNoticeCount;
    private RecyclerView mRvAllList;
    private ShapeLoadingDialog mShapeLoadingDialog;
    private IsLogin isLogin;

    @Override
    protected int setLayout() {
        return R.layout.fragment_notice;
    }

    @Override
    protected void initView(View view) {
        mShapeLoadingDialog = getUtil();

        mTvNoticeCount = view.findViewById(R.id.tv_notice_count);
        mRvAllList = view.findViewById(R.id.rv_all_list);
        mRvAllList.setLayoutManager(new LinearLayoutManager(getContext()));

        view.findViewById(R.id.tv_give_the_thumbs_up).setOnClickListener(this);
        view.findViewById(R.id.tv_notice).setOnClickListener(this);

        HomeFragmentPresenter presenter = new HomeFragmentPresenter(this);
        presenter.loadingData(Constant.NOTICE,"PageSize=20&CurrentPage="+1,Constant.GET,false);


    }

//    private void bindAdapter(RecyclerView recyclerView,List<Notice> list){
//        NoticeAdapter placeAdapter = new NoticeAdapter(getContext(),list);
//        recyclerView.setAdapter(placeAdapter);
//        placeAdapter.setmRecyclerViewClickListener(this);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_give_the_thumbs_up:
                openActivity(getContext(), GiveTheThumbsUpActivity.class,0,0,false);
                break;
            case R.id.tv_notice:
                openActivity(getContext(), NoticeActivity.class,0,0,false);
                break;
        }
    }

    @Override
    public void noticFail() {

    }

    @Override
    public void showNoticeData(String list) {

    }

    public IsLogin getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(IsLogin isLogin) {
        this.isLogin = isLogin;
    }

    @Override
    public void onClick(View view, int position) {
        if (mIsLogin){

        }else {
            isLogin.isLogin("您还未登陆，请先登陆","登陆",2000,openClick);
        }
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    @Override
    public void onDoubleClick(View view, int position) {

    }
}
