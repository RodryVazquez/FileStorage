package com.example.rodrigovazquez.filestorage.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Helper para la administracion de la base datos
 */

public class EventDataSQLHelper extends SQLiteOpenHelper {

    /* Nombre de la base de datos
     * Version
     * Nombre de la tabla
     * Columna */
    private static final String DATABASE_NAME = "events.db";
    private static final int VERSION = 1;
    public static final String TABLE = "User";
    public static final String USERNAME = "username";

    /**
     * @param context
     */
    public EventDataSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    /**
     * Crea la tabla
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE + "( "
                + BaseColumns._ID
                + " integer primary key autoincrement, "
                + USERNAME + " text not null);";
        Log.d("Events Data", "onCreate: " + sql);
        db.execSQL(sql);
    }

    /**
     * Actualiza la tabla tomando en cuenta la version actual y la anterior
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;
        String sql = null;
        if (oldVersion == 1)
            sql = "alter table " + TABLE + " add note text;";
        if (oldVersion == 2)
            sql = "";
        Log.d("Events Data", "onUpgrade : " + sql);
        if (sql != null)
            db.execSQL(sql);
    }
}
