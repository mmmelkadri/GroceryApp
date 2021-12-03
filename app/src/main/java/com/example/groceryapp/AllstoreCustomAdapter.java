package com.example.groceryapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AllstoreCustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Object> store_list;
    LayoutInflater inflater;

    public AllstoreCustomAdapter(Context ctx, ArrayList<Object> store_list){
        this.context = ctx;
        this.store_list = store_list;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return store_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("Before:", "inflater");
        convertView = inflater.inflate(R.layout.activity_all_store_single_row, null);
        TextView textView = (TextView) convertView.findViewById(R.id.Allstore_store_textView);
        Log.d("Before:", "setText");
        textView.setText((String) store_list.get(position));
        return convertView;
    }
}
