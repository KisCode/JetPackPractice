package com.kiscode.jetpack.navigation.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final MyViewModel viewModel = new ViewModelProvider(getActivity()).get(MyViewModel.class);
        Log.i("homeFragment", "homeFragment:" + viewModel.hashCode());
        FragmentVmHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vm_home, container, false);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(getActivity());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                Bundle bundle = new Bundle();
                controller.navigate(R.id.action_vmHomeFragment_to_vmDetailFragment);

            }
        });

        binding.seekBar.setProgress(0);
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewModel.getNumber().setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return binding.getRoot();
    }
}
