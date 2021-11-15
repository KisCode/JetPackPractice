package kiscode.jetpack.hilt.model;

import android.util.Log;

import javax.inject.Inject;

public class ChinesEngine implements Engine{
    private static final String TAG = "ChinesEngine";

    @Inject
    public ChinesEngine() {
    }

    @Override
    public void on() {
        Log.i(TAG,"chines engine on");

    }

    @Override
    public void off() {
        Log.i(TAG,"chines engine off");
    }
}