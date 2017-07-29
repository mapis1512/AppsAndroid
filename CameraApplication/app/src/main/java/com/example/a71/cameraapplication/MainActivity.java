package com.example.a71.cameraapplication;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_PIC_REQUEST = 0;
    private static final int CAMERA_PERMISION = 11;

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA )
                != PackageManager.PERMISSION_GRANTED && Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP) {
            requestPermission();
        }

    }
    private void saveDate() {
        SharedPreferences date=getSharedPreferences("shared",CONTEXT_IGNORE_SECURITY);

        SharedPreferences.Editor editor= date.edit();
        editor.putString("name","data prueba");
        editor.apply();

        String dataSaved=date.getString("name","");
        Toast.makeText(this,dataSaved,Toast.LENGTH_LONG).show();
           }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode!=CAMERA_PERMISION){
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras=data.getExtras();
        Bitmap imageBitmap=(Bitmap) extras.get("data");
        ImageView mimage=(ImageView)findViewById(R.id.imageView);
        mimage.setImageBitmap(imageBitmap);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        saveDate();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCam();
            }
        });

    }

    private void openCam() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
private void requestPermission(){
    final  String[] permission=new String[]{
            Manifest.permission.CAMERA
    };
    ActivityCompat.requestPermissions(this,permission,CAMERA_PERMISION);
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
