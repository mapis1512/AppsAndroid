package com.example.a68.httpapplication.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a68.httpapplication.MainActivity;
import com.example.a68.httpapplication.R;
import com.example.a68.httpapplication.adapters.MyCursorRecyclerViewAdapter;
import com.example.a68.httpapplication.adapters.MyPlaceRecyclerViewAdapter;
import com.example.a68.httpapplication.databases.MyDataBase;
import com.example.a68.httpapplication.models.Place;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static com.example.a68.httpapplication.databases.MyDataBase.COLUMN_CELULAR;
import static com.example.a68.httpapplication.databases.MyDataBase.COLUMN_CIUDAD;
import static com.example.a68.httpapplication.databases.MyDataBase.COLUMN_DIRECCION;
import static com.example.a68.httpapplication.databases.MyDataBase.COLUMN_HORARIOS;
import static com.example.a68.httpapplication.databases.MyDataBase.COLUMN_ID_MODEL;
import static com.example.a68.httpapplication.databases.MyDataBase.COLUMN_LAT;
import static com.example.a68.httpapplication.databases.MyDataBase.COLUMN_LON;
import static com.example.a68.httpapplication.databases.MyDataBase.COLUMN_NAME;
import static com.example.a68.httpapplication.databases.MyDataBase.COLUMN_PAIS;
import static com.example.a68.httpapplication.utilities.Uris.BULK_INSERT_URI;
import static com.example.a68.httpapplication.utilities.Uris.GET_ALL_URI;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PlaceFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private ArrayList<Place> places;
    private MyCursorRecyclerViewAdapter adapter;
    private MyDataBase dataBase;
    private Cursor cursor;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlaceFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PlaceFragment newInstance(int columnCount) {
        PlaceFragment fragment = new PlaceFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBase = new MyDataBase(getActivity());
        places = new ArrayList<>();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        //cursor = dataBase.getAll();
        cursor = getActivity().getContentResolver().query(GET_ALL_URI, null, null, null, null);
        if (cursor == null || !cursor.moveToFirst())
            ((MainActivity) getActivity()).request();
        //else
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MyCursorRecyclerViewAdapter(cursor, mListener);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void updateList(JSONArray response) {
        Gson gson = new Gson();

        Date currentLocalTime = new Date();
        places = gson.fromJson(response.toString(),
                new TypeToken<ArrayList<Place>>() {
                }.getType());

        /*try {*/
            //dataBase.addAll(response);
            getActivity().
                    getContentResolver().bulkInsert(BULK_INSERT_URI, getValuesFromJArray(places));
        /*} catch (JSONException e) {
            e.printStackTrace();
        }*/
        cursor = getActivity().getContentResolver().query(GET_ALL_URI, null, null, null, null);
        //cursor = dataBase.getAll();
        long millis = new Date().getTime() - currentLocalTime.getTime();
        Log.e("CURRENT TIME", "duration secs: "
                + ((double) millis / 1000.0) + ", millis: " + millis);
        adapter.notifyDataSetChanged();
        adapter.updateList(cursor);
    }


    public ContentValues[] getValuesFromJArray(ArrayList<Place> places) {
        ArrayList<ContentValues> contentValues = new ArrayList<>();
        for (int i = 0; i < places.size(); i++) {
            Place place = places.get(i);

            ContentValues values = new ContentValues();

                values.put(COLUMN_ID_MODEL, place.id);
                values.put(COLUMN_LAT, place.lat);
                values.put(COLUMN_LON, place.lon);
                values.put(COLUMN_CIUDAD, place.ciudad);
                values.put(COLUMN_CELULAR, place.celular);
                values.put(COLUMN_PAIS, place.pais);
                values.put(COLUMN_DIRECCION, place.direccion);
                values.put(COLUMN_HORARIOS, place.horarios);
                values.put(COLUMN_NAME, place.nombre);


            contentValues.add(values);

            //db.insert(TABLE_NAME, null, cv);
        }
        return contentValues.toArray(new ContentValues[contentValues.size()]);
    }

    public ContentValues[] getValuesFromJArray(JSONArray jsonArray) throws JSONException {
        ArrayList<ContentValues> contentValues = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject place = jsonArray.getJSONObject(i);

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

            contentValues.add(values);

            //db.insert(TABLE_NAME, null, cv);
        }
        return contentValues.toArray(new ContentValues[contentValues.size()]);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Place item);
    }
}
