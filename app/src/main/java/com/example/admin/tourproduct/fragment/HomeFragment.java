package com.example.admin.tourproduct.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.activity.DelicacyActivity;
import com.example.admin.tourproduct.activity.DelicacyDetailsActivity;
import com.example.admin.tourproduct.activity.HomeActivity;
import com.example.admin.tourproduct.activity.HotEventActivity;
import com.example.admin.tourproduct.activity.StrategyActivity;
import com.example.admin.tourproduct.activity.StrategyDetailActivity;
import com.example.admin.tourproduct.activity.TravelsActivity;
import com.example.admin.tourproduct.activity.TravelsDetailActivity;
import com.example.admin.tourproduct.adapter.DelicacyAdapter;
import com.example.admin.tourproduct.adapter.TravelsItemAdapter;
import com.example.admin.tourproduct.adapter.StrategyAdapter;
import com.example.admin.tourproduct.entry.Advertisement;
import com.example.admin.tourproduct.entry.Delicacy;
import com.example.admin.tourproduct.entry.Strategy;
import com.example.admin.tourproduct.entry.TravelsNote;
import com.example.admin.tourproduct.entry.User;
import com.example.admin.tourproduct.interfaces.ADView;
import com.example.admin.tourproduct.interfaces.DelicacyView;
import com.example.admin.tourproduct.interfaces.HomeFragmentDataPresenter;
import com.example.admin.tourproduct.interfaces.IsLogin;
import com.example.admin.tourproduct.interfaces.LoginView;
import com.example.admin.tourproduct.interfaces.PersonChange;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;
import com.example.admin.tourproduct.interfaces.StrategyView;
import com.example.admin.tourproduct.interfaces.TravelsView;
import com.example.admin.tourproduct.presenter.HomeFragmentPresenter;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.LogUtil;
import com.example.admin.tourproduct.util.SharedUtils;
import com.example.admin.tourproduct.util.ViewPagerBitmapUtil;
import com.example.admin.tourproduct.view.ShapeLoadingDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import base.fragment.BaseFragment;

/**
 * Created by admin on 2018/4/13.
 */

public class HomeFragment extends BaseFragment implements PersonChange, View.OnClickListener {

    private ViewPager mVpAllViewPager;
    private int curretItem;

    private ShapeLoadingDialog mShapeLoadingDialog;
    private TextView mTvPlace;
    private List<Strategy.EntityBean> strategies;
    private Strategy strategy;
    private List<Delicacy.EntityBean> delicacies;
    private LinearLayout mLlHome;
    private HomeFragmentDataPresenter homeFragmentDataPresenter;
    private Delicacy mDelicacy;
    private User users;
    private TravelsNote travels;
    private Advertisement advertisement;
    private List<Advertisement.EntityBean> ads;
    private IsLogin isLogin;
    private RecyclerView mHomeStrategy,mHomeTravels,mHomeDelicacy;
    private ViewPagerBitmapUtil viewPagerBitmapUtil;
    private LinearLayout ll_circle;

