package co.torres.christhian.testgrability.applist;

import android.content.Context;

import java.util.List;

import co.torres.christhian.testgrability.applist.events.AppListEvent;
import co.torres.christhian.testgrability.applist.ui.AppListView;
import co.torres.christhian.testgrability.lib.EventBus;
import co.torres.christhian.testgrability.lib.GreenRobotEventBus;
import co.torres.christhian.testgrability.models.AppInfo;

/**
 * Created by Ceindetec02 on 09/11/2016.
 */

public class AppListPresenterImpl implements AppListPresenter {

    EventBus eventBus;

    //Instanciamiento de la interface SplashScreenView
    private AppListView appListView;

    //Instanciamiento de la interface SplashScreenInteractor
    private AppListInteractor appListInteractor;

    public AppListPresenterImpl(AppListView appListView) {
        this.appListView = appListView;
        this.appListInteractor = new AppListInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {

        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        appListView = null;
        eventBus.unregister(this);
    }

    @Override
    public void loadData(Context context, int idCategoria) {

        appListInteractor.doLoadData(context, idCategoria);

    }

    @Override
    public void onEventMainThread(AppListEvent appListEvent) {
        switch (appListEvent.getEventType()) {
            case AppListEvent.onLoadSuccess:
                loadSuccess(appListEvent.getAppInfoList());
                break;
            case AppListEvent.onLoadError:
                loadError(appListEvent.getErrorMessage());
                break;

        }

    }


    private void loadSuccess(List<AppInfo> appInfoList) {

        appListView.loadDataSuccess(appInfoList);

    }

    private void loadError(String errorMessage) {

        appListView.loadDataError(errorMessage);

    }

}
