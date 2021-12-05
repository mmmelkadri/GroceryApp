package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{
    private LoginContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(new LoginModel(), this);
    }

    @Override
    public String getUsername() {
        EditText username_editText = findViewById(R.id.Login_Username_EditText);
        return username_editText.getText().toString();
    }

    @Override
    public String getPassword() {
        EditText password_editText = findViewById(R.id.Login_Password_EditText);
        return password_editText.getText().toString();
    }

    public void SignUpButton (View view){
        Intent intent = new Intent(this, SignUpActivity.class);

        startActivity(intent);
    }

    public void Login_LoginAsOwner_Button(View view){
        if (presenter.checkOwnerUsername() && presenter.checkOwnerPassword()) {
            Intent intent = new Intent(this, PersonalStoreActivity.class)
                    .putExtra("owner_Id", getUsername());
            startActivity(intent);
        }
    }

    public void Login_LoginAsCustomer_Button (View view) {
        if (presenter.checkCustomerUsername() && presenter.checkCustomerPassword()) {
            Intent intent = new Intent(this, AllStorePageActivity.class)
                    .putExtra("cust_Id", getUsername());
            startActivity(intent);
        }
    }

    public void toastError(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        // disables back button since previous activity is just a loading screen
    }
}