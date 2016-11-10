package co.torres.christhian.testgrability.database;

/**
 * Clase que restaura la base de datos a un estado inicial
 */

public class ScriptDatabase {

    //Etiqueta para Depuración
    private static final String TAG = ScriptDatabase.class.getSimpleName();

    // Metainformación de la base de datos
    public static final String APP_TABLE_NAME = "app";
    public static final String CAT_TABLE_NAME = "categoria";
    public static final String STRING_TYPE = "TEXT";
    public static final String INT_TYPE = "INTEGER";

    // Campos de la tabla App
    public static class ColumnApps {

        public static final String ID = "id";
        public static final String NAME = "nombre";
        public static final String SUMMARY = "summary";
        public static final String PRICE = "precio";
        public static final String CURRENCY = "currency";
        public static final String RIGHTS = "rights";
        public static final String TITLE = "title";
        public static final String CATEGORYID = "category_id";
        public static final String RELEASE = "release";
        public static final String URLIMAGECERO = "url_image_cero";
        public static final String URLIMAGEUNO = "url_image_uno";
        public static final String URLIMAGEDOS = "url_image_dos";

    }

    // Campos de la tabla Categorias
    public static class ColumnCategorias {

        public static final String ID = "id";
        public static final String NAME = "nombre";

    }

    // Comando CREATE para la tabla APP
    public static final String CREAR_APP =
            "CREATE TABLE " + APP_TABLE_NAME + "(" +
                    ColumnApps.ID + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnApps.NAME + " " + STRING_TYPE + " not null," +
                    ColumnApps.SUMMARY + " " + STRING_TYPE + "," +
                    ColumnApps.PRICE + " " + STRING_TYPE + "," +
                    ColumnApps.CURRENCY + " " + STRING_TYPE + "," +
                    ColumnApps.RIGHTS + " " + STRING_TYPE + "," +
                    ColumnApps.TITLE + " " + STRING_TYPE + "," +
                    ColumnApps.CATEGORYID + " " + INT_TYPE + "," +
                    ColumnApps.RELEASE + " " + STRING_TYPE + "," +
                    ColumnApps.URLIMAGECERO + " " + STRING_TYPE + "," +
                    ColumnApps.URLIMAGEUNO + " " + STRING_TYPE + "," +
                    ColumnApps.URLIMAGEDOS + " " + STRING_TYPE + ")";

    // Comando CREATE para la tabla Categoria
    public static final String CREAR_CATEGORIA =
            "CREATE TABLE " + CAT_TABLE_NAME + "(" +
                    ColumnCategorias.ID + " " + INT_TYPE + " primary key," +
                    ColumnCategorias.NAME + " " + STRING_TYPE + " not null)";

}
