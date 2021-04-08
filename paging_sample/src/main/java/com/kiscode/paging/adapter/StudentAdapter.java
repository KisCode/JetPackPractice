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
import com.kiscode.paging.model.pojo.Student;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/8 20:30
 */

public class StudentAdapter extends PagedListAdapter<Student, StudentAdapter.StudentViewHolder> {


    public StudentAdapter() {
        super(new DiffUtil.ItemCallback<Student>() {
            @Override
            public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getNumber() == newItem.getNumber();
            }
        });
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student item = getItem(position);

        //分页加载 容器数量预留，getItem可能为空
        if (item == null) {
            holder.tvContent.setText("loading");
        } else {
            holder.tvContent.setText(String.valueOf(item.getNumber()));
        }

    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvCell);
        }
    }
}
