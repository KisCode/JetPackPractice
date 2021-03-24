package com.kiscode.jetpack.viewmodel.vm;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/****
 * ProjectName: JetPackPractice
 * Package: com.kiscode.jetpack.viewmodel.vm
 * ClassName: InputViewModel
 * Description: 演示ViewModel管理输入对象
 * Author:  Administrator
 * CreateDate: 2020/6/24 11:07
 */

public class InputViewModel extends ViewModel {
    private MutableLiveData<String> name;

    public MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<>();
        }
        return name;
    }
}
