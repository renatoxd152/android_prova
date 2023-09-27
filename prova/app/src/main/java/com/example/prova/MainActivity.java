package com.example.prova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listaTextos;
    private ArrayList<Texto> lista = new ArrayList<>();
    private AdapterTexto adapterTexto;

    SQLiteDatabase bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = openOrCreateDatabase("banco_prova",MODE_PRIVATE,null);

        String cmd = "CREATE TABLE IF NOT EXISTS textos (id INTEGER PRIMARY KEY AUTOINCREMENT, NOME VARCHAR(100) NOT NULL, COR INT NOT NULL)";

        bd.execSQL(cmd);

        listaTextos = findViewById(R.id.listaTextos);
        adapterTexto = new AdapterTexto(this,lista);
        listaTextos.setAdapter(adapterTexto);

        carregarDadosDoBanco();

        EscutadorLista el = new EscutadorLista();
        listaTextos.setOnItemClickListener( el );
        listaTextos.setOnItemLongClickListener( el );
    }

    public void segundaTela(View v)
    {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( resultCode == RESULT_OK ) {
            String texto1 = data.getStringExtra("texto");
            int corValor = data.getIntExtra("cor", 0);
            Texto texto = new Texto(texto1,corValor);
            inserirTextoNoBanco(texto);
            lista.add(texto);
            adapterTexto.notifyDataSetChanged();


        }

    }


    private void carregarDadosDoBanco() {
        String query = "SELECT * FROM textos";
        Cursor cursor = bd.rawQuery(query, null);

        int idColumnnIndex = cursor.getColumnIndex("id");
        int nomeColumnIndex = cursor.getColumnIndex("NOME");
        int corColumnIndex = cursor.getColumnIndex("COR");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(idColumnnIndex);
                String nome = cursor.getString(nomeColumnIndex);
                int cor = cursor.getInt(corColumnIndex);

                Texto texto = new Texto(id,nome,cor);


                lista.add(texto);
            } while (cursor.moveToNext());

            cursor.close();

            adapterTexto.notifyDataSetChanged();
        }
    }

    private class EscutadorLista implements AdapterView.OnItemClickListener,
            AdapterView.OnItemLongClickListener {
        String cor;

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if(lista.get(i).getCor() == 1)
            {
                cor = "Vermelho";
            }
            else if(lista.get(i).getCor() == 2)
            {
                cor = "Verde";
            }
            else
            {
                cor = "Azul";
            }
            String textoCompleto = lista.get(i).getTexto() + "\nCor: " + cor;
            Toast.makeText(MainActivity.this,textoCompleto , Toast.LENGTH_LONG).show();

        }

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            int id = lista.get(i).getId();

            removerTextodoBanco(id);

            lista.remove(i);

            adapterTexto.notifyDataSetChanged();

            Toast.makeText(MainActivity.this, "Item Apagado!", Toast.LENGTH_LONG).show();
            return true;
        }

    }


    public void inserirTextoNoBanco(Texto texto) {

        bd = openOrCreateDatabase("banco_prova", MODE_PRIVATE, null);

        String cmd = "INSERT INTO textos (NOME, COR) VALUES (?, ?)";
        bd.execSQL(cmd, new Object[]{texto.getTexto(), texto.getCor()});

        bd.close();
    }


    public void removerTextodoBanco(int id)
    {
        bd = openOrCreateDatabase("banco_prova", MODE_PRIVATE, null);

        String cmd = "DELETE FROM textos WHERE id = ?";
        bd.execSQL(cmd, new Object[]{id});

        bd.close();
    }





}