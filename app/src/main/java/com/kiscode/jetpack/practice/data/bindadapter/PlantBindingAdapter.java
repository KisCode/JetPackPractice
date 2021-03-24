package com.kiscode.jetpack.practice.data.bindadapter;


import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;
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

    @BindingAdapter("renderHtml")
    public static void bindReaderHtml(TextView view, String description) {
        if (description == null) {
            view.setText("");
        } else {
            view.setText(HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT));
            view.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
