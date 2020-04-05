package com.keno.databinding.sample.bindingadapter;


import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

/****
 * ProjectName: JetPackPractice
 * Package: com.keno.databinding.sample.bindingadapter
 * ClassName: TextViewBindingAdapter
 * Description:
 * Author:  Administrator
 * CreateDate: 2020/3/8 10:20
 */

public class TextViewBindingAdapter {

    @BindingAdapter("textValue")
    public static void logText(TextView view, CharSequence textValue) {
        Log.i("textValue", textValue.toString());
    }

}
