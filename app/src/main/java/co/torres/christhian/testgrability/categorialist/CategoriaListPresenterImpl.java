package co.torres.christhian.testgrability.categorialist;

import android.content.Context;

import java.util.List;

import co.torres.christhian.testgrability.categorialist.events.CategoriaListEvent;
import co.torres.christhian.testgrability.categorialist.ui.CategoriaListView;
import co.torres.christhian.testgrability.lib.EventBus;
import co.torres.christhian.testgrability.lib.GreenRobotEventBus;
import co.torres.christhian.testgrability.models.CategoryApp;

public class CategoriaListPresenterImpl implements CategoriaListPresenter {


    EventBus eventBus;

    //Instanciamiento de la interface SplashScreenView
    private CategoriaListView categoriaListView;

    //Instanciamiento de la interface SplashScreenInteractor
    private CategoriaListInteractor categoriaListInteractor;


    public CategoriaListPresenterImpl(CategoriaListView categoriaListView) {
        this.categoriaListView = categoriaListView;
        this.categoriaListInteractor = new CategoriaListInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    //Sobrecarga del metodo onCreate de la interface CategoriaListPresenter "crear" el registro al bus de eventos
    @Override
    public void onCreate() {

        eventBus.register(this);

    }

    //Sobrecarga del metodo onDestroy de la interface CategoriaListPresenter "destruir" el registro al bus de eventos
    @Override
    public void onDestroy() {
        categoriaListView = null;
        eventBus.unregister(this);
    }

    @Override
    public void loadData(Context context) {
        categoriaListInteractor.doLoadData(context);

    }

    @Override
    public void onEventMainThread(CategoriaListEvent categoriaListEvent) {
        switch (categoriaListEvent.getEventType()) {
            case CategoriaListEvent.onLoadSuccess:
                loadSuccess(categoriaListEvent.getCategoryAppList());
                break;
            case CategoriaListEvent.onLoadError:
                loadError(categoriaListEvent.getErrorMessage());
                break;
        }
    }

    private void loadSuccess(List<CategoryApp> categoryAppList) {

        categoriaListView.loadDataSuccess(categoryAppList);

    }


    private void loadError(String errorMessage) {

        categoriaListView.loadDataError(errorMessage);

    }


}
