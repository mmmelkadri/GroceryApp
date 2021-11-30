package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Read users cart and add buttons to the scroll layout
        RecyclerView recyclerView = findViewById(R.id.rvItems);

        // Change total to products_and_quantity from Owner
        // onClick implementations in Adapter
        /*CheckoutRecyclerViewAdapter adapter = new CheckoutRecyclerViewAdapter(this, total);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);*/
    }
}