package com.keno.livedata;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.keno.livedata.viewmodel.NameViewModel;

import java.util.Random;

public class MainLiveDataActivity extends AppCompatActivity implements View.OnClickListener {
    private NameViewModel model;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_live_data);
        initView();

        // Get the ViewModel.
        //废弃的初始化方法
//        model = ViewModelProviders.of(this).get(NameViewModel.class);

        //初始化ViewModel
        model = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new NameViewModel();
            }
        }).get(NameViewModel.class);

        //初始化方法3
//        model =new NameViewModel();


        // Create the observer which updates the UI.
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                // Update the UI, in this case, a TextView.
                Log.i("onChanged", newName);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.getCurrentName().observe(this, nameObserver);

    }

    private void initView() {
        btnUpdate = (Button) findViewById(R.id.btn_update);

        btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
//调用 setValue(T) 方法以从主线程更新 LiveData 对象。如果在 worker 线程中执行代码，则您可以改用 postValue(T) 方法来更新 LiveData 对象
                model.getCurrentName().postValue("new Data:" + new Random().nextInt());
                break;
        }
    }
}
