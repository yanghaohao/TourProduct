package com.example.admin.tourproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.entry.HotEvent;

import java.util.List;

public class HotEventAdapter extends RecyclerView.Adapter<HotEventAdapter.ViewHolder> {

    private List<HotEvent> list;
    private Context context;

    public HotEventAdapter(List<HotEvent> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HotEventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_hot_event,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HotEventAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
