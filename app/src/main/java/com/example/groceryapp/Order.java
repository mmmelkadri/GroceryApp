package com.example.groceryapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Order  {
    String orderId;
    String customerId;
    String ownerID;
    ArrayList<Object> products;

    public Order(String customerId, String ownerID){
        this.customerId = customerId;
        this.ownerID = ownerID;
    }
    //public void add_to_order()
    public void change_order_status() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        String status = Reader.getInstance().readValue("users", ownerID, "orders", orderId, "state");
        if (status.equals("incomplete")) {
            mDatabase.child("users").child(ownerID).child("orders").child(orderId).child("state").setValue("complete");
            mDatabase.child("users").child(customerId).child("orders").child(orderId).child("state").setValue("complete");
            mDatabase.child("orders").child(orderId).child("state").setValue("complete");
        }
        else if (status.equals("complete")) {
            mDatabase.child("users").child(ownerID).child("orders").child(orderId).child("state").setValue("incomplete");
            mDatabase.child("users").child(customerId).child("orders").child(orderId).child("state").setValue("incomplete");
            mDatabase.child("orders").child(orderId).child("state").setValue("incomplete");
        }
    }

    public void checkoutOrder(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        //Access Order counter, add one, assign to orderId, WriteToDatabase()
        int i = 0;
        while (true) {
            ArrayList<Object> list = getInformation.getInstance().getOrderInformation(Integer.toString(Integer.valueOf(i)));
            if(list == null) {
                break;
            }
            i++;
        }
        orderId = Integer.toString(Integer.valueOf(i));
        mDatabase.child("users").child(ownerID).child("orders").child(orderId).child("state").setValue("incomplete");
        mDatabase.child("users").child(ownerID).child("orders").child(orderId).child("customerId").setValue(customerId);
        mDatabase.child("users").child(ownerID).child("orders").child(orderId).child("ownerId").setValue(ownerID);

        mDatabase.child("users").child(customerId).child("orders").child(orderId).child("state").setValue("incomplete");
        mDatabase.child("users").child(customerId).child("orders").child(orderId).child("customerId").setValue(customerId);
        mDatabase.child("users").child(customerId).child("orders").child(orderId).child("ownerId").setValue(ownerID);

        mDatabase.child("orders").child(orderId).child("state").setValue("incomplete");
        mDatabase.child("orders").child(orderId).child("customerId").setValue(customerId);
        mDatabase.child("orders").child(orderId).child("ownerId").setValue(ownerID);
    }
}
