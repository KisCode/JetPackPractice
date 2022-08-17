package com.kiscode.jetpack.navigation.common.strategy;

import android.content.Context;
import android.view.animation.Animation;

public abstract class AbsInitLoadStrategy {
    IInitLoad initLoad;
    Context context;

    public AbsInitLoadStrategy(Context context, IInitLoad initLoad) {
        this.context = context;
        this.initLoad = initLoad;
    }

    public abstract Animation load(boolean enter, int nextAnim);
}
