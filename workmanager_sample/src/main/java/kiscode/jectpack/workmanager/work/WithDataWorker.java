package kiscode.jectpack.workmanager.work;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import kiscode.jectpack.workmanager.Constants;


public class WithDataWorker extends Worker {
    private static final String TAG = "WithDataWorker";

    public WithDataWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String title = getInputData().getString(Constants.KEY_TITLE);
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i(TAG, title + " value = " + i + " in " + Thread.currentThread().getName());
        }

        //任务执行完毕，将data回传至调用方
        Data data = new Data.Builder()
                .putString(Constants.KEY_MESSAGE, "I'm WithDataWorker,work finish!!!")
                .build();
        return Result.success(data);
    }
}