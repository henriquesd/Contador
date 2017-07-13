package com.example.henrique.contador.dominio.entidades;

import java.io.Serializable;

/**
 * Created by Henrique on 13/01/2016.
 */
public class Contador implements Serializable {

    public static String ID = "_id";
    public static String VALOR = "valor";

    private long id;
    private int valor;

    public Contador()
    {
        id = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

}
