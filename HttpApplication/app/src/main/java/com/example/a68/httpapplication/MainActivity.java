package com.example.a68.httpapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.a68.httpapplication.fragments.PlaceFragment;
import com.example.a68.httpapplication.models.Place;
import com.example.a68.httpapplication.utilities.MyPreferences;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity implements PlaceFragment.OnListFragmentInteractionListener {

    private MyPreferences preferences;
    private PlaceFragment placeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = new MyPreferences(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new Logout());

        placeFragment = PlaceFragment.newInstance(1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, placeFragment);
        fragmentTransaction.commit();
    }


    class Logout implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            preferences.setUsername("");
            startActivity(new Intent(MainActivity.this, LoginActivity.class));

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void request() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Consultando datos...");
        progressDialog.show();

        if (isInternet()) {
            String url = "https://vesta.sersoluciones.com:9050/points/get_distance/?lat=9999&lon=999";
            RequestQueue queue = Volley.newRequestQueue(this);
            JsonArrayRequest jsObjRequest = new JsonArrayRequest
                    (Request.Method.GET, url, null,
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    progressDialog.dismiss();
//                                Toast.makeText(MainActivity.this,
//                                        response.toString(), Toast.LENGTH_LONG).show();
                                    placeFragment.updateList(response);
                                }
                            }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO Auto-generated method stub
                            progressDialog.dismiss();
                        }
                    });

            // Add the request to the RequestQueue.
            queue.add(jsObjRequest);
        }else{
            Toast.makeText(this, "Sin conexion a internet", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public void onListFragmentInteraction(Place item) {

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("place", item);
        startActivity(intent);
    }
}
