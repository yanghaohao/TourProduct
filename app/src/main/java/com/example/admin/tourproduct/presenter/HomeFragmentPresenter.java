package com.example.admin.tourproduct.presenter;

import android.util.Log;

import com.example.admin.tourproduct.entry.Notice;
import com.example.admin.tourproduct.entry.Place;
import com.example.admin.tourproduct.interfaces.ADView;
import com.example.admin.tourproduct.interfaces.AddCoupon;
import com.example.admin.tourproduct.interfaces.AddDelicacyDiscuss;
import com.example.admin.tourproduct.interfaces.AddTravelDiscuss;
import com.example.admin.tourproduct.interfaces.AddTravelView;
import com.example.admin.tourproduct.interfaces.DelicacyView;
import com.example.admin.tourproduct.interfaces.EnrollView;
import com.example.admin.tourproduct.interfaces.HomeFragmentDataPresenter;
import com.example.admin.tourproduct.interfaces.LoginView;
import com.example.admin.tourproduct.interfaces.NoticeView;
import com.example.admin.tourproduct.interfaces.StrategyView;
import com.example.admin.tourproduct.interfaces.TravelsView;
import com.example.admin.tourproduct.model.HomeFragmentDataModel;
import com.example.admin.tourproduct.util.Constant;

import java.util.List;

/**
 * Created by YH on 2018/4/28.
 */

public class HomeFragmentPresenter implements HomeFragmentDataPresenter,HomeFragmentDataModel.IsLoadingData {

    private HomeFragmentDataModel mHomeFragmentDataTransmit;
    private int contral;
    private NoticeView mNoticeView;
    private AddTravelView mAddTravelView;
    private LoginView mLoginView;
    private EnrollView mEnrollView;
    private StrategyView mStrategyView;
    private DelicacyView mDelicacyView;
    private TravelsView mTravelsView;
    private AddDelicacyDiscuss mAddDelicacyDiscuss;
    private ADView mADView;
    private boolean isUpdate;
    private AddTravelDiscuss mAddTravelDiscuss;
    private AddCoupon mAddCoupon;
    public final static String LOAD = "数据加载中...";

    public HomeFragmentPresenter(AddTravelView mAddTravelView) {
        this.mAddTravelView = mAddTravelView;
        this.mHomeFragmentDataTransmit = new HomeFragmentDataModel();
    }

    public HomeFragmentPresenter(AddCoupon mAddCoupon) {
        this.mAddCoupon = mAddCoupon;
        this.mHomeFragmentDataTransmit = new HomeFragmentDataModel();
    }

    public HomeFragmentPresenter(AddTravelDiscuss mAddTravelDiscuss) {
        this.mAddTravelDiscuss = mAddTravelDiscuss;
        this.mHomeFragmentDataTransmit = new HomeFragmentDataModel();
    }

    public HomeFragmentPresenter(AddDelicacyDiscuss mAddDelicacyDiscuss) {
        this.mAddDelicacyDiscuss = mAddDelicacyDiscuss;
        this.mHomeFragmentDataTransmit = new HomeFragmentDataModel();
    }

    public HomeFragmentPresenter(LoginView mLoginView) {
        this.mLoginView = mLoginView;
        this.mHomeFragmentDataTransmit = new HomeFragmentDataModel();
    }

    public HomeFragmentPresenter(EnrollView mEnrollView,LoginView loginView) {
        this.mEnrollView = mEnrollView;
        this.mLoginView = loginView;
        this.mHomeFragmentDataTransmit = new HomeFragmentDataModel();
    }

    public HomeFragmentPresenter(StrategyView mStrategyView, DelicacyView mDelicacyView,LoginView loginView,TravelsView mTravelsView
            ,ADView mADView) {
        this.mStrategyView = mStrategyView;
        this.mDelicacyView = mDelicacyView;
        this.mLoginView = loginView;
        this.mTravelsView = mTravelsView;
        this.mADView = mADView;
        this.mHomeFragmentDataTransmit = new HomeFragmentDataModel();
    }

