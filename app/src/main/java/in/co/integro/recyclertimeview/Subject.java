package in.co.integro.recyclertimeview;

public interface Subject {

    void registerObserver(RepositoryObserver repositoryObserver);

    void removeObserver(RepositoryObserver repositoryObserver);

    void notifyObserver();


}
