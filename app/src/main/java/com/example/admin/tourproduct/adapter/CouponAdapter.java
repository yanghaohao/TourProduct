package com.example.admin.tourproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.entry.Delicacy;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;

import java.util.List;


public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {

    private List<Delicacy.EntityBean.CouponListBean> couponListBeans;
    private Context context;
    private RecyclerViewClickListener mRecyclerViewClickListener;

    public RecyclerViewClickListener getmRecyclerViewClickListener() {
        return mRecyclerViewClickListener;
    }

    public void setmRecyclerViewClickListener(RecyclerViewClickListener mRecyclerViewClickListener) {
        this.mRecyclerViewClickListener = mRecyclerViewClickListener;
    }

    public CouponAdapter(List<Delicacy.EntityBean.CouponListBean> couponListBeans, Context context) {
        this.couponListBeans = couponListBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_coupon,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(couponListBeans.get(position).getBitmap()).into(holder.imageView);
        holder.tvItemCouponName.setText(couponListBeans.get(position).getName());
        holder.tvItemCouponDenomination.setText(couponListBeans.get(position).getDenomination()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerViewClickListener.onClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return couponListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvItemCouponName,tvItemCouponDenomination;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_item_coupon);
            tvItemCouponName = itemView.findViewById(R.id.tv_item_coupon_name);
            tvItemCouponDenomination = itemView.findViewById(R.id.tv_item_coupon_denomination);
        }
    }
}
