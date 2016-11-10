package co.torres.christhian.testgrability.splashscreen;

import android.content.Context;

import co.torres.christhian.testgrability.lib.VolleySingleton;
import co.torres.christhian.testgrability.splashscreen.events.SplashScreenEvent;

/**
 * Interface del presentador para el Splash Screen
 */

public interface SplashScreenPresenter {

    //Metodo para la creacion del presentador
    void onCreate();

    //Metodo para la destruccion del presentador
    void onDestroy();

    //Manejador validar la actualizacion de los datos
    void checkUpdateData(Context context);

    //Manejador validar la actualizacion de los datos
    void doUpdateData(Context context);

    //Metodo para recibir los eventos generados
    void onEventMainThread(SplashScreenEvent splashScreenEvent);
}
