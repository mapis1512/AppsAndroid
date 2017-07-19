package com.example.a71.httpapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a71.httpapplication.modules.place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Research_Development on 19/07/2017.
 */
public class Information extends AppCompatActivity implements OnMapReadyCallback {
    private place places;
    private GoogleMap mMap;
    private UiSettings mUiSettings;
    double latitud;
    double longitud;
    FragmentManager fragmentManager;
    SupportMapFragment supportMapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
        Bundle extras=getIntent().getExtras();
        if(extras!= null){
            places = (place) extras.getSerializable("place");
            ((TextView)findViewById(R.id.name)).setText("Nombre: "+places.getName());
            ((TextView)findViewById(R.id.id)).setText("Id: "+places.getId());
            ((TextView)findViewById(R.id.city)).setText("Ciudad: "+places.getCity());
            ((TextView)findViewById(R.id.cel)).setText("Celular: "+places.getCelphone());
            ((TextView)findViewById(R.id.phone)).setText("Telefono: "+places.getPhone());
            ((TextView)findViewById(R.id.country)).setText("Pais: "+places.getCountry());
            ((TextView)findViewById(R.id.horarios)).setText("Horario: "+places.getScheduler());
            ((TextView)findViewById(R.id.address)).setText("Direccion: "+places.getAddress());
            latitud=places.getLat();
            longitud=places.getLon();
        }
        fragmentManager = this.getSupportFragmentManager();
        supportMapFragment = (SupportMapFragment) fragmentManager
                .findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("entro a:", "onMapReady");
        mMap = googleMap;
        float zoom= (float) 15.0;
        mUiSettings=mMap.getUiSettings();
        // Add a marker in Sydney and move the camera
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
        mUiSettings.setMapToolbarEnabled(true);
        LatLng ubicacion = new LatLng(latitud,longitud);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,zoom));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
        final Marker marker1= mMap.addMarker(new MarkerOptions().position(ubicacion).title("mi ubicacion"));
        if(latitud!=0.0&&longitud!=0.0){

            mMap.addMarker(new MarkerOptions().position(ubicacion).title("mi ubicacion"));
        }


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            public void onInfoWindowClick(final Marker marker) {

            }
        });
    }
}
