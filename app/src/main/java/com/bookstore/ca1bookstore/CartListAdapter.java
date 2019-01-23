package com.bookstore.ca1bookstore;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private List<ShoppingCartItem> mItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout mCartItem;
        public TextView mBookTextView;
        public TextView mQuantityTextView;
        public TextView mTotalPriceTextView;
        public Button mRemoveBtn;
        public Button mAddBtn;

        public ViewHolder(View v) {
            super(v);
            mCartItem = v.findViewById(R.id.cart_list_item);
            mBookTextView = v.findViewById(R.id.book);
            mQuantityTextView = v.findViewById(R.id.quantity);
            mTotalPriceTextView = v.findViewById(R.id.price);
            mRemoveBtn = v.findViewById(R.id.btn_remove_from_cart);
            mAddBtn = v.findViewById(R.id.btn_add_one_to_cart);
        }
    }

    private CartActivity mCartActivity;

    public CartListAdapter(CartActivity activity, List<ShoppingCartItem> items) {
        mCartActivity = activity;
        mItems = items;
    }

    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ShoppingCartItem c = mItems.get(position);
        holder.mBookTextView.setText((CharSequence) c.getBook().getTitle());
        holder.mQuantityTextView.setText("quantity: " + c.getQuantity());
        holder.mTotalPriceTextView.setText("total price: " + c.getTotalPrice());
        holder.mRemoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = c.getQuantity();
                if (qty > 1) {
                    c.setQuantity(qty - 1);
                }
                else if (qty == 1) {
                    mItems.remove(c);
                }
                CartListAdapter.this.notifyDataSetChanged();
            }
        });
        holder.mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = c.getQuantity();
                c.setQuantity(qty + 1);

                CartListAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}