package com.store.model;

import java.util.regex.Pattern;

public abstract class Product implements Identifiable, Searchable {
    private static int nextId = 1;
    private final String id;
    private String name;
    private double price;
    private final Category category;

    public Product(String name, double price, Category category) {
        this.id = String.format("PROD-%04d", nextId++);
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean matchesKeyword(String keyword) {
        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(name).find() || pattern.matcher(id).find();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - R$ %.2f", id, name, category, price);
    }
}