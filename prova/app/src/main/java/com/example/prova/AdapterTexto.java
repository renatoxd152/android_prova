package com.example.prova;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class AdapterTexto extends ArrayAdapter<Texto>
{
    private Context context;

    private ArrayList<Texto> itens;

    public AdapterTexto(Context context, ArrayList<Texto> textos) {
        super(context, R.layout.item_lista, textos);
        this.context = context;
        this.itens = textos;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater li = LayoutInflater.from(parent.getContext());

        View itemView = li.inflate(R.layout.item_lista, parent, false);

        TextView lblName = itemView.findViewById(R.id.textoItem);

        lblName.setText(itens.get(position).getTexto());

        int cor = itens.get(position).getCor();
        int corValor = 0;
        switch (cor)
        {
            case 1:
                corValor = R.color.cor1;
                break;
            case 2:
                corValor = R.color.cor2;
                break;
            case 3:
                corValor = R.color.cor3;
                break;

        }


        lblName.setTextColor(ContextCompat.getColor(getContext(), corValor));

        return itemView;
    }

}
