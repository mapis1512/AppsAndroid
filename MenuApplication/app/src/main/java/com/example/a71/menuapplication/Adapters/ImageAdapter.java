package com.example.a71.menuapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.a71.menuapplication.R;

import java.util.ArrayList;

/**
 * Created by 71 on 7/15/2017.
 */

 public class ImageAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Integer> imagenes;

    public ImageAdapter(@NonNull Context context) {
        this.context=context;
        imagenes = new ArrayList<>();
        imagenes.add(R.drawable.a);
        imagenes.add(R.drawable.b);
        imagenes.add(R.drawable.c);
        imagenes.add(R.drawable.e);
        imagenes.add(R.drawable.f);
        imagenes.add(R.drawable.g);
        imagenes.add(R.drawable.h);
        imagenes.add(R.drawable.i);
        imagenes.add(R.drawable.j);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ImageView imageView=new ImageView(context);
        final int pos=position;
        imageView.setImageResource(imagenes.get(position));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,400));
       /* imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity=new Intent(context,ImageAct.class);
                newActivity.putExtra("id",imagenes.get(pos));
                context.startActivity(newActivity);
            }
        });*/
        return imageView;
    }

    @Override
    public int getCount() {
        return imagenes.size();
    }

    @Override
    public Object getItem(int position) {
        return imagenes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
