package co.torres.christhian.testgrability.appdetail;

import android.content.Context;
import android.os.AsyncTask;

import co.torres.christhian.testgrability.appdetail.events.AppDetailEvent;
import co.torres.christhian.testgrability.database.AppDatabase;
import co.torres.christhian.testgrability.lib.EventBus;
import co.torres.christhian.testgrability.lib.GreenRobotEventBus;
import co.torres.christhian.testgrability.models.AppInfo;

/**
 * Created by Ceindetec02 on 09/11/2016.
 */

public class AppDetailRepositoryImpl implements AppDetailRepository {

    Context context;
    int idApp;

    @Override
    public void loadData(Context context, int idApp) {
        this.idApp = idApp;
        this.context = context;
        new LoadData().execute();
    }



    private void postEvent(int type) {

        postEvent(type, null, null);

    }

    private void postEvent(int type, AppInfo appInfo) {

        postEvent(type, appInfo, null);

    }

    private void postEvent(int type, AppInfo appInfo, String errorMessage) {
        AppDetailEvent appDetailEvent = new AppDetailEvent();
        appDetailEvent.setEventType(type);
        if (errorMessage != null) {
            appDetailEvent.setErrorMessage(errorMessage);
        }
        if (appInfo != null) {
            appDetailEvent.setAppInfo(appInfo);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(appDetailEvent);
    }


    public class LoadData extends AsyncTask<Void, Void, AppInfo> {

        @Override
        protected AppInfo doInBackground(Void... params) {
            // Carga inicial de registros
            AppInfo appInfo = new AppInfo();
            appInfo = AppDatabase.getInstance(context).obtenerInfoAppById(idApp);
            return appInfo;

        }

        @Override
        protected void onPostExecute(AppInfo appInfo) {
            super.onPostExecute(appInfo);
            postEvent(AppDetailEvent.onLoadSuccess, appInfo);
        }
    }


}
