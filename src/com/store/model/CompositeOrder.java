package com.store.model;

import java.util.ArrayList;
import java.util.List;

public class CompositeOrder extends OrderComponent {
    private final List<OrderComponent> components = new ArrayList<>();

    @Override
    public void add(OrderComponent component) {
        components.add(component);
    }

    @Override
    public double getPrice() {
        return components.stream()
                .mapToDouble(OrderComponent::getPrice)
                .sum();
    }

    @Override
    public void print(int indentation) {
        System.out.printf("%sPedido Composto (Total: R$ %.2f):%n", " ".repeat(indentation), getPrice());
        for (OrderComponent comp : components) {
            comp.print(indentation + 2);
        }
    }
}