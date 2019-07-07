package in.co.integro.recyclertimeview;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.Observable;

public class CustomTime extends BaseObservable {

    private String time;
    private boolean state = false;
    private long timeInMillis;

    public CustomTime(String time, long timeInMillis) {
        this.time = time;
        this.timeInMillis = timeInMillis;
    }

    public String getTime() {
        return time;
    }

    @Bindable
    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
        notifyPropertyChanged(BR.state);
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }
}
