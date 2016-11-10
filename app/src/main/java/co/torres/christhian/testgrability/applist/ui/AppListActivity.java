package co.torres.christhian.testgrability.applist.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import co.torres.christhian.testgrability.R;
import co.torres.christhian.testgrability.appdetail.ui.AppDetailActivity_;
import co.torres.christhian.testgrability.applist.AppListPresenter;
import co.torres.christhian.testgrability.applist.AppListPresenterImpl;
import co.torres.christhian.testgrability.applist.ui.adapter.AppAdapter;
import co.torres.christhian.testgrability.models.AppInfo;

@EActivity(R.layout.app_list)
public class AppListActivity extends AppCompatActivity implements AppListView, AppAdapter.OnItemClickListener {


    //Instanciamiento del presentador
    private AppListPresenter appListPresenter;

    private AppAdapter appAdapter;

    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    int idCategoria;

    Bundle bundle;

    @ViewById
    RecyclerView rcvContentApp;

    @ViewById
    Toolbar tlbAppToolBar;

    //Metodo inicial de la activity
    @AfterViews
    void CategoriaListInit() {

        //Obtencion del dato de la categoria
        if (getIntent().getExtras() != null) {
            bundle = getIntent().getExtras();
            idCategoria = bundle.getInt("idCategoria");
        }

        //Inicializacion del presentador
        appListPresenter = new AppListPresenterImpl(this);
        appListPresenter.onCreate();
        appListPresenter.loadData(this, idCategoria);

        //Metodo para setear la ToolBarr
        setToolbar();

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
            getWindow().setReenterTransition(new Fade());
            getWindow().setExitTransition(new Explode().setDuration(500));
        }

    }

    /**
     * Metodo que inicializa las transiciones entre actividades
     */
    private void setOrientation() {

        rcvContentApp.setHasFixedSize(true);

        if (getResources().getBoolean(R.bool.portrait_only)) {

            //Coloca la activity en PORTRAIT
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            //Coloca el manejador del layout de manera lineal
            linearLayoutManager = new LinearLayoutManager(this);
            rcvContentApp.setLayoutManager(linearLayoutManager);

        } else {

            //Coloca la activity en LANDSCAPE
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            //Coloca el manejador del layout de manera Grilla
            gridLayoutManager = new GridLayoutManager(this, 2);
            rcvContentApp.setLayoutManager(gridLayoutManager);
        }

        appAdapter = new AppAdapter(this);
        rcvContentApp.setAdapter(appAdapter);

    }

    /**
     * Metodo para setear la ToolBarr
     */
    private void setToolbar() {
        tlbAppToolBar.setTitle(getString(R.string.app_title));
        setSupportActionBar(tlbAppToolBar);
    }

    /**
     * Metodo sobrecargado de la vista para la destruccion de la activity
     */
    @Override
    public void onDestroy() {
        appListPresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Metodo sobrecargado de la view para manejar la obtencion correcta de los datos
     *
     * @param appList
     */
    @Override
    public void loadDataSuccess(List<AppInfo> appList) {
        appAdapter.swapData(appList);
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
     * Metodo para reaccionar al click del item
     *
     * @param holder
     * @param idApp
     */
    @Override
    public void onClick(AppAdapter.ViewHolder holder, int idApp) {

        Bundle args = new Bundle();
        args.putInt("idApp", idApp);

        Intent intent = new Intent(AppListActivity.this, AppDetailActivity_.class);
        intent.putExtras(args);
        ActivityOptions options0 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options0 = ActivityOptions.makeSceneTransitionAnimation(AppListActivity.this);
            startActivity(intent, options0.toBundle());
        } else {
            startActivity(intent);
        }

    }

    /**
     * Maneja el retorno de la actividdad para recuperar el id de la caategoria
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                bundle = getIntent().getExtras();
                idCategoria = bundle.getInt("idCategoria");
            }
        }
    }

}
