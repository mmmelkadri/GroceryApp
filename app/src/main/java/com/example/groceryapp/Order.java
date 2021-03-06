package com.example.groceryapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Order implements Serializable {
    private static Order cart;
    String orderId;
    String customerId;
    String ownerID;
    boolean status = false;
    ArrayList<ArrayList<String>> products_and_quantity;
    int PRODUCT_ID = 0;
    int BRAND = 1;
    int PRICE = 2;
    int QUANTITY = 3;

    private Order(){
        products_and_quantity = new ArrayList<>();
    }

    // Should be called at the onCreate of AllStores
    public static void instantiateCart(String customerId, String ownerID) {
        cart = new Order();
        cart.customerId = customerId;
        cart.ownerID = ownerID;
        cart.products_and_quantity = new ArrayList<>();
    }

    public static Order getCart() {
        if (cart == null)
            cart = new Order();
        return cart;
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
            mDatabase.child("Owners").child(ownerID).child("Orders").child(orderid).child("state").setValue("Complete");
            mDatabase.child("Customers").child(customerId).child("Orders").child(orderid).child("state").setValue("Complete");
            mDatabase.child("Orders").child(orderid).child("state").setValue("Complete");
        }
        else if (status.equals("Complete")) {
            mDatabase.child("Owners").child(ownerID).child("Orders").child(orderid).child("state").setValue("Incomplete");
            mDatabase.child("Customers").child(customerId).child("Orders").child(orderid).child("state").setValue("Incomplete");
            mDatabase.child("Orders").child(orderid).child("state").setValue("Incomplete");
        }
    }

    public void checkoutOrder(){
        //Access Order counter, add one, assign to orderId, call WriteToDatabase()
        orderId = Integer.toString(getInformation.getInstance().getNumOrders() + 1);
        write_to_database();
        // make cart empty
        this.products_and_quantity = new ArrayList<>();
    }

    public void write_to_database(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Owners").child(ownerID).child("Orders").child(orderId).child("state").setValue("Incomplete");
        mDatabase.child("Owners").child(ownerID).child("Orders").child(orderId).child("customer_id").setValue(customerId);
        mDatabase.child("Owners").child(ownerID).child("Orders").child(orderId).child("ownerId").setValue(ownerID);

        mDatabase.child("Customers").child(customerId).child("Orders").child(orderId).child("state").setValue("Incomplete");
        mDatabase.child("Customers").child(customerId).child("Orders").child(orderId).child("customer_id").setValue(customerId);
        mDatabase.child("Customers").child(customerId).child("Orders").child(orderId).child("owner_id").setValue(ownerID);

        mDatabase.child("Orders").child(orderId).child("state").setValue("Incomplete");
        mDatabase.child("Orders").child(orderId).child("customer_id").setValue(customerId);
        mDatabase.child("Orders").child(orderId).child("owner_id").setValue(ownerID);

        for(ArrayList<String> i: products_and_quantity){
            mDatabase.child("Orders").child(orderId).child("Products").child(i.get(PRODUCT_ID))
                    .child("quantity").setValue(i.get(QUANTITY));
        }

    }
}
