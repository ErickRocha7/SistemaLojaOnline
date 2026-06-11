package com.store.model;

public abstract class OrderComponent {
    public abstract double getPrice();

    public abstract void print(int indentation);

    public void add(OrderComponent component) {
        throw new UnsupportedOperationException("Operação não suportada em folha");
    }
}