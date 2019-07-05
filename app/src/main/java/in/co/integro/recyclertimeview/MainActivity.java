package in.co.integro.recyclertimeview;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
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
                time.setState(true);
                Toast.makeText(MainActivity.this, "Selected Time: " + time.getTime(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
