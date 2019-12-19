package com.example.logistraffic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.logistraffic.R;
import com.example.logistraffic.entities.Loja;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Loja> {
    public CustomArrayAdapter(Context context, ArrayList<Loja> users){
        super(context,0,users);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent){
        Loja l = getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.layout_linha,parent,false);
        }

        ((TextView) convertView.findViewById(R.id.nome)).setText(l.getNome());
        ((TextView) convertView.findViewById(R.id.distancia)).setText(l.getDistancia());
        ((TextView) convertView.findViewById(R.id.rua)).setText(l.getRua());
        ((TextView) convertView.findViewById(R.id.lugar)).setText(l.getLugares());

        return convertView;
    }
}
