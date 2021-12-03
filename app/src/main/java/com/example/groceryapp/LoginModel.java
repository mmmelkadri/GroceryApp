package com.example.groceryapp;

import java.util.ArrayList;

public class LoginModel implements LoginContract.Model {

    public ArrayList<Object> allCustomers, allOwners;

    public LoginModel() {
        ArrayList<Object> allOwners = getInformation.getInstance().getAllUsers(getInformation.ownerKey);
        ArrayList<Object> allCustomers = getInformation.getInstance().getAllUsers(getInformation.customerKey);
    }
    @Override
    public boolean ownerExists(String username) {
        return allOwners.contains(username);
    }

    @Override
    public boolean customerExists(String username){
        return allCustomers.contains(username);
    }

    @Override
    public String fetch_owner_password(String username){
        return getInformation.getInstance().getPassword(getInformation.ownerKey, username);
    }

    @Override
    public String fetch_customer_password(String username){
        return getInformation.getInstance().getPassword(getInformation.customerKey, username);
    }

}
