package com.example.a71.httpapplication.Adapters;

/**
 * Created by Research_Development on 13/07/2017.
 */

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.a71.httpapplication.R;
import com.example.a71.httpapplication.modules.place;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;



/**
 * Created by 71 on 7/9/2017.
 */

public class ListAdapter extends ArrayAdapter {

    private ArrayList<place> places;

    public ListAdapter(@NonNull Context context, @NonNull ArrayList<place> objects) {
        super(context, android.R.layout.simple_expandable_list_item_2,objects);
        places=objects;

    }

    @Override
    public int getCount() {
        return places.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(getContext());
        if(convertView==null){
            convertView= inflater.inflate(R.layout.list,parent,false);
        }

        TextView textView=(TextView) convertView.findViewById(R.id.name);
        TextView textView2=(TextView) convertView.findViewById(R.id.address);
        TextView textView3=(TextView) convertView.findViewById(R.id.country);
        place place=places.get(position);
        textView.setText("Nombre: "+place.getName());
        textView3.setText("Pais: "+place.getCountry());
        textView2.setText("Dirección: "+place.getAddress());

        return convertView;
    }
}