    private Handler viewPagerHanler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
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
            return false;
        }
    });
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            if (strategies!=null){
                switch (msg.what){
                    case 11:
                        StrategyAdapter homeItemAdapter = new StrategyAdapter(getContext(),strategies,1);
                        mHomeStrategy.setAdapter(homeItemAdapter);
                        homeItemAdapter.setmRecyclerViewClickListener(new RecyclerViewClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                if (mIsLogin) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable(Constant.STRATEGY_DETAIL_BUNDLE, strategies.get(position));
                                    openActivity(getContext(), StrategyDetailActivity.class, bundle, false);
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
                        });
                        break;
                }
            }

            if (travels!=null){
                switch (msg.what){
                    case 12:
                        TravelsItemAdapter itemAdapter = new TravelsItemAdapter(travels.getEntity(),getContext(),1);
                        mHomeTravels.setAdapter(itemAdapter);
                        itemAdapter.setmRecyclerViewClickListener(new RecyclerViewClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                Bundle bundle = new Bundle();
                                bundle.putSerializable(Constant.TRAVEL_DETAIL_BUNDLE, travels.getEntity().get(position));
                                openActivity(getContext(),TravelsDetailActivity.class,bundle,false);
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

            if (delicacies!=null) {
                switch (msg.what){
                    case 13:
                        DelicacyAdapter adapter = new DelicacyAdapter(delicacies, getContext(), 1);
                        mHomeDelicacy.setAdapter(adapter);
                        adapter.setmRecyclerViewClickListener(new RecyclerViewClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                if (mIsLogin) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable(Constant.DELICACY_DETAIL_BUNDLE, delicacies.get(position));
                                    openActivity(getContext(), DelicacyDetailsActivity.class, bundle, false);
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
                        });
                        break;
                }

            }
            if (ads!=null){
                switch (msg.what){
                    case 14:
                        List<View> list = new ArrayList<>();
//                        Collections.reverse(ads);
                        viewPagerBitmapUtil.bindHomeAdViewPager(mVpAllViewPager,list,ll_circle,ads);

                        break;
                }
            }
            switch (msg.what){
                case 10:
                    sharedUtils.saveBoolean(Constant.IS_LOGIN,true);
                    mShapeLoadingDialog.cancel();
                    HomeActivity.user = users;
                    break;
                case -10:
                    mShapeLoadingDialog.cancel();
                    isLogin.isLogin("您还未登陆，请先登陆","登陆",2000,openClick);
                    break;
            }
            return false;
        }
    });


    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void initView(View view) {

        mShapeLoadingDialog = new ShapeLoadingDialog.Builder(getContext())
                .loadText(login)
                .build();

        mVpAllViewPager = (ViewPager) view.findViewById(R.id.vp_all_view_pager);

        ll_circle = (LinearLayout) view.findViewById(R.id.ll_circle);

        mTvPlace = view.findViewById(R.id.tv_place);

        mLlHome = view.findViewById(R.id.ll_home);

        viewPagerBitmapUtil = ViewPagerBitmapUtil.getViewPagerBitmapUtil(getContext());
        viewPagerBitmapUtil.setmPersonChange(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        homeFragmentDataPresenter = new HomeFragmentPresenter(strategyView,delicacyView,loginView,travelsView,mADView);
        homeFragmentDataPresenter.loadingData(Constant.GET_ALL_AD,"不需要",Constant.GET,false);
        homeFragmentDataPresenter.loadingData(Constant.STRATEGY,"PageSize=20&CurrentPage=1",Constant.GET,false);
        homeFragmentDataPresenter.loadingData(Constant.DELICACY,"PageSize=20&CurrentPage=1",Constant.GET,false);
        homeFragmentDataPresenter.loadingData(Constant.TRAVEL,"PageSize=50&CurrentPage=1",Constant.GET,false);

        String email = sharedUtils.readString(SharedUtils.USERNAME);
        String password = sharedUtils.readString(SharedUtils.PASSWORD);
        if (email!=null&&password!=null&&!email.equals("")&&!password.equals("")) {
            JSONObject object2 = new JSONObject();
            try {
                object2.put("LoginName", URLEncoder.encode(email));
                object2.put("PassWord", URLEncoder.encode(password));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String data = object2.toString();
            // Simulate network access.
            homeFragmentDataPresenter.loadingData(Constant.LOGIN, data, Constant.POST,false);
            mShapeLoadingDialog.show();
        }else {
            isLogin.isLogin("您还未登陆，请先登陆","登陆",2000,openClick);
        }

        mTvPlace.setOnClickListener(this);

        ViewFlipper viewFlipper = view.findViewById(R.id.propelling_movement_top_line);
        for (int i = 0;i < 3;i++){
            View flipView = LayoutInflater.from(getContext()).inflate(R.layout.item_filling_view,null);
            viewFlipper.addView(flipView);
        }




        LinearLayout homeStrategy = view.findViewById(R.id.home_strategy);
        TextView tvHomeStrategy = homeStrategy.findViewById(R.id.tv_name_item);
        tvHomeStrategy.setText(SharedUtils.TITLE_RECOMMEND_PLACE);
        mHomeStrategy = homeStrategy.findViewById(R.id.home_strategy_item);
        GridLayoutManager strategyLayoutManager = new GridLayoutManager(getContext(),2);
        strategyLayoutManager.setSmoothScrollbarEnabled(true);
        strategyLayoutManager.setAutoMeasureEnabled(true);
        mHomeStrategy.setLayoutManager(strategyLayoutManager);
        mHomeStrategy.setHasFixedSize(true);
        mHomeStrategy.setNestedScrollingEnabled(false);
        homeStrategy.findViewById(R.id.rl_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsLogin){
                    LogUtil.e("home");
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.STRATEGY_BUNDLE,strategy);
                    openActivity(getContext(), StrategyActivity.class,bundle,false);
                }else {
                    isLogin.isLogin("您还未登陆，请先登陆","登陆",2000,openClick);
                }
            }
        });

        LinearLayout homeTip = view.findViewById(R.id.home_tip);
        TextView tvHomeTip = homeTip.findViewById(R.id.tv_name_item);
        tvHomeTip.setText(SharedUtils.HOT_PLACE);
        mHomeTravels = homeTip.findViewById(R.id.home_strategy_item);
        GridLayoutManager travelLayoutManager = new GridLayoutManager(getContext(),2);
        travelLayoutManager.setSmoothScrollbarEnabled(true);
        travelLayoutManager.setAutoMeasureEnabled(true);
        mHomeTravels.setLayoutManager(travelLayoutManager);
        mHomeTravels.setHasFixedSize(true);
        mHomeTravels.setNestedScrollingEnabled(false);

        homeTip.findViewById(R.id.rl_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsLogin) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.TRAVEL_BUNDLE, travels);
                    openActivity(getContext(),TravelsActivity.class,bundle,false);
                }else {
                    isLogin.isLogin("您还未登陆，请先登陆","登陆",2000,openClick);
                }

            }
        });

        LinearLayout homeDelicacy = view.findViewById(R.id.home_delicacy);
        TextView tvHomeDelicacy = homeDelicacy.findViewById(R.id.tv_name_item);
        tvHomeDelicacy.setText(SharedUtils.DELICACY_NAME);
        mHomeDelicacy = homeDelicacy.findViewById(R.id.home_strategy_item);
        GridLayoutManager delicacyLayoutManager = new GridLayoutManager(getContext(),2);
        delicacyLayoutManager.setSmoothScrollbarEnabled(true);
        delicacyLayoutManager.setAutoMeasureEnabled(true);
        mHomeDelicacy.setLayoutManager(delicacyLayoutManager);
        mHomeDelicacy.setHasFixedSize(true);
        mHomeDelicacy.setNestedScrollingEnabled(false);

        homeDelicacy.findViewById(R.id.rl_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsLogin) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.DELICACY_BUNDLE, mDelicacy);
                    openActivity(getContext(), DelicacyActivity.class, bundle, false);
                }else {
                    isLogin.isLogin("您还未登陆，请先登陆","登陆",2000,openClick);
                }
            }
        });

        viewFlipper.setOnClickListener(this);
        view.findViewById(R.id.tuanzi_top_line).setOnClickListener(this);

        EditText etActionBar = view.findViewById(R.id.et_actionbar);
        setEdiText(etActionBar);

    }


    @Override
    public void onResume() {
        super.onResume();
        String place = sharedUtils.readString(SharedUtils.CITY_PLACE);
        if (place !=null){
            mTvPlace.setText(place);
        }
        if (SharedUtils.getSharedUtils(getContext()).readBoolean(SharedUtils.IS_UPDATE_TRAVEL)){
            homeFragmentDataPresenter.loadingData(Constant.TRAVEL,"PageSize=50&CurrentPage="+1,Constant.GET,false);
        }
    }

    @Override
    public void personChangeViewPager(int i) {
        viewPagerHanler.sendMessage(Message.obtain(viewPagerHanler, Constant.VIEW_PAGER_PERSON_CHANGE, i, 0));
    }

    @Override
    public void personDragging() {
        viewPagerHanler.sendEmptyMessage(Constant.VIEW_PAGER_RESUME);
    }

    @Override
    public void startViewPager() {
        viewPagerHanler.sendEmptyMessageDelayed(Constant.UPDATE_VIEW_PAGER_ITEM,Constant.UPDATE_TIME);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewPagerHanler.removeMessages(Constant.UPDATE_VIEW_PAGER_ITEM);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.propelling_movement_top_line:
                openActivity(getContext(), HotEventActivity.class,false);
                break;
            case R.id.tuanzi_top_line:
                openActivity(getContext(), HotEventActivity.class,false);
                break;

        }
    }

    ADView mADView = new ADView() {
        @Override
        public void AD(String result) {
            advertisement = (Advertisement) new Gson().fromJson(result, new TypeToken<Advertisement>() {}.getType());
            ads = advertisement.getEntity();
            if(advertisement.getStatus()==1)
                handler.sendEmptyMessage(Constant.HOME_AD_STATUS_NORMAL);
            else {
                handler.sendEmptyMessage(Constant.HOME_AD_STATUS_UNUSUAL);
            }
        }

        @Override
        public void ADFail() {

        }
    };

    StrategyView strategyView = new StrategyView() {
        @Override
        public void strategyFail() {

        }

        @Override
        public void showStrategyData(String list,int status) {
            strategy = (Strategy) new Gson().fromJson(list, new TypeToken<Strategy>() {}.getType());
            strategies = strategy.getEntity();
            if(strategy.getStatus()==1)
                handler.sendEmptyMessage(Constant.HOME_STRATEGY_STATUS_NORMAL);
            else {
                handler.sendEmptyMessage(Constant.HOME_STRATEGY_STATUS_UNUSUAL);
            }
        }
    };

    DelicacyView delicacyView = new DelicacyView() {
        @Override
        public void delicacyFail() {

        }

        @Override
        public void showDelicacyData(String delicacy,int status) {
            mDelicacy = (Delicacy) new Gson().fromJson(delicacy, new TypeToken<Delicacy>() {}.getType());
            delicacies = mDelicacy.getEntity();
            if(mDelicacy.getStatus()==1)
                handler.sendEmptyMessage(Constant.HOME_DELICACY_STATUS_NORMAL);
            else {
                handler.sendEmptyMessage(Constant.HOME_DELICACY_STATUS_UNUSUAL);
            }
        }
    };

    LoginView loginView = new LoginView() {
        @Override
        public void loginFail() {
            sharedUtils.saveBoolean(Constant.IS_LOGIN,false);
        }

        @Override
        public void showlogin(String user) {
            users = (User) new Gson().fromJson(user, new TypeToken<User>() {}.getType());
            if(users.getStatus()==1)
                handler.sendEmptyMessage(Constant.ISLOGIN);
            else {
                handler.sendEmptyMessage(Constant.NotLOGIN);
            }
        }
    };

    TravelsView travelsView = new TravelsView() {
        @Override
        public void travelsFail() {

        }

        @Override
        public void travelsData(String list,int status) {
            travels = (TravelsNote) new Gson().fromJson(list, new TypeToken<TravelsNote>() {}.getType());
            if(travels.getStatus()==1)
                handler.sendEmptyMessage(Constant.HOME_TRAVEL_STATUS_NORMAL);
            else {
                handler.sendEmptyMessage(Constant.HOME_TRAVEL_STATUS_UNUSUAL);
            }
        }
    };

    public IsLogin getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(IsLogin isLogin) {
        this.isLogin = isLogin;
    }
}
