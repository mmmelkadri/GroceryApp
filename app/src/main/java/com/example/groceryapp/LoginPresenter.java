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
            view.toastError("Missing username, please complete all the required fields");
        }
        else if (model.ownerExists(username)){
            return true;
        }
        else{
            view.toastError("Invalid Username");
        }
        return false;
    }

    @Override
    public boolean checkCustomerUsername() {
        String username = view.getUsername();
        if (username.equals("")) {
            view.toastError("Missing username, please complete all the required fields");
        }
        else if(model.customerExists(username)){
            return true;
        }
        else{
            view.toastError("Invalid Username");
        }
        return false;
    }

    @Override
    public boolean checkOwnerPassword() {
        String username = view.getUsername();
        String password = view.getPassword();
        if (password.equals("")) {
            view.toastError("Missing password, please complete all the required fields");
        }
        else if(model.fetch_owner_password(username).equals(password)){
            return true;
        }
        else{
            view.toastError("Invalid Password");
        }
        return false;
    }

    @Override
    public boolean checkCustomerPassword() {
        String username = view.getUsername();
        String password = view.getPassword();
        if (password.equals("")) {
            view.toastError("Missing password, please complete all the required fields");
        }
        else if(model.fetch_customer_password(username).equals(password)){
            return true;
        }
        else{
            view.toastError("Invalid Password");
        }
        return false;
    }

}
