package com.keno.databinding.sample;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;

import com.keno.databinding.sample.databinding.ActivityViewmodelSavedStateBinding;
import com.keno.databinding.sample.viewmodel.SavedStateViewModel;

public class ViewModelSavedStateActivity extends AppCompatActivity {
    ActivityViewmodelSavedStateBinding binding;
    SavedStateViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_viewmodel_saved_state);

        viewModel = new ViewModelProvider(this, new SavedStateViewModelFactory(getApplication(), this))
                .get(SavedStateViewModel.class);
        binding.setData(viewModel);
        binding.setLifecycleOwner(this);
    }

}