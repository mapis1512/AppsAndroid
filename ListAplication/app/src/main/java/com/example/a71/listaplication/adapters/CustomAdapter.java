package com.example.a71.listaplication.adapters;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a71.listaplication.models.Persona;

import java.util.ArrayList;

/**
 * Created by 71 on 7/1/2017.
 */

public class CustomAdapter extends ArrayAdapter {

    private ArrayList<Persona> personas;

    public CustomAdapter(@NonNull Context context, @NonNull ArrayList<Persona> objects) {
        super(context, android.R.layout.simple_expandable_list_item_2,objects);
        personas=objects;

    }

    @Override
    public int getCount() {
        return personas.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(getContext());
        if(convertView==null){
            convertView= inflater.inflate(android.R.layout.simple_expandable_list_item_2,parent,false);
        }

        TextView textView=(TextView) convertView.findViewById(android.R.id.text1);
        TextView textView2=(TextView) convertView.findViewById(android.R.id.text2);
        Persona persona=personas.get(position);
        textView.setText(persona.nombre+" "+persona.apellido);
        textView2.setText("Edad> "+persona.edad);

        return convertView;
    }
}
