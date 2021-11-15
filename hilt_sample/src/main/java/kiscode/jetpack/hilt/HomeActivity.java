package kiscode.jetpack.hilt;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kiscode.jetpack.hilt.model.Cat;
import kiscode.jetpack.hilt.model.Dog;
import kiscode.jetpack.hilt.model.Engine;
import kiscode.jetpack.hilt.model.User;
import kiscode.jetpack.hilt.module.MakeBlueCat;
import kiscode.jetpack.hilt.module.MakeRedCat;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    @Inject
    User mUser;

    @Inject
    Engine mEngine;

    @Inject
    Dog mDog;

    @Inject
    @MakeRedCat
    Cat mRedCat;

    @Inject
    @MakeBlueCat
    Cat mBlueCat;

    @Inject
    ExecutorService mExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        TextView tvName = findViewById(R.id.tv_name);
        tvName.setText("name is " + mUser.name);

        mEngine.on();
        mEngine.off();

        Log.i(TAG, "dog name is " + mDog.getName());

        Log.i(TAG, "cat name is " + mBlueCat.getName());
        Log.i(TAG, "cat name is " + mRedCat.getName());

    }

    private void initView() {
        Button btnExecute = findViewById(R.id.btn_execute);
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 10000; i++) {
                    mExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, Thread.currentThread().getName());
                        }
                    });
                }
            }
        });
    }
}