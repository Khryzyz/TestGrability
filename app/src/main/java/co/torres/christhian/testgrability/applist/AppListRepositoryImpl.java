package co.torres.christhian.testgrability.applist;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import co.torres.christhian.testgrability.applist.events.AppListEvent;
import co.torres.christhian.testgrability.database.AppDatabase;
import co.torres.christhian.testgrability.lib.EventBus;
import co.torres.christhian.testgrability.lib.GreenRobotEventBus;
import co.torres.christhian.testgrability.models.AppInfo;

/**
 * Created by Ceindetec02 on 09/11/2016.
 */

public class AppListRepositoryImpl implements AppListRepository {
    Context context;
    int idCategoria;

    @Override
    public void loadData(Context context, int idCategoria) {
        this.idCategoria = idCategoria;
        this.context = context;
        new LoadData().execute();
    }


    private void postEvent(int type) {

        postEvent(type, null, null);

    }

    private void postEvent(int type, List<AppInfo> appInfoList) {

        postEvent(type, appInfoList, null);

    }

    private void postEvent(int type, List<AppInfo> appInfoList, String errorMessage) {
        AppListEvent appListEvent = new AppListEvent();
        appListEvent.setEventType(type);
        if (errorMessage != null) {
            appListEvent.setErrorMessage(errorMessage);
        }
        if (appInfoList != null) {
            appListEvent.setAppInfoList(appInfoList);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(appListEvent);
    }


    public class LoadData extends AsyncTask<Void, Void, List<AppInfo>> {

        @Override
        protected List<AppInfo> doInBackground(Void... params) {
            // Carga inicial de registros
            List<AppInfo> appInfoList = new ArrayList<AppInfo>();
            appInfoList = AppDatabase.getInstance(context).obtenerListadoAppsByCategoriaId(idCategoria);
            return appInfoList;

        }

        @Override
        protected void onPostExecute(List<AppInfo> appInfoList) {
            super.onPostExecute(appInfoList);
            postEvent(AppListEvent.onLoadSuccess, appInfoList);
        }
    }

}
