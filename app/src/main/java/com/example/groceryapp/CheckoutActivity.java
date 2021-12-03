package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Read users cart and add buttons to the scroll layout
        RecyclerView recyclerView = findViewById(R.id.rvItems);

        // onClick implementations in Adapter
        CheckoutRecyclerViewAdapter adapter = new CheckoutRecyclerViewAdapter(this, Order.getCart().products_and_quantity);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void checkout(View view) {
        Order.getCart().checkoutOrder();

        CharSequence error = "Checked out!";
        Toast toast = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT);

        Log.d("Checkout: ", "Checked Out");
        // return to store page
        finish();
    }
}