package com.example.henrique.contador.database;

/**
 * Created by Henrique on 13/01/2016.
 */
public class ScriptSQL {

    public static String getCreateContato()
    {
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE IF NOT EXISTS Contador ( ");
        sqlBuilder.append("_id    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("valor    INTEGER NOT NULL ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

}
