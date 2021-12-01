package com.example.groceryapp;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class localDatabase {
    // Must be set
    public DataSnapshot dataSnapshot;

    private ArrayList<Owner> owners;
    private ArrayList<Customer> customers;
    private static localDatabase local;

    private localDatabase() {
        owners = setOwners();
        customers = setCustomers();
    }

    // MUST be called in main activity
    public void setSnapshot(DataSnapshot dataSnapshot) {
        this.dataSnapshot = dataSnapshot;
    }

    public static localDatabase access() {
        if (local == null)
            local = new localDatabase();
        return local;
    }

    private ArrayList<Owner> setOwners() {
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

    private ArrayList<Customer> setCustomers() {
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
}
