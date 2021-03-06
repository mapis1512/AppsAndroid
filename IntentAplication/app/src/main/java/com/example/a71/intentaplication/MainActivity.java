package com.example.a71.intentaplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a71.intentaplication.Models.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ArrayList<Person> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView listPerson=(ListView)findViewById(R.id.listView);
        dataset = Person.getDataSet();
        ArrayList<String> datasetString=new ArrayList<String>();
        for(int i = 0; i< dataset.size(); i++){
            datasetString.add(dataset.get(i).getNombre());
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datasetString);
        listPerson.setAdapter(adapter);
        listPerson.setOnItemClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        restoreData();
        

    }

    private void restoreData() {
        Context context=null;
        try{
            context=createPackageContext("com.example.a71.cameraapplication",0);
            SharedPreferences pref=context.getSharedPreferences("shared",context.CONTEXT_IGNORE_SECURITY);
            String name=pref.getString("name","none");
            Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
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
            Intent intent=new Intent();
            intent.setAction("android.example.action.MYACTIVITY");
            intent.addCategory("android.intent.category.DEFAULT");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent newActivity=new Intent(MainActivity.this,PersonDetailActivity.class);
        newActivity.putExtra("person",dataset.get(position));
        startActivityForResult(newActivity,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==PersonDetailActivity.WEB){
            Bundle extraDate=data.getExtras();
            String web=extraDate.getString("web");
            Uri call = Uri.parse("http://" + web);
            Intent newIntent = new Intent(Intent.ACTION_VIEW, call);
            startActivity(newIntent);
        }
    }
}
