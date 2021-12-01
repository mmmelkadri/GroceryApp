package com.example.groceryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
//TODO: Change to adapt Products
public class PendingOrderProductAdapter extends ArrayAdapter<ArrayList<String>> {
    public PendingOrderProductAdapter(Context context, ArrayList<ArrayList<String>> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ArrayList<String> obj = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pending_orders_list_view, parent, false);
        }
        TextView item = (TextView) convertView.findViewById(R.id.item);
        TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
        item.setText((String) obj.get(0));
        quantity.setText((String) obj.get(1));
        return convertView;
    }
}
