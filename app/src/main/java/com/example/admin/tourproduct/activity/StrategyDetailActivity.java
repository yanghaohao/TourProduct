package com.example.admin.tourproduct.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.entry.Strategy;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.HttpUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import base.activity.BaseActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StrategyDetailActivity extends BaseActivity implements View.OnClickListener {

    private Strategy.EntityBean mStrategy;
    private List<String> graphicsAndTextures;
    private List<String> content;
    private static final int SUCCESS = 1;
    private static final int FALL = 2;
    private static final int CLICK = 3;
    private boolean isClick;
    private byte[] Picture;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                //加载网络成功进行UI的更新,处理得到的图片资源
                case SUCCESS:
                    //通过message，拿到字节数组
                    Picture = (byte[]) msg.obj;
                    break;
                //当加载网络失败执行的逻辑代码
                case FALL:
                    break;
                case CLICK:
                    isClick = true;
                    break;
            }
            if (isClick){
                if (Picture!=null){
                    Intent intent = new Intent(StrategyDetailActivity.this,PanoramaActivity.class);
                    intent.putExtra("packageName",Picture);
                    startActivity(intent);
                }
            }
            return false;
        }
    });

    private byte[] bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }


        @Override
    protected void intiView() {


        hideActionBar();

        showActionBar(true,false,"攻略详情",null);

        findViewById(R.id.ll_action_bar).setVisibility(View.INVISIBLE);

        Bundle bundle = getIntent().getExtras();
            if (bundle!=null) {
                mStrategy = (Strategy.EntityBean) bundle.getSerializable(Constant.STRATEGY_DETAIL_BUNDLE);
            }else{
                Toast.makeText(this, "暂时没有获取到数据请退出后重试", Toast.LENGTH_SHORT).show();
            }


        LinearLayout llStrategy = findViewById(R.id.ll_strategy);

        graphicsAndTextures = new ArrayList<>();
        content = new ArrayList<>();

        String[] a = mStrategy.getContent().split("http://203.195.237.243:8725/");
        String[] b = mStrategy.getContent().split("景区：");
        for (int i= 1; i < a.length;i++){
            try {
                boolean isPng = a[i].split(".png")[1] != null;
                graphicsAndTextures.add(HttpUtil.DRAWABLE_RES+a[i].split(".png")[0]+".png");
            }catch (Exception e){
                graphicsAndTextures.add(HttpUtil.DRAWABLE_RES+a[i].split(".jpg")[0]+".jpg");
            }
        }
        for (int i = 1; i < b.length;i++){
            content.add(b[i].split("结束")[0]);
        }
        for (int i = 0;i < graphicsAndTextures.size();i++){
            View view = LayoutInflater.from(this).inflate(R.layout.strategy_item,null);
            ImageView imageView = view.findViewById(R.id.iv_strategy_item);
            Glide.with(this).load(graphicsAndTextures.get(i)).into(imageView);
            TextView textView = view.findViewById(R.id.tv_strategy_item);
            textView.setText(content.get(i));
            llStrategy.addView(view);

        }
        ImageView ivPanorama = findViewById(R.id.iv_panorama);
        RelativeLayout rlHead = findViewById(R.id.rl_head);
        ivPanorama.setOnClickListener(this);
        Glide.with(this).load(HttpUtil.DRAWABLE_RES+mStrategy.getHeadSculpture()).into(ivPanorama);
        getPan(HttpUtil.DRAWABLE_RES+mStrategy.getPanorama());

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_strategy_detail);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.action_bar_menu:

                break;
            case R.id.iv_panorama:
                handler.sendEmptyMessage(CLICK);
                break;
        }
    }

    private void getPan(String path){
        //1.创建一个okhttpclient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request.Builder对象，设置参数，请求方式如果是Get，就不用设置，默认就是Get
        Request request = new Request.Builder()
                .url(path)
                .build();
        //3.创建一个Call对象，参数是request对象，发送请求
        Call call = okHttpClient.newCall(request);
        //4.异步请求，请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //得到从网上获取资源，转换成我们想要的类型
                byte[] Picture_bt = response.body().bytes();
                //通过handler更新UI
                Message message = handler.obtainMessage();
                message.obj = Picture_bt;
                message.what = SUCCESS;
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
