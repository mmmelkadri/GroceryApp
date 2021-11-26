package com.example.groceryapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Owner extends User{
    ArrayList<String> orders;
    ArrayList<Object> products;
    public Owner(String password, String display_name){
        this.password = password;
        this.display_name = display_name;
    }

    public void writeToDatabase(String user_ID){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Owners").child(user_ID).child("Public Name").setValue(this.display_name);
        mDatabase.child("Owners").child(user_ID).child("Password").setValue(this.password);
    }

    public ArrayList<Object> getOrders(String user_ID) {
        return getInformation.info.getOrders(user_ID, getInformation.ownerIDKey);
    }
}
