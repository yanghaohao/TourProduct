package com.example.admin.tourproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.entry.Place;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;

import java.util.List;

/**
 * Created by YH on 2018/4/25.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder>  {

    private List<Place> mList;
    private Context mContext;
    private RecyclerViewClickListener mRecyclerViewClickListener;

    public PlaceAdapter(List<Place> mList, Context context) {
        this.mList = mList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.place_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mTvPlace.setText(mList.get(position).getName());
        holder.mIvPlace.setBackgroundResource(mList.get(position).getIcon());
//        holder.mLiPlace.set
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerViewClickListener.onClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvPlace;
        ImageView mIvPlace;
        RelativeLayout mLiPlace;
        public ViewHolder(View itemView) {
            super(itemView);

            mIvPlace = itemView.findViewById(R.id.iv_place);
            mTvPlace = itemView.findViewById(R.id.tv_place_item);
            mLiPlace = itemView.findViewById(R.id.ll_place_item);
        }
    }

    public RecyclerViewClickListener getmRecyclerViewClickListener() {
        return mRecyclerViewClickListener;
    }

    public void setmRecyclerViewClickListener(RecyclerViewClickListener mRecyclerViewClickListener) {
        this.mRecyclerViewClickListener = mRecyclerViewClickListener;
    }

    @Override
    public String toString() {
        return "PlaceAdapter{" +
                "mList=" + mList +
                ", mContext=" + mContext +
                ", mRecyclerViewClickListener=" + mRecyclerViewClickListener +
                '}';
    }
}
