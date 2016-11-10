package co.torres.christhian.testgrability.categorialist;

import android.content.Context;

import co.torres.christhian.testgrability.categorialist.events.CategoriaListEvent;

/**
 * Created by Ceindetec02 on 07/11/2016.
 */

public interface CategoriaListPresenter {
    //Metodo para la creacion del presentador
    void onCreate();

    //Metodo para la destruccion del presentador
    void onDestroy();

    //Metodo que efectua la carga de datos del sistema
    void loadData(Context context);

    //Metodo para recibir los eventos generados
    void onEventMainThread(CategoriaListEvent categoriaListEvent);

}
