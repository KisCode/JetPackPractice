package com.kiscode.jetpack.viewmodel.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.kiscode.jetpack.viewmodel.vm.InputViewModel;
import com.kiscode.viewmodel.R;

/****
 * ProjectName: JetPackPractice
 * Package: com.kiscode.jetpack.viewmodel.fragment
 * ClassName: MasterFragment
 * Description:
 * Author:  Administrator
 * CreateDate: 2020/6/24 11:01
 */

public class MasterFragment extends Fragment {
    InputViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(InputViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master, container, false);
        final TextView tvName = view.findViewById(R.id.tv_name);

        tvName.setText(viewModel.getName().getValue());
        viewModel.getName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvName.setText(viewModel.getName().getValue());
            }
        });
        return view;
    }

}
