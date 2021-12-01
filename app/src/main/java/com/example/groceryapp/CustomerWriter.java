package com.example.groceryapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerWriter extends Writer {
    public DatabaseReference mDatabase;
    public CustomerWriter(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public void writeNewUser(String UserId, String password, String name){
        Customer customer = new Customer(UserId, password, name);
        mDatabase.child("users").child(UserId).setValue(customer);
    }
}
