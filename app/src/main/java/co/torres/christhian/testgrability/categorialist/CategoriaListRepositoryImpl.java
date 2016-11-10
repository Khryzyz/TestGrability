package co.torres.christhian.testgrability.categorialist;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import co.torres.christhian.testgrability.categorialist.events.CategoriaListEvent;
import co.torres.christhian.testgrability.database.AppDatabase;
import co.torres.christhian.testgrability.lib.EventBus;
import co.torres.christhian.testgrability.lib.GreenRobotEventBus;
import co.torres.christhian.testgrability.models.CategoryApp;

public class CategoriaListRepositoryImpl implements CategoriaListRepository {

    Context context;

    @Override
    public void loadData(Context context) {
        this.context = context;
        new LoadData().execute();
    }


    private void postEvent(int type) {

        postEvent(type, null, null);

    }

    private void postEvent(int type, List<CategoryApp> categoryAppList) {

        postEvent(type, categoryAppList, null);

    }

    private void postEvent(int type, List<CategoryApp> categoryAppList, String errorMessage) {
        CategoriaListEvent categoriaListEvent = new CategoriaListEvent();
        categoriaListEvent.setEventType(type);
        if (errorMessage != null) {
            categoriaListEvent.setErrorMessage(errorMessage);
        }
        if (categoryAppList != null) {
            categoriaListEvent.setCategoryAppList(categoryAppList);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(categoriaListEvent);
    }


    public class LoadData extends AsyncTask<Void, Void, List<CategoryApp>> {

        @Override
        protected List<CategoryApp> doInBackground(Void... params) {
            // Carga inicial de registros
            List<CategoryApp> categoriaAppList = new ArrayList<CategoryApp>();
            categoriaAppList = AppDatabase.getInstance(context).obtenerListadoCategorias();
            return categoriaAppList;

        }

        @Override
        protected void onPostExecute(List<CategoryApp> categoryAppList) {
            super.onPostExecute(categoryAppList);
            postEvent(CategoriaListEvent.onLoadSuccess, categoryAppList);
        }
    }

}
