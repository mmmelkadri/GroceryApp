package com.example.groceryapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OwnerOrderBaaseAdaptor extends BaseAdapter {
    Context context;
    ArrayList<ArrayList<String>> array_list;
    LayoutInflater inflater;

    public OwnerOrderBaaseAdaptor(Context ctx, ArrayList<ArrayList<String>> arr){
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
        TextView textViewFiller = (TextView) convertView.findViewById(R.id.order_filler);
        textViewFiller.setTextSize(24);
        textViewOrder.setText(array_list.get(position).get(0));
        textViewOrder.setTextSize(24);

        String state_s = array_list.get(position).get(1);
        textViewState.setText(state_s);
        if(state_s.equals("Complete")){
            textViewState.setTextColor(context.getResources().getColor(R.color.App_green));
        }
        else {
            textViewState.setTextColor(Color.RED);
        }
        textViewState.setTextSize(20);


        return convertView;
    }
}
