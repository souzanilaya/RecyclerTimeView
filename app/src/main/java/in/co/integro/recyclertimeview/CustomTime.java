package in.co.integro.recyclertimeview;

import java.util.Observable;

public class CustomTime extends Observable {

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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
        notifyObservers();
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }
}
