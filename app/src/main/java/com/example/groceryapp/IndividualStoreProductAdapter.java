package com.example.groceryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class IndividualStoreProductAdapter extends ArrayAdapter<Object> {
    public IndividualStoreProductAdapter(Context context, ArrayList<Object> list) {
        super(context, 0, list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ArrayList<String> product_info = (ArrayList<String>) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.products_list_view, parent, false);
        }
        TextView product = (TextView) convertView.findViewById(R.id.product);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        product.setText((String) product_info.get(0));
        price.setText((String) product_info.get(2));
        return convertView;
    }
}
