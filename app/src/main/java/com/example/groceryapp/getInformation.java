package com.example.groceryapp;

import android.provider.ContactsContract;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

// Mohamad El Kadri
public class getInformation {
    /********************************************************
     * Class for use in cleanly accessing data using Reader *
     * *****************************************************/

    private static getInformation info;
    public DataSnapshot dataSnapshot;
    // keywords
    private static final String orderKey = "order";
    private static final String productKey = "products";
    private static final String stateKey = "state";
    private static final String quantity = "quantity";

    private static final String passwordKey = "password";
    private static final String displayNameKey = "Public Name";


    private static final String productListKey = "product_list";

    private static final String itemNameKey = "product name";
    private static final String itemBrandKey = "brand";
    private static final String itemPriceKey = "price";

    // public to be used in Owner getOrders and Customer getOrders
    public static final String productIDKey = "product_id";
    public static final String customerIDKey = "customer_id";
    public static final String ownerIDKey = "owner_id";
    public static final String ownerKey = "Owners";
    public static final String customerKey = "Customers";

    public getInformation(DataSnapshot dataSnapshot) {
        this.dataSnapshot = dataSnapshot;
    }

    public void updateData(DataSnapshot dataSnapshot) {
        this.dataSnapshot = dataSnapshot;
    }

    public String getStoreName(String owner_ID) {
        // Returns store name with owner_ID as username
        DataSnapshot stores = Reader.getInstance().readSnapshot(dataSnapshot, ownerKey);

        if (stores == null)
            return null;

        for (DataSnapshot store : stores.getChildren()) {
            Object owner = store.child(ownerIDKey).getValue(String.class);

            if (owner instanceof String && owner == owner_ID)
                return (String) owner;
        }

        return "";
    }

    public ArrayList<String> getIndividualProduct(String owner_ID, String product_ID) {
        // if provided owner id and product id return {itemName, itemBrand, itemPrice}
        ArrayList<String> item = new ArrayList<String>();

        DataSnapshot products = Reader.getInstance().readSnapshot(dataSnapshot, orderKey, owner_ID, productListKey);

        for (DataSnapshot product : products.getChildren()) {
            // if we've found the item
            if (product.getKey() == product_ID) {
                item.add(product.child(itemNameKey).getValue(String.class));
                item.add(product.child(itemBrandKey).getValue(String.class));
                item.add(product.child(itemPriceKey).getValue(String.class));
            }
        }

        return item;
    }

    public ArrayList<ArrayList<String>> getAllProducts(String owner_ID) {
        // if provided owner id return {{itemName, itemBrand, itemPrice} ... }
        ArrayList<ArrayList<String>> items = new ArrayList<>();

        DataSnapshot products = Reader.getInstance().readSnapshot(dataSnapshot, orderKey, owner_ID, productListKey);

        for (DataSnapshot product : products.getChildren()) {
            ArrayList<String> item = new ArrayList<>();

            item.add(product.child(itemNameKey).getValue(String.class));
            item.add(product.child(itemBrandKey).getValue(String.class));
            item.add(product.child(itemPriceKey).getValue(String.class));

            items.add(item);
        }

        return items;
    }

    public ArrayList<ArrayList<String>> getProductInformation(String order_ID) {
        // Returns {{productID1, quantity}, {productID2, quantity} ...} in given order
        ArrayList<ArrayList<String>> productInformation = new ArrayList<>();
        DataSnapshot products = Reader.getInstance().readSnapshot(dataSnapshot, orderKey, order_ID, productKey);

        if (products == null)
            return null;

        for (DataSnapshot product : products.getChildren()) {
            ArrayList<String> temp = new ArrayList<String>();
            // Each product must have a productIDKey and quantity
            temp.add(product.child(productIDKey).getValue(String.class));
            temp.add(product.child(quantity).getValue(String.class));
            productInformation.add(temp);
        }

        return productInformation;
    }

    public ArrayList<String> getOrderInformation(String order_ID) {
        // Returns {CustomerID, OwnerID, {{productID1, quantity}, {productID2, quantity} ... }, state}
        ArrayList<String> orderInformation = new ArrayList<>();
        DataSnapshot order = Reader.getInstance().readSnapshot(dataSnapshot, orderKey, order_ID);

        if (order == null)
            return null;

        // Add first two elements
        orderInformation.add(order.child(customerIDKey).getValue(String.class));
        orderInformation.add(order.child(ownerIDKey).getValue(String.class));

        orderInformation.add(getProductInformation(order_ID));
        orderInformation.add(order.child(stateKey).getValue(String.class));

        return orderInformation;
    }

    public ArrayList<String> getPasswordAndDisplay(String owner_or_customerKey, String IDKey) {
        // Returns {password, display_name}
        ArrayList<String> display_and_password = new ArrayList<>();
        DataSnapshot snap = Reader.getInstance().readSnapshot(dataSnapshot, owner_or_customerKey, IDKey);

        if (snap == null)
            return null;

        display_and_password.add(snap.child(passwordKey).getValue(String.class));
        display_and_password.add(snap.child(displayNameKey).getValue(String.class));

        return display_and_password;
    }

    public ArrayList<String> getAllUsers(String owner_or_customerKey) {
        // Returns {UserID1, UserID2 ... }, owner/customer user depending on key
        ArrayList<String> userInformation = new ArrayList<String>();
        DataSnapshot users = Reader.getInstance().readSnapshot(dataSnapshot, owner_or_customerKey);

        if (users == null)
            return null;

        for (DataSnapshot user : users.getChildren()) {
            userInformation.add(user.getKey());
        }

        return userInformation;
    }

    public ArrayList<ArrayList<String>> getOrders(String user_ID, String owner_or_customerIDKey) {
        // Returns {{OrderID1, state}, ...} if order owner/customer (depending on input) has same user_ID
        ArrayList<ArrayList<String>> storeInformation = new ArrayList<>();
        DataSnapshot orders = Reader.getInstance().readSnapshot(dataSnapshot, orderKey);

        if (orders == null)
            return null;

        for (DataSnapshot order : orders.getChildren()) {
            if (order.child(owner_or_customerIDKey).getValue() == user_ID) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(order.getKey());
                temp.add(order.child(stateKey).getValue(String.class));

                storeInformation.add(temp);
            }
        }

        return storeInformation;
    }
}
