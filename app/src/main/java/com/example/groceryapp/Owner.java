package com.example.groceryapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Owner extends User{
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
}
