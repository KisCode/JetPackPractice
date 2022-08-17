package com.kiscode.jetpack.navigation.vm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Description:
 * Author: keno
 * CreateDate: 2020/5/25 22:04
 */

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> number;

    public MyViewModel() {
        number = new MutableLiveData<>();
    }

    public MutableLiveData<Integer> getNumber() {
        return number;
    }

    public void add(int value) {
        if (number.getValue() < 0) {
            number.postValue(0);
        }
        int newValue = number.getValue() + value;
        number.postValue(newValue);
    }
}
