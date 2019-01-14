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
import com.example.admin.tourproduct.entry.Strategy;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;
import com.example.admin.tourproduct.util.HttpUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * Created by YH on 2018/4/25.
 */
public class StrategyAdapter extends RecyclerView.Adapter<StrategyAdapter.ViewHolder> {

    private Context mContext;
    private List<Strategy.EntityBean> mStrategy;
    private RecyclerViewClickListener mRecyclerViewClickListener;
    private int type;

    public RecyclerViewClickListener getmRecyclerViewClickListener() {
        return mRecyclerViewClickListener;
    }

    public void setmRecyclerViewClickListener(RecyclerViewClickListener mRecyclerViewClickListener) {
        this.mRecyclerViewClickListener = mRecyclerViewClickListener;
    }

    public StrategyAdapter(Context mContext, List<Strategy.EntityBean> mStrategy, int type) {
        this.mContext = mContext;
        this.mStrategy = mStrategy;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (type == 0)
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_strategy,parent,false));
        else if (type == 1)
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_item_all,parent,false));
        else
            return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (type == 0) {
            holder.mTvStrategy.setText(mStrategy.get(position).getName());
            holder.mIvStrategy.setCornerRadius(30);
            Glide.with(mContext).load(HttpUtil.DRAWABLE_RES+mStrategy.get(position).getHeadSculpture()).into(holder.mIvStrategy);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerViewClickListener.onClick(v, position);
                }
            });
        }else if (type == 1){
            Glide.with(mContext).load(HttpUtil.DRAWABLE_RES+mStrategy.get(position).getHeadSculpture()).into(holder.mIvHomeAll);
            holder.mIvHomeAll.setCornerRadius(30);
            holder.mTvHomeAllName.setText(mStrategy.get(position).getName());
            holder.mTvHomeAllContent.setText(mStrategy.get(position).getIntroduce());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mRecyclerViewClickListener.onClick(view,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (type == 0)
            return mStrategy.size();
        else if (type == 1)
            return 2;
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView mIvStrategy;
        TextView mTvStrategy;
        RoundedImageView mIvHomeAll;
        TextView mTvHomeAllName,mTvHomeAllContent;
        public ViewHolder(View itemView) {
            super(itemView);

            mIvStrategy = itemView.findViewById(R.id.iv_strategy);
            mTvStrategy = itemView.findViewById(R.id.tv_strategy);
            mIvHomeAll = itemView.findViewById(R.id.iv_home_all);
            mTvHomeAllName = itemView.findViewById(R.id.tv_home_all_name);
            mTvHomeAllContent = itemView.findViewById(R.id.tv_home_all_content);
        }
    }
}
