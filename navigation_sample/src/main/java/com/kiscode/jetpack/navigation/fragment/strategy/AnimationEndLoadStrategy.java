package com.kiscode.jetpack.navigation.fragment.strategy;

import android.content.Context;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Description: 动画加载完成后初始化
 * Author: keno
 * Date : 2021/7/14 17:06
 **/
public class AnimationEndLoadStrategy extends AbsInitLoadStrategy {

    private static final String TAG = "DefaultInitLoadStrategy";

    public AnimationEndLoadStrategy(Context context, IInitLoad initLoad) {
        super(context, initLoad);
    }

    @Override
    public Animation load(boolean enter, int nextAnim) {
        if (enter && nextAnim > 0) { //进入动画
            Animation animation = AnimationUtils.loadAnimation(context, nextAnim);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Log.i(TAG, "onAnimationStart");
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Log.i(TAG, "onAnimationEnd");
                    initLoad.init();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    Log.i(TAG, "onAnimationRepeat");
                }
            });
            return animation;
        } else {
            initLoad.init();
            return null;
        }
    }
}