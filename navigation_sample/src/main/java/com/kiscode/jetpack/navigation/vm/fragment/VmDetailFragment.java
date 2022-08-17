package com.kiscode.jetpack.navigation.vm.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.kiscode.jetpack.navigation.R;
import com.kiscode.jetpack.navigation.databinding.FragmentVmDetailBinding;
import com.kiscode.jetpack.navigation.vm.viewmodel.MyViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class VmDetailFragment extends Fragment {

    public VmDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentVmDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vm_detail, container, false);
        final MyViewModel myViewModel = new ViewModelProvider(getActivity()).get(MyViewModel.class);
        myViewModel.getNumber().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.i("onChanged", "onChanged:" + integer);
            }
        });
        Log.i("homeFragment", "VmDetailFragment:" + myViewModel.hashCode());
        binding.setData(myViewModel);
        binding.setLifecycleOwner(this);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.add(1);
            }
        });

        binding.btnDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.add(-1);
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_vmDetailFragment_to_vmHomeFragment);
            }
        });
        return binding.getRoot();
    }
}
