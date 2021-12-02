package com.example.groceryapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Order  {
    String orderId;
    String customerId;
    String ownerID;
    boolean status = false;
    ArrayList<ArrayList<String>> products_and_quantity;
    int PRODUCT_ID = 0;
    int PRODUCT_NAME = 1;
    int BRAND = 2;
    int PRICE = 3;
    int QUANTITY = 4;
    public Order(String customerId, String ownerID){
        this.customerId = customerId;
        this.ownerID = ownerID;
    }

    public void add_to_order(Product product, String quantity){
        ArrayList<String> product_spec = new ArrayList<String>();
        product_spec.add(product.product_Id);
        product_spec.add(product.brand);
        product_spec.add(product.price);
        product_spec.add(quantity);
        products_and_quantity.add(product_spec);
    }

    //Should this change order_status in database or in order?
    public void change_order_status(String orderid) {
        if (!status) {
            status = true;
        }
        else {
            status = false;
        }
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        String status = getInformation.getInstance().getState(orderid);
        if (status.equals("Incomplete")) {
            mDatabase.child("users").child(ownerID).child("orders").child(orderid).child("state").setValue("Complete");
            mDatabase.child("users").child(customerId).child("orders").child(orderid).child("state").setValue("Complete");
            mDatabase.child("orders").child(orderid).child("state").setValue("Complete");
        }
        else if (status.equals("Complete")) {
            mDatabase.child("users").child(ownerID).child("orders").child(orderid).child("state").setValue("Incomplete");
            mDatabase.child("users").child(customerId).child("orders").child(orderid).child("state").setValue("Incomplete");
            mDatabase.child("orders").child(orderid).child("state").setValue("Incomplete");
        }
    }

    public void checkoutOrder(){
        //Access Order counter, add one, assign to orderId, call WriteToDatabase()
        orderId = Integer.toString(getInformation.getInstance().getNumOrders() + 1);
        write_to_database();
    }

    public void write_to_database(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(ownerID).child("orders").child(orderId).child("state").setValue("incomplete");
        mDatabase.child("users").child(ownerID).child("orders").child(orderId).child("customerId").setValue(customerId);
        mDatabase.child("users").child(ownerID).child("orders").child(orderId).child("ownerId").setValue(ownerID);

        mDatabase.child("users").child(customerId).child("orders").child(orderId).child("state").setValue("incomplete");
        mDatabase.child("users").child(customerId).child("orders").child(orderId).child("customerId").setValue(customerId);
        mDatabase.child("users").child(customerId).child("orders").child(orderId).child("ownerId").setValue(ownerID);

        mDatabase.child("orders").child(orderId).child("state").setValue("incomplete");
        mDatabase.child("orders").child(orderId).child("customerId").setValue(customerId);
        mDatabase.child("orders").child(orderId).child("ownerId").setValue(ownerID);

        // Also add to localDatabase
        // *************************

        int j=0;
        for(ArrayList<String> i: products_and_quantity){
            mDatabase.child("orders").child(orderId).child("products").child(Integer.toString(j))
                    .child("product_id").setValue(i.get(PRODUCT_ID));
            j++;
        }

    }
}
