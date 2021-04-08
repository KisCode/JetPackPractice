package com.kiscode.paging.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.paging.R;
import com.kiscode.paging.model.pojo.User;

import java.util.Objects;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/8 21:44
 */

public class UserAdapter extends PagedListAdapter<User, UserAdapter.MyViewHolder> {
    public UserAdapter() {
        super(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return Objects.equals(oldItem, newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.getNumber() == newItem.getNumber();
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = getItem(position);
        if (user == null) {
            return;
        }
        holder.tvNumber.setText(String.valueOf(user.getNumber()));
        holder.tvId.setText(user.getId());
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvId;
        private TextView tvNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNumber = itemView.findViewById(R.id.tv_number);
        }
    }
}
