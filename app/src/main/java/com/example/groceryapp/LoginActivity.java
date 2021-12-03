package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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
        EditText username_editText = (EditText) findViewById(R.id.Login_Username_EditText);
        return username_editText.getText().toString();
    }

    @Override
    public String getPassword() {
        EditText password_editText = (EditText) findViewById(R.id.Login_Password_EditText);
        return password_editText.getText().toString();
    }

    public void SignUpButton (View view){
        Intent intent = new Intent(this, SignUpActivity.class);

        startActivity(intent);
    }

    public void Login_LoginAsOwner_Button(View view){
        try {
            if (presenter.checkOwnerUsername() && presenter.checkOwnerPassword()) {
                    Intent intent = new Intent(this, PersonalStoreActivity.class)
                            .putExtra("owner_Id", getUsername());
                    startActivity(intent);
                }
        }
        catch(Exception e){
            Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void Login_LoginAsCustomer_Button (View view){

        try {
            if (presenter.checkCustomerUsername() && presenter.checkCustomerPassword()) {
                Intent intent = new Intent(this, AllStorePageActivity.class)
                        .putExtra("cust_Id", getUsername());
                startActivity(intent);
            }
        }
        catch(Exception e){
            Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}