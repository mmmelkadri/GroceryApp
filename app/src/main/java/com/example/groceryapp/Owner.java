package com.example.groceryapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Owner extends User{
    ArrayList<ArrayList<String>> orders;
    ArrayList<Product> products;

    String username;
    String password;
    String display_name;

    public Owner(String username, String password, String display_name){
        this.password = password;
        this.display_name = display_name;
        this.username = username;
    }

    public void writeToDatabase(){
        if (username.equals("")||password.equals("")||display_name.equals("")){
            throw new IllegalArgumentException("Missing fields, please complete all the required fields");
        }

        if(getInformation.getInstance().getAllUsers(getInformation.ownerKey).contains(username)){
            throw new IllegalArgumentException("This username is already in use, please change your username");
        }

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Owners").child(username).child("Public Name").setValue(this.display_name);
        mDatabase.child("Owners").child(username).child("Password").setValue(this.password);
    }

    /*
    public ArrayList<ArrayList<String>> getOrders(String user_ID) {
        return getInformation.getInstance().getOrders(user_ID, getInformation.ownerIDKey);
    }*/

    public void add_product(Product product) {
        products.add(product);
    }
}
