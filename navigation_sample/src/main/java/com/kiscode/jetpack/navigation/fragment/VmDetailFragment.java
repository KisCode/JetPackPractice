package com.kiscode.jetpack.navigation.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiscode.jetpack.navigation.R;
import com.kiscode.jetpack.navigation.databinding.FragmentVmDetailBinding;
import com.kiscode.jetpack.navigation.viewmodel.MyViewModel;

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
