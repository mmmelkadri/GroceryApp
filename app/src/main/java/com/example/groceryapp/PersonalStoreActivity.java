package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PersonalStoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_store);
    }

    public void openPendingOrders(View view) {
        /*
        TODO: Use get_intent to retrieve store's name and send this information to OwnerOrders so
         owner's/store's pending orders can be loaded.
        */
        Intent get_intent = getIntent();
        Intent send_intent = new Intent(this, OwnerOrdersActivity.class);
        startActivity(send_intent);
    }

    public void openCreateNewItem(View view) {
        /*
        TODO: Use get_intent to retrieve store's name and send this information to CreateNewItem.
         This way, CreateNewItem will know where to save the newly-created item.
        */
        Intent get_intent = getIntent();
        Intent send_intent = new Intent(this, CreateNewItem.class);
        startActivity(send_intent);
    }
}