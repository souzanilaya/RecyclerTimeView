package in.co.integro.recyclertimeview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateTimeAdapter extends RecyclerView.Adapter<DateTimeAdapter.DateTimeViewHolder> {

    private static final String TAG = "DateTimeAdapter";

    private ArrayList<CustomTime> timeList = new ArrayList<>();

    private Calendar serviceFromTime = Calendar.getInstance();

    private Calendar serviceTillTime = Calendar.getInstance();

    private OnTimeStateChanged onTimeStateChanged;

    private int lastItemSelectedPosition = -1;

    private Context context;

    public void setTimeList(String serviceFromTimeInString, String serviceTillTimeInString) {
        String[] serviceFromTimeArray = serviceFromTimeInString.split(":");
        String[] serviceTillTimeArray = serviceTillTimeInString.split(":");
        if (((serviceFromTimeArray.length > 0) && (serviceTillTimeArray.length > 0))) {

            Calendar clonedCalFrom;
            Calendar clonedCalTill;

            //Setting service Form Time Calender Object
            serviceFromTime.set(Calendar.SECOND, 0);
            serviceFromTime.set(Calendar.MINUTE, Integer.parseInt(serviceFromTimeArray[1]));
            serviceFromTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(serviceFromTimeArray[0]));

            //Setting service Till Time Calender Object
            serviceTillTime.set(Calendar.SECOND, 0);
            serviceTillTime.set(Calendar.MINUTE, Integer.parseInt(serviceTillTimeArray[1]));
            serviceTillTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(serviceTillTimeArray[0]));

            clonedCalFrom = (Calendar) serviceFromTime.clone();
            clonedCalTill = (Calendar) serviceTillTime.clone();

            while (clonedCalFrom.before(serviceTillTime)) {
                clonedCalFrom.add(Calendar.MINUTE, 15);
                if (!clonedCalFrom.after(Calendar.getInstance())) {
                    continue;
                }
                if (timeList == null) {
                    timeList = new ArrayList<>();
                }

                timeList.add(
                        new CustomTime(
                                DateFormat.getTimeInstance(DateFormat.SHORT).format(clonedCalFrom.getTime()),
                                clonedCalFrom.getTimeInMillis()
                        )
                );
            }

        } else {
            this.timeList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public void refreshState(int oldPosition, int newPosition) {
        if (oldPosition!=-1) {
            timeList.get(oldPosition).setState(false);
        }
        timeList.get(newPosition).setState(true);
        notifyItemChanged(oldPosition);
        notifyItemChanged(newPosition);
    }

    @NonNull
    @Override
    public DateTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_time, parent, false);
        return new DateTimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateTimeViewHolder holder, int position) {
        CustomTime customTime = timeList.get(position);
        holder.timeView.setText(timeList.get(position).getTime());
        if (customTime.getState()) {
            holder.timeHolder.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        } else {
            holder.timeHolder.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public void setOnTimeStateChanged(OnTimeStateChanged onTimeStateChanged) {
        this.onTimeStateChanged = onTimeStateChanged;
    }

    public interface OnTimeStateChanged {
        void onTimeSelected(View view, int oldPosition, int newPostion, CustomTime time);
    }

    public class DateTimeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.time)
        TextView timeView;

        @BindView(R.id.timeHolder)
        LinearLayout timeHolder;

        public DateTimeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTimeStateChanged.onTimeSelected(itemView, lastItemSelectedPosition, getAdapterPosition(), timeList.get(getAdapterPosition()));
                    refreshState(lastItemSelectedPosition,getAdapterPosition());
                    lastItemSelectedPosition = getAdapterPosition();
                }
            });
        }
    }
}
