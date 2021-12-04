package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PersonalStoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_store);
        Intent intent = getIntent();
        String owner_id = intent.getStringExtra("owner_Id");
        String store_name = getInformation.getInstance().getStoreName(owner_id);
        TextView storeName = findViewById(R.id.companyNameTextView);
        storeName.setText(store_name);
    }

    public void openPendingOrders(View view) {
        Intent intent = getIntent();
        String owner_id = intent.getStringExtra("owner_Id");
        Intent send_intent = new Intent(this, OwnerOrdersActivity.class);
        send_intent.putExtra("owner_Id", owner_id);
        startActivity(send_intent);
    }

    public void openCreateNewItem(View view) {
        Intent intent = getIntent();
        String owner_id = intent.getStringExtra("owner_Id");
        Intent send_intent = new Intent(this, CreateNewItem.class);
        send_intent.putExtra("owner_Id", owner_id);
        startActivity(send_intent);
    }
}