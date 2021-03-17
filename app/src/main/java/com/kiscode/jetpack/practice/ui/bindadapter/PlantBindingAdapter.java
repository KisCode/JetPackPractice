package com.kiscode.jetpack.practice.ui.bindadapter;


import android.media.Image;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/3/16 19:52
 */

public class PlantBindingAdapter {
    private static final String TAG = "PlantBindingAdapter";

    @BindingAdapter("imgUrl")
    public static void bindImageFromUrl(ImageView imageView, String imgUrl) {
        if (!TextUtils.isEmpty(imgUrl)) {
            Log.i(TAG, imgUrl);
            Glide.with(imageView.getContext())
                    .load(imgUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }
    }


    @BindingAdapter("isGone")
    public static void bindIsGone(View view, boolean isGone) {
        view.setVisibility(isGone ? View.GONE : View.VISIBLE);
    }
}
