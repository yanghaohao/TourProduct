package com.example.admin.tourproduct.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by YH on 2018/4/13.
 */

public class LeaderFragmentAdapter extends FragmentStatePagerAdapter {

    List<Fragment> mList;
    public LeaderFragmentAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
