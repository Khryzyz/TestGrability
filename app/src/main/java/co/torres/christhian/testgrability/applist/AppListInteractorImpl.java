package co.torres.christhian.testgrability.applist;

import android.content.Context;

public class AppListInteractorImpl implements AppListInteractor {

    private AppListRepository appListRepository;

    public AppListInteractorImpl() {

        this.appListRepository = new AppListRepositoryImpl();

    }

    @Override
    public void doLoadData(Context context, int idCategoria) {

        appListRepository.loadData(context, idCategoria);

    }
}
