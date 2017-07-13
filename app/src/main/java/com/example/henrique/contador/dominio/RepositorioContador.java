package com.example.henrique.contador.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.widget.*;

import com.example.henrique.contador.dominio.entidades.Contador;

/**
 * Created by Henrique on 13/01/2016.
 */
public class RepositorioContador {
    private SQLiteDatabase connection;

    public RepositorioContador(SQLiteDatabase connection) {
        this.connection = connection;
    }

    public Contador carregarObjeto(Context context) {

        Cursor cursor = connection.query("Contador", null, null, null, null, null, null);
        Contador contador = new Contador();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                contador.setId(cursor.getLong(0));

                contador.setValor(cursor.getInt(1));
            }
            while (cursor.moveToNext());
        }

        return contador;
    }

    private ContentValues preencheContentValues(Contador contador){
        ContentValues values = new ContentValues();
        values.put("valor", contador.getValor());
        return values;
    }

    public void inserir(Contador contador)
    {
        ContentValues values = preencheContentValues(contador);
        connection.insertOrThrow("Contador", null, values);
    }

    public void atualizar(Contador contador)
    {
        ContentValues values = preencheContentValues(contador);
        connection.update("Contador", values, " _id = ? ", new String[] { String.valueOf(contador.getId()) }   );
    }



}
