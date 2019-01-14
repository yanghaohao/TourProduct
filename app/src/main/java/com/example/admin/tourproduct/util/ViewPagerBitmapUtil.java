package com.example.admin.tourproduct.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.activity.ADInfoActivity;
import com.example.admin.tourproduct.activity.HomeActivity;
import com.example.admin.tourproduct.activity.LeaderActivity;
import com.example.admin.tourproduct.adapter.HomeLeaderAdapter;
import com.example.admin.tourproduct.adapter.LeaderAdapter;
import com.example.admin.tourproduct.entry.Advertisement;
import com.example.admin.tourproduct.interfaces.PersonChange;
import com.example.admin.tourproduct.interfaces.PersonChanged;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YH on 2018/4/25.
 */

public class ViewPagerBitmapUtil implements View.OnClickListener {

    private Context mContext;
    private static ViewPagerBitmapUtil sViewPagerBitmapUtil;
    private ImageView[] dotViews;
    private PersonChange mPersonChange;
    private PersonChanged mPersonChanged;

    public PersonChanged getmPersonChanged() {
        return mPersonChanged;
    }

    public void setmPersonChanged(PersonChanged mPersonChanged) {
        this.mPersonChanged = mPersonChanged;
    }

    private ViewPagerBitmapUtil(Context mContext) {
        this.mContext = mContext;
    }

    public PersonChange getmPersonChange() {
        return mPersonChange;
    }

    public void setmPersonChange(PersonChange mPersonChange) {
        this.mPersonChange = mPersonChange;
    }

    //单例,懒汉式
    public static ViewPagerBitmapUtil getViewPagerBitmapUtil(Context context) {
        if (sViewPagerBitmapUtil == null) {
            synchronized (ViewPagerBitmapUtil.class) {
                if (sViewPagerBitmapUtil == null) {
                    sViewPagerBitmapUtil = new ViewPagerBitmapUtil(context);
                }
            }
        }
        return sViewPagerBitmapUtil;
    }

    private List<View> initLeaderData() {
        List<View> list = new ArrayList<>();
        View view1 = LayoutInflater.from(mContext).inflate(R.layout.item_leader_1,null);
        View view2 = LayoutInflater.from(mContext).inflate(R.layout.item_leader_2,null);
        View view3 = LayoutInflater.from(mContext).inflate(R.layout.item_leader_3,null);
        list.add(view1);
        list.add(view2);
        list.add(view3);
        Button button = view3.findViewById(R.id.btn_leader);
        button.setOnClickListener(this);
        return list;
    }

    public void bindLeaderViewPager(ViewPager viewPager, LinearLayout linearLayout , final int[] drawableRes) {
        List<View> list = initLeaderData();
        LeaderAdapter homeLeaderAdapter = new LeaderAdapter(list,mContext);
        viewPager.setAdapter(homeLeaderAdapter);

        initDots(list, drawableRes, linearLayout);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("onPageSelected: ",position + " " );
                for (int i = 0; i < dotViews.length; i++) {
                    if ((position % drawableRes.length) == i) {
                        dotViews[i].setSelected(true);
                    } else {
                        dotViews[i].setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public void bindHomeAdViewPager(final ViewPager viewPager, final List<View> list, LinearLayout linearLayout  , final List<Advertisement.EntityBean> drawableRes) {
        for (int i = 0; i < drawableRes.size(); i++) {
            ImageView view = new ImageView(mContext);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            LogUtil.e("dasdgasu", HttpUtil.DRAWABLE_RES+drawableRes.get(i).getDrawableRes());
            Glide.with(mContext).load(HttpUtil.DRAWABLE_RES+drawableRes.get(i).getDrawableRes()).into(view);
            list.add(view);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(mContext,ADInfoActivity.class);
                    intent.putExtra(Constant.AD_PICTURE,drawableRes.get(finalI));
                    LogUtil.e("ashgdj", finalI +"");
                    mContext.startActivity(intent);
                }
            });
        }
        HomeLeaderAdapter homeLeaderAdapter = new HomeLeaderAdapter(list, mContext);
        viewPager.setAdapter(homeLeaderAdapter);

        initDots(list, drawableRes, linearLayout);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("onPageSelected: ",position + " " );
                for (int i = 0; i < dotViews.length; i++) {
                    if ((position % drawableRes.size()) == i) {
                        dotViews[i].setSelected(true);
                    } else {
                        dotViews[i].setSelected(false);
                    }
                }
                mPersonChange.personChangeViewPager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                    switch (state){
                        case ViewPager.SCROLL_STATE_DRAGGING:
                            mPersonChange.personDragging();
                            break;
                        case ViewPager.SCROLL_STATE_IDLE:
                            mPersonChange.startViewPager();
                            break;
                    }

            }
        });
        mPersonChange.startViewPager();
    }

