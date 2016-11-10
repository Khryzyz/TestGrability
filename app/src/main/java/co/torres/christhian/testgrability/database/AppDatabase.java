package co.torres.christhian.testgrability.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.torres.christhian.testgrability.models.AppInfo;
import co.torres.christhian.testgrability.models.CategoryApp;


//Clase que administra el acceso y operaciones hacia la base de datos
public final class AppDatabase extends SQLiteOpenHelper {

    // Mapeado rápido de indices
    public static final int COLUMN_ID = 0;
    public static final int COLUMN_NAME = 1;
    public static final int COLUMN_SUMMARY = 2;
    public static final int COLUMN_PRICE = 3;
    public static final int COLUMN_CURRENCY = 4;
    public static final int COLUMN_RIGHTS = 5;
    public static final int COLUMN_TITLE = 6;
    public static final int COLUMN_CATEGORYID = 7;
    public static final int COLUMN_RELEASE = 8;
    public static final int COLUMN_URL_CERO = 9;
    public static final int COLUMN_URL_UNO = 10;
    public static final int COLUMN_URL_DOS = 11;


    //Instancia singleton
    private static AppDatabase singleton;

    //Etiqueta de depuración
    private static final String TAG = AppDatabase.class.getSimpleName();


    //Nombre de la base de datos
    public static final String DATABASE_NAME = "App.db";

    //Versión actual de la base de datos
    public static final int DATABASE_VERSION = 8;


    private AppDatabase(Context context) {
        super(context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);

    }

    /**
     * Retorna la instancia unica del singleton
     *
     * @param context contexto donde se ejecutarán las peticiones
     * @return Instancia
     */
    public static synchronized AppDatabase getInstance(Context context) {
        if (singleton == null) {
            singleton = new AppDatabase(context.getApplicationContext());
        }
        return singleton;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla 'app'
        db.execSQL(ScriptDatabase.CREAR_APP);
        db.execSQL(ScriptDatabase.CREAR_CATEGORIA);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Añade los cambios que se realizarán en el esquema
        db.execSQL("DROP TABLE IF EXISTS " + ScriptDatabase.APP_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ScriptDatabase.CAT_TABLE_NAME);
        onCreate(db);
    }

    /**
     * Obtiene todos los registros de la tabla app
     *
     * @return cursor con los registros
     */
    public Cursor obtenerApps() {
        // Seleccionamos todas las filas de la tabla 'app'
        return getWritableDatabase().rawQuery(
                "select * from " + ScriptDatabase.APP_TABLE_NAME, null);
    }

    /**
     * Obtiene todos los registros de la tabla categoria
     *
     * @return cursor con los registros
     */
    public List<AppInfo> obtenerListadoAppsByCategoriaId(int idCategoria) {
        List<AppInfo> appInfoList = new ArrayList<AppInfo>();
        Cursor cursor = getWritableDatabase().rawQuery("select * from " + ScriptDatabase.APP_TABLE_NAME + " where " + ScriptDatabase.ColumnApps.CATEGORYID + " = " + idCategoria, null);
        while (cursor.moveToNext()) {
            AppInfo appInfo = new AppInfo(
                    cursor.getInt(COLUMN_ID),
                    cursor.getString(COLUMN_NAME),
                    cursor.getString(COLUMN_SUMMARY),
                    cursor.getString(COLUMN_PRICE),
                    cursor.getString(COLUMN_CURRENCY),
                    cursor.getString(COLUMN_RIGHTS),
                    cursor.getString(COLUMN_TITLE),
                    cursor.getInt(COLUMN_CATEGORYID),
                    cursor.getString(COLUMN_RELEASE),
                    cursor.getString(COLUMN_URL_CERO),
                    cursor.getString(COLUMN_URL_UNO),
                    cursor.getString(COLUMN_URL_DOS)
            );
            appInfoList.add(appInfo);
        }
        return appInfoList;
    }


