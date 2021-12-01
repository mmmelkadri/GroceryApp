package com.example.groceryapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class getSnapshot {
    public static getSnapshot snapshot = new getSnapshot();
    DatabaseReference mDatabase;
    DataSnapshot dataSnapshot;

    public getSnapshot() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void updateDataSnapshot() {
        mDatabase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Log.d("DONE", "DONE");
                snapshot.dataSnapshot = task.getResult();
            }
        });
    }
}
