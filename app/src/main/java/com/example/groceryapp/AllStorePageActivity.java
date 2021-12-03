package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AllStorePageActivity extends AppCompatActivity {
    ArrayList<Object> owner_list = getInformation.getInstance().getAllUsers(getInformation.ownerKey);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Object> store_name_list = new ArrayList<Object>();
        for(Object x : owner_list){
            String store_name = getInformation.getInstance().getStoreName((String) x);
            store_name_list.add(store_name);
        }

        String customer_id = getIntent().getStringExtra("cust_Id");
        setContentView(R.layout.activity_all_store_page);
        ListView listView = (ListView) findViewById(R.id.Allstore_StoreList_ListView);

        AllstoreCustomAdapter AllstoreCustomAdapter = new
                AllstoreCustomAdapter(getApplicationContext(), store_name_list);
        listView.setAdapter(AllstoreCustomAdapter);
        Intent intent = new Intent(this, IndividualStoreActivity.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("owner_Id", (String)owner_list.get(position));
                intent.putExtra("cust_Id", (String) customer_id);
                startActivity(intent);
            }
        });
    }

    public void AllStore_past_orders_button (View view){
        String customer_id = getIntent().getStringExtra("cust_Id");
        Intent intent = new Intent(this, CustomerOrderActivity.class);
        intent.putExtra("cust_Id", customer_id);
        startActivity(intent);
    }
}