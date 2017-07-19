package com.example.a71.httpapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.a71.httpapplication.Adapters.ListAdapter;
import com.example.a71.httpapplication.modules.place;
import com.example.a71.httpapplication.utilities.MyPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyPreferences preferences;
    ArrayList<place> places=new ArrayList<>();
    ListView listaPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listaPlaces=(ListView)findViewById(R.id.listView);
        preferences = new MyPreferences(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Seguro desea cerrar sesion", Snackbar.LENGTH_LONG)
                        .setAction("SI", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                preferences.setUsername("");
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            }
                        }).show();

            }
        });
        listaPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final int position=i;
               /* AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                builder.setTitle("Informacion Detallada");
                View dialoglayout = inflater.inflate(R.layout.information, null);
                ((TextView)dialoglayout.findViewById(R.id.name)).setText("Nombre: "+places.get(i).getName());
                ((TextView)dialoglayout.findViewById(R.id.id)).setText("Id: "+places.get(i).getId());
                ((TextView)dialoglayout.findViewById(R.id.city)).setText("Ciudad: "+places.get(i).getCity());
                ((TextView)dialoglayout.findViewById(R.id.cel)).setText("Celular: "+places.get(i).getCelphone());
                ((TextView)dialoglayout.findViewById(R.id.phone)).setText("Telefono: "+places.get(i).getPhone());
                ((TextView)dialoglayout.findViewById(R.id.country)).setText("Pais: "+places.get(i).getCountry());
                ((TextView)dialoglayout.findViewById(R.id.horarios)).setText("Horario: "+places.get(i).getScheduler());
                ((TextView)dialoglayout.findViewById(R.id.address)).setText("Direccion: "+places.get(i).getAddress());
                builder.setView(dialoglayout);
                builder.show();
                builder.create();*/
                Intent newActivity=new Intent(MainActivity.this,Information.class);
                newActivity.putExtra("place",places.get(i));
                startActivity(newActivity);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        request();
    }

    private void request() {
        String url = "https://vesta.sersoluciones.com:9050/points/get_distance/?lat=9999&lon=999";
        //JSONArray jObj;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for(int i=0;i<response.length();i++){
                                    // Get current json object
                                    JSONObject placeJSON = null;
                                    try {
                                        placeJSON = response.getJSONObject(i);
                                        place place1=new place(placeJSON.getBoolean("is_active"),placeJSON.getDouble("lon"),placeJSON.getDouble("lat"),placeJSON.getInt("id"),placeJSON.getString("ciudad"),placeJSON.getString("celular"),
                                                placeJSON.getString("telefono"),placeJSON.getString("pais"),placeJSON.getString("direccion"),placeJSON.getString("point"),placeJSON.getString("horarios"),placeJSON.getString("nombre"));
                                        places.add(place1);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                                ListAdapter listAdapter = new ListAdapter(MainActivity.this,places);
                                listaPlaces.setAdapter(listAdapter);
                                //Toast.makeText(MainActivity.this,response.toString(), Toast.LENGTH_LONG).show();
                                Log.e("json",response.toString());
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);
    }
}
