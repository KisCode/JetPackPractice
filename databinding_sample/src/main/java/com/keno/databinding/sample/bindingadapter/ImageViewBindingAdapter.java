package com.keno.databinding.sample.bindingadapter;


import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
* Description:  DataBinding BindingAdapter
* Author: keno
* CreateDate: 2020/3/8 10:31
*/


public class ImageViewBindingAdapter {
    /*
    BindingAdapter用来设置布局中View的自定义属性，当使用该属性时，可以自定义其行为
    当一个方法加上@BindingAdapter注解后，就定义了一个BindingAdapter，方法的第一个参数是需要绑定到的View，第二个参数是绑定的属性值。
    */
    @BindingAdapter("imgUrl")
    public static void bindImageUrl(ImageView imageView, String imageUrl) {
        RequestOptions options =
                new RequestOptions()
                        .centerCrop()
                        .dontAnimate();
        Glide.with(imageView)
                .load(imageUrl)
                .apply(options)
                .into(imageView);

    }
}
