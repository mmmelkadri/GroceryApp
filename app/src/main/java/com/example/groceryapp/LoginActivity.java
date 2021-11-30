package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

            ArrayList<Object> all_users = getInformation.getInstance().getAllUsers(getInformation.ownerKey);
            boolean user_found = false;
            for (Object i : all_users) {
                if (username.equals(i)) {
                    user_found = true;
                    break;
                }
            }

            if (user_found) {
                DataSnapshot password_snap = Reader.getInstance()
                        .readSnapshot(getInformation.ownerKey, username, "password");
                if (password_snap.getValue().toString().equals(password)) {
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

            ArrayList<Object> all_users = getInformation.getInstance()
                    .getAllUsers(getInformation.customerKey);
            boolean user_found = false;
            for (Object i : all_users) {
                if (username.equals(i)) {
                    user_found = true;
                    break;
                }
            }

            if (user_found) {
                DataSnapshot password_snap = Reader.getInstance()
                        .readSnapshot(getInformation.customerKey, username, "password");
                if (password_snap.getValue().toString().equals(password)) {
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