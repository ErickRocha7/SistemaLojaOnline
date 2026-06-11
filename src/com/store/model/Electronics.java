package com.store.model;

public class Electronics extends Product implements Discountable {
    private String brand;
    private int warrantyMonths;

    public Electronics(String name, double price, String brand, int warrantyMonths) {
        super(name, price, Category.ELECTRONICS);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    public String getBrand() { return brand; }
    public int getWarrantyMonths() { return warrantyMonths; }

    @Override
    public void applyDiscount(double percentage) {
        double discounted = getPrice() * (1 - percentage / 100.0);
        setPrice(discounted);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Marca: %s, Garantia: %d meses", brand, warrantyMonths);
    }
}