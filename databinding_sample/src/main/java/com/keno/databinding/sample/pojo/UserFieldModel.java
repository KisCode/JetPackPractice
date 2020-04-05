package com.keno.databinding.sample.pojo;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

/**
 * Description: ObservableField 的使用
 * Author: KrisKeno
 * Date : 2020/3/7 8:21 AM
 **/
public class UserFieldModel {
    /*
    ObservableField<T>中传入的泛型可以是Java中的基本类型，当然还可以使用
     ObservableBoolean, ObservableByte, ObservableChar, ObservableShort, ObservableInt,
     ObservableLong, ObservableFloat, ObservableDouble, ObservableParcelable等具体的类型，效果也和ObservableField<T>一样的
 */
    public final ObservableField<String> name = new ObservableField<>();

//    public final ObservableField<Integer> age = new ObservableField<>();

    public final ObservableInt age = new ObservableInt();


    public final ObservableField<String> avator = new ObservableField<>();
}
