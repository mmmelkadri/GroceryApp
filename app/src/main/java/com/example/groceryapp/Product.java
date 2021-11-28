package com.example.groceryapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Product {
    String product_name;
    String brand;
    String product_Id;
    String price;

    public Product(String product_Id, String product_name, String price, String brand){
        this.brand = brand;
        this.product_Id = product_Id;
        this.price = price;
        this.product_name = product_name;
    }
    public void writeToDatabase(String user_ID){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Owners").child(user_ID).child("Products").child(product_Id).child("brand").setValue(brand);
        mDatabase.child("Owners").child(user_ID).child("Products").child(product_Id).child("price").setValue(price);
        mDatabase.child("Owners").child(user_ID).child("Products").child(product_Id).child("product name").setValue(product_name);
    }
}
