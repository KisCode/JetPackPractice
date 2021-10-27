package kiscode.jectpack.workmanager.work;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import kiscode.jectpack.workmanager.Constants;


public class SimpleWorker extends Worker {
    private static final String TAG = "SimpleWorker";

    public SimpleWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        for (int i = 0; i < 100; i++) {
            Log.i(TAG, " value = " + i + " in " + Thread.currentThread().getName());
        }
        return Result.success();
    }
}