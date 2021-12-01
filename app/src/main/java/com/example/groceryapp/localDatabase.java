package com.example.groceryapp;

import com.google.firebase.database.DataSnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Mohamad El Kadri
public class localDatabase {
    // Must be set
    public DataSnapshot dataSnapshot;

    public ArrayList<Owner> owners;
    public ArrayList<Customer> customers;
    public ArrayList<ArrayList<ArrayList<String>>> orders;

    private static localDatabase local;

    private localDatabase() {
        this.dataSnapshot = getSnapshot.snapshot.dataSnapshot;
        setOwners();
        setCustomers();
        setOrders();
    }

    // Only call after snapshot.dataSnapshot has been initialized
    public static localDatabase access() {
        if (local == null)
            local = new localDatabase();
        return local;
    }

    private void setOwners() {
        getInformation info = new getInformation(dataSnapshot);
        ArrayList<String> ownerIDs = info.getAllUsers(info.ownerKey);

        owners = new ArrayList<>();

        for (String ownerID : ownerIDs) {
            ArrayList<String> password_and_display = info.getPasswordAndDisplay(info.ownerKey, ownerID);

            Owner temp = new Owner(ownerID, password_and_display.get(0), password_and_display.get(1));

            temp.orders = info.getOrders(ownerID, info.ownerKey);

            // Set up the products in owner
            ArrayList<ArrayList<String>> products = info.getAllProducts(ownerID);

            ArrayList<Product> ownerProducts = new ArrayList<>();

            for (ArrayList<String> product : products) {
                Product t = new Product(product.get(0), product.get(2), product.get(1));
                ownerProducts.add(t);
            }
            temp.products = ownerProducts;
            //

            owners.add(temp);
        }
    }

    private void setCustomers() {
        getInformation info = new getInformation(dataSnapshot);
        ArrayList<String> customerIDs = info.getAllUsers(info.customerKey);

        customers = new ArrayList<>();

        for (String customerID : customerIDs) {
            ArrayList<String> password_and_display = info.getPasswordAndDisplay(info.customerKey, customerID);

            Customer temp = new Customer(customerID, password_and_display.get(0), password_and_display.get(1));

            temp.orders = info.getOrders(customerID, info.customerKey);

            customers.add(temp);
        }
    }

    public void setOrders() {
        getInformation info = new getInformation(dataSnapshot);
        orders = info.getAllOrders();
    }

    public Customer getCustomer(String userID) {
        for (Customer customer : customers) {
            if (customer.username == userID)
                return customer;
        }

        return null;
    }

    public Owner getOwner(String userID) {
        for (Owner owner : owners) {
            if (owner.username.equals(userID))
                return owner;
        }

        return null;
    }

    public ArrayList<ArrayList<String>> getOrder(String orderID) {
        for (ArrayList<ArrayList<String>> order : orders) {
            if (order.get(0).get(0).equals(orderID)) {
                return order;
            }
        }

        return null;
    }

    // Functions that might or might not be used. Delete if not used in app
    public ArrayList<Object> getUserIDs(String customer_or_userKey) {
        ArrayList<Object> user_ids = new ArrayList<>();

        if (customer_or_userKey.equals(getInformation.customerKey)) {
            for (Customer customer : customers) {
                user_ids.add(customer.username);
            }
        } else {
            for (Owner owner : owners) {
                user_ids.add(owner.username);
            }
        }

        return user_ids;
    }
}
