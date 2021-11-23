package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Testing: Write a message to Database
        // Please delete later
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        // Test complete

        // test test

        Writer n_w = new Writer();
        String email = "tengo_hambre@email.com";
        String username = "hambriento";
        String UserId = "105";
        n_w.writeNewUser(UserId, username, email);
    }


}