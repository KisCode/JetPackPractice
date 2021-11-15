package kiscode.jetpack.hilt;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}