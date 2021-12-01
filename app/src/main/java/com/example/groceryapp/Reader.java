package com.example.groceryapp;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.ExecutionException;

// Mohamad El Kadri
public class Reader {
    /***************************
     * Generic FireBase reader *
     * ************************/

    private static Reader read;
    DataSnapshot dataSnapshot;

    private Reader() { }

    public static Reader getInstance() {
        if (read == null)
            read = new Reader();
        return read;
    }

    public String readValue(DataSnapshot dataSnapshot, String... args) {
        // Pass in keys path from mDatabase root to get value

        for (String arg : args) {
            // if the child does not exist, return empty string
            if (dataSnapshot.child(arg) == null)
                return "";

            dataSnapshot = dataSnapshot.child(arg);
        }

        return (String) dataSnapshot.getValue(String.class);
    }

    public DataSnapshot readSnapshot(DataSnapshot dataSnapshot, String... args) {
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
