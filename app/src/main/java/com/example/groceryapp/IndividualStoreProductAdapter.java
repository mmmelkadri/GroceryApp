package com.example.groceryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class IndividualStoreProductAdapter extends ArrayAdapter<Product> {
    public IndividualStoreProductAdapter(Context context, ArrayList<Product> list) {
        super(context, 0, list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product_info = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.products_list_view, parent, false);
        }
        TextView product = (TextView) convertView.findViewById(R.id.product);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        String productString = product_info.product_Id;
        String priceString = "$" + product_info.price;
        product.setText((String) productString);
        price.setText((String) priceString);
        return convertView;
    }
}
