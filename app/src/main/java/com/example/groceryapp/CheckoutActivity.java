package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // REMOVE
        ArrayList<ArrayList<String>> total = new ArrayList<ArrayList<String>>();

        ArrayList<String> a = new ArrayList<String>();
        a.add("a");
        a.add("a");
        a.add("a");
        a.add("4");
        a.add("4");

        ArrayList<String> b = new ArrayList<String>();
        a.add("b");
        a.add("b");
        a.add("b");
        a.add("4");
        a.add("4");

        total.add(a); total.add(b);
        // REMOVE

        // Read users cart and add buttons to the scroll layout
        RecyclerView recyclerView = findViewById(R.id.rvItems);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, total);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}