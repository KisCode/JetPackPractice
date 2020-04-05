package com.keno.databinding.sample.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keno.databinding.sample.R;
import com.keno.databinding.sample.pojo.UserInfo;

import java.util.List;

/**
* Description: ItemAdapter
* Author: keno
* CreateDate: 2020/4/4 12:02
*/

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context mContext;
    private List<UserInfo> mDatas;

    public ItemAdapter(Context mContext, List<UserInfo> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_text, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.tvName.setText(mDatas.get(position).getName());
        holder.tvEmail.setText(mDatas.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void add(UserInfo userInfo) {
        mDatas.add(userInfo);
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvEmail;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_item);
            tvEmail = itemView.findViewById(R.id.tv_email_item);
        }
    }
}
