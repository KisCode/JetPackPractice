package kiscode.jetpack.hilt.module;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class AppModule {

    //单例线程池
    @Singleton
    @Provides
    public static ExecutorService provideExecutor() {
        return new ThreadPoolExecutor(3, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue(1000));
    }
} 