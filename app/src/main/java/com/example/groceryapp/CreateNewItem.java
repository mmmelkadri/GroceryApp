package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateNewItem extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_item);
        Reader.getInstance();
    }

    public void CreateNewItemButton(View view) {
        EditText new_name = (EditText) findViewById(R.id.editTextTextPersonName);
        String name_s = new_name.getText().toString();

        EditText new_brand = (EditText) findViewById(R.id.editTextTextNewBrand);
        String brand_s = new_brand.getText().toString();

        EditText new_price = (EditText) findViewById(R.id.editTextTextPrice);
        String price_s = new_price.getText().toString();

        String user_id = "KFC"; //CHANGE WHEN CONNECTED TO PARENT TO GET ID AS INTENT

        try {
            Product product = new Product(name_s, price_s, brand_s);
            product.writeToDatabase(user_id);
            Toast.makeText(CreateNewItem.this, "Product Succesfully added!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(CreateNewItem.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
