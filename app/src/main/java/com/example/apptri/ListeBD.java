package com.example.apptri;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ListeBD extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "liste.db";

    public final String SQL_CREATE = "CREATE TABLE Liste (id INTEGER PRIMARY KEY, libelle Text, text TEXT);";
    public final String SQL_INSERT = "INSERT INTO Liste VALUES(1,'dechets','Les dechets c est pas ouf');";
    public final String SQL_DELETE = "DROP TABLE IF EXISTS Liste;";

    public ListeBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
        db.execSQL(SQL_INSERT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE);
        onCreate(db);
    }
}