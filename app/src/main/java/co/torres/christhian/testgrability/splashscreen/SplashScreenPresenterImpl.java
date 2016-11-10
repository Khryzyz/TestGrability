package co.torres.christhian.testgrability.splashscreen;

import android.content.Context;

import co.torres.christhian.testgrability.lib.EventBus;
import co.torres.christhian.testgrability.lib.GreenRobotEventBus;
import co.torres.christhian.testgrability.lib.VolleySingleton;
import co.torres.christhian.testgrability.splashscreen.events.SplashScreenEvent;
import co.torres.christhian.testgrability.splashscreen.ui.SplashScreenView;


public class SplashScreenPresenterImpl implements SplashScreenPresenter {

    EventBus eventBus;

    //Instanciamiento de la interface SplashScreenView
    private SplashScreenView splashScreenView;

    //Instanciamiento de la interface SplashScreenInteractor
    private SplashScreenInteractor splashScreenInteractor;


    public SplashScreenPresenterImpl(SplashScreenView splashScreenView) {
        this.splashScreenView = splashScreenView;
        this.splashScreenInteractor = new SplashScreenInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    //Sobrecarga del metodo onCreate de la interface SplashScreenPresenter "crear" el registro al bus de eventos
    @Override
    public void onCreate() {

        eventBus.register(this);

    }

    //Sobrecarga del metodo onDestroy de la interface SplashScreenPresenter para "eliminar"  el registro al bus de eventos
    @Override
    public void onDestroy() {
        splashScreenView = null;
        eventBus.unregister(this);
    }

    //Sobrecarga del metodo onDestroy de la interface SplashScreenPresenter para intentar actualizar los datos
    @Override
    public void checkUpdateData(Context context) {
        if (splashScreenView != null) {
            splashScreenView.showProgress();
        }
        splashScreenInteractor.checkData(context);
    }

    @Override
    public void doUpdateData(Context context) {
        if (splashScreenView != null) {
            splashScreenView.showProgress();
        }
        splashScreenInteractor.doUpdateData(context);
    }

    //Sobrecarga del metodo onEventMainThread de la interface SplashScreenPresenter para el manejo de eventos
    @Override
    public void onEventMainThread(SplashScreenEvent splashScreenEvent) {
        switch (splashScreenEvent.getEventType()) {
            case SplashScreenEvent.onUpdateDataSuccess:
                updateDataSuccess();
                break;
            case SplashScreenEvent.onUpdateDataError:
                updateDataError(splashScreenEvent.getErrorMessage());
                break;
            case SplashScreenEvent.onDataDeprecated:
                onDataDeprecated();
                break;
            case SplashScreenEvent.onDataUpdated:
                onDataUpdated();
                break;
            case SplashScreenEvent.onInternetNoConnected:
                onInternetNoConnected();
                break;
            case SplashScreenEvent.onDataError:
                onDataError(splashScreenEvent.getErrorMessage());
                break;
        }
    }

    //Metodo llamado en el evento de la actualizacion de datos correcta
    private void updateDataSuccess() {
        if (splashScreenView != null) {
            splashScreenView.hideProgress();
            splashScreenView.handleUpdateDataSuccess();
        }
    }

    //Metodo llamado en el evento de la actualizacion de datos fallida
    private void updateDataError(String errorMessage) {
        if (splashScreenView != null) {
            splashScreenView.hideProgress();
            splashScreenView.handleUpdateDataError(errorMessage);
        }
    }

    //Metodo llamado en el evento de la actualizacion de datos fallida
    private void onDataDeprecated() {
        if (splashScreenView != null) {
            splashScreenView.handleDataDeprecated();
        }
    }

    //Metodo llamado en el evento de la actualizacion de datos fallida
    private void onDataUpdated() {
        if (splashScreenView != null) {
            splashScreenView.hideProgress();
            splashScreenView.handleDataUpdated();
        }
    }

    //Metodo llamado en el evento de la actualizacion de datos fallida
    private void onInternetNoConnected() {
        if (splashScreenView != null) {
            splashScreenView.hideProgress();
            splashScreenView.handleNoInternetConnection();
        }
    }

    //Metodo llamado en el evento de la falla de datos
    private void onDataError(String errorMessage) {
        if (splashScreenView != null) {
            splashScreenView.hideProgress();
            splashScreenView.handleNoInternetConnection();
        }
    }

}
