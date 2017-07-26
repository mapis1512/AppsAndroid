package com.example.a68.httpapplication.databases;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.a68.httpapplication.models.Place;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MyDataBase extends SQLiteOpenHelper {

    private static final String DB_NAME = "ExampleDB";

    public static final String TABLE_NAME = "Place";

    //columns
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ID_MODEL = "id";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LON = "lon";
    public static final String COLUMN_CIUDAD = "ciudad";
    public static final String COLUMN_CELULAR = "celular";
    public static final String COLUMN_PAIS = "pais";
    public static final String COLUMN_DIRECCION = "direccion";
    public static final String COLUMN_HORARIOS = "horarios";
    public static final String COLUMN_NAME = "nombre";

    private static final String[] cols = {COLUMN_ID, COLUMN_ID_MODEL, COLUMN_LAT,
            COLUMN_LON, COLUMN_CIUDAD, COLUMN_CELULAR, COLUMN_PAIS, COLUMN_DIRECCION,
            COLUMN_HORARIOS, COLUMN_NAME
    };
    private static final String TAG = MyDataBase.class.getSimpleName();

    private final SQLiteDatabase db;

    public MyDataBase(Context context) {
        super(context, DB_NAME, null, 1);
        db = getWritableDatabase();
    }

    public void add(JSONObject place) {
        //db.insert(TABLE_NAME, null, generateValues(place));
    }

    public int update(int id,JSONObject place){
        return db.update(TABLE_NAME,generateValues(place),COLUMN_ID+" ?",new String[]{String.valueOf(id)}
        );
    }

    public void bulkInsert(ContentValues[] values){
        db.beginTransaction();
        try{
            for(ContentValues value:values){
                db.insert(TABLE_NAME, null, value);
            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }

    }

    public ContentValues generateValues(JSONObject place){
        ContentValues values = new ContentValues();
        try {
            values.put(COLUMN_ID_MODEL, place.getInt("id"));
            values.put(COLUMN_LAT, place.getDouble("lat"));
            values.put(COLUMN_LON, place.getDouble("lon"));
            values.put(COLUMN_CIUDAD, place.getString("ciudad"));
            values.put(COLUMN_CELULAR, place.getInt("celular"));
            values.put(COLUMN_PAIS, place.getString("pais"));
            values.put(COLUMN_DIRECCION, place.getString("direccion"));
            values.put(COLUMN_HORARIOS, place.getString("horarios"));
            values.put(COLUMN_NAME, place.getString("nombre"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return values;
    }
    public void delete(int id){
        //db.delete(TABLE_NAME,COLUMN_ID+" =? AND "+COLUMN_NAME+" =?",new String []{String.valueOf(id),String.valueOf("Bogota")});
        db.delete(TABLE_NAME,COLUMN_ID+" =? ",new String []{String.valueOf(id)});
    }

    public void addAll(JSONArray places) throws JSONException {
        ArrayList<ContentValues> contenido=new ArrayList<>();
        for (int i=0;i<places.length();i++) {
            JSONObject json=places.getJSONObject(i);

            contenido.add(generateValues(json));
            //add(json);
        }
        bulkInsert(contenido.toArray(new ContentValues[contenido.size()]));
    }

    public Cursor getAll() {
        return db.query(TABLE_NAME, cols, null, null, null, null, COLUMN_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME
                + " ("
                + COLUMN_ID + " integer PRIMARY KEY not null, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_ID_MODEL + " integer, "
                + COLUMN_LAT + " REAL, "
                + COLUMN_LON + " REAL, "
                + COLUMN_CIUDAD + " TEXT, "
                + COLUMN_CELULAR + " integer, "
                + COLUMN_PAIS + " TEXT, "
                + COLUMN_DIRECCION + " TEXT, "
                + COLUMN_HORARIOS + " TEXT "
                + ")";
        db.execSQL(query);
        Log.d(TAG, "onCreate DB");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
