package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

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
        // if cart is not empty
        if (Order.getCart().products_and_quantity.size() != 0) {
            Order.getCart().checkoutOrder();

            CharSequence msg = "Checked out!";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

            Log.d("Checkout: ", "Checked Out");
            // return to store page
            finish();
        } else {
            CharSequence msg = "No items in cart.";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}