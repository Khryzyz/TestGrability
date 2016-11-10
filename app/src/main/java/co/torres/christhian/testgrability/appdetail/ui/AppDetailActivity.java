package co.torres.christhian.testgrability.appdetail.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import co.torres.christhian.testgrability.R;
import co.torres.christhian.testgrability.appdetail.AppDetailPresenter;
import co.torres.christhian.testgrability.appdetail.AppDetailPresenterImpl;
import co.torres.christhian.testgrability.models.AppInfo;

@EActivity(R.layout.app_detail)
public class AppDetailActivity extends AppCompatActivity implements AppDetailView {


    //Instanciamiento del presentador
    private AppDetailPresenter appDetailPresenter;

    int idApp;

    int idCategoria;

    @ViewById
    TextView txvAppNombreDetail;
    @ViewById
    ImageView imgAppDetail;
    @ViewById
    TextView txvAutorDetail;
    @ViewById
    TextView txvLanzamientoDetail;
    @ViewById
    TextView txvValorDetail;
    @ViewById
    TextView txvSummaryTitle;
    @ViewById
    TextView txvSummaryContent;
    @ViewById
    Toolbar tlbDetailApp;

    @AfterViews
    void AppDetailInit() {

        //Obtencion del dato de la app
        Bundle bundle = getIntent().getExtras();
        idApp = bundle.getInt("idApp");

        appDetailPresenter = new AppDetailPresenterImpl(this);
        appDetailPresenter.onCreate();
        appDetailPresenter.loadData(this, idApp);

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
            getWindow().setReenterTransition(new Explode());
            getWindow().setExitTransition(new Explode().setDuration(500));
        }

    }

    /**
     * Metodo que inicializa las transiciones entre actividades
     */
    private void setOrientation() {
        if (getResources().getBoolean(R.bool.portrait_only))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * Metodo para setear la ToolBarr recibe el nombre de la app para mostrarla en el titulo
     *
     * @param nombreAppDetail
     */
    private void setToolbar(String nombreAppDetail) {
        tlbDetailApp.setTitle(nombreAppDetail);
        setSupportActionBar(tlbDetailApp);
    }

    /**
     * Metodo sobrecargado de la vista para la destruccion de la activity
     */
    @Override
    public void onDestroy() {
        appDetailPresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Metodo sobrecargado de la view para manejar la obtencion correcta de los datos
     *
     * @param appInfo
     */
    @Override
    public void loadDataSuccess(AppInfo appInfo) {

        //Pasando informacion a la vista
        txvAppNombreDetail.setText(appInfo.getName());
        Glide.with(this)
                .load(appInfo.getUrlImageDos())
                .into(imgAppDetail);
        txvAutorDetail.setText(String.format(getString(R.string.detail_app_autor), appInfo.getRights()));
        txvLanzamientoDetail.setText(String.format(getString(R.string.detail_app_lanzamiento), appInfo.getRelease()));
        txvValorDetail.setText(String.format(getString(R.string.detail_app_valor), appInfo.getPrice(), appInfo.getCurrency()));
        txvSummaryTitle.setText(appInfo.getTitle());
        txvSummaryContent.setText(appInfo.getSummary());

        setToolbar(appInfo.getName());

        idCategoria = appInfo.getCategoryId();

    }

    /**
     * Metodo sobrecargado de la view para manejar el error en los datos
     *
     * @param errorMessage
     */
    @Override
    public void loadDataError(String errorMessage) {
        Snackbar.make(findViewById(android.R.id.content), "Lo sentimos pero no podemos procesar sus datos.",
                Snackbar.LENGTH_LONG).show();
    }

    /**
     * Metodo para devolver el idCategoria a la vista anterior
     */
    @Override
    public void onBackPressed() {

        Bundle args = new Bundle();
        args.putInt("idCategoria", idCategoria);

        Intent intent = new Intent();
        intent.putExtras(args);
        setResult(1, intent);
        finish();
    }

}
