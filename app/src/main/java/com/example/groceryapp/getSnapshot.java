package com.example.groceryapp;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class getSnapshot {
    DatabaseReference mDatabase;
    DataSnapshot dataSnapshot;

    public getSnapshot() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public DataSnapshot getDataSnapshot() {
        mDatabase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                dataSnapshot = task.getResult();
            }
        });

        return dataSnapshot;
    }
}
