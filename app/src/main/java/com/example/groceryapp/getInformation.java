package com.example.groceryapp;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class getInformation {
    // keywords
    private static final String orderKey = "order";
    private static final String productKey = "products";
    private static final String ownerKey = "owner";
    private static final String quantity = "quantity";
    private static final String customerIDKey = "customer_id";
    private static final String ownerIDKey = "owner_id";
    private static final String productIDKey = "product_id";

    public ArrayList<Object> getProductInformation(String orderID) {
        // Returns {{productID1, quantity}, {productID2, quantity} ...}
        ArrayList<Object> productInformation = new ArrayList<Object>();
        DataSnapshot products = Reader.getInstance().readSnapshot(orderKey, orderID).child(productKey);

        for (DataSnapshot product : products.getChildren()) {
            ArrayList<Object> t = new ArrayList<Object>();
            t.add(product.child(productIDKey).getValue());
            t.add(product.child(quantity).getValue());
            productInformation.add(t);
        }

        return productInformation;
    }

    public ArrayList<Object> getOrderInformation(String orderID) {
        // Returns {CustomerID, OwnerID, {{productID1, quantity}, {productID2, quantity} ... }}
        ArrayList<Object> orderInformation = new ArrayList<Object>();
        DataSnapshot order = Reader.getInstance().readSnapshot(orderKey, orderID);

        // Add first two elements
        orderInformation.add(order.child(customerIDKey).getValue());
        orderInformation.add(order.child(ownerIDKey).getValue());

        orderInformation.add(getProductInformation(orderID));

        return orderInformation;
    }

    public ArrayList<Object> getAllStores() {
        // Returns {OwnerID1, OwnerID2 ... }
        ArrayList<Object> storeInformation = new ArrayList<Object>();

        DataSnapshot stores = Reader.getInstance().readSnapshot(ownerKey);

        for (DataSnapshot store : stores.getChildren()) {
            storeInformation.add(store.getKey());
        }

        return storeInformation;
    }
}
