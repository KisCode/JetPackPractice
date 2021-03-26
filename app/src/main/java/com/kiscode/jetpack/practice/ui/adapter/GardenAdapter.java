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
import com.kiscode.jetpack.practice.data.pojo.GardenWithPlant;
import com.kiscode.jetpack.practice.ui.fragment.PlantDetailFragment;
import com.kiscode.jetpack.practice.viewmodels.GardenItemViewModel;

import java.util.List;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/16 15:41
 **/
public class GardenAdapter extends RecyclerView.Adapter<GardenAdapter.GardenViewHolder> {
    private List<GardenWithPlant> mDatas;

    public GardenAdapter(List<GardenWithPlant> mDatas) {
        this.mDatas = mDatas;
    }

    public void setNewDatas(List<GardenWithPlant> datas) {
        if (mDatas != datas) {
            mDatas = datas;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public GardenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_garden_with_plant, parent, false);
        return new GardenViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GardenViewHolder holder, int position) {
        holder.binding.setVariable(BR.gardenItemViewModel, new GardenItemViewModel(mDatas.get(position)));
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
            GardenWithPlant gardenWithPlant = mDatas.get(position);
            Bundle bundle = new Bundle();
            bundle.putString(PlantDetailFragment.KEY_PLANT_ID, gardenWithPlant.getPlant().getPlantId());
            Navigation.findNavController(v).navigate(R.id.action_gardenFragment_to_plantDetailFragment, bundle);
        };
    }

    static class GardenViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public GardenViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}