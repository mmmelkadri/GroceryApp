package com.example.groceryapp;

public interface LoginContract {
        interface Model{
                boolean customerExists(String username);
                boolean ownerExists(String username);
                String fetch_customer_password(String username);
                String fetch_owner_password(String username);
        }

        interface View{
                String getUsername();
                String getPassword();
                void toastError(String message);
        }

        interface Presenter{
                boolean checkCustomerUsername();
                boolean checkCustomerPassword();
                boolean checkOwnerUsername();
                boolean checkOwnerPassword();

        }
}
