package com.kiscode.jetpack.navigation.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiscode.jetpack.navigation.R;

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vm_detail, container, false);
    }
}
