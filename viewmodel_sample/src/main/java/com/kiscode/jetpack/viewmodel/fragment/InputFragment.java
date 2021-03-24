package com.kiscode.jetpack.viewmodel.fragment;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import com.kiscode.jetpack.viewmodel.vm.InputViewModel;
import com.kiscode.viewmodel.R;

/****
 * ProjectName: JetPackPractice
 * Package: com.kiscode.jetpack.viewmodel.fragment
 * ClassName: InputFragment
 * Description:
 * Author:  Administrator
 * CreateDate: 2020/6/24 11:13
 */

public class InputFragment extends Fragment {
    InputViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        new ViewModelProvider((ViewModelStore) getViewLifecycleOwner(),new ViewModelProvider.NewInstanceFactory());
        viewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(InputViewModel.class);

        EditText editText = view.findViewById(R.id.et_input);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.getName().postValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }
}
