package com.example.admin.tourproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.entry.Notice;
import com.example.admin.tourproduct.interfaces.RecyclerViewClickListener;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder>{

    private Context mContext;
    private List<Notice.EntityBean> notices;
    private RecyclerViewClickListener mRecyclerViewClickListener;

    public RecyclerViewClickListener getmRecyclerViewClickListener() {
        return mRecyclerViewClickListener;
    }

    public void setmRecyclerViewClickListener(RecyclerViewClickListener mRecyclerViewClickListener) {
        this.mRecyclerViewClickListener = mRecyclerViewClickListener;
    }

    public NoticeAdapter(Context mContext, List<Notice.EntityBean> notices) {
        this.mContext = mContext;
        this.notices = notices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_notice,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
//        holder.mIvHeadSculpture.setImageResource(notices.get(position).getUserIcon());
//        holder.mTvNoticeUserName.setText(notices.get(position).getSender());
//        holder.mTvNoticeContent.setText(notices.get(position).getMesContent());
//        if (!notices.get(position).isRead()){
//            holder.mTvNoticeNoReadCount.setVisibility(View.VISIBLE);
//        }else {
//            holder.mTvNoticeNoReadCount.setVisibility(View.INVISIBLE);
//        }
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mRecyclerViewClickListener.onClick(v,position);
//                if (!notices.get(position).isRead()){
//                    holder.mTvNoticeNoReadCount.setVisibility(View.INVISIBLE);
//                }else {
//                    holder.mTvNoticeNoReadCount.setVisibility(View.INVISIBLE);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return notices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvHeadSculpture;
        private TextView mTvNoticeUserName,mTvNoticeContent,mTvNoticeNoReadCount;
        public ViewHolder(View itemView) {
            super(itemView);
            mIvHeadSculpture = itemView.findViewById(R.id.iv_head_sculpture);
            mTvNoticeContent = itemView.findViewById(R.id.tv_notice_content);
            mTvNoticeNoReadCount = itemView.findViewById(R.id.tv_notice_no_read_count);
            mTvNoticeUserName = itemView.findViewById(R.id.tv_notice_user_name);
        }
    }
}
