package com.example.spotifly;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NOMBRE = "Spotifly";
    public static final String TABLA_USUARIOS = "CREATE TABLE usuarios (" +
            "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "nombre TEXT NOT NULL UNIQUE," +
            "sexo TEXT ,"+
            "fecha_nac TEXT ,"+
            "imagen BLOB ," +
            "video TEXT)";

    public static final String TABLA_CANCIONES = "CREATE TABLE canciones (" +
            "id_cancion INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "titulo TEXT NOT NULL," +
            "artista text)";

    public static final String TABLA_USUARIO_CANCION = "CREATE TABLE usuario_cancion (" +
            "id_usuario_cancion INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "id_usuario INTEGER NOT NULL," +
            "id_cancion INTEGER NOT NULL," +
            " FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE," +
            " FOREIGN KEY (id_cancion) REFERENCES canciones(id_cancion) ON DELETE CASCADE ON UPDATE CASCADE)";

    public BaseDatos(@Nullable Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_USUARIOS);
        db.execSQL(TABLA_CANCIONES);
        db.execSQL(TABLA_USUARIO_CANCION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS canciones");
        db.execSQL("DROP TABLE IF EXISTS usuario_cancion");
        onCreate(db);
    }

}