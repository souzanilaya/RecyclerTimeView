
package in.co.integro.recyclertimeview;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Observer {

    private static final String TAG = "MainActivity";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.age)
    TextView age;

    @BindView(R.id.fullName)
    TextView fullName;

    private Observable observable;

    private DateTimeAdapter dateTimeAdapter = new DateTimeAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(dateTimeAdapter);

        dateTimeAdapter.setTimeList("07:15", "24:30");

        dateTimeAdapter.setOnTimeStateChanged(new DateTimeAdapter.OnTimeStateChanged() {
            @Override
            public void onTimeSelected(View view, int oldPosition, int newPosition, CustomTime time) {
                Toast.makeText(MainActivity.this, "Selected Time: " + time.getTime(), Toast.LENGTH_SHORT).show();

            }
        });

        observable = UserDataRepository.getInstance();
        observable.addObserver(this);

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof UserDataRepository) {
            UserDataRepository userDataRepository = (UserDataRepository) o;
            fullName.setText(userDataRepository.getFullName());
            age.setText("" + userDataRepository.getAge());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        observable.deleteObserver(this);
    }
}
