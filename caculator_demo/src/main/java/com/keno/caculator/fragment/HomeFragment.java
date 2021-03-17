package com.keno.caculator.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keno.caculator.R;
import com.keno.caculator.databinding.FragmentHomeBinding;
import com.keno.caculator.viewmodel.MyViewModel;

/**
 *
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyViewModel viewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setData(viewModel);
        binding.setLifecycleOwner(this);

        binding.btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_homeFragment_to_questionFragment);
            }
        });
        return binding.getRoot();
    }
}
