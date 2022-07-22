package com.keno.lifecycler;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Description: LifeCycle使用Demo
 * https://developer.android.google.cn/topic/libraries/architecture/lifecycle
 * Author: keno
 * CreateDate: 2020/4/5 13:53
 */

public class LifecycleMainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private LifecycleHandler handler;
    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            Log.i(TAG, "timerRunnable running...");
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycler_main);

        testLifecycle();

        handler = new LifecycleHandler(this);
        handler.postDelayed(timerRunnable, 1000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onDestroy() {
        super.onStart();
        Log.i(TAG, "onDestroy");
    }

    private void testLifecycle() {
        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                Log.i("lifecycleEvent", "onStateChanged：" + event.name());
            }

        });

        getLifecycle().addObserver(new LifecycleObserver() {
            @NonNull
            @Override
            public String toString() {
                return super.toString();
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            public void onResume() {
                Log.i(TAG, "onResume by Lifecycle.Event.ON_RESUME");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onStart();
        Log.i(TAG, "onResume");
    }

    /**
     * 通过持有页面LifecycleOwner 实现onDestroy移除定时器
     */
    class LifecycleHandler extends Handler implements LifecycleObserver {
        private LifecycleOwner lifecycleOwner;

        public LifecycleHandler(LifecycleOwner lifecycleOwner) {
            this.lifecycleOwner = lifecycleOwner;
            lifecycleOwner.getLifecycle().addObserver(this);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        private void onDestroy() {
            removeCallbacks(timerRunnable);
            lifecycleOwner.getLifecycle().removeObserver(this);
        }
    }
}
