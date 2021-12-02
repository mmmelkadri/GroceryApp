package com.example.groceryapp;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Product {
    String brand;
    String product_Id;
    String price;

    public Product(String product_Id, String price, String brand){
        this.brand = brand;
        this.product_Id = product_Id;
        this.price = price;
    }
    public void writeToDatabase(String user_ID){
        if (brand.equals("")||product_Id.equals("")||price.equals("")){
            throw new IllegalArgumentException("Missing fields, please complete all the required fields");
        }

        // check if .contains will find String and Object as equal

        ArrayList<ArrayList<String>> all_products  = getInformation.getInstance().getAllProducts(user_ID);
        for(ArrayList<String> i: all_products){
            if (i.contains(product_Id)){
                throw new IllegalArgumentException("This product is already in use, please change the product name");
            }
        }

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Owners").child(user_ID).child("Products").child(product_Id).child("brand").setValue(brand);
        mDatabase.child("Owners").child(user_ID).child("Products").child(product_Id).child("price").setValue(price);
        mDatabase.child("Owners").child(user_ID).child("Products").child(product_Id).child("product name").setValue(product_Id);
    }
}
