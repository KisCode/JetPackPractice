package com.kiscode.jetpack.practice.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.kiscode.jetpack.practice.data.pojo.GardenWithPlant;
import com.kiscode.jetpack.practice.databinding.FragmentGardenBinding;
import com.kiscode.jetpack.practice.ui.adapter.GardenAdapter;
import com.kiscode.jetpack.practice.viewmodels.GardenViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/16 14:46
 **/
public class GardenFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentGardenBinding binding = FragmentGardenBinding.inflate(inflater, container, false);

//        View rootView = inflater.inflate(R.layout.fragment_garden, container, false);
        GardenAdapter adapter = new GardenAdapter(Collections.<GardenWithPlant>emptyList());
        binding.recyclerviewGarden.setAdapter(adapter);

        subScribeUi(binding, adapter);

        return binding.getRoot();
    }

    private void subScribeUi(final FragmentGardenBinding binding, final GardenAdapter adapter) {
        //初始化ViewModel
        GardenViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(GardenViewModel.class);

        viewModel.gardenWithPlant.observe(getViewLifecycleOwner(), new Observer<List<GardenWithPlant>>() {
            @Override
            public void onChanged(List<GardenWithPlant> gardenWithPlants) {
                Log.i("GardenWithPlant", "gardenWithPlants size:" + gardenWithPlants.size());
//                adapter.setNewDatas();
                adapter.setNewDatas(gardenWithPlants);
                binding.setHasPlantings(!gardenWithPlants.isEmpty());
            }
        });
    }
}