package kiscode.jectpack.workmanager.work;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class PeriodWorker extends Worker {
    private static final String TAG = "PeriodWorker";

    public PeriodWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e(TAG, "周期性任务开始执行1");
        Log.e(TAG, "周期性任务开始执行2");
        Log.e(TAG, "周期性任务开始执行3");
        return Result.success();
    }
}