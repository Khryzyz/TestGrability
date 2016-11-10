package co.torres.christhian.testgrability.splashscreen.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Handler;
import android.transition.Explode;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import co.torres.christhian.testgrability.R;
import co.torres.christhian.testgrability.categorialist.ui.CategoriaListActivity_;
import co.torres.christhian.testgrability.splashscreen.SplashScreenPresenter;
import co.torres.christhian.testgrability.splashscreen.SplashScreenPresenterImpl;

@EActivity(R.layout.activity_splash_screen)
public class SplashScreenActivity extends Activity implements SplashScreenView {

    //Instanciamiento del presentador
    private SplashScreenPresenter splashScreenPresenter;

    //Declaracion de la variable window para las animaciones
    Window window;

    @ViewById
    TextView txvSplashScreenInfo;

    //Metodo inicial de la activity
    @AfterViews
    void SplashInit() {

        //Inicializacion del presentador
        splashScreenPresenter = new SplashScreenPresenterImpl(this);
        splashScreenPresenter.onCreate();
        splashScreenPresenter.checkUpdateData(this);


        //Metodo para colocar la orientacion de la app
        setOrientation();

        //Metodo para inicializar las transiciones de la app
        setupWindowAnimations();


    }

    /**
     * Metodo que inicializa las transiciones entre actividades
     */
    private void setupWindowAnimations() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window = getWindow();
            window.setReenterTransition(new Explode());
            window.setExitTransition(new Slide().setDuration(500));
        }

    }

    /**
     * Metodo que determina la orientacion de la app
     */
    private void setOrientation() {
        if (getResources().getBoolean(R.bool.portrait_only))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * Metodo sobrecargado de la vista para la destruccion de la activity
     */
    @Override
    public void onDestroy() {
        splashScreenPresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Metodo sobrecargado de la interface de la view para mostrar la barra de progreso
     */
    @Override
    public void showProgress() {
        txvSplashScreenInfo.setVisibility(View.VISIBLE);
        txvSplashScreenInfo.setText(getString(R.string.splash_message_loading));
    }

    /**
     * Metodo sobrecargado de la interface de la view para ocultar la barra de progreso
     */
    @Override
    public void hideProgress() {
        txvSplashScreenInfo.setVisibility(View.GONE);
    }

    /**
     * Metodo sobrecargado de la interface de la view para el manejo de la actualizacion de datos correcta
     */
    @Override
    public void handleUpdateDataSuccess() {
        txvSplashScreenInfo.setVisibility(View.VISIBLE);
        txvSplashScreenInfo.setText(getString(R.string.splash_message_endload));
        navigateToMainScreen();
    }

    /**
     * Metodo sobrecargado de la interface de la view para el error de la actualizacion de datos
     */
    @Override
    public void handleUpdateDataError(String errorMessage) {
        txvSplashScreenInfo.setVisibility(View.VISIBLE);
        txvSplashScreenInfo.setText(String.format(getString(R.string.splash_message_error), errorMessage));
    }

    /**
     * Metodo sobrecargado de la interface de la view para manejar los datos que ya estan depreciados
     */
    @Override
    public void handleDataDeprecated() {
        Toast.makeText(this, getString(R.string.splash_message_data_deprectaed), Toast.LENGTH_SHORT).show();
        splashScreenPresenter.doUpdateData(this);

    }

    /**
     * Metodo sobrecargado de la interface de la view para manejar los datos que ya estan actualizados
     */
    @Override
    public void handleDataUpdated() {
        navigateToMainScreen();
    }

    /**
     * Metodo sobrecargado de la interface de la view para manejar la falta de conexion a internet
     */
    @Override
    public void handleNoInternetConnection() {
        Toast.makeText(this, getString(R.string.splash_message_no_internet), Toast.LENGTH_SHORT).show();
        navigateToMainScreen();
    }

    /**
     * Metodo para navegar a la activity principal
     */
    public void navigateToMainScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Inicializacion del intent para ir a la activity CategoriaListActivity_
                Intent intent = new Intent(SplashScreenActivity.this, CategoriaListActivity_.class);
                //Agregadas  ventanas para no retorno
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                ActivityOptions options0 = null;
                //Si la version soporta transicioes lanza la actividad con transicion
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    options0 = ActivityOptions.makeSceneTransitionAnimation(SplashScreenActivity.this);
                    startActivity(intent, options0.toBundle());
                } else {
                    startActivity(intent);
                }


            }
        }, 3000);


    }
}