    /**
     * Obtiene todos los registros de la tabla categoria
     *
     * @return cursor con los registros
     */
    public AppInfo obtenerInfoAppById(int idApp) {
        Cursor cursor = getWritableDatabase().rawQuery("select * from " + ScriptDatabase.APP_TABLE_NAME + " where " + ScriptDatabase.ColumnApps.ID + " = " + idApp + " limit 1", null);
        cursor.moveToFirst();
        AppInfo appInfo = new AppInfo(
                cursor.getInt(COLUMN_ID),
                cursor.getString(COLUMN_NAME),
                cursor.getString(COLUMN_SUMMARY),
                cursor.getString(COLUMN_PRICE),
                cursor.getString(COLUMN_CURRENCY),
                cursor.getString(COLUMN_RIGHTS),
                cursor.getString(COLUMN_TITLE),
                cursor.getInt(COLUMN_CATEGORYID),
                cursor.getString(COLUMN_RELEASE),
                cursor.getString(COLUMN_URL_CERO),
                cursor.getString(COLUMN_URL_UNO),
                cursor.getString(COLUMN_URL_DOS)
        );
        return appInfo;
    }

    /**
     * Inserta un registro en la tabla app
     *
     * @param name
     * @param summary
     * @param price
     * @param currency
     * @param rights
     * @param title
     * @param categoryid
     * @param release
     */
    public void insertarApp(
            String name,
            String summary,
            String price,
            String currency,
            String rights,
            String title,
            int categoryid,
            String release,
            String urlimagecero,
            String urlimageuno,
            String urlimagedos) {

        ContentValues values = new ContentValues();
        values.put(ScriptDatabase.ColumnApps.NAME, name);
        values.put(ScriptDatabase.ColumnApps.SUMMARY, summary);
        values.put(ScriptDatabase.ColumnApps.PRICE, price);
        values.put(ScriptDatabase.ColumnApps.CURRENCY, currency);
        values.put(ScriptDatabase.ColumnApps.RIGHTS, rights);
        values.put(ScriptDatabase.ColumnApps.TITLE, title);
        values.put(ScriptDatabase.ColumnApps.CATEGORYID, categoryid);
        values.put(ScriptDatabase.ColumnApps.RELEASE, release);
        values.put(ScriptDatabase.ColumnApps.URLIMAGECERO, urlimagecero);
        values.put(ScriptDatabase.ColumnApps.URLIMAGEUNO, urlimageuno);
        values.put(ScriptDatabase.ColumnApps.URLIMAGEDOS, urlimagedos);

        // Insertando el registro en la base de datos
        getWritableDatabase().insert(
                ScriptDatabase.APP_TABLE_NAME,
                null,
                values
        );
    }

    /**
     * Modifica los valores de las columnas de una app
     *
     * @param id
     * @param name
     * @param summary
     * @param price
     * @param currency
     * @param rights
     * @param title
     * @param categoryid
     * @param release
     */
    public void actualizarApp(int id,
                              String name,
                              String summary,
                              String price,
                              String currency,
                              String rights,
                              String title,
                              int categoryid,
                              String release,
                              String urlimagecero,
                              String urlimageuno,
                              String urlimagedos) {


        ContentValues values = new ContentValues();
        values.put(ScriptDatabase.ColumnApps.NAME, name);
        values.put(ScriptDatabase.ColumnApps.SUMMARY, summary);
        values.put(ScriptDatabase.ColumnApps.PRICE, price);
        values.put(ScriptDatabase.ColumnApps.CURRENCY, currency);
        values.put(ScriptDatabase.ColumnApps.RIGHTS, rights);
        values.put(ScriptDatabase.ColumnApps.TITLE, title);
        values.put(ScriptDatabase.ColumnApps.CATEGORYID, categoryid);
        values.put(ScriptDatabase.ColumnApps.RELEASE, release);
        values.put(ScriptDatabase.ColumnApps.URLIMAGECERO, urlimagecero);
        values.put(ScriptDatabase.ColumnApps.URLIMAGEUNO, urlimageuno);
        values.put(ScriptDatabase.ColumnApps.URLIMAGEDOS, urlimagedos);

        // Modificar app
        getWritableDatabase().update(
                ScriptDatabase.APP_TABLE_NAME,
                values,
                ScriptDatabase.ColumnApps.ID + "=?",
                new String[]{String.valueOf(id)});

    }


