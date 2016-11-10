package co.torres.christhian.testgrability.splashscreen.ui;

/**
 * Interface de la vista para el Splash Screen
 */
public interface SplashScreenView {

    //Metodo para mostrar la barra de progreso
    void showProgress();

    //Metodo para ocutar la barra de progreso
    void hideProgress();

    //Manejador de la actualizacion de datos completa
    void handleUpdateDataSuccess();

    //Manejador de error en la actualizacion de datos
    void handleUpdateDataError(String errorMessage);

    //Manejador de la actualizacion de datos completa
    void handleDataDeprecated();


    //Manejador de la actualizacion de datos completa
    void handleDataUpdated();


    //Manejador de la actualizacion de datos completa
    void handleNoInternetConnection();


}
