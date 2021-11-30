package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class IndividualStoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_store);
        Intent intent = getIntent();
        //String customer_id = intent.getStringExtra("USER_ID");
        ArrayList<ArrayList<String>> productList = new ArrayList<ArrayList<String>>();
        DataSnapshot cart_products = Reader.getInstance().readSnapshot("Customers", customer_id, "cart", "products");
        for (DataSnapshot product : cart_products.getChildren()) {
            ArrayList<String> temp = new ArrayList<String>();
            temp.add(product.child("product_id").getValue(String.class));
            temp.add(product.child("quantity").getValue(String.class));
            productList.add(temp);
        }
        float total = 0;
        for (int i = 0; i < productList.size(); i++) {
            total += Float.parseFloat(productList.get(i).get(1));
        }
        TextView cartPrice = findViewById(R.id.textViewPrice);
        String price_total = "$" + total;
        cartPrice.setText(price_total);
        //String store_name = intent.getStringExtra("STORE_NAME");
        TextView storeName = findViewById(R.id.textViewStoreName);
        storeName.setText(store_name);
        ArrayList<Object> owners = getInformation.getInstance().getAllUsers("Owners");
        String owner = "";

        for (int i = 0; i < owners.size(); i++) {
            if (getInformation.getInstance().getStoreName((String) owners.get(i)) == store_name) {
                owner = (String) owners.get(i);
                break;
            }
        }
        ArrayList<Object> products = getInformation.getInstance().getAllProducts(owner);
        IndividualStoreProductAdapter adapter = new IndividualStoreProductAdapter(this, products);
        ListView listView = (ListView) findViewById(R.id.productsListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            ArrayList<String> selectedProduct = (ArrayList<String>) products.get(position);
            String product_name = (String) selectedProduct.get(0);
            Intent send_intent = new Intent(this, ProductActivity.class);
            //send_intent.putExtra(USER_ID, customer_id);
            //send_intent.putExtra(PRODUCT_ID, product_name);
            startActivity(send_intent);
        });
    }


    public void openCheckout(View view) {
        /* TODO: Use get_intent to retrieve username and send this information to
            CheckoutActivity so user's cart can be loaded.
        */
        Intent get_intent = getIntent();
        //String customer_id = intent.getStringExtra(USER_ID);
        Intent send_intent = new Intent(this, CheckoutActivity.class);
        //send_intent.putExtra(USER_ID, customer_id);
        startActivity(send_intent);
    }
}