package com.example.groceryapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Writer {
    public DatabaseReference mDatabase;
    public Writer(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public void writeNewUser(String UserId, String name, String email){
       // Customer customer = new Customer(name, email);
       // mDatabase.child("users").child(UserId).setValue(customer);
    }
}
