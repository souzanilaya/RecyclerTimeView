package in.co.integro.recyclertimeview;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

public class TimeObserver implements Observer {

    private static final String TAG = "TimeObserver";

    @Override
    public void update(Observable o, Object arg) {
        Log.i(TAG, "update: " + arg.getClass().getSimpleName());
        Log.i(TAG, "update: " +o.toString());
    }
}
