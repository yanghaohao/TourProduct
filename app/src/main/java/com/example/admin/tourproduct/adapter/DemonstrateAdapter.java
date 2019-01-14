package com.example.admin.tourproduct.adapter;


import android.content.Context;
import android.view.View;

import java.util.List;

import base.adapter.BaseRecyclerViewAdapter;

/**
 * Created by YH on 2018/4/16.
 */

public class DemonstrateAdapter<T> extends BaseRecyclerViewAdapter {

    private List<T> list;
    private Context context;

    public DemonstrateAdapter(List list, Context context) {
        super(list, context);
    }



    @Override
    protected int setLayout() {
        return 0;
    }

    @Override
    protected void bindView(BaseRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



//    class ViewHolder extends BaseRecyclerViewAdapter.ViewHolder{
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//        }
//    }
}
