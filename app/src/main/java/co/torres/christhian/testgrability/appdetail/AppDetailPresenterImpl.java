package co.torres.christhian.testgrability.appdetail;

import android.content.Context;

import co.torres.christhian.testgrability.appdetail.events.AppDetailEvent;
import co.torres.christhian.testgrability.appdetail.ui.AppDetailView;
import co.torres.christhian.testgrability.lib.EventBus;
import co.torres.christhian.testgrability.lib.GreenRobotEventBus;
import co.torres.christhian.testgrability.models.AppInfo;


public class AppDetailPresenterImpl implements AppDetailPresenter {


    EventBus eventBus;

    //Instanciamiento de la interface SplashScreenView
    private AppDetailView appDetailView;

    //Instanciamiento de la interface SplashScreenInteractor
    private AppDetailInteractor appDetailInteractor;

    public AppDetailPresenterImpl(AppDetailView appDetailView) {
        this.appDetailView = appDetailView;
        this.appDetailInteractor = new AppDetailInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {

        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        appDetailView = null;
        eventBus.unregister(this);
    }

    @Override
    public void loadData(Context context, int idApp) {
        appDetailInteractor.doLoadData(context, idApp);
    }

    @Override
    public void onEventMainThread(AppDetailEvent appDetailEvent) {
        switch (appDetailEvent.getEventType()) {
            case AppDetailEvent.onLoadSuccess:
                loadSuccess(appDetailEvent.getAppInfo());
                break;
            case AppDetailEvent.onLoadError:
                loadError(appDetailEvent.getErrorMessage());
                break;

        }
    }

    private void loadSuccess(AppInfo appInfo) {

        appDetailView.loadDataSuccess(appInfo);

    }

    private void loadError(String errorMessage) {

        appDetailView.loadDataError(errorMessage);

    }


}
