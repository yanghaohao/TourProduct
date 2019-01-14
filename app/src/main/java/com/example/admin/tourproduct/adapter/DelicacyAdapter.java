package com.example.admin.tourproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.entry.Delicacy;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;
import com.example.admin.tourproduct.util.HttpUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class DelicacyAdapter extends RecyclerView.Adapter<DelicacyAdapter.ViewHolder>{

    private List<Delicacy.EntityBean> entity;
    private Context mContext;
    private RecyclerViewClickListener mRecyclerViewClickListener;
    private int type;

    public RecyclerViewClickListener getmRecyclerViewClickListener() {
        return mRecyclerViewClickListener;
    }

    public void setmRecyclerViewClickListener(RecyclerViewClickListener mRecyclerViewClickListener) {
        this.mRecyclerViewClickListener = mRecyclerViewClickListener;
    }

    public DelicacyAdapter(List<Delicacy.EntityBean> mDelicacy, Context mContext, int type) {
        this.entity = mDelicacy;
        this.mContext = mContext;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (type == 0)
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_delicacy,parent,false));
        else if (type == 1)
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_item_all,parent,false));
        else
            return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (type == 1){
            Glide.with(mContext).load(HttpUtil.DELICACY_DRAWABLE+entity.get(position).getDrawableURL()).into(holder.mIvHomeAll);
            holder.mIvHomeAll.setCornerRadius(30);
            holder.mTvHomeAllName.setText(entity.get(position).getCommercialTenantName());
            holder.mTvHomeAllContent.setText("评分高商户");
        }else if (type == 0){
            holder.mTvDelicacy.setText(entity.get(position).getCommercialTenantName());
            Glide.with(mContext).load(HttpUtil.DELICACY_DRAWABLE+entity.get(position).getDrawableURL()).into(holder.mIvDelicacy);
            holder.mIvDelicacy.setCornerRadius(30);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerViewClickListener.onClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (type == 0)
            return entity.size();
        else if (type == 1)
            return 2;
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView mIvDelicacy;
        TextView mTvDelicacy;
        RoundedImageView mIvHomeAll;
        TextView mTvHomeAllName,mTvHomeAllContent;
        public ViewHolder(View itemView) {
            super(itemView);

            mIvDelicacy = itemView.findViewById(R.id.iv_delicacy);
            mTvDelicacy = itemView.findViewById(R.id.tv_delicacy);
            mIvHomeAll = itemView.findViewById(R.id.iv_home_all);
            mTvHomeAllName = itemView.findViewById(R.id.tv_home_all_name);
            mTvHomeAllContent = itemView.findViewById(R.id.tv_home_all_content);
        }
    }
}
