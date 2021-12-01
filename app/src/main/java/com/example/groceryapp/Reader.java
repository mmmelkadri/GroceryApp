package com.example.groceryapp;

import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// Mohamad El Kadri
public class Reader {
    /***************************
     * Generic FireBase reader *
     * ************************/

    private static Reader read;
    DataSnapshot dataSnapshot;

    private Reader() {
        FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataSnapshot = snapshot;
                Log.d("Reader: ", "Updated DataSnapshot");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });
    }

    public static Reader getInstance() {
        if (read == null)
            read = new Reader();
        return read;
    }

    public String readValue(String... args) {
        // Pass in keys path from mDatabase root to get value
        DataSnapshot temp = dataSnapshot;

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
        DataSnapshot temp = dataSnapshot;

        for (String arg : args) {
            // if the child does not exist, return null
            if (temp.child(arg) == null)
                return null;

            temp = temp.child(arg);
        }

        return temp;
    }
}
