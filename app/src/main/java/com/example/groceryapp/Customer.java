package com.example.groceryapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Customer extends User{
    ArrayList<String> orders;
    Order cart;
    public Customer(String password, String display_name){
        orders = new ArrayList<String>();
        this.password = password;
        this.display_name = display_name;
    }
    public void writeToDatabase(String user_ID){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Customers").child(user_ID).child("Public Name").setValue(this.display_name);
        mDatabase.child("Customers").child(user_ID).child("Password").setValue(this.password);
    }
    public ArrayList<Object> getOrders(String user_ID) {
        return getInformation.getInstance().getOrders(user_ID, getInformation.customerIDKey);
    }
}
