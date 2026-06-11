package com.store.model;

public class OrderItem extends OrderComponent {
    private final Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public double getPrice() {
        return product.getPrice() * quantity;
    }

    @Override
    public void print(int indentation) {
        System.out.printf("%s- %s x%d = R$ %.2f%n",
                " ".repeat(indentation), product.getName(), quantity, getPrice());
    }
}