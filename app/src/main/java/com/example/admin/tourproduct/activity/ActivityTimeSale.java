package com.example.admin.tourproduct.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.tourproduct.adapter.AdapterSaleItem;
import com.example.admin.tourproduct.entry.Delicacy;
import com.example.admin.tourproduct.entry.ProductItem;
import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.util.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import base.activity.BaseActivity;

public class ActivityTimeSale extends BaseActivity {

	private ListView listView;
	private AdapterSaleItem adapterSaleItem;
	Random random = new Random();
	private Delicacy delicacy;
	private List<Delicacy.EntityBean.CouponListBean> items;

	@Override
	protected void intiView() {
		hideActionBar();

		showActionBar(true, false, "团购秒杀", null);
		listView = (ListView) findViewById(R.id.listView);


		Bundle bundle = getIntent().getExtras();
		delicacy = (Delicacy) bundle.getSerializable(Constant.DELICACY_DETAIL_BUNDLE);

		items = new ArrayList<>();
		for (int i = 0;i <delicacy.getEntity().size();i++){
			items.addAll(delicacy.getEntity().get(i).getCouponList());
		}

		adapterSaleItem = new AdapterSaleItem(this, items);
		listView.setAdapter(adapterSaleItem);
		listView.setOnItemClickListener(onItemClickListener);
	}

	AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
			Bundle bundle = new Bundle();
			bundle.putSerializable(Constant.CONPON, items.get(i));
			for (int j = 0; j < items.size();j++){
				if (delicacy.getEntity().get(j).getId() == items.get(i).getShopId()){
					bundle.putString(Constant.CONPON_PICTURE,delicacy.getEntity().get(j).getDrawableURL());
				}
			}
			bundle.putInt(Constant.SHOPID,items.get(i).getShopId());
			openActivity(CouponActivity.class,bundle,false );
		}
	};
	@Override
	protected void setLayout() {
		setContentView(R.layout.activity_time_sale);
	}

	private long getRandomTime() {
    	long curTime = System.currentTimeMillis();
    	long [] t = new long[] {500,200,640,120,300,450,100,1000,1540,2500};
    	return curTime + t[random.nextInt(9)]*1000;
    }
    private String [] getImages() {
    	String [] images = null;
    	try {
			images = this.getApplicationContext().getAssets().list("images");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return images;
    }


   

    
}
