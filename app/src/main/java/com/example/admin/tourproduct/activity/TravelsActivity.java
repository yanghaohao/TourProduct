package com.example.admin.tourproduct.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.adapter.AdapterWrapper;
import com.example.admin.tourproduct.adapter.TravelsItemAdapter;
import com.example.admin.tourproduct.entry.TravelsNote;
import com.example.admin.tourproduct.helper.SwipeToLoadHelper;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;
import com.example.admin.tourproduct.interfaces.TravelsView;
import com.example.admin.tourproduct.presenter.HomeFragmentPresenter;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.LogUtil;
import com.example.admin.tourproduct.util.SharedUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import base.activity.BaseActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TravelsActivity extends BaseActivity {

    private TravelsNote travelsNote;
    private List<TravelsNote.EntityBean> notes;
    private HomeFragmentPresenter homeFragmentPresenter;
    private RecyclerView allList;
    private int page = 1;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case Constant.STATUS_NORMAL:
                    notes = travelsNote.getEntity();
                    bindAdapter();
                    SharedUtils.getSharedUtils(TravelsActivity.this).saveBoolean(SharedUtils.IS_UPDATE_TRAVEL,false);
                    break;
                case Constant.UPDATE:
                    notes.addAll(travelsNote.getEntity());
                    bindAdapter();
                    break;
                case Constant.STATUS_UNUSUAL:
                    showShortToast("网络连接错误");
                    break;
                case Constant.STATUS_UPDATE_UNUSUAL:
                    showShortToast("没有更多了");
                    break;
            }
            return false;
        }
    });

    private TravelsView travelsView = new TravelsView() {
        @Override
        public void travelsFail() {

        }

        @Override
        public void travelsData(String list,int status) {
            travelsNote = (TravelsNote) new Gson().fromJson(list, new TypeToken<TravelsNote>() {}.getType());
            if(travelsNote.getStatus()!=-1&&status!=Constant.UPDATE)
                handler.sendEmptyMessage(Constant.STATUS_NORMAL);
            else if (travelsNote.getStatus()!=-1&&status==Constant.UPDATE)
                handler.sendEmptyMessage(Constant.UPDATE);
            else if (travelsNote.getStatus()==-1&&status==Constant.UPDATE)
                handler.sendEmptyMessage(Constant.STATUS_UPDATE_UNUSUAL);
            else
                handler.sendEmptyMessage(Constant.STATUS_UNUSUAL);
        }
    };

    @Override
    protected void intiView() {
        hideActionBar();

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            travelsNote = (TravelsNote) bundle.getSerializable(Constant.TRAVEL_BUNDLE);
            if (travelsNote != null){
                notes = travelsNote.getEntity();
                LogUtil.e( "intiView: ",travelsNote.getEntity().size()+"" );
            }
        }else{
            Toast.makeText(this, "暂时没有获取到数据请退出后重试", Toast.LENGTH_SHORT).show();
        }
        showActionBar(true, false, "攻略", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(AddTravelsActivity.class,false);
            }
        });

        allList = findViewById(R.id.travel_list);
        allList.setLayoutManager(new LinearLayoutManager(this));

        bindAdapter();
        homeFragmentPresenter = new HomeFragmentPresenter(travelsView);
    }

    private void bindAdapter(){
        TravelsItemAdapter travelsItemAdapter = new TravelsItemAdapter(notes,TravelsActivity.this,0);
        AdapterWrapper adapterWrapper = new AdapterWrapper(travelsItemAdapter);
//        SwipeToLoadHelper swipeToLoadHelper = new SwipeToLoadHelper(allList,adapterWrapper);
//        swipeToLoadHelper.setLoadMoreListener(loadMoreListener);
        allList.setAdapter(adapterWrapper);

        travelsItemAdapter.setmRecyclerViewClickListener(recyclerViewClickListener);
    }

    SwipeToLoadHelper.LoadMoreListener loadMoreListener = new SwipeToLoadHelper.LoadMoreListener() {
        @Override
        public void onLoad() {
            page++;
            homeFragmentPresenter.loadingData(Constant.TRAVEL,"PageSize=50&CurrentPage="+page,Constant.GET,true);
        }
    };

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_travels);
    }

    RecyclerViewClickListener recyclerViewClickListener = new RecyclerViewClickListener() {
        @Override
        public void onClick(View view, int position) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.TRAVEL_DETAIL_BUNDLE, notes.get(position));
            openActivity(TravelsDetailActivity.class,bundle,false);
        }

        @Override
        public void onLongClick(View view, int position) {

        }

        @Override
        public void onDoubleClick(View view, int position) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (SharedUtils.getSharedUtils(TravelsActivity.this).readBoolean(SharedUtils.IS_UPDATE_TRAVEL)){
            homeFragmentPresenter.loadingData(Constant.TRAVEL,"PageSize=50&CurrentPage="+page,Constant.GET,false);
        }
    }

    private void deleteTravel(String url,String postHead){
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

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();
                LogUtil.e("onResponse123: ", responseStr);

            }
        });
    }
}
