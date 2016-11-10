package co.torres.christhian.testgrability.categorialist.ui;

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
import android.transition.Slide;
import android.widget.GridView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import co.torres.christhian.testgrability.R;
import co.torres.christhian.testgrability.applist.ui.AppListActivity_;
import co.torres.christhian.testgrability.categorialist.CategoriaListPresenter;
import co.torres.christhian.testgrability.categorialist.CategoriaListPresenterImpl;
import co.torres.christhian.testgrability.categorialist.ui.adapter.CategoriaAdapter;
import co.torres.christhian.testgrability.models.CategoryApp;
import co.torres.christhian.testgrability.splashscreen.ui.SplashScreenActivity;

@EActivity(R.layout.categoria_list)
public class CategoriaListActivity extends AppCompatActivity implements CategoriaListView, CategoriaAdapter.OnItemClickListener {

    //Instanciamiento del presentador
    private CategoriaListPresenter categoriaListPresenter;

    private CategoriaAdapter categoriaAdapter;

    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    @ViewById
    RecyclerView rcvContentCategoria;

    @ViewById
    Toolbar tlbCategoriaToolBar;

    //Metodo inicial de la activity
    @AfterViews
    void CategoriaListInit() {

        //Inicializacion del presentador
        categoriaListPresenter = new CategoriaListPresenterImpl(this);
        categoriaListPresenter.onCreate();
        categoriaListPresenter.loadData(this);

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
            getWindow().setReenterTransition(new Slide());
            getWindow().setExitTransition(new Fade().setDuration(500));
        }

    }

    /**
     * Metodo que determina la orientacion de la app
     */
    private void setOrientation() {
        rcvContentCategoria.setHasFixedSize(true);

        if (getResources().getBoolean(R.bool.portrait_only)) {

            //Coloca la activity en PORTRAIT
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            //Coloca el manejador del layout de manera lineal
            linearLayoutManager = new LinearLayoutManager(this);
            rcvContentCategoria.setLayoutManager(linearLayoutManager);

        } else {

            //Coloca la activity en LANDSCAPE
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            //Coloca el manejador del layout de manera Grilla
            gridLayoutManager = new GridLayoutManager(this, 2);
            rcvContentCategoria.setLayoutManager(gridLayoutManager);
        }


        categoriaAdapter = new CategoriaAdapter(this);
        rcvContentCategoria.setAdapter(categoriaAdapter);

    }

    /**
     * Metodo para setear la ToolBarr
     */
    private void setToolbar() {
        tlbCategoriaToolBar.setTitle(getString(R.string.categoria_title));
        setSupportActionBar(tlbCategoriaToolBar);
    }

    /**
     * Metodo sobrecargado de la vista para la destruccion de la activity
     */
    @Override
    public void onDestroy() {
        categoriaListPresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Metodo sobrecargado de la view para manejar la obtencion correcta de los datos
     *
     * @param categoryAppList
     */
    @Override
    public void loadDataSuccess(List<CategoryApp> categoryAppList) {

        categoriaAdapter.swapData(categoryAppList);

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
     * @param idCategoria
     */
    @Override
    public void onClick(CategoriaAdapter.ViewHolder holder, int idCategoria) {

        Bundle args = new Bundle();
        args.putInt("idCategoria", idCategoria);

        //Inicializacion del intent para ir a la activity AppListActivity_
        Intent intent = new Intent(CategoriaListActivity.this, AppListActivity_.class);
        intent.putExtras(args);

        ActivityOptions options0 = null;
        //Si la version soporta transicioes lanza la actividad con transicion
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options0 = ActivityOptions.makeSceneTransitionAnimation(CategoriaListActivity.this);
            startActivity(intent, options0.toBundle());
        } else {
            startActivity(intent);
        }


    }
}
