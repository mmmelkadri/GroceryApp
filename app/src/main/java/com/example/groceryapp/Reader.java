package com.example.groceryapp;

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

    public void updateDataSnapshot() {
        FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataSnapshot = snapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });
    }

    public String readValue(String... args) {
        // Pass in keys path from mDatabase root to get value

        for (String arg : args) {
            // if the child does not exist, return empty string
            if (dataSnapshot.child(arg) == null)
                return "";

            dataSnapshot = dataSnapshot.child(arg);
        }

        return (String) dataSnapshot.getValue(String.class);
    }

    public DataSnapshot readSnapshot(String... args) {
        // Pass in keys path from mDatabase root to get snapshot at final key

        for (String arg : args) {
            // if the child does not exist, return null
            if (dataSnapshot.child(arg) == null)
                return null;

            dataSnapshot = dataSnapshot.child(arg);
        }

        return dataSnapshot;
    }
}
