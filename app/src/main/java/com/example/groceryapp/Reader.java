package com.example.groceryapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// Mohamad El Kadri
public class Reader {
    /***************************
     * Generic FireBase reader *
     * ************************/

    DatabaseReference mDatabase;
    public static Reader read;

    private Reader() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public static Reader getInstance() {
        if (read == null)
            read = new Reader();
        return read;
    }

    public String readValue(String... args) {
        // Pass in keys path from mDatabase root to get value

        // .get() is asynchronous, so we need to wait for the database
        Task<DataSnapshot> databaseSnapshot = mDatabase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });

        DataSnapshot temp = databaseSnapshot.getResult();

        for (String arg : args) {
            // if the child does not exist, return empty string
            if (temp.child(arg) == null)
                return "";

            temp = temp.child(arg);
        }

        return (String) temp.getValue(String.class);
    }

    public DataSnapshot readSnapshot(String... args) {
        // Pass in keys path from mDatabase root to get snapshot at final key

        // .get() is asynchronous, so we need to wait for the database
        Task<DataSnapshot> databaseSnapshot = mDatabase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });

        DataSnapshot temp = databaseSnapshot.getResult();

        for (String arg : args) {
            // if the child does not exist, return null
            if (temp.child(arg) == null)
                return null;

            temp = temp.child(arg);
        }

        return temp;
    }
}
