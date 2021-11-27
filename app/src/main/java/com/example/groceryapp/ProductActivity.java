package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Mohamad El Kadri
public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // receive Intent itemID, read item name, description and push to textView boxes
    }

    public void addToCart(View view) {
        EditText num_items = (EditText) findViewById(R.id.numItems);
        String num = num_items.getText().toString();

        // Check that the number given is valid. i.e. integer > 0
        Pattern pattern = Pattern.compile("[1-9]+\\d*");
        Matcher matcher = pattern.matcher(num);

        if (!matcher.matches()) {
            //display message stating problem
            CharSequence error = "Please enter a valid amount.";
            Toast toast = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT);
        } else {
            //add to users cart


            //return to store page
            finish();
        }
    }
}