package com.kiscode.jetpack.practice.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.kiscode.jetpack.practice.R;
import com.kiscode.jetpack.practice.data.pojo.Plant;
import com.kiscode.jetpack.practice.databinding.FragmentGardenBinding;
import com.kiscode.jetpack.practice.databinding.FragmentPlantListBinding;
import com.kiscode.jetpack.practice.ui.adapter.PlantListAdapter;
import com.kiscode.jetpack.practice.viewmodels.PlantListViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Description: 植物列表
 * Author: keno
 * Date : 2021/3/16 14:33
 **/
public class PlantListFragment extends Fragment {

    private static final String TAG = "PlantListFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG,"onCreateView");
        FragmentPlantListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_plant_list, container, false);

        PlantListAdapter adapter = new PlantListAdapter(Collections.<Plant>emptyList());
        binding.recyclerview.setAdapter(adapter);

        subScribeUi(binding, adapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"onDestroyView");
    }

    private void subScribeUi(final FragmentPlantListBinding binding, final PlantListAdapter adapter) {
        //初始化ViewModel
        PlantListViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(PlantListViewModel.class);

        viewModel.getPlantsLiveData().observe(getViewLifecycleOwner(), new Observer<List<Plant>>() {
            @Override
            public void onChanged(List<Plant> plants) {
//                Collections.shuffle(plants);        //随机排序
                adapter.setNewDatas(plants);
                binding.setHasPlantings(plants != null && !plants.isEmpty());
            }
        });
    }
}