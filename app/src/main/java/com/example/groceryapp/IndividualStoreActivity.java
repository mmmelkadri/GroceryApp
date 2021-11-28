package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IndividualStoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_store);
    }


    public void openCheckout(View view) {
        /* TODO: Use get_intent to retrieve username and send this information to
            CheckoutActivity so user's cart can be loaded.
        */
        Intent get_intent = getIntent();
        Intent send_intent = new Intent(this, CheckoutActivity.class);
        startActivity(send_intent);
    }

    public void loadProducts(View view) {
        /*
        TODO: Dynamically change the LinearLayout in ScrollView to show store's products.
         Retrieve products from database. The Intent get_intent should give the store's name.
        */
        Intent get_intent = getIntent();
    }

    public void openProduct(View view) {
        /*
        TODO: Use database to retrieve information on product and send this information via Intent
         when opening ProductActivity. Customer's username should also be sent in order for
         ProductActivity to successfully add product to user's cart. The Intent get_intent should
         give the store's name and customer's username.
        */
        Intent get_intent = getIntent();
        Intent send_intent = new Intent(this, ProductActivity.class);
        startActivity(send_intent);
    }
}