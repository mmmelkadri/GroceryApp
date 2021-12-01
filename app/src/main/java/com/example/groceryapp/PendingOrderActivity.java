package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class PendingOrderActivity extends AppCompatActivity {
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_order);
        //TODO: Change to adapt Products
        Intent get_intent = getIntent();
        String order_id = get_intent.getStringExtra("ORDER_ID");
        ArrayList<Object> productInformation = new ArrayList<Object>();
        DataSnapshot products = Reader.getInstance().readSnapshot("order", order_id).child("product");

        if (products != null) {
            for (DataSnapshot product : products.getChildren()) {
                ArrayList<Object> temp = new ArrayList<Object>();
                // Each product must have a productIDKey and quantity
                temp.add(product.child("product_name").getValue());
                temp.add(product.child("quantity").getValue());
                productInformation.add(temp);
            }
        }

        PendingOrderProductAdapter adapter = new PendingOrderProductAdapter(this, productInformation);
        ListView listView = (ListView) findViewById(R.id.orderItemsListView);
        listView.setAdapter(adapter);
    }

    public void onClickCheck(View view) {
        /* TODO: Mark order as complete in database. The intent get_intent should give the order ID.
        *//*
        Intent get_intent = getIntent();
        String order_id = get_intent.getStringExtra("ORDER_ID");

        ArrayList<Object> list = getInformation.getInstance().getOrderInformation(order_id);
        Order order = new Order((String) list.get(0), (String) list.get(1));
        order.change_order_status(order_id);
    }*/
}