package com.example.groceryapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Customer extends User{
    ArrayList<String> orders;
    Order cart;
    public Customer(String username, String password, String display_name){
        orders = new ArrayList<String>();
        this.password = password;
        this.display_name = display_name;
        this.username = username;
    }
    public void writeToDatabase(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Customers").child(username).child("Public Name").setValue(this.display_name);
        mDatabase.child("Customers").child(username).child("Password").setValue(this.password);
    }
    public ArrayList<Object> getOrders(String user_ID) {
        return getInformation.getInstance().getOrders(user_ID, getInformation.customerIDKey);
    }
}
