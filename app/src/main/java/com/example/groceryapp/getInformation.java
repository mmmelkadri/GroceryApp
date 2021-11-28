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
    // keywords
    private static final String orderKey = "order";
    private static final String productKey = "products";
    private static final String stateKey = "state";
    private static final String quantity = "quantity";

    private static final String productListKey = "product_list";

    private static final String itemIDKey = "0";
    private static final String itemNameKey = "1";
    private static final String itemBrandKey = "2";
    private static final String itemPriceKey = "3";

    // public to be used in Owner getOrders and Customer getOrders
    public static final String productIDKey = "product_id";
    public static final String customerIDKey = "customer_id";
    public static final String ownerIDKey = "owner_id";
    public static final String ownerKey = "Owners";
    public static final String customerKey = "Customers";


    private getInformation() { }

    public static getInformation getInstance() {
        if (info == null)
            info = new getInformation();
        return info;
    }

    public String getStoreName(String owner_ID) {
        // Returns store name with owner_ID as username
        DataSnapshot stores = Reader.getInstance().readSnapshot(ownerKey);

        if (stores == null)
            return null;

        for (DataSnapshot store : stores.getChildren()) {
            Object owner = store.child(ownerIDKey).getValue(String.class);

            if (owner instanceof String && owner == owner_ID)
                return (String) owner;
        }

        return "";
    }

    public ArrayList<Object> getIndividualProduct(String owner_ID, String product_ID) {
        // if provided owner id and product id return {itemName, itemBrand, itemPrice}
        ArrayList<Object> item = new ArrayList<Object>();

        DataSnapshot products = Reader.getInstance().readSnapshot(orderKey, owner_ID, productListKey);

        for (DataSnapshot product : products.getChildren()) {
            // if we've found the item
            if (product.child(itemIDKey).getValue(String.class) == product_ID) {
                item.add(product.child(itemNameKey).getValue(String.class));
                item.add(product.child(itemBrandKey).getValue(String.class));
                item.add(product.child(itemPriceKey).getValue(String.class));
            }
        }

        return item;
    }

    public ArrayList<Object> getAllProducts(String owner_ID) {
        // if provided owner id return {{itemName, itemBrand, itemPrice} ... }
        ArrayList<Object> items = new ArrayList<Object>();

        DataSnapshot products = Reader.getInstance().readSnapshot(orderKey, owner_ID, productListKey);

        for (DataSnapshot product : products.getChildren()) {
            ArrayList<Object> item = new ArrayList<Object>();

            item.add(product.child(itemNameKey).getValue(String.class));
            item.add(product.child(itemBrandKey).getValue(String.class));
            item.add(product.child(itemPriceKey).getValue(String.class));

            items.add(item);
        }

        return items;
    }

    public ArrayList<Object> getProductInformation(String order_ID) {
        // Returns {{productID1, quantity}, {productID2, quantity} ...} in given order
        ArrayList<Object> productInformation = new ArrayList<Object>();
        DataSnapshot products = Reader.getInstance().readSnapshot(orderKey, order_ID, productKey);

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
        DataSnapshot order = Reader.getInstance().readSnapshot(orderKey, order_ID);

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
        DataSnapshot users = Reader.getInstance().readSnapshot(owner_or_customerKey);

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
        DataSnapshot orders = Reader.getInstance().readSnapshot(orderKey);

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
