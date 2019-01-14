package com.example.admin.tourproduct.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.entry.Advertisement;
import com.example.admin.tourproduct.interfaces.StrategyView;
import com.example.admin.tourproduct.presenter.HomeFragmentPresenter;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.LogUtil;

import base.activity.BaseActivity;

public class ADInfoActivity extends BaseActivity implements View.OnClickListener {


    private Advertisement.EntityBean advertisement;
    public static final int MSG_PROGRESS = 1;
    public static final int MSG_PROGRESS_GONE = 2;
    private ProgressBar mProgressbar;
    private Handler mHandle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case MSG_PROGRESS:
                    int progress = msg.arg1;
                    mProgressbar.setProgress(progress);

                    if (mProgressbar.getVisibility() != View.VISIBLE){
                        mProgressbar.setVisibility(View.VISIBLE);
                    }
                    break;
                case MSG_PROGRESS_GONE:
                    mProgressbar.setVisibility(View.GONE);
                default:
                    break;
            }
            return false;
        }
    });

    @Override
    protected void intiView() {
        hideActionBar();


        showActionBar(true,false,"活动",null);

        advertisement = (Advertisement.EntityBean) getIntent().getSerializableExtra(Constant.AD_PICTURE);
        LogUtil.e("ashgdj",advertisement.getType()+"");
        switch (advertisement.getType()){
            case 0:
                haveInterLinkageAD();
                break;
            case 1:
                haveNoInterLinkageAD();
                break;
        }

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_adinfo);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.action_bar_cancel:
                finish();
                break;
        }
    }

    private void haveInterLinkageAD(){
        LinearLayout llAD = findViewById(R.id.ll_ad);
        llAD.setVisibility(View.VISIBLE);
        showActionBar(true,false,"广告详情",null);
        mProgressbar = findViewById(R.id.pb_ad);
        WebView wbAD = findViewById(R.id.wv_ad);
        WebSettings setting = wbAD.getSettings();
        setting.setJavaScriptEnabled(true);//支持js
        setting.setSupportZoom(false);//不支持缩放
        setting.setBuiltInZoomControls(false);//不出现放大和缩小的按钮
        setting.setCacheMode(WebSettings.LOAD_NO_CACHE);//不设置网络缓存

        wbAD.setWebViewClient(new WebViewClient() {
        });//IE内核

        wbAD.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mHandle.obtainMessage(MSG_PROGRESS,newProgress,0).sendToTarget();
                if (newProgress == 100){
                    mHandle.sendEmptyMessageDelayed(MSG_PROGRESS_GONE,800);
                }
            }
        });//谷歌内核

        wbAD.loadUrl(advertisement.getUrl());
    }

    private void haveNoInterLinkageAD(){
        LinearLayout asdAd = findViewById(R.id.asd_ad);
        asdAd.setVisibility(View.VISIBLE);
        showActionBar(true,false,"广告详情",null);
        findViewById(R.id.tv_ad_info).setVisibility(View.GONE);
        HomeFragmentPresenter homeFragmentPresenter = new HomeFragmentPresenter(strategyView);
        homeFragmentPresenter.loadingData(Constant.GET_STRATEGY_BY_ID,"Id="+advertisement.getAD(),Constant.GET,false);
    }

    StrategyView strategyView = new StrategyView() {
        @Override
        public void strategyFail() {

        }

        @Override
        public void showStrategyData(String list, int status) {

        }
    };
}
