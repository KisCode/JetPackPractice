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

import com.kiscode.jetpack.practice.data.pojo.Plant;
import com.kiscode.jetpack.practice.databinding.FragmentPlantDetailBinding;
import com.kiscode.jetpack.practice.viewmodels.PlantDetailViewModel;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/16 14:33
 **/
public class PlantDetailFragment extends Fragment {
    public static final String KEY_PLANT_ID = "PLANT_ID";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentPlantDetailBinding binding = FragmentPlantDetailBinding.inflate(inflater, container, false);
        initViewModel(binding);
        return binding.getRoot();
    }

    private void initViewModel(FragmentPlantDetailBinding binding) {
        String plantId = getArguments().getString(KEY_PLANT_ID);
        PlantDetailViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(PlantDetailViewModel.class);
        binding.setPlantViewmodel(viewModel);
//        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);        //解決ViewModel刷新問題
        viewModel.loadById(plantId);
        setHasOptionsMenu(true);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addToGarden(plantId);
            }
        });

        viewModel.isPlanted.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.i("isPlanted","onChange "+aBoolean);
            }
        });
    }
}