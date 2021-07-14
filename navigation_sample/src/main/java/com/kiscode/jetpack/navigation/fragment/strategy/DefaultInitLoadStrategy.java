package com.kiscode.jetpack.navigation.fragment.strategy;

import android.content.Context;
import android.view.animation.Animation;

/**
 * Description: 默认页面加载策略
 * Author: keno
 * Date : 2021/7/14 17:05
 **/
public class DefaultInitLoadStrategy extends AbsInitLoadStrategy {

    private static final String TAG = "DefaultInitLoadStrategy";

    public DefaultInitLoadStrategy(Context context, IInitLoad initLoad) {
        super(context, initLoad);
    }

    @Override
    public Animation load(boolean enter, int nextAnim) {
        initLoad.init();
        return null;
    }
}