package co.torres.christhian.testgrability.applist;

import android.content.Context;

import co.torres.christhian.testgrability.applist.events.AppListEvent;

/**
 * Created by Ceindetec02 on 09/11/2016.
 */

public interface AppListPresenter {

    //Metodo para la creacion del presentador
    void onCreate();

    //Metodo para la destruccion del presentador
    void onDestroy();

    //Metodo que efectua la carga de datos del sistema
    void loadData(Context context, int idCategoria);

    //Metodo para recibir los eventos generados
    void onEventMainThread(AppListEvent appListEvent);

}
