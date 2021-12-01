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
        Intent intent = getIntent();
        String owner_id = intent.getStringExtra("USER_ID");
        Intent send_intent = new Intent(this, OwnerOrdersActivity.class);
        send_intent.putExtra("USER_ID", owner_id);
        startActivity(send_intent);
    }

    public void openCreateNewItem(View view) {
        Intent intent = getIntent();
        String owner_id = intent.getStringExtra("USER_ID");
        Intent send_intent = new Intent(this, CreateNewItem.class);
        send_intent.putExtra("USER_ID", owner_id);
        startActivity(send_intent);
    }
}