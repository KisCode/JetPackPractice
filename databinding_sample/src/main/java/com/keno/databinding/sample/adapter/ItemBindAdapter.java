package com.keno.databinding.sample.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.keno.databinding.sample.BR;
import com.keno.databinding.sample.R;
import com.keno.databinding.sample.pojo.UserInfo;

import java.util.List;

/****
 * ProjectName: JetPackPractice
 * Package: com.keno.databinding.sample.adapter
 * ClassName: ItemBindAdapter
 * Description: DataBinding适配 RecyclerView.Adapter
 * Author:  Administrator
 * CreateDate: 2020/4/4 12:29
 */

public class ItemBindAdapter extends RecyclerView.Adapter<ItemBindAdapter.ItemBindViewHolder> {

    private Context mContext;
    private List<UserInfo> mDatas;

    public ItemBindAdapter(Context mContext, List<UserInfo> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public ItemBindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_text, parent, false);
        return new ItemBindViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemBindViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.userinfoItem, mDatas.get(position));
        holder.getBinding().executePendingBindings(); //闪烁
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void add(UserInfo userInfo) {
        mDatas.add(userInfo);
        notifyDataSetChanged();
    }

    class ItemBindViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public ItemBindViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
