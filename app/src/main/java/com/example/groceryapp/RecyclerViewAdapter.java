package com.example.groceryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Mohamad El Kadri
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    // Holds the data for our buttons
    private ArrayList<ArrayList<String>> products;

    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemBrand;
        TextView itemPrice;

        Button remove;
        Button increment;
        Button decrement;

        ConstraintLayout constraintLayout;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            itemName = itemView.findViewById(R.id.item_name);
            itemBrand = itemView.findViewById(R.id.item_brand);
            itemPrice = itemView.findViewById(R.id.price);

            remove = itemView.findViewById(R.id.remove);
            increment = itemView.findViewById(R.id.plus);
            decrement = itemView.findViewById(R.id.minus);

            constraintLayout = itemView.findViewById(R.id.constraintButtonLayout);

        }
    }

    // initializes our button data
    public RecyclerViewAdapter(Context context, ArrayList<ArrayList<String>> products) {
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
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
                /*
        int index = viewHolder.getAdapterPosition();
        // Set the viewHolders text at each position in products
        viewHolder.itemName.setText(products.get(index).get(0));
        viewHolder.itemBrand.setText(products.get(index).get(1));
        viewHolder.itemPrice.setText(products.get(index).get(2));


        viewHolder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get quantity and increment in product
                int quantity = Integer.valueOf(products.get(index).get(4));
                products.get(viewHolder.getAdapterPosition()).set(4, String.valueOf(quantity + 1));
            }
        });

        viewHolder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get quantity and decrement in product
                int quantity = Integer.valueOf(products.get(index).get(4));
                if (quantity > 0)
                    products.get(index).set(4, String.valueOf(quantity - 1));
            }
        });

        viewHolder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                products.remove(index);
                notifyItemRemoved(index);
                notifyItemRangeChanged(index, products.size());
            }
        });*/
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return products.size();
    }
}
