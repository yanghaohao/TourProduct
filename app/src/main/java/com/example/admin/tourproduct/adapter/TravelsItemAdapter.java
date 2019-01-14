package com.example.admin.tourproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.entry.TravelsNote;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;
import com.example.admin.tourproduct.util.HttpUtil;
import com.example.admin.tourproduct.util.LogUtil;
import com.example.admin.tourproduct.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TravelsItemAdapter extends RecyclerView.Adapter<TravelsItemAdapter.ViewHolder> {

    private List<TravelsNote.EntityBean> homeItems;
    private Context context;
    private RecyclerViewClickListener mRecyclerViewClickListener;
    private int type;

    public RecyclerViewClickListener getmRecyclerViewClickListener() {
        return mRecyclerViewClickListener;
    }

    public void setmRecyclerViewClickListener(RecyclerViewClickListener mRecyclerViewClickListener) {
        this.mRecyclerViewClickListener = mRecyclerViewClickListener;
    }

    public TravelsItemAdapter(List<TravelsNote.EntityBean> homeItems, Context context,int type) {
        this.homeItems = homeItems;
        this.context = context;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (type == 0)
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_travel,parent,false));
        else if (type == 1)
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item_all,parent,false));
        else
            return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (type == 0) {
            if (homeItems.get(position).getBitmaps().size() == 1){
                holder.ivTravelItemBitmap.setVisibility(View.VISIBLE);
                holder.mGvItemTravelPic.setVisibility(View.GONE);
                Glide.with(context).load(HttpUtil.DELICACY_DRAWABLE+"/"+homeItems.get(position).getBitmaps().get(0).getBitmap())
                        .into(holder.ivTravelItemBitmap);
            }else if (homeItems.get(position).getBitmaps().size() == 0){
                holder.ivTravelItemBitmap.setVisibility(View.GONE);
                holder.mGvItemTravelPic.setVisibility(View.GONE);
            }else {
                holder.ivTravelItemBitmap.setVisibility(View.GONE);
                holder.mGvItemTravelPic.setVisibility(View.VISIBLE);
            }
            holder.mTvItemTravelName.setText(homeItems.get(position).getName());
            holder.mTvItemTravelContent.setText(homeItems.get(position).getContent());
            TravelsBitmapAdapter travelsBitmapAdapter = new TravelsBitmapAdapter(homeItems.get(position).getBitmaps(),context);
            GridLayoutManager layoutManager = new GridLayoutManager(context,3) {
                @Override
                public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                    return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                }
            };
            holder.mGvItemTravelPic.setLayoutManager(layoutManager);
            holder.mGvItemTravelPic.setAdapter(travelsBitmapAdapter);
            String date = TimeUtil.contrasTime(homeItems.get(position).getInputdate());
            holder.mTvItemTravelTime.setText(date);
        }else if (type == 1){
            if (homeItems.get(position).getBitmaps().size() > 0) {
                LogUtil.e("onResponse333", HttpUtil.DELICACY_DRAWABLE + "/" + homeItems.get(position).getBitmaps().get(0).getBitmap());
                Glide.with(context).load(HttpUtil.DELICACY_DRAWABLE + "/" + homeItems.get(position).getBitmaps().get(0).getBitmap()).into(holder.mIvHomeAll);
            }else
                Glide.with(context).load(R.mipmap.head_sculpture).into(holder.mIvHomeAll);
            holder.mIvHomeAll.setCornerRadius(30);
            holder.mTvHomeAllName.setText(homeItems.get(position).getName());
            holder.mTvHomeAllContent.setText("热门游记");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mRecyclerViewClickListener.onClick(view,position);
                }
            });
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.e("asdhagsjhd",position+"");
                mRecyclerViewClickListener.onClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (type == 0)
            return homeItems.size();
        else if (type == 1)
            return 2;
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView mIvHomeAll;
        TextView mTvHomeAllName,mTvHomeAllContent;

        CircleImageView mClvItemTravelHead;
        TextView mTvItemTravelName,mTvItemTravelContent,mTvItemTravelTime;
        RecyclerView mGvItemTravelPic;
        ImageView ivTravelItemBitmap;
        public ViewHolder(View itemView) {
            super(itemView);

            mClvItemTravelHead = itemView.findViewById(R.id.clv_item_travel_head);
            mTvItemTravelName = itemView.findViewById(R.id.tv_item_travel_name);
            mTvItemTravelContent = itemView.findViewById(R.id.tv_item_travel_content);
            mGvItemTravelPic = itemView.findViewById(R.id.gv_item_travel_pic);
            mTvItemTravelTime = itemView.findViewById(R.id.tv_item_travel_time);
            ivTravelItemBitmap = itemView.findViewById(R.id.iv_travel_item_bitmap);

            mIvHomeAll = itemView.findViewById(R.id.iv_home_all);
            mTvHomeAllName = itemView.findViewById(R.id.tv_home_all_name);
            mTvHomeAllContent = itemView.findViewById(R.id.tv_home_all_content);
        }
    }



}
