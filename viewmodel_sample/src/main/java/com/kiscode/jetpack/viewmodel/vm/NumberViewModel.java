package com.kiscode.jetpack.viewmodel.vm;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/****
 * ProjectName: JetPackPractice
 * Package: com.kiscode.jetpack.viewmodel.vm
 * ClassName: MyViewModel
 * Description:
 * Author:  keno
 * CreateDate: 2020/6/24 10:28
 */

public class NumberViewModel extends ViewModel {
    private MutableLiveData<Integer> number;

    public MutableLiveData<Integer> getNumber() {
        if (number == null) {
            number = new MutableLiveData<>(0);
        }
        return number;
    }

    public void add() {
        number.setValue(number.getValue() + 1);
    }
}
