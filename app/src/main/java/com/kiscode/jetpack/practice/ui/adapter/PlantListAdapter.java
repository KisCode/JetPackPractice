package com.kiscode.jetpack.practice.ui.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;
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
        holder.binding.setVariable(BR.clickListener, createOnClickListener(position));
//        holder.binding.setVariable(BR.onClickListener, createOnClickListener(position));
        holder.binding.executePendingBindings(); //刷新
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private View.OnClickListener createOnClickListener(final int position) {
        return v -> {
            Plant plant = mDatas.get(position);
            Bundle bundle = new Bundle();
            bundle.putString("plantId", plant.getPlantId());
            Navigation.findNavController(v).navigate(R.id.action_plantListFragment_to_plantDetailFragment, bundle);
        };
    }

    static class PlantListViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public PlantListViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}