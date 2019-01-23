package com.bookstore.ca1bookstore;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<ShoppingCartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<ShoppingCartItem>();
    }

    public List<ShoppingCartItem> getItems() { return this.items; }

    public double getTotalPrice() {
        double total = 0.0;
        for (int i = 0; i != this.items.size(); i++) {
            ShoppingCartItem item = this.items.get(i);
            total += item.getTotalPrice();
        }
        return total;
    }

    public void add(Book book, int qty) {
        ShoppingCartItem item;
        item = this.contains(book);
        if (item != null) {
            int oldQuantity = item.getQuantity();
            item.setQuantity(oldQuantity + qty);
        }
        else {
            item = new ShoppingCartItem(book, qty);
            this.items.add(item);
        }
    }

    public void update(Book book, int qty) {
        ShoppingCartItem item;
        item = this.contains(book);
        if (item != null) {
            if (qty > 0) {
                item.setQuantity(qty);
            }
            else if (qty == 0) {
                this.items.remove(item);
            }
        }
    }

    private ShoppingCartItem contains(Book b) {
        for (int i = 0; i != this.items.size(); i++) {
            ShoppingCartItem item = this.items.get(i);
            if (item.getBook().equals(b)) {
                return item;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return this.items.size() == 0;
    }

    public void removeAll() {
        this.items.clear();
    }
}
