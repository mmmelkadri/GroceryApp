package com.example.groceryapp;

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.Model model;
    private LoginContract.View view;

    public LoginPresenter(LoginContract.Model model, LoginContract.View view){
        this.model = model;
        this.view = view;

    }

    @Override
    public boolean checkOwnerUsername() {
        String username = view.getUsername();
        if (username.equals("")) {
            throw new IllegalArgumentException
                    ("Missing username, please complete all the required fields");
        }
        if(model.ownerExists(username)){
            return true;
        }
        else{
            throw new IllegalArgumentException
                    ("Invalid Username");
        }

    }

    @Override
    public boolean checkCustomerUsername() {
        String username = view.getUsername();
        if (username.equals("")) {
            throw new IllegalArgumentException
                    ("Missing username, please complete all the required fields");
        }
        if(model.customerExists(username)){
            return true;
        }
        else{
            throw new IllegalArgumentException
                    ("Invalid Username");
        }

    }

    @Override
    public boolean checkOwnerPassword() {
        String username = view.getUsername();
        String password = view.getPassword();
        if (password.equals("")) {
            throw new IllegalArgumentException
                    ("Missing password, please complete all the required fields");
        }
        if(model.fetch_owner_password(username).equals(password)){
            return true;
        }
        else{
            throw new IllegalArgumentException
                    ("Invalid Password");
        }

    }

    @Override
    public boolean checkCustomerPassword() {
        String username = view.getUsername();
        String password = view.getPassword();
        if (password.equals("")) {
            throw new IllegalArgumentException
                    ("Missing password, please complete all the required fields");
        }
        if(model.fetch_customer_password(username).equals(password)){
            return true;
        }
        else{
            throw new IllegalArgumentException
                    ("Invalid Password");
        }

    }

}
