package com.kiscode.jetpack.practice.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.jetpack.practice.BR;
import com.kiscode.jetpack.practice.R;
import com.kiscode.jetpack.practice.data.pojo.Plant;

import java.util.List;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/16 15:41
 **/
public class PlantListAdapter extends RecyclerView.Adapter<PlantListAdapter.PlantListViewHolder> {
    private List<Plant> mDatas;

    public PlantListAdapter(List<Plant> mDatas) {
        this.mDatas = mDatas;
    }

    public void setNewDatas(List<Plant> datas) {
        if (mDatas != datas) {
            mDatas = datas;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public PlantListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_plant_list, parent, false);
        return new PlantListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantListViewHolder holder, int position) {
        holder.binding.setVariable(BR.plantModel, mDatas.get(position));
        holder.binding.executePendingBindings(); //刷新
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class PlantListViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public PlantListViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}