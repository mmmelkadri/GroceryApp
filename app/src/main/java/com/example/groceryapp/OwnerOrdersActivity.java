package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OwnerOrdersActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_orders);

        listView=(ListView)findViewById(R.id.OwnerOderListView);

        ArrayList<String[]> arrayList = new ArrayList<>();
        for(int i = 0; i<10; i++){
            String[] arr = {"order "+i+"     ", String.valueOf(i)};
            arrayList.add(arr);
        }

        OwnerOrderBaaseAdaptor arrayAdapter = new OwnerOrderBaaseAdaptor(this,arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                    Toast.makeText(OwnerOrdersActivity.this, "clicked item " + i +arrayList.get(i).toString(), Toast.LENGTH_SHORT).show();
                }
        });
    }
}