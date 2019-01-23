package com.bookstore.ca1bookstore;

public class ShoppingCartItem {
    private Book book;
    private int quantity;

    public ShoppingCartItem(Book book, int qty) {
        this.book = book;
        this.quantity = qty;
    }

    public Book getBook() { return this.book; }
    public int getQuantity() { return this.quantity; }
    public double getTotalPrice() { return this.book.getPrice() * this.quantity; }
    public void setQuantity(int qty) { this.quantity = qty; }
}
