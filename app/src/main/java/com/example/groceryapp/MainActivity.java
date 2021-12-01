package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MUST be called ahead of delay, since .get() is asynchronous
        getSnapshot snapshot = new getSnapshot();

        // login delay
        new Timer().schedule(new TimerTask(){
            public void run() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        localDatabase.access().setSnapshot(snapshot.dataSnapshot);
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                });

            }
        }, 2000);
    }
}