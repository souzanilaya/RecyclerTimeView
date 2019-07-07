package in.co.integro.recyclertimeview;

import android.os.Handler;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Observable;

public class UserDataRepository extends Observable {

    private static UserDataRepository INSTANCE = null;
    private String fullName;
    private int age;

    public UserDataRepository() {
        getNewDataFromRemote();
    }

    public static UserDataRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserDataRepository();
        }
        return INSTANCE;
    }

    private void getNewDataFromRemote() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setUserData("Kelvin", 27);
            }
        }, 5000);
    }

    private void setUserData(String name, int age) {
        fullName = name;
        this.age = age;
        setChanged();
        notifyObservers();
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }
}
