package com.keno.livedata.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Description:
 * Author: keno
 * CreateDate: 2020/4/5 16:31
 */
public class NameViewModel extends ViewModel {
    MutableLiveData<String> currentName;

    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<>();
        }
        return currentName;
    }
}
