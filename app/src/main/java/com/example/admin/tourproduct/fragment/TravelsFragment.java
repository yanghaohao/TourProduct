package com.example.admin.tourproduct.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.interfaces.PersonChanged;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.SharedUtils;

import base.fragment.BaseFragment;

/**
 * Created by admin on 2018/4/13.
 */

public class TravelsFragment extends BaseFragment implements PersonChanged, RecyclerViewClickListener {

    private ViewPager mVpAllViewPager;
    private RecyclerView mRvAllList;
    private int curretItem;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if ((  handler.hasMessages(Constant.UPDATE_VIEW_PAGER_ITEM))&&(curretItem!=Integer.MAX_VALUE/2)){
                handler.removeMessages(Constant.UPDATE_VIEW_PAGER_ITEM);
            }
            switch (msg.what){
                case Constant.UPDATE_VIEW_PAGER_ITEM:
                    curretItem++;
                    mVpAllViewPager.setCurrentItem(curretItem);
                    handler.sendEmptyMessageDelayed(Constant.UPDATE_VIEW_PAGER_ITEM,Constant.UPDATE_TIME);
                    break;
                case Constant.VIEW_PAGER_RESUME:
                    handler.sendEmptyMessageDelayed(Constant.UPDATE_VIEW_PAGER_ITEM,Constant.UPDATE_TIME);
                    break;
                case Constant.VIEW_PAGER_STOP:
                    break;
                case Constant.VIEW_PAGER_PERSON_CHANGE:
                    curretItem = msg.arg1;
                    break;
            }
        }
    };

    @Override
    protected int setLayout() {
        return R.layout.fragment_travels;
    }

    @Override
    protected void initView(View view) {

        mVpAllViewPager = (ViewPager) view.findViewById(R.id.vp_all_view_pager);

        mRvAllList = view.findViewById(R.id.rv_delicacy);
        mRvAllList.setLayoutManager(new LinearLayoutManager(getContext()));

//        List<View> list = new ArrayList<>();
//        LinearLayout ll_circle = (LinearLayout) view.findViewById(R.id.ll_circle);

//        ViewPagerBitmapUtil viewPagerBitmapUtil = ViewPagerBitmapUtil.getViewPagerBitmapUtil(getContext());
//        viewPagerBitmapUtil.setmPersonChanged(this);
//        viewPagerBitmapUtil.bindDelicacyAdViewPager(mVpAllViewPager,list,ll_circle,getDrawableRes(), ADInfoActivity.class);

//        HomeFragmentPresenter homeFragmentPresenter = new HomeFragmentPresenter(this);
//        homeFragmentPresenter.loadingData(Constant.DELICACY,null);

        view.findViewById(R.id.et_actionbar).setVisibility(View.INVISIBLE);

        TextView tvPlace = view.findViewById(R.id.tv_place);
        tvPlace.setText(SharedUtils.getSharedUtils(getContext()).readString(SharedUtils.CITY_PLACE));
    }

//    private List<Advertisement> getDrawableRes(){
//        List<Advertisement> list = new ArrayList<>();
//        list.add(new Advertisement(0,R.mipmap.ad_delicacy));
//        list.add(new Advertisement(1,R.color.bamboo_color));
//        list.add(new Advertisement(0,R.color.black));
//        return list;
//    }

//    private void bindAdapter(RecyclerView recyclerView,List<Delicacy> list){
//        DelicacyAdapter delicacyAdapter = new DelicacyAdapter(list,getContext());
//        recyclerView.setAdapter(delicacyAdapter);
//        delicacyAdapter.setmRecyclerViewClickListener(this);
//    }

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
        handler.sendEmptyMessageDelayed(Constant.UPDATE_VIEW_PAGER_ITEM,Constant.UPDATE_TIME);
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

//    @Override
//    public void showLoadingProgress(String msg) {
//
//    }
//
//    @Override
//    public void showData(List<Delicacy> list) {
//        LogUtil.e(list+"");
//        bindAdapter(mRvAllList,list);
//    }
}
