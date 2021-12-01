package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

public class CheckoutActivity extends AppCompatActivity implements Serializable {

    Order cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent intent = getIntent();

        cart = (Order)intent.getSerializableExtra("ORDER");

        // Read users cart and add buttons to the scroll layout
        RecyclerView recyclerView = findViewById(R.id.rvItems);

        // onClick implementations in Adapter
        CheckoutRecyclerViewAdapter adapter = new CheckoutRecyclerViewAdapter(this, cart.products_and_quantity);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void checkoutButton(View view) {
        cart.checkoutOrder();;
    }
}