package com.example.groceryapp;

import java.util.ArrayList;

public class Order {
    String orderId;
    String customerId;
    String ownerID;
    ArrayList<Object> products;

    public Order(String customerId, String ownerID){
        this.customerId = customerId;
        this.ownerID = ownerID;
    }
    //public void add_to_order()

}