//    public void bindDelicacyAdViewPager(ViewPager viewPager, List<View> list, LinearLayout linearLayout, final List<Advertisement> drawableRes, @NonNull final Class<?> cls) {
//        for (int i = 0; i < drawableRes.size(); i++) {
//            ImageView view = new ImageView(mContext);
//            view.setScaleType(ImageView.ScaleType.FIT_XY);
//            Glide.with(mContext).load(drawableRes.get(i).getDrawableRes()).into(view);
//            list.add(view);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(mContext,cls);
//                    mContext.startActivity(i);
//                }
//            });
//        }
//        HomeLeaderAdapter homeLeaderAdapter = new HomeLeaderAdapter(list, mContext);
//        viewPager.setAdapter(homeLeaderAdapter);
//
//        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(20, 20);
//        mParams.setMargins(10, 0, 10, 0);//设置小圆点左右之间的间隔
//        final ImageView[] dotViews = new ImageView[drawableRes.size()];
//
//        //判断小圆点的数量，从0开始，0表示第一个
//        for (int i = 0; i < list.size(); i++) {
//            ImageView imageView = new ImageView(mContext);
//            imageView.setLayoutParams(mParams);
//            imageView.setImageResource(R.drawable.small_circle);
//            if (i == 0) {
//                imageView.setSelected(true);//默认启动时，选中第一个小圆点
//            } else {
//                imageView.setSelected(false);
//            }
//            dotViews[i] = imageView;//得到每个小圆点的引用，用于滑动页面时，（onPageSelected方法中）更改它们的状态。
//            linearLayout.addView(imageView);//添加到布局里面显示
//        }
//
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                Log.e("onPageSelected1: ",position + " " );
//                for (int i = 0; i < dotViews.length; i++) {
//                    if ((position % drawableRes.size()) == i) {
//                        dotViews[i].setSelected(true);
//                    } else {
//                        dotViews[i].setSelected(false);
//                    }
//                }
//                mPersonChanged.personChangeViewPager(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                    switch (state){
//                        case ViewPager.SCROLL_STATE_DRAGGING:
//                            mPersonChanged.personDragging();
//                            break;
//                        case ViewPager.SCROLL_STATE_IDLE:
//                            mPersonChanged.startViewPager();
//                            break;
//                    }
//
//            }
//        });
//        mPersonChanged.startViewPager();
//
//    }

    private void initDots(List<View> imgs, List<Advertisement.EntityBean> drawableRes, LinearLayout layout) {
            LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(20, 20);
            mParams.setMargins(10, 0, 10, 0);//设置小圆点左右之间的间隔
            dotViews = new ImageView[drawableRes.size()];
            //判断小圆点的数量，从0开始，0表示第一个
            for (int i = 0; i < imgs.size(); i++) {
                ImageView imageView = new ImageView(mContext);
                imageView.setLayoutParams(mParams);
                imageView.setImageResource(R.drawable.small_circle);
                if (i == 0) {
                    imageView.setSelected(true);//默认启动时，选中第一个小圆点
                } else {
                    imageView.setSelected(false);
                }
                dotViews[i] = imageView;//得到每个小圆点的引用，用于滑动页面时，（onPageSelected方法中）更改它们的状态。
                layout.addView(imageView);//添加到布局里面显示
            }


    }

    private void initDots(List<View> imgs, int[] drawableRes, LinearLayout layout) {
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(20, 20);
        mParams.setMargins(10, 0, 10, 0);//设置小圆点左右之间的间隔
        dotViews = new ImageView[drawableRes.length];
        //判断小圆点的数量，从0开始，0表示第一个
        for (int i = 0; i < imgs.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(mParams);
            imageView.setImageResource(R.drawable.small_circle);
            if (i == 0) {
                imageView.setSelected(true);//默认启动时，选中第一个小圆点
            } else {
                imageView.setSelected(false);
            }
            dotViews[i] = imageView;//得到每个小圆点的引用，用于滑动页面时，（onPageSelected方法中）更改它们的状态。
            layout.addView(imageView);//添加到布局里面显示
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_leader:
                SharedUtils.getSharedUtils(mContext).saveBoolean(Constant.FIRST,true);
                Intent intent = new Intent(mContext, HomeActivity.class);
                mContext.startActivity(intent);
                Activity activity = new LeaderActivity();
                activity.finish();
                break;
        }
    }
}
