package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CustomerOrderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);

        // Receive customer_id and store_name

        /*ArrayList<ArrayList<String>> orders = getInformation.getInstance().getOrders(user_ID, getInformation.customerIDKey);

        // Read users cart and add buttons to the scroll layout
        RecyclerView recyclerView = findViewById(R.id.rvOrders);

        CheckoutRecyclerViewAdapter adapter = new CheckoutRecyclerViewAdapter(this, store_name, orders);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);*/
    }
}