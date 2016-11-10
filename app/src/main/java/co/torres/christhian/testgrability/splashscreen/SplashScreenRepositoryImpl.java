package co.torres.christhian.testgrability.splashscreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import co.torres.christhian.testgrability.database.AppDatabase;
import co.torres.christhian.testgrability.lib.GreenRobotEventBus;
import co.torres.christhian.testgrability.lib.EventBus;
import co.torres.christhian.testgrability.lib.VolleyTransaction;
import co.torres.christhian.testgrability.models.AppInfo;
import co.torres.christhian.testgrability.models.CategoryApp;
import co.torres.christhian.testgrability.splashscreen.events.SplashScreenEvent;

public class SplashScreenRepositoryImpl implements SplashScreenRepository {

    private final static String TAG_ERROR_DATA = "Error: Data ";
    private final static String ID_ENTRY = "entry";

    Context context;

    public SplashScreenRepositoryImpl() {
    }

    @Override
    public void checkData(final Context context) {

        if (verificarInternet(context)) {

            VolleyTransaction volleyTransaction = new VolleyTransaction();

            volleyTransaction.getData(context, new VolleyTransaction.VolleyCallback() {
                @Override
                public void onSuccess(JsonObject data) {
                    try {

                        (data.getAsJsonObject("updated")).get("label").getAsString();

                        SharedPreferences prefs = context.getSharedPreferences("date_updated", Context.MODE_PRIVATE);
                        String date_system = prefs.getString("date_updated", "NODATA");
                        String date_json = (data.getAsJsonObject("updated")).get("label").getAsString();

                        if (date_system.equals("NODATA") || !date_system.equals(date_json)) {
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("date_updated", date_json);
                            editor.commit();

                            postEvent(SplashScreenEvent.onDataDeprecated);
                        } else {
                            postEvent(SplashScreenEvent.onDataUpdated);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG_ERROR_DATA, e.toString());
                        postEvent(SplashScreenEvent.onUpdateDataError, e.toString());
                    }
                }

                @Override
                public void onError(String errorMessage) {
                    Log.e(TAG_ERROR_DATA, errorMessage.toString());
                    postEvent(SplashScreenEvent.onDataError, errorMessage.toString());
                }
            });
        } else {
            Log.i("Internet", "La conexion a internet no esta disponible");
            postEvent(SplashScreenEvent.onInternetNoConnected);
        }

    }

