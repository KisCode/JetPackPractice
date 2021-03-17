package com.keno.databinding.sample.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

/**
 * Description:
 * Author: keno
 * CreateDate: 2020/6/22 20:58
 */
//public class SavedStateViewModel extends ViewModel {
public class SavedStateViewModel extends ViewModel {
    /*private MutableLiveData<Integer> number;

    public MutableLiveData<Integer> getNumber() {
        if (number == null) {
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }

    public void add() {
        getNumber().setValue(getNumber().getValue() + 1);
    }*/

    private static final String KEY_NUMBER = "NUMBER";
    private SavedStateHandle handle;

    public SavedStateViewModel(SavedStateHandle handle) {
        if (!handle.contains(KEY_NUMBER)) {
            handle.set(KEY_NUMBER, 0);
        }
        this.handle = handle;
    }

    public MutableLiveData<Integer> getNumber() {
        if (!handle.contains(KEY_NUMBER)) {
            handle.set(KEY_NUMBER, 0);
        }
        return handle.getLiveData(KEY_NUMBER);
    }

    public void add() {
        getNumber().setValue(getNumber().getValue() + 1);
    }

}
