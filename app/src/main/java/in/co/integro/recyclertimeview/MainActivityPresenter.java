package in.co.integro.recyclertimeview;

import android.content.Context;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View view;

    private Context context;

    public MainActivityPresenter(MainActivityContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void onShowData() {
        view.showData();
    }

}
