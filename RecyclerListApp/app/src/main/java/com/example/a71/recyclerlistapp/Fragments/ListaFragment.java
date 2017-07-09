package com.example.a71.recyclerlistapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a71.recyclerlistapp.Adapters.CustomRecyclerAdapter;
import com.example.a71.recyclerlistapp.R;

/**
 * Created by 71 on 7/9/2017.
 */

public class ListaFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_fragment, container, false);
        RecyclerView recyclerview = (RecyclerView) view.findViewById(R.id.recycler);
        String[] dataset={"Lollipop","Honeycumb","Kitkat","Icecream","masshmallow"};
        CustomRecyclerAdapter customRecyclerAdapter=new CustomRecyclerAdapter(dataset);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(customRecyclerAdapter);
        return view;
    }
}
