package com.example.groceryapp;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class getInformation {
    // keywords
    private static final String orderKey = "order";
    private static final String productKey = "products";
    private static final String stateKey = "state";
    private static final String quantity = "quantity";
    private static final String productIDKey = "product_id";
    // public to be used in Owner getOrders and Customer getOrders
    public static final String customerIDKey = "customer_id";
    public static final String ownerIDKey = "owner_id";
    public static final String ownerKey = "owner";
    public static final String customerKey = "customer";


    public getInformation() { }

    public String getStoreName(String owner_ID) {
        // Returns store name with owner_ID as username
        DataSnapshot stores = new Reader().readSnapshot(ownerKey);

        if (stores == null)
            return null;

        for (DataSnapshot store : stores.getChildren()) {
            Object owner = store.child(ownerIDKey).getValue(String.class);

            if (owner instanceof String && owner == owner_ID)
                return (String) owner;
        }

        return "";
    }

    /*public ArrayList<Object> getIndividualProduct(String owner_ID, String product_ID) {
        // if provided owner id, product id return name and brand
        DataSnapshot products = Reader.getInstance().readSnapshot(orderKey, order_ID).child(productKey);
        //
        // TO COMPLETE
        //
    }*/

    public ArrayList<Object> getProductInformation(String order_ID) {
        // Returns {{productID1, quantity}, {productID2, quantity} ...}
        ArrayList<Object> productInformation = new ArrayList<Object>();
        DataSnapshot products = new Reader().readSnapshot(orderKey, order_ID).child(productKey);

        if (products == null)
            return null;

        for (DataSnapshot product : products.getChildren()) {
            ArrayList<Object> temp = new ArrayList<Object>();
            // Each product must have a productIDKey and quantity
            temp.add(product.child(productIDKey).getValue(String.class));
            temp.add(product.child(quantity).getValue(String.class));
            productInformation.add(temp);
        }

        return productInformation;
    }

    public ArrayList<Object> getOrderInformation(String order_ID) {
        // Returns {CustomerID, OwnerID, {{productID1, quantity}, {productID2, quantity} ... }, state}
        ArrayList<Object> orderInformation = new ArrayList<Object>();
        DataSnapshot order = new Reader().readSnapshot(orderKey, order_ID);

        if (order == null)
            return null;

        // Add first two elements
        orderInformation.add(order.child(customerIDKey).getValue(String.class));
        orderInformation.add(order.child(ownerIDKey).getValue(String.class));

        orderInformation.add(getProductInformation(order_ID));
        orderInformation.add(order.child(stateKey).getValue(String.class));

        return orderInformation;
    }

    public ArrayList<Object> getAllUsers(String owner_or_customerKey) {
        // Returns {UserID1, UserID2 ... }, owner/customer user depending on key
        ArrayList<Object> userInformation = new ArrayList<Object>();
        DataSnapshot users = new Reader().readSnapshot(owner_or_customerKey);

        if (users == null)
            return null;

        for (DataSnapshot user : users.getChildren()) {
            userInformation.add(user.getKey());
        }

        return userInformation;
    }

    public ArrayList<Object> getOrders(String user_ID, String owner_or_customerIDKey) {
        // Returns {{OrderID1, state}, ...} if order owner/customer (depending on input) has same user_ID
        ArrayList<Object> storeInformation = new ArrayList<Object>();
        DataSnapshot orders = new Reader().readSnapshot(orderKey);

        if (orders == null)
            return null;

        for (DataSnapshot order : orders.getChildren()) {
            if (order.child(owner_or_customerIDKey).getValue() == user_ID) {
                ArrayList<Object> temp = new ArrayList<Object>();
                temp.add(order.getKey());
                temp.add(order.child(stateKey).getValue(String.class));

                storeInformation.add(temp);
            }
        }

        return storeInformation;
    }
}
