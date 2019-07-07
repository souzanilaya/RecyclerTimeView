package in.co.integro.recyclertimeview;

public interface MainActivityContract {
    public interface Presenter {
        void onShowData();
    }

    public interface View {
        void showData();
    }
}
