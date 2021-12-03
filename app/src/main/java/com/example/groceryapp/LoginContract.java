package com.example.groceryapp;

public interface LoginContract {
        public interface Model{
                public boolean customerExists(String username);
                public boolean ownerExists(String username);
                public String fetch_customer_password(String username);
                public String fetch_owner_password(String username);
        }

        public interface View{
                public String getUsername();
                public String getPassword();

        }

        public interface Presenter{
                public boolean checkCustomerUsername();
                public boolean checkCustomerPassword();
                public boolean checkOwnerUsername();
                public boolean checkOwnerPassword();

        }
}
