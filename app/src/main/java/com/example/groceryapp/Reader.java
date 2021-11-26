package com.example.groceryapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Reader {
    DatabaseReference mDatabase;
    private static Reader reader;

    private Reader() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public static Reader getInstance() {
        if(reader == null)
            reader = new Reader();
        return reader;
    }

    public String readValue(String... args) {
        // Pass in keys in order of access from mDatabase root to get value
        DataSnapshot temp = mDatabase.get().getResult();

        for (String arg : args) {
            // if the child does not exist, return empty string
            if (temp.child(arg) == null)
                return "";

            temp = temp.child(arg);
        }

        return (String) temp.getValue();
    }

    public DataSnapshot readSnapshot(String... args) {
        // Pass in keys in order of access from mDatabase root to get snapshot at final key
        DataSnapshot temp = mDatabase.get().getResult();

        for (String arg : args) {
            // if the child does not exist, return empty string
            if (temp.child(arg) == null)
                return null;

            temp = temp.child(arg);
        }

        return temp;
    }
}
