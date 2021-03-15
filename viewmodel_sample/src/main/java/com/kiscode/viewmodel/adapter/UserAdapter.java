package com.kiscode.viewmodel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.viewmodel.R;
import com.kiscode.viewmodel.pojo.User;

import java.util.List;

/**
 * Description:
 * Author: kanjianxiong
 * Date : 2021/3/15 13:43
 **/
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> mDatas;

    public UserAdapter(List<User> mDatas) {
        this.mDatas = mDatas;
    }

    public void setNewDatas(List<User> newDatas) {
        if (mDatas != newDatas) {
            this.mDatas = newDatas;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mDatas.get(position);
        holder.tvName.setText("Name:" + user.getName());
        holder.tvAge.setText("Age:" + user.getAge());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvAge;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
        }
    }
} 