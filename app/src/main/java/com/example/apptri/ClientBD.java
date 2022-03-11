package com.example.apptri;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ClientBD extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "client.db";

    public final String SQL_CREATE = "CREATE TABLE Utilisateurs (id INTEGER PRIMARY KEY, nom Text, prenom TEXT, sexe TEXT, ville TEXT, codePostal TEXT, Email TEXT, MDP TEXT, cMDP TEXT);";
    //public final String SQL_INSERT = "INSERT INTO Utilisateurs VALUES(1,'Piazzi','Nathan','M','Toulouse','31000','NP@android.com','mdp','mdp');";
    public final String SQL_DELETE = "DROP TABLE IF EXISTS Utilisateurs;";

    public ClientBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE);
        onCreate(db);
    }
}
