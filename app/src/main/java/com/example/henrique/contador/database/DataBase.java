package com.example.henrique.contador.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Henrique on 13/01/2016.
 */
public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context)
    {
        super(context, "CONTADOR", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSQL.getCreateContato());
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
