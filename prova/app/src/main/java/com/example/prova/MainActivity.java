package com.example.prova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaTextos = findViewById(R.id.listaTextos);
        adapterTexto = new AdapterTexto(this,lista);
        listaTextos.setAdapter(adapterTexto);

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

            lista.add(texto);
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
            lista.remove(i);

            adapterTexto.notifyDataSetChanged();

            Toast.makeText(MainActivity.this, "Item apagado!", Toast.LENGTH_LONG).show();
            return true;
        }
    }

}