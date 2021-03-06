package com.example.groceryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PendingOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_order);
        Intent get_intent = getIntent();
        String order_id = get_intent.getStringExtra("ORDER_ID");

        TextView textView = (TextView) findViewById(R.id.textViewOrderID);
        textView.setText("Order No. " + order_id);

        ArrayList<ArrayList<ArrayList<String>>> allOrders = getInformation.getInstance().getAllOrders();

        ArrayList<ArrayList<String>> productInformation = new ArrayList<>();

        // iterate through allOrders to find the correct order
        for (ArrayList<ArrayList<String>> order : allOrders) {
            if (order.get(0).get(0).equals(order_id)) {
                productInformation = order;
                break;
            }
        }
        ArrayList<ArrayList<String>> productInfo = new ArrayList<>();
        for (int i = 1; i < productInformation.size(); i++) {
            productInfo.add(productInformation.get(i));
        }

        PendingOrderProductAdapter adapter = new PendingOrderProductAdapter(this, productInfo);
        ListView listView = (ListView) findViewById(R.id.orderItemsListView);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent get_intent = getIntent();
        String order_id = get_intent.getStringExtra("ORDER_ID");
        CheckBox cb = (CheckBox)findViewById(R.id.checkBoxOrderComplete);
        String status = getInformation.getInstance().getState(order_id);
        if (status.equals("Complete")) {
            cb.setChecked(true);
        }
    }

    public void onClickCheck(View view) {
        Intent get_intent = getIntent();
        String order_id = get_intent.getStringExtra("ORDER_ID");

        String customerid = Reader.getInstance().readValue("Orders", order_id, "customer_id");
        String ownerid = Reader.getInstance().readValue("Orders", order_id, "owner_id");
        Order.instantiateCart(customerid, ownerid);
        Order.getCart().change_order_status(order_id);
    }
}