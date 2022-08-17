package com.kiscode.jetpack.navigation.vm.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.kiscode.jetpack.navigation.vm.viewmodel.MyViewModel;
import com.kiscode.jetpack.navigation.R;
import com.kiscode.jetpack.navigation.databinding.FragmentVmHomeBinding;

public class VmHomeFragment extends Fragment implements ViewModelStoreOwner {
    private static final String TAG = "VmHomeFragment";
    private static final String TAG_LIFE = "tag_life";

    public VmHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attrs, @Nullable Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        Log.i(TAG_LIFE, "onInflate");
    }

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Log.i(TAG_LIFE, "onAttachFragment");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG_LIFE, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG_LIFE, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG_LIFE, "onCreateView");
        final MyViewModel viewModel = new ViewModelProvider(getActivity()).get(MyViewModel.class);
        Log.i(TAG, "onCreateView homeFragment:" + viewModel.hashCode());
        FragmentVmHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vm_home, container, false);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(getActivity());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");
        Log.i(TAG_LIFE, "onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG_LIFE, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG_LIFE, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG_LIFE, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG_LIFE, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG_LIFE, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG_LIFE, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        Log.i(TAG_LIFE, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG_LIFE, "onDetach");
    }
}
