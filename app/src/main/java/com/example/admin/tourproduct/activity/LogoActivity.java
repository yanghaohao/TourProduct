package com.example.admin.tourproduct.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.SharedUtils;

import base.activity.BaseActivity;

public class LogoActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvSkip;
    private Thread mThread;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            int i = msg.what;
            mTvSkip.setText("跳过"+(i+1));
            if (i == 2){
                if (SharedUtils.getSharedUtils(LogoActivity.this).readBoolean(Constant.FIRST))
                    openActivity(HomeActivity.class,true);
                else
                    openActivity(HomeActivity.class,true);
            }
            return false;
        }
    });

    @Override
    protected void intiView() {

        //动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);

        //最底部文字
//        TextView tv_logo = (TextView) findViewById(R.id.tv_logo);
        //设置为行楷
//        FontsUtil.getFontsUtils(this).setTextViewFonts(tv_logo);

        //app名字
//        TextView tv_app_name = (TextView) findViewById(R.id.tv_app_name);
//        FontsUtil.getFontsUtils(this).setTextViewFonts(tv_app_name);


        mTvSkip = findViewById(R.id.tv_skip);
        mTvSkip.setOnClickListener(this);

//        JPushInterface.setAlias( this, 2, "-1");
        hideActionBar();



//        startAnimation(tv_logo);
        mThread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    for (int i = 0;i < 3;i++){
                        sleep(1000);
                        handler.sendEmptyMessage(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        mThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mThread.interrupt();
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_logo);
    }

    private void startAnimation( View view){
        AnimatorSet set = new AnimatorSet();
        set.play(ObjectAnimator.ofFloat(view, "alpha", 0, 1));
        set.setDuration(3000);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (SharedUtils.getSharedUtils(LogoActivity.this).readBoolean(Constant.FIRST))
                    openActivity(LeaderActivity.class,true);
                else
                    openActivity(HomeActivity.class,true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_skip:
                if (SharedUtils.getSharedUtils(LogoActivity.this).readBoolean(Constant.FIRST))
                    openActivity(LeaderActivity.class,true);
                else
                    openActivity(HomeActivity.class,true);
                break;
        }
    }
}
