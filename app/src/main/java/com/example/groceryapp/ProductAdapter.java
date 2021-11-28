package com.example.groceryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
//TODO: Change to adapt Products
public class ProductAdapter extends ArrayAdapter<Object> {
    public ProductAdapter(Context context, ArrayList<Object> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ArrayList obj = (ArrayList) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pending_orders_list_view, parent, false);
        }
        TextView item = (TextView) convertView.findViewById(R.id.item);
        TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
        item.setText((Integer) obj.get(0));
        quantity.setText((Integer) obj.get(1));
        return convertView;
    }
}
