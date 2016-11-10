package co.torres.christhian.testgrability.lib;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Created by Ceindetec02 on 07/11/2016.
 */

public class VolleyTransaction {

    private final static String URL_BASE = "https://itunes.apple.com/us/rss/topfreeapplications/limit=20/json";
    private final static String ID_FEED = "feed";

    private static final String TAG_ERROR_JSON = "Error Json: ";
    private static final String TAG_ERROR_VOLLEY = "Error Volley: ";

    //Constructor de la clase TransactionArrayAdapterSucursales
    public void getData(Context context, final VolleyCallback callback) {

        // Añadir petición GSON a la cola
        VolleySingleton.getInstance(context).addToRequestQueue(

                //Llamado al constructor de la clase GsonRequest
                new GsonRequest<JsonObject>(

                        //@param int method
                        Request.Method.GET,

                        //@param URL
                        URL_BASE,

                        //@param Class<T> clazz Clase o modelo en el que se formatean los datos
                        JsonObject.class,

                        //@param headers
                        null,

                        //@param Listener
                        new Response.Listener<JsonObject>() {
                            @Override
                            public void onResponse(JsonObject response) {

                                try {
                                    JsonObject jsonObject = response.getAsJsonObject(ID_FEED);
                                    callback.onSuccess(jsonObject);

                                } catch (JsonIOException e) {
                                    e.printStackTrace();
                                    Log.e(TAG_ERROR_JSON, e.toString());
                                } catch (JsonParseException e) {
                                    e.printStackTrace();
                                    Log.e(TAG_ERROR_JSON, e.toString());
                                }
                            }
                        },
                        //@param errorListener
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG_ERROR_VOLLEY, error.getMessage());
                                callback.onError(error.getMessage());
                            }
                        }
                )
        );
    }

    public interface VolleyCallback {
        void onSuccess(JsonObject data);
        void onError(String errorMessage);
    }

}
