package com.example.groceryapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Mohamad El Kadri
public class CustomerOrderRecyclerViewAdapter extends RecyclerView.Adapter<CustomerOrderRecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "CustomerOrderRVA";

    // Holds the data for our buttons
    private final ArrayList<ArrayList<String>> orders;
    private final Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView storeName;
        TextView orderID;
        TextView status;

        ConstraintLayout constraintLayout;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            storeName = view.findViewById(R.id.store_name_customer);
            orderID = view.findViewById(R.id.order_id_customer);
            status = view.findViewById(R.id.completeStatus);

            constraintLayout = view.findViewById(R.id.constraintButtonLayoutCustomer);
        }
    }

    // initializes our button data
    public CustomerOrderRecyclerViewAdapter(Context context, ArrayList<ArrayList<String>> orders) {
        mContext = context;
        this.orders = orders;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, based on the xml layout given
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.customer_order_button, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder called");
        int index = viewHolder.getAdapterPosition();
        //Log.d(TAG, "adapter: " + index);

        Log.d(TAG, "onBindViewHolder: " + "BEFORE");

        // Set the viewHolders text at each position in orders
        String order_id = orders.get(index).get(0);
        String owner_id = getInformation.getInstance().getOrderOwner(order_id);


        viewHolder.storeName.setText(getInformation.getInstance().getStoreName(owner_id));
        viewHolder.orderID.setText("#" + order_id);

        String completion_status = orders.get(index).get(1);

        // Set colour depending on completion status
        if (completion_status.equalsIgnoreCase("Complete"))
            viewHolder.status.setTextColor(Color.parseColor("#38761d"));
        else
            viewHolder.status.setTextColor(Color.parseColor("#981414"));
        viewHolder.status.setText(completion_status);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return orders.size();
    }
}
