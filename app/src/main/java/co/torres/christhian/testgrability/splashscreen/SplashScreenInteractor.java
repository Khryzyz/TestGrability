package co.torres.christhian.testgrability.splashscreen;

import android.content.Context;

import co.torres.christhian.testgrability.lib.VolleySingleton;

/**
 * Interface del interactuador para el Splash Screen
 */

public interface SplashScreenInteractor {

    void checkData(Context context);

    void doUpdateData(Context context);

}
