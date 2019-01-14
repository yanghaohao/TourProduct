package com.example.admin.tourproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.entry.Discuss;

import java.util.List;

public class DiscussAdapter extends RecyclerView.Adapter<DiscussAdapter.ViewHolder> {

    private List<Discuss> discusses;
    private Context mContext;

    public DiscussAdapter(List<Discuss> discusses, Context mContext) {
        this.discusses = discusses;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_notice,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNoticeUserName.setText(discusses.get(position).getInputuser());
        holder.tvNoticeContent.setText(discusses.get(position).getContent());
        holder.mTvNoticeNoReadCount.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return discusses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNoticeUserName;
        TextView tvNoticeContent,mTvNoticeNoReadCount;
        public ViewHolder(View itemView) {
            super(itemView);

            tvNoticeContent = itemView.findViewById(R.id.tv_notice_content);
            tvNoticeUserName = itemView.findViewById(R.id.tv_notice_user_name);
            mTvNoticeNoReadCount = itemView.findViewById(R.id.tv_notice_no_read_count);
        }
    }

    public void addDiscuss(Discuss discuss){
        discusses.add(discuss);
        notifyDataSetChanged();
    }
}
