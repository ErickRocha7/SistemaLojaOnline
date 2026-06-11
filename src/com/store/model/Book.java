package com.store.model;

public class Book extends Product implements Discountable {
    private String author;
    private String isbn;

    public Book(String name, double price, String author, String isbn) {
        super(name, price, Category.BOOK);
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public void applyDiscount(double percentage) {
        double discounted = getPrice() * (1 - percentage / 100.0);
        setPrice(discounted);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Autor: %s, ISBN: %s", author, isbn);
    }
}