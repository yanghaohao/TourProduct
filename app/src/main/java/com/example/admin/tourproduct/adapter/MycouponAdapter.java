package com.example.admin.tourproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.entry.Delicacy;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;

import java.util.List;

public class MycouponAdapter extends RecyclerView.Adapter<MycouponAdapter.ViewHolder> {

    private Context context;
    private List<Delicacy.EntityBean.CouponListBean> coupons;
    private RecyclerViewClickListener mRecyclerViewClickListener;

    public MycouponAdapter(Context context, List<Delicacy.EntityBean.CouponListBean> coupons) {
        this.context = context;
        this.coupons = coupons;
    }

    public RecyclerViewClickListener getmRecyclerViewClickListener() {
        return mRecyclerViewClickListener;
    }

    public void setmRecyclerViewClickListener(RecyclerViewClickListener mRecyclerViewClickListener) {
        this.mRecyclerViewClickListener = mRecyclerViewClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_my_coupon,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.itemTvCouponName.setText(coupons.get(position).getName());
        holder.itemTvCouponTime.setText("有效期至"+coupons.get(position).getTime());
        holder.itemTvCouponMoney.setText("￥"+coupons.get(position).getDenomination());
        holder.itemTvCouponRule.setText(coupons.get(position).getLimit());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerViewClickListener.onClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return coupons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //item_tv_coupon_name,item_tv_coupon_time,item_tv_coupon_money,item_tv_coupon_rule
        TextView itemTvCouponName,itemTvCouponTime,itemTvCouponMoney,itemTvCouponRule;
        public ViewHolder(View itemView) {
            super(itemView);

            itemTvCouponName = itemView.findViewById(R.id.item_tv_coupon_name);
            itemTvCouponTime = itemView.findViewById(R.id.item_tv_coupon_time);
            itemTvCouponMoney = itemView.findViewById(R.id.item_tv_coupon_money);
            itemTvCouponRule = itemView.findViewById(R.id.item_tv_coupon_rule);
        }
    }
}
