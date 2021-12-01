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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void SignUpButton (View view){
        Intent intent = new Intent(this, SignUpActivity.class);

        startActivity(intent);
    }

    public void Login_LoginAsOwner_Button(View view){

        EditText username_editText = (EditText) findViewById(R.id.Login_Username_EditText);
        String username = username_editText.getText().toString();

        EditText password_editText = (EditText) findViewById(R.id.Login_Password_EditText);
        String password = password_editText.getText().toString();
        try {
            if (username.equals("") || password.equals("")) {
                throw new IllegalArgumentException
                        ("Missing fields, please complete all the required fields");
            }

            boolean user_found = false;

            // Check if username is already in localDatabase
            if (localDatabase.access().getOwner(username) != null) {
                user_found = true;
            }

            if (user_found) {
                if (localDatabase.access().getOwner(username).equals(password)) {
                    Intent intent = new Intent(this, PersonalStoreActivity.class)
                            .putExtra("username_key", username);
                    startActivity(intent);
                } else {
                    throw new IllegalArgumentException
                            ("Invalid Password");
                }
            } else {
                throw new IllegalArgumentException
                        ("Invalid Username");
            }
        }
        catch(Exception e){
            Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void Login_LoginAsCustomer_Button (View view){
        EditText username_editText = (EditText) findViewById(R.id.Login_Username_EditText);
        String username = username_editText.getText().toString();

        EditText password_editText = (EditText) findViewById(R.id.Login_Password_EditText);
        String password = password_editText.getText().toString();
        try {
            if (username.equals("") || password.equals("")) {
                throw new IllegalArgumentException
                        ("Missing fields, please complete all the required fields");
            }

            boolean user_found = false;

            // Check if username is already in localDatabase
            if (localDatabase.access().getCustomer(username) != null) {
                user_found = true;
            }

            if (user_found) {
                if (localDatabase.access().getCustomer(username).equals(password)) {
                    Intent intent = new Intent(this, AllStorePageActivity.class)
                            .putExtra("username_key", username);
                    startActivity(intent);
                } else {
                    throw new IllegalArgumentException
                            ("Invalid Password");
                }
            } else {
                throw new IllegalArgumentException
                        ("Invalid Username");
            }
        }
        catch(Exception e){
            Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}