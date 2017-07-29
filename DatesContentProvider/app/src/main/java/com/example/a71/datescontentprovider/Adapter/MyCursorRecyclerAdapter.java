package com.example.a71.datescontentprovider.Adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a71.datescontentprovider.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 71 on 7/29/2017.
 */

public class MyCursorRecyclerAdapter extends RecyclerView.Adapter<MyCursorRecyclerAdapter.ViewHolder> {

    private Cursor mValues;
    private int itemID;

    public MyCursorRecyclerAdapter() {
        //this.itemID=id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_place, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        mValues.moveToPosition(position);
        holder.mIdView.setText(mValues.getString(mValues.getColumnIndex("nombre")));
        holder.mContentView.setText(mValues.getString(mValues.getColumnIndex("ciudad")));
       /* try {
            holder.mItem = getObj(mValues);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public int getItemCount() {
        if (mValues!=null)
            return mValues.getCount();
        return 0;
    }

    public void updateList(Cursor nuevoCursor) {
        if (nuevoCursor != null) {
            mValues = nuevoCursor;
            notifyDataSetChanged();
        }
    }

    /*private Place getObj(Cursor cursor) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        for (String columnName : cursor.getColumnNames()) {
            int typeColumn = cursor.getType(cursor.getColumnIndex(columnName));
            if (typeColumn == Cursor.FIELD_TYPE_STRING) {
                String entityStr = cursor.getString(cursor.getColumnIndex(columnName));
                jsonObject.put(columnName, entityStr);
            } else if (typeColumn == Cursor.FIELD_TYPE_INTEGER) {
                int entityInt = cursor.getInt(cursor.getColumnIndex(columnName));
                jsonObject.put(columnName, entityInt);
            } else if (typeColumn == Cursor.FIELD_TYPE_FLOAT) {
                double entityDouble = cursor.getDouble(cursor.getColumnIndex(columnName));
                jsonObject.put(columnName, entityDouble);
            }

        }
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), new TypeToken<Place>() {
        }.getType());
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
       /* public Place mItem;
        public CardView cardview;*/

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            //cardview = (CardView) view.findViewById(R.id.cardview);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}

