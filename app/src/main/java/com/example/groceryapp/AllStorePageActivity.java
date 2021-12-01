package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AllStorePageActivity extends AppCompatActivity {
    ArrayList<Object> owner_list = getInformation.getInstance().getAllUsers(getInformation.ownerKey);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_store_page);
        ListView listView = (ListView) findViewById(R.id.Allstore_StoreList_ListView);
        AllstoreCustomAdapter AllstoreCustomAdapter = new
                AllstoreCustomAdapter(getApplicationContext(), owner_list);
        listView.setAdapter(AllstoreCustomAdapter);
        Intent intent = new Intent(this, IndividualStoreActivity.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("owner_Id", (String)owner_list.get(position));
                startActivity(intent);
            }
        });
    }
}