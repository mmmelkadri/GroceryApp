package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void SignUpSignUpButton (View view){
        EditText username_edit =  (EditText) findViewById(R.id.Signup_Username_EditText);
        String username_s = username_edit.getText().toString();

        EditText password_edit =  (EditText) findViewById(R.id.Signup_Password_EditText);
        String password_s = password_edit.getText().toString();

        EditText public_name_edit =  (EditText) findViewById(R.id.Signup_PublicName_EditText);
        String public_s = public_name_edit.getText().toString();

        Spinner spinner = (Spinner) findViewById(R.id.Signup_UserDropdown);
        String spinner_s = String.valueOf(spinner.getSelectedItem());

        try {
            if (spinner_s.equals("Owner")) {
                Owner owner = new Owner(password_s, public_s);
                owner.writeToDatabase(username_s);
                Toast.makeText(LoginActivity.this,"done", Toast.LENGTH_SHORT).show();
            }
            else {
                Customer customer = new Customer(password_s, public_s);
                customer.writeToDatabase(username_s);
            }
        }
        catch(Exception e){
            Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}