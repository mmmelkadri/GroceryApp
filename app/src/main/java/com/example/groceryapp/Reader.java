package com.example.groceryapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Reader {
    Task<DataSnapshot> databaseSnapshot;
    public static Reader read;

    private Reader() {
        // .get() is asynchronous, so we need to set it up ahead of time
        databaseSnapshot = FirebaseDatabase.getInstance().getReference().get();
    }

    public static Reader getInstance() {
        if (read == null)
            read = new Reader();
        return read;
    }

    public String readValue(String... args) {
        // Pass in keys in order of access from mDatabase root to get value
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
        // Pass in keys in order of access from mDatabase root to get snapshot at final key
        DataSnapshot temp = databaseSnapshot.getResult();

        for (String arg : args) {
            // if the child does not exist, return empty string
            if (temp.child(arg) == null)
                return null;

            temp = temp.child(arg);
        }

        return temp;
    }
}
