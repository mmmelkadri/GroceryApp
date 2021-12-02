package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        Intent get_intent = getIntent();
        String USER_ID = get_intent.getStringExtra("owner_Id");

        listView=(ListView)findViewById(R.id.OwnerOderListView);
        ArrayList<ArrayList<String>> arrayList = getInformation.getInstance().getOwnerOrders(USER_ID,getInformation.ownerKey);

        OwnerOrderBaaseAdaptor arrayAdapter = new OwnerOrderBaaseAdaptor(this,arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                    String order_id = arrayList.get(i).get(0);
                    Intent intent_next = new Intent(OwnerOrdersActivity.this, PendingOrderActivity.class);
                    intent_next.putExtra("ORDER_ID", order_id);
                    startActivity(intent_next);
                }
        });
    }
}