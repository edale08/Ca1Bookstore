package com.bookstore.ca1bookstore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static Model instance = null;
    public static final String TAG = "MyTag";

    private Context mCtx;
    private RequestQueue mRequestQueue;
    private final List<Book> mBooks;

    public static Model getInstance(Context context) {
        if (instance == null) {
            instance = new Model(context);
        }
        return instance;
    }

    public List<Book> getBooks() {
        return mBooks;
    }

    public Book findBookById(int book_id) {
        Book book = null;
        for (Book b : mBooks) {
            if (b.getId() == book_id) {
                book = b;
                break;
            }
        }
        return book;
    }

    public void addBooks(List<Book> books) {
        for (Book b : books) {
            this.mBooks.add(b);
        }
    }

    public void requestBooks(final RecyclerView.Adapter adapter, String url) {
        JsonArrayRequest stringRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {

                        try {
                            for (int i = 0; i != jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                String title = jsonObject.getString("title");
                                String author = jsonObject.getString("author");
                                String isbn = jsonObject.getString("isbn");
                                int year = jsonObject.getInt("year");
                                double price = jsonObject.getDouble("price");

                                Book book = new Book(id, title, author, isbn, year, price);
                                mBooks.add(book);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    System.out.println(error);
                    }
                });

        // Add the request to the RequestQueue.
        this.addRequest(stringRequest);
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    private <T> void addRequest(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelAll() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }
    }

//    private final List<Book> mBooks;
    private final ShoppingCart mCart;

    private Model(Context context) {
        mBooks = new ArrayList<>();
        getBooks().add(new Book(1, "Learning PHP: A Gentle Introduction to the Web's Most Popular Language", "David Sklar", "9781491933572", 2016, 41.99));
        getBooks().add(new Book(2, "Beginning PHP 5.3", "Matt Doyle", "9780470413968", 2009, 36.40));
        getBooks().add(new Book(3, "Beginning JavaScript, 5th Edition", "Jeremy McPeak", "9781118903339", 2015, 40.90));
        getBooks().add(new Book(4, "Learning JavaScript, 3rd Edition", "Ethan Brown", "9781491914915", 2016, 39.99));
        getBooks().add(new Book(5, "100 Things Every Designer Needs to Know About People", "Susan Weinschenk", "9780321767530", 2011, 26.31));
        getBooks().add(new Book(6, "Smashing CSS: Professional Techniques for Modern L", "Eric Meyer", "9780470684160", 2010, 28.90));
        getBooks().add(new Book(7, "HTML5: The Missing Manual, 2nd Edition", "Matthew MacDonald", "9781449363260", 2014, 34.99));
        getBooks().add(new Book(8, "Stylin' with CSS: A Designer's Guide, 3rd Edition", "Charles Wyke-Smith", "9780321858474", 2012, 25.76));
        getBooks().add(new Book(9, "Introducing HTML5, 2nd Edition", "Bruce Lawson", "9780321784421", 2011, 17.95));
        getBooks().add(new Book(10, "CSS: The Missing Manual, 4th Edition", "David Sawyer McFarland", "9781491918050", 2015, 35.72));
        getBooks().add(new Book(11, "HTML5 Foundations", "Matt West", "9781118356555", 2012, 36.55));

        mCtx = context;
        mRequestQueue = getRequestQueue();
        //mBooks = new ArrayList<Book>();
        mCart = new ShoppingCart();
    }

    public ShoppingCart getCart() {
        return mCart;
    }
}
