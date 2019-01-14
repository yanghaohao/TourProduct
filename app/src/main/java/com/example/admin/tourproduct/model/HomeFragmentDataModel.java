package com.example.admin.tourproduct.model;

import android.util.Log;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.entry.Notice;
import com.example.admin.tourproduct.entry.Place;
import com.example.admin.tourproduct.interfaces.HomeFragmentDataTransmit;
import com.example.admin.tourproduct.util.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by YH on 2018/4/28.
 */

public class HomeFragmentDataModel implements HomeFragmentDataTransmit {

    @Override
    public void getData(final IsLoadingData loadingData, final int control, String postHead,int postOrGet) {
        String url = "";
        switch (control){
            case 0:
                url = HttpUtil.LOGIN;
                break;
            case 1:
                List<Place> places = new ArrayList<>();
                loadingData.place(places);
                break;
            case 2:
                url = HttpUtil.STRATEGY;
                break;
            case 3:
                url = HttpUtil.DELICACY;
                break;
            case 4:
                url = HttpUtil.GET_MESSAGE;
                break;
            case 5:
                url = HttpUtil.ENROLL;
                break;
            case 6:
                url = HttpUtil.SAVE_TRAVEL_NOTE;
                break;
            case 7:
                url = HttpUtil.TRAVEL;
                break;
            case 8:
                url = HttpUtil.SAVE_TRAVEL_DISCUSS;
                break;
            case 9:
                url = HttpUtil.GET_TRAVEL_BY_ID;
                break;
            case 10:
                url = HttpUtil.ADD_DELICACY_DISCUSS;
                break;
            case 11:
                url = HttpUtil.GET_ALL_AD;
                break;
            case 12:
                url = HttpUtil.GET_STRATEGY_BY_ID;
                break;
            case 13:
                url = HttpUtil.ALLOT_COUPON;
                break;
        }


        if(postOrGet == 1) {
            if (!url.equals("")) {
                Log.e("getData: ", url + "\n" + postHead);
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),
                        postHead);
                final Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        loadingData.failure();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseStr = response.body().string();
                        Log.e("onResponse: ", responseStr);
                        switch (control) {
                            case 0:
                                loadingData.login(responseStr);
                                break;
                            case 3:
                                loadingData.delicacy(responseStr);
                            case 4:
                                loadingData.notice(responseStr);
                                break;
                            case 5:
                                loadingData.enroll(responseStr);
                                break;
                            case 6:
                                loadingData.addTravel(responseStr);
                                break;
                            case 8:
                                loadingData.addTravelDiscuss(responseStr);
                                break;
                            case 10:
                                loadingData.addDelicacyDiscuss(responseStr);
                                break;
                            case 12:
                                loadingData.strategy(responseStr);
                                break;
                            case 13:
                                loadingData.addCoupon(responseStr);
                                break;
                        }
                    }
                });
            }
        }else if (postOrGet == 2&&postHead!=null){
                Log.e("getData: ", url + "\n" + postHead);
                OkHttpClient client = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url(postHead.equals("不需要")?url:url+"?"+postHead)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        loadingData.failure();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseStr = response.body().string();
                        Log.e("onResponse: ", responseStr);
                        switch (control) {
                            case 3:
                                loadingData.delicacy(responseStr);
                                break;
                            case 2:
                                loadingData.strategy(responseStr);
                                break;
                            case 7:
                                loadingData.travel(responseStr);
                                break;
                            case 9:
                                break;
                            case 11:
                                loadingData.getAD(responseStr);
                                break;

                        }
                    }
                });

        }

    }

    public interface IsLoadingData{
        void login(String user);
        void place(List<Place> list);
        void strategy(String list);
        void delicacy(String delicacy);
        void notice(String list);
        void enroll(String enroll);
        void addTravel(String result);
        void travel(String travel);
        void addTravelDiscuss(String travel);
        void addDelicacyDiscuss(String re);
        void getAD(String ad);
        void addCoupon(String coupon);
        void failure();
    }


}
