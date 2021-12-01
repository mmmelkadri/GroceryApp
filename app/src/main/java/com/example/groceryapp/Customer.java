package com.example.groceryapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Customer extends User{
    ArrayList<ArrayList<String>> orders;
    Order cart;
    String username;
    String display_name;
    String password;

    public Customer(String username, String password, String display_name){
        orders = new ArrayList<>();
        this.password = password;
        this.display_name = display_name;
        this.username = username;
    }
    public void writeToDatabase(){
        if (username.equals("")||password.equals("")||display_name.equals("")){
            throw new IllegalArgumentException("Missing fields, please complete all the required fields");
        }

        if(localDatabase.access().getCustomer(username) != null){
            throw new IllegalArgumentException("This username is already in use, please change your username");
        }

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Customers").child(username).child("Public Name").setValue(this.display_name);
        mDatabase.child("Customers").child(username).child("Password").setValue(this.password);
    }
    /*
    public ArrayList<ArrayList<String>> getOrders(String user_ID) {
        return getInformation.getInstance().getOrders(user_ID, getInformation.customerIDKey);
    }*/
}
