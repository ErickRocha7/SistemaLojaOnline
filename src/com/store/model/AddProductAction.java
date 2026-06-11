package com.store.model;

import com.store.repository.GenericRepository;

public class AddProductAction extends UndoAction {
    private final Product product;
    private final GenericRepository<Product> repository;

    public AddProductAction(Product product, GenericRepository<Product> repository) {
        this.product = product;
        this.repository = repository;
    }

    @Override
    public void undo() {
        repository.remove(product.getId());
    }

    @Override
    public String getDescription() {
        return "Adicionar " + product.getName();
    }
}