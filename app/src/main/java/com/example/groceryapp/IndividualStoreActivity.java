package com.example.groceryapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class IndividualStoreActivity extends AppCompatActivity implements Serializable {
    Order cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_store);
        Intent intent = getIntent();
        String customer_id = intent.getStringExtra("cust_Id");
        String owner_id = intent.getStringExtra("owner_Id");
        cart = new Order(customer_id, owner_id);
        String store_name = getInformation.getInstance().getStoreName(owner_id);
        TextView storeName = findViewById(R.id.textViewStoreName);
        storeName.setText(store_name);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();

        ArrayList<ArrayList<String>> productList = cart.products_and_quantity;
        String owner_id = intent.getStringExtra("owner_Id");
            float total = 0;
            for (int i = 0; i < productList.size(); i++) {
                total += Float.parseFloat(productList.get(i).get(2)) * Float.parseFloat(productList.get(i).get(3));
            }
            TextView cartPrice = findViewById(R.id.textViewPrice);
            DecimalFormat df = new DecimalFormat("0.00");
            String price_total = "$" + df.format(total);
            cartPrice.setText(price_total);

        ArrayList<ArrayList<String>> temp = getInformation.getInstance().getAllProducts(owner_id);
        ArrayList<Product> products = new ArrayList<>();

        for (ArrayList<String> product : temp) {
            Product tmp = new Product(product.get(0), product.get(2), product.get(1));
            products.add(tmp);
        }

        IndividualStoreProductAdapter adapter = new IndividualStoreProductAdapter(this, products);
        ListView listView = (ListView) findViewById(R.id.productsListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Product selectedProduct = products.get(position);
            String product_name = (String) selectedProduct.product_Id;
            Intent send_intent = new Intent(this, ProductActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ORDER", (Serializable) cart);
            send_intent.putExtras(bundle);
            send_intent.putExtra("PRODUCT_ID", product_name);
            startActivity(send_intent);
        });
    }


    public void openCheckout(View view) {
        Intent send_intent = new Intent(this, CheckoutActivity.class);
        send_intent.putExtra("ORDER", (Serializable) cart);
        startActivity(send_intent);
    }
}