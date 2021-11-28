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
        try {
            DataSnapshot temp = Tasks.await(mDatabase.get());

            for (String arg : args) {
                // if the child does not exist, return empty string
                if (temp.child(arg) == null)
                    return "";

                temp = temp.child(arg);
            }

            return (String) temp.getValue(String.class);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return "";
    }

    public DataSnapshot readSnapshot(String... args) {
        // Pass in keys path from mDatabase root to get snapshot at final key

        // .get() is asynchronous, so we need to wait for the database
        try {
            DataSnapshot temp = Tasks.await(mDatabase.get());

            for (String arg : args) {
                // if the child does not exist, return null
                if (temp.child(arg) == null)
                    return null;

                temp = temp.child(arg);
            }

            return temp;

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
