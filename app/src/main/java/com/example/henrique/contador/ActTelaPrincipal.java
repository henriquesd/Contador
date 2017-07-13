package com.example.henrique.contador;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.database.sqlite.*;
import android.database.*;

import com.example.henrique.contador.database.DataBase;
import com.example.henrique.contador.dominio.RepositorioContador;
import com.example.henrique.contador.dominio.entidades.Contador;

public class ActTelaPrincipal extends AppCompatActivity {

    private AlertDialog alerta;

    private int valorContador;
    private Contador contador;

    private DataBase dataBase;
    private SQLiteDatabase connection;
    private RepositorioContador repositorioContador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tela_principal);

        final TextView campoContador = (TextView) findViewById(R.id.txtValor);

        try {
            contador = new Contador();

            dataBase = new DataBase(this);
            connection = dataBase.getWritableDatabase();
            repositorioContador = new RepositorioContador(connection);

            contador = repositorioContador.carregarObjeto(this);
            campoContador.setText(Integer.toString(contador.getValor()));

        }
        catch (SQLException ex) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Erro ao criar o banco: " + ex.getMessage());
            dialog.setNeutralButton("Ok", null);
            dialog.show();
        }

        Button btnSomar = (Button) findViewById(R.id.btnSomar);
        btnSomar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                valorContador = Integer.parseInt(campoContador.getText().toString());
                valorContador += 1;
                campoContador.setText(Integer.toString(valorContador));
                contador.setValor(valorContador);
                salvar();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_tela_principal, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // quando alguma opção do menu é selecionado este método é chamado;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final TextView campoContador = (TextView) findViewById(R.id.txtValor);

        switch (item.getItemId()) {
            case R.id.menu_subtrair:
                subtrairContador();
                break;
            case R.id.menu_zerar:
                zerarContador();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void subtrairContador(){
        final TextView campoContador = (TextView) findViewById(R.id.txtValor);
        int valorCampo = Integer.parseInt(campoContador.getText().toString());
        if (valorCampo > 0) {
            valorCampo -= 1;
        } else {
            valorCampo = 0;
        }
        campoContador.setText(Integer.toString(valorCampo));
        contador.setValor(valorCampo);
        salvar();
    }

    protected void zerarContador() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção");
        builder.setMessage("Deseja zerar o contador?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                final TextView campoContador = (TextView) findViewById(R.id.txtValor);
                campoContador.setText(Integer.toString(0));
                contador.setValor(0);
                salvar();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        alerta = builder.create();
        alerta.show();
    }

    private void salvar() {
        try {
            if (contador.getId() == 0) {
                repositorioContador.inserir(contador);
            } else {
                repositorioContador.atualizar(contador);
            }
        } catch (Exception ex) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Erro ao inserir os dados: " + ex.getMessage());
            dialog.setNeutralButton("Ok", null);
            dialog.show();
        }
    }


    private void preencheDados()
    {
        final TextView campoContador = (TextView) findViewById(R.id.txtValor);
        campoContador.setText(contador.getValor());
    }


}
