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

public class BookListFragment extends Fragment {

    public interface ItemListener {
        public void itemSelected(Book b);
    }

    private MainActivity mActivity = null;

    public BookListFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ItemListener){
            this.mActivity = (MainActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView mRecyclerView = view.findViewById(R.id.book_list_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Book> books = Model.getInstance(mActivity).getBooks();

        RecyclerView.Adapter adapter = new BookListAdapter(mActivity, books);
        mRecyclerView.setAdapter(adapter);

        String url ="http://10.0.2.2/BookstoreWebApp/api/books";

        Model.getInstance(mActivity).requestBooks(adapter, url);
    }
}
