package com.example.groceryapp;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Mohamad El Kadri
public class getInformation {
    /********************************************************
     * Class for use in cleanly accessing data using Reader *
     * *****************************************************/

    private static getInformation info;
    // keywords
    private static final String orderKey = "Orders";
    private static final String productKey = "Products";
    private static final String stateKey = "state";
    private static final String quantity = "quantity";

    private static final String passwordKey = "Password";
    private static final String displayNameKey = "Public Name";

    private static final String itemNameKey = "product name";
    private static final String itemBrandKey = "brand";
    private static final String itemPriceKey = "price";

    // public to be used in Owner getOrders and Customer getOrders
    public static final String productIDKey = "product_id";
    public static final String customerIDKey = "customer_id";
    public static final String ownerIDKey = "owner_id";
    public static final String ownerKey = "Owners";
    public static final String customerKey = "Customers";

    public getInformation() { }

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
            String owner = store.child(displayNameKey).getValue(String.class);

            if (store.getKey().equals(owner_ID))
                return owner;
        }

        return "";
    }

    public ArrayList<String> getIndividualProduct(String owner_ID, String product_ID) {
        // if provided owner id and product id return {itemName, itemBrand, itemPrice}
        ArrayList<String> item = new ArrayList<String>();

        DataSnapshot products = Reader.getInstance().readSnapshot(orderKey, owner_ID, productKey);

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

    public ArrayList<String> getProduct(String owner_ID, String product_ID) {
        // return {itemName, itemBrand, itemPrice} where itemName == product_ID
        ArrayList<String> item = new ArrayList<>();

        DataSnapshot products = Reader.getInstance().readSnapshot(ownerKey, owner_ID, productKey);

        for (DataSnapshot product : products.getChildren()) {
            if (product.child(itemNameKey).getValue(String.class).equals(product_ID)) {
                item.add(product.child(itemNameKey).getValue(String.class));
                item.add(product.child(itemBrandKey).getValue(String.class));
                item.add(product.child(itemPriceKey).getValue(String.class));

                break;
            }
        }

        return item;
    }

    public ArrayList<ArrayList<String>> getAllProducts(String owner_ID) {
        // if provided owner id return {{itemName, itemBrand, itemPrice} ... }
        ArrayList<ArrayList<String>> items = new ArrayList<>();

        DataSnapshot products = Reader.getInstance().readSnapshot(ownerKey, owner_ID, productKey);
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
        DataSnapshot products = Reader.getInstance().readSnapshot(orderKey, order_ID, productKey);

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

    public String getPassword(String owner_or_customerKey, String IDKey) {
        // Returns password
        String password = Reader.getInstance().readValue(owner_or_customerKey, IDKey, passwordKey);
        return password;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getAllOrders() {
        // Returns {{{OrderID1, customerID1, ownerID1}, {productID1, productQuantity1}, {productID2, productQuantity2}...}, ...}
        ArrayList<ArrayList<ArrayList<String>>> allOrders = new ArrayList<>();

        DataSnapshot orders = Reader.getInstance().readSnapshot(orderKey);

        for (DataSnapshot order : orders.getChildren()) {
            ArrayList<ArrayList<String>> temp = new ArrayList<>();

            ArrayList<String> orderInfo = new ArrayList<>();
            orderInfo.add(order.getKey());
            orderInfo.add(order.child(customerIDKey).getValue(String.class));
            orderInfo.add(order.child(ownerIDKey).getValue(String.class));

            temp.add(orderInfo);

            for (DataSnapshot product : order.child(productKey).getChildren()) {
                ArrayList<String> item = new ArrayList<>();

                item.add(product.child(productIDKey).getValue(String.class));
                item.add(product.child(quantity).getValue(String.class));

                temp.add(item);
            }

            allOrders.add(temp);
        }

        return allOrders;
    }

    public ArrayList<Object> getAllUsers(String owner_or_customerKey) {
        // Returns {UserID1, UserID2 ... }, owner/customer user depending on key
        ArrayList<Object> userInformation = new ArrayList<>();
        DataSnapshot users = Reader.getInstance().readSnapshot(owner_or_customerKey);

        if (users == null) {
            return null;
        }

        for (DataSnapshot user : users.getChildren()) {
            Log.d("User: ", user.getKey());
            if (user.getKey() != null)
                userInformation.add(user.getKey());
        }

        return userInformation;
    }

    public String getState(String orderID) {
        // Returns state of given orderID. If not found, return empty string
        DataSnapshot orders = Reader.getInstance().readSnapshot(orderKey);

        if (orders == null)
            return null;

        for (DataSnapshot order : orders.getChildren()) {
            if (order.getKey() == orderID) {
                return order.child(stateKey).getValue(String.class);
            }
        }

        return "";
    }
    public ArrayList<ArrayList<String>> getOrders(String user_ID, String owner_or_customerIDKey) {
        // Returns {{OrderID1, state}, ...} if order owner/customer (depending on input) has same user_ID
        ArrayList<ArrayList<String>> storeInformation = new ArrayList<>();
        DataSnapshot orders = Reader.getInstance().readSnapshot(orderKey);

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

    public String getOrderOwner(String orderID) {
        // Returns ownerID of given orderID
        DataSnapshot orders = Reader.getInstance().readSnapshot(orderKey);

        for (DataSnapshot order : orders.getChildren()) {
            if (order.getKey().equals(orderID)) {
                return order.child(ownerIDKey).getValue(String.class);
            }
        }

        return "";
    }

    public ArrayList<ArrayList<String>> getOwnerOrders(String user_ID, String owner_or_customerIDKey) {
        // Returns {{OrderID1, state}, ...} if order owner/customer (depending on input) has same user_ID
        ArrayList<ArrayList<String>> storeInformation = new ArrayList<>();
        DataSnapshot orders = Reader.getInstance().readSnapshot(owner_or_customerIDKey, user_ID, orderKey);

        if (orders == null){

            return null;
        }

        for (DataSnapshot order : orders.getChildren()) {

            ArrayList<String> temp = new ArrayList<>();
            temp.add(order.getKey());
            temp.add(order.child(stateKey).getValue(String.class));

            storeInformation.add(temp);
        }
        return storeInformation;
    }

    public int getNumOrders() {
        // Returns number of orders
        DataSnapshot orders = Reader.getInstance().readSnapshot(orderKey);

        if (orders == null)
            return -1;


        int counter = 0;
        for (DataSnapshot order : orders.getChildren()) {
            counter++;
        }

        return counter;
    }
}
