package com.example.a71.datescontentprovider;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.a71.datescontentprovider.Adapter.MyCursorRecyclerAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>,SearchView.OnQueryTextListener {
    private Cursor cursor;
    public static final String AUTHORITY = "com.example.a68.httpapplication.MyContentProvide";
    public static final String TABLE_NAME = "Place";
    public static final Uri GET_ALL_URI=Uri.parse("content://"+ AUTHORITY+"/"+TABLE_NAME);
    private MyCursorRecyclerAdapter adapter;


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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        adapter = new MyCursorRecyclerAdapter();
        recyclerView.setAdapter(adapter);
        grantUriPermission("com.example.read",GET_ALL_URI, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        getSupportLoaderManager().initLoader(0,null,this);
        /*cursor = this.getContentResolver().query(GET_ALL_URI, null, null, null, null);
        if (cursor != null ){
            cursor.moveToFirst();
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
            adapter = new MyCursorRecyclerAdapter(cursor);
            recyclerView.setAdapter(adapter);

            Toast.makeText(this,cursor.getString(cursor.getColumnIndex("nombre")),Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);


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

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Uri baseUri=GET_ALL_URI;
        String selection=null;
        String[]selectionargs=null;
        if(args!=null){
            selection=args.getString("selection");
            selectionargs=args.getStringArray("selectionArgs");
        }
        return new CursorLoader(this,baseUri,null,selection,selectionargs,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(adapter!=null){
            adapter.updateList(data);
        }
    }


    @Override
    public void onLoaderReset(Loader loader) {
        adapter.updateList(null);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(!newText.isEmpty()){
            Bundle bundle =new Bundle();
            String selection="ciudad like ?";
            String[]selectionbargs=new String[]{"%"+newText+"%"};
            bundle.putString("selection",selection);
            bundle.putStringArray("selectionArgs",selectionbargs);
            getSupportLoaderManager().restartLoader(0,bundle,this);
        }else{
            getSupportLoaderManager().restartLoader(0,null,this);
        }

        return false;
    }
}
