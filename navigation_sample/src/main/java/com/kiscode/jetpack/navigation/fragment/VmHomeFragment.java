package com.kiscode.jetpack.navigation.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiscode.jetpack.navigation.R;
import com.kiscode.jetpack.navigation.databinding.FragmentVmHomeBinding;
import com.kiscode.jetpack.navigation.viewmodel.MyViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class VmHomeFragment extends Fragment implements ViewModelStoreOwner {

    public VmHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
//        ViewModelProvider.NewInstanceFactory
//        new MyViewModel();
        new ViewModelProvider(this).get(MyViewModel.class);
        return inflater.inflate(R.layout.fragment_vm_home, container, false);
    }
}
