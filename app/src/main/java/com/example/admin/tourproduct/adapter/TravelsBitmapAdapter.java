package com.example.admin.tourproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.entry.TravelsNote;
import com.example.admin.tourproduct.util.HttpUtil;
import com.example.admin.tourproduct.util.LogUtil;

import java.util.List;


public class TravelsBitmapAdapter extends RecyclerView.Adapter<TravelsBitmapAdapter.ViewHolder> {

    private List<TravelsNote.EntityBean.BitmapsBean> list;
    private Context context;

    public TravelsBitmapAdapter(List<TravelsNote.EntityBean.BitmapsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_travels_bitmap,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LogUtil.e("onResponse", HttpUtil.DELICACY_DRAWABLE+"/"+list.get(position).getBitmap());
        Glide.with(context).load(HttpUtil.DELICACY_DRAWABLE+"/"+list.get(position).getBitmap()).into(holder.mIvTravelBitmap);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvTravelBitmap;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvTravelBitmap = (ImageView) itemView.findViewById(R.id.iv_travel_bitmap);
        }
    }
}
