package demo.kiscode.architecture;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App extends Application {
    private static App instance;
    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public Executor getExecutor() {
        return executorService;
    }
}