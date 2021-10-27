package kiscode.jectpack.workmanager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import kiscode.jectpack.workmanager.work.PeriodWorker;
import kiscode.jectpack.workmanager.work.SimpleWorker;
import kiscode.jectpack.workmanager.work.WithDataWorker;

/**
 * Description: WorkManager用法示例
 * Author: keno
 * Date : 2021/10/27 18:31
 **/
public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void doSimpleWork(View view) {
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(SimpleWorker.class).build();
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);
    }

    /**
     * 延迟执行任务
     *
     * @param view
     */
    public void doDelayWork(View view) {
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(SimpleWorker.class)
                .setInitialDelay(1, TimeUnit.MINUTES)
                .build();
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);
    }

    /**
     * 调用方向WorkManager传递数据
     *
     * @param view 当前view
     */
    public void doWorkWithData(View view) {
        //
        Data data = new Data.Builder()
                .putString(Constants.KEY_TITLE, "doWorkWithData")
                .build();

        //构建任务请求
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WithDataWorker.class)
                .setInputData(data)
                .build();

        //执行任务
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);

        //监听Worker回传的数据
        WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(oneTimeWorkRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo.getState().isFinished()) {
                            Data outputData = workInfo.getOutputData();
                            String resultMsg = outputData.getString(Constants.KEY_MESSAGE);
                            Log.i(TAG, "result is:\t" + resultMsg);
                        }
                    }
                });

    }

    /**
     * 链式调用
     *
     * @param view 当前页面
     */
    public void doChainWork(View view) {
        String requestTag = "chainWork";
        //任务1
        OneTimeWorkRequest oneTimeWorkRequest1 = new OneTimeWorkRequest.Builder(WithDataWorker.class)
                .setInputData(new Data.Builder().putString(Constants.KEY_TITLE, "Task 1").build())
                .addTag(requestTag)
                .build();
        //任务2
        OneTimeWorkRequest oneTimeWorkRequest2 = new OneTimeWorkRequest.Builder(WithDataWorker.class)
                .setInputData(new Data.Builder().putString(Constants.KEY_TITLE, "Task 2").build())
                .addTag(requestTag)
                .build();

        //任务3
        OneTimeWorkRequest oneTimeWorkRequest3 = new OneTimeWorkRequest.Builder(WithDataWorker.class)
                .setInputData(new Data.Builder().putString(Constants.KEY_TITLE, "Task 3").build())
                .build();

        //链式执行任务
        WorkManager.getInstance(this)
                .beginWith(oneTimeWorkRequest1)
                .then(oneTimeWorkRequest2)
                .then(oneTimeWorkRequest3)
                .enqueue();

        WorkManager.getInstance(this)
                .getWorkInfosByTagLiveData(requestTag)
                .observe(this, new Observer<List<WorkInfo>>() {
                    @Override
                    public void onChanged(List<WorkInfo> workInfoList) {
                        for (WorkInfo workInfo : workInfoList) {
                            Log.i(TAG, "result " + workInfo.getState());
                        }
                    }
                });

        //2秒后 取消任务
        new Handler().postDelayed(() -> WorkManager.getInstance(HomeActivity.this).cancelAllWorkByTag(requestTag), 2100);
    }

    /**
     * 周期行任务， 间隔周期必须大于等于15分钟
     *
     * @param view
     */
    public void doPeriodWork(View view) {
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(PeriodWorker.class, 15, TimeUnit.MINUTES)
                .build();
        WorkManager.getInstance(this).enqueue(periodicWorkRequest);
    }
}