package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Mohamad El Kadri
public class ProductActivity extends AppCompatActivity {
    private String user_ID;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // receive Intent itemID, read item name, brand and price
        Intent intent = getIntent();
        String owner_ID = intent.getStringExtra("OWNER_ID");
        String item_ID = intent.getStringExtra("ITEM_ID");

        ArrayList<Object> product_info = getInformation.getInstance().getIndividualProduct(owner_ID, item_ID);

        // Create a product object to add to the order
        product = new Product(item_ID, (String) product_info.get(0),
                (String) product_info.get(2), (String) product_info.get(1));

        // Set the values on the layout
        TextView itemName = (TextView) findViewById(R.id.itemName);
        itemName.setText(product.product_name);

        TextView itemBrand = (TextView) findViewById(R.id.itemBrand);
        itemName.setText(product.brand);
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
            //order.addToOrder(product, num);
            //return to store page
            finish();
        }
    }
}