    public HomeFragmentPresenter(TravelsView mTravelsView) {
        this.mTravelsView = mTravelsView;
        this.mHomeFragmentDataTransmit = new HomeFragmentDataModel();
    }

    public HomeFragmentPresenter(AddTravelDiscuss mAllDataGet, TravelsView mTravelsView) {
        this.mAddTravelDiscuss = mAllDataGet;
        this.mTravelsView = mTravelsView;
        this.mHomeFragmentDataTransmit = new HomeFragmentDataModel();
    }

    public HomeFragmentPresenter(StrategyView mStrategyView) {
        this.mStrategyView = mStrategyView;
        this.mHomeFragmentDataTransmit = new HomeFragmentDataModel();
    }

    public HomeFragmentPresenter(DelicacyView mDelicacyView) {
        this.mDelicacyView = mDelicacyView;
        this.mHomeFragmentDataTransmit = new HomeFragmentDataModel();
    }

    public HomeFragmentPresenter( NoticeView mNoticeView) {
        this.mHomeFragmentDataTransmit = new HomeFragmentDataModel();
        this.mNoticeView = mNoticeView;
    }

    @Override
    public void loadingData(int control,String postHead,int postOrGet,boolean isUpdate) {
        Log.e( "loadingData: ", "走了这个方法");
        contral = control;
        this.isUpdate = isUpdate;

        mHomeFragmentDataTransmit.getData(this,control,postHead,postOrGet);
    }


    @Override
    public void login(String user) {
        mLoginView.showlogin(user);
    }

    @Override
    public void place(List<Place> list) {

    }

    @Override
    public void strategy(String list) {
        if (contral == 2&&isUpdate){
            mStrategyView.showStrategyData(list, Constant.UPDATE);
        }else {
            mStrategyView.showStrategyData(list,Constant.NOTUPDATE);
        }
    }

    @Override
    public void delicacy(String delicacy) {
        if (contral == 3&&isUpdate){
            mDelicacyView.showDelicacyData(delicacy, Constant.UPDATE);
        }else {
            mDelicacyView.showDelicacyData(delicacy,Constant.NOTUPDATE);
        }

    }

    @Override
    public void notice(String list) {
        mNoticeView.showNoticeData(list);
    }

    @Override
    public void enroll(String enroll) {
        mEnrollView.showEnroll(enroll);
    }

    @Override
    public void addTravel(String result) {
        mAddTravelView.addTravel(result);
    }

    @Override
    public void travel(String travel) {
        if (contral == 7&&isUpdate){
            mTravelsView.travelsData(travel, Constant.UPDATE);
        }else {
            mTravelsView.travelsData(travel, Constant.NOTUPDATE);
        }

    }

    @Override
    public void addCoupon(String coupon) {
        mAddCoupon.addCoupon(coupon);
    }

    @Override
    public void addTravelDiscuss(String travel) {
        mAddTravelDiscuss.addTravelDiscuss(travel);
    }

    @Override
    public void addDelicacyDiscuss(String re) {
        mAddDelicacyDiscuss.addDelicacyDiscuss(re);
    }

    @Override
    public void getAD(String ad) {
        mADView.AD(ad);
    }


    @Override
    public void failure() {
        switch (contral){
            case 0:
                mLoginView.loginFail();
                break;
            case 1:

                break;
            case 2:
                mStrategyView.strategyFail();
                break;
            case 3:
                mDelicacyView.delicacyFail();
                break;
            case 4:
                mNoticeView.noticFail();
                break;
            case 5:
                mEnrollView.enrollFail();
                break;
            case 6:
                mAddTravelView.addTravelFail();
                break;
            case 7:
                mTravelsView.travelsFail();
                break;
            case 8:
                mAddTravelDiscuss.addTravelDiscussFail();
                break;
            case 10:
                mAddDelicacyDiscuss.addDelicacyDiscussFail();
                break;
            case 11:
                mADView.ADFail();
                break;
            case 13:
                mAddCoupon.addCouponFail();
                break;
        }
    }
}
