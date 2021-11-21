package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OwnerOrders extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_orders);

        listView=(ListView)findViewById(R.id.listview);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("order 1");
        arrayList.add("order 2");
        arrayList.add("order 3");
        arrayList.add("order 4");
        arrayList.add("order 5");
        arrayList.add("order 6");
        arrayList.add("order 7");
        arrayList.add("order 8");
        arrayList.add("order 1");
        arrayList.add("order 2");
        arrayList.add("order 3");
        arrayList.add("order 4");
        arrayList.add("order 5");
        arrayList.add("order 6");
        arrayList.add("order 7");
        arrayList.add("order 8");
        arrayList.add("order 1");
        arrayList.add("order 2");
        arrayList.add("order 3");
        arrayList.add("order 4");
        arrayList.add("order 5");
        arrayList.add("order 6");
        arrayList.add("order 7");
        arrayList.add("order 8");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                    Toast.makeText(OwnerOrders.this, "clicked item " + i +arrayList.get(i).toString(), Toast.LENGTH_SHORT).show();
                }
        });
    }
}