package com.example.prova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private EditText editText;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText = findViewById(R.id.texto);
        radioGroup = findViewById(R.id.grupoInput);
    }

    public void primeiraTela(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void inserirDados(View view)
    {
        String texto = editText.getText().toString();
        int cor = radioGroup.getCheckedRadioButtonId();

        radioGroup = findViewById(R.id.grupoInput);
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        if(texto.isEmpty())
        {
            Toast.makeText(this, "Digite algo no campo de texto", Toast.LENGTH_SHORT).show();
        }
        else if(cor == -1)
        {
            Toast.makeText(this, "Seleciona uma cor", Toast.LENGTH_SHORT).show();
        }
        else
        {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            int numericValue = Integer.parseInt(selectedRadioButton.getTag().toString());

            Intent i = new Intent(MainActivity2.this, MainActivity.class);

            i.putExtra("texto", texto);
            i.putExtra("cor", numericValue);

            setResult(RESULT_OK, i);

            finish();

            editText.getText().clear();
            radioGroup.clearCheck();

        }


    }
}