    /**
     * Procesa una lista de items para su almacenamiento local
     * y sincronización.
     *
     * @param entries lista de items
     */
    public void sincronizarApps(List<AppInfo> entries) {

        //Mapear temporalemente las apps nuevas para realizar una comparación con las locales
        HashMap<String, AppInfo> entryMap = new HashMap<String, AppInfo>();
        for (AppInfo e : entries) {
            entryMap.put(e.getName(), e);
        }

        //Obtener las apps locales        
        Log.i(TAG, "Consultar items actualmente almacenados");
        Cursor c = obtenerApps();
        assert c != null;
        Log.i(TAG, "Se encontraron " + c.getCount() + " apps, computando...");

        //Comenzar a comparar las apps
        int id;
        String name;
        String summary;
        String price;
        String currency;
        String rights;
        String title;
        int categoryid;
        String release;
        String urlimagecero;
        String urlimageuno;
        String urlimagedos;

        while (c.moveToNext()) {

            id = c.getInt(COLUMN_ID);
            name = c.getString(COLUMN_NAME);
            summary = c.getString(COLUMN_SUMMARY);
            price = c.getString(COLUMN_PRICE);
            currency = c.getString(COLUMN_CURRENCY);
            rights = c.getString(COLUMN_RIGHTS);
            title = c.getString(COLUMN_TITLE);
            categoryid = c.getInt(COLUMN_CATEGORYID);
            release = c.getString(COLUMN_RELEASE);
            urlimagecero = c.getString(COLUMN_URL_CERO);
            urlimageuno = c.getString(COLUMN_URL_UNO);
            urlimagedos = c.getString(COLUMN_URL_DOS);

            AppInfo match = entryMap.get(name);
            if (match != null) {
                // Filtrar apps existentes. Remover para prevenir futura inserción
                entryMap.remove(name);


                //Comprobar si la app necesita ser actualizada
                if ((match.getName() != null && !match.getName().equals(name)) ||
                        (match.getSummary() != null && !match.getSummary().equals(summary)) ||
                        (match.getPrice() != null && !match.getPrice().equals(price)) ||
                        (match.getCurrency() != null && !match.getCurrency().equals(currency)) ||
                        (match.getRights() != null && !match.getRights().equals(rights)) ||
                        (match.getTitle() != null && !match.getTitle().equals(title)) ||
                        (match.getCategoryId() != 0 && match.getCategoryId() != (categoryid)) ||
                        (match.getRelease() != null && !match.getRelease().equals(release)) ||
                        (match.getUrlImageCero() != null && !match.getUrlImageCero().equals(urlimagecero)) ||
                        (match.getUrlImageUno() != null && !match.getUrlImageUno().equals(urlimageuno)) ||
                        (match.getUrlImageDos() != null && !match.getUrlImageDos().equals(urlimagedos))) {
                    // Actualizar apps
                    actualizarApp(
                            id,
                            match.getName(),
                            match.getSummary(),
                            match.getPrice(),
                            match.getCurrency(),
                            match.getRights(),
                            match.getTitle(),
                            match.getCategoryId(),
                            match.getRelease(),
                            match.getUrlImageCero(),
                            match.getUrlImageUno(),
                            match.getUrlImageDos()
                    );

                }
            }
        }
        c.close();


        // Añadir apps nuevas
        for (AppInfo e : entryMap.values()) {
            Log.i(TAG, "Insertado: titulo=" + e.getName());
            insertarApp(
                    e.getName(),
                    e.getSummary(),
                    e.getPrice(),
                    e.getCurrency(),
                    e.getRights(),
                    e.getTitle(),
                    e.getCategoryId(),
                    e.getRelease(),
                    e.getUrlImageCero(),
                    e.getUrlImageUno(),
                    e.getUrlImageDos());
        }
        Log.i(TAG, "Se actualizaron los registros");

    }


