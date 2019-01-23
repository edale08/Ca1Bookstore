package com.bookstore.ca1bookstore;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class BookDetailActivity extends AppCompatActivity {

    private int book_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Booktaku");

        Intent intent = getIntent();
        book_id = intent.getIntExtra(BookDetailFragment.EXTRA_BOOK_ID, -1);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.book_details_one_pane, BookDetailFragment.newInstance(book_id));
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_shoppingCart:
                Toast.makeText(this, "Shopping Cart selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
