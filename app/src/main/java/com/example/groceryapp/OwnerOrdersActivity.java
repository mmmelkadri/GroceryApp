package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        // TODO: CHANGE FOR GET INTENT TO GET USERid
        Intent get_intent = getIntent();
        String USER_ID = get_intent.getStringExtra("USER_ID");

        listView=(ListView)findViewById(R.id.OwnerOderListView);
        ArrayList<String[]> arrayList = new ArrayList<>();

        for(int i = 0; i<10; i++){

            String[] arr;
            if(i%3==0){
                arr = new String[]{"" + i, "Completed"};
            }
            else{
                arr = new String[]{"" + i, "Incomplete"};
            }
            arrayList.add(arr);
        }

        OwnerOrderBaaseAdaptor arrayAdapter = new OwnerOrderBaaseAdaptor(this,arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                    String order_id = arrayList.get(i)[0];
                    Intent intent_next = new Intent(OwnerOrdersActivity.this, PendingOrderActivity.class);
                    intent_next.putExtra("order id", order_id);
                    startActivity(intent_next);
                }
        });
    }
}