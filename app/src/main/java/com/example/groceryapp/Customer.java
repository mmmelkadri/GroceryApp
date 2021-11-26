package com.example.groceryapp;

import java.util.ArrayList;

public class Customer extends User{
    public Customer(String username, String password, String display_name){
        this.username = username;
        this.password = password;
        this.display_name = display_name;
    }

    public ArrayList<Object> getOrders(String user_ID) {
        return getInformation.info.getOrders(user_ID, getInformation.customerIDKey);
    }
}
