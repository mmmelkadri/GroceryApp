package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Mohamad El Kadri
public class ProductActivity extends AppCompatActivity implements Serializable {
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // receive Intent itemID, read item name, brand and price
        Intent intent = getIntent();
        String item_ID = intent.getStringExtra("PRODUCT_ID");

        ArrayList<String> product_info = getInformation.getInstance().getProduct(Order.getCart().ownerID, item_ID);

        // Create a product object to add to the order
        product = new Product(item_ID,
                product_info.get(2), product_info.get(1));

        // Set the values on the layout
        TextView itemName = findViewById(R.id.itemName);
        itemName.setText(product.product_Id);

        TextView itemBrand = findViewById(R.id.itemBrand);
        itemBrand.setText(product.brand);
    }

    public void addToCart(View view) {
        EditText num_items = findViewById(R.id.numItems);
        String num = num_items.getText().toString();

        // Check that the number given is valid. i.e. integer > 0
        Pattern pattern = Pattern.compile("[1-9]+\\d*");
        Matcher matcher = pattern.matcher(num);


        if (!matcher.matches()) {
            //display message stating problem
            CharSequence msg = "Please enter a valid amount.";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        } else {
            //add to users cart
            Order.getCart().add_to_order(product, num);
            //return to store page
            finish();
        }
    }
}