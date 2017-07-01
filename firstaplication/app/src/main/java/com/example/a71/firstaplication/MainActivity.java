package com.example.a71.firstaplication;

import android.media.MediaCodec;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private String h;
    private String w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void calcular(View view) {
        EditText height = (EditText) findViewById(R.id.editTextH);
        EditText weigth = (EditText) findViewById(R.id.editTextW);
       if(!validate(height, weigth)){
           float altura =Float.valueOf(h)/100;
           float peso =Float.valueOf(w);
           float imc =peso/(altura*altura);
           Log.d("IMC> ","su indice de masa corporal es> "+imc);
           String result="";
           if(imc<18){
               Log.d("IMC> ","peso bajo");
               result="peso bajo";
           }else if(imc<25){
               Log.d("IMC> ","normal");
               result="peso normal";
           }else if(imc<27){
               Log.d("IMC> ","obesidad");
               result="peso obesidad";
           }else if(imc<30){
               Log.d("IMC> ","obesidad 1");
               result="peso obesidad 1";
           }else if(imc<40){
               Log.d("IMC> ","obesidad 2");
               result="peso obesidad 2";
           }else{
               Log.d("IMC> ","obesidad 3");
               result="peso obesidad 3";
           }
           ((TextView) findViewById(R.id.imc)).setText(result);

       }
    }

    private boolean validate(EditText height, EditText weigth) {
        h = height.getText().toString();
        w = weigth.getText().toString();
        boolean flag=false;
        if(h.isEmpty()){
            height.requestFocus();
            height.setError("este campo es obligatorio");
            flag=true;
        }else if (!h.matches("\\d{2,3}")){
            height.requestFocus();
            height.setError("minimo 2 caracteres");
            flag=true;
        } else if (w.isEmpty()){
            weigth.requestFocus();
            weigth.setError("este campo es obligatorio");
            flag=true;
        }else if (!w.matches("\\d{2,3}")){
            weigth.requestFocus();
            weigth.setError("minimo 2 caracteres");
            flag=true;
        }
        return  flag;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
