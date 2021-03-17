package com.kiscode.jetpack.navigation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Description:
 * Author: keno
 * CreateDate: 2020/5/25 22:04
 */

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> number;

    public MutableLiveData<Integer> getNumber() {
        if (number == null) {
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }

    public void add(int value) {
        if (number == null) {
            number = new MutableLiveData<>();
        }

        if (number.getValue() < 0) {
            number.setValue(0);
        }
        number.setValue(number.getValue() + value);
    }
}
