package com.bookstore.ca1bookstore;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CartListFragment extends Fragment {

    public interface ItemListener {
        public void itemSelected(ShoppingCartItem b);
    }

    private CartActivity mCartActivity = null;

    public CartListFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ItemListener){
            this.mCartActivity = (CartActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView mRecyclerView = view.findViewById(R.id.cart_list_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mCartActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<ShoppingCartItem> items = Model.getInstance(mCartActivity).getCart().getItems();

        RecyclerView.Adapter adapter = new CartListAdapter(mCartActivity, items);
        mRecyclerView.setAdapter(adapter);
    }
}