    @Override
    public void updateData(final Context context) {

        if (verificarInternet(context)) {

            VolleyTransaction volleyTransaction = new VolleyTransaction();

            volleyTransaction.getData(context, new VolleyTransaction.VolleyCallback() {
                @Override
                public void onSuccess(JsonObject data) {
                    try {

                        AppDatabase.getInstance(context).sincronizarApps(procesarDataEntry(data.getAsJsonArray(ID_ENTRY)));
                        AppDatabase.getInstance(context).sincronizarCategorias(procesarDataCategory(data.getAsJsonArray(ID_ENTRY)));

                        postEvent(SplashScreenEvent.onUpdateDataSuccess);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG_ERROR_DATA, e.toString());
                        postEvent(SplashScreenEvent.onUpdateDataError, e.toString());
                    }
                }

                @Override
                public void onError(String errorMessage) {
                    Log.i("Internet", "La conexion a internet no esta disponible");
                    postEvent(SplashScreenEvent.onDataError);
                }
            });
        } else {
            Log.i("Internet", "La conexion a internet no esta disponible");
            postEvent(SplashScreenEvent.onInternetNoConnected);
        }
    }

    private void postEvent(int type, String errorMessage) {
        SplashScreenEvent splashScreenEvent = new SplashScreenEvent();
        splashScreenEvent.setEventType(type);
        if (errorMessage != null) {
            splashScreenEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(splashScreenEvent);
    }


    private void postEvent(int type) {

        postEvent(type, null);

    }


    private boolean verificarInternet(Context context) {

        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            return true;

        } else {

            return false;

        }
    }

    private List<AppInfo> procesarDataEntry(JsonArray data) {

        List<AppInfo> listAppInfo = new ArrayList<AppInfo>();

        for (int i = 0; i < data.size(); i++) {

            //Inicializacion del objeto appInfo
            AppInfo appInfo = new AppInfo();

            //Obtencion del JsonObject del objeto de respuesta
            JsonObject dataEntry = (JsonObject) data.get(i);

            //Agreando el nombre al AppInfo
            appInfo.setName(((JsonObject) dataEntry.get("im:name")).get("label").getAsString());

            //Agregando el resumen al objeto AppInfo
            appInfo.setSummary(((JsonObject) dataEntry.get("summary")).get("label").getAsString());

            //Agregando el propietrario  al objeto AppInfo
            appInfo.setRights(((JsonObject) dataEntry.get("rights")).get("label").getAsString());

            //Agregando el titulo al objeto AppInfo
            appInfo.setTitle(((JsonObject) dataEntry.get("title")).get("label").getAsString());

            //Agregando el valor y la moneda a appInfo
            appInfo.setRelease((((JsonObject) dataEntry.get("im:releaseDate")).getAsJsonObject("attributes")).get("label").getAsString());

            //Inicializacion del objeto Category
            CategoryApp categoryApp = new CategoryApp();

            //Agregando el valor y la moneda a appInfo
            categoryApp.setId((((JsonObject) dataEntry.get("category")).getAsJsonObject("attributes")).get("im:id").getAsInt());
            categoryApp.setDescription((((JsonObject) dataEntry.get("category")).getAsJsonObject("attributes")).get("label").getAsString());

            //Agregando el id del array de imagenes
            appInfo.setCategoryId((((JsonObject) dataEntry.get("category")).getAsJsonObject("attributes")).get("im:id").getAsInt());

            //Agregando el valor y la moneda a appInfo
            appInfo.setPrice((((JsonObject) dataEntry.get("im:price")).getAsJsonObject("attributes")).get("amount").getAsString());
            appInfo.setCurrency((((JsonObject) dataEntry.get("im:price")).getAsJsonObject("attributes")).get("currency").getAsString());

            //Agregando las urls's de las imagenes
            JsonArray dataImage = dataEntry.getAsJsonArray("im:image");

            appInfo.setUrlImageCero(((JsonObject) dataImage.get(0)).get("label").getAsString());
            appInfo.setUrlImageUno(((JsonObject) dataImage.get(1)).get("label").getAsString());
            appInfo.setUrlImageDos(((JsonObject) dataImage.get(2)).get("label").getAsString());

            listAppInfo.add(appInfo);
        }
        return listAppInfo;
    }

    private List<CategoryApp> procesarDataCategory(JsonArray data) {

        List<CategoryApp> listAppCategory = new ArrayList<CategoryApp>();

        int categoria_id;
        String categoria_name;
        boolean coincidencia;

        for (int i = 0; i < data.size(); i++) {

            //Obtencion del JsonObject del objeto de respuesta
            JsonObject dataEntry = (JsonObject) data.get(i);

            //Inicializacion del objeto Category
            CategoryApp categoryApp = new CategoryApp();

            categoria_id = (((JsonObject) dataEntry.get("category")).getAsJsonObject("attributes")).get("im:id").getAsInt();
            categoria_name = (((JsonObject) dataEntry.get("category")).getAsJsonObject("attributes")).get("label").getAsString();

            coincidencia = true;

            for (int j = 0; j < listAppCategory.size(); j++) {
                if (listAppCategory.get(j).getId() == categoria_id) {
                    coincidencia = false;
                    break;
                }
            }

            if (coincidencia){

                //Agregando el valor y la moneda a appInfo
                categoryApp.setId(categoria_id);
                categoryApp.setDescription(categoria_name);


                listAppCategory.add(categoryApp);
            }
        }
        return listAppCategory;
    }

}
