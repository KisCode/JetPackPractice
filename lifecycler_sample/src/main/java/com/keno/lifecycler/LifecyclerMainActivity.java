package com.keno.lifecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

/**
 * Description: LifeCycler使用Demo
 * https://developer.android.google.cn/topic/libraries/architecture/lifecycle
 * Author: keno
 * CreateDate: 2020/4/5 13:53
 */

public class LifecyclerMainActivity extends AppCompatActivity {
    private MyLifeListener myLifeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycler_main);

        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                Log.i("lifecycleEvent", "onStateChanged：" + event.name());
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            public void connection() {
                Log.i("lifecycleEvent", "ON_RESUME");
            }

        });

        //
//        initLifeCycle();

//        myLifeListener = new MyLifeListener(this, getLifecycle());

    }

    /***
     * 如果没有LifeCycle，只能通过自定义实现回调监听
     */
    private void initLifeCycle() {
//        myLifeListener = new MyLifeListener() {
//            @Override
//            public void onCreate() {
//                //监听Acitivity的生命周期
//                Log.i("initLifeCycle","onCreate");
//            }
//
//            @Override
//            public void onStart() {
//                Log.i("initLifeCycle","onStart");
//            }
//
//            @Override
//            public void onRetart() {
//
//            }
//
//            @Override
//            public void onResume() {
//
//            }
//
//            @Override
//            public void onPause() {
//
//            }
//
//            @Override
//            public void onStop() {
//                /*
//                无法保证组件会在 Activity 或 Fragment 停止之前启动。
//                在我们需要执行长时间运行的操作（如 onStart() 中的某种配置检查）时尤其如此。
//                这可能会导致出现一种竞争条件，在这种条件下，onStop() 方法会在 onStart() 之前结束，这使得组件留存的时间比所需的时间要长
//*/
//            }
//
//            @Override
//            public void onDestory() {
//
//            }
//        };
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //
    }

    class MyLifeListener implements LifecycleObserver {
        public MyLifeListener(Context context, Lifecycle lifecycle) {

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void connection() {
            Log.i("lifecycleEvent", "ON_RESUME");
        }
    }
}