    /**
     * Obtiene todos los registros de la tabla categoria
     *
     * @return cursor con los registros
     */
    public Cursor obtenerCategorias() {
        // Seleccionamos todas las filas de la tabla 'categoria'
        return getWritableDatabase().rawQuery("select * from " + ScriptDatabase.CAT_TABLE_NAME, null);
    }

    /**
     * Obtiene todos los registros de la tabla categoria
     *
     * @return cursor con los registros
     */
    public List<CategoryApp> obtenerListadoCategorias() {
        List<CategoryApp> categoriaAppList = new ArrayList<CategoryApp>();
        Cursor cursor = getWritableDatabase().rawQuery("select * from " + ScriptDatabase.CAT_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            CategoryApp categoryApp = new CategoryApp(cursor.getInt(COLUMN_ID), cursor.getString(COLUMN_NAME));
            categoriaAppList.add(categoryApp);
        }
        return categoriaAppList;
    }

    /**
     * Inserta un registro en la tabla categoria
     *
     * @param name
     */
    public void insertarCategoria(
            int id,
            String name) {

        ContentValues values = new ContentValues();
        values.put(ScriptDatabase.ColumnCategorias.ID, id);
        values.put(ScriptDatabase.ColumnCategorias.NAME, name);

        // Insertando el registro en la base de datos
        getWritableDatabase().insert(
                ScriptDatabase.CAT_TABLE_NAME,
                null,
                values
        );
    }

    /**
     * Modifica los valores de las columnas de una categoria
     *
     * @param id
     * @param name
     */
    public void actualizarCategoria(int id,
                                    String name) {


        ContentValues values = new ContentValues();
        values.put(ScriptDatabase.ColumnCategorias.NAME, name);

        // Modificar categoria
        getWritableDatabase().update(
                ScriptDatabase.CAT_TABLE_NAME,
                values,
                ScriptDatabase.ColumnCategorias.ID + "=?",
                new String[]{String.valueOf(id)});

    }


    /**
     * Procesa una lista de items para su almacenamiento local
     * y sincronización.
     *
     * @param entries lista de items
     */
    public void sincronizarCategorias(List<CategoryApp> entries) {

        //Mapear temporalemente las categorias nuevas para realizar una comparación con las locales
        HashMap<String, CategoryApp> entryMap = new HashMap<String, CategoryApp>();
        for (CategoryApp e : entries) {
            entryMap.put(e.getDescription(), e);
        }

        //Obtener las categorias locales
        Log.i(TAG, "Consultar items actualmente almacenados");
        Cursor c = obtenerCategorias();
        assert c != null;
        Log.i(TAG, "Se encontraron " + c.getCount() + " categorias, computando...");

        //Comenzar a comparar las categorias
        int id;
        String name;

        while (c.moveToNext()) {

            id = c.getInt(COLUMN_ID);
            name = c.getString(COLUMN_NAME);

            CategoryApp match = entryMap.get(name);
            if (match != null) {
                // Filtrar categorias existentes. Remover para prevenir futura inserción
                entryMap.remove(name);


                //Comprobar si la categoria necesita ser actualizada
                if ((match.getId() != 0 && match.getId() != (id)) ||
                        (match.getDescription() != null && !match.getDescription().equals(name))) {
                    // Actualizar categorias
                    actualizarCategoria(
                            id,
                            match.getDescription()
                    );

                }
            }
        }
        c.close();


        // Añadir categorias nuevas
        for (CategoryApp e : entryMap.values()) {
            Log.i(TAG, "Insertado: titulo=" + e.getDescription());
            insertarCategoria(
                    e.getId(),
                    e.getDescription()
            );
        }
        Log.i(TAG, "Se actualizaron los registros");
    }

}