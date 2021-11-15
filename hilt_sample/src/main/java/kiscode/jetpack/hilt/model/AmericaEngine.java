package kiscode.jetpack.hilt.model;

import android.util.Log;

import javax.inject.Inject;

public class AmericaEngine implements Engine{
    private static final String TAG = "AmericaEngine";

    @Inject
    public AmericaEngine() {
    }

    @Override
    public void on() {
        Log.i(TAG,"usa engine on");

    }

    @Override
    public void off() {
        Log.i(TAG,"usa engine off");
    }
}