package com.example.admin.tourproduct.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.example.admin.tourproduct.interfaces.ADType;

import java.util.List;

/**
 * Created by YH on 2018/4/25.
 */

public class HomeLeaderAdapter extends PagerAdapter {

    private List<View> mViewList;
    private Context mContext;


    public HomeLeaderAdapter(List<View> mViewList, Context mContext ) {
        this.mViewList = mViewList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position%=mViewList.size();
        if (position<0){
            position = mViewList.size() + position;
        }
        ImageView view = (ImageView) mViewList.get(position);

        ViewParent viewParent = view.getParent();
        if (viewParent!=null){
            ViewGroup parent = (ViewGroup)viewParent;
            parent.removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

}
