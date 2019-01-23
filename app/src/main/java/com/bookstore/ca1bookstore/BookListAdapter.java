package com.bookstore.ca1bookstore;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {
    private List<Book> mBooks;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout mBookItem;
        public TextView mTitleTextView;
        public TextView mAuthorTextView;

        public ViewHolder(View v) {
            super(v);
            mBookItem = v.findViewById(R.id.book_list_item);
            mTitleTextView = v.findViewById(R.id.title);
            mAuthorTextView = v.findViewById(R.id.author);
        }
    }

    private MainActivity mActivity;

    public BookListAdapter(MainActivity activity, List<Book> books) {
        mActivity = activity;
        mBooks = books;
    }

    @Override
    public BookListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Book b = mBooks.get(position);
        holder.mTitleTextView.setText(b.getTitle());
        holder.mAuthorTextView.setText(b.getAuthor());
        holder.mBookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.itemSelected(b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }
}