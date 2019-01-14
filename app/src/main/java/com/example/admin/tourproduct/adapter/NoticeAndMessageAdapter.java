package com.example.admin.tourproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.tourproduct.entry.NoticeAndMessage;

import java.util.List;

public class NoticeAndMessageAdapter extends RecyclerView.Adapter<NoticeAndMessageAdapter.ViewHolder> {

    private Context context;
    private List<NoticeAndMessage> noticeAndMessages;

    public NoticeAndMessageAdapter(Context context, List<NoticeAndMessage> noticeAndMessages) {
        this.context = context;
        this.noticeAndMessages = noticeAndMessages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
