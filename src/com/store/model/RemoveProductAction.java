package com.store.model;

import com.store.repository.GenericRepository;

public class RemoveProductAction extends UndoAction {
    private final Product product;
    private final GenericRepository<Product> repository;

    public RemoveProductAction(Product product, GenericRepository<Product> repository) {
        this.product = product;
        this.repository = repository;
    }

    @Override
    public void undo() {
        repository.add(product);
    }

    @Override
    public String getDescription() {
        return "Remover " + product.getName();
    }
}