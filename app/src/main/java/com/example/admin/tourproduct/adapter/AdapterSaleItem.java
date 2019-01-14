 /*
  * Copyright (C) 2012 The TimeSale Project
  * All right reserved.
  * Version 1.00 2012-11-25
  * Author veally@foxmail.com
  */
package com.example.admin.tourproduct.adapter;

 import android.content.Context;
 import android.content.res.AssetManager;
 import android.graphics.BitmapFactory;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.BaseAdapter;
 import android.widget.ImageView;
 import android.widget.TextView;

 import com.example.admin.tourproduct.clock.CustomDigitalClock;
 import com.example.admin.tourproduct.entry.Delicacy;
 import com.example.admin.tourproduct.entry.ProductItem;
 import com.example.admin.tourproduct.R;

 import java.io.BufferedInputStream;
 import java.io.IOException;
 import java.util.List;

 /**
  *
  * @author veally@foxmail.com
  */
 public class AdapterSaleItem extends BaseAdapter {

     private Context mContext;
     private List<Delicacy.EntityBean.CouponListBean> mItems;
     private LayoutInflater inflater;
     private BufferedInputStream bis;
     private AssetManager assetManager;
     public AdapterSaleItem(Context context, List<Delicacy.EntityBean.CouponListBean> items) {
         this.mContext = context;
         this.mItems = items;
         inflater = LayoutInflater.from(mContext);
         assetManager = mContext.getAssets();
     }
     @Override
     public int getCount() {
         return mItems.size();
     }

     @Override
     public Object getItem(int position) {
         return null;
     }

     @Override
     public long getItemId(int position) {
         return 0;
     }


     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
         if(convertView == null) {
             convertView = inflater.inflate(R.layout.adapter_item, null);
         }
         ImageView image = (ImageView) convertView.findViewById(R.id.image);
         TextView tvCouponNameGou = convertView.findViewById(R.id.tv_coupon_name_gou);
         TextView tvCouponDescribe = convertView.findViewById(R.id.tv_coupon_describe);
         TextView tvTimeSale = convertView.findViewById(R.id.tv_time_sale);
         TextView tv_coupon_money_gou = convertView.findViewById(R.id.tv_coupon_money_gou);
         CustomDigitalClock remainTime = (CustomDigitalClock) convertView.findViewById(R.id.remainTime);
         image.setImageResource(R.mipmap.hots_s);

         tvCouponNameGou.setText(mItems.get(position).getName());
         tvCouponDescribe.setText("价值"+mItems.get(position).getDenomination()+"现在仅售"+mItems.get(position).getMoney()+"元");
         tv_coupon_money_gou.setText("￥"+mItems.get(position).getMoney()+"\t"+"不限时");
         remainTime.setEndTime(1);
         remainTime.setClockListener(new CustomDigitalClock.ClockListener() { // register the clock's listener

             @Override
             public void timeEnd() {
                 // The clock time is ended.
             }

             @Override
             public void remainFiveMinutes() {
                 // The clock time is remain five minutes.
             }
         });
         remainTime.setVisibility(View.GONE);
         return convertView;
     }

 }
