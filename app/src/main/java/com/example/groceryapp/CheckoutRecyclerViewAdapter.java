package com.example.groceryapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Mohamad El Kadri
public class CheckoutRecyclerViewAdapter extends RecyclerView.Adapter<CheckoutRecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "CheckoutRVA";

    // Holds the data for our buttons
    private ArrayList<ArrayList<String>> products;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemBrand;
        TextView itemPrice;
        TextView itemAmount;

        Button remove;
        Button increment;
        Button decrement;

        ConstraintLayout constraintLayout;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            itemName = view.findViewById(R.id.store_name);
            itemBrand = view.findViewById(R.id.item_brand);
            itemPrice = view.findViewById(R.id.completeStatus);
            itemAmount = view.findViewById(R.id.order_id);

            remove = view.findViewById(R.id.remove);
            increment = view.findViewById(R.id.plus);
            decrement = view.findViewById(R.id.minus);

            constraintLayout = view.findViewById(R.id.constraintButtonLayout);

        }
    }

    // initializes our button data
    public CheckoutRecyclerViewAdapter(Context context, ArrayList<ArrayList<String>> products) {
        mContext = context;
        this.products = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, based on the xml layout given
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.individual_product_button, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Log.d(TAG, "onBindViewHolder called");
        int index = viewHolder.getAdapterPosition();
        Log.d(TAG, "adapter: " + index);

        // object has been removed
        if (index == -1)
            return;

        // Set the viewHolders text at each position in products
        viewHolder.itemName.setText(String.valueOf(products.get(index).get(0)));

        viewHolder.itemBrand.setText(String.valueOf(products.get(index).get(1)));
        viewHolder.itemPrice.setText(String.valueOf(products.get(index).get(2)));
        viewHolder.itemAmount.setText(String.valueOf(products.get(index).get(4)));

        viewHolder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get quantity and increment in product
                int quantity = Integer.valueOf(products.get(index).get(4));
                products.get(index).set(4, String.valueOf(quantity + 1));
                viewHolder.itemAmount.setText(String.valueOf(products.get(index).get(4)));
            }
        });

        viewHolder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get quantity and decrement in product
                int quantity = Integer.valueOf(products.get(index).get(4));

                // minimum quantity must be 1
                if (quantity > 1)
                    products.get(index).set(4, String.valueOf(quantity - 1));
                    viewHolder.itemAmount.setText(String.valueOf(products.get(index).get(4)));
            }
        });

        viewHolder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                products.remove(index);
                notifyItemRemoved(index);
                notifyItemRangeChanged(index, products.size());
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return products.size();
    }
}
