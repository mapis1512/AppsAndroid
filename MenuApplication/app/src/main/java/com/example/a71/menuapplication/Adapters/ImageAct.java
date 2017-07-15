package com.example.a71.menuapplication.Adapters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a71.menuapplication.R;

public class ImageAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Bundle extras=getIntent().getExtras();
        ImageView imagen=(ImageView)findViewById(R.id.imageView);
        if(extras!= null){
            int id=extras.getInt("id");
            Log.e("id 2:"," "+id);
            imagen.setImageResource(id);
        }
    }
}
