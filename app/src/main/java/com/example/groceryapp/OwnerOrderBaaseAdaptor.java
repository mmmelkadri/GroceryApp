package com.example.groceryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OwnerOrderBaaseAdaptor extends BaseAdapter {
    Context context;
    ArrayList<String[]> array_list;
    LayoutInflater inflater;

    public OwnerOrderBaaseAdaptor(Context ctx, ArrayList<String[]> arr){
        this.context = ctx;
        this.array_list = arr;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return array_list.size();
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
        convertView = inflater.inflate(R.layout.activity_owner_order_button_lv, null);
        TextView textViewOrder = (TextView) convertView.findViewById(R.id.textViewOrder);
        TextView textViewState = (TextView) convertView.findViewById(R.id.textViewState);
        textViewOrder.setText(array_list.get(position)[0]);
        textViewState.setText(array_list.get(position)[1]);
        return convertView;
    }